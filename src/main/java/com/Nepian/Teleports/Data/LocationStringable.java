package com.Nepian.Teleports.Data;

import org.bukkit.Location;
import org.bukkit.World;

import com.Nepian.Teleports.Main;

public class LocationStringable {
	private static final Main plugin = Main.getPlugin();
	
	public static final String SPLIT = "__";
	public static final String PATH_LOCATION = "location";
	
	private Location location;
	
	/* Constructor ------------------------------------------------------------------------------*/

	public LocationStringable(Location location) {
		this.location = location;
	}
	
	public LocationStringable(String string) {
		this(toLocation(string));
	}
	
	/* Method -----------------------------------------------------------------------------------*/
	
	public static Location toLocation(String string) {
		String[] data = string.split(SPLIT);
		
		World world = plugin.getServer().getWorld(data[0]);
		double x = Double.valueOf(data[1]);
		double y = Double.valueOf(data[2]);
		double z = Double.valueOf(data[3]);
		float yaw = Float.valueOf(data[4]);
		float pitch = Float.valueOf(data[5]);
		
		return new Location(world, x, y, z, yaw, pitch);
	}
	
	public static String toString(Location location) {
		StringBuilder data = new StringBuilder("");
		
		World world = location.getWorld();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		float yaw = location.getYaw();
		float pitch = location.getPitch();
		
		data.append(world.getName()).append(SPLIT);
		data.append(x).append(SPLIT);
		data.append(y).append(SPLIT);
		data.append(z).append(SPLIT);
		data.append(yaw).append(SPLIT);
		data.append(pitch);
		
		return data.toString();
	}
	
	@Override
	public String toString() {
		return toString(location);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null) return false;
		
		if (obj.getClass() == Location.class) {
			Location loc = (Location) obj;
			if (loc.equals(this.location)) {
				return true;
			}
			return false;
		}
		
		if (obj.getClass() == LocationStringable.class) {
			LocationStringable ls = (LocationStringable) obj;
			Location loc = ls.getLocation();
			if (loc.equals(this.location)) {
				return true;
			}
			return false;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.location.hashCode();
	}
	
	/* Getter -----------------------------------------------------------------------------------*/
	
	public Location getLocation() {
		return this.location;
	}
}
