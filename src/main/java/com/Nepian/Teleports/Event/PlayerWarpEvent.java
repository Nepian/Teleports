package com.Nepian.Teleports.Event;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PlayerWarpEvent extends CancellableEvent {
	private Player player;
	private Block block;
	private Location location;
	
	public PlayerWarpEvent(Player player, Block block, Location location) {
		super();
		this.player = player;
		this.block = block;
		this.location = location;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Block getBlock() {
		return this.block;
	}
	
	public Location getLocation() {
		return this.location;
	}
}
