package net.al44jpp.makeawish.effect;

import net.al44jpp.makeawish.MAW;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
    public static DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, MAW.MOD_ID);

    public static Holder<MobEffect> REGAINING_MAGIC = MOB_EFFECTS.register("regaining_magic",
            ()-> new RegainingMagicEffect(MobEffectCategory.NEUTRAL,0xFFFFFF).withSoundOnAdded(SoundEvents.BEACON_DEACTIVATE));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
