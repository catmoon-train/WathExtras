package net.mokus.wathextras;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.mokus.wathextras.block.ModBlocks;

public class WathExtrasClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
                ModBlocks.NORA_PLUSH,
                ModBlocks.DAVIDANDROCKET_PLUSH,
                ModBlocks.DUCKAMOLY_PLUSH,
                ModBlocks.PICKLE_PLUSH,
                ModBlocks.VERID__PLUSH,
                ModBlocks.WILLO_PLUSH,
                ModBlocks.MOKUS_PLUSH,
                ModBlocks.DOOGEY_PLUSH,
                ModBlocks.SQUID_PLUSH,
                ModBlocks.PENCIL_PLUSH,
                ModBlocks.INDIGO_PLUSH,
                // Transparent texture blocks;
                ModBlocks.WALL_CANDELABRE,
                ModBlocks.PALE_BENCH,
                ModBlocks.QUEEN_BENCH,
                ModBlocks.THORN_BENCH,
                ModBlocks.STEEL_BENCH,
                ModBlocks.BUTTERFLY_DOOR_BLOCK,
                ModBlocks.KILL_BLOCK_PANEL,
                ModBlocks.KILL_BLOCK,
                ModBlocks.SERVICE_BELL,
                ModBlocks.WREATH,
                ModBlocks.SNOWY_WREATH,
                ModBlocks.DARK_STEEL_ORNAMENT,
                ModBlocks.STAINLESS_STEEL_ORNAMENT,
                ModBlocks.BRONZE_ORNAMENT,
                ModBlocks.PLATINUM_ORNAMENT,
                ModBlocks.COPPER_ORNAMENT,
                ModBlocks.EXPOSED_COPPER_ORNAMENT,
                ModBlocks.OXIDIZED_COPPER_ORNAMENT,
                ModBlocks.WEATHERED_COPPER_ORNAMENT);
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(),
                ModBlocks.ROOF_LAMP,
                ModBlocks.CHRISTMAS_LIGHTS);

    }
}
