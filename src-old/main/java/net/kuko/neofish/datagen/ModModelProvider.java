package net.kuko.neofish.datagen;

/*?if  26.1 {*/

/*?} else {*/
import net.kuko.neofish.NeoFish;
import net.kuko.neofish.registries.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Objects;

import static net.minecraft.resources.ResourceLocation.DEFAULT_NAMESPACE;
/*?}*/

public class ModModelProvider extends ItemModelProvider {
    public ModModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, NeoFish.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        sharedRegisterModels();
    }

    @Override
    public String getName() {
        return "";
    }

    private void sharedRegisterModels() {
        bItem(ModItems.MARKER.get(),
                ResourceLocation.fromNamespaceAndPath(DEFAULT_NAMESPACE, "item/stick"));
    }


    private ItemModelBuilder bItem(Item item) { return basicItem(item); }
    private ItemModelBuilder bItem(Item item, ResourceLocation texture) {
        return withExistingParent(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)).getPath(), mcLoc("item/generated"))
                .texture("layer0", texture);
    }
}
