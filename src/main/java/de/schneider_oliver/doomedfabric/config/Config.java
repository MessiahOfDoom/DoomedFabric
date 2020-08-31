package de.schneider_oliver.doomedfabric.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import de.schneider_oliver.doomedfabric.DoomedFabric;
import de.schneider_oliver.doomedfabric.config.module.BaseModule;
import de.schneider_oliver.doomedfabric.config.module.RecipeUnlockerModule;
import net.fabricmc.loader.api.FabricLoader;

public class Config {

	public static HashMap<String, BaseModule> modules = new HashMap<>();
	public static HashMap<String, Boolean> cachedModules = new HashMap<>();
	
	private static boolean read = false;
	
	private static File configDir = FabricLoader.getInstance().getConfigDir().resolve("messiahsmods").toFile();

	public static void readAllConfigs() {
		if (!configDir.exists()) {
			if (!configDir.mkdir()) {
				DoomedFabric.LOGGER.warn("[DoomedFabric] Could not create configuration directory: " + configDir.getAbsolutePath());
			}
		}
		modules.forEach((k, v) -> {
			v = readConfig(configDir, k, v);
		});

		read = true;
	}
	
	public static void cacheConfigsOnStartup() {
		if(!read) readAllConfigs();
	}
	
	private static <T extends BaseModule> T readConfig(File configDir, String k, T v) {
		File configDir2 = new File(configDir, v.getModuleDirName());
		if (!configDir2.exists()) {
			if (!configDir2.mkdir()) {
				DoomedFabric.LOGGER.warn("[DoomedFabric] Could not create module directory: " + configDir2.getAbsolutePath());
			}
		}
		File configFile = v.getConfigFile(configDir2);
		Properties properties = new Properties();
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
		cachedModules.put(k, true);
		return v;
	}
	
	public static <T extends BaseModule> T addModule(T t) {
		modules.put(t.getModuleName(), t);
		cachedModules.put(t.getModuleName(), false);
		return t;
	}
	
	public static <T extends BaseModule> T getCachedModuleByName(String moduleName) {
		return getCachedModuleByName(moduleName, false);
	}
	
	
	@SuppressWarnings("unchecked")
	public static <T extends BaseModule> T getCachedModuleByName(String moduleName, boolean forceRecache) {
		try {
			if(cachedModules.getOrDefault(moduleName, false) && !forceRecache && modules.containsKey(moduleName)) {
				return (T) modules.get(moduleName);
			}
			if(modules.containsKey(moduleName)) {
				return (T) readConfig(configDir, moduleName, modules.get(moduleName));
			}
		}catch (ClassCastException e) {
			DoomedFabric.LOGGER.warn("[DoomedFabric] Tried to cast a Module to the wrong subtype: '" + moduleName + "'", e);
			return null;
		}
		DoomedFabric.LOGGER.warn("[DoomedFabric] Tried to read an unregistered Config Module: '" + moduleName + "'");
		return null;
		
	}
	
	static{
		addModule(new RecipeUnlockerModule());
	}
	
	
}
