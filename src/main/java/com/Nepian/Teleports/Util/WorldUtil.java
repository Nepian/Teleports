package com.Nepian.Teleports.Util;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldUtil {

	public static World getWorld(JavaPlugin plugin, String name) {
		return plugin.getServer().getWorld(name);
	}
}
