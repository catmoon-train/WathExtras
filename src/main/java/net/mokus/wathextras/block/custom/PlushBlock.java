package net.mokus.wathextras.block.custom;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.item.Equipment;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class PlushBlock extends HorizontalFacingBlock implements Equipment {
    protected static final MapCodec<SoundEvent> SOUND_EVENT_CODEC = BuiltInRegistries.SOUND_EVENT
            .getCodec()
            .comapFlatMap(
                    soundType -> soundType instanceof SoundEvent SoundType
                            ? DataResult.success(SoundType)
                            : DataResult.error(() -> "Not a SoundEvent: " + soundType),
                    soundType -> soundType
            )
            .fieldOf("sound_options");
    public static final MapCodec<PlushBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(SOUND_EVENT_CODEC.forGetter(block -> block.sound), createSettingsCodec()).apply(instance, PlushBlock::new)
    );

    private static final VoxelShape SHAPE = Block.box(3.0,0.0,3.0,13.0,16.0,13.0);
    protected final SoundEvent sound;

    public PlushBlock(SoundEvent sound,Settings settings) {
        super(settings);
        this.sound = sound;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder){
        builder.add(FACING);
    }

    @Override
    public EquipmentSlot getSlotType() {
        return EquipmentSlot.HEAD;
    }

    @Override
    protected ActionResult onUse(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
            world.playSound(
                    player,
                    pos.getX(),
                    pos.getY(),
                    pos.getZ(),
                    this.sound, SoundSource.BLOCKS,
                    1.0f,1.0f
            );
        return InteractionResult.sidedSuccess(world.isClientSide);
    }
}
