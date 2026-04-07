package net.kuko.neofish.platform.neoforge.datagen;


import net.kuko.neofish.ModNeoFish;
import net.kuko.neofish.registries.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
	public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, ModNeoFish.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		basicItem(ModItems.MARKER.get());
//		basicItem(ModItems.RAW_BISMUTH.get());
//
//		basicItem(ModItems.RADISH.get());
//		basicItem(ModItems.STARLIGHT_ASHES.get());
//		basicItem(ModItems.FROSTFIRE_ICE.get());
//		basicItem(ModItems.CHISEL.get());
	}
}
