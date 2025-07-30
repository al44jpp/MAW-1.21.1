package net.al44jpp.makeawish.datagen;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MAW.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.night_crystal_block.get())
                .add(ModBlocks.stone_night_crystal_ore.get())
                .add(ModBlocks.deepslate_night_crystal_ore.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.starwood_plank.get())
                .add(ModBlocks.starwood_log.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.starwood_plank.get());

        tag(BlockTags.LOGS)
                .add(ModBlocks.starwood_log.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.night_crystal_block.get())
                .add(ModBlocks.stone_night_crystal_ore.get())
                .add(ModBlocks.deepslate_night_crystal_ore.get());

        tag(BlockTags.FENCES)
                .add(ModBlocks.starwood_fence.get());
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.starwood_fence_gate.get());

    }
}
