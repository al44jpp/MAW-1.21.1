package net.al44jpp.makeawish.worldgen.tree;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower STARWOOD= new TreeGrower(MAW.MOD_ID + ":starwood",
            Optional.empty(), Optional.of(ModConfiguredFeatures.STARWOOD_KEY),Optional.empty());
}
