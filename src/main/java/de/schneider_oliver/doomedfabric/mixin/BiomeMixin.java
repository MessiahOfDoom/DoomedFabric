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

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import de.schneider_oliver.doomedfabric.block.AutoOreRegistry;
import de.schneider_oliver.doomedfabric.block.AutoOreRegistry.FeatureWrapper;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;

@Mixin(Biome.class)
public class BiomeMixin {

	@Shadow
	private GenerationSettings generationSettings;
	
	@Inject(at = @At("TAIL"), method = "generateFeatureStep(Lnet/minecraft/world/gen/StructureAccessor;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/world/ChunkRegion;JLnet/minecraft/world/gen/ChunkRandom;Lnet/minecraft/util/math/BlockPos;)V")
	public void generateFeatureStep(StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, ChunkRegion region, long populationSeed, ChunkRandom random, BlockPos pos, CallbackInfo info) {
		List<List<Supplier<ConfiguredFeature<?, ?>>>> list = this.generationSettings.getFeatures();
		int j = list.size() >= 7 ? list.get(6).size() + 1 : 1;
		int k = 77;
		
		
		for(Iterator<FeatureWrapper> var23 = AutoOreRegistry.wrappers.iterator(); var23.hasNext(); ++k) {
            FeatureWrapper wrapper = var23.next();
            ConfiguredFeature<?, ?> configuredFeature = wrapper.feature;
            if(wrapper.biomePredicate.test((Biome)(Object)this)){
            	random.setDecoratorSeed(populationSeed, k, j);
            	j++;
                try {
                   configuredFeature.generate(region, chunkGenerator, random, pos);
                } catch (Exception var22) {
                   CrashReport crashReport2 = CrashReport.create(var22, "Feature placement");
                   crashReport2.addElement("Feature").add("Id", (Object)Registry.FEATURE.getId(configuredFeature.feature)).add("Config", (Object)configuredFeature.config).add("Description", () -> {
                      return configuredFeature.feature.toString();
                   });
                   throw new CrashException(crashReport2);
                }
            }
            
         }
		
		
	}

}
