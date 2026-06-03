package net.mokus.wathextras.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.mokus.wathextras.block.ModBlocks;
import net.mokus.wathextras.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider extends FabricLanguageProvider {
    public ModLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider wrapperLookup, TranslationBuilder translationBuilder) {
        // Menus Etc.
        translationBuilder.add(ModBlocks.MOQUETTES_KEY, "WathExtras: Moquettes!");
        translationBuilder.add(ModBlocks.STRIPED_CARPET_KEY, "WathExtras: Striped Carpets!");
        translationBuilder.add(ModBlocks.TMMORE_BUILDING_KEY,"WathExtras: Building Blocks!");
        translationBuilder.add(ModItems.TMMORE_ITEM_KEY,"WathExtras: Items!");
        // translationBuilder.add(ModBlocks.Dark_Marble_Key, "MM!: Dark Marble");

        // Full blocks
        translationBuilder.add(ModBlocks.BLACK_MOQUETTE, "Black Moquette");
        translationBuilder.add(ModBlocks.CYAN_MOQUETTE, "Cyan Moquette");
        translationBuilder.add(ModBlocks.GRAY_MOQUETTE, "Gray Moquette");
        translationBuilder.add(ModBlocks.GREEN_MOQUETTE, "Green Moquette");
        translationBuilder.add(ModBlocks.LIGHT_BLUE_MOQUETTE, "Light Blue Moquette");
        translationBuilder.add(ModBlocks.LIGHT_GRAY_MOQUETTE, "Light Gray Moquette");
        translationBuilder.add(ModBlocks.LIME_MOQUETTE, "Lime Moquette");
        translationBuilder.add(ModBlocks.MAGENTA_MOQUETTE, "Magenta Moquette");
        translationBuilder.add(ModBlocks.ORANGE_MOQUETTE, "Orange Moquette");
        translationBuilder.add(ModBlocks.PINK_MOQUETTE, "Pink Moquette");
        translationBuilder.add(ModBlocks.PURPLE_MOQUETTE, "Purple Moquette");
        translationBuilder.add(ModBlocks.WHITE_MOQUETTE, "White Moquette");
        translationBuilder.add(ModBlocks.YELLOW_MOQUETTE, "Yellow Moquette");

        // Carpet Variants
        translationBuilder.add(ModBlocks.BLACK_MOQUETTE_CARPET, "Black Moquette Carpet");
        translationBuilder.add(ModBlocks.CYAN_MOQUETTE_CARPET, "Cyan Moquette Carpet");
        translationBuilder.add(ModBlocks.GRAY_MOQUETTE_CARPET, "Gray Moquette Carpet");
        translationBuilder.add(ModBlocks.GREEN_MOQUETTE_CARPET, "Green Moquette Carpet");
        translationBuilder.add(ModBlocks.LIGHT_BLUE_MOQUETTE_CARPET, "Light Blue Moquette Carpet");
        translationBuilder.add(ModBlocks.LIGHT_GRAY_MOQUETTE_CARPET, "Light Gray Moquette Carpet");
        translationBuilder.add(ModBlocks.LIME_MOQUETTE_CARPET, "Lime Moquette Carpet");
        translationBuilder.add(ModBlocks.MAGENTA_MOQUETTE_CARPET, "Magenta Moquette Carpet");
        translationBuilder.add(ModBlocks.ORANGE_MOQUETTE_CARPET, "Orange Moquette Carpet");
        translationBuilder.add(ModBlocks.PINK_MOQUETTE_CARPET, "Pink Moquette Carpet");
        translationBuilder.add(ModBlocks.PURPLE_MOQUETTE_CARPET, "Purple Moquette Carpet");
        translationBuilder.add(ModBlocks.WHITE_MOQUETTE_CARPET, "White Moquette Carpet");
        translationBuilder.add(ModBlocks.YELLOW_MOQUETTE_CARPET, "Yellow Moquette Carpet");

        // Striped Carpets
        // Blocks
        translationBuilder.add(ModBlocks.BLACK_STRIPED_CARPET_BLOCK , "Black Striped Carpet Block");
        translationBuilder.add(ModBlocks.BLUE_STRIPED_CARPET_BLOCK , "Blue Striped Carpet Block");
        translationBuilder.add(ModBlocks.CYAN_STRIPED_CARPET_BLOCK , "Cyan Striped Carpet Block");
        translationBuilder.add(ModBlocks.GRAY_STRIPED_CARPET_BLOCK , "Gray Striped Carpet Block");
        translationBuilder.add(ModBlocks.GREEN_STRIPED_CARPET_BLOCK , "Green Striped Carpet Block");
        // translationBuilder.add(ModBlocks.LIGHT_BLUE_STRIPED_CARPET_BLOCK , "Light Blue Striped Carpet Block");
        translationBuilder.add(ModBlocks.LIGHT_GRAY_STRIPED_CARPET_BLOCK , "Light Gray Striped Carpet Block");
        translationBuilder.add(ModBlocks.LIME_STRIPED_CARPET_BLOCK , "Lime Striped Carpet Block");
        translationBuilder.add(ModBlocks.ORANGE_STRIPED_CARPET_BLOCK , "Orange Striped Carpet Block");
        translationBuilder.add(ModBlocks.PURPLE_STRIPED_CARPET_BLOCK , "Purple Striped Carpet Block");
        translationBuilder.add(ModBlocks.WHITE_STRIPED_CARPET_BLOCK , "White Striped Carpet Block");
        translationBuilder.add(ModBlocks.YELLOW_STRIPED_CARPET_BLOCK , "Yellow Striped Carpet Block");
        translationBuilder.add(ModBlocks.RED_STRIPED_CARPET_BLOCK , "Red Striped Carpet Block");
        translationBuilder.add(ModBlocks.BROWN_STRIPED_CARPET_BLOCK , "Brown Striped Carpet Block");

        // Carpets
        translationBuilder.add(ModBlocks.BLACK_STRIPED_CARPET,"Black Striped Carpet");
        translationBuilder.add(ModBlocks.BLUE_STRIPED_CARPET,"Blue Striped Carpet");
        translationBuilder.add(ModBlocks.CYAN_STRIPED_CARPET,"Cyan Striped Carpet");
        translationBuilder.add(ModBlocks.GRAY_STRIPED_CARPET,"Gray Striped Carpet");
        translationBuilder.add(ModBlocks.GREEN_STRIPED_CARPET,"Green Striped Carpet");
        // translationBuilder.add(ModBlocks.LIGHT_BLUE_STRIPED_CARPET,"Light Blue Striped Carpet");
        translationBuilder.add(ModBlocks.LIGHT_GRAY_STRIPED_CARPET,"Light Gray Striped Carpet");
        translationBuilder.add(ModBlocks.LIME_STRIPED_CARPET,"Lime Striped Carpet");
        translationBuilder.add(ModBlocks.ORANGE_STRIPED_CARPET,"Orange Striped Carpet");
        translationBuilder.add(ModBlocks.PURPLE_STRIPED_CARPET,"Purple Striped Carpet");
        translationBuilder.add(ModBlocks.WHITE_STRIPED_CARPET,"White Striped Carpet");
        translationBuilder.add(ModBlocks.YELLOW_STRIPED_CARPET,"Yellow Striped Carpet");
        translationBuilder.add(ModBlocks.RED_STRIPED_CARPET,"Red Striped Carpet");
        translationBuilder.add(ModBlocks.BROWN_STRIPED_CARPET,"Brown Striped Carpet");

        // Plushies
        translationBuilder.add(ModBlocks.NORA_PLUSH,"Nora Plushie");
        translationBuilder.add(ModBlocks.DUCKAMOLY_PLUSH,"Duckamoly Plushie");
        translationBuilder.add(ModBlocks.DAVIDANDROCKET_PLUSH,"DavidandRocket Plushie");
        translationBuilder.add(ModBlocks.PICKLE_PLUSH,"Pickle Plushie");
        translationBuilder.add(ModBlocks.VERID__PLUSH,"Verid__ Plushie");
        translationBuilder.add(ModBlocks.WILLO_PLUSH,"Willofkek Plushie");
        translationBuilder.add(ModBlocks.DOOGEY_PLUSH,"Doogey Plush");
        translationBuilder.add(ModBlocks.MOKUS_PLUSH, "Mokus Plush");
        translationBuilder.add(ModBlocks.SQUID_PLUSH,"Squid Plush");
        translationBuilder.add(ModBlocks.PENCIL_PLUSH,"Pencil Plush");
        translationBuilder.add(ModBlocks.INDIGO_PLUSH,"Indigo Plush");

        //Hull Blocks!
        translationBuilder.add(ModBlocks.KHAKI_RIVETED_HULL, "Khaki Riveted Hull");
        translationBuilder.add(ModBlocks.KHAKI_RIVETED_HULL_SMALL, "Small Khaki Riveted Hull");
        translationBuilder.add(ModBlocks.KHAKI_RIVETED_HULL_SMALL_PANEL, "Small Khaki Riveted Hull Panel");
        translationBuilder.add(ModBlocks.KHAKI_RIVETED_HULL_SMALL_STAIRS, "Small Khaki Riveted Hull Stair");
        translationBuilder.add(ModBlocks.KHAKI_RIVETED_HULL_SMALL_SLAB, "Small Khaki Riveted Hull Slab");
        translationBuilder.add(ModBlocks.KHAKI_RIVETED_HULL_SMALL_WALL, "Small Khaki Riveted Hull Wall");

        translationBuilder.add(ModBlocks.ANTHRACITE_RIVETED_HULL, "Anthracite Riveted Hull");
        translationBuilder.add(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL, "Small Anthracite Riveted Hull");
        translationBuilder.add(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_PANEL, "Small Anthracite Riveted Hull Panel");
        translationBuilder.add(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_STAIRS, "Small Anthracite Riveted Hull Stair");
        translationBuilder.add(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_SLAB, "Small Anthracite Riveted Hull Slab");
        translationBuilder.add(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_WALL, "Small Anthracite Riveted Hull Wall");

        translationBuilder.add(ModBlocks.BLACK_RIVETED_HULL, "Black Riveted Hull");
        translationBuilder.add(ModBlocks.BLACK_RIVETED_HULL_SMALL, "Small Black Riveted Hull");
        translationBuilder.add(ModBlocks.BLACK_RIVETED_HULL_SMALL_PANEL, "Small Black Riveted Hull Panel");
        translationBuilder.add(ModBlocks.BLACK_RIVETED_HULL_SMALL_STAIRS, "Small Black Riveted Hull Stair");
        translationBuilder.add(ModBlocks.BLACK_RIVETED_HULL_SMALL_SLAB, "Small Black Riveted Hull Slab");
        translationBuilder.add(ModBlocks.BLACK_RIVETED_HULL_SMALL_WALL, "Small Black Riveted Hull Wall");

        translationBuilder.add(ModBlocks.MAROON_RIVETED_HULL, "Maroon Riveted Hull");
        translationBuilder.add(ModBlocks.MAROON_RIVETED_HULL_SMALL, "Small Maroon Riveted Hull");
        translationBuilder.add(ModBlocks.MAROON_RIVETED_HULL_SMALL_PANEL, "Small Maroon Riveted Hull Panel");
        translationBuilder.add(ModBlocks.MAROON_RIVETED_HULL_SMALL_STAIRS, "Small Maroon Riveted Hull Stair");
        translationBuilder.add(ModBlocks.MAROON_RIVETED_HULL_SMALL_SLAB, "Small Maroon Riveted Hull Slab");
        translationBuilder.add(ModBlocks.MAROON_RIVETED_HULL_SMALL_WALL, "Small Maroon Riveted Hull Wall");

        translationBuilder.add(ModBlocks.MUNTZ_RIVETED_HULL, "Muntz Riveted Hull");
        translationBuilder.add(ModBlocks.MUNTZ_RIVETED_HULL_SMALL, "Small Muntz Riveted Hull");
        translationBuilder.add(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_PANEL, "Small Muntz Riveted Hull Panel");
        translationBuilder.add(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_STAIRS, "Small Muntz Riveted Hull Stair");
        translationBuilder.add(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_SLAB, "Small Muntz Riveted Hull Slab");
        translationBuilder.add(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_WALL, "Small Muntz Riveted Hull Wall");

        translationBuilder.add(ModBlocks.NAVY_RIVETED_HULL_SMALL, "Small Navy Riveted Hull");
        translationBuilder.add(ModBlocks.NAVY_RIVETED_HULL, "Navy Riveted Hull");
        translationBuilder.add(ModBlocks.NAVY_RIVETED_HULL_SMALL_PANEL, "Small Navy Riveted Hull Panel");
        translationBuilder.add(ModBlocks.NAVY_RIVETED_HULL_SMALL_STAIRS, "Small Navy Riveted Hull Stair");
        translationBuilder.add(ModBlocks.NAVY_RIVETED_HULL_SMALL_SLAB, "Small Navy Riveted Hull Slab");
        translationBuilder.add(ModBlocks.NAVY_RIVETED_HULL_SMALL_WALL, "Small Navy Riveted Hull Wall");


        translationBuilder.add(ModBlocks.WHITE_RIVETED_HULL, "White Riveted Hull");
        translationBuilder.add(ModBlocks.WHITE_RIVETED_HULL_SMALL, "Small White Riveted Hull");
        translationBuilder.add(ModBlocks.WHITE_RIVETED_HULL_SMALL_PANEL, "Small White Riveted Hull Panel");
        translationBuilder.add(ModBlocks.WHITE_RIVETED_HULL_SMALL_STAIRS, "Small White Riveted Hull Stair");
        translationBuilder.add(ModBlocks.WHITE_RIVETED_HULL_SMALL_SLAB, "Small White Riveted Hull Slab");
        translationBuilder.add(ModBlocks.WHITE_RIVETED_HULL_SMALL_WALL, "Small White Riveted Hull Wall");

        translationBuilder.add(ModBlocks.CORRUGATED_DARK_STEEL,"Corrugated Dark Steel");
        translationBuilder.add(ModBlocks.CORRUGATED_DARK_STEEL_STAIRS,"Corrugated Dark Steel Stairs");
        translationBuilder.add(ModBlocks.CORRUGATED_DARK_STEEL_PANEL,"Corrugated Dark Steel Panel");
        translationBuilder.add(ModBlocks.CORRUGATED_DARK_STEEL_SLAB,"Corrugated Dark Steel Slab");
        translationBuilder.add(ModBlocks.CORRUGATED_DARK_STEEL_WALL,"Corrugated Dark Steel Wall");

        translationBuilder.add(ModBlocks.CORRUGATED_STAINLESS_STEEL,"Corrugated Stainless Steel");
        translationBuilder.add(ModBlocks.CORRUGATED_STAINLESS_STEEL_STAIRS,"Corrugated Stainless Steel Stairs");
        translationBuilder.add(ModBlocks.CORRUGATED_STAINLESS_STEEL_PANEL,"Corrugated Stainless Steel Panel");
        translationBuilder.add(ModBlocks.CORRUGATED_STAINLESS_STEEL_SLAB,"Corrugated Stainless Steel Slab");
        translationBuilder.add(ModBlocks.CORRUGATED_STAINLESS_STEEL_WALL,"Corrugated Stainless Steel Wall");

        translationBuilder.add(ModBlocks.PERFORATED_DARK_STEEL,"Perforated Dark Steel");
        translationBuilder.add(ModBlocks.PERFORATED_DARK_STEEL_STAIRS,"Perforated Dark Steel Stairs");
        translationBuilder.add(ModBlocks.PERFORATED_DARK_STEEL_PANEL,"Perforated Dark Steel Panel");
        translationBuilder.add(ModBlocks.PERFORATED_DARK_STEEL_SLAB,"Perforated Dark Steel Slab");
        translationBuilder.add(ModBlocks.PERFORATED_DARK_STEEL_WALL,"Perforated Dark Steel Wall");

        translationBuilder.add(ModBlocks.PERFORATED_STAINLESS_STEEL,"Perforated Stainless Steel");
        translationBuilder.add(ModBlocks.PERFORATED_STAINLESS_STEEL_STAIRS,"Perforated Stainless Steel Stairs");
        translationBuilder.add(ModBlocks.PERFORATED_STAINLESS_STEEL_PANEL,"Perforated Stainless Steel Panel");
        translationBuilder.add(ModBlocks.PERFORATED_STAINLESS_STEEL_SLAB,"Perforated Stainless Steel Slab");
        translationBuilder.add(ModBlocks.PERFORATED_STAINLESS_STEEL_WALL,"Perforated Stainless Steel Wall");

        // Planks
        translationBuilder.add(ModBlocks.BLEACHED_PLANKS, "Bleached Planks");
        translationBuilder.add(ModBlocks.BLEACHED_STAIRS, "Bleached Stairs");
        translationBuilder.add(ModBlocks.BLEACHED_FENCE, "Bleached Fence");
        translationBuilder.add(ModBlocks.BLEACHED_PANEL, "Bleached Panel");
        translationBuilder.add(ModBlocks.BLEACHED_SLAB, "Bleached Slab");
        translationBuilder.add(ModBlocks.BLEACHED_WALL, "Bleached Wall");

        translationBuilder.add(ModBlocks.VERAWOOD_PLANKS, "Verawood Planks");
        translationBuilder.add(ModBlocks.VERAWOOD_STAIRS, "Verawood Stairs");
        translationBuilder.add(ModBlocks.VERAWOOD_SLAB, "Verawood Slab");
        translationBuilder.add(ModBlocks.VERAWOOD_FENCE, "Verawood Fence");
        translationBuilder.add(ModBlocks.VERAWOOD_PANEL, "Verawood Panel");
        translationBuilder.add(ModBlocks.VERAWOOD_WALL, "Verawood Wall");
        translationBuilder.add(ModBlocks.VERAWOOD_WALL_PANEL, "Verawood Panel Wall");

        // Ornaments
        translationBuilder.add(ModBlocks.DARK_STEEL_ORNAMENT,"Dark Steel Ornament");
        translationBuilder.add(ModBlocks.STAINLESS_STEEL_ORNAMENT,"Stainless Steel Ornament");
        translationBuilder.add(ModBlocks.BRONZE_ORNAMENT,"Bronze Ornament");
        translationBuilder.add(ModBlocks.PLATINUM_ORNAMENT,"Platinum Ornament");
        translationBuilder.add(ModBlocks.COPPER_ORNAMENT,"Copper Ornament");
        translationBuilder.add(ModBlocks.EXPOSED_COPPER_ORNAMENT,"Exposed Copper Ornament");
        translationBuilder.add(ModBlocks.WEATHERED_COPPER_ORNAMENT,"Weathered Copper Ornament");
        translationBuilder.add(ModBlocks.OXIDIZED_COPPER_ORNAMENT,"Oxidized Copper Ornament");

        // Candy Cane
        translationBuilder.add(ModBlocks.CANDY_CANE_BLOCK,"Candy Cane Block");
        translationBuilder.add(ModBlocks.ZIGZAG_CANDY_BLOCK, "Zig Zag Candy Cane");

        // Benches
        translationBuilder.add(ModBlocks.PALE_BENCH, "Pale Bench");
        translationBuilder.add(ModBlocks.QUEEN_BENCH, "Queen's Bench");
        translationBuilder.add(ModBlocks.STEEL_BENCH, "Steel Bench");
        translationBuilder.add(ModBlocks.THORN_BENCH, "Thorn Bench");

        // Marble
        translationBuilder.add(ModBlocks.DARK_MARBLE_TILE, "Dark Marble Tiles");
        translationBuilder.add(ModBlocks.CHECKERED_MARBLE_TILES, "Checkered Marble Tiles");
        translationBuilder.add(ModBlocks.MIXED_MARBLE_TILES, "Mixed Marble Tiles");
        translationBuilder.add(ModBlocks.DEEPWOKEN_TILE, "Deepwoken Tile");

        translationBuilder.add(ModBlocks.ARCADE_FLOOR,"Arcade Floor");

        // Items
        translationBuilder.add(ModItems.CANDELABRE_ITEM,"Candelbra");
        translationBuilder.add(ModItems.MILK_GLASS,"Glass of Milk");
        translationBuilder.add(ModItems.STRAWBERRY_MILK_GLASS,"Glass of Strawberry Milk");
        translationBuilder.add(ModItems.CHOCCY_MILK_GLASS,"Glass of Choccy Milk");
        translationBuilder.add(ModItems.CANDY_CANE,"Candy Cane");
        translationBuilder.add(ModItems.BLUE_LOLLIPOP, "Blue Lollipop");
        translationBuilder.add(ModItems.RED_LOLLIPOP, "Red Lollipop");
        translationBuilder.add(ModItems.YELLOW_LOLLIPOP, "Yellow Lollipop");
        translationBuilder.add(ModItems.GREEN_LOLLIPOP, "Green Lollipop");

        translationBuilder.add(ModItems.BLENDED_BERRY_BLAST,"Blended Berry Blast");
        translationBuilder.add(ModItems.COTTON_SWIZZLE,"Cotton Swizzle");
        translationBuilder.add(ModItems.NO_BERRY_LEMON_SOUR,"No Berry Lemon Sour");
        translationBuilder.add(ModItems.SALTED_SEAFRUIT,"Salted Seafruit");
        translationBuilder.add(ModItems.SAPPHIC_SUNSET,"Sapphic Sunset");
        translationBuilder.add(ModItems.THE_ALL_IN,"The All In");
        translationBuilder.add(ModItems.THE_CHROMATIC,"The Chromatic");
        translationBuilder.add(ModItems.CAKESLICE,"Slice of Cake");

        translationBuilder.add(ModItems.HOT_CHOCOLATE,"Hot Chocolate");

        translationBuilder.add(ModBlocks.PINK_CITRINE_BRICKS,"Pink Citrine Bricks");
        translationBuilder.add(ModBlocks.POLISHED_PINK_CITRINE,"Polished Pink Citrine");
        translationBuilder.add(ModBlocks.SMOOTH_PINK_CITRINE,"Smooth Pink Citrine");

        translationBuilder.add(ModBlocks.FORTERRA_BRICKS,"Forterra Bricks");
        translationBuilder.add(ModBlocks.POLISHED_FORTERRA,"Polished Forterra");

        translationBuilder.add(ModBlocks.ASPHALT, "Asphalt");

        translationBuilder.add(ModBlocks.BUTTERFLY_DOOR_BLOCK, "Butterfly Stoker Door");
        translationBuilder.add(ModBlocks.SERVICE_BELL,"Service Bell");
        translationBuilder.add(ModBlocks.STACK_LIGHTS, "Modern Stack Lights");
        translationBuilder.add(ModBlocks.ROOF_LAMP, "Roof Lamp");

        translationBuilder.add(ModBlocks.EBONY_WALL_PANEL, "Ebony Panel Wall");
        translationBuilder.add(ModBlocks.BLEACHED_WALL_PANEL, "Bleached Panel Wall");
        translationBuilder.add(ModBlocks.MAHOGANY_WALL_PANEL, "Mahogany Panel Wall");
        translationBuilder.add(ModBlocks.DARK_OAK_WALL_PANEL, "Dark Oak Panel Wall");

        translationBuilder.add(ModBlocks.KILL_BLOCK, "Kill Block");
        translationBuilder.add(ModBlocks.KILL_BLOCK_PANEL, "Kill Block Panel");

        translationBuilder.add(ModItems.GINGERBREAD,"Gingerbread");
        translationBuilder.add(ModItems.GINGERBREAD_CHOCO,"Chocolate Gingerbread");
        translationBuilder.add(ModItems.GINGERBREAD_DUCKAMOLY,"Duckamoly-bread");
        translationBuilder.add(ModItems.GINGERBREAD_NORA,"Nora-bread");
        translationBuilder.add(ModItems.GINGERBREAD_RUDOLPH,"Rudolph Gingerbread");
        translationBuilder.add(ModItems.GINGERBREAD_SCARF,"Scarfed Gingerbread");

        translationBuilder.add(ModBlocks.CHRISTMAS_LIGHTS,"Christmas Lights");
        translationBuilder.add(ModBlocks.WREATH,"Wreath");
        translationBuilder.add(ModBlocks.SNOWY_WREATH,"Snowy Wreath");

        translationBuilder.add(ModItems.CAKESLICE,"Slice of Cake");

        translationBuilder.add(ModItems.WATER_GLASS,"Glass of Water");
        translationBuilder.add(ModItems.MIXED_MOCKTAIL,"Mixed Mocktail");
        translationBuilder.add(ModItems.TEQUILA_SHOT,"Shot of Tequila");
        translationBuilder.add(ModItems.WHISKEY_SOUR,"Whiskey Sour");
        translationBuilder.add(ModItems.MOONSHINE,"Moonshine");
        translationBuilder.add(ModItems.BEER_PINT,"Pint of Beer");
        translationBuilder.add(ModItems.HOT_CHOCOLATE,"Hot Chocolate");

        translationBuilder.add(ModItems.BLENDED_BERRY_BLAST,"\"Blended Berry Blast\"");
        translationBuilder.add(ModItems.COTTON_SWIZZLE,"\"Cotton Swizzle\"");
        translationBuilder.add(ModItems.NO_BERRY_LEMON_SOUR,"\"No Berry Lemon Sour\"");
        translationBuilder.add(ModItems.SALTED_SEAFRUIT,"\"Salted Seafruit\"");
        translationBuilder.add(ModItems.SAPPHIC_SUNSET,"\"Sapphic Sunset\"");
        translationBuilder.add(ModItems.THE_ALL_IN,"\"The All In\"");
        translationBuilder.add(ModItems.THE_CHROMATIC,"\"The Chromatic\"");

        translationBuilder.add("tooltip.wathextras.killblocks.block","Kills players that stand on top of the block");
        translationBuilder.add("tooltip.wathextras.killblocks.panel","Kills players that touch the block the panel is in");

        translationBuilder.add("sounds.tmmore.squid_plush","Squid plays a tune");
        translationBuilder.add("sounds.tmmore.willo_plush","Willo \"POWs!\"");
        translationBuilder.add("sounds.tmmore.pickle_plush","Pickle makes noise");
        translationBuilder.add("sounds.tmmore.verid__plush","Verid opens the cashdrawer");
        translationBuilder.add("sounds.tmmore.doogey_plush","Doogey becomes one with code");
        translationBuilder.add("sounds.tmmore.davidandrocket_plush","davidandrocket fights a fryingpan");
        translationBuilder.add("sounds.tmmore.duckamoly_plush","Duckamoly Quacks!");
        translationBuilder.add("sounds.tmmore.indigo_plush","Indigo goes \"yippie\"");

        // Paintings
        translationBuilder.add("painting.tmmore.cold_whispings.title","Cold Whispings");
        translationBuilder.add("painting.tmmore.cold_whispings.author","PhantomPickle");
        translationBuilder.add("painting.tmmore.fishpainting.title","Fish");
        translationBuilder.add("painting.tmmore.fishpainting.author","PhantomPickle");


        translationBuilder.add("painting.tmmore.veraportrait.title","Vera");
        translationBuilder.add("painting.tmmore.veraportrait.author","duckamoly");
        translationBuilder.add("painting.tmmore.impression.title","Impression");
        translationBuilder.add("painting.tmmore.impression.author","duckamoly");
        translationBuilder.add("painting.tmmore.visitor.title","Visitor");
        translationBuilder.add("painting.tmmore.visitor.author","duckamoly");


        translationBuilder.add("painting.tmmore.wall_banana.title","Wall Banana");
        translationBuilder.add("painting.tmmore.wall_banana.author","DavidAndRocket");

        translationBuilder.add("painting.tmmore.dogspoker.title","Dogs Poker");
        translationBuilder.add("painting.tmmore.dogspoker.author","willofkek");
    }
}
