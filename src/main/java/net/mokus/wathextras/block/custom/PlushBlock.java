package net.mokus.wathextras.block.custom;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PlushBlock extends HorizontalDirectionalBlock implements Equipable {
    protected static final MapCodec<SoundEvent> SOUND_EVENT_CODEC = BuiltInRegistries.SOUND_EVENT
            .byNameCodec()
            .comapFlatMap(
                    soundType -> soundType instanceof SoundEvent SoundType
                            ? DataResult.success(SoundType)
                            : DataResult.error(() -> "Not a SoundEvent: " + soundType),
                    soundType -> soundType
            )
            .fieldOf("sound_options");
    public static final MapCodec<PlushBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(SOUND_EVENT_CODEC.forGetter(block -> block.sound), propertiesCodec()).apply(instance, PlushBlock::new)
    );

    private static final VoxelShape SHAPE = Block.box(3.0,0.0,3.0,13.0,16.0,13.0);
    protected final SoundEvent sound;

    public PlushBlock(SoundEvent sound,Properties properties) {
        super(properties);
        this.sound = sound;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return EquipmentSlot.HEAD;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
            level.playSound(
                    player,
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    this.sound, SoundSource.BLOCKS,
                    1.0f,1.0f
            );
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
