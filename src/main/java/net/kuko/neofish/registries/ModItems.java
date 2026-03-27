package net.kuko.neofish.registries;

import net.kuko.neofish.NeoFish;
import net.kuko.neofish.registries.item.MarkerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.awt.event.ItemListener;
import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NeoFish.MOD_ID);


//    public static final DeferredItem<Item> MARKER = ITEMS.registerItem( "marker",
//            MarkerItem::new, props -> props); //26.1

    public static final DeferredItem<Item> MARKER = item("marker",
        () -> new MarkerItem(new Item.Properties()));

    /**
     * Abstraction helper with generics
     * @param <T> The specific subtype of Item
     */
    public static <T extends Item> DeferredItem<T> item(String name, Supplier<T> supplier) {
        /*? if 1.21.1 {*/
        return ITEMS.register(name, supplier);
        /*?}*/
    }

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
/*
 *   Use Font monocraft.ttc
 *   Generator for Enchanting language:
 *   Generator for Lorem Ipsum: https://www.lipsum.com/
 *   Convert it with: https://cryptii.com/pipes/alphabetical-substitution
 *   Replace "ciphertext alphabet" with "
 */
