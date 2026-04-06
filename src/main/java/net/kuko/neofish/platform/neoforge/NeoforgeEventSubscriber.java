package net.kuko.neofish.platform.neoforge;

//? neoforge {

import net.kuko.neofish.event.FishEventHandler; // sample_content
import net.minecraft.server.level.ServerPlayer; // sample_content
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber
public class NeoforgeEventSubscriber {

	@SubscribeEvent // sample_content
	public static void onPlayerDamage(LivingDamageEvent.Post event) { // sample_content
		if (event.getEntity() instanceof ServerPlayer player && event.getNewDamage() > 0) { // sample_content
			FishEventHandler.onPlayerHurt(player); // sample_content
		} // sample_content
	} // sample_content
}
//?}
