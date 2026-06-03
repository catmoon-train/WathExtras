package net.mokus.wathextras.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WreathBlock extends HorizontalDirectionalBlock {
    private static final VoxelShape NORTH_SHAPE = Block.box(2.0, 2.0, 14.0, 14.0, 14.0, 16.0);
    private static final VoxelShape SOUTH_SHAPE = Block.box(2.0, 2.0, 0.0, 14.0, 14.0, 2.0);
    private static final VoxelShape WEST_SHAPE = Block.box(14.0, 2.0, 2.0, 16.0, 14.0, 14.0);
    private static final VoxelShape EAST_SHAPE = Block.box(0.0, 2.0, 2.0, 2.0, 14.0, 14.0);

    public WreathBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            case EAST -> EAST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }
}
