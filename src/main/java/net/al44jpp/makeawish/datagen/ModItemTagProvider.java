package net.al44jpp.makeawish.datagen;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.block.ModBlocks;
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

        tag(ItemTags.LOGS)
                .add(ModBlocks.starwood_log.asItem());

        tag(ItemTags.PLANKS)
                .add(ModBlocks.starwood_plank.asItem());
        //  .add(ModItems.EXISTING_ITEM.get())
        //  .add(ModItems.EXISTING_ITEM.get())
        //              ...
    }
}
