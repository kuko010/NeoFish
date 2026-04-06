package net.kuko.neofish.platform.fabric;

//? fabric {

/*import com.example.modtemplate.event.FishEventHandler; // sample_content
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents; // sample_content
import net.minecraft.server.level.ServerPlayer; // sample_content

public class FabricEventSubscriber {

	public static void registerEvents() {
		//? != 1.19.2 {
		// sample_content
		ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamage, damageTaken, blocked) -> { // sample_content
			if (entity instanceof ServerPlayer && damageTaken > 0) { // sample_content
				FishEventHandler.onPlayerHurt((ServerPlayer) entity); // sample_content
			} // sample_content
		}); // sample_content
		//?}
	}
}
*///?}
