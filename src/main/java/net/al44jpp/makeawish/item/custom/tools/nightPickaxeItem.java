package net.al44jpp.makeawish.item.custom.tools;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.worldgen.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class nightPickaxeItem extends PickaxeItem {

    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    public float mining_power_boost = 0f;
    public int blocks_to_mine = 3;
    ScheduledFuture<?> task = executorService.schedule(()->{},999,TimeUnit.DAYS);

    public nightPickaxeItem(Tier p_42961_, Properties p_42964_) {
        super(p_42961_, p_42964_);
    }

    @Override
    public boolean mineBlock(@NotNull ItemStack stack, @NotNull Level level, @NotNull BlockState state, @NotNull BlockPos pos, @NotNull LivingEntity miningEntity) {
        //check if level is server-side and if the entity mining the block is a player.
        if(level instanceof ServerLevel serverLevel){
            //check if time or biome conditions are occurring.
            if(level.isNight()||level.getBiome(pos).is(ModBiomes.STARWOOD_FOREST)){
                if(miningEntity instanceof Player player && player instanceof ServerPlayer serverPlayer){
                    //inform the player that the pickaxe is active via a sound, higher pitch meaning higher mining speed.
                    player.playNotifySound(SoundEvents.BEACON_AMBIENT, SoundSource.PLAYERS,1,0.25f+mining_power_boost*0.25f);

                    //if the player is eligible to a mining speed boost, apply that boost to them.
                    if(mining_power_boost>0){
                        player.getAttribute(Attributes.BLOCK_BREAK_SPEED).removeModifier(ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID,"aaa"));
                        player.getAttribute(Attributes.BLOCK_BREAK_SPEED).addOrReplacePermanentModifier(new AttributeModifier(ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID,"aaa"),mining_power_boost/3, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));
                    }
                    //check if the number of blocks mined is sufficient and give a mining speed bonus if so.
                    if(mining_power_boost>=0 && blocks_to_mine <= 0){
                        mining_power_boost += 1;
                        if(mining_power_boost<=6){
                            serverLevel.sendParticles(serverPlayer,ParticleTypes.FIREWORK,false,player.getX(),player.getY(),player.getZ(),
                                    (int)Math.pow(4,(int)mining_power_boost),1,1,1,0.1);
                        }
                    }
                    //reset the number of blocks to mine relative to the current speed boost of the player.
                    if(blocks_to_mine<=0){
                        blocks_to_mine = (int)(Math.exp(mining_power_boost)/Math.max(mining_power_boost,1));
                        //player.sendSystemMessage(Component.literal("blocks to mine set to: " + blocks_to_mine));
                    }

                    //reduce by one the number of blocks to mine and schedule the reset of the speed bonus in 15s
                    //this will be canceled if the player mines another block.
                    blocks_to_mine-=1;
                    task.cancel(true);
                    task = executorService.schedule(resetMiningSpeedBonus(player),15,TimeUnit.SECONDS);
                    serverLevel.sendParticles(ParticleTypes.CRIT,pos.getX(),pos.getY(),pos.getZ(),25,0.5f,0.5f,0.5f,0);
                }

            }
        }
        return super.mineBlock(stack, level, state, pos, miningEntity);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.makeawish.night_pickaxe"));
    }

    private Runnable resetMiningSpeedBonus(Player player){
        return ()->{
            mining_power_boost = 0;
            player.getAttribute(Attributes.BLOCK_BREAK_SPEED).removeModifier(ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID,"aaa"));
            blocks_to_mine = 2;
        };
    }

}
