package net.al44jpp.makeawish.item;

import net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.item.custom.ModToolTiers;
import net.al44jpp.makeawish.item.custom.night_crystalItem;
import net.al44jpp.makeawish.item.custom.wish_appleItem;
import net.minecraft.world.item.*;
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


        //tools
    public static final DeferredItem<SwordItem> night_sword = ITEMS.register("night_sword",
                () -> new SwordItem(ModToolTiers.NIGHT_CRYSTAL,new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.NIGHT_CRYSTAL,3,-2.4F))));
    public static final DeferredItem<PickaxeItem> night_pickaxe = ITEMS.register("night_pickaxe",
            () -> new PickaxeItem(ModToolTiers.NIGHT_CRYSTAL,new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.NIGHT_CRYSTAL,1,-2.8F))));
    public static final DeferredItem<AxeItem> night_axe = ITEMS.register("night_axe",
            () -> new AxeItem(ModToolTiers.NIGHT_CRYSTAL,new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.NIGHT_CRYSTAL,5,-3.0F))));
    public static final DeferredItem<ShovelItem> night_shovel = ITEMS.register("night_shovel",
            () -> new ShovelItem(ModToolTiers.NIGHT_CRYSTAL,new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.NIGHT_CRYSTAL,1.5f,-3.0F))));


    //end of that field

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
