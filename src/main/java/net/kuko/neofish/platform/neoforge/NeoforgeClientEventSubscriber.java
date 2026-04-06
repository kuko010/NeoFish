package net.kuko.neofish.platform.neoforge;

//? neoforge {

import net.kuko.neofish.ModNeoFish;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = ModNeoFish.MOD_ID, value = Dist.CLIENT)
public class NeoforgeClientEventSubscriber {
	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		ModNeoFish.onInitializeClient();
	}
}
//?}
