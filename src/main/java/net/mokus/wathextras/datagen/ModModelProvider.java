package net.mokus.wathextras.datagen;

import com.mojang.datafixers.util.Pair;

import io.wifi.starrailexpress.content.block.OrnamentBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.models.*;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.Util;
import net.minecraft.core.Direction;
import net.mokus.wathextras.WathExtras;
import net.mokus.wathextras.block.ModBlocks;
import net.mokus.wathextras.block.custom.BenchBlock;
import net.mokus.wathextras.block.custom.DoubleHullBlock;
import net.mokus.wathextras.block.custom.WallPanelBlock;
import net.mokus.wathextras.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

import static net.minecraft.data.models.BlockModelGenerators.*;


public class ModModelProvider extends FabricModelProvider {


    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }


    //Literally just code from TMM, I gave up trying to use other methods to access them. PS if you have other ideas
    //PLEASE SHARE THEM

    private static Model template(ResourceLocation parent, @Nullable String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(parent), Optional.ofNullable(variant), requiredTextureKeys);
    }

    private static Model template(ResourceLocation parent, TextureKey... requiredTextureKeys) {
        return template(parent, null, requiredTextureKeys);
    }

    private static Model template(String parentName, TextureKey... requiredTextureKeys) {
        return template(ResourceLocation.fromNamespaceAndPath("wathe", parentName), requiredTextureKeys);
    }

    private BlockStateVariant rotateForFace(BlockStateVariant variant, Direction direction, boolean uvlock) {
        if (uvlock) {
            variant.put(VariantSettings.UVLOCK, true);
        }
        switch (direction) {
            case EAST -> variant.put(VariantSettings.Y, VariantSettings.Rotation.R90);
            case SOUTH -> variant.put(VariantSettings.Y, VariantSettings.Rotation.R180);
            case WEST -> variant.put(VariantSettings.Y, VariantSettings.Rotation.R270);
            case UP -> variant.put(VariantSettings.X, VariantSettings.Rotation.R270);
            case DOWN -> variant.put(VariantSettings.X, VariantSettings.Rotation.R90);
        }
        return variant;
    }
    private BlockStateVariant variant() {
        return BlockStateVariant.of();
    }

    private <T> BlockStateVariant variant(VariantSetting<T> variantSetting, T value) {
        return this.variant().put(variantSetting, value);
    }

    private BlockStateVariant model(ResourceLocation model) {
        return this.variant(VariantSettings.MODEL, model);
    }

    private static final Model ORNAMENT_R0 = template(
            "block/template_ornament_r0", TextureKey.TEXTURE
    );
    private static final Model ORNAMENT_R90 = template(
            "block/template_ornament_r90", TextureKey.TEXTURE
    );
    private static final Model ORNAMENT_R180 = template(
            "block/template_ornament_r180", TextureKey.TEXTURE
    );
    private static final Model ORNAMENT_R270 = template(
            "block/template_ornament_r270", TextureKey.TEXTURE
    );

    private static final Model PANEL = template(
            "block/template_panel", TextureKey.ALL
    );



    private void registerPanel(BlockStateModelGenerator generator, Block block, Block textureBlock) {
        registerPanel(generator, block, TextureMap.getId(textureBlock));
    }

    private void registerPanel(BlockStateModelGenerator generator, Block block, ResourceLocation texture) {
        Models.GENERATED.upload(ModelIds.getItemModelId(block.asItem()), TextureMap.layer0(texture), generator.modelCollector);
        ResourceLocation model = PANEL.upload(block, TextureMap.all(texture), generator.modelCollector);
        MultipartBlockStateSupplier blockStateSupplier = MultipartBlockStateSupplier.create(block);
        When.PropertyCondition propertyCondition = When.of();
        BlockStateModelGenerator.CONNECTION_VARIANT_FUNCTIONS.stream().map(Pair::getFirst)
                .forEach(property -> propertyCondition.set(property, false));

        for (Pair<BooleanProperty, Function<ResourceLocation, BlockStateVariant>> pair : BlockStateModelGenerator.CONNECTION_VARIANT_FUNCTIONS) {
            BooleanProperty facingProperty = pair.getFirst();
            BlockStateVariant variant = pair.getSecond().apply(model);
            blockStateSupplier.with(When.of().set(facingProperty, true), variant);
            blockStateSupplier.with(propertyCondition, variant);
        }

        generator.blockStateCollector.accept(blockStateSupplier);
    }

    private void registerOrnament(BlockStateModelGenerator generator, Block block) {
        TextureMap allTexture = TextureMap.texture(TextureMap.getSubId(block, "_all"));
        TextureMap endTexture = TextureMap.texture(TextureMap.getSubId(block, "_end"));
        TextureMap sideTexture = TextureMap.texture(TextureMap.getSubId(block, "_side"));
        TextureMap cornerTexture = TextureMap.texture(TextureMap.getSubId(block, "_corner"));
        TextureMap sidesTexture = TextureMap.texture(TextureMap.getSubId(block, "_sides"));
        TextureMap centerTexture = TextureMap.texture(TextureMap.getSubId(block, "_center"));
        TextureMap sidesCenterTexture = TextureMap.texture(TextureMap.getSubId(block, "_sides_center"));
        ORNAMENT_R0.upload(block, "_all", allTexture, generator.modelCollector);
        ORNAMENT_R0.upload(block, "_center", centerTexture, generator.modelCollector);
        ORNAMENT_R0.upload(block, "_left_right_center", sidesCenterTexture, generator.modelCollector);
        ORNAMENT_R0.upload(block, "_left", sideTexture, generator.modelCollector);
        ORNAMENT_R90.upload(block, "_top", sideTexture, generator.modelCollector);
        ORNAMENT_R180.upload(block, "_right", sideTexture, generator.modelCollector);
        ORNAMENT_R270.upload(block, "_bottom", sideTexture, generator.modelCollector);
        ORNAMENT_R0.upload(block, "_left_bottom", cornerTexture, generator.modelCollector);
        ORNAMENT_R90.upload(block, "_left_top", cornerTexture, generator.modelCollector);
        ORNAMENT_R180.upload(block, "_right_top", cornerTexture, generator.modelCollector);
        ORNAMENT_R270.upload(block, "_right_bottom", cornerTexture, generator.modelCollector);
        ORNAMENT_R0.upload(block, "_left_right", sidesTexture, generator.modelCollector);
        ORNAMENT_R90.upload(block, "_top_bottom", sidesTexture, generator.modelCollector);
        ORNAMENT_R0.upload(block, "_left_right_top", endTexture, generator.modelCollector);
        ORNAMENT_R90.upload(block, "_right_top_bottom", endTexture, generator.modelCollector);
        ORNAMENT_R180.upload(block, "_left_right_bottom", endTexture, generator.modelCollector);
        ORNAMENT_R270.upload(block, "_left_top_bottom", endTexture, generator.modelCollector);
        generator.registerItemModel(block, "_all");
        BlockStateVariantMap map = BlockStateVariantMap.create(OrnamentBlock.FACING, OrnamentBlock.SHAPE).register((facing, shape) ->
                this.rotateForFace(this.model(ModelIds.getBlockSubModelId(block, "_" + shape.getSerializedName())), facing, false)
        );
        generator.blockStateCollector.accept(VariantsBlockStateSupplier.create(block).coordinate(map));
    }

    //Joinked code end

    private static Model templateM(String parentName, TextureKey... requiredTextureKeys) {
        return template(ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID,parentName), requiredTextureKeys);
    }

    private static final Model BENCH_LEFT = templateM(
            "block/bench_left_template", TextureKey.ALL
    );

    private static final Model BENCH_CENTER = templateM(
            "block/bench_center_template", TextureKey.ALL
    );

    private static final Model BENCH_RIGHT = templateM(
            "block/bench_right_template", TextureKey.ALL
    );

    private static final Model BENCH_ITEM = templateM(
            "block/bench_item_template", TextureKey.ALL
    );

    private static final Model DOUBLE_HULL_BLOCK= templateM(
            "block/double_hull_block_template", TextureKey.ALL
    );

    private void registerBenchBlock(BlockStateModelGenerator generator, Block block) {
        TextureMap textureMap = TextureMap.all(block);
        ResourceLocation leftModel = BENCH_LEFT.upload(block, "_left", textureMap, generator.modelCollector);
        ResourceLocation centerModel = BENCH_CENTER.upload(block, "_center", textureMap, generator.modelCollector);
        ResourceLocation itemModel = BENCH_ITEM.upload(block, "_item", textureMap, generator.modelCollector);
        ResourceLocation rightModel = BENCH_RIGHT.upload(block, "_right", textureMap, generator.modelCollector);

        generator.registerParentedItemModel(block, itemModel);

        generator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(block)
                        .coordinate(
                                BlockStateVariantMap.create(BenchBlock.PART)
                                        .register(BenchBlock.PartType.LEFT,
                                                BlockStateVariant.of().put(VariantSettings.MODEL, leftModel))
                                        .register(BenchBlock.PartType.CENTER,
                                                BlockStateVariant.of().put(VariantSettings.MODEL, centerModel))
                                        .register(BenchBlock.PartType.RIGHT,
                                                BlockStateVariant.of().put(VariantSettings.MODEL, rightModel))
                        )
                        .coordinate(BlockStateModelGenerator.createSouthDefaultHorizontalRotationStates())
        );
    }

    private void registerDoubleHullBlock(BlockStateModelGenerator generator, Block block) {
        TextureMap leftTextureMap = TextureMap.all(TextureMap.getSubId(block, "_left"));
        TextureMap rightTextureMap = TextureMap.all(TextureMap.getSubId(block, "_right"));

        ResourceLocation leftModel = DOUBLE_HULL_BLOCK.upload(block, "_left", leftTextureMap, generator.modelCollector);
        ResourceLocation rightModel = DOUBLE_HULL_BLOCK.upload(block, "_right", rightTextureMap, generator.modelCollector);

        generator.registerParentedItemModel(block, leftModel);

        generator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(block)
                        .coordinate(
                                BlockStateVariantMap.create(DoubleHullBlock.PART)
                                        .register(DoubleHullBlock.PartType.LEFT,
                                                BlockStateVariant.of().put(VariantSettings.MODEL, leftModel))
                                        .register(DoubleHullBlock.PartType.RIGHT,
                                                BlockStateVariant.of().put(VariantSettings.MODEL, rightModel))
                        )
                        .coordinate(BlockStateModelGenerator.createSouthDefaultHorizontalRotationStates())
        );
    }


    public final void registerCandelabra(BlockStateModelGenerator generator,Block candelabre, Block wallCandelabre) {
        ResourceLocation unlitModel = ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID, "block/candelabre");
        ResourceLocation litModel = ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID, "block/candelabre_lit");

        generator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(candelabre)
                        .coordinate(createBooleanModelMap(BlockStateProperties.LIT, litModel, unlitModel))
        );

        ResourceLocation wallUnlitModel = ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID, "block/wall_candelabre");
        ResourceLocation wallLitModel = ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID, "block/wall_candelabre_lit");

        generator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(wallCandelabre)
                        .coordinate(createBooleanModelMap(BlockStateProperties.LIT, wallLitModel, wallUnlitModel))
                        .coordinate(createEastDefaultHorizontalRotationStates())
        );

        generator.excludeFromSimpleItemModelGeneration(candelabre);
        generator.excludeFromSimpleItemModelGeneration(wallCandelabre);
    }

    private void registerConnectiveBlock(BlockStateModelGenerator generator, Block block) {
        TextureMap singleTexture = new TextureMap()
                .put(TextureKey.SIDE, TextureMap.getSubId(block, "_side_single"))
                .put(TextureKey.END, TextureMap.getSubId(block, "_top"));

        TextureMap topTexture = new TextureMap()
                .put(TextureKey.SIDE, TextureMap.getSubId(block, "_side_top"))
                .put(TextureKey.END, TextureMap.getSubId(block, "_top"));

        TextureMap middleTexture = new TextureMap()
                .put(TextureKey.SIDE, TextureMap.getSubId(block, "_side_middle"))
                .put(TextureKey.END, TextureMap.getSubId(block, "_top"));

        TextureMap bottomTexture = new TextureMap()
                .put(TextureKey.SIDE, TextureMap.getSubId(block, "_side_bottom"))
                .put(TextureKey.END, TextureMap.getSubId(block, "_top"));

        TextureMap boxTexture = new TextureMap()
                .put(TextureKey.SIDE, TextureMap.getSubId(block, "_top"))
                .put(TextureKey.END, TextureMap.getSubId(block, "_top"));

        ResourceLocation singleModel = Models.CUBE_COLUMN.upload(block, "_single", singleTexture, generator.modelCollector);
        ResourceLocation topModel = Models.CUBE_COLUMN.upload(block, "_top", topTexture, generator.modelCollector);
        ResourceLocation middleModel = Models.CUBE_COLUMN.upload(block, "_middle", middleTexture, generator.modelCollector);
        ResourceLocation bottomModel = Models.CUBE_COLUMN.upload(block, "_bottom", bottomTexture, generator.modelCollector);
        ResourceLocation boxModel = Models.CUBE_COLUMN.upload(block, "_box", boxTexture, generator.modelCollector);

        generator.registerParentedItemModel(block,singleModel);

        generator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(block)
                        .coordinate(BlockStateVariantMap.create(WallPanelBlock.PART)
                                .register(WallPanelBlock.PartType.SINGLE,
                                        BlockStateVariant.of().put(VariantSettings.MODEL, singleModel))
                                .register(WallPanelBlock.PartType.TOP,
                                        BlockStateVariant.of().put(VariantSettings.MODEL, topModel))
                                .register(WallPanelBlock.PartType.MIDDLE,
                                        BlockStateVariant.of().put(VariantSettings.MODEL, middleModel))
                                .register(WallPanelBlock.PartType.BOTTOM,
                                        BlockStateVariant.of().put(VariantSettings.MODEL, bottomModel))
                                .register(WallPanelBlock.PartType.BOX,
                                        BlockStateVariant.of().put(VariantSettings.MODEL, boxModel))
                        )
        );
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {

        registerCandelabra(generator,ModBlocks.CANDELABRE,ModBlocks.WALL_CANDELABRE);

        registerBenchBlock(generator,ModBlocks.PALE_BENCH);
        registerBenchBlock(generator,ModBlocks.QUEEN_BENCH);
        registerBenchBlock(generator,ModBlocks.STEEL_BENCH);
        registerBenchBlock(generator,ModBlocks.THORN_BENCH);

        //Small Hulls
        BlockStateModelGenerator.BlockTexturePool KHAKI_RIVETED_HULL_SMALL =
                generator.registerCubeAllModelTexturePool(ModBlocks.KHAKI_RIVETED_HULL_SMALL);
        KHAKI_RIVETED_HULL_SMALL.stairs(ModBlocks.KHAKI_RIVETED_HULL_SMALL_STAIRS);
        KHAKI_RIVETED_HULL_SMALL.slab(ModBlocks.KHAKI_RIVETED_HULL_SMALL_SLAB);
        KHAKI_RIVETED_HULL_SMALL.wall(ModBlocks.KHAKI_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.KHAKI_RIVETED_HULL);

        BlockStateModelGenerator.BlockTexturePool ANTHRACITE_RIVETED_HULL_SMALL =
                generator.registerCubeAllModelTexturePool(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL);
        ANTHRACITE_RIVETED_HULL_SMALL.stairs(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_STAIRS);
        ANTHRACITE_RIVETED_HULL_SMALL.slab(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_SLAB);
        ANTHRACITE_RIVETED_HULL_SMALL.wall(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.ANTHRACITE_RIVETED_HULL);


        BlockStateModelGenerator.BlockTexturePool BLACK_RIVETED_HULL_SMALL =
                generator.registerCubeAllModelTexturePool(ModBlocks.BLACK_RIVETED_HULL_SMALL);
        BLACK_RIVETED_HULL_SMALL.stairs(ModBlocks.BLACK_RIVETED_HULL_SMALL_STAIRS);
        BLACK_RIVETED_HULL_SMALL.slab(ModBlocks.BLACK_RIVETED_HULL_SMALL_SLAB);
        BLACK_RIVETED_HULL_SMALL.wall(ModBlocks.BLACK_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.BLACK_RIVETED_HULL);


        BlockStateModelGenerator.BlockTexturePool MAROON_RIVETED_HULL_SMALL =
                generator.registerCubeAllModelTexturePool(ModBlocks.MAROON_RIVETED_HULL_SMALL);
        MAROON_RIVETED_HULL_SMALL.stairs(ModBlocks.MAROON_RIVETED_HULL_SMALL_STAIRS);
        MAROON_RIVETED_HULL_SMALL.slab(ModBlocks.MAROON_RIVETED_HULL_SMALL_SLAB);
        MAROON_RIVETED_HULL_SMALL.wall(ModBlocks.MAROON_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.MAROON_RIVETED_HULL);


        BlockStateModelGenerator.BlockTexturePool MUNTZ_RIVETED_HULL_SMALL =
                generator.registerCubeAllModelTexturePool(ModBlocks.MUNTZ_RIVETED_HULL_SMALL);
        MUNTZ_RIVETED_HULL_SMALL.stairs(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_STAIRS);
        MUNTZ_RIVETED_HULL_SMALL.slab(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_SLAB);
        MUNTZ_RIVETED_HULL_SMALL.wall(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.MUNTZ_RIVETED_HULL);


        BlockStateModelGenerator.BlockTexturePool NAVY_RIVETED_HULL_SMALL =
                generator.registerCubeAllModelTexturePool(ModBlocks.NAVY_RIVETED_HULL_SMALL);
        NAVY_RIVETED_HULL_SMALL.stairs(ModBlocks.NAVY_RIVETED_HULL_SMALL_STAIRS);
        NAVY_RIVETED_HULL_SMALL.slab(ModBlocks.NAVY_RIVETED_HULL_SMALL_SLAB);
        NAVY_RIVETED_HULL_SMALL.wall(ModBlocks.NAVY_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.NAVY_RIVETED_HULL);


        BlockStateModelGenerator.BlockTexturePool WHITE_RIVETED_HULL_SMALL =
                generator.registerCubeAllModelTexturePool(ModBlocks.WHITE_RIVETED_HULL_SMALL);
        WHITE_RIVETED_HULL_SMALL.stairs(ModBlocks.WHITE_RIVETED_HULL_SMALL_STAIRS);
        WHITE_RIVETED_HULL_SMALL.slab(ModBlocks.WHITE_RIVETED_HULL_SMALL_SLAB);
        WHITE_RIVETED_HULL_SMALL.wall(ModBlocks.WHITE_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.WHITE_RIVETED_HULL);


        BlockStateModelGenerator.BlockTexturePool BLEACHED =
                generator.registerCubeAllModelTexturePool(ModBlocks.BLEACHED_PLANKS);
        BLEACHED.stairs(ModBlocks.BLEACHED_STAIRS);
        BLEACHED.slab(ModBlocks.BLEACHED_SLAB);
        BLEACHED.wall(ModBlocks.BLEACHED_WALL);
        BLEACHED.fence(ModBlocks.BLEACHED_FENCE);

        BlockStateModelGenerator.BlockTexturePool VERAWOOD =
                generator.registerCubeAllModelTexturePool(ModBlocks.VERAWOOD_PLANKS);
        VERAWOOD.stairs(ModBlocks.VERAWOOD_STAIRS);
        VERAWOOD.slab(ModBlocks.VERAWOOD_SLAB);
        VERAWOOD.wall(ModBlocks.VERAWOOD_WALL);
        VERAWOOD.fence(ModBlocks.VERAWOOD_FENCE);
        this.registerPanel(generator,ModBlocks.VERAWOOD_PANEL,ModBlocks.VERAWOOD_PLANKS);

        //Panel registration
        this.registerPanel(generator,ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_PANEL,ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL);
        this.registerPanel(generator,ModBlocks.BLACK_RIVETED_HULL_SMALL_PANEL,ModBlocks.BLACK_RIVETED_HULL_SMALL);
        this.registerPanel(generator,ModBlocks.MAROON_RIVETED_HULL_SMALL_PANEL,ModBlocks.MAROON_RIVETED_HULL_SMALL);
        this.registerPanel(generator,ModBlocks.MUNTZ_RIVETED_HULL_SMALL_PANEL,ModBlocks.MUNTZ_RIVETED_HULL_SMALL);
        this.registerPanel(generator,ModBlocks.NAVY_RIVETED_HULL_SMALL_PANEL,ModBlocks.NAVY_RIVETED_HULL_SMALL);
        this.registerPanel(generator,ModBlocks.WHITE_RIVETED_HULL_SMALL_PANEL,ModBlocks.WHITE_RIVETED_HULL_SMALL);
        this.registerPanel(generator,ModBlocks.BLEACHED_PANEL,ModBlocks.BLEACHED_PLANKS);
        this.registerPanel(generator,ModBlocks.KHAKI_RIVETED_HULL_SMALL_PANEL, ModBlocks.KHAKI_RIVETED_HULL_SMALL);





        // Moquettes
        generator.registerWoolAndCarpet(ModBlocks.BLACK_MOQUETTE,ModBlocks.BLACK_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.CYAN_MOQUETTE,ModBlocks.CYAN_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.GRAY_MOQUETTE,ModBlocks.GRAY_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.GREEN_MOQUETTE,ModBlocks.GREEN_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.LIGHT_BLUE_MOQUETTE,ModBlocks.LIGHT_BLUE_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.LIGHT_GRAY_MOQUETTE,ModBlocks.LIGHT_GRAY_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.LIME_MOQUETTE,ModBlocks.LIME_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.MAGENTA_MOQUETTE,ModBlocks.MAGENTA_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.ORANGE_MOQUETTE,ModBlocks.ORANGE_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.PINK_MOQUETTE,ModBlocks.PINK_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.PURPLE_MOQUETTE,ModBlocks.PURPLE_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.WHITE_MOQUETTE,ModBlocks.WHITE_MOQUETTE_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.YELLOW_MOQUETTE,ModBlocks.YELLOW_MOQUETTE_CARPET);

        // Striped Carpets
        generator.registerWoolAndCarpet(ModBlocks.BLACK_STRIPED_CARPET_BLOCK,ModBlocks.BLACK_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.BLUE_STRIPED_CARPET_BLOCK,ModBlocks.BLUE_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.CYAN_STRIPED_CARPET_BLOCK,ModBlocks.CYAN_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.GRAY_STRIPED_CARPET_BLOCK,ModBlocks.GRAY_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.GREEN_STRIPED_CARPET_BLOCK,ModBlocks.GREEN_STRIPED_CARPET);
        // blockStateModelGenerator.registerWoolAndCarpet(ModBlocks.LIGHT_BLUE_STRIPED_CARPET_BLOCK,ModBlocks.LIGHT_BLUE_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.LIGHT_GRAY_STRIPED_CARPET_BLOCK,ModBlocks.LIGHT_GRAY_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.LIME_STRIPED_CARPET_BLOCK,ModBlocks.LIME_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.ORANGE_STRIPED_CARPET_BLOCK,ModBlocks.ORANGE_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.PURPLE_STRIPED_CARPET_BLOCK,ModBlocks.PURPLE_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.WHITE_STRIPED_CARPET_BLOCK,ModBlocks.WHITE_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.YELLOW_STRIPED_CARPET_BLOCK,ModBlocks.YELLOW_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.BROWN_STRIPED_CARPET_BLOCK,ModBlocks.BROWN_STRIPED_CARPET);
        generator.registerWoolAndCarpet(ModBlocks.RED_STRIPED_CARPET_BLOCK,ModBlocks.RED_STRIPED_CARPET);

        // Dark Marble
        generator.registerSimpleCubeAll(ModBlocks.DARK_MARBLE_TILE);
        generator.registerSimpleCubeAll(ModBlocks.CHECKERED_MARBLE_TILES);
        generator.registerSimpleCubeAll(ModBlocks.MIXED_MARBLE_TILES);
        generator.registerSimpleCubeAll(ModBlocks.DEEPWOKEN_TILE);

        // Plushies!
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.NORA_PLUSH);
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.PICKLE_PLUSH);
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.DAVIDANDROCKET_PLUSH);
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.VERID__PLUSH);
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.DUCKAMOLY_PLUSH);
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.WILLO_PLUSH);
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.MOKUS_PLUSH);
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.DOOGEY_PLUSH);
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.SQUID_PLUSH);
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.PENCIL_PLUSH);
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.INDIGO_PLUSH);

        generator.registerNorthDefaultHorizontalRotation(ModBlocks.SERVICE_BELL);

        generator.registerNorthDefaultHorizontalRotation(ModBlocks.WREATH);
        generator.registerNorthDefaultHorizontalRotation(ModBlocks.SNOWY_WREATH);

        generator.registerSimpleCubeAll(ModBlocks.PINK_CITRINE_BRICKS);
        generator.registerSimpleCubeAll(ModBlocks.POLISHED_PINK_CITRINE);
        generator.registerSimpleCubeAll(ModBlocks.SMOOTH_PINK_CITRINE);

        generator.registerSimpleCubeAll(ModBlocks.FORTERRA_BRICKS);
        generator.registerSimpleCubeAll(ModBlocks.POLISHED_FORTERRA);

        // Candy Cane
        generator.registerAxisRotated(ModBlocks.CANDY_CANE_BLOCK,TexturedModel.END_FOR_TOP_CUBE_COLUMN, TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
        generator.registerSimpleCubeAll(ModBlocks.ZIGZAG_CANDY_BLOCK);

        generator.registerSimpleCubeAll(ModBlocks.ARCADE_FLOOR);

        generator.registerRotatable(ModBlocks.ASPHALT);

        registerConnectiveBlock(generator,ModBlocks.BLEACHED_WALL_PANEL);
        registerConnectiveBlock(generator,ModBlocks.DARK_OAK_WALL_PANEL);
        registerConnectiveBlock(generator,ModBlocks.MAHOGANY_WALL_PANEL);
        registerConnectiveBlock(generator,ModBlocks.EBONY_WALL_PANEL);
        registerConnectiveBlock(generator,ModBlocks.VERAWOOD_WALL_PANEL);
        registerConnectiveBlock(generator,ModBlocks.BUBINGA_WALL_PANEL);

        BlockStateModelGenerator.BlockTexturePool PERFORATED_DARK_STEEL =
                generator.registerCubeAllModelTexturePool(ModBlocks.PERFORATED_DARK_STEEL);
        PERFORATED_DARK_STEEL.stairs(ModBlocks.PERFORATED_DARK_STEEL_STAIRS);
        PERFORATED_DARK_STEEL.slab(ModBlocks.PERFORATED_DARK_STEEL_SLAB);
        PERFORATED_DARK_STEEL.wall(ModBlocks.PERFORATED_DARK_STEEL_WALL);
        this.registerPanel(generator,ModBlocks.PERFORATED_DARK_STEEL_PANEL,ModBlocks.PERFORATED_DARK_STEEL);

        BlockStateModelGenerator.BlockTexturePool PERFORATED_STAINLESS_STEEL =
                generator.registerCubeAllModelTexturePool(ModBlocks.PERFORATED_STAINLESS_STEEL);
        PERFORATED_STAINLESS_STEEL.stairs(ModBlocks.PERFORATED_STAINLESS_STEEL_STAIRS);
        PERFORATED_STAINLESS_STEEL.slab(ModBlocks.PERFORATED_STAINLESS_STEEL_SLAB);
        PERFORATED_STAINLESS_STEEL.wall(ModBlocks.PERFORATED_STAINLESS_STEEL_WALL);
        this.registerPanel(generator,ModBlocks.PERFORATED_STAINLESS_STEEL_PANEL,ModBlocks.PERFORATED_STAINLESS_STEEL);

        BlockStateModelGenerator.BlockTexturePool CORRUGATED_STAINLESS_STEEL =
                generator.registerCubeAllModelTexturePool(ModBlocks.CORRUGATED_STAINLESS_STEEL);
        CORRUGATED_STAINLESS_STEEL.stairs(ModBlocks.CORRUGATED_STAINLESS_STEEL_STAIRS);
        CORRUGATED_STAINLESS_STEEL.slab(ModBlocks.CORRUGATED_STAINLESS_STEEL_SLAB);
        CORRUGATED_STAINLESS_STEEL.wall(ModBlocks.CORRUGATED_STAINLESS_STEEL_WALL);
        this.registerPanel(generator,ModBlocks.CORRUGATED_STAINLESS_STEEL_PANEL,ModBlocks.CORRUGATED_STAINLESS_STEEL);

        BlockStateModelGenerator.BlockTexturePool CORRUGATED_DARK_STEEL =
                generator.registerCubeAllModelTexturePool(ModBlocks.CORRUGATED_DARK_STEEL);
        CORRUGATED_DARK_STEEL.stairs(ModBlocks.CORRUGATED_DARK_STEEL_STAIRS);
        CORRUGATED_DARK_STEEL.slab(ModBlocks.CORRUGATED_DARK_STEEL_SLAB);
        CORRUGATED_DARK_STEEL.wall(ModBlocks.CORRUGATED_DARK_STEEL_WALL);
        this.registerPanel(generator,ModBlocks.CORRUGATED_DARK_STEEL_PANEL,ModBlocks.CORRUGATED_DARK_STEEL);

        generator.registerBuiltinWithParticle(ModBlocks.KILL_BLOCK, ModBlocks.KILL_BLOCK.asItem());
        generator.registerItemModel(ModBlocks.KILL_BLOCK.asItem());
        this.registerPanel(generator,ModBlocks.KILL_BLOCK_PANEL,ModBlocks.KILL_BLOCK_PANEL);

        this.registerOrnament(generator,ModBlocks.DARK_STEEL_ORNAMENT);
        this.registerOrnament(generator,ModBlocks.STAINLESS_STEEL_ORNAMENT);
        this.registerOrnament(generator,ModBlocks.BRONZE_ORNAMENT);
        this.registerOrnament(generator,ModBlocks.PLATINUM_ORNAMENT);

        this.registerOrnament(generator,ModBlocks.COPPER_ORNAMENT);
        this.registerOrnament(generator,ModBlocks.EXPOSED_COPPER_ORNAMENT);
        this.registerOrnament(generator,ModBlocks.OXIDIZED_COPPER_ORNAMENT);
        this.registerOrnament(generator,ModBlocks.WEATHERED_COPPER_ORNAMENT);


    }

    @Override
    public void generateItemModels(ItemModelGenerator itemGen) {
        itemGen.register(ModItems.MILK_GLASS, Models.GENERATED);
        itemGen.register(ModItems.CHOCCY_MILK_GLASS, Models.GENERATED);
        itemGen.register(ModItems.STRAWBERRY_MILK_GLASS, Models.GENERATED);
        itemGen.register(ModItems.BLUE_LOLLIPOP, Models.GENERATED);
        itemGen.register(ModItems.RED_LOLLIPOP, Models.GENERATED);
        itemGen.register(ModItems.GREEN_LOLLIPOP, Models.GENERATED);
        itemGen.register(ModItems.YELLOW_LOLLIPOP, Models.GENERATED);
        itemGen.register(ModItems.CANDY_CANE, Models.GENERATED);
        itemGen.register(ModItems.GINGERBREAD, Models.GENERATED);
        itemGen.register(ModItems.GINGERBREAD_CHOCO, Models.GENERATED);
        itemGen.register(ModItems.GINGERBREAD_DUCKAMOLY, Models.GENERATED);
        itemGen.register(ModItems.GINGERBREAD_NORA, Models.GENERATED);
        itemGen.register(ModItems.GINGERBREAD_RUDOLPH, Models.GENERATED);
        itemGen.register(ModItems.GINGERBREAD_SCARF, Models.GENERATED);
        itemGen.register(ModItems.BLENDED_BERRY_BLAST, Models.GENERATED);
        itemGen.register(ModItems.COTTON_SWIZZLE, Models.GENERATED);
        itemGen.register(ModItems.NO_BERRY_LEMON_SOUR, Models.GENERATED);
        itemGen.register(ModItems.SALTED_SEAFRUIT, Models.GENERATED);
        itemGen.register(ModItems.SAPPHIC_SUNSET, Models.GENERATED);
        itemGen.register(ModItems.THE_ALL_IN, Models.GENERATED);
        itemGen.register(ModItems.THE_CHROMATIC, Models.GENERATED);
        itemGen.register(ModItems.CAKESLICE, Models.GENERATED);
        itemGen.register(ModItems.MIXED_MOCKTAIL, Models.GENERATED);
        itemGen.register(ModItems.TEQUILA_SHOT, Models.GENERATED);
        itemGen.register(ModItems.WHISKEY_SOUR, Models.GENERATED);
        itemGen.register(ModItems.WATER_GLASS, Models.GENERATED);
        itemGen.register(ModItems.MOONSHINE, Models.GENERATED);
        itemGen.register(ModItems.BEER_PINT, Models.GENERATED);
        itemGen.register(ModItems.HOT_CHOCOLATE,Models.GENERATED);


    }


}
