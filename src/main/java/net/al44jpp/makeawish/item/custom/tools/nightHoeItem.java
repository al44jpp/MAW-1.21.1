package net.al44jpp.makeawish.item.custom.tools;

import net.al44jpp.makeawish.worldgen.ModBiomes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class nightHoeItem extends HoeItem {
    public nightHoeItem(Tier p_41336_, Properties p_41339_) {
        super(p_41336_, p_41339_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (level.getBiome(player.blockPosition()).is(ModBiomes.STARWOOD_FOREST) || level.isNight()){
            Vec3 lookAngle = player.getLookAngle();
            player.setDeltaMovement(player.getDeltaMovement().add(lookAngle));
        }
        return super.use(level, player, usedHand);
    }
}
