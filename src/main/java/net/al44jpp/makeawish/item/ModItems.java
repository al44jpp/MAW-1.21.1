package net.al44jpp.makeawish.item;

import  net.al44jpp.makeawish.MAW;
import net.al44jpp.makeawish.item.custom.*;
import net.al44jpp.makeawish.item.custom.armor.ModArmorMaterials;
import net.al44jpp.makeawish.item.custom.armor.WhishArmorItem;

import net.al44jpp.makeawish.item.custom.tools.*;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MAW.MOD_ID);

    //start of the field for creating items



    public static final DeferredItem<Item> wish = ITEMS.register("wish",
            ()-> new WishItem(new Item.Properties().rarity(Rarity.EPIC).fireResistant().stacksTo(1)));

    public static final DeferredItem<Item> wish_fragment = ITEMS.register("wish_fragment",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> night_crystal = ITEMS.register("night_crystal",
            ()-> new night_crystalItem(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final DeferredItem<Item> wish_apple = ITEMS.register("wish_apple",
            ()-> new wish_appleItem(new Item.Properties().food(ModFoodProperties.wish_apple).rarity(Rarity.EPIC).component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE,true)));


        //tools
    public static final DeferredItem<SwordItem> night_sword = ITEMS.register("night_sword",
                () -> new nightSwordItem(ModToolTiers.NIGHT_CRYSTAL,new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.NIGHT_CRYSTAL,5,-2.4F)).rarity(Rarity.RARE)));
    public static final DeferredItem<PickaxeItem> night_pickaxe = ITEMS.register("night_pickaxe",
            () -> new nightPickaxeItem(ModToolTiers.NIGHT_CRYSTAL,new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.NIGHT_CRYSTAL,1.5f,-2.8F)).rarity(Rarity.RARE)));
    public static final DeferredItem<AxeItem> night_axe = ITEMS.register("night_axe",
            () -> new nightAxeItem(ModToolTiers.NIGHT_CRYSTAL,new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.NIGHT_CRYSTAL,8,-3.0F)).rarity(Rarity.RARE)));
    public static final DeferredItem<ShovelItem> night_shovel = ITEMS.register("night_shovel",
            () -> new nightShovelItem(ModToolTiers.NIGHT_CRYSTAL,new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.NIGHT_CRYSTAL,2f,-3.0F)).rarity(Rarity.RARE)));
    public static final DeferredItem<HoeItem> night_hoe = ITEMS.register("night_hoe",
            () -> new nightHoeItem(ModToolTiers.NIGHT_CRYSTAL,new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.NIGHT_CRYSTAL,1.0f,0.0F)).rarity(Rarity.RARE)));

        //armor

    public static final DeferredItem<ArmorItem> wish_chestplate = ITEMS.register("wish_chestplate",
            ()-> new WhishArmorItem(ModArmorMaterials.WISH_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19)).rarity(Rarity.EPIC)));

    public static final DeferredItem<ArmorItem> wish_leggings = ITEMS.register("wish_leggings",
            ()-> new WhishArmorItem(ModArmorMaterials.WISH_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19)).rarity(Rarity.EPIC)));

    public static final DeferredItem<ArmorItem> wish_helmet = ITEMS.register("wish_helmet",
            ()-> new WhishArmorItem(ModArmorMaterials.WISH_ARMOR_MATERIAL, ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19)).rarity(Rarity.EPIC)));

    public static final DeferredItem<ArmorItem> wish_boots = ITEMS.register("wish_boots",
            ()-> new WhishArmorItem(ModArmorMaterials.WISH_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(19)).rarity(Rarity.EPIC)));

    //end of that field

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
