package net.kuko.neofish;


import net.kuko.neofish.client.highlight.Highlight;
import net.kuko.neofish.client.highlight.HighlighterRenderer;
import net.kuko.neofish.data.DataEntry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

@Mod(value = NeoFish.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = NeoFish.MOD_ID, value = Dist.CLIENT)
public class NeoFishClient {



    @SubscribeEvent // on the game event bus only on the physical client
    public static void onClientTick(ClientTickEvent.Post event) {
    }


    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
        Highlight.render(event);
    }

    public static final ResourceKey<Registry<DataEntry>> DATA_ENTRY_KEY =
            ResourceKey.createRegistryKey(
                    // JSONs must be put into: data/<namespace>/<Fisch.MOD_ID>/data/*
                    ResourceLocation.fromNamespaceAndPath(NeoFish.MOD_ID, "data")
            );

    @SubscribeEvent
    public static void addRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(
                DATA_ENTRY_KEY,
                DataEntry.CODEC,
                DataEntry.CODEC,
                builder -> builder.maxId(256)
        );
    }

    // Access wiith:
    /*
        //If you have level access:
        Registry<DataEntry> reg = level.registryAccess().registryOrThrow(FischClient.DATA_ENTRY_KEY);
        //If you want to loop over it:
        for (DataEntry entry : reg) {
        ...
        }

     */

}
