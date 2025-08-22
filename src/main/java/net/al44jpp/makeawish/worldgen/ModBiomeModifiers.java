package net.al44jpp.makeawish.worldgen;

import net.al44jpp.makeawish.MAW;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {

   // public static ResourceKey<BiomeModifier> ADD_TREE_STARWOOD = registerKey("add_tree_starwood");  NOT USEFUL BUT KEPT AS AN EXAMPLE
    public static ResourceKey<BiomeModifier> ADD_NIGHT_CRYSTAL_ORE = registerKey("add_night_crystal_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context){
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

       // context.register(ADD_TREE_STARWOOD,new BiomeModifiers.AddFeaturesBiomeModifier(
              //  HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS)),
                //HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.STARWOOD_PLACED_KEY)),
                //GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_NIGHT_CRYSTAL_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NIGHT_CRYSTAL_ORE_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID, name));
    }
}
