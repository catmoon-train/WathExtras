package net.mokus.wathextras.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.mokus.wathextras.block.ModBlocks;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {


    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.KHAKI_RIVETED_HULL_SMALL_WALL)
                .add(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_WALL)
                .add(ModBlocks.BLACK_RIVETED_HULL_SMALL_WALL)
                .add(ModBlocks.MAROON_RIVETED_HULL_SMALL_WALL)
                .add(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_WALL)
                .add(ModBlocks.NAVY_RIVETED_HULL_SMALL_WALL)
                .add(ModBlocks.WHITE_RIVETED_HULL_SMALL_WALL)
                .add(ModBlocks.BLEACHED_WALL)
                .add(ModBlocks.CORRUGATED_DARK_STEEL_WALL)
                .add(ModBlocks.CORRUGATED_STAINLESS_STEEL_WALL)
                .add(ModBlocks.PERFORATED_DARK_STEEL_WALL)
                .add(ModBlocks.PERFORATED_STAINLESS_STEEL_WALL)
                .add(ModBlocks.VERAWOOD_WALL);
        getOrCreateTagBuilder(BlockTags.SLABS)
                .add(ModBlocks.KHAKI_RIVETED_HULL_SMALL_SLAB)
                .add(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_SLAB)
                .add(ModBlocks.BLACK_RIVETED_HULL_SMALL_SLAB)
                .add(ModBlocks.MAROON_RIVETED_HULL_SMALL_SLAB)
                .add(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_SLAB)
                .add(ModBlocks.NAVY_RIVETED_HULL_SMALL_SLAB)
                .add(ModBlocks.WHITE_RIVETED_HULL_SMALL_SLAB)
                .add(ModBlocks.BLEACHED_SLAB)
                .add(ModBlocks.CORRUGATED_DARK_STEEL_SLAB)
                .add(ModBlocks.CORRUGATED_STAINLESS_STEEL_SLAB)
                .add(ModBlocks.PERFORATED_DARK_STEEL_SLAB)
                .add(ModBlocks.PERFORATED_STAINLESS_STEEL_SLAB)
                .add(ModBlocks.VERAWOOD_SLAB);
        getOrCreateTagBuilder(BlockTags.STAIRS)
                .add(ModBlocks.KHAKI_RIVETED_HULL_SMALL_STAIRS)
                .add(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_STAIRS)
                .add(ModBlocks.BLACK_RIVETED_HULL_SMALL_STAIRS)
                .add(ModBlocks.MAROON_RIVETED_HULL_SMALL_STAIRS)
                .add(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_STAIRS)
                .add(ModBlocks.NAVY_RIVETED_HULL_SMALL_STAIRS)
                .add(ModBlocks.WHITE_RIVETED_HULL_SMALL_STAIRS)
                .add(ModBlocks.BLEACHED_STAIRS)
                .add(ModBlocks.CORRUGATED_DARK_STEEL_STAIRS)
                .add(ModBlocks.CORRUGATED_STAINLESS_STEEL_STAIRS)
                .add(ModBlocks.PERFORATED_DARK_STEEL_STAIRS)
                .add(ModBlocks.PERFORATED_STAINLESS_STEEL_STAIRS)
                .add(ModBlocks.VERAWOOD_STAIRS);
        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(ModBlocks.BLEACHED_FENCE)
                .add(ModBlocks.VERAWOOD_FENCE);
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.BLEACHED_FENCE)
                .add(ModBlocks.VERAWOOD_FENCE);

    }
}
