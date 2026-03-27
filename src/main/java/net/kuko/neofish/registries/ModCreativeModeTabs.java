package net.kuko.neofish.registries;

import net.kuko.neofish.NeoFish;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB, NeoFish.MOD_ID);

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
