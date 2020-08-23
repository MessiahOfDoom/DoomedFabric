package de.schneider_oliver.doomedfabric.config.values;

import java.util.Properties;

public abstract class ConfigValue<T> {

	protected String value = "";
	protected String propertyName = "";
	
	public ConfigValue(String propertyNameIn){
		propertyName = propertyNameIn;
	}
	
	public abstract T get();
	
	public Properties parse(Properties properties) {
		value = (String)properties.computeIfAbsent(propertyName, a -> getDefaultValue());
		return properties;
	}
	
	public abstract String getDefaultValue();
}
