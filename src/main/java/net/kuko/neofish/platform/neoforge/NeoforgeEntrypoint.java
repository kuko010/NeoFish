package net.kuko.neofish.platform.neoforge;

//? neoforge {

import net.kuko.neofish.ModNeoFish;
import net.kuko.neofish.registries.ModCreativeModeTabs;
import net.kuko.neofish.registries.ModDataComponents;
import net.kuko.neofish.registries.ModItems;
import net.mcexpanded.fancytabsections.FancyTabSections;
import net.mcexpanded.fancytabsections.creativetab.SectionColored;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLLoader;

import java.util.List;

import static net.kuko.neofish.ModNeoFish.rl;

@Mod(ModNeoFish.MOD_ID)
public class NeoforgeEntrypoint {

	public NeoforgeEntrypoint(IEventBus modEventBus) {
		ModItems.register(modEventBus);
		ModCreativeModeTabs.register(modEventBus);
		ModDataComponents.register(modEventBus);
		ModNeoFish.onInitialize(); // pass bus in, do addSection inside


		modEventBus.addListener(FMLCommonSetupEvent.class, event -> {
			FancyTabSections.addSection(rl("neo"),
					new SectionColored(
							rl("neo_items"),
							Component.translatable("itemGroup.neofish.neoitems"),
							0xFFb989d9,
							0xFFFFFFFF,
							List.of(ModItems.MARKER.get()) // ✅ safe here
					)
			);
		});
	}
}
//?}
