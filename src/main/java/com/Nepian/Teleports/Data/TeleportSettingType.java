package com.Nepian.Teleports.Data;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.Nepian.Teleports.Configuration.Config;

public enum TeleportSettingType {

	TELEPORT_CREATE,
	TELEPORT_DIRECTION_CHANGE,
	
	OTHER;
	
	public static TeleportSettingType getType(ItemStack item) {
		Material itemMaterial = item.getType();
		
		if (itemMaterial == Config.TELEPORT_CREATE_ITEM.getMaterial()) {
			return TELEPORT_CREATE;
		}
		
		if (itemMaterial == Config.TELEPORT_DIRECTION_CHANGE_ITEM.getMaterial()) {
			return TELEPORT_DIRECTION_CHANGE;
		}
		
		return OTHER;
	}
	
	public static boolean isCreateItem(ItemStack item) {
		return getType(item) == TELEPORT_CREATE;
	}
	
	public static boolean isDirectionChangeItem(ItemStack item) {
		return getType(item) == TELEPORT_DIRECTION_CHANGE;
	}
}
