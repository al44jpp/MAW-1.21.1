package net.al44jpp.makeawish.block.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.SimpleMapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class moon_altarBlock extends HorizontalDirectionalBlock {

    public static MapCodec<moon_altarBlock> codec = simpleCodec(moon_altarBlock::new);

    public moon_altarBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return codec;
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING,context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected void onPlace(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean movedByPiston) {
        if(level instanceof ServerLevel serverLevel && !movedByPiston){
            level.playLocalSound(pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS,1,0.5f,false);
            serverLevel.sendParticles(ParticleTypes.FIREWORK,pos.getX(),pos.getY(),pos.getZ(),500,1,1,1,1);
        }

        super.onPlace(state, level, pos, oldState, movedByPiston);
    }
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);


        int i;
        for(i=0;i<4;i++){
            level.addParticle(ParticleTypes.FIREWORK,pos.getX()+Math.random()*20-10,pos.getY()+Math.random()*5+3,pos.getZ()+Math.random()*20-10,0,0.05,0);
        }



    }
}
