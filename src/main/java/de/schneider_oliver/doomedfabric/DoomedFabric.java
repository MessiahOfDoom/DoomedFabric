package de.schneider_oliver.doomedfabric;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.schneider_oliver.doomedfabric.recipe.ConditionalRecipeRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class DoomedFabric implements ModInitializer {
	
	public static final Logger LOGGER = LogManager.getLogger("DoomedFabric");
	
	@Override
	public void onInitialize() {
		ConditionalRecipeRegistry.registerConditionForId(new Identifier("minecraft:anvil"), (a,b,c) -> {
			return !b.isCreative();
		});
	}
}
