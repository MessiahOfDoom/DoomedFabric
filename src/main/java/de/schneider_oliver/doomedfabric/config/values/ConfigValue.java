/*******************************************************************************
 * Copyright (c) 2020 Oliver Schneider
 *
 * This program and the accompanying materials
 * are made available under the terms of the GNU General Public License version 3 (GPLv3)
 * which accompanies this distribution, and is available at
 * https://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * SPDX-License-Identifier: GPL-3.0-only
 *******************************************************************************/
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
