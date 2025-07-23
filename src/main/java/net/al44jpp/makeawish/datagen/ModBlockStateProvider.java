package net.al44jpp.makeawish.datagen;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MAW.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        BlockWithItem(ModBlocks.deepslate_night_crystal_ore);
        BlockWithItem(ModBlocks.night_crystal_block);
        BlockWithItem(ModBlocks.stone_night_crystal_ore);
        //leaves made custom
        BlockWithItem(ModBlocks.starwood_plank);


        stairsBlock(ModBlocks.starwood_stairs.get(),blockTexture(ModBlocks.starwood_plank.get()));
        slabBlock(ModBlocks.starwood_slab.get(), blockTexture(ModBlocks.starwood_plank.get()), blockTexture(ModBlocks.starwood_plank.get()));

        buttonBlock(ModBlocks.starwood_button.get(),blockTexture(ModBlocks.starwood_plank.get()));
        pressurePlateBlock(ModBlocks.starwood_pressure_plate.get(),blockTexture(ModBlocks.starwood_plank.get()));



        BlockItem(ModBlocks.starwood_stairs);
        BlockItem(ModBlocks.starwood_slab);
        BlockItem(ModBlocks.starwood_button);
        BlockItem(ModBlocks.starwood_pressure_plate);
        BlockItem(ModBlocks.starwood_fence);
        BlockItem(ModBlocks.starwood_fence_gate);
        //BlockItem(ModBlocks.starwood_door);
        BlockItem(ModBlocks.starwood_trapdoor,"_bottom");

    }

    private void BlockWithItem(DeferredBlock<?> deferredBlock){
        simpleBlockWithItem(deferredBlock.get(),cubeAll(deferredBlock.get()));
    }
    private void BlockItem(DeferredBlock<?> deferredBlock){
        simpleBlockItem(deferredBlock.get(),new ModelFile.UncheckedModelFile("makeawish:block/" + deferredBlock.getId().getPath()));
    }
    private void BlockItem(DeferredBlock<?> deferredBlock, String appendix){
        simpleBlockItem(deferredBlock.get(),new ModelFile.UncheckedModelFile("makeawish:block/" + deferredBlock.getId().getPath() + appendix));
    }


}
