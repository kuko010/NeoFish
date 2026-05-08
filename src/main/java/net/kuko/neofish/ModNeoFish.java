package net.kuko.neofish;

import net.kuko.neofish.platform.Platform;

import net.kuko.neofish.registries.ModItems;
import net.mcexpanded.fancytabsections.FancyTabSections;
import net.mcexpanded.fancytabsections.creativetab.SectionColored;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
/*import com.example.modtemplate.platform.fabric.FabricPlatform;
*///?} neoforge {
import net.kuko.neofish.platform.neoforge.NeoforgePlatform;
import java.util.List;
		//?} forge {
/*import com.example.modtemplate.platform.forge.ForgePlatform;
*///?} 

@SuppressWarnings("LoggingSimilarMessage")
public class ModNeoFish {

	public static final String MOD_ID = /*$ mod_id*/ "neofish";
	public static final String MOD_VERSION = /*$ mod_version*/ "0.1.0-mc";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "NeoFish";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static ResourceLocation rl(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}

	public static void onInitialize() {
		LOGGER.info("Initializing {} on {}", MOD_ID, ModNeoFish.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
		/*
		        FancyTabSections.addSection(rl("shiny_things"),
                SectionTextured.of(
                        rl("shiny"),
                        Component.literal(""),
                        0xFFFFFFFF,
                        List.of(
                                Items.DIAMOND,
                                Items.GOLDEN_HOE,
                                Items.AMETHYST_SHARD
                        )
                )
        );
		 */
	}

	public static void onInitializeClient() {
		LOGGER.info("Initializing {} Client on {}", MOD_ID, ModNeoFish.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}

	static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		/*return new FabricPlatform();
		*///?} neoforge {
		return new NeoforgePlatform();
		 //?} forge {
		/*return new ForgePlatform();
		*///?}
	}
}
