package com.Nepian.Teleports.Util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Attachable;

public class BlockUtil {
	
	public static BlockFace getAttachedFace(Block block) {
		return ((Attachable) block.getState().getData()).getAttachedFace();
	}
	
	public static Block getBlockRelative(Block block) {
		return block.getRelative(getAttachedFace(block));
	}

	public static Block getBlockUnder(Block block) {
		return block.getRelative(BlockFace.DOWN);
	}
	
	public static Block getBlockAbove(Block block) {
		return block.getRelative(BlockFace.UP);
	}
	
	public static Material getType(Block block) {
		return block.getType();
	}

	public static boolean isStonePlate(Block block) {
		return getType(block) == Material.STONE_PLATE;
	}
	
	public static boolean isWoodPlate(Block block) {
		return getType(block) == Material.WOOD_PLATE;
	}

	public static boolean isDiamondBlock(Block block) {
		return getType(block) == Material.DIAMOND_BLOCK;
	}

	public static boolean isEmeraldBlock(Block block) {
		return getType(block) == Material.EMERALD_BLOCK;
	}
	
	public static boolean isStoneButton(Block block) {
		return getType(block) == Material.STONE_BUTTON;
	}
	
	public static boolean isWoodButton(Block block) {
		return getType(block) == Material.WOOD_BUTTON;
	}
	
	public static boolean isPlate(Block block) {
		return isStonePlate(block) || isWoodPlate(block);
	}
	
	public static boolean isButton(Block block) {
		return isStoneButton(block) || isWoodButton(block);
	}
}
