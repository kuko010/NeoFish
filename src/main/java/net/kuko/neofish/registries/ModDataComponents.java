package net.kuko.neofish.registries;

import net.kuko.neofish.ModNeoFish;
import net.kuko.neofish.client.BlockOutline;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.UnaryOperator;

public class ModDataComponents {
	public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
			DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ModNeoFish.MOD_ID);



	public static final DeferredHolder<DataComponentType<?>, DataComponentType<List<BlockOutline>>> SELECTED_BLOCKS = register("selected_blocks",
			builder -> builder.persistent(BlockOutline.CODEC.listOf()));



	private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(
			String name, UnaryOperator<DataComponentType.Builder<T>> builder
	) {
		return DATA_COMPONENT_TYPES.register(name, () -> builder.apply(DataComponentType.builder()).build());
	}

	public static void register(IEventBus bus) {
		DATA_COMPONENT_TYPES.register(bus);
	}
}
