package com.Nepian.Teleports;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import com.Nepian.Teleports.Configuration.Config;
import com.Nepian.Teleports.Configuration.Files;
import com.Nepian.Teleports.Configuration.Properties;
import com.Nepian.Teleports.Util.LocationUtil;

public class TeleportManager {
	public static enum Type { START, END, OTHER };
	
	private static final String PATH_STARTS = "Starts";
	private static final String PATH_ENDS   = "Ends";
	
	private static Main plugin = Main.getPlugin();
	private static File file = Files.FILE_WARPS;
	
	private static Map<Location, String> starts;
	private static Map<Location, String> ends;
	private static Map<String, Location> warps;
	
	static {
		starts = new HashMap<Location, String>();
		ends = new HashMap<Location, String>();
		warps = new HashMap<String, Location>();
	}
	
	/* Methods ----------------------------------------------------------------------------------*/
	
	public static void putStart(Block block, String name) {
		putStart(LocationUtil.convertSimpleLocation(block.getLocation()), name);
	}
	
	public static void putEnd(Block block, String name) {
		putEnd(LocationUtil.convertSimpleLocation(block.getLocation()), name);
	}
	
	public static boolean isStart(Block block) {
		return isStart(LocationUtil.convertSimpleLocation(block.getLocation()));
	}
	
	public static boolean isEnd(Block block) {
		return isEnd(LocationUtil.convertSimpleLocation(block.getLocation()));
	}
	
	public static boolean hasWarp(Block block) {
		return hasWarp(LocationUtil.convertSimpleLocation(block.getLocation()));
	}
	
	public static boolean alreadyExist(String name) {
		return warps.containsKey(name);
	}
	
	public static Location getWarp(Block block) {
		return getWarp(LocationUtil.convertSimpleLocation(block.getLocation()));
	}
	
	public static void removeStart(Block block) {
		removeStart(LocationUtil.convertSimpleLocation(block.getLocation()));
	}
	
	public static void removeEnd(Block block) {
		removeEnd(LocationUtil.convertSimpleLocation(block.getLocation()));
	}
	
	public static String getStartName(Block block) {
		return getStartName(LocationUtil.convertSimpleLocation(block.getLocation()));
	}
	
	public static String getEndName(Block block) {
		return getEndName(LocationUtil.convertSimpleLocation(block.getLocation()));
	}
	
	public static Type getType(Block block) {
		if (block.getType() == Properties.BLOCK_START) return Type.START;
		if (block.getType() == Properties.BLOCK_END)   return Type.END;
		return Type.OTHER;
	}
	
	public static boolean isRegisteringItem(ItemStack item) {
		if (item == null) return false;
		return item.getType() == Material.getMaterial(Config.WARP_REGISTERING_ITEM.getString());
	}
	
	public static Map<Location, String> getStarts() {
		return starts;
	}
	
	public static Map<Location, String> getEnds() {
		return ends;
	}
	
	/* Private Methods --------------------------------------------------------------------------*/
	
	private static String getStartName(Location location) {
		return starts.get(location);
	}
	
	private static String getEndName(Location location) {
		return ends.get(location);
	}
	
	private static void putStart(Location location, String name) {
		starts.put(location, name);
	}
	
	private static void putEnd(Location location, String name) {
		warps.put(name, location);
		ends.put(location, name);
	}
	
	private static boolean isStart(Location location) {
		return starts.containsKey(location);
	}
	
	private static boolean isEnd(Location location) {
		return ends.containsKey(location);
	}
	
	private static boolean hasWarp(Location location) {
		return alreadyExist(getStartName(location));
	}
	
	private static Location getWarp(Location location) {
		return warps.get(getStartName(location));
	}
	
	private static void removeStart(Location location) {
		starts.remove(location);
	}
	
	private static void removeEnd(Location location) {
		warps.remove(getEndName(location));
		ends.remove(location);
	}
	
	/* Load and Save Method ---------------------------------------------------------------------*/
	
	public static void save() {
		YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
		
		List<String> list = new ArrayList<String>();
		
		for (Location loc : starts.keySet()) {
			String stringLoc = LocationUtil.toStringSimpleLocation(loc);
			list.add(stringLoc + "," + starts.get(loc));
		}
		
		data.set(PATH_STARTS, list);
		
		list = new ArrayList<String>();
		
		for (Location loc : ends.keySet()) {
			String endLoc = LocationUtil.toStringSimpleLocation(loc);
			list.add(endLoc + "," + ends.get(loc));
		}
		
		data.set(PATH_ENDS, list);
		
		try {
			data.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
		
		List<?> list = data.getList(PATH_STARTS);
		
		for (Object tmp : list) {
			String[] s = ((String) tmp).split(",");
			Location loc = LocationUtil.getSimpleLocationFromString(plugin, s[0]);
			putStart(loc, s[1]);
		}
		
		list = data.getList(PATH_ENDS);
		
		for (Object tmp : list) {
			String[] s = ((String) tmp).split(",");
			Location loc = LocationUtil.getSimpleLocationFromString(plugin, s[0]);
			putEnd(loc, s[1]);
		}
	}
}
