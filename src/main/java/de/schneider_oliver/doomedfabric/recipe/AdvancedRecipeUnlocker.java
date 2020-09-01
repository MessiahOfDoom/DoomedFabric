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
package de.schneider_oliver.doomedfabric.recipe;

import de.schneider_oliver.doomedfabric.recipe.ConditionalRecipeRegistry.RecipeCondition;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

public interface AdvancedRecipeUnlocker {

	
	public default boolean shouldCraftRecipe(World world, ServerPlayerEntity player, Recipe<?> recipe) {

		boolean out = true;
		if(ConditionalRecipeRegistry.registeredConditions.keySet().contains(recipe.getId())) {
			for(RecipeCondition r: ConditionalRecipeRegistry.registeredConditions.get(recipe.getId())) {
				if(!r.canCraft(world, player, recipe)) {
					out = false;
					break;
				}
			}
		}
		return out == true ? ((RecipeUnlocker)(Object)this).shouldCraftRecipe(world, player, recipe) : false;
	}
	
}
