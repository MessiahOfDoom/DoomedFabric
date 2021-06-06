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
package de.schneider_oliver.doomedfabric;

import java.io.File;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.schneider_oliver.doomedfabric.config.Config;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class DoomedFabric implements ModInitializer {
	
	public static final Logger LOGGER = LogManager.getLogger("DoomedFabric");
	public static File CacheDir;
	
	
	@Override
	public void onInitialize() {
		Config.cacheConfigsOnStartup();
		CacheDir = Paths.get(FabricLoader.getInstance().getGameDir().toString(), "doomedfabric").toFile();
		CacheDir.mkdirs();
	}
}
