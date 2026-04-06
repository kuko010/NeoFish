package net.kuko.neofish.platform.neoforge;

//? neoforge {

import net.kuko.neofish.ModNeoFish;
import net.kuko.neofish.registries.ModCreativeModeTabs;
import net.kuko.neofish.registries.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ModNeoFish.MOD_ID)
public class NeoforgeEntrypoint {

	public NeoforgeEntrypoint(IEventBus modEventBus) {
		ModItems.register(modEventBus);
		ModCreativeModeTabs.register(modEventBus);
		ModNeoFish.onInitialize();
	}
}
//?}
