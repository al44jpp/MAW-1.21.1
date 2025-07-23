package net.al44jpp.makeawish.block;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MAW.MOD_ID);


    public static final DeferredBlock<Block> night_crystal_block =registerBlock("night_crystal_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> stone_night_crystal_ore =registerBlock("stone_night_crystal_ore",
            () -> new DropExperienceBlock(UniformInt.of(15,25)
                    ,BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    public static final DeferredBlock<Block> deepslate_night_crystal_ore =registerBlock("deepslate_night_crystal_ore",
            () -> new DropExperienceBlock(UniformInt.of(15,25)
                    ,BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> starwood_leaves =registerBlock("starwood_leaves",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.GRASS).noLootTable()));









//below is crazy stuff to register the block as well as the item associated with it
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> ToReturn = BLOCKS.register(name, block);
        registerBlockItem(name, ToReturn);
        return ToReturn;
    }
    private static <T extends Block> void registerBlockItem(String name,DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));

    }






    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
