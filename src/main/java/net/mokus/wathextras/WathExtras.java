package net.mokus.wathextras;

import net.fabricmc.api.ModInitializer;
import net.mokus.wathextras.block.ModBlocks;
import net.mokus.wathextras.item.ModItems;
import net.mokus.wathextras.util.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WathExtras implements ModInitializer {
	public static final String MOD_ID = "wathextras";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.init();
		ModBlocks.init();
		ModSounds.init();
	}
}