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

import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.feature.OreFeatureConfig.Rules;

public class AutoOreBlock extends Block implements AutoOre{

	private RuleTest spawningRule = Rules.BASE_STONE_OVERWORLD;
	private int maxSize = 8, minHeight = 0, maxHeight = 64, repeats = 16;
	private RepeatMode mode = RepeatMode.REPEAT_STATIC;
	private Predicate<Biome> predicate = (b) -> {
		return b.getCategory() != Category.THEEND && b.getCategory() != Category.NETHER;
	};
	private Identifier id;
	
	public AutoOreBlock(Settings settings, Identifier id) {
		super(settings);
		this.id = id;
	}

	@Override
	public RuleTest getSpawningRule() {
		return spawningRule;
	}

	@Override
	public BlockState getSpawningState() {
		return getDefaultState();
	}

	@Override
	public int getMaxSize() {
		return maxSize;
	}

	@Override
	public int getMaxHeight() {
		return maxHeight;
	}

	@Override
	public int getMinHeight() {
		return minHeight;
	}

	@Override
	public int getRepeatsPerChunk() {
		return repeats;
	}

	@Override
	public RepeatMode getRepeatMode() {
		return mode;
	}

	@Override
	public Predicate<Biome> getBiomePredicate() {
		return predicate;
	}

	public AutoOreBlock spawningRule(RuleTest spawningRule) {
		this.spawningRule = spawningRule;
		return this;
	}
	
	public AutoOreBlock maxSize(int maxSize) {
		this.maxSize = maxSize;
		return this;
	}
	
	public AutoOreBlock maxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
		return this;
	}
	
	public AutoOreBlock minHeight(int minHeight) {
		this.minHeight = minHeight;
		return this;
	}
	
	public AutoOreBlock repeats(int repeats) {
		this.repeats = repeats;
		return this;
	}
	
	public AutoOreBlock repeatMode(RepeatMode mode) {
		this.mode = mode;
		return this;
	}
	
	public AutoOreBlock biomePredicate(Predicate<Biome> predicate) {
		this.predicate = predicate;
		return this;
	}

	@Override
	public Identifier getIdentifier() {
		return id;
	}
	
}
