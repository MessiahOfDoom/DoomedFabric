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

public class NumberConfigValue extends ConfigValue<Number>{

	private double defaultValue;
	private NumberType numType;
	
	public NumberConfigValue(String propertyNameIn, double defaultValueIn, NumberType numTypeIn) {
		super(propertyNameIn);
		defaultValue = defaultValueIn;
		numType = numTypeIn;
	}

	@Override
	public Number get() {
		switch(numType) {
		case INT:
			return asInt(value, (int)defaultValue);
		case FLOAT:
			return asFloat(value, (float)defaultValue);
		case DOUBLE:
			return asDouble(value, defaultValue);
		}
		return defaultValue;
	}

	@Override
	public String getDefaultValue() {
		switch(numType) {
		case INT:
			return String.format("%d", (int)defaultValue);
		case FLOAT:
			return String.format("%1.2f", (float)defaultValue);
		case DOUBLE:
			return String.format("%1.3f", defaultValue);
		}
		return "";
	}
	
	
	protected static double asDouble(String property, double defValue) {
		try {
			return Double.parseDouble(property);
		}catch(NumberFormatException | NullPointerException e) {
			return defValue;
		}
	}
	
	protected static float asFloat(String property, float defValue) {
		try {
			return Float.parseFloat(property);
		}catch(NumberFormatException | NullPointerException e) {
			return defValue;
		}
	}
	
	protected static int asInt(String property, int defValue) {
		try {
			return Integer.parseInt(property);
		}catch(NumberFormatException | NullPointerException e) {
			return defValue;
		}
	}
	
	public enum NumberType{
		INT,
		FLOAT,
		DOUBLE;
	}

}
