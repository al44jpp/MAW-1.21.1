package net.al44jpp.makeawish.datagen;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.block.ModBlocks;
import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MAW.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.night_crystal.get());
        basicItem(ModItems.wish_apple.get());
        basicItem(ModItems.wish.get());
        basicItem(ModItems.wish_fragment.get());
        //fenceItem(ModBlocks.starwood_fence,ModBlocks.starwood_plank);
        buttonItem(ModBlocks.starwood_button,ModBlocks.starwood_plank);

        basicItem(ModBlocks.starwood_door.asItem());
    }







    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
