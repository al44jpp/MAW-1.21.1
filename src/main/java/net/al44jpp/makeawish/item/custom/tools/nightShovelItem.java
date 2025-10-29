package net.al44jpp.makeawish.item.custom.tools;

import net.al44jpp.makeawish.worldgen.ModBiomes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class nightShovelItem extends ShovelItem {
    public static float scaleModifier = -0.5f;

    public static float getScaleModifier(){
        return scaleModifier;
    }

    public nightShovelItem(Tier p_43114_, Properties p_43117_) {
        super(p_43114_, p_43117_);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(!level.isClientSide){
            if ((level.isNight()||level.getBiome(entity.getOnPos()).is(ModBiomes.STARWOOD_FOREST))){
                scaleModifier = -0.5f;
            }else{
                scaleModifier = 0;
            }
        }
    }
}
