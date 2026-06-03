package net.mokus.wathextras.datagen;

import com.mojang.datafixers.util.Pair;

import io.wifi.starrailexpress.content.block.OrnamentBlock;
import io.wifi.starrailexpress.content.block.property.OrnamentShape;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.models.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.models.blockstates.*;
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

    private static ModelTemplate template(ResourceLocation parent, @Nullable String variant, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(parent), Optional.ofNullable(variant), requiredTextureKeys);
    }

    private static ModelTemplate template(ResourceLocation parent, TextureSlot... requiredTextureKeys) {
        return template(parent, null, requiredTextureKeys);
    }

    private static ModelTemplate template(String parentName, TextureSlot... requiredTextureKeys) {
        return template(ResourceLocation.fromNamespaceAndPath("wathe", parentName), requiredTextureKeys);
    }

    private Variant rotateForFace(Variant variant, Direction direction, boolean uvlock) {
        if (uvlock) {
            variant = variant.with(VariantProperties.UV_LOCK, true);
        }
        switch (direction) {
            case NORTH -> {}
            case EAST -> variant = variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
            case SOUTH -> variant = variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180);
            case WEST -> variant = variant.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270);
            case UP -> variant = variant.with(VariantProperties.X_ROT, VariantProperties.Rotation.R270);
            case DOWN -> variant = variant.with(VariantProperties.X_ROT, VariantProperties.Rotation.R90);
        }
        return variant;
    }
    private Variant variant() {
        return Variant.variant();
    }

    private <T> Variant variant(VariantProperty<T> variantSetting, T value) {
        return this.variant().with(variantSetting, value);
    }

    private Variant model(ResourceLocation model) {
        return this.variant(VariantProperties.MODEL, model);
    }

    private static final ModelTemplate ORNAMENT_R0 = template(
            "block/template_ornament_r0", TextureSlot.TEXTURE
    );
    private static final ModelTemplate ORNAMENT_R90 = template(
            "block/template_ornament_r90", TextureSlot.TEXTURE
    );
    private static final ModelTemplate ORNAMENT_R180 = template(
            "block/template_ornament_r180", TextureSlot.TEXTURE
    );
    private static final ModelTemplate ORNAMENT_R270 = template(
            "block/template_ornament_r270", TextureSlot.TEXTURE
    );

    private static final ModelTemplate PANEL = template(
            "block/template_panel", TextureSlot.ALL
    );



    private void registerPanel(BlockModelGenerators generator, Block block, Block textureBlock) {
        registerPanel(generator, block, TextureMapping.getBlockTexture(textureBlock));
    }

    private void registerPanel(BlockModelGenerators generator, Block block, ResourceLocation texture) {
        ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(block.asItem()), TextureMapping.layer0(texture), generator.modelOutput);
        ResourceLocation panelModel = PANEL.createWithSuffix(block, "", TextureMapping.cube(texture), generator.modelOutput);
        MultiPartGenerator blockStateSupplier = MultiPartGenerator.multiPart(block);
        Condition.TerminalCondition propertyCondition = Condition.condition();
        // Use manual connection variant mapping (equivalent to CONNECTION_VARIANT_FUNCTIONS)
        BooleanProperty[] connectionProperties = {
            BlockStateProperties.NORTH, BlockStateProperties.EAST, BlockStateProperties.SOUTH, BlockStateProperties.WEST,
            BlockStateProperties.UP, BlockStateProperties.DOWN
        };
        for (BooleanProperty prop : connectionProperties) {
            propertyCondition.term(prop, false);
        }
        Variant baseVariant = Variant.variant().with(VariantProperties.MODEL, panelModel);
        for (BooleanProperty prop : connectionProperties) {
            blockStateSupplier.with(Condition.condition().term(prop, true), baseVariant);
        }
        blockStateSupplier.with(propertyCondition, baseVariant);
        generator.blockStateOutput.accept(blockStateSupplier);
    }

    private void registerOrnament(BlockModelGenerators generator, Block block) {
        TextureMapping allTexture = new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block, "_all"));
        TextureMapping endTexture = new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block, "_end"));
        TextureMapping sideTexture = new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block, "_side"));
        TextureMapping cornerTexture = new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block, "_corner"));
        TextureMapping sidesTexture = new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block, "_sides"));
        TextureMapping centerTexture = new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block, "_center"));
        TextureMapping sidesCenterTexture = new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block, "_sides_center"));
        ORNAMENT_R0.createWithSuffix(block, "_all", allTexture, generator.modelOutput);
        ORNAMENT_R0.createWithSuffix(block, "_center", centerTexture, generator.modelOutput);
        ORNAMENT_R0.createWithSuffix(block, "_left_right_center", sidesCenterTexture, generator.modelOutput);
        ORNAMENT_R0.createWithSuffix(block, "_left", sideTexture, generator.modelOutput);
        ORNAMENT_R90.createWithSuffix(block, "_top", sideTexture, generator.modelOutput);
        ORNAMENT_R180.createWithSuffix(block, "_right", sideTexture, generator.modelOutput);
        ORNAMENT_R270.createWithSuffix(block, "_bottom", sideTexture, generator.modelOutput);
        ORNAMENT_R0.createWithSuffix(block, "_left_bottom", cornerTexture, generator.modelOutput);
        ORNAMENT_R90.createWithSuffix(block, "_left_top", cornerTexture, generator.modelOutput);
        ORNAMENT_R180.createWithSuffix(block, "_right_top", cornerTexture, generator.modelOutput);
        ORNAMENT_R270.createWithSuffix(block, "_right_bottom", cornerTexture, generator.modelOutput);
        ORNAMENT_R0.createWithSuffix(block, "_left_right", sidesTexture, generator.modelOutput);
        ORNAMENT_R90.createWithSuffix(block, "_top_bottom", sidesTexture, generator.modelOutput);
        ORNAMENT_R0.createWithSuffix(block, "_left_right_top", endTexture, generator.modelOutput);
        ORNAMENT_R90.createWithSuffix(block, "_right_top_bottom", endTexture, generator.modelOutput);
        ORNAMENT_R180.createWithSuffix(block, "_left_right_bottom", endTexture, generator.modelOutput);
        ORNAMENT_R270.createWithSuffix(block, "_left_top_bottom", endTexture, generator.modelOutput);
        generator.createSimpleFlatItemModel(block, ModelLocationUtils.getModelLocation(block, "_all").toString());
        PropertyDispatch propertyDispatch = PropertyDispatch.properties(OrnamentBlock.FACING, OrnamentBlock.SHAPE)
                .generate((facing, shape) ->
                this.rotateForFace(this.model(ModelLocationUtils.getModelLocation(block, "_" + shape.getSerializedName())), facing, false)
        );
        generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(propertyDispatch));
    }

    //Joinked code end

    private static ModelTemplate templateM(String parentName, TextureSlot... requiredTextureKeys) {
        return template(ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID,parentName), requiredTextureKeys);
    }

    private static final ModelTemplate BENCH_LEFT = templateM(
            "block/bench_left_template", TextureSlot.ALL
    );

    private static final ModelTemplate BENCH_CENTER = templateM(
            "block/bench_center_template", TextureSlot.ALL
    );

    private static final ModelTemplate BENCH_RIGHT = templateM(
            "block/bench_right_template", TextureSlot.ALL
    );

    private static final ModelTemplate BENCH_ITEM = templateM(
            "block/bench_item_template", TextureSlot.ALL
    );

    private static final ModelTemplate DOUBLE_HULL_BLOCK= templateM(
            "block/double_hull_block_template", TextureSlot.ALL
    );

    private void registerBenchBlock(BlockModelGenerators generator, Block block) {
        TextureMapping textureMap = TextureMapping.cube(block);
        ResourceLocation leftModel = BENCH_LEFT.createWithSuffix(block, "_left", textureMap, generator.modelOutput);
        ResourceLocation centerModel = BENCH_CENTER.createWithSuffix(block, "_center", textureMap, generator.modelOutput);
        ResourceLocation itemModel = BENCH_ITEM.createWithSuffix(block, "_item", textureMap, generator.modelOutput);
        ResourceLocation rightModel = BENCH_RIGHT.createWithSuffix(block, "_right", textureMap, generator.modelOutput);

        generator.createSimpleFlatItemModel(block, itemModel.toString());

        generator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(block)
                        .with(
                                PropertyDispatch.property(BenchBlock.PART)
                                        .generate(part -> switch (part) {
                                            case LEFT -> Variant.variant().with(VariantProperties.MODEL, leftModel);
                                            case CENTER -> Variant.variant().with(VariantProperties.MODEL, centerModel);
                                            case RIGHT -> Variant.variant().with(VariantProperties.MODEL, rightModel);
                                        })
                        )
                        .with(PropertyDispatch.property(BlockStateProperties.HORIZONTAL_FACING)
                                .generate(facing -> Variant.variant().with(VariantProperties.Y_ROT, facing.get2DDataValue() == 2 ? VariantProperties.Rotation.R0 : facing.get2DDataValue() == 3 ? VariantProperties.Rotation.R180 : facing.get2DDataValue() == 4 ? VariantProperties.Rotation.R270 : VariantProperties.Rotation.R90)))
        );
    }

    private void registerDoubleHullBlock(BlockModelGenerators generator, Block block) {
        TextureMapping leftTextureMap = TextureMapping.cube(TextureMapping.getBlockTexture(block, "_left"));
        TextureMapping rightTextureMap = TextureMapping.cube(TextureMapping.getBlockTexture(block, "_right"));

        ResourceLocation leftModel = DOUBLE_HULL_BLOCK.createWithSuffix(block, "_left", leftTextureMap, generator.modelOutput);
        ResourceLocation rightModel = DOUBLE_HULL_BLOCK.createWithSuffix(block, "_right", rightTextureMap, generator.modelOutput);

        generator.createSimpleFlatItemModel(block, leftModel.toString());

        generator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(block)
                        .with(
                                PropertyDispatch.property(DoubleHullBlock.PART)
                                        .generate(part -> switch (part) {
                                            case LEFT -> Variant.variant().with(VariantProperties.MODEL, leftModel);
                                            case RIGHT -> Variant.variant().with(VariantProperties.MODEL, rightModel);
                                        })
                        )
                        .with(PropertyDispatch.property(BlockStateProperties.HORIZONTAL_FACING)
                                .generate(facing -> Variant.variant().with(VariantProperties.Y_ROT, facing.get2DDataValue() == 2 ? VariantProperties.Rotation.R0 : facing.get2DDataValue() == 3 ? VariantProperties.Rotation.R180 : facing.get2DDataValue() == 4 ? VariantProperties.Rotation.R270 : VariantProperties.Rotation.R90)))
        );
    }


    public final void registerCandelabra(BlockModelGenerators generator,Block candelabre, Block wallCandelabre) {
        ResourceLocation unlitModel = ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID, "block/candelabre");
        ResourceLocation litModel = ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID, "block/candelabre_lit");

        generator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(candelabre)
                        .with(PropertyDispatch.property(BlockStateProperties.LIT)
                                .generate(lit -> lit ? Variant.variant().with(VariantProperties.MODEL, litModel) : Variant.variant().with(VariantProperties.MODEL, unlitModel)))
        );

        ResourceLocation wallUnlitModel = ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID, "block/wall_candelabre");
        ResourceLocation wallLitModel = ResourceLocation.fromNamespaceAndPath(WathExtras.MOD_ID, "block/wall_candelabre_lit");

        generator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(wallCandelabre)
                        .with(PropertyDispatch.property(BlockStateProperties.LIT)
                                .generate(lit -> lit ? Variant.variant().with(VariantProperties.MODEL, wallLitModel) : Variant.variant().with(VariantProperties.MODEL, wallUnlitModel)))
                        .with(PropertyDispatch.property(BlockStateProperties.HORIZONTAL_FACING)
                                .generate(facing -> Variant.variant().with(VariantProperties.Y_ROT, VariantProperties.Rotation.values()[facing.get2DDataValue()])))
        );

        generator.skipAutoItemBlock(candelabre);
        generator.skipAutoItemBlock(wallCandelabre);
    }

    private void registerConnectiveBlock(BlockModelGenerators generator, Block block) {
        TextureMapping singleTexture = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side_single"))
                .put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top"));

        TextureMapping topTexture = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side_top"))
                .put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top"));

        TextureMapping middleTexture = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side_middle"))
                .put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top"));

        TextureMapping bottomTexture = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side_bottom"))
                .put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top"));

        TextureMapping boxTexture = new TextureMapping()
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.END, TextureMapping.getBlockTexture(block, "_top"));

        ResourceLocation singleModel = ModelTemplates.CUBE_COLUMN.createWithSuffix(block, "_single", singleTexture, generator.modelOutput);
        ResourceLocation topModel = ModelTemplates.CUBE_COLUMN.createWithSuffix(block, "_top", topTexture, generator.modelOutput);
        ResourceLocation middleModel = ModelTemplates.CUBE_COLUMN.createWithSuffix(block, "_middle", middleTexture, generator.modelOutput);
        ResourceLocation bottomModel = ModelTemplates.CUBE_COLUMN.createWithSuffix(block, "_bottom", bottomTexture, generator.modelOutput);
        ResourceLocation boxModel = ModelTemplates.CUBE_COLUMN.createWithSuffix(block, "_box", boxTexture, generator.modelOutput);

        generator.createSimpleFlatItemModel(block, singleModel.toString());

        generator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(block)
                        .with(PropertyDispatch.property(WallPanelBlock.PART)
                                .generate(part -> switch (part) {
                                    case SINGLE -> Variant.variant().with(VariantProperties.MODEL, singleModel);
                                    case TOP -> Variant.variant().with(VariantProperties.MODEL, topModel);
                                    case MIDDLE -> Variant.variant().with(VariantProperties.MODEL, middleModel);
                                    case BOTTOM -> Variant.variant().with(VariantProperties.MODEL, bottomModel);
                                    case BOX -> Variant.variant().with(VariantProperties.MODEL, boxModel);
                                }))
        );
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {

        registerCandelabra(generator,ModBlocks.CANDELABRE,ModBlocks.WALL_CANDELABRE);

        registerBenchBlock(generator,ModBlocks.PALE_BENCH);
        registerBenchBlock(generator,ModBlocks.QUEEN_BENCH);
        registerBenchBlock(generator,ModBlocks.STEEL_BENCH);
        registerBenchBlock(generator,ModBlocks.THORN_BENCH);

        //Small Hulls
        BlockModelGenerators.BlockFamilyProvider KHAKI_RIVETED_HULL_SMALL =
                generator.family(ModBlocks.KHAKI_RIVETED_HULL_SMALL);
        KHAKI_RIVETED_HULL_SMALL.stairs(ModBlocks.KHAKI_RIVETED_HULL_SMALL_STAIRS);
        KHAKI_RIVETED_HULL_SMALL.slab(ModBlocks.KHAKI_RIVETED_HULL_SMALL_SLAB);
        KHAKI_RIVETED_HULL_SMALL.wall(ModBlocks.KHAKI_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.KHAKI_RIVETED_HULL);

        BlockModelGenerators.BlockFamilyProvider ANTHRACITE_RIVETED_HULL_SMALL =
                generator.family(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL);
        ANTHRACITE_RIVETED_HULL_SMALL.stairs(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_STAIRS);
        ANTHRACITE_RIVETED_HULL_SMALL.slab(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_SLAB);
        ANTHRACITE_RIVETED_HULL_SMALL.wall(ModBlocks.ANTHRACITE_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.ANTHRACITE_RIVETED_HULL);


        BlockModelGenerators.BlockFamilyProvider BLACK_RIVETED_HULL_SMALL =
                generator.family(ModBlocks.BLACK_RIVETED_HULL_SMALL);
        BLACK_RIVETED_HULL_SMALL.stairs(ModBlocks.BLACK_RIVETED_HULL_SMALL_STAIRS);
        BLACK_RIVETED_HULL_SMALL.slab(ModBlocks.BLACK_RIVETED_HULL_SMALL_SLAB);
        BLACK_RIVETED_HULL_SMALL.wall(ModBlocks.BLACK_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.BLACK_RIVETED_HULL);


        BlockModelGenerators.BlockFamilyProvider MAROON_RIVETED_HULL_SMALL =
                generator.family(ModBlocks.MAROON_RIVETED_HULL_SMALL);
        MAROON_RIVETED_HULL_SMALL.stairs(ModBlocks.MAROON_RIVETED_HULL_SMALL_STAIRS);
        MAROON_RIVETED_HULL_SMALL.slab(ModBlocks.MAROON_RIVETED_HULL_SMALL_SLAB);
        MAROON_RIVETED_HULL_SMALL.wall(ModBlocks.MAROON_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.MAROON_RIVETED_HULL);


        BlockModelGenerators.BlockFamilyProvider MUNTZ_RIVETED_HULL_SMALL =
                generator.family(ModBlocks.MUNTZ_RIVETED_HULL_SMALL);
        MUNTZ_RIVETED_HULL_SMALL.stairs(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_STAIRS);
        MUNTZ_RIVETED_HULL_SMALL.slab(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_SLAB);
        MUNTZ_RIVETED_HULL_SMALL.wall(ModBlocks.MUNTZ_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.MUNTZ_RIVETED_HULL);


        BlockModelGenerators.BlockFamilyProvider NAVY_RIVETED_HULL_SMALL =
                generator.family(ModBlocks.NAVY_RIVETED_HULL_SMALL);
        NAVY_RIVETED_HULL_SMALL.stairs(ModBlocks.NAVY_RIVETED_HULL_SMALL_STAIRS);
        NAVY_RIVETED_HULL_SMALL.slab(ModBlocks.NAVY_RIVETED_HULL_SMALL_SLAB);
        NAVY_RIVETED_HULL_SMALL.wall(ModBlocks.NAVY_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.NAVY_RIVETED_HULL);


        BlockModelGenerators.BlockFamilyProvider WHITE_RIVETED_HULL_SMALL =
                generator.family(ModBlocks.WHITE_RIVETED_HULL_SMALL);
        WHITE_RIVETED_HULL_SMALL.stairs(ModBlocks.WHITE_RIVETED_HULL_SMALL_STAIRS);
        WHITE_RIVETED_HULL_SMALL.slab(ModBlocks.WHITE_RIVETED_HULL_SMALL_SLAB);
        WHITE_RIVETED_HULL_SMALL.wall(ModBlocks.WHITE_RIVETED_HULL_SMALL_WALL);
        registerDoubleHullBlock(generator,ModBlocks.WHITE_RIVETED_HULL);


        BlockModelGenerators.BlockFamilyProvider BLEACHED =
                generator.family(ModBlocks.BLEACHED_PLANKS);
        BLEACHED.stairs(ModBlocks.BLEACHED_STAIRS);
        BLEACHED.slab(ModBlocks.BLEACHED_SLAB);
        BLEACHED.wall(ModBlocks.BLEACHED_WALL);
        BLEACHED.fence(ModBlocks.BLEACHED_FENCE);

        BlockModelGenerators.BlockFamilyProvider VERAWOOD =
                generator.family(ModBlocks.VERAWOOD_PLANKS);
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





        // Moquettes - use family approach
        for (Block wool : new Block[]{
            ModBlocks.BLACK_MOQUETTE, ModBlocks.CYAN_MOQUETTE, ModBlocks.GRAY_MOQUETTE,
            ModBlocks.GREEN_MOQUETTE, ModBlocks.LIGHT_BLUE_MOQUETTE, ModBlocks.LIGHT_GRAY_MOQUETTE,
            ModBlocks.LIME_MOQUETTE, ModBlocks.MAGENTA_MOQUETTE, ModBlocks.ORANGE_MOQUETTE,
            ModBlocks.PINK_MOQUETTE, ModBlocks.PURPLE_MOQUETTE, ModBlocks.WHITE_MOQUETTE,
            ModBlocks.YELLOW_MOQUETTE
        }) {
            generator.createTrivialCube(wool);
        }

        // Striped Carpets
        for (Block carpetBlock : new Block[]{
            ModBlocks.BLACK_STRIPED_CARPET_BLOCK, ModBlocks.BLUE_STRIPED_CARPET_BLOCK,
            ModBlocks.CYAN_STRIPED_CARPET_BLOCK, ModBlocks.GRAY_STRIPED_CARPET_BLOCK,
            ModBlocks.GREEN_STRIPED_CARPET_BLOCK, ModBlocks.LIGHT_GRAY_STRIPED_CARPET_BLOCK,
            ModBlocks.LIME_STRIPED_CARPET_BLOCK, ModBlocks.ORANGE_STRIPED_CARPET_BLOCK,
            ModBlocks.PURPLE_STRIPED_CARPET_BLOCK, ModBlocks.WHITE_STRIPED_CARPET_BLOCK,
            ModBlocks.YELLOW_STRIPED_CARPET_BLOCK, ModBlocks.BROWN_STRIPED_CARPET_BLOCK,
            ModBlocks.RED_STRIPED_CARPET_BLOCK
        }) {
            generator.createTrivialCube(carpetBlock);
        }

        // Dark Marble
        generator.createTrivialCube(ModBlocks.DARK_MARBLE_TILE);
        generator.createTrivialCube(ModBlocks.CHECKERED_MARBLE_TILES);
        generator.createTrivialCube(ModBlocks.MIXED_MARBLE_TILES);
        generator.createTrivialCube(ModBlocks.DEEPWOKEN_TILE);

        // Plushies - simple horizontal facing blocks
        Block[] plushies = {
            ModBlocks.NORA_PLUSH, ModBlocks.PICKLE_PLUSH, ModBlocks.DAVIDANDROCKET_PLUSH,
            ModBlocks.VERID__PLUSH, ModBlocks.DUCKAMOLY_PLUSH, ModBlocks.WILLO_PLUSH,
            ModBlocks.MOKUS_PLUSH, ModBlocks.DOOGEY_PLUSH, ModBlocks.SQUID_PLUSH,
            ModBlocks.PENCIL_PLUSH, ModBlocks.INDIGO_PLUSH,
            ModBlocks.SERVICE_BELL, ModBlocks.WREATH, ModBlocks.SNOWY_WREATH
        };
        for (Block plush : plushies) {
            ResourceLocation model = TexturedModel.CUBE.create(plush, generator.modelOutput);
            generator.blockStateOutput.accept(
                MultiVariantGenerator.multiVariant(plush)
                    .with(PropertyDispatch.property(BlockStateProperties.HORIZONTAL_FACING)
                        .generate(facing -> Variant.variant().with(VariantProperties.MODEL, model)
                            .with(VariantProperties.Y_ROT, switch(facing) {
                                case EAST -> VariantProperties.Rotation.R90;
                                case SOUTH -> VariantProperties.Rotation.R180;
                                case WEST -> VariantProperties.Rotation.R270;
                                default -> VariantProperties.Rotation.R0;
                            })))
            );
            generator.createSimpleFlatItemModel(plush, model.toString());
        }

        generator.createTrivialCube(ModBlocks.PINK_CITRINE_BRICKS);
        generator.createTrivialCube(ModBlocks.POLISHED_PINK_CITRINE);
        generator.createTrivialCube(ModBlocks.SMOOTH_PINK_CITRINE);

        generator.createTrivialCube(ModBlocks.FORTERRA_BRICKS);
        generator.createTrivialCube(ModBlocks.POLISHED_FORTERRA);

        // Candy Cane - use CUBE_COLUMN directly
        ResourceLocation candyModel = ModelTemplates.CUBE_COLUMN.createWithSuffix(ModBlocks.CANDY_CANE_BLOCK, "", TextureMapping.cube(ModBlocks.CANDY_CANE_BLOCK), generator.modelOutput);
        generator.blockStateOutput.accept(
            MultiVariantGenerator.multiVariant(ModBlocks.CANDY_CANE_BLOCK)
                .with(PropertyDispatch.property(BlockStateProperties.AXIS)
                    .generate(axis -> {
                        var variant = Variant.variant().with(VariantProperties.MODEL, candyModel);
                        return switch(axis) {
                            case X -> variant.with(VariantProperties.X_ROT, VariantProperties.Rotation.R90).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90);
                            case Z -> variant.with(VariantProperties.X_ROT, VariantProperties.Rotation.R90);
                            default -> variant;
                        };
                    }))
        );
        generator.createTrivialCube(ModBlocks.ZIGZAG_CANDY_BLOCK);

        generator.createTrivialCube(ModBlocks.ARCADE_FLOOR);

        generator.createTrivialCube(ModBlocks.ASPHALT);

        registerConnectiveBlock(generator,ModBlocks.BLEACHED_WALL_PANEL);
        registerConnectiveBlock(generator,ModBlocks.DARK_OAK_WALL_PANEL);
        registerConnectiveBlock(generator,ModBlocks.MAHOGANY_WALL_PANEL);
        registerConnectiveBlock(generator,ModBlocks.EBONY_WALL_PANEL);
        registerConnectiveBlock(generator,ModBlocks.VERAWOOD_WALL_PANEL);
        registerConnectiveBlock(generator,ModBlocks.BUBINGA_WALL_PANEL);

        BlockModelGenerators.BlockFamilyProvider PERFORATED_DARK_STEEL =
                generator.family(ModBlocks.PERFORATED_DARK_STEEL);
        PERFORATED_DARK_STEEL.stairs(ModBlocks.PERFORATED_DARK_STEEL_STAIRS);
        PERFORATED_DARK_STEEL.slab(ModBlocks.PERFORATED_DARK_STEEL_SLAB);
        PERFORATED_DARK_STEEL.wall(ModBlocks.PERFORATED_DARK_STEEL_WALL);
        this.registerPanel(generator,ModBlocks.PERFORATED_DARK_STEEL_PANEL,ModBlocks.PERFORATED_DARK_STEEL);

        BlockModelGenerators.BlockFamilyProvider PERFORATED_STAINLESS_STEEL =
                generator.family(ModBlocks.PERFORATED_STAINLESS_STEEL);
        PERFORATED_STAINLESS_STEEL.stairs(ModBlocks.PERFORATED_STAINLESS_STEEL_STAIRS);
        PERFORATED_STAINLESS_STEEL.slab(ModBlocks.PERFORATED_STAINLESS_STEEL_SLAB);
        PERFORATED_STAINLESS_STEEL.wall(ModBlocks.PERFORATED_STAINLESS_STEEL_WALL);
        this.registerPanel(generator,ModBlocks.PERFORATED_STAINLESS_STEEL_PANEL,ModBlocks.PERFORATED_STAINLESS_STEEL);

        BlockModelGenerators.BlockFamilyProvider CORRUGATED_STAINLESS_STEEL =
                generator.family(ModBlocks.CORRUGATED_STAINLESS_STEEL);
        CORRUGATED_STAINLESS_STEEL.stairs(ModBlocks.CORRUGATED_STAINLESS_STEEL_STAIRS);
        CORRUGATED_STAINLESS_STEEL.slab(ModBlocks.CORRUGATED_STAINLESS_STEEL_SLAB);
        CORRUGATED_STAINLESS_STEEL.wall(ModBlocks.CORRUGATED_STAINLESS_STEEL_WALL);
        this.registerPanel(generator,ModBlocks.CORRUGATED_STAINLESS_STEEL_PANEL,ModBlocks.CORRUGATED_STAINLESS_STEEL);

        BlockModelGenerators.BlockFamilyProvider CORRUGATED_DARK_STEEL =
                generator.family(ModBlocks.CORRUGATED_DARK_STEEL);
        CORRUGATED_DARK_STEEL.stairs(ModBlocks.CORRUGATED_DARK_STEEL_STAIRS);
        CORRUGATED_DARK_STEEL.slab(ModBlocks.CORRUGATED_DARK_STEEL_SLAB);
        CORRUGATED_DARK_STEEL.wall(ModBlocks.CORRUGATED_DARK_STEEL_WALL);
        this.registerPanel(generator,ModBlocks.CORRUGATED_DARK_STEEL_PANEL,ModBlocks.CORRUGATED_DARK_STEEL);

        generator.createTrivialCube(ModBlocks.KILL_BLOCK);
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
    public void generateItemModels(ItemModelGenerators itemGen) {
        itemGen.generateFlatItem(ModItems.MILK_GLASS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.CHOCCY_MILK_GLASS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.STRAWBERRY_MILK_GLASS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.BLUE_LOLLIPOP, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.RED_LOLLIPOP, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.GREEN_LOLLIPOP, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.YELLOW_LOLLIPOP, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.CANDY_CANE, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.GINGERBREAD, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.GINGERBREAD_CHOCO, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.GINGERBREAD_DUCKAMOLY, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.GINGERBREAD_NORA, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.GINGERBREAD_RUDOLPH, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.GINGERBREAD_SCARF, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.BLENDED_BERRY_BLAST, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.COTTON_SWIZZLE, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.NO_BERRY_LEMON_SOUR, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.SALTED_SEAFRUIT, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.SAPPHIC_SUNSET, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.THE_ALL_IN, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.THE_CHROMATIC, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.CAKESLICE, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.MIXED_MOCKTAIL, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.TEQUILA_SHOT, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.WHISKEY_SOUR, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.WATER_GLASS, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.MOONSHINE, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.BEER_PINT, ModelTemplates.FLAT_ITEM);
        itemGen.generateFlatItem(ModItems.HOT_CHOCOLATE,ModelTemplates.FLAT_ITEM);


    }


}
