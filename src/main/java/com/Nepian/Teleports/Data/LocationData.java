package com.Nepian.Teleports.Data;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

public class LocationData {
	private Location location;
	private OfflinePlayer owner;
	
	public LocationData(Location location, OfflinePlayer owner) {
		this.location = location;
		this.owner = owner;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public OfflinePlayer getOwner() {
		return this.owner;
	}
}
