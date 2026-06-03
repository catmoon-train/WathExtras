package net.mokus.wathextras.mixin;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.Item;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Holder;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.storage.WritableLevelData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import net.mokus.wathextras.block.ModBlocks;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

@Mixin(ClientLevel.class)
public abstract class ClientWorldMixin extends Level {
    protected ClientWorldMixin(WritableLevelData properties, ResourceKey<Level> registryRef, RegistryAccess registryManager, Holder<DimensionType> dimensionEntry, Supplier<ProfilerFiller> ProfilerFiller, boolean isClient, boolean debugWorld, long biomeAccess, int maxChainedNeighborUpdates) {
        super(properties, registryRef, registryManager, dimensionEntry, ProfilerFiller, isClient, debugWorld, biomeAccess, maxChainedNeighborUpdates);
    }

    @Final
    @Shadow
    @Mutable
    private static Set<Item> BLOCK_MARKER_ITEMS;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void tmmore$addBarrierLikeBlocks(ClientPacketListener networkHandler, ClientLevel.ClientLevelData properties, ResourceKey<Level> registryRef, Holder<DimensionType> dimensionTypeEntry, int loadDistance, int simulationDistance, Supplier<ProfilerFiller> ProfilerFiller, LevelRenderer LevelRenderer, boolean debugWorld, long seed, CallbackInfo ci) {
        BLOCK_MARKER_ITEMS = new HashSet<>(BLOCK_MARKER_ITEMS);
        BLOCK_MARKER_ITEMS.add(ModBlocks.KILL_BLOCK.asItem());
        BLOCK_MARKER_ITEMS.add(ModBlocks.KILL_BLOCK_PANEL.asItem());
    }
}
