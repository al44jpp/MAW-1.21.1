package net.al44jpp.makeawish.item.custom;

import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundContainerSetContentPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.inventory.AbstractContainerMenu;

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
                    player.getCooldowns().addCooldown(this,200);


                    player.getInventory().add(new ItemStack(ModItems.wish_apple.get()));
                    player.sendSystemMessage(Component.literal("you should have received the apple"));
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
        return UseAnim.BOW;
    }
}
