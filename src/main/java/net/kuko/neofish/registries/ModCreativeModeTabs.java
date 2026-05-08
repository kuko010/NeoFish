package net.kuko.neofish.registries;

import net.kuko.neofish.ModNeoFish;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

//todo: Make this platform-agonist
public class ModCreativeModeTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
			Registries.CREATIVE_MODE_TAB, ModNeoFish.MOD_ID);

	public static final Supplier<CreativeModeTab> NEOFISH_TAB = CREATIVE_MODE_TABS.register("neo", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(ModItems.MARKER.get()))
			.title(Component.translatable("itemGroup.neofish.tab"))
			.displayItems((p, o) ->
			{
				// Intentionally empty as we add the items through FancyTabsSections#addSection
			})
			.build());


	public static void register(/*? if neoforge {*/ IEventBus bus /*?}*/) {
		CREATIVE_MODE_TABS.register(bus);
	}
}
