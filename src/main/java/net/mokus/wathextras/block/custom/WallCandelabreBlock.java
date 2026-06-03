package net.mokus.wathextras.block.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.ToIntFunction;

public class WallCandelabreBlock extends CandelabreBlock{
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = state -> state.getValue(LIT) ? 7 : 0;
    public static final MapCodec<WallCandelabreBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(PARTICLE_TYPE_CODEC.forGetter(block -> block.particle), propertiesCodec()).apply(instance, WallCandelabreBlock::new)
    );
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES = Maps.newEnumMap(
            ImmutableMap.of(
                    Direction.NORTH,
                    Block.box(5.5, 3.0, 11.0, 10.5, 13.0, 16.0),
                    Direction.SOUTH,
                    Block.box(5.5, 3.0, 0.0, 10.5, 13.0, 5.0),
                    Direction.WEST,
                    Block.box(11.0, 3.0, 5.5, 16.0, 13.0, 10.5),
                    Direction.EAST,
                    Block.box(0.0, 3.0, 5.5, 5.0, 13.0, 10.5)
            )
    );


    public WallCandelabreBlock(SimpleParticleType particle, Properties properties) {
        super(particle, properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(LIT, false));
    }

    @Override
    protected MapCodec<? extends CandelabreBlock> codec() {
        return CODEC;
    }

    @Override
    public String getDescriptionId() {
        return this.asItem().getDescriptionId();
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return getBoundingShape(state);
    }

    public static VoxelShape getBoundingShape(BlockState state) {
        return BOUNDING_SHAPES.get(state.getValue(FACING));
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return canPlaceAt(world, pos, state.getValue(FACING));
    }

    public static boolean canPlaceAt(LevelReader world, BlockPos pos, Direction facing) {
        BlockPos blockPos = pos.relative(facing.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isFaceSturdy(world, blockPos, facing);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState blockState = this.defaultBlockState();
        LevelReader levelReader = ctx.getLevel();
        BlockPos blockPos = ctx.getClickedPos();
        Direction[] directions = ctx.getNearestLookingDirections();

        for (Direction direction : directions) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction2 = direction.getOpposite();
                blockState = blockState.setValue(FACING, direction2);
                if (blockState.canSurvive(levelReader, blockPos)) {
                    return blockState;
                }
            }
        }

        return null;
    }

    @Override
    protected BlockState updateShape(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos
    ) {
        return direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(world, pos) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (this.isLit(state)){
            Direction direction = state.getValue(FACING);
            double d = pos.getX() + 0.5;
            double e = pos.getY() + 0.6;
            double f = pos.getZ() + 0.5;
            Direction direction2 = direction.getOpposite();

            double x = (d + 0.30 * direction2.getStepX());
            double y = e + 0.22;
            double z = (f + 0.30 * direction2.getStepZ());

            double zl, zr, xl, xr;
            double yB = y + 0.1;

            if (direction == Direction.EAST || direction == Direction.WEST) {
                zl = z + 0.12;
                zr = z - 0.12;
                xl = x + 0.10 * direction2.getStepX();
                xr = x + 0.10 * direction2.getStepX();
            } else {
                zl = z + 0.10 * direction2.getStepZ();
                zr = z + 0.10 * direction2.getStepZ();
                xl = x + 0.12;
                xr = x - 0.12;
            }

            //west and east needs dif calc AAAA

            float ff = random.nextFloat();
            if (ff < 0.3F) {
                level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);
                level.addParticle(ParticleTypes.SMOKE, xl, yB, zr, 0.0, 0.0, 0.0);
                level.addParticle(ParticleTypes.SMOKE, xr, yB, zr, 0.0, 0.0, 0.0);
                if (ff < 0.17F) {
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
            level.addParticle(this.particle, x, y, z, 0.0, 0.0, 0.0);
            level.addParticle(this.particle, xl, yB, zl, 0.0, 0.0, 0.0);
            level.addParticle(this.particle, xr, yB, zr, 0.0, 0.0, 0.0);
        }
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }
}
