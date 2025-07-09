package net.al44jpp.makeawish.item;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.item.custom.night_crystalItem;
import net.al44jpp.makeawish.item.custom.wish_appleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MAW.MOD_ID);

    //start of the field for creating items



    public static final DeferredItem<Item> wish = ITEMS.register("wish",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> wish_fragment = ITEMS.register("wish_fragment",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> night_crystal = ITEMS.register("night_crystal",
            ()-> new night_crystalItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> wish_apple = ITEMS.register("wish_apple",
            ()-> new wish_appleItem(new Item.Properties().food(ModFoodProperties.wish_apple).rarity(Rarity.EPIC)));

    //end of that field

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
