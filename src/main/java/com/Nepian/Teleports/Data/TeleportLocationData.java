package com.Nepian.Teleports.Data;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
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
	private Location blockLocation;
	private Location teleportLocation;
	
	public TeleportLocationData(CreateTeleportEvent event) {
		this.type = event.getType();
		this.owner = event.getPlayer();
		this.name = event.getName();
		this.blockLocation = event.getLocation();
		this.teleportLocation = this.setDefaultTeleportLocation(event.getLocation());
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
	
	public Location getBlockLocation() {
		return blockLocation;
	}
	
	public Location getTeleportLocation() {
		return teleportLocation;
	}
	
	public void setTeleportLocation(Location location) {
		this.teleportLocation = location;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(type.toString()).append(": ");
		sb.append("Name:").append(name).append(", ");
		sb.append("Owner:").append(owner.getName()).append(", ");
		sb.append("Blcok:").append(LocationStringable.toString(blockLocation)).append(", ");
		sb.append("Teleport:").append(LocationStringable.toString(teleportLocation));
		return sb.toString();
	}
	
	private Location setDefaultTeleportLocation(Location location) {
		return location.clone().add(0.5, 1, 0.5);
	}
	
	/*-------------------------------------------------------------------------------------------*/
	///////////////////////////////////////////////////////////////////////////////////////////////
	/* Write and Read ---------------------------------------------------------------------------*/
	
	public static class Path {
		public static final String TYPE = "type";
		public static final String OWNER = "owner";
		public static final String NAME = "name";
		public static final String BLOCK = "block";
		public static final String TELEPORT = "teleport";
	}
	
	public void write() {
		if (file == null) {
			file = createFile(blockLocation);
		}
		
		YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
		
		data.set(Path.TYPE, type.toString());
		data.set(Path.OWNER, owner.getUniqueId().toString());
		data.set(Path.NAME, name);
		data.set(Path.BLOCK, LocationStringable.toString(blockLocation));
		data.set(Path.TELEPORT, LocationStringable.toString(teleportLocation));
		
		try {
			data.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void read() {
		if (file == null) {
			file = createFile(blockLocation);
		}
		
		YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
		
		this.type = TeleportType.getType(data.getString(Path.TYPE));
		this.owner = Bukkit.getOfflinePlayer(UUID.fromString(data.getString(Path.OWNER)));
		this.name = data.getString(Path.NAME);
		this.blockLocation = LocationStringable.toLocation(data.getString(Path.BLOCK));
		this.teleportLocation = LocationStringable.toLocation(data.getString(Path.TELEPORT));
	}
	
	public static File createFile(Location location) {
		String fileName = LocationStringable.toString(location) + ".yml";
		return new File(Files.FOLDER_TELEPORTS, fileName);
	}
	
	public void deleteFile() {
		if (file != null) {
			if (file.exists()) {
				file.delete();
			}
		}
	}
}