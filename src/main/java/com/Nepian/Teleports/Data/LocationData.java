package com.Nepian.Teleports.Data;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import com.Nepian.Teleports.Main;
import com.Nepian.Teleports.Util.LocationUtil;

public class LocationData implements ConfigurationSerializable {
	private static final Main plugin = Main.getPlugin();
	
	public static final String PATH_LOCATION;
	
	static {
		PATH_LOCATION = "Location";
	}
	
	private Location location;
	
	public LocationData(Location location) {
		this.location = location;
	}
	
	public Location getLocation() {
		return this.location;
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> data = new HashMap<String, Object>();
		
		return data;
	}
	
	public static LocationData deserialize(Map<String, Object> data) {
		String stringLoc = (String) data.get(PATH_LOCATION);
		Location location = LocationUtil.getSimpleLocationFromString(plugin, stringLoc);
		
		return new LocationData(location);
	}
}
