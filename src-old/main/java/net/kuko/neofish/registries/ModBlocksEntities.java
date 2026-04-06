package net.kuko.neofish.registries;

import net.kuko.neofish.NeoFish;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocksEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, NeoFish.MOD_ID);

//    public static final Supplier<BlockEntityType<InverseBlockEntity>> INVERSE_BE =
//            BLOCK_ENTITIES.register("inverse_be", () -> new BlockEntityType<InverseBlockEntity>(
//                    InverseBlockEntity::new, ModBlocks.INVERSE_BLOCK.get()));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
