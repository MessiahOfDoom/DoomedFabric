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
package de.schneider_oliver.doomedfabric.block;

import java.util.ArrayList;
import java.util.function.Predicate;

import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.decorator.ConfiguredDecorator;

public interface AutoOre {

	public RuleTest getSpawningRule();
	
	public BlockState getSpawningState();
	
	public int getMaxSize();
	
	public int getMaxHeight();
	
	public int getMinHeight();
	
	public int getRepeatsPerChunk();
	
	public RepeatMode getRepeatMode();
	
	public Predicate<Biome> getBiomePredicate();
	
	
	public Identifier getIdentifier();
	
	public default boolean hasExtraDecorators() {
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public default ArrayList<ConfiguredDecorator<?>> getExtraDecorators() {
		return new ArrayList<>();
	}
	
	
	public static enum RepeatMode {
		
		REPEAT_STATIC,
		REPEAT_RANDOM
		
	}
	
}
