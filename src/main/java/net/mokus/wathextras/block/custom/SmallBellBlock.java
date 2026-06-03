package net.mokus.wathextras.block.custom;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.block.CollisionContext;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.level.BlockGetter;

public class SmallBellBlock extends PlushBlock{

    private static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(
            VoxelShapes.cuboid(0.25,0,0.25,0.75,0.25,0.75),
            VoxelShapes.cuboid(0.44,0.2,0.44,0.56,0.31,0.56),
            BooleanBiFunction.OR
    );

    public SmallBellBlock(SoundEvent sound, Settings settings) {
        super(sound, settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

}
