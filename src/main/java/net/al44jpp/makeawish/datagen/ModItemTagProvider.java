package net.al44jpp.makeawish.datagen;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.block.ModBlocks;
import net.al44jpp.makeawish.item.ModItems;
import net.al44jpp.makeawish.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MAW.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        //this is where to add items to tags:

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.starwood_log.asItem());

        tag(ModTags.Items.STARWOOD_LOGS)
                .add(ModBlocks.starwood_log.asItem());

        tag(ItemTags.PLANKS)
                .add(ModBlocks.starwood_plank.asItem());


            //for tools
        tag(ItemTags.SWORDS).add(ModItems.night_sword.asItem());

        tag(ItemTags.PICKAXES).add(ModItems.night_pickaxe.asItem());

        tag(ItemTags.AXES).add(ModItems.night_axe.asItem());

        tag(ItemTags.SHOVELS).add(ModItems.night_shovel.asItem());

        tag(ItemTags.HOES).add(ModItems.night_hoe.asItem());

    }
}
