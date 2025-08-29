package net.al44jpp.makeawish.util;

import net.al44jpp.makeawish.MAW;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Items{

        public static final TagKey<Item> STARWOOD_LOGS = createTag("starwood_logs");

        private static TagKey<Item> createTag (String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID,name));
        }
    }
    public static class Blocks{

        public static final TagKey<Block> STARWOOD_LOGS = createTag("starwood_logs");

        private static TagKey<Block> createTag (String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID,name));
        }
    }

    public static class Biomes{

        public static final TagKey<Biome> OBSERVATORY_BIOMES = createTag("observatory_biomes");

        private static TagKey<Biome> createTag (String name){
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID,name));
        }
    }


}
