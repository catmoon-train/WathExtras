package net.mokus.wathextras.block.custom;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.LevelAccessor;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
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

    public static final BooleanProperty LIT = Properties.LIT;
    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = state -> state.get(LIT) ? 7 : 0;

    //BTW yes this is basically the torch code

    protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 15.0, 15.0);
    protected final SimpleParticleType particle;
    public CandelabreBlock(SimpleParticleType particle,Settings settings) {
        super(settings);
        this.particle = particle;
        this.setDefaultState(this.stateManager.getDefaultState().with(LIT, false));
    }

    @Override
    protected MapCodec<? extends CandelabreBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, CollisionContext context) {
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
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    public void randomDisplayTick(BlockState state, Level level, BlockPos pos, Random random) {
        if (this.isLit(state)) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.9;
            double z = pos.getZ() + 0.5;
            float f = random.nextFloat();
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
                            1.0F + random.nextFloat(),
                            random.nextFloat() * 0.7F + 0.3F,
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
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, Hand hand, BlockHitResult hit) {
        ItemStack mainHand = player.getStackInHand(hand);
        if (player.getAbilities().allowModifyWorld && (mainHand.getItem() instanceof FlintAndSteelItem) && !this.isLit(state)) {
            setLit(world,state,pos,true);
            return ItemInteractionResult.sidedSuccess(true);
        } else if (stack.isEmpty() && player.getAbilities().allowModifyWorld && this.isLit(state)) {
            extinguish(player,state,world,pos);
            return ItemInteractionResult.sidedSuccess(true);
        }
        else {
            return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
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
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
