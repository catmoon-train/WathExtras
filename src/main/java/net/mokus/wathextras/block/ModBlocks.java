package net.mokus.wathextras.block;

import dev.doctor4t.ratatouille.util.registrar.BlockRegistrar;
import io.wifi.starrailexpress.content.block.OrnamentBlock;
import io.wifi.starrailexpress.content.block.PanelBlock;
import io.wifi.starrailexpress.index.wathe_bridge.WatheBridgerBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.item.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.*;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.mokus.wathextras.WathExtras;
import net.mokus.wathextras.block.custom.*;
import net.mokus.wathextras.item.ModItems;
import net.mokus.wathextras.util.ModSounds;

public class ModBlocks {

    private static final BlockRegistrar registrar = new BlockRegistrar(WathExtras.MOD_ID);


    public static final Block CANDELABRE = registerBlock("candelabre",
            new CandelabreBlock(ParticleTypes.SMALL_FLAME,
                    BlockBehaviour.Properties.of().instabreak().lightLevel(CandelabreBlock.STATE_TO_LUMINANCE).
                            sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));
    public static final Block WALL_CANDELABRE = registerBlock("wall_candelabre",
            new WallCandelabreBlock(ParticleTypes.SMALL_FLAME,
                    BlockBehaviour.Properties.of().instabreak().lightLevel(WallCandelabreBlock.STATE_TO_LUMINANCE).
                            sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY).dropsLike(ModBlocks.CANDELABRE)));

    public static final Block BLEACHED_PLANKS = registerBlock("bleached_planks",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block BLEACHED_STAIRS = registerBlock("bleached_stairs",
            new StairBlock(ModBlocks.BLEACHED_PLANKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block BLEACHED_SLAB = registerBlock("bleached_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block BLEACHED_WALL = registerBlock("bleached_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block BLEACHED_PANEL = registerBlock("bleached_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block BLEACHED_FENCE = registerBlock("bleached_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));

    public static final Block VERAWOOD_PLANKS = registerBlock("verawood_planks",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block VERAWOOD_STAIRS = registerBlock("verawood_stairs",
            new StairBlock(ModBlocks.VERAWOOD_PLANKS.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block VERAWOOD_SLAB = registerBlock("verawood_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block VERAWOOD_WALL = registerBlock("verawood_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block VERAWOOD_PANEL = registerBlock("verawood_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block VERAWOOD_FENCE = registerBlock("verawood_fence",
            new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));


    public static final Block KHAKI_RIVETED_HULL_SMALL = registerBlock("khaki_riveted_hull_small",
            new Block(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block KHAKI_RIVETED_HULL_SMALL_SLAB = registerBlock("khaki_riveted_hull_small_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block KHAKI_RIVETED_HULL_SMALL_STAIRS = registerBlock("khaki_riveted_hull_small_stairs",
            new StairBlock(ModBlocks.KHAKI_RIVETED_HULL_SMALL.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block KHAKI_RIVETED_HULL_SMALL_WALL = registerBlock("khaki_riveted_hull_small_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block KHAKI_RIVETED_HULL_SMALL_PANEL = registerBlock("khaki_riveted_hull_small_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block ANTHRACITE_RIVETED_HULL_SMALL = registerBlock("anthracite_riveted_hull_small",
            new Block(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block ANTHRACITE_RIVETED_HULL_SMALL_SLAB = registerBlock("anthracite_riveted_hull_small_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block ANTHRACITE_RIVETED_HULL_SMALL_STAIRS = registerBlock("anthracite_riveted_hull_small_stairs",
            new StairBlock(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block ANTHRACITE_RIVETED_HULL_SMALL_WALL = registerBlock("anthracite_riveted_hull_small_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block ANTHRACITE_RIVETED_HULL_SMALL_PANEL = registerBlock("anthracite_riveted_hull_small_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block BLACK_RIVETED_HULL_SMALL = registerBlock("black_riveted_hull_small",
            new Block(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block BLACK_RIVETED_HULL_SMALL_SLAB = registerBlock("black_riveted_hull_small_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block BLACK_RIVETED_HULL_SMALL_STAIRS = registerBlock("black_riveted_hull_small_stairs",
            new StairBlock(ModBlocks.BLACK_RIVETED_HULL_SMALL.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block BLACK_RIVETED_HULL_SMALL_WALL = registerBlock("black_riveted_hull_small_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block BLACK_RIVETED_HULL_SMALL_PANEL = registerBlock("black_riveted_hull_small_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block MAROON_RIVETED_HULL_SMALL = registerBlock("maroon_riveted_hull_small",
            new Block(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block MAROON_RIVETED_HULL_SMALL_SLAB = registerBlock("maroon_riveted_hull_small_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block MAROON_RIVETED_HULL_SMALL_STAIRS = registerBlock("maroon_riveted_hull_small_stairs",
            new StairBlock(ModBlocks.MAROON_RIVETED_HULL_SMALL.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block MAROON_RIVETED_HULL_SMALL_WALL = registerBlock("maroon_riveted_hull_small_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block MAROON_RIVETED_HULL_SMALL_PANEL = registerBlock("maroon_riveted_hull_small_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block MUNTZ_RIVETED_HULL_SMALL = registerBlock("muntz_riveted_hull_small",
            new Block(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block MUNTZ_RIVETED_HULL_SMALL_SLAB = registerBlock("muntz_riveted_hull_small_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block MUNTZ_RIVETED_HULL_SMALL_STAIRS = registerBlock("muntz_riveted_hull_small_stairs",
            new StairBlock(ModBlocks.MUNTZ_RIVETED_HULL_SMALL.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block MUNTZ_RIVETED_HULL_SMALL_WALL = registerBlock("muntz_riveted_hull_small_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block MUNTZ_RIVETED_HULL_SMALL_PANEL = registerBlock("muntz_riveted_hull_small_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block NAVY_RIVETED_HULL_SMALL = registerBlock("navy_riveted_hull_small",
            new Block(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block NAVY_RIVETED_HULL_SMALL_SLAB = registerBlock("navy_riveted_hull_small_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block NAVY_RIVETED_HULL_SMALL_STAIRS = registerBlock("navy_riveted_hull_small_stairs",
            new StairBlock(ModBlocks.NAVY_RIVETED_HULL_SMALL.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block NAVY_RIVETED_HULL_SMALL_WALL = registerBlock("navy_riveted_hull_small_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block NAVY_RIVETED_HULL_SMALL_PANEL = registerBlock("navy_riveted_hull_small_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block WHITE_RIVETED_HULL_SMALL = registerBlock("white_riveted_hull_small",
            new Block(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block WHITE_RIVETED_HULL_SMALL_SLAB = registerBlock("white_riveted_hull_small_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block WHITE_RIVETED_HULL_SMALL_STAIRS = registerBlock("white_riveted_hull_small_stairs",
            new StairBlock(ModBlocks.WHITE_RIVETED_HULL_SMALL.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block WHITE_RIVETED_HULL_SMALL_WALL = registerBlock("white_riveted_hull_small_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block WHITE_RIVETED_HULL_SMALL_PANEL = registerBlock("white_riveted_hull_small_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));

    // Normal Moquette Blocks
    public static final Block BLACK_MOQUETTE = registerBlock("black_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block CYAN_MOQUETTE = registerBlock("cyan_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block GRAY_MOQUETTE = registerBlock("gray_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block GREEN_MOQUETTE = registerBlock("green_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block LIGHT_BLUE_MOQUETTE = registerBlock("light_blue_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block LIGHT_GRAY_MOQUETTE = registerBlock("light_gray_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block LIME_MOQUETTE = registerBlock("lime_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block MAGENTA_MOQUETTE = registerBlock("magenta_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block ORANGE_MOQUETTE = registerBlock("orange_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block PINK_MOQUETTE = registerBlock("pink_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block PURPLE_MOQUETTE = registerBlock("purple_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block WHITE_MOQUETTE = registerBlock("white_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block YELLOW_MOQUETTE = registerBlock("yellow_moquette",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_WOOL).strength(-1.0f,3600000.0f)));

    // Start Carpets
    public static final Block BLACK_MOQUETTE_CARPET = registerBlock("black_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block CYAN_MOQUETTE_CARPET = registerBlock("cyan_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block GRAY_MOQUETTE_CARPET = registerBlock("gray_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block GREEN_MOQUETTE_CARPET = registerBlock("green_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block LIGHT_BLUE_MOQUETTE_CARPET = registerBlock("light_blue_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block LIGHT_GRAY_MOQUETTE_CARPET = registerBlock("light_gray_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block LIME_MOQUETTE_CARPET = registerBlock("lime_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block MAGENTA_MOQUETTE_CARPET = registerBlock("magenta_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block ORANGE_MOQUETTE_CARPET = registerBlock("orange_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block PINK_MOQUETTE_CARPET = registerBlock("pink_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block PURPLE_MOQUETTE_CARPET = registerBlock("purple_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block WHITE_MOQUETTE_CARPET = registerBlock("white_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block YELLOW_MOQUETTE_CARPET = registerBlock("yellow_moquette_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_CARPET).strength(-1.0f,3600000.0f)));

    // Striped Carpets
    public static final Block BLACK_STRIPED_CARPET_BLOCK = registerBlock("black_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block CYAN_STRIPED_CARPET_BLOCK = registerBlock("cyan_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block GRAY_STRIPED_CARPET_BLOCK = registerBlock("gray_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block GREEN_STRIPED_CARPET_BLOCK = registerBlock("green_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    //public static final Block LIGHT_BLUE_STRIPED_CARPET_BLOCK = registerBlock("light_blue_striped_carpet_block",
    //        new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block LIGHT_GRAY_STRIPED_CARPET_BLOCK = registerBlock("light_gray_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block LIME_STRIPED_CARPET_BLOCK = registerBlock("lime_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block ORANGE_STRIPED_CARPET_BLOCK = registerBlock("orange_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block PURPLE_STRIPED_CARPET_BLOCK = registerBlock("purple_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block WHITE_STRIPED_CARPET_BLOCK = registerBlock("white_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block YELLOW_STRIPED_CARPET_BLOCK = registerBlock("yellow_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block BLUE_STRIPED_CARPET_BLOCK = registerBlock("blue_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block RED_STRIPED_CARPET_BLOCK = registerBlock("red_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));
    public static final Block BROWN_STRIPED_CARPET_BLOCK = registerBlock("brown_striped_carpet_block",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).strength(-1.0f,3600000.0f)));

    // Striped Carpets
    public static final Block BLACK_STRIPED_CARPET = registerBlock("black_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block CYAN_STRIPED_CARPET = registerBlock("cyan_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block GRAY_STRIPED_CARPET = registerBlock("gray_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block GREEN_STRIPED_CARPET = registerBlock("green_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    // public static final Block LIGHT_BLUE_STRIPED_CARPET = registerBlock("light_blue_striped_carpet",
    //        new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block LIGHT_GRAY_STRIPED_CARPET = registerBlock("light_gray_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block LIME_STRIPED_CARPET = registerBlock("lime_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block ORANGE_STRIPED_CARPET = registerBlock("orange_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block PURPLE_STRIPED_CARPET = registerBlock("purple_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block WHITE_STRIPED_CARPET = registerBlock("white_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block YELLOW_STRIPED_CARPET = registerBlock("yellow_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block BLUE_STRIPED_CARPET = registerBlock("blue_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block RED_STRIPED_CARPET = registerBlock("red_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));
    public static final Block BROWN_STRIPED_CARPET = registerBlock("brown_striped_carpet",
            new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_CARPET).strength(-1.0f,3600000.0f)));

    // Dark marbles
    public static final Block DARK_MARBLE_TILE = registerBlock("dark_marble_tile",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).strength(-1.0f,3600000.0f)));
    public static final Block CHECKERED_MARBLE_TILES = registerBlock("checkered_marble_tiles",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).strength(-1.0f,3600000.0f)));
    public static final Block MIXED_MARBLE_TILES = registerBlock("mixed_marble_tiles",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).strength(-1.0f,3600000.0f)));
    public static final Block DEEPWOKEN_TILE = registerBlock("deepwoken_tile",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).strength(-1.0f,3600000.0f)));

    // Plushies
    public static final Block NORA_PLUSH = registerBlock("nora_plush",
            new PlushBlock(ModSounds.NORA_PLUSH,BlockBehaviour.Properties.ofFullCopy(Blocks.RED_WOOL).noOcclusion()));
    public static final Block DAVIDANDROCKET_PLUSH = registerBlock("davidandrocket_plush",
            new PlushBlock(ModSounds.DAVIDANDROCKET_PLUSH,BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_WOOL).noOcclusion()));
    public static final Block DUCKAMOLY_PLUSH = registerBlock("duckamoly_plush",
            new PlushBlock(ModSounds.DUCKAMOLY_PLUSH,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion()));
    public static final Block PICKLE_PLUSH = registerBlock("pickle_plush",
            new PlushBlock(ModSounds.PICKLE_PLUSH,BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_WOOL).noOcclusion()));
    public static final Block VERID__PLUSH = registerBlock("verid__plush",
            new PlushBlock(ModSounds.VERID__PLUSH,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion()));
    public static final Block WILLO_PLUSH = registerBlock("willo_plush",
            new PlushBlock(ModSounds.WILLO_PLUSH,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion()));
    public static final Block MOKUS_PLUSH = registerBlock("mokus_plush",
            new PlushBlock(ModSounds.DEFAULT_PLUSH,BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL).noOcclusion()));
    public static final Block DOOGEY_PLUSH = registerBlock("doogey_plush",
            new PlushBlock(ModSounds.DOOGEY_PLUSH,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion()));
    public static final Block SQUID_PLUSH = registerBlock("squid_plush",
            new PlushBlock(ModSounds.SQUID_PLUSH,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion()));
    public static final Block PENCIL_PLUSH = registerBlock("pencil_plush",
            new PlushBlock(ModSounds.PENCIL_PLUSH,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion()));
    public static final Block INDIGO_PLUSH = registerBlock("indigo_plush",
            new PlushBlock(ModSounds.INDIGO_PLUSH,BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL).noOcclusion()));

    // Candy Cane
    public static final Block CANDY_CANE_BLOCK = registerBlock("candy_cane_block",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK)));
    public static final Block ZIGZAG_CANDY_BLOCK = registerBlock("zigzagcandy",
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BONE_BLOCK)));

    public static final Block ARCADE_FLOOR = registerBlock("arcade_floor",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_WOOL).strength(-1.0f,3600000.0f)));

    // Benches
    public static final Block PALE_BENCH = registerBlock("pale_bench",
            new BenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block QUEEN_BENCH = registerBlock("queen_bench",
            new BenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block THORN_BENCH = registerBlock("thorn_bench",
            new BenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block STEEL_BENCH = registerBlock("steel_bench",
            new BenchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    // Double Hull
    public static final Block ANTHRACITE_RIVETED_HULL = registerBlock("anthracite_riveted_hull",
            new DoubleHullBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block BLACK_RIVETED_HULL = registerBlock("black_riveted_hull",
            new DoubleHullBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block KHAKI_RIVETED_HULL = registerBlock("khaki_riveted_hull",
            new DoubleHullBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block MAROON_RIVETED_HULL = registerBlock("maroon_riveted_hull",
            new DoubleHullBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block MUNTZ_RIVETED_HULL = registerBlock("muntz_riveted_hull",
            new DoubleHullBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block NAVY_RIVETED_HULL = registerBlock("navy_riveted_hull",
            new DoubleHullBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block WHITE_RIVETED_HULL = registerBlock("white_riveted_hull",
            new DoubleHullBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));

    public static final Block STACK_LIGHTS = registerBlock("stack_lights",
            new StackLightBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BULB).lightLevel(StackLightBlock.STATE_TO_LUMINANCE)));

    public static final Block ROOF_LAMP = registerBlock("roof_lamp",
            new RoofLampBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BULB).lightLevel(StackLightBlock.STATE_TO_LUMINANCE)));


    public static final Block SMOOTH_PINK_CITRINE = registerBlock("smooth_pink_citrine",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final Block POLISHED_PINK_CITRINE = registerBlock("polished_pink_citrine",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));
    public static final Block PINK_CITRINE_BRICKS = registerBlock("pink_citrine_bricks",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)));

    public static final Block POLISHED_FORTERRA = registerBlock("polished_forterra",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICK_WALL)));
    public static final Block FORTERRA_BRICKS = registerBlock("forterra_bricks",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MUD_BRICKS)));

    public static final Block ASPHALT = registerBlock("asphalt",
            new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAVEL)));


    public static final Block BLEACHED_WALL_PANEL = registerBlock("bleached_wall_panel",
            new WallPanelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block DARK_OAK_WALL_PANEL = registerBlock("dark_oak_wall_panel",
            new WallPanelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block EBONY_WALL_PANEL = registerBlock("ebony_wall_panel",
            new WallPanelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block MAHOGANY_WALL_PANEL = registerBlock("mahogany_wall_panel",
            new WallPanelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block VERAWOOD_WALL_PANEL = registerBlock("verawood_wall_panel",
            new WallPanelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));
    public static final Block BUBINGA_WALL_PANEL = registerBlock("bubinga_wall_panel",
            new WallPanelBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    public static final Block CORRUGATED_DARK_STEEL = registerBlock("corrugated_dark_steel",
            new Block(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block CORRUGATED_DARK_STEEL_SLAB = registerBlock("corrugated_dark_steel_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block CORRUGATED_DARK_STEEL_STAIRS = registerBlock("corrugated_dark_steel_stairs",
            new StairBlock(ModBlocks.CORRUGATED_DARK_STEEL.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block CORRUGATED_DARK_STEEL_WALL = registerBlock("corrugated_dark_steel_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block CORRUGATED_DARK_STEEL_PANEL = registerBlock("corrugated_dark_steel_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));

    public static final Block CORRUGATED_STAINLESS_STEEL = registerBlock("corrugated_stainless_steel",
            new Block(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block CORRUGATED_STAINLESS_STEEL_SLAB = registerBlock("corrugated_stainless_steel_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block CORRUGATED_STAINLESS_STEEL_STAIRS = registerBlock("corrugated_stainless_steel_stairs",
            new StairBlock(ModBlocks.CORRUGATED_STAINLESS_STEEL.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block CORRUGATED_STAINLESS_STEEL_WALL = registerBlock("corrugated_stainless_steel_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block CORRUGATED_STAINLESS_STEEL_PANEL = registerBlock("corrugated_stainless_steel_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));

    public static final Block PERFORATED_STAINLESS_STEEL = registerBlock("perforated_stainless_steel",
            new Block(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block PERFORATED_STAINLESS_STEEL_SLAB = registerBlock("perforated_stainless_steel_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block PERFORATED_STAINLESS_STEEL_STAIRS = registerBlock("perforated_stainless_steel_stairs",
            new StairBlock(ModBlocks.PERFORATED_STAINLESS_STEEL.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block PERFORATED_STAINLESS_STEEL_WALL = registerBlock("perforated_stainless_steel_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block PERFORATED_STAINLESS_STEEL_PANEL = registerBlock("perforated_stainless_steel_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));

    public static final Block PERFORATED_DARK_STEEL = registerBlock("perforated_dark_steel",
            new Block(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block PERFORATED_DARK_STEEL_SLAB = registerBlock("perforated_dark_steel_slab",
            new SlabBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final Block PERFORATED_DARK_STEEL_STAIRS = registerBlock("perforated_dark_steel_stairs",
            new StairBlock(ModBlocks.PERFORATED_DARK_STEEL.defaultBlockState(),BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block PERFORATED_DARK_STEEL_WALL = registerBlock("perforated_dark_steel_wall",
            new WallBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));
    public static final  Block PERFORATED_DARK_STEEL_PANEL = registerBlock("perforated_dark_steel_panel",
            new PanelBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL)));

    public static final Block BUTTERFLY_DOOR_BLOCK = registerBlock("butterfly_door",
            new ButterflyDoorBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL).noOcclusion()));

    public static final Block KILL_BLOCK_PANEL = registerBlock("kill_block_panel",
            new KillBlockPanel(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.BARRIER_PANEL)));
    public static final Block KILL_BLOCK = registerBlock("kill_block",
            new KillBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noOcclusion()));

    public static final Block SERVICE_BELL = registerBlock("service_bell",
            new SmallBellBlock(ModSounds.SERVICE_BELL,BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.DARK_STEEL).noOcclusion()));

    public static final Block DARK_STEEL_ORNAMENT = registerBlock("dark_steel_ornament",
            new OrnamentBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.GOLD_ORNAMENT)));
    public static final Block STAINLESS_STEEL_ORNAMENT = registerBlock("stainless_steel_ornament",
            new OrnamentBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.GOLD_ORNAMENT)));
    public static final Block BRONZE_ORNAMENT = registerBlock("bronze_ornament",
            new OrnamentBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.GOLD_ORNAMENT)));
    public static final Block PLATINUM_ORNAMENT = registerBlock("platinum_ornament",
            new OrnamentBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.GOLD_ORNAMENT)));

    public static final Block COPPER_ORNAMENT = registerBlock("copper_ornament",
            new OrnamentBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.GOLD_ORNAMENT)));
    public static final Block EXPOSED_COPPER_ORNAMENT = registerBlock("exposed_copper_ornament",
            new OrnamentBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.GOLD_ORNAMENT)));
    public static final Block OXIDIZED_COPPER_ORNAMENT = registerBlock("oxidized_copper_ornament",
            new OrnamentBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.GOLD_ORNAMENT)));
    public static final Block WEATHERED_COPPER_ORNAMENT = registerBlock("weathered_copper_ornament",
            new OrnamentBlock(BlockBehaviour.Properties.ofFullCopy(WatheBridgerBlocks.GOLD_ORNAMENT)));

    public static final Block CHRISTMAS_LIGHTS = registerBlock("christmas_lights",
            new ChristmasLights(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).sound(SoundType.COPPER_BULB).noOcclusion()));

    public static final Block WREATH = registerBlock("wreath",
            new WreathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).sound(SoundType.AZALEA_LEAVES).noOcclusion()));
    public static final Block SNOWY_WREATH = registerBlock("snowy_wreath",
            new WreathBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.VINE).sound(SoundType.AZALEA_LEAVES).noOcclusion()));


    private static Block registerBlock(String name, Block block){
        return registrar.createWithItem(name, block);
    }

    // Creative Item Group Striped Carpets
    public static final ResourceKey<CreativeModeTab> STRIPED_CARPET_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID, "striped_carpet_group"));
    public static final CreativeModeTab STRIPED_CARPET = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.WHITE_STRIPED_CARPET_BLOCK))
            .title(Component.translatable("buildGroup.Striped_Carpet"))
            .build();

    // Creative Item Group Moquettes
    public static final ResourceKey<CreativeModeTab> MOQUETTES_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID, "moquette_group"));
    public static final CreativeModeTab MOQUETTES = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.YELLOW_MOQUETTE))
            .title(Component.translatable("buildGroup.Moquettes"))
            .build();

    public static final ResourceKey<CreativeModeTab> TMMORE_BUILDING_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID, "tmmore_building_group"));
    public static final CreativeModeTab TMMORE_BUILDING = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModBlocks.KHAKI_RIVETED_HULL_SMALL))
            .title(Component.translatable("buildGroup.TMMore_Building"))
            .build();


    public static void init(){

        WathExtras.LOGGER.info("Weaving carpets and making blocks for " + WathExtras.MOD_ID);

        registrar.registerEntries();

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TMMORE_BUILDING_KEY, TMMORE_BUILDING);
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.TMMORE_BUILDING_KEY).register(TMMORE_BLOCKS -> {
            TMMORE_BLOCKS.accept(KHAKI_RIVETED_HULL);
            TMMORE_BLOCKS.accept(KHAKI_RIVETED_HULL_SMALL);
            TMMORE_BLOCKS.accept(KHAKI_RIVETED_HULL_SMALL_PANEL);
            TMMORE_BLOCKS.accept(KHAKI_RIVETED_HULL_SMALL_STAIRS);
            TMMORE_BLOCKS.accept(KHAKI_RIVETED_HULL_SMALL_SLAB);
            TMMORE_BLOCKS.accept(KHAKI_RIVETED_HULL_SMALL_WALL);
            TMMORE_BLOCKS.accept(ANTHRACITE_RIVETED_HULL);
            TMMORE_BLOCKS.accept(ANTHRACITE_RIVETED_HULL_SMALL);
            TMMORE_BLOCKS.accept(ANTHRACITE_RIVETED_HULL_SMALL_PANEL);
            TMMORE_BLOCKS.accept(ANTHRACITE_RIVETED_HULL_SMALL_STAIRS);
            TMMORE_BLOCKS.accept(ANTHRACITE_RIVETED_HULL_SMALL_SLAB);
            TMMORE_BLOCKS.accept(ANTHRACITE_RIVETED_HULL_SMALL_WALL);
            TMMORE_BLOCKS.accept(BLACK_RIVETED_HULL);
            TMMORE_BLOCKS.accept(BLACK_RIVETED_HULL_SMALL);
            TMMORE_BLOCKS.accept(BLACK_RIVETED_HULL_SMALL_PANEL);
            TMMORE_BLOCKS.accept(BLACK_RIVETED_HULL_SMALL_STAIRS);
            TMMORE_BLOCKS.accept(BLACK_RIVETED_HULL_SMALL_SLAB);
            TMMORE_BLOCKS.accept(BLACK_RIVETED_HULL_SMALL_WALL);
            TMMORE_BLOCKS.accept(MAROON_RIVETED_HULL);
            TMMORE_BLOCKS.accept(MAROON_RIVETED_HULL_SMALL);
            TMMORE_BLOCKS.accept(MAROON_RIVETED_HULL_SMALL_PANEL);
            TMMORE_BLOCKS.accept(MAROON_RIVETED_HULL_SMALL_STAIRS);
            TMMORE_BLOCKS.accept(MAROON_RIVETED_HULL_SMALL_SLAB);
            TMMORE_BLOCKS.accept(MAROON_RIVETED_HULL_SMALL_WALL);
            TMMORE_BLOCKS.accept(MUNTZ_RIVETED_HULL);
            TMMORE_BLOCKS.accept(MUNTZ_RIVETED_HULL_SMALL);
            TMMORE_BLOCKS.accept(MUNTZ_RIVETED_HULL_SMALL_PANEL);
            TMMORE_BLOCKS.accept(MUNTZ_RIVETED_HULL_SMALL_STAIRS);
            TMMORE_BLOCKS.accept(MUNTZ_RIVETED_HULL_SMALL_SLAB);
            TMMORE_BLOCKS.accept(MUNTZ_RIVETED_HULL_SMALL_WALL);
            TMMORE_BLOCKS.accept(NAVY_RIVETED_HULL);
            TMMORE_BLOCKS.accept(NAVY_RIVETED_HULL_SMALL);
            TMMORE_BLOCKS.accept(NAVY_RIVETED_HULL_SMALL_PANEL);
            TMMORE_BLOCKS.accept(NAVY_RIVETED_HULL_SMALL_STAIRS);
            TMMORE_BLOCKS.accept(NAVY_RIVETED_HULL_SMALL_SLAB);
            TMMORE_BLOCKS.accept(NAVY_RIVETED_HULL_SMALL_WALL);
            TMMORE_BLOCKS.accept(WHITE_RIVETED_HULL);
            TMMORE_BLOCKS.accept(WHITE_RIVETED_HULL_SMALL);
            TMMORE_BLOCKS.accept(WHITE_RIVETED_HULL_SMALL_PANEL);
            TMMORE_BLOCKS.accept(WHITE_RIVETED_HULL_SMALL_STAIRS);
            TMMORE_BLOCKS.accept(WHITE_RIVETED_HULL_SMALL_SLAB);
            TMMORE_BLOCKS.accept(WHITE_RIVETED_HULL_SMALL_WALL);

            TMMORE_BLOCKS.accept(CORRUGATED_DARK_STEEL);
            TMMORE_BLOCKS.accept(CORRUGATED_DARK_STEEL_PANEL);
            TMMORE_BLOCKS.accept(CORRUGATED_DARK_STEEL_STAIRS);
            TMMORE_BLOCKS.accept(CORRUGATED_DARK_STEEL_SLAB);
            TMMORE_BLOCKS.accept(CORRUGATED_DARK_STEEL_WALL);
            TMMORE_BLOCKS.accept(CORRUGATED_STAINLESS_STEEL);
            TMMORE_BLOCKS.accept(CORRUGATED_STAINLESS_STEEL_PANEL);
            TMMORE_BLOCKS.accept(CORRUGATED_STAINLESS_STEEL_STAIRS);
            TMMORE_BLOCKS.accept(CORRUGATED_STAINLESS_STEEL_SLAB);
            TMMORE_BLOCKS.accept(CORRUGATED_STAINLESS_STEEL_WALL);
            TMMORE_BLOCKS.accept(PERFORATED_STAINLESS_STEEL);
            TMMORE_BLOCKS.accept(PERFORATED_STAINLESS_STEEL_PANEL);
            TMMORE_BLOCKS.accept(PERFORATED_STAINLESS_STEEL_STAIRS);
            TMMORE_BLOCKS.accept(PERFORATED_STAINLESS_STEEL_SLAB);
            TMMORE_BLOCKS.accept(PERFORATED_STAINLESS_STEEL_WALL);
            TMMORE_BLOCKS.accept(PERFORATED_DARK_STEEL);
            TMMORE_BLOCKS.accept(PERFORATED_DARK_STEEL_PANEL);
            TMMORE_BLOCKS.accept(PERFORATED_DARK_STEEL_STAIRS);
            TMMORE_BLOCKS.accept(PERFORATED_DARK_STEEL_SLAB);
            TMMORE_BLOCKS.accept(PERFORATED_DARK_STEEL_WALL);

            TMMORE_BLOCKS.accept(DARK_STEEL_ORNAMENT);
            TMMORE_BLOCKS.accept(STAINLESS_STEEL_ORNAMENT);
            TMMORE_BLOCKS.accept(PLATINUM_ORNAMENT);
            TMMORE_BLOCKS.accept(BRONZE_ORNAMENT);
            TMMORE_BLOCKS.accept(COPPER_ORNAMENT);
            TMMORE_BLOCKS.accept(EXPOSED_COPPER_ORNAMENT);
            TMMORE_BLOCKS.accept(WEATHERED_COPPER_ORNAMENT);
            TMMORE_BLOCKS.accept(OXIDIZED_COPPER_ORNAMENT);

            TMMORE_BLOCKS.accept(BLEACHED_PLANKS);
            TMMORE_BLOCKS.accept(BLEACHED_PANEL);
            TMMORE_BLOCKS.accept(BLEACHED_STAIRS);
            TMMORE_BLOCKS.accept(BLEACHED_SLAB);
            TMMORE_BLOCKS.accept(BLEACHED_WALL);
            TMMORE_BLOCKS.accept(BLEACHED_FENCE);
            TMMORE_BLOCKS.accept(BLEACHED_WALL_PANEL);
            TMMORE_BLOCKS.accept(VERAWOOD_PLANKS);
            TMMORE_BLOCKS.accept(VERAWOOD_PANEL);
            TMMORE_BLOCKS.accept(VERAWOOD_STAIRS);
            TMMORE_BLOCKS.accept(VERAWOOD_SLAB);
            TMMORE_BLOCKS.accept(VERAWOOD_WALL);
            TMMORE_BLOCKS.accept(VERAWOOD_FENCE);


            //Wall panels
            TMMORE_BLOCKS.accept(VERAWOOD_WALL_PANEL);
            TMMORE_BLOCKS.accept(BUBINGA_WALL_PANEL);
            TMMORE_BLOCKS.accept(EBONY_WALL_PANEL);
            TMMORE_BLOCKS.accept(DARK_OAK_WALL_PANEL);
            TMMORE_BLOCKS.accept(MAHOGANY_WALL_PANEL);

            TMMORE_BLOCKS.accept(CANDY_CANE_BLOCK);

            TMMORE_BLOCKS.accept(DARK_MARBLE_TILE);
            TMMORE_BLOCKS.accept(CHECKERED_MARBLE_TILES);
            TMMORE_BLOCKS.accept(MIXED_MARBLE_TILES);
            TMMORE_BLOCKS.accept(DEEPWOKEN_TILE);

            TMMORE_BLOCKS.accept(PINK_CITRINE_BRICKS);
            TMMORE_BLOCKS.accept(POLISHED_PINK_CITRINE);
            TMMORE_BLOCKS.accept(SMOOTH_PINK_CITRINE);

            TMMORE_BLOCKS.accept(FORTERRA_BRICKS);
            TMMORE_BLOCKS.accept(POLISHED_FORTERRA);

            TMMORE_BLOCKS.accept(ASPHALT);

            TMMORE_BLOCKS.accept(ARCADE_FLOOR);

            //ETC NON BLOCK CONFORMING BLOCKS
            TMMORE_BLOCKS.accept(CHRISTMAS_LIGHTS);
            TMMORE_BLOCKS.accept(SNOWY_WREATH);
            TMMORE_BLOCKS.accept(WREATH);

            TMMORE_BLOCKS.accept(KILL_BLOCK);
            TMMORE_BLOCKS.accept(KILL_BLOCK_PANEL);
            TMMORE_BLOCKS.accept(ModItems.CANDELABRE_ITEM);
            TMMORE_BLOCKS.accept(STACK_LIGHTS);
            TMMORE_BLOCKS.accept(ROOF_LAMP);
            TMMORE_BLOCKS.accept(PALE_BENCH);
            TMMORE_BLOCKS.accept(QUEEN_BENCH);
            TMMORE_BLOCKS.accept(STEEL_BENCH);
            TMMORE_BLOCKS.accept(THORN_BENCH);
            TMMORE_BLOCKS.accept(BUTTERFLY_DOOR_BLOCK);
            TMMORE_BLOCKS.accept(SERVICE_BELL);

            //Plushies
            TMMORE_BLOCKS.accept(NORA_PLUSH);
            TMMORE_BLOCKS.accept(DAVIDANDROCKET_PLUSH);
            TMMORE_BLOCKS.accept(PICKLE_PLUSH);
            TMMORE_BLOCKS.accept(DUCKAMOLY_PLUSH);
            TMMORE_BLOCKS.accept(VERID__PLUSH);
            TMMORE_BLOCKS.accept(WILLO_PLUSH);
            TMMORE_BLOCKS.accept(MOKUS_PLUSH);
            TMMORE_BLOCKS.accept(DOOGEY_PLUSH);
            TMMORE_BLOCKS.accept(SQUID_PLUSH);
            TMMORE_BLOCKS.accept(PENCIL_PLUSH);
            TMMORE_BLOCKS.accept(INDIGO_PLUSH);


                });

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MOQUETTES_KEY, MOQUETTES);
        ItemGroupEvents.modifyEntriesEvent(ModBlocks.MOQUETTES_KEY).register(BUILDING_BLOCKS -> {
            // Normal Moquette Blocks
            BUILDING_BLOCKS.accept(BLACK_MOQUETTE);
            BUILDING_BLOCKS.accept(CYAN_MOQUETTE);
            BUILDING_BLOCKS.accept(GRAY_MOQUETTE);
            BUILDING_BLOCKS.accept(GREEN_MOQUETTE);
            BUILDING_BLOCKS.accept(LIGHT_BLUE_MOQUETTE);
            BUILDING_BLOCKS.accept(LIGHT_GRAY_MOQUETTE);
            BUILDING_BLOCKS.accept(LIME_MOQUETTE);
            BUILDING_BLOCKS.accept(MAGENTA_MOQUETTE);
            BUILDING_BLOCKS.accept(ORANGE_MOQUETTE);
            BUILDING_BLOCKS.accept(PINK_MOQUETTE);
            BUILDING_BLOCKS.accept(PURPLE_MOQUETTE);
            BUILDING_BLOCKS.accept(WHITE_MOQUETTE);
            BUILDING_BLOCKS.accept(YELLOW_MOQUETTE);

            // Carpets
            BUILDING_BLOCKS.accept(BLACK_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(CYAN_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(GRAY_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(GREEN_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(LIGHT_BLUE_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(LIGHT_GRAY_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(LIME_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(MAGENTA_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(ORANGE_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(PINK_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(PURPLE_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(WHITE_MOQUETTE_CARPET);
            BUILDING_BLOCKS.accept(YELLOW_MOQUETTE_CARPET);
        });

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, STRIPED_CARPET_KEY, STRIPED_CARPET);
        ItemGroupEvents.modifyEntriesEvent(STRIPED_CARPET_KEY).register(STRIPED_CARPET -> {
            // Striped Carpets
            // Blocks
            STRIPED_CARPET.accept(BLACK_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(BLUE_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(CYAN_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(GRAY_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(GREEN_STRIPED_CARPET_BLOCK);
            // STRIPED_CARPET.accept(ModBlocks.LIGHT_BLUE_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(LIGHT_GRAY_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(LIME_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(ORANGE_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(PURPLE_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(WHITE_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(YELLOW_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(RED_STRIPED_CARPET_BLOCK);
            STRIPED_CARPET.accept(BROWN_STRIPED_CARPET_BLOCK);

            // Carpets
            STRIPED_CARPET.accept(BLACK_STRIPED_CARPET);
            STRIPED_CARPET.accept(CYAN_STRIPED_CARPET);
            STRIPED_CARPET.accept(GRAY_STRIPED_CARPET);
            STRIPED_CARPET.accept(GREEN_STRIPED_CARPET);
            // STRIPED_CARPET.accept(ModBlocks.LIGHT_BLUE_STRIPED_CARPET);
            STRIPED_CARPET.accept(LIGHT_GRAY_STRIPED_CARPET);
            STRIPED_CARPET.accept(LIME_STRIPED_CARPET);
            STRIPED_CARPET.accept(ORANGE_STRIPED_CARPET);
            STRIPED_CARPET.accept(PURPLE_STRIPED_CARPET);
            STRIPED_CARPET.accept(WHITE_STRIPED_CARPET);
            STRIPED_CARPET.accept(YELLOW_STRIPED_CARPET);
            STRIPED_CARPET.accept(BLUE_STRIPED_CARPET);
            STRIPED_CARPET.accept(RED_STRIPED_CARPET);
            STRIPED_CARPET.accept(BROWN_STRIPED_CARPET);
        });
    }
}
