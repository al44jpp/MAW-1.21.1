package net.al44jpp.makeawish.item.custom.util;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;


public class LegendaryItem {
    private ItemStack stack;
    private ItemEntity entity;
    public LegendaryItem(ItemStack stack, ItemEntity entity){
        this.stack = stack;
        this.entity = entity;
    }


    public void SpawnLegendaryParticles() {
        double randomDouble = (Math.random()-0.5)*2*3.1415d;
        if(this.entity.level() instanceof  ServerLevel level){
            level.sendParticles(ParticleTypes.FIREWORK,this.entity.getX(),this.entity.getY(),this.entity.getZ(),2,0.1d,1,0.1d,0);
            level.sendParticles(ParticleTypes.FIREWORK,this.entity.getX()+Math.cos(randomDouble),this.entity.getY()+0.2f,this.entity.getZ()+Math.sin(randomDouble),1,0,0,0,0);
        }
    }
}
