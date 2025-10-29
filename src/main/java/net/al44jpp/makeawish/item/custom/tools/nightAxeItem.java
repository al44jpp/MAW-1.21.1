package net.al44jpp.makeawish.item.custom.tools;

import net.al44jpp.makeawish.item.ModItems;
import net.al44jpp.makeawish.worldgen.ModBiomes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.concurrent.*;

public class nightAxeItem extends AxeItem {
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    ScheduledFuture<?> task = executorService.schedule(()->{},999,TimeUnit.DAYS);
    public nightAxeItem(Tier p_40521_, Properties p_40524_) {
        super(p_40521_, p_40524_);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();
        int amount_to_heal = (int)(( 1 - (attacker.getHealth()/attacker.getMaxHealth())) * 17 + 2);
        if ((level.isNight()||level.getBiome(attacker.blockPosition()).is(ModBiomes.STARWOOD_FOREST))  && !level.isClientSide && attacker instanceof Player player && !player.getCooldowns().isOnCooldown(ModItems.night_axe.asItem())){
            //player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,45,Math.min(amount_to_heal,4)));
            level.playSound(null, player.getX(),player.getY(),player.getZ(), SoundEvents.BELL_RESONATE, SoundSource.AMBIENT, 10f, 1f);
            heal(amount_to_heal,player);
            player.getCooldowns().addCooldown(ModItems.night_axe.asItem(),200);
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.makeawish.night_axe"));
    }
    private void heal(int amount, Player player){
        if(player.getHealth()==player.getMaxHealth()) {
            player.setAbsorptionAmount(player.getAbsorptionAmount()+1);
        }else {
            player.heal(1);
        }

        if(amount>0){
            executorService.schedule(()-> heal(amount-1,player),200,TimeUnit.MILLISECONDS);
        }


    }
}
