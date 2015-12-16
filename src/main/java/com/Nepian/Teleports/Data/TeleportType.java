package com.Nepian.Teleports.Data;

import org.bukkit.block.Block;

public enum TeleportType {
	START,
	END,
	OTHER;
	
	public static TeleportType getType(Block block) {
		switch (block.getType()) {
		case EMERALD_BLOCK:
			return START;
		case DIAMOND_BLOCK:
			return END;
		default:
			return OTHER;
		}
	}
	
	public static TeleportType getType(String string) {
		for (TeleportType type : values()) {
			if (string.equalsIgnoreCase(type.toString())) {
				return type;
			}
		}
		return null;
	}
	
	public static boolean isEnd(Block block) {
		return getType(block) == END;
	}
	
	public static boolean isStart(Block block) {
		return getType(block) == START;
	}
	
	public static boolean isOther(Block block) {
		return getType(block) == OTHER;
	}
}
