package net.al44jpp.makeawish.worldgen;

import net.al44jpp.makeawish.MAW;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModBiomes {

    public static final ResourceKey<Biome> STARWOOD_FOREST = createKey("starwood_forest");

    private static ResourceKey<Biome> createKey(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID, name);
        return ResourceKey.create(Registries.BIOME, id);
    }

    /**
     * Datagen/bootstrap method for the biome.
     * - Adds default forest vegetation via BiomeDefaultFeatures
     * - Adds your starwood placed feature in VEGETAL_DECORATION
     * - Adds the mob spawns matching vanilla birch forest
     */
    public static void bootstrap(BootstrapContext<Biome> ctx) {
        // required lookups for the BiomeGenerationSettings.Builder constructor (1.21.1)
        HolderGetter<PlacedFeature> placedLookup = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> configuredCarverLookup = ctx.lookup(Registries.CONFIGURED_CARVER);

        // creation of generation settings builder (pass the lookups)
        BiomeGenerationSettings.Builder generation = new BiomeGenerationSettings.Builder(placedLookup, configuredCarverLookup);

        // --- vanilla-like features for a forest / birch forest ---
        // Add the default carvers and lakes (caves, lakes)
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generation); ;;;;

        // Add the forest grass patches (tall grass / grass)
        BiomeDefaultFeatures.addForestGrass(generation); ;;;;

        // Add forest flowers (roses, lilac, peony, lilies of the valley where applicable)
       //                                                   BiomeDefaultFeatures.addForestFlowers(generation);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_FOREST_FLOWERS);
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_PLAINS);

        // Add mushrooms (small mushrooms) and other small vegetation extras
        BiomeDefaultFeatures.addDefaultMushrooms(generation); ;;;;
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation); ;;;;

        // Add standard underground features & ores / springs, etc.
        //                                                  BiomeDefaultFeatures.addDefaultUndergroundVariety(generation);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_ANDESITE_LOWER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_ANDESITE_UPPER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_DIORITE_LOWER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_DIORITE_UPPER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_GRANITE_LOWER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_GRANITE_UPPER);
        generation.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_GRAVEL);

        //generation.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.NIGHT_CRYSTAL_ORE_PLACED);

        BiomeDefaultFeatures.addDefaultOres(generation); ;;;;
        BiomeDefaultFeatures.addDefaultSprings(generation); ;;;;

        // If you want to specifically include seagrass/water plants near water edges:
        BiomeDefaultFeatures.addDefaultSeagrass(generation); ;;;;

        // --- your custom tree feature already referenced earlier ---
        // ensure your ModPlacedFeatures.STARWOOD_PLACED_KEY is a ResourceKey<PlacedFeature>
        generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.STARWOOD_PLACED_KEY); ;;;;

        // --- mob spawns (copied from vanilla birch forest spawn table) ---
        MobSpawnSettings.Builder mobs = new MobSpawnSettings.Builder();

        // Monster category (hostiles)
        mobs.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 100, 4, 4));
        mobs.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 100, 4, 4));
        // slime attempts only succeed in slime chunks — weight kept same as vanilla
        mobs.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
        mobs.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4));
        mobs.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 95, 4, 4));
        mobs.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 4));
        mobs.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1));
        mobs.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));

        // Ambient category
        mobs.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 1, 8, 8));

        // Creature category (passive mobs)
        mobs.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 12, 4, 4));
        mobs.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.CHICKEN, 10, 4, 4));
        mobs.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.PIG, 10, 4, 4));
        mobs.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.COW, 8, 4, 4));

        // Underground water creature (glow squid)
        mobs.addSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GLOW_SQUID, 1, 4, 6));

        // Build the Effect / Biome and register
        BiomeSpecialEffects effects = new BiomeSpecialEffects.Builder()
                .fogColor(0x000005)
                .waterColor(4159204)
                .waterFogColor(0x3f3fc1)
                .skyColor(0x000005)
                .foliageColorOverride(0x3F3F74)
                .grassColorOverride(0x3F3F74)
                .build();

        Biome biome = new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.6f)
                .downfall(0.6f)
                .specialEffects(effects)
                .mobSpawnSettings(mobs.build())
                .generationSettings(generation.build())
                .build();

        ctx.register(STARWOOD_FOREST, biome);
    }
}