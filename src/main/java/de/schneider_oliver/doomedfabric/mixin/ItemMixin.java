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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import de.schneider_oliver.doomedfabric.item.ItemSelfRemainder;
import net.minecraft.item.Item;

@Mixin(Item.class)
public class ItemMixin {

	@Inject(at = @At("HEAD"), method = "getRecipeRemainder()Lnet/minecraft/item/Item;", cancellable = true)
	public void getRecipeRemainder(CallbackInfoReturnable<Item> info) {
		if(this instanceof ItemSelfRemainder) {
			info.setReturnValue(((ItemSelfRemainder)(Object)this).getActualRemainder());
		}
	}
	
	@Inject(at = @At("HEAD"), method = "hasRecipeRemainder()Z", cancellable = true)
	public void hasRecipeRemainder(CallbackInfoReturnable<Boolean> info) {
		if(this instanceof ItemSelfRemainder) info.setReturnValue(true);
	}

}
