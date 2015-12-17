package com.Nepian.Teleports.Data;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.Nepian.Teleports.Configuration.Config;

public enum TeleportSettingType {

	TELEPORT_CREATE,
	TELEPORT_CHANGE_DIRECTION,
	TELEPORT_GET_INFO,
	
	OTHER;
	
	public static TeleportSettingType getType(ItemStack item) {
		Material itemMaterial = item.getType();
		
		if (itemMaterial == Config.ITEM_TELEPORT_CREATE.getMaterial()) {
			return TELEPORT_CREATE;
		}
		
		if (itemMaterial == Config.ITEM_TELEPORT_CHANGE_DIRECTION.getMaterial()) {
			return TELEPORT_CHANGE_DIRECTION;
		}
		
		if (itemMaterial == Config.ITEM_TELEPORT_GET_INFO.getMaterial()) {
			return TELEPORT_GET_INFO;
		}
		
		return OTHER;
	}
	
	public static boolean isCreateItem(ItemStack item) {
		return getType(item) == TELEPORT_CREATE;
	}
	
	public static boolean isChangeDirectionItem(ItemStack item) {
		return getType(item) == TELEPORT_CHANGE_DIRECTION;
	}
	
	public static boolean isGetInfoItem(ItemStack item) {
		return getType(item) == TELEPORT_GET_INFO;
	}
}
