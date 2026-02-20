package net.al44jpp.makeawish.item.custom.armor;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.item.ModItems;
import net.al44jpp.makeawish.item.custom.util.LegendaryItem;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;


public class WhishArmorItem extends ArmorItem{
    public WhishArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }



    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {

        if(entity instanceof Player player && level instanceof ServerLevel serverLevel){
            if(FullArmorEquiped(player) && player.getKnownMovement().length()>0.22f && !player.isCrouching()){
                serverLevel.sendParticles(ParticleTypes.FIREWORK,player.getX(),player.getY()+0.2f,player.getZ(), 2, 0.5, 0, 0.5, 0);
            }
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        LegendaryItem legendaryItem = new LegendaryItem(stack,entity);
        legendaryItem.SpawnLegendaryParticles();
        return super.onEntityItemUpdate(stack,entity);
    }


    public static boolean FullArmorEquiped(Player player){
        return player.getInventory().getArmor(0).is(ModItems.wish_boots) && player.getInventory().getArmor(1).is(ModItems.wish_leggings) && player.getInventory().getArmor(2).is(ModItems.wish_chestplate) && player.getInventory().getArmor(3).is(ModItems.wish_helmet);
    }

}
