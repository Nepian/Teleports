package com.Nepian.Teleports.Util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackUtil {

	public static boolean isWatch(ItemStack item) {
		return item.getType() == Material.WATCH;
	}
	
	public static boolean hasDisplayName(ItemStack item) {
		return getDisplayName(item) != null;
	}
	
	public static String getDisplayName(ItemStack item) {
		return item.getItemMeta().getDisplayName();
	}
}
