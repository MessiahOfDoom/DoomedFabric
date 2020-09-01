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
