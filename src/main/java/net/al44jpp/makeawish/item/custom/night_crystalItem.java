package net.al44jpp.makeawish.item.custom;

import com.mojang.blaze3d.shaders.Effect;
import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.client.gui.font.providers.UnihexProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Interaction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.WorldDimensions;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class night_crystalItem extends Item {

    public night_crystalItem(Properties properties) {
        super(properties);
    }



    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack item = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (level.isNight()) {

            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300 * 20));
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 45 * 20));
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 30, 255));
            player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 150, 1));

            level.playSound(null, player.getX(),player.getY(),player.getZ(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.AMBIENT, 10f, 1f);

            // Spawn particles
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.WARPED_SPORE,player.getX(),player.getY(),player.getZ(), 120, 0.5, 1, 0.5, 0);
                serverLevel.sendParticles(ParticleTypes.FIREWORK,player.getX(),player.getY(),player.getZ(), 15, 0.5, 2, 0.5, 0);
            }
            item.consume(1,null);
            return InteractionResultHolder.success(item);

        }
        return InteractionResultHolder.pass(item);



    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.makeawish.night_crystal"));
    }
}





