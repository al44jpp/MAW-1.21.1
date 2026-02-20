package net.al44jpp.makeawish.event;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Tuple;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

import java.util.*;

import static net.al44jpp.makeawish.item.custom.armor.WhishArmorItem.FullArmorEquiped;

@EventBusSubscriber(modid = MAW.MOD_ID)
public class EventHandler {
    private static final Set<ResourceKey<DamageType>> DAMAGE_TYPES_SET = new HashSet<>();


    @SubscribeEvent
    public static void LivingDamage(LivingDamageEvent.Pre event){
        DAMAGE_TYPES_SET.add(DamageTypes.GENERIC_KILL);
        DAMAGE_TYPES_SET.add(DamageTypes.FELL_OUT_OF_WORLD);
        if(event.getEntity() instanceof  Player player && FullArmorEquiped(player) && !player.hasEffect(ModEffects.REGAINING_MAGIC)){
            if(!DAMAGE_TYPES_SET.contains(event.getSource().typeHolder().getKey())){
               if(event.getNewDamage()>=player.getHealth() && player.level() instanceof ServerLevel serverLevel){
                   event.setNewDamage(0f);
                   serverLevel.playSound(null,player.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS);
                   serverLevel.sendParticles(ParticleTypes.FIREWORK,player.getX(),player.getY(),player.getZ(),5000,1,1,1,1);
                   serverLevel.sendParticles(ParticleTypes.WARPED_SPORE,player.getX(),player.getY(),player.getZ(),500,1,1,1,1);
                   player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,300,2));
                   player.addEffect(new MobEffectInstance(ModEffects.REGAINING_MAGIC,2400));
               }
            }
        }
    }
}
