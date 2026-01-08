package net.al44jpp.makeawish.item.custom;

import net.al44jpp.makeawish.entity.ModEntities;
import net.al44jpp.makeawish.entity.custom.WishProjectileEntity;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class WishItem extends Item {
    public WishItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        level.playSound(null, player.blockPosition(), SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.PLAYERS);
        WishProjectileEntity wishProjectile = new WishProjectileEntity(ModEntities.wish_projectile.get(), level);
        wishProjectile.noPhysics = true;
        wishProjectile.setPos(player.position().add(new Vec3(0d, 50d, 0d)));
        wishProjectile.shoot((Math.random()-0.5)*5, Math.random()*2, (Math.random()-0.5)*5, 1f, 0.5f);
        level.addFreshEntity(wishProjectile);
        stack.consume(1, player);
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());

    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 50;
    }
}