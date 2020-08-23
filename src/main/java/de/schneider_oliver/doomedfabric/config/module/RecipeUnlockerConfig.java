package de.schneider_oliver.doomedfabric.config.module;

import de.schneider_oliver.doomedfabric.config.Config;
import de.schneider_oliver.doomedfabric.config.values.BooleanConfigValue;

public class RecipeUnlockerConfig extends BaseModule{

	public BooleanConfigValue fixesEnabled = addConfigValue(new BooleanConfigValue("recipe-unlocker-overrides-enabled", true));
	
	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
		return ((RecipeUnlockerConfig)Config.modulesByName.getOrDefault(getModuleName(), new RecipeUnlockerConfig())).fixesEnabled.get();
	}

	@Override
	public String getModuleName() {
		return "recipe-unlocker";
	}

}
