package net.mokus.wathextras.block.custom;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
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
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class CandelabreBlock extends Block {
    protected static final MapCodec<SimpleParticleType> PARTICLE_TYPE_CODEC = BuiltInRegistries.PARTICLE_TYPE
            .byNameCodec()
            .comapFlatMap(
                    particleType -> particleType instanceof SimpleParticleType simpleParticleType
                            ? DataResult.success(simpleParticleType)
                            : DataResult.error(() -> "Not a SimpleParticleType: " + particleType),
                    particleType -> particleType
            )
            .fieldOf("particle_options");
    public static final MapCodec<CandelabreBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(PARTICLE_TYPE_CODEC.forGetter(block -> block.particle), propertiesCodec()).apply(instance, CandelabreBlock::new)
    );

    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = state -> state.getValue(LIT) ? 7 : 0;

    //BTW yes this is basically the torch code

    protected static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 15.0, 15.0);
    protected final SimpleParticleType particle;
    public CandelabreBlock(SimpleParticleType particle,Properties properties) {
        super(properties);
        this.particle = particle;
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false));
    }

    @Override
    protected MapCodec<? extends CandelabreBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected BlockState updateShape(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos
    ) {
        return direction == Direction.DOWN && !this.canSurvive(state, world, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return canSupportCenter(world, pos.below(), Direction.UP);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (this.isLit(state)) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 0.9;
            double z = pos.getZ() + 0.5;
            float f = random.nextFloat();
            if (f < 0.3F) {
                level.addParticle(ParticleTypes.SMOKE, x, y + 0.3, z, 0.0, 0.0, 0.0);
                level.addParticle(ParticleTypes.SMOKE, x - 0.25, y, z, 0.0, 0.0, 0.0);
                level.addParticle(ParticleTypes.SMOKE, x + 0.25, y, z, 0.0, 0.0, 0.0);
                level.addParticle(ParticleTypes.SMOKE, x, y, z - 0.25, 0.0, 0.0, 0.0);
                level.addParticle(ParticleTypes.SMOKE, x, y, z + 0.25, 0.0, 0.0, 0.0);
                if (f < 0.17F) {
                    level.playSound(
                            null,
                            x,
                            y,
                            z,
                            SoundEvents.CANDLE_AMBIENT,
                            SoundSource.BLOCKS,
                            1.0F + random.nextFloat(),
                            random.nextFloat() * 0.7F + 0.3F
                    );
                }
            }
            //IS this a bit stupid? Yes.

            //Middle
            level.addParticle(this.particle, x, y + 0.1, z, 0.0, 0.0, 0.0);
            //West
            level.addParticle(this.particle, x - 0.25, y, z, 0.0, 0.0, 0.0);
            //East
            level.addParticle(this.particle, x  + 0.25, y, z, 0.0, 0.0, 0.0);
            //North
            level.addParticle(this.particle, x, y, z- 0.25, 0.0, 0.0, 0.0);
            //South
            level.addParticle(this.particle, x, y, z + 0.25, 0.0, 0.0, 0.0);
        }
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack mainHand = player.getItemInHand(hand);
        if (player.getAbilities().mayBuild && (mainHand.getItem() instanceof FlintAndSteelItem) && !this.isLit(state)) {
            setLit(level,state,pos,true);
            return ItemInteractionResult.sidedSuccess(true);
        } else if (stack.isEmpty() && player.getAbilities().mayBuild && this.isLit(state)) {
            extinguish(player,state,level,pos);
            return ItemInteractionResult.sidedSuccess(true);
        }
        else {
            return super.useItemOn(stack, state, level, pos, player, hand, hit);
        }

    }


    protected boolean isLit(BlockState state) {
        return state.getValue(LIT);
    }

    public static void extinguish(@Nullable Player player, BlockState state, LevelAccessor world, BlockPos pos) {
        setLit(world, state, pos, false);
        if (state.getBlock() instanceof AbstractCandleBlock) {
            world.addParticle(ParticleTypes.SMOKE, pos.getX(), pos.getY(), pos.getZ(), 0.0, 0.1F, 0.0);
        }

        world.playSound(null, pos, SoundEvents.CANDLE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, 1.0F);
        world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
    }

    private static void setLit(LevelAccessor world, BlockState state, BlockPos pos, boolean lit) {
        world.setBlock(pos, state.setValue(LIT, lit), Block.UPDATE_ALL | Block.UPDATE_CLIENTS);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }
}
