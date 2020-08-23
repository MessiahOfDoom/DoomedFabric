package de.schneider_oliver.doomedfabric.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import de.schneider_oliver.doomedfabric.DoomedFabric;
import de.schneider_oliver.doomedfabric.config.module.BaseModule;
import de.schneider_oliver.doomedfabric.config.module.RecipeUnlockerConfig;
import net.fabricmc.loader.api.FabricLoader;

public class Config {

	public static HashMap<String, BaseModule> modulesByName = new HashMap<>();
	
	public static RecipeUnlockerConfig recipeUnlocker = addModule(new RecipeUnlockerConfig());
	
	private static boolean read = false;
	
	public static void readConfigs() {
		
		File configDir = FabricLoader.getInstance().getConfigDir().resolve("messiahsmods").toFile();
		if (!configDir.exists()) {
			if (!configDir.mkdir()) {
				DoomedFabric.LOGGER.warn("[DoomedFabric] Could not create configuration directory: " + configDir.getAbsolutePath());
			}
		}
		File configDir2 = new File(configDir, "doomedfabric");
		if (!configDir2.exists()) {
			if (!configDir2.mkdir()) {
				DoomedFabric.LOGGER.warn("[DoomedFabric] Could not create module directory: " + configDir2.getAbsolutePath());
			}
		}
		modulesByName.forEach((k, v) -> {
			File configFile = v.getConfigFile(configDir2);
			Properties properties = new Properties();
			DoomedFabric.LOGGER.warn(v);
			if (configFile.exists()) {
				try (FileInputStream stream = new FileInputStream(configFile)) {
					properties.load(stream);
				} catch (IOException e) {
					DoomedFabric.LOGGER.warn("[DoomedFabric] Could not read property file '" + configFile.getAbsolutePath() + "'", e);
				}
			}
			properties = v.parseConfig(properties);
			try (FileOutputStream stream = new FileOutputStream(configFile)){
				properties.store(stream, "[DoomedFabric Module: '" + k + "']" );
			}catch (Exception e) {
				DoomedFabric.LOGGER.warn("[DoomedFabric] Could not save to property file '" + configFile.getAbsolutePath() + "'", e);
			}
		});

		read = true;
	}
	
	public static void tryReadFirst() {
		if(!read) readConfigs();
	}
	
	
	public static <T extends BaseModule> T addModule(T t) {
		modulesByName.put(t.getModuleName(), t);
		return t;
	}
	
	
	
}
