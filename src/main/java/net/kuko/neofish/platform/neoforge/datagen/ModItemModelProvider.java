package net.kuko.neofish.platform.neoforge.datagen;


import net.kuko.neofish.ModNeoFish;
import net.kuko.neofish.registries.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, ModNeoFish.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		item(ModItems.MARKER.get(), BuiltInRegistries.ITEM.getKey(Items.STICK));
	}


	private ItemModelBuilder item(Item item, ResourceLocation texture) {
		return getBuilder(item.toString())
				.parent(new ModelFile.UncheckedModelFile("item/generated"))
				.texture("layer0", ResourceLocation.fromNamespaceAndPath(texture.getNamespace(), "item/" + texture.getPath()));
	}
}
