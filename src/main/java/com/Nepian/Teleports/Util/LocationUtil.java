package com.Nepian.Teleports.Util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class LocationUtil {
	private static final String LOCATION_PREFIX = "(Location)";

	/**
	 * 指定したLocationを簡単なLocationに変換する
	 * @param location
	 * @return world, x, y, z のみのLocationを返す
	 */
	public static Location convertSimpleLocation(Location location) {
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		return new Location(location.getWorld(), x, y, z);
	}
	
	/**
	 * 指定したLocationを簡単な文字列に変換する
	 * @param location
	 * @return world, x, y, z を文字列として繋げた文字列を返す
	 */
	public static String toStringSimpleLocation(Location location) {
		StringBuilder data = new StringBuilder(LOCATION_PREFIX);
		
		World world = location.getWorld();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		
		data.append(world.getName()).append(":");
		data.append(x).append(":");
		data.append(y).append(":");
		data.append(z);
		
		return data.toString();
	}
	
	/**
	 * 指定した文字列からLocationを取得する
	 * @param plugin
	 * @param string toStringSimpleLocationで変換された文字列
	 * @return 文字列から取得したLocationを返す
	 */
	public static Location getSimpleLocationFromString(JavaPlugin plugin, String string) {
		if (!string.startsWith(LOCATION_PREFIX)) {
			return null;
		}
		
		String[] strings = string.substring(LOCATION_PREFIX.length()).split(":");
		
		World world = WorldUtil.getWorld(plugin, strings[0]);
		double x = Double.valueOf(strings[1]);
		double y = Double.valueOf(strings[2]);
		double z = Double.valueOf(strings[3]);
		
		return new Location(world, x, y, z);
	}
}
