package net.al44jpp.makeawish.item.custom;

import net.al44jpp.makeawish.entity.custom.WishProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WishItem extends Item {
    public WishItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        level.playSound(null,player.blockPosition(), SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.PLAYERS);
        WishProjectileEntity wishProjectile = new WishProjectileEntity(player,level);
        wishProjectile.noPhysics = true;
        wishProjectile.setPos(player.position());
        wishProjectile.shoot(0,1,0,0.5f,0.5f);
        level.addFreshEntity(wishProjectile);
        stack.shrink(1);
        return InteractionResultHolder.sidedSuccess(stack,level.isClientSide());

    }
}