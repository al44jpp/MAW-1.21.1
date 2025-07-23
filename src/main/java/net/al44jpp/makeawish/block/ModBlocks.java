package net.al44jpp.makeawish.block;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
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

    public static final DeferredBlock<Block> starwood_plank =registerBlock("starwood_plank",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f).sound(SoundType.WOOD)));

    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

    public static final DeferredBlock<StairBlock> starwood_stairs=registerBlock("starwood_stairs",
            ()-> new StairBlock(ModBlocks.starwood_plank.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD)));
    public static final DeferredBlock<SlabBlock> starwood_slab=registerBlock("starwood_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD)));


    public static final DeferredBlock<PressurePlateBlock> starwood_pressure_plate=registerBlock("starwood_pressure_plate",
            ()-> new PressurePlateBlock(BlockSetType.ACACIA,
                    BlockBehaviour.Properties.of().strength(1f).sound(SoundType.WOOD)));
    public static final DeferredBlock<ButtonBlock> starwood_button=registerBlock("starwood_button",
            ()-> new ButtonBlock(BlockSetType.ACACIA,100,
                    BlockBehaviour.Properties.of().strength(1f).sound(SoundType.WOOD).noCollission()));


    public static final DeferredBlock<FenceBlock> starwood_fence=registerBlock("starwood_fence",
            ()-> new FenceBlock(BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD)));
    public static final DeferredBlock<FenceGateBlock> starwood_fence_gate=registerBlock("starwood_fence_gate",
            ()-> new FenceGateBlock(WoodType.ACACIA,BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD)));



    public static final DeferredBlock<DoorBlock> starwood_door=registerBlock("starwood_door",
            ()-> new DoorBlock(BlockSetType.ACACIA,BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD).noOcclusion()));
    public static final DeferredBlock<TrapDoorBlock> starwood_trapdoor=registerBlock("starwood_trapdoor",
            ()-> new TrapDoorBlock(BlockSetType.ACACIA,
                    BlockBehaviour.Properties.of().strength(2f).sound(SoundType.WOOD).noOcclusion()));









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
