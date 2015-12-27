package com.Nepian.Teleports.Data;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.Nepian.Teleports.Configuration.Config;

public enum TeleporterSettingType {

	TELEPORTER_CREATE,
	TELEPORTER_CHANGE_DIRECTION,
	TELEPORTER_GET_INFO,
	
	OTHER;
	
	public static TeleporterSettingType getType(ItemStack item) {
		Material itemMaterial = item.getType();
		
		if (itemMaterial == Config.ITEM_TELEPORT_CREATE.getMaterial()) {
			return TELEPORTER_CREATE;
		}
		
		if (itemMaterial == Config.ITEM_TELEPORT_CHANGE_DIRECTION.getMaterial()) {
			return TELEPORTER_CHANGE_DIRECTION;
		}
		
		if (itemMaterial == Config.ITEM_TELEPORT_GET_INFO.getMaterial()) {
			return TELEPORTER_GET_INFO;
		}
		
		return OTHER;
	}
	
	public static boolean isTeleporterCreatingItem(ItemStack item) {
		return getType(item) == TELEPORTER_CREATE;
	}
	
	public static boolean isDirectionChangingItem(ItemStack item) {
		return getType(item) == TELEPORTER_CHANGE_DIRECTION;
	}
	
	public static boolean isInformationGettingItem(ItemStack item) {
		return getType(item) == TELEPORTER_GET_INFO;
	}
}
