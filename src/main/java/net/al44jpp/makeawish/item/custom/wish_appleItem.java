package net.al44jpp.makeawish.item.custom;

import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
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
                    assert (player != null);
                    player.getCooldowns().addCooldown(this,2000);


                    player.getInventory().add(new ItemStack(ModItems.wish_apple.get()));
                    player.sendSystemMessage(Component.literal("the power of the apple fades... It needs to recover."));
                    serverPlayer.connection.send(new ClientboundContainerSetContentPacket(
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
        return UseAnim.EAT;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("A §dlegendary item§r summoned from the §k hi_gl:)§r by a wish. This apple will keep you full for §equite some time§r after eaten."));
    }
}
