package net.al44jpp.makeawish.entity;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.entity.custom.WishProjectileEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MAW.MOD_ID);

    public static final Supplier<EntityType<WishProjectileEntity>> wish_projectile =
            ENTITY_TYPES.register("wish_projectile",() -> EntityType.Builder.<WishProjectileEntity>of(WishProjectileEntity::new, MobCategory.MISC)
                    .sized(1.0f,1.0f).build("wish_projectile"));

    public static void register (IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
