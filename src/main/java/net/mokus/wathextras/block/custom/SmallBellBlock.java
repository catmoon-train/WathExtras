package net.mokus.wathextras.block.custom;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;

public class SmallBellBlock extends PlushBlock{

    private static final VoxelShape SHAPE = Shapes.combineAndSimplify(
            Shapes.cuboid(0.25,0,0.25,0.75,0.25,0.75),
            Shapes.cuboid(0.44,0.2,0.44,0.56,0.31,0.56),
            BooleanOp.OR
    );

    public SmallBellBlock(SoundEvent sound, Properties properties) {
        super(sound, properties);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

}
