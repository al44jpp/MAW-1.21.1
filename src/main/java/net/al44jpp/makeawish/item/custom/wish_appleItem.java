package net.al44jpp.makeawish.item.custom;

import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystBlock;
import net.neoforged.neoforge.client.event.sound.SoundEvent;
import net.neoforged.neoforge.common.data.SoundDefinition;
import org.apache.logging.log4j.core.Layout;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.inventory.AbstractContainerMenu;

import java.util.List;

public class wish_appleItem extends Item {

    public wish_appleItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity) {
        ItemStack  itemStack = super.finishUsingItem(stack,level,livingEntity);
        if(!level.isClientSide){
            if (livingEntity instanceof Player player){
                if(player instanceof ServerPlayer serverPlayer){
                    player.getCooldowns().addCooldown(this,2000);


                    player.getInventory().add(new ItemStack(ModItems.wish_apple.get()));
                    player.sendSystemMessage(Component.literal("the power of the apple fades... It needs to recover."));
                    level.playSound(null,player.getX(),player.getY(),player.getZ(),SoundEvents.BEACON_DEACTIVATE,SoundSource.PLAYERS);

                    serverPlayer.connection.send(new ClientboundContainerSetContentPacket(//refreshes the player's slot on client
                            player.containerMenu.containerId,
                            player.containerMenu.getStateId(),
                            player.containerMenu.getItems(),
                            ItemStack.EMPTY
                    ));
                }

            }
        }
        return itemStack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("A §dlegendary item§r summoned from the §k hi_gl:)§r by a wish. This apple will keep you full for §equite some time§r after eaten."));
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
        if (remainingUseDuration%4 == 0){
            level.playSound(null, livingEntity.getX(),livingEntity.getY(),livingEntity.getZ(), SoundEvents.AMETHYST_BLOCK_PLACE, SoundSource.AMBIENT, 20f, 33-remainingUseDuration);
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.WARPED_SPORE,livingEntity.getX(),livingEntity.getY(),livingEntity.getZ(), 120, 0.5, 1, 0.5, 0);
                serverLevel.sendParticles(ParticleTypes.FIREWORK,livingEntity.getX(),livingEntity.getY(),livingEntity.getZ(), 15, 0.5, 2, 0.5, 0);
            }
        }
    }
}

