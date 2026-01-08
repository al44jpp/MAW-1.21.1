package net.al44jpp.makeawish.entity.custom;

import net.al44jpp.makeawish.entity.ModEntities;
import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.apache.commons.lang3.ObjectUtils;

public class WishProjectileEntity extends ThrowableItemProjectile {


    public WishProjectileEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public WishProjectileEntity(EntityType<? extends ThrowableItemProjectile> entityType, double x, double y, double z, Level level) {
        super(entityType, x, y, z, level);
    }

    public WishProjectileEntity(EntityType<? extends ThrowableItemProjectile> entityType, LivingEntity shooter, Level level) {
        super(entityType, shooter, level);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        LightningBolt bolt = new LightningBolt(EntityType.LIGHTNING_BOLT,level());
        bolt.setVisualOnly(true);
        bolt.setPos(result.getBlockPos().getX(),result.getBlockPos().getY(), result.getBlockPos().getZ());
        Item item = ModItems.wish_apple.asItem();
        ItemStack stack = new ItemStack(item,1);
        BlockPos pos = result.getBlockPos();
        Level level = this.level();
        ItemEntity itemEntity = new ItemEntity(level,pos.getX(),pos.getY(),pos.getZ(),stack);
        level.addFreshEntity(itemEntity);
        level.addFreshEntity(bolt);
        this.discard();
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.wish.get();
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
    }

    @Override
    public void tick() {
        Level level = this.level();
        if(level instanceof ServerLevel serverLevel){
            serverLevel.sendParticles(ParticleTypes.FIREWORK,this.getX(),this.getY(),this.getZ(),5,0.5d,0.5d,0.5d,0.01d);
        }
        this.displayFireAnimation();
        super.tick();
    }
}
