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

   // public static ResourceKey<BiomeModifier> ADD_TREE_STARWOOD = registerKey("add_tree_starwood");


    public static void bootstrap(BootstrapContext<BiomeModifier> context){
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

       // context.register(ADD_TREE_STARWOOD,new BiomeModifiers.AddFeaturesBiomeModifier(
              //  HolderSet.direct(biomes.getOrThrow(Biomes.PLAINS)),
                //HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.STARWOOD_PLACED_KEY)),
                //GenerationStep.Decoration.VEGETAL_DECORATION));
            }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID, name));
    }
}
