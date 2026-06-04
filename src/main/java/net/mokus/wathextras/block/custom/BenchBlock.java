package net.mokus.wathextras.block.custom;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.MapCodec;
import io.wifi.starrailexpress.content.block.HorizontalFacingMountableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class BenchBlock extends HorizontalFacingMountableBlock {
    public static final MapCodec<BenchBlock> CODEC = createSimpleCodec(BenchBlock::new);

    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES = Maps.newEnumMap(
            ImmutableMap.of(
                    Direction.NORTH, Block.box(1.0, 0.0, 0.0, 16.0, 13.0, 12.0),
                    Direction.SOUTH, Block.box(1.0, 0.0, 4.0, 16.0, 13.0, 16.0),
                    Direction.WEST, Block.box(0.0, 0.0, 1.0, 12.0, 13.0, 16),
                    Direction.EAST, Block.box(4.0, 0.0, 1.0, 16.0, 13.0, 16)));

    public BenchBlock(Properties properties) {
        super(properties);
        this.registerDefaultStateMirrored(this.getDefaultBlockState());
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
    public @Nullable BlockState getStateForPlacementMirrored(BlockPlaceContext ctx) {
        BlockState state = this.getDefaultBlockState().setValue(FACING, ctx.getHorizontalDirection());

        Direction direction = state.getValue(FACING);
        BlockPos pos = ctx.getClickedPos();
        Level level = ctx.getLevel();
        var partType = PartType.CENTER;
        {
            return this.getDefaultBlockState().setValue(FACING, direction).setValue(PART,
                    partType);
        }
    }

    @Override
    public void setPlacedByMirrored(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer,
            ItemStack stack) {
        super.setPlacedByMirrored(level, pos, state, placer, stack);
        if (!level.isClientSide) {
            Direction direction = state.getValue(FACING);
            BlockPos leftPos = pos.relative(direction.getCounterClockWise());
            BlockPos rightPos = pos.relative(direction.getClockWise());

            boolean hasLeft = false, hasRight = false;
            var ls = level.getBlockState(leftPos);
            var rs = level.getBlockState(rightPos);
            if (ls.getBlock() instanceof BenchBlock) {
                if (ls.getValue(FACING).equals(direction)) {
                    hasLeft = true;
                }
            }
            if (rs.getBlock() instanceof BenchBlock) {
                if (rs.getValue(FACING).equals(direction)) {
                    hasRight = true;
                }
            }
            if (hasLeft && hasRight) {
                state = state.setValue(PART, PartType.CENTER);
            } else if (hasLeft) {
                state = state.setValue(PART, PartType.RIGHT);
            } else if (hasRight) {
                state = state.setValue(PART, PartType.LEFT);
            } else {
                state = state.setValue(PART, PartType.CENTER);
            }
            level.setBlock(pos, state, UPDATE_ALL);
        }

        // 关键：再次更新中心方块，强制它重新检查邻居（防止自我销毁）
        // level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return BOUNDING_SHAPES.get(state.getValue(FACING));
    }
}
