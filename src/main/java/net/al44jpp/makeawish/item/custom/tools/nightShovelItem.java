package net.al44jpp.makeawish.item.custom.tools;

import net.al44jpp.makeawish.block.ModBlocks;
import net.al44jpp.makeawish.worldgen.ModBiomes;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.IShearable;
import net.neoforged.neoforge.common.ItemAbilities;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class nightShovelItem extends ShovelItem {


    public nightShovelItem(Tier p_43114_, Properties p_43117_) {
        super(p_43114_, p_43117_);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
    }
    @Override
    public @NotNull InteractionResult interactLivingEntity(@NotNull ItemStack stack, @NotNull Player player, @NotNull LivingEntity entity, @NotNull InteractionHand hand) {
        if (entity instanceof IShearable target) {
            BlockPos pos = entity.blockPosition();
            boolean isClient = entity.level().isClientSide();
            if (target.isShearable(player, stack, entity.level(), pos)) {
                List<ItemStack> drops = target.onSheared(player, stack, entity.level(), pos);
                if (!isClient) {
                    for(ItemStack drop : drops) {
                        target.spawnShearedDrop(entity.level(), pos, drop);
                    }
                }

                entity.gameEvent(GameEvent.SHEAR, player);
                if (!isClient) {
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                }

                return InteractionResult.sidedSuccess(isClient);
            }
        }

        return InteractionResult.PASS;
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        Level level = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        boolean nightCondition = level.isNight()|level.getBiome(player.blockPosition()).is(ModBiomes.STARWOOD_FOREST);
        if(nightCondition) {
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.is(Blocks.OBSIDIAN) || blockstate.is(BlockTags.INFINIBURN_OVERWORLD) || blockstate.is(BlockTags.INFINIBURN_NETHER) || blockstate.is(BlockTags.INFINIBURN_END)) {
                BlockState blockstate2 = blockstate.getToolModifiedState(context, ItemAbilities.FIRESTARTER_LIGHT, false);
                if (blockstate2 == null) {
                    BlockPos blockpos1 = blockpos.relative(context.getClickedFace());
                    if (BaseFireBlock.canBePlacedAt(level, blockpos1, context.getHorizontalDirection())) {
                        level.playSound(player, blockpos1, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                        BlockState blockstate1 = BaseFireBlock.getState(level, blockpos1);
                        level.setBlock(blockpos1, blockstate1, 11);
                        level.gameEvent(player, GameEvent.BLOCK_PLACE, blockpos);
                        ItemStack itemstack = context.getItemInHand();
                        if (player instanceof ServerPlayer) {
                            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer) player, blockpos1, itemstack);
                            itemstack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                        }
                        return InteractionResult.sidedSuccess(level.isClientSide());
                    }
                } else {
                    level.playSound(player, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
                    level.setBlock(blockpos, blockstate2, 11);
                    level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockpos);
                    if (player != null) {
                        context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                    }

                    return InteractionResult.sidedSuccess(level.isClientSide());
                }
            }else{
               level.playSound(null, blockpos, SoundEvents.ROOTED_DIRT_PLACE, SoundSource.BLOCKS, 10.0f, 1.0f);
                if (player.isCrouching() || !ShovelItem.FLATTENABLES.containsKey(blockstate.getBlock())) {
                    if (blockstate.is(BlockTags.REPLACEABLE)) {
                        level.setBlock(blockpos, ModBlocks.night_dirt.get().defaultBlockState(), 11);
                        if (player != null) {
                            context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                        }
                        level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockpos);
                        return InteractionResult.SUCCESS;
                    }
                    BlockPos blockpos1 = blockpos.relative(context.getClickedFace());
                    BlockState blockState1 = level.getBlockState(blockpos1);
                    if (blockState1.is(BlockTags.REPLACEABLE) || blockState1.is(Blocks.AIR)) {
                        level.setBlock(blockpos1, ModBlocks.night_dirt.get().defaultBlockState(), 11);
                        if (player != null) {
                            context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
                        }
                        level.gameEvent(player, GameEvent.BLOCK_CHANGE, blockpos);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("tooltip.makeawish.night_shovel"));
    }
}

