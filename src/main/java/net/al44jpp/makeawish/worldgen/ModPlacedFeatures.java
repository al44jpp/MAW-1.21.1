package net.al44jpp.makeawish.worldgen;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.HeightmapPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static ResourceKey<PlacedFeature> STARWOOD_PLACED_KEY = registerKey("starwood_placed");
    public static ResourceKey<PlacedFeature> NIGHT_CRYSTAL_ORE_PLACED = registerKey("night_crystal_ore_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context){
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context,STARWOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.STARWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(6,0.05f,2),
                        ModBlocks.starwood_sapling.get()));

        register(context,NIGHT_CRYSTAL_ORE_PLACED,configuredFeatures.getOrThrow(ModConfiguredFeatures.NIGHT_CRYSTAL_OVERWORLD_ORE_KEY),
                ModOrePlacement.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64),VerticalAnchor.absolute(80))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
