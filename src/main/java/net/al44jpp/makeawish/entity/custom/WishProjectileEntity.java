package net.al44jpp.makeawish.entity.custom;

import net.al44jpp.makeawish.entity.ModEntities;
import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.ObjectUtils;

public class WishProjectileEntity extends AbstractArrow {

    public WishProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }
    public WishProjectileEntity(LivingEntity shooter, Level level){
        super(ModEntities.wish_projectile.get(),shooter,level,new ItemStack(ModItems.wish.get()),null);
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

    @Override
    protected ItemStack getDefaultPickupItem() {
        return null;
    }
}
