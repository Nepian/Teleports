package com.Nepian.Teleports.Data;

import org.bukkit.inventory.ItemStack;

public enum TeleportSettingType {

	CREATE,
	
	OTHER;
	
	public static TeleportSettingType getType(ItemStack item) {
		switch(item.getType()) {
		case WATCH:
			return CREATE;
		default:
			return OTHER;
		}
	}
	
	public static boolean isCreateItem(ItemStack item) {
		return getType(item) == CREATE;
	}
}
