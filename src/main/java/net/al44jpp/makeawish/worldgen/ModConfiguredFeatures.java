package net.al44jpp.makeawish.worldgen;

import io.netty.util.Constant;
import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.BendingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModConfiguredFeatures {









    public static final ResourceKey<ConfiguredFeature<?,?>> STARWOOD_KEY = registerKey("starwood");
    public static final ResourceKey<ConfiguredFeature<?,?>> NIGHT_CRYSTAL_OVERWORLD_ORE_KEY = registerKey("night_crystal_overworld_ore_key");








    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {

        register(context,STARWOOD_KEY,Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.starwood_log.get()),
                new StraightTrunkPlacer(4,0,4),

                BlockStateProvider.simple(ModBlocks.starwood_leaves.get()),
                new AcaciaFoliagePlacer(ConstantInt.of(3),ConstantInt.of(0)), //first is radius, second is bonus height

                new TwoLayersFeatureSize(1,0,0)).build());

        RuleTest STONE_REPLACEABLE = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest DEEPSLATE_REPLACEABLE = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreConfiguration.TargetBlockState> STARWOOD_ORE_OVERWORLD = List.of(
            OreConfiguration.target(STONE_REPLACEABLE, ModBlocks.stone_night_crystal_ore.get().defaultBlockState()),
            OreConfiguration.target(DEEPSLATE_REPLACEABLE, ModBlocks.deepslate_night_crystal_ore.get().defaultBlockState())
        );

        register(context,NIGHT_CRYSTAL_OVERWORLD_ORE_KEY,Feature.ORE,new OreConfiguration(STARWOOD_ORE_OVERWORLD,15,0.99f));




    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
