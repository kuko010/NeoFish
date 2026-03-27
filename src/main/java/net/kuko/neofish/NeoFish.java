package net.kuko.neofish;

/*? if neoforge && 1.21.1 {*/
import net.kuko.neofish.registries.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.commons.io.input.ReaderInputStream;
/*?}*/
/*? if neoforge && 26.1 {*/

/*?}*/


/*? if !forgeLike {*/
/*public class NeoFish {*/
/*?}*/

/*? if neoforge {*/
@Mod(NeoFish.MOD_ID)
public class NeoFish {/*?}*/
    public static final String MOD_ID = "neofish";

    // mappings-agonistc, works on unmapped (26.1 unobfscuated) and on mojmaps (below 26.1)
    public static /*? if !26.1 {*/ ResourceLocation /*? } elif 26.1 {*/ /* Identifier*/  /*?}*/ id(String path) {
        return /*? if !26.1 {*/ ResourceLocation /*? } elif 26.1 {*/ /* Identifier*/  /*?}*/.fromNamespaceAndPath(MOD_ID, path);
    }



    /*? if neoforge {*/
    public NeoFish(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.register(modEventBus);
    }
    /*?}*/
}
