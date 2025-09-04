package net.al44jpp.makeawish.item.custom;

import com.mojang.datafixers.kinds.App;
import net.al44jpp.makeawish.worldgen.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.commands.ScheduleCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class nightSwordItem extends SwordItem {
    public nightSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public float getAttackDamageBonus(Entity target, float damage, DamageSource damageSource) {
        if (!target.level().isNight() && !target.level().getBiome(target.blockPosition()).is(ModBiomes.STARWOOD_FOREST)){
            return super.getAttackDamageBonus(target, damage, damageSource);
        }
        return(5);
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();
        if((level.isNight() || level.getBiome(attacker.blockPosition()).is(ModBiomes.STARWOOD_FOREST)) && level instanceof ServerLevel serverLevel){
            EntityType<LightningBolt> entityType = EntityType.LIGHTNING_BOLT;
            target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS,300,0));


            final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            executorService.scheduleWithFixedDelay(()->{
                entityType.spawn(serverLevel,target.blockPosition(), MobSpawnType.TRIGGERED);
            },1,0,TimeUnit.SECONDS);



        }
        return super.hurtEnemy(stack, target, attacker);
    }

}
