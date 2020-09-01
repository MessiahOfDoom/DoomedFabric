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

import java.util.Locale;

import net.fabricmc.fabric.api.util.TriState;

public class BooleanConfigValue extends ConfigValue<Boolean>{

	
	private boolean defaultValue = false;
	
	public BooleanConfigValue(String propertyNameIn, boolean defaultValueIn) {
		super(propertyNameIn);
		defaultValue = defaultValueIn;
	}
	
	@Override
	public Boolean get() {
		return asBoolean(value, defaultValue);
	}
	
	protected static TriState asTriState(String property) {
		if (property == null || property.isEmpty()) {
			return TriState.DEFAULT;
		} else {
			switch (property.toLowerCase(Locale.ROOT)) {
			case "true":
				return TriState.TRUE;
			case "false":
				return TriState.FALSE;
			case "auto":
			default:
				return TriState.DEFAULT;
			}
		}
	}
	
	protected static boolean asBoolean(String property, boolean defValue) {
		switch (asTriState(property)) {
		case TRUE:
			return true;
		case FALSE:
			return false;
		default:
			return defValue;
		}
	}

	@Override
	public String getDefaultValue() {
		return Boolean.toString(defaultValue);
	}

}
