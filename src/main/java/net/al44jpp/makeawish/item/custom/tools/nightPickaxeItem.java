package net.al44jpp.makeawish.item.custom.tools;

import net.al44jpp.makeawish.worldgen.ModBiomes;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.effects.SpawnParticlesEffect;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

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
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        if(level instanceof ServerLevel serverLevel){
            if(level.isNight()||level.getBiome(pos).is(ModBiomes.STARWOOD_FOREST)){
                if(miningEntity instanceof Player player && player instanceof ServerPlayer serverPlayer){
                    player.playNotifySound(SoundEvents.BEACON_AMBIENT, SoundSource.PLAYERS,1,0.25f+mining_power_boost*0.25f);
                    if(mining_power_boost>0){
                        player.removeEffect(MobEffects.DIG_SPEED);
                        player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,60,Math.min(5,(int)mining_power_boost)-1));

                    }
                    if(mining_power_boost>=0 && blocks_to_mine <= 0){
                        mining_power_boost += 1;
                        if(mining_power_boost<=5){
                            serverLevel.sendParticles(serverPlayer,ParticleTypes.FIREWORK,false,player.getX(),player.getY(),player.getZ(),
                                    (int)Math.pow(4,(int)mining_power_boost),1,1,1,0.1);
                        }
                    }

                    if(blocks_to_mine<=0){
                        blocks_to_mine = (int)(Math.exp(mining_power_boost)/Math.max(mining_power_boost,1));
                        player.sendSystemMessage(Component.literal("blocks to mine set to: " + blocks_to_mine));
                    }
                    blocks_to_mine-=1;

                    task.cancel(true);
                    System.out.println(task.getDelay(TimeUnit.MILLISECONDS));
                    task = executorService.schedule(resetMiningSpeedBonus(),15,TimeUnit.SECONDS);
                }

            }
        }
        return super.mineBlock(stack, level, state, pos, miningEntity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.makeawish.night_pickaxe"));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.sendSystemMessage(Component.literal("task: " + task.state()));
        return super.use(level, player, usedHand);
    }

    public Runnable resetMiningSpeedBonus(){
        return ()->{
            mining_power_boost = 0;
            blocks_to_mine = 2;
        };
    }

}
