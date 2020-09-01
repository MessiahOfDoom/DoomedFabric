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
