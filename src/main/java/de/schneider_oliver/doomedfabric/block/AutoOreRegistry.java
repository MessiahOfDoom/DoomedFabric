package de.schneider_oliver.doomedfabric.block;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.apache.logging.log4j.Level;

import de.schneider_oliver.doomedfabric.DoomedFabric;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.decorator.ConfiguredDecorator;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class AutoOreRegistry {
	
	public static ArrayList<FeatureWrapper> wrappers = new ArrayList<FeatureWrapper>();
	
	
	public static void registerOre(AutoOre ore) {
		ConfiguredFeature<?, ?> feature = Feature.ORE.configure(new OreFeatureConfig(ore.getSpawningRule(), ore.getSpawningState(), ore.getMaxSize()))
				   .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(ore.getMinHeight(), 0, ore.getMaxHeight() - ore.getMinHeight())))
				   .spreadHorizontally();
		if(ore.getRepeatMode() == AutoOre.RepeatMode.REPEAT_STATIC) {
			feature = feature.repeat(ore.getRepeatsPerChunk());
		}else {
			feature = feature.repeatRandomly(ore.getRepeatsPerChunk());
		}
		if(ore.hasExtraDecorators()) {
			for(ConfiguredDecorator<?> d: ore.getExtraDecorators()) {
				feature = feature.decorate(d);
			}
		}
		final FeatureWrapper wrapper = new FeatureWrapper(feature, ore);
		DoomedFabric.LOGGER.log(Level.INFO, "Registering ore: " + ore.getSpawningState().getBlock().getTranslationKey());
		wrappers.add(wrapper);
	}
	

	
	public static final class FeatureWrapper {
		public final ConfiguredFeature<?, ?> feature;
		public final Predicate<Biome> biomePredicate;
		public FeatureWrapper(ConfiguredFeature<?, ?> feature, AutoOre ore) {
			this.feature = feature;
			biomePredicate = ore.getBiomePredicate();
		}
	}

}
