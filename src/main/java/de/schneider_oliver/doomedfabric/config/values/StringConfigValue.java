package de.schneider_oliver.doomedfabric.config.values;

public class StringConfigValue extends ConfigValue<String>{

	private String defaultValue;
	
	public StringConfigValue(String propertyNameIn, String defaultValueIn) {
		super(propertyNameIn);
		defaultValue = defaultValueIn;
	}

	@Override
	public String get() {
		return value;
	}

	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

}
