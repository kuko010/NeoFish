package net.kuko.neofish.registries;

import net.kuko.neofish.ModNeoFish;
import net.kuko.neofish.registries.item.MarkerItem;
import net.minecraft.world.item.Item;

/*? if neoforge {*/
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
/*? }*/

import java.util.function.Supplier;

//todo: Make this platform-agonist
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ModNeoFish.MOD_ID);

    public static final DeferredItem<Item> MARKER = item("marker",
        () -> new MarkerItem(new Item.Properties()));


    /**
     * Abstraction helper with generics
     * @param <T> The specific subtype of Item
     */
    protected static <T extends Item> DeferredItem<T> item(String name, Supplier<T> supplier) {
        return ITEMS.register(name, supplier);
    }

    public static void register(/*? if neoforge {*/ IEventBus bus /*?}*/) {
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
