package net.mokus.wathextras.block.custom;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class CandelabreBlock extends Block {
    protected static final MapCodec<SimpleParticleType> PARTICLE_TYPE_CODEC = Registries.PARTICLE_TYPE
            .getCodec()
            .comapFlatMap(
                    particleType -> particleType instanceof SimpleParticleType simpleParticleType
                            ? DataResult.success(simpleParticleType)
                            : DataResult.error(() -> "Not a SimpleParticleType: " + particleType),
                    particleType -> particleType
            )
            .fieldOf("particle_options");
    public static final MapCodec<CandelabreBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(PARTICLE_TYPE_CODEC.forGetter(block -> block.particle), createSettingsCodec()).apply(instance, CandelabreBlock::new)
    );

    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = state -> state.get(LIT) ? 7 : 0;

    //BTW yes this is basically the torch code

    protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 15.0, 15.0);
    protected final SimpleParticleType particle;
    public CandelabreBlock(SimpleParticleType particle,Properties properties) {
        super(properties);
        this.particle = particle;
        this.setDefaultState(this.stateDefinition.getDefaultState().with(LIT, false));
    }

    @Override
    protected MapCodec<? extends CandelabreBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected BlockState getStateForNeighborUpdate(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos
    ) {
        return direction == Direction.DOWN && !this.canPlaceAt(state, world, pos)
                ? Blocks.AIR.getDefaultState()
                : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, LevelReader world, BlockPos pos) {
        return sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    public void randomDisplayTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (this.isLit(state)) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.9;
            double z = pos.getZ() + 0.5;
            float f = RandomSource.nextFloat();
            if (f < 0.3F) {
                world.addParticle(ParticleTypes.SMOKE, x, y + 0.3, z, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.SMOKE, x - 0.25, y, z, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.SMOKE, x + 0.25, y, z, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.SMOKE, x, y, z - 0.25, 0.0, 0.0, 0.0);
                world.addParticle(ParticleTypes.SMOKE, x, y, z + 0.25, 0.0, 0.0, 0.0);
                if (f < 0.17F) {
                    world.playSound(
                            x,
                            y,
                            z,
                            SoundEvents.BLOCK_CANDLE_AMBIENT,
                            SoundSource.BLOCKS,
                            1.0F + RandomSource.nextFloat(),
                            RandomSource.nextFloat() * 0.7F + 0.3F,
                            false
                    );
                }
            }
            //IS this a bit stupid? Yes.

            //Middle
            world.addParticle(this.particle, x, y + 0.1, z, 0.0, 0.0, 0.0);
            //West
            world.addParticle(this.particle, x - 0.25, y, z, 0.0, 0.0, 0.0);
            //East
            world.addParticle(this.particle, x  + 0.25, y, z, 0.0, 0.0, 0.0);
            //North
            world.addParticle(this.particle, x, y, z- 0.25, 0.0, 0.0, 0.0);
            //South
            world.addParticle(this.particle, x, y, z + 0.25, 0.0, 0.0, 0.0);
        }
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack mainHand = player.getStackInHand(hand);
        if (player.getAbilities().allowModifyWorld && (mainInteractionHand.getItem() instanceof FlintAndSteelItem) && !this.isLit(state)) {
            setLit(world,state,pos,true);
            return ItemInteractionResult.sidedSuccess(true);
        } else if (stack.isEmpty() && player.getAbilities().allowModifyWorld && this.isLit(state)) {
            extinguish(player,state,world,pos);
            return ItemInteractionResult.sidedSuccess(true);
        }
        else {
            return super.useItemOn(stack, state, world, pos, player, hand, hit);
        }

    }


    protected boolean isLit(BlockState state) {
        return state.get(LIT);
    }

    public static void extinguish(@Nullable Player player, BlockState state, LevelAccessor world, BlockPos pos) {
        setLit(world, state, pos, false);
        if (state.getBlock() instanceof AbstractCandleBlock) {
            world.addParticle(ParticleTypes.SMOKE, pos.getX(), pos.getY(), pos.getZ(), 0.0, 0.1F, 0.0);
        }

        world.playSound(null, pos, SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
        world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    }

    private static void setLit(LevelAccessor world, BlockState state, BlockPos pos, boolean lit) {
        world.setBlockState(pos, state.with(LIT, lit), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
