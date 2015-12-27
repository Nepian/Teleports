package com.Nepian.Teleports.Event;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.Nepian.Teleports.Data.TeleporterType;

public class TeleporterCreatingEvent extends CancellableEvent {
	private TeleporterType type;
	private Player player;
	private Block block;
	private String name;
	private Location location;
	
	public TeleporterCreatingEvent(Player player, Block block, String name) {
		super();
		this.type = TeleporterType.getType(block);
		this.player = player;
		this.block = block;
		this.name = name;
		this.location = block.getLocation();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public String getName() {
		return name;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public TeleporterType getType() {
		return type;
	}
}
