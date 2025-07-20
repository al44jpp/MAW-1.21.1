package net.al44jpp.makeawish.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties wish_apple = new FoodProperties.Builder().nutrition(20).saturationModifier(2.0f).alwaysEdible().fast()
            .effect(()-> new MobEffectInstance(MobEffects.SATURATION,75*20,1,false,false),1.0f).build();


}
