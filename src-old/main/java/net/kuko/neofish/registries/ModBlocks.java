package net.kuko.neofish.registries;

import net.kuko.neofish.NeoFish;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NeoFish.MOD_ID);


    public static final DeferredBlock<Block> INVERSE_BLOCK = block("inverse",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)));

    private static <T extends Block> DeferredBlock<T> block(String name, Supplier<T> supplier) {
        /*? if 26.1 {*/
        /* DeferredBlock<T> toReturn = BLOCKS.registerBlock(name, supplier); */
        /* ModItems.ITEMS.registerItem(name, (properties) -> new BlockItem(toReturn.get(), properties.useBlockDescriptionPrefix())); */
        /*? } else {*/
        // For 1.21.1 / NeoForge
        DeferredBlock<T> toReturn = BLOCKS.register(name, supplier);

        // You must wrap the block in a BlockItem supplier
        ModItems.item(name, () -> new BlockItem(toReturn.get(), new Item.Properties()));
        /*?}*/

        return toReturn;
    }

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
