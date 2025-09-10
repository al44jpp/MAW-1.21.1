package net.al44jpp.makeawish.item.custom.tools;

import net.al44jpp.makeawish.worldgen.ModBiomes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
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
    ScheduledFuture<?> task = executorService.schedule(()->{},0,TimeUnit.MILLISECONDS);

    public nightPickaxeItem(Tier p_42961_, Properties p_42964_) {
        super(p_42961_, p_42964_);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        if(level.isNight()||level.getBiome(pos).is(ModBiomes.STARWOOD_FOREST)){
            if(miningEntity instanceof Player player){
                if(mining_power_boost>0){
                    player.removeEffect(MobEffects.DIG_SPEED);
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,60,Math.min(5,(int)mining_power_boost)-1));

                }
                if(mining_power_boost>=0 && blocks_to_mine <= 0){
                    mining_power_boost += 1;
                    if(mining_power_boost<=5){
                        player.playNotifySound(SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.PLAYERS,10f,(mining_power_boost)/3f - 0.5f);
                    }
                    task.cancel(true);
                    task = executorService.schedule(resetMiningSpeedBonus(),15,TimeUnit.SECONDS);
                }
                blocks_to_mine -= 1;
                if(blocks_to_mine<=0){
                    blocks_to_mine = 3+2*(int)mining_power_boost;
                }
                blocks_to_mine-=1;
            }
        }




        return super.mineBlock(stack, level, state, pos, miningEntity);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.makeawish.night_pickaxe"));
    }

    public Runnable resetMiningSpeedBonus(){
        return ()->{
            mining_power_boost = 0;
        };
    }

}
