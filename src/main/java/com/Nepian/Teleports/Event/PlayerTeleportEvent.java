package com.Nepian.Teleports.Event;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PlayerTeleportEvent extends CancellableEvent {
	private Player player;
	private Block block;
	private Location location;
	private String name;
	
	public PlayerTeleportEvent(Player player, Block block, Location location, String name) {
		super();
		this.player = player;
		this.block = block;
		this.location = location;
		this.name = name;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}
}
