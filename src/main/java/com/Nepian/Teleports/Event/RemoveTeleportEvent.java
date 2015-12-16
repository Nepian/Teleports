package com.Nepian.Teleports.Event;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.Nepian.Teleports.TeleportManager;
import com.Nepian.Teleports.Data.TeleportType;

public class RemoveTeleportEvent extends CancellableEvent {
	private TeleportType type;
	private Player player;
	private Block block;
	private String name;
	private Location location;
	
	public RemoveTeleportEvent(Player player, Block block) {
		super();
		this.type = TeleportType.getType(block);
		this.player = player;
		this.block = block;
		this.location = block.getLocation();
		this.name = TeleportManager.getName(this.location);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public TeleportType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public Location getLocation() {
		return location;
	}
}
