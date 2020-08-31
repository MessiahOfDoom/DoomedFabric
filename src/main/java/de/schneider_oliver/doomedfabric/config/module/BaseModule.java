package de.schneider_oliver.doomedfabric.config.module;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import de.schneider_oliver.doomedfabric.config.Config;
import de.schneider_oliver.doomedfabric.config.values.ConfigValue;

@SuppressWarnings("rawtypes")
public abstract class BaseModule implements IMixinConfigPlugin{

	public abstract String getModuleName();
	public abstract String getModuleDirName();
	public List<ConfigValue> values = new ArrayList<>();
	
	
	public File getConfigFile(File configDir) {
		return new File(configDir, getModuleName() + ".properties");
	}
	
	public Properties parseConfig(Properties properties) {
		for(ConfigValue value : values) {
			properties = value.parse(properties);
		}
		return properties;
	}
	
	
	public <T extends ConfigValue> T addConfigValue(T value){
		values.add(value);
		return value;
	}

	@Override
	public void onLoad(String mixinPackage) {
		Config.getCachedModuleByName(getModuleName());
	}

	@Override
	public String getRefMapperConfig() { return null; }

	@Override
	public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

	@Override
	public List<String> getMixins() { return null; }

	@Override
	public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

	@Override
	public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
	
	@Override
	public boolean shouldApplyMixin(String targetClassName, String mixinClassName) { return true; }
}