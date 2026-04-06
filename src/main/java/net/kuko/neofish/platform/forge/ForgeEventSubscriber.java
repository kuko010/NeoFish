package net.kuko.neofish.platform.forge;

//? forge {

/*import com.example.modtemplate.event.FishEventHandler; // sample_content
import net.minecraft.server.level.ServerPlayer; // sample_content
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ForgeEventSubscriber {

	@SubscribeEvent // sample_content
	public static void onPlayerDamage(LivingDamageEvent event) { // sample_content
		if (event.getEntity() instanceof ServerPlayer player && event.getAmount() > 0) { // sample_content
			FishEventHandler.onPlayerHurt(player); // sample_content
		} // sample_content
	} // sample_content
}
*///?}
