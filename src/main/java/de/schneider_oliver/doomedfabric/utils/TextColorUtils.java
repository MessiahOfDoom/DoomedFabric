package de.schneider_oliver.doomedfabric.utils;

import java.util.function.UnaryOperator;

import net.minecraft.text.Style;
import net.minecraft.text.TextColor;

public class TextColorUtils implements UnaryOperator<Style>{

	private TextColor color;
	
	private TextColorUtils(TextColor c) {
		this.color = c;
	}
	
	
	public static TextColorUtils of(int rgb) {
		return new TextColorUtils(TextColor.fromRgb(rgb));
	}

	@Override
	public Style apply(Style t) {
		return t.withColor(this.color);
	}

	
	
	
}
