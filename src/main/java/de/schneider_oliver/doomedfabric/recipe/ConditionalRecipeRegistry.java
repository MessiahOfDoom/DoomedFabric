package de.schneider_oliver.doomedfabric.recipe;

import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.recipe.Recipe;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ConditionalRecipeRegistry {

	public static HashMap<Identifier, HashSet<RecipeCondition>> registeredConditions = new HashMap<>();
	
	public static void registerConditionForId(Identifier recipeID, RecipeCondition condition) {
		registeredConditions.compute(recipeID, (k, v) -> {
			if(v == null) v = new HashSet<>();
			v.add(condition);
			return v;
		});
	}
	
	
	public static interface RecipeCondition {
		
		public boolean canCraft(World world, ServerPlayerEntity player, Recipe<?> recipe);
		
	}
	
}
