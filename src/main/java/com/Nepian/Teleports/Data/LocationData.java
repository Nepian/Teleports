package com.Nepian.Teleports.Data;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import com.Nepian.Teleports.Main;
import com.Nepian.Teleports.Util.LocationUtil;

public class LocationData implements ConfigurationSerializable {
	private static final Main plugin = Main.getPlugin();
	
	public static final String PATH_NAME;
	public static final String PATH_LOCATION;
	
	static {
		PATH_NAME     = "Name";
		PATH_LOCATION = "Location";
	}
	
	private String name;
	private Location location;
	
	public LocationData(String name, Location location) {
		this.name = name;
		this.location = location;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Location getLocation() {
		return this.location;
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> data = new HashMap<String, Object>();
		
		String stringLoc = LocationUtil.toStringSimpleLocation(this.location);
		
		data.put(PATH_NAME, this.name);
		data.put(PATH_LOCATION, stringLoc);
		
		return data;
	}
	
	public static LocationData deserialize(Map<String, Object> data) {
		String name = (String) data.get(PATH_NAME);
		String stringLoc = (String) data.get(PATH_LOCATION);
		Location location = LocationUtil.getSimpleLocationFromString(plugin, stringLoc);
		
		return new LocationData(name, location);
	}
}
