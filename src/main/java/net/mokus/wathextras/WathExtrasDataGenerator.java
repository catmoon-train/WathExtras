package net.mokus.wathextras;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.mokus.wathextras.datagen.ModBlockTagProvider;
import net.mokus.wathextras.datagen.ModItemTagProvider;
import net.mokus.wathextras.datagen.ModLanguageProvider;
import net.mokus.wathextras.datagen.ModModelProvider;

public class WathExtrasDataGenerator implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModLanguageProvider::new);
	}

}
