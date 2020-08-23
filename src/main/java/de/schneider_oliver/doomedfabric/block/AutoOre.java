package de.schneider_oliver.doomedfabric.block;

import java.util.ArrayList;
import java.util.function.Predicate;

import net.minecraft.block.BlockState;
import net.minecraft.structure.rule.RuleTest;
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
