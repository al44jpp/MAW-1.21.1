package net.al44jpp.makeawish.entity.custom;

import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class WishProjectileEntity extends AbstractHurtingProjectile {
    public WishProjectileEntity(EntityType<? extends AbstractHurtingProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        Item item = ModItems.wish_apple.asItem();
        ItemStack stack = new ItemStack(item,1);
        BlockPos pos = result.getBlockPos();
        Level level = this.level();
        ItemEntity itemEntity = new ItemEntity(level,pos.getX(),pos.getY(),pos.getZ(),stack);
        level.addFreshEntity(itemEntity);
        this.discard();
    }
}
