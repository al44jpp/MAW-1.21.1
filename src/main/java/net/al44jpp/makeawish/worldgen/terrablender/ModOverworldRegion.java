package net.al44jpp.makeawish.worldgen.terrablender;

import com.mojang.datafixers.util.Pair;
import net.al44jpp.makeawish.worldgen.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRegion extends Region
{
    public ModOverworldRegion(ResourceLocation name, int weight)
    {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // Insert Starwood Forest into forest-like climate, with low weight (weight is set in the Region ctor)
        this.addBiome(mapper,
                Climate.parameters(
                        Climate.Parameter.point(0.25F), // temperature
                        Climate.Parameter.point(0.8F),  // humidity
                        Climate.Parameter.point(0.0F),  // continentalness (near inland)
                        Climate.Parameter.point(0.0F),  // erosion
                        Climate.Parameter.point(0.0F),  // depth
                        Climate.Parameter.point(0.0F),  // weirdness
                        0.0F // offset
                ),
                ModBiomes.STARWOOD_FOREST
        );
    }
}