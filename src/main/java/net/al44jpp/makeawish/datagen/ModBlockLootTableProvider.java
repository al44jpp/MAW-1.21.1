package net.al44jpp.makeawish.datagen;

import net.al44jpp.makeawish.block.ModBlocks;
import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        add(ModBlocks.deepslate_night_crystal_ore.get(),
                block -> createOreDrop(ModBlocks.deepslate_night_crystal_ore.get(), ModItems.night_crystal.get()));
        add(ModBlocks.stone_night_crystal_ore.get(),
                block -> createOreDrop(ModBlocks.stone_night_crystal_ore.get(), ModItems.night_crystal.get()));
        add(ModBlocks.night_crystal_block.get(),
                block ->createSingleItemTableWithSilkTouch(ModBlocks.night_crystal_block.get(),ModItems.night_crystal.get(), ConstantValue.exactly(4.0f)) );
        add(ModBlocks.starwood_plank.get(),
                block -> createSingleItemTable(ModBlocks.starwood_plank));
        dropSelf(ModBlocks.starwood_stairs.get());
        add(ModBlocks.starwood_slab.get(),
                block -> createSlabItemTable(ModBlocks.starwood_slab.get()));
        dropSelf(ModBlocks.starwood_button.get());
        dropSelf(ModBlocks.starwood_pressure_plate.get());
        dropSelf(ModBlocks.starwood_fence.get());
        dropSelf(ModBlocks.starwood_fence_gate.get());
        dropSelf(ModBlocks.starwood_trapdoor.get());

        dropSelf(ModBlocks.starwood_log.get());
        add(ModBlocks.starwood_door.get(),
                block ->createDoorTable(ModBlocks.starwood_door.get()));






    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
