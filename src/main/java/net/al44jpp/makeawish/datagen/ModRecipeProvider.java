package net.al44jpp.makeawish.datagen;

import net.al44jpp.makeawish.block.ModBlocks;
import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {


        List<ItemLike> NIGHT_CRYSTAL_SMELTABLES = List.of(ModBlocks.deepslate_night_crystal_ore.get(),
                ModBlocks.stone_night_crystal_ore.get());



        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.night_crystal_block.get())
                .pattern("CC")
                .pattern("CC")
                .define('C', ModItems.night_crystal.get())
                .unlockedBy("has crystal",has(ModItems.night_crystal.get()))
                .save(recipeOutput,"night_crystal_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.night_crystal.get(),4)
                .requires(ModBlocks.night_crystal_block.get()).unlockedBy("has crystal block",has(ModBlocks.night_crystal_block.get()))
                .save(recipeOutput,"makeawish:night_crystal_from_block");





        oreSmelting(recipeOutput,NIGHT_CRYSTAL_SMELTABLES,RecipeCategory.MISC,ModItems.night_crystal.get(),3.0f,200,"night_crystal");
        oreBlasting(recipeOutput,NIGHT_CRYSTAL_SMELTABLES,RecipeCategory.MISC,ModItems.night_crystal.get(),3.0f,50,"night_crystal");
    }
}
