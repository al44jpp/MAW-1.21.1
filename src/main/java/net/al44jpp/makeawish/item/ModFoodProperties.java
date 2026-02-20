package net.al44jpp.makeawish.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static FoodProperties wish_apple = new FoodProperties.Builder().nutrition(20).saturationModifier(2.0f).build();

    public static void setWish_apple(FoodProperties wish_apple) {
        ModFoodProperties.wish_apple = wish_apple;
    }
}
