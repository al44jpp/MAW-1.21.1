package net.al44jpp.makeawish.item.custom.tools;

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

public class nightAxeItem extends AxeItem {
    public nightAxeItem(Tier p_40521_, Properties p_40524_) {
        super(p_40521_, p_40524_);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Level level = attacker.level();
        int amount_to_heal = (int)(( 1 - (attacker.getHealth()/attacker.getMaxHealth())) * 10);
        if ((level.isNight()||level.getBiome(attacker.blockPosition()).is(ModBiomes.STARWOOD_FOREST))  && !level.isClientSide && attacker instanceof Player player){
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION,45,Math.min(amount_to_heal,4)));
            level.playSound(null, player.getX(),player.getY(),player.getZ(), SoundEvents.BELL_RESONATE, SoundSource.AMBIENT, 10f, 1f);
        }

        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.makeawish.night_axe"));
    }
}
