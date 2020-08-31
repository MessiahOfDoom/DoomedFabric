package de.schneider_oliver.doomedfabric.config.module;

import de.schneider_oliver.doomedfabric.config.Config;
import de.schneider_oliver.doomedfabric.config.values.BooleanConfigValue;

public class RecipeUnlockerModule extends BaseModule{

	public BooleanConfigValue fixesEnabled = addConfigValue(new BooleanConfigValue("recipe-unlocker-overrides-enabled", true));
	
	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		return Config.<RecipeUnlockerModule>getCachedModuleByName(getModuleName()).fixesEnabled.get();
	}

	@Override
	public String getModuleName() {
		return "recipe-unlocker";
	}

	@Override
	public String getModuleDirName() {
		return "doomedfabric";
	}

}
