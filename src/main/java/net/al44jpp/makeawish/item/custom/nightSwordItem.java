package net.al44jpp.makeawish.item.custom;

import com.mojang.datafixers.kinds.App;
import net.al44jpp.makeawish.worldgen.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.commands.ScheduleCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class nightSwordItem extends SwordItem {
    public nightSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, @NotNull LivingEntity target, LivingEntity attacker) {
        if(attacker instanceof Player player) {

            Level level = attacker.level();
            LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(level);
            assert lightningBolt != null;
            lightningBolt.setVisualOnly(true);

            if ((level.isNight() || level.getBiome(attacker.blockPosition()).is(ModBiomes.STARWOOD_FOREST)) && level instanceof ServerLevel level_server && !player.getCooldowns().isOnCooldown(this)) {
                executorService.schedule(() -> {
                    lightningBolt.setPos(target.getX(), target.getY(), target.getZ());
                    level.addFreshEntity(lightningBolt);
                    target.hurt(target.damageSources().lightningBolt(), 5f);
                    target.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 0));
                    target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 300, 2));
                    target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60, 0));
                }, 1000, TimeUnit.MILLISECONDS);
                player.getCooldowns().addCooldown(this,100);
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.makeawish.night_sword"));
    }
}
