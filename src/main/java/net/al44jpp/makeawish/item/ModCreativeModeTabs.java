package net.al44jpp.makeawish.item;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static  final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MAW.MOD_ID);



    public static final Supplier<CreativeModeTab> WISH_ITEMS_TAB = CREATIVE_MODE_TAB.register("wish_items_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.wish.get()))
                    .title(Component.translatable("creativetab.makeawish.wish_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.wish);
                        output.accept(ModItems.wish_fragment);
                        output.accept(ModItems.wish_apple);
                        output.accept(ModItems.night_sword);
                        output.accept(ModItems.night_pickaxe);
                        output.accept(ModItems.night_axe);
                        output.accept(ModItems.night_shovel);
                        output.accept(ModItems.night_hoe);
                        output.accept(ModBlocks.stone_night_crystal_ore);
                        output.accept(ModBlocks.deepslate_night_crystal_ore);
                        output.accept(ModBlocks.night_crystal_block);
                        output.accept(ModItems.night_crystal);
                        output.accept(ModBlocks.starwood_log);
                        output.accept(ModBlocks.starwood_leaves);
                        output.accept(ModBlocks.starwood_sapling);
                        output.accept(ModBlocks.starwood_plank);
                        output.accept(ModBlocks.starwood_stairs);
                        output.accept(ModBlocks.starwood_slab);
                        output.accept(ModBlocks.starwood_pressure_plate);
                        output.accept(ModBlocks.starwood_button);
                        output.accept(ModBlocks.starwood_fence);
                        output.accept(ModBlocks.starwood_fence_gate);
                        output.accept(ModBlocks.starwood_door);
                        output.accept(ModBlocks.starwood_trapdoor);



                    })
                    //.withTabsBefore(ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID, "wish_items_tab"))


                    .build());













    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
