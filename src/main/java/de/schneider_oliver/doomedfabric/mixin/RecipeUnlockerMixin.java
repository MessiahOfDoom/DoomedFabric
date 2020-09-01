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
package de.schneider_oliver.doomedfabric.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import de.schneider_oliver.doomedfabric.recipe.ConditionalRecipeRegistry;
import de.schneider_oliver.doomedfabric.recipe.ConditionalRecipeRegistry.RecipeCondition;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

@Mixin({RecipeUnlocker.class})
public interface RecipeUnlockerMixin {
	
	@Shadow
	void setLastRecipe(Recipe<?> recipe);
	
//	@Inject(at = @At("HEAD"), method = "shouldCraftRecipe(Lnet/minecraft/world/World;Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/minecraft/recipe/Recipe;)Z", cancellable = true)
	default public boolean shouldCraftRecipe(World world, ServerPlayerEntity player, Recipe<?> recipe) {
		boolean out = true;
		if(ConditionalRecipeRegistry.registeredConditions.keySet().contains(recipe.getId())) {
			for(RecipeCondition r: ConditionalRecipeRegistry.registeredConditions.get(recipe.getId())) {
				if(!r.canCraft(world, player, recipe)) {
					out = false;
					break;
				}
			}
		}
		if(out) {
			if (!recipe.isIgnoredInRecipeBook() && world.getGameRules().getBoolean(GameRules.DO_LIMITED_CRAFTING) && !player.getRecipeBook().contains(recipe)) {
				return false;
			} else {
				this.setLastRecipe(recipe);
				return true;
			}
		}
		return false;
	}

}
