package net.al44jpp.makeawish.item.custom;

import net.al44jpp.makeawish.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {
    public static final Tier NIGHT_CRYSTAL = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_NIGHT_CRYSTAL_TOOL,4096,13.0f,5.0f,30,() -> Ingredient.EMPTY);
}
