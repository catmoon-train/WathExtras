package net.mokus.wathextras.block.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import io.wifi.starrailexpress.content.block.HorizontalFacingMountableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class BenchBlock extends HorizontalFacingMountableBlock {
    public static final MapCodec<BenchBlock> CODEC = simpleCodec(BenchBlock::new);
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<PartType> PART = EnumProperty.create("part", PartType.class);

    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES = Maps.newEnumMap(
            ImmutableMap.of(
                    Direction.NORTH, Block.box(1.0, 0.0, 0.0, 16.0, 13.0, 12.0),
                    Direction.SOUTH, Block.box(1.0, 0.0, 4.0, 16.0, 13.0, 16.0),
                    Direction.WEST, Block.box(0.0, 0.0, 1.0, 12.0, 13.0, 16),
                    Direction.EAST, Block.box(4.0, 0.0, 1.0, 16.0, 13.0, 16)
            )
    );

    public BenchBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(PART, PartType.CENTER));
    }

    @Override
    protected MapCodec<? extends HorizontalFacingMountableBlock> codec() {
        return CODEC;
    }

    @Override
    public Vec3 getNorthFacingSitPos(Level level, BlockState state, BlockPos pos) {
        return new Vec3(0.5f, -0.5f, 0.6f);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction direction = ctx.getHorizontalDirection();
        BlockPos pos = ctx.getClickedPos();
        BlockPos leftPos = pos.relative(direction.getCounterClockWise());
        BlockPos rightPos = pos.relative(direction.getClockWise());
        Level level = ctx.getLevel();

        if (level.getBlockState(leftPos).canBeReplaced(ctx) && level.getBlockState(rightPos).canBeReplaced(ctx)) {
            return this.defaultBlockState().setValue(FACING, direction).setValue(PART, PartType.CENTER);
        }
        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            Direction direction = state.getValue(FACING);
            BlockPos leftPos = pos.relative(direction.getCounterClockWise());
            BlockPos rightPos = pos.relative(direction.getClockWise());
            level.setBlock(leftPos, state.setValue(PART, PartType.LEFT), Block.UPDATE_ALL);
            level.setBlock(rightPos, state.setValue(PART, PartType.RIGHT), Block.UPDATE_ALL);
        }
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            PartType part = state.getValue(PART);
            Direction facing = state.getValue(FACING);
            BlockPos centerPos = getCenterPos(pos, part, facing);
            if (part != PartType.CENTER) {
                BlockState centerState = level.getBlockState(centerPos);
                if (centerState.is(this)) {
                    level.destroyBlock(centerPos, !player.isCreative());
                }
            } else {
                BlockPos leftPos = pos.relative(facing.getCounterClockWise());
                BlockPos rightPos = pos.relative(facing.getClockWise());
                level.destroyBlock(leftPos, false);
                level.destroyBlock(rightPos, false);
            }
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        PartType part = state.getValue(PART);
        Direction facing = state.getValue(FACING);
        if (part == PartType.CENTER) {
            Direction leftDir = facing.getCounterClockWise();
            Direction rightDir = facing.getClockWise();
            if (direction == leftDir && !level.getBlockState(pos.relative(leftDir)).is(this)) {
                return Blocks.AIR.defaultBlockState();
            }
            if (direction == rightDir && !level.getBlockState(pos.relative(rightDir)).is(this)) {
                return Blocks.AIR.defaultBlockState();
            }
        } else {
            BlockPos centerPos = getCenterPos(pos, part, facing);
            if (!level.getBlockState(centerPos).is(this)) {
                return Blocks.AIR.defaultBlockState();
            }
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return BOUNDING_SHAPES.get(state.getValue(FACING));
    }

    private static BlockPos getCenterPos(BlockPos pos, PartType part, Direction facing) {
        return switch (part) {
            case LEFT -> pos.relative(facing.getClockWise());
            case RIGHT -> pos.relative(facing.getCounterClockWise());
            case CENTER -> pos;
        };
    }

    public enum PartType implements StringRepresentable {
        LEFT("left"),
        CENTER("center"),
        RIGHT("right");

        private final String name;

        PartType(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return this.name;
        }
    }
}
