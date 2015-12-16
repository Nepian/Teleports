package com.Nepian.Teleports.Data;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import com.Nepian.Teleports.Configuration.Files;
import com.Nepian.Teleports.Event.CreateTeleportEvent;

public class TeleportLocationData {
	private File file;
	private TeleportType type;
	private OfflinePlayer owner;
	private String name;
	private Location location;
	
	public TeleportLocationData(CreateTeleportEvent event) {
		this.type = event.getType();
		this.owner = event.getPlayer();
		this.name = event.getName();
		this.location = event.getLocation();
	}
	
	public TeleportLocationData(File file) {
		this.file = file;
		this.read();
	}
		
	public TeleportType getType() {
		return type;
	}
	
	public OfflinePlayer getOwner() {
		return owner;
	}
	
	public String getName() {
		return name;
	}
	
	public Location getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(type.toString()).append(": ");
		sb.append("Name:").append(name).append(", ");
		sb.append("Owner:").append(owner.getName()).append(", ");
		sb.append("Location:").append(LocationStringable.toString(location));
		return sb.toString();
	}
	
	/*-------------------------------------------------------------------------------------------*/
	///////////////////////////////////////////////////////////////////////////////////////////////
	/* Write and Read ---------------------------------------------------------------------------*/
	
	public static class Path {
		public static final String TYPE = "type";
		public static final String OWNER = "owner";
		public static final String NAME = "name";
		public static final String LOCATION = "location";
	}
	
	public void write() {
		if (file == null) {
			file = createFile(location);
		}
		
		YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
		
		data.set(Path.TYPE, type.toString());
		data.set(Path.OWNER, owner);
		data.set(Path.NAME, name);
		data.set(Path.LOCATION, LocationStringable.toString(location));
		
		try {
			data.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void read() {
		if (file == null) {
			file = createFile(location);
		}
		
		YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
		
		this.type = TeleportType.getType(data.getString(Path.TYPE));
		this.owner = (OfflinePlayer) data.get(Path.OWNER);
		this.name = data.getString(Path.NAME);
		this.location = LocationStringable.toLocation(data.getString(Path.LOCATION));
	}
	
	public static File createFile(Location location) {
		String fileName = LocationStringable.toString(location) + ".yml";
		return new File(Files.FOLDER_TELEPORTS, fileName);
	}
	
	public void deleteFile() {
		if (file.exists()) {
			file.delete();
		}
	}
}