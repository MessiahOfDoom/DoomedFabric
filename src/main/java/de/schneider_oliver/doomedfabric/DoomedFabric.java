package de.schneider_oliver.doomedfabric;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.schneider_oliver.doomedfabric.config.Config;
import net.fabricmc.api.ModInitializer;

public class DoomedFabric implements ModInitializer {
	
	public static final Logger LOGGER = LogManager.getLogger("DoomedFabric");
	
	@Override
	public void onInitialize() {
		Config.tryReadFirst();
	}
}
