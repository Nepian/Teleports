package com.Nepian.Teleports;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.Nepian.Teleports.Configuration.Files;
import com.Nepian.Teleports.Data.TeleportLocationData;
import com.Nepian.Teleports.Data.TeleportType;
import com.Nepian.Teleports.Event.CreateTeleportEvent;
import com.Nepian.Teleports.Event.RemoveTeleportEvent;

public class TeleportManager {
	private static File teleportLocationDataFolder = Files.FOLDER_TELEPORTS;
	private static Map<Location, TeleportLocationData> teleports;
	private static Map<String, TeleportLocationData> names;
	
	static {
		teleports = new HashMap<Location, TeleportLocationData>();
		names = new HashMap<String, TeleportLocationData>();
	}
	
	/* Methods ----------------------------------------------------------------------------------*/
	
	/**
	 * 指定されたデータでテレポートロケーションを登録する
	 * @param event
	 */
	public static void put(CreateTeleportEvent event) {
		put(new TeleportLocationData(event));
	}
	
	/**
	 * 指定した場所をテレポートローケーションから削除する
	 * @param location
	 */
	public static void remove(Location location) {
		TeleportLocationData tld = teleports.get(location);
		if (isEnd(tld.getType())) {
			names.remove(tld.getName());
		}
		teleports.remove(location);
		tld.deleteFile();
	}
	
	/**
	 * 指定されたデータでテレポートロケーションを削除する
	 * @param event
	 */
	public static void remove(RemoveTeleportEvent event) {
		remove(event.getTeleportLocationData().getBlockLocation());
	}
	
	/**
	 * 指定した場所のテレポート先を取得する
	 * @param name
	 * @return 場所がテレポートロケーションとして未登録 -> null
	 */
	public static Location getTeleportLocation(String name) {
		if (!hasName(name)) return null;
		return names.get(name).getTeleportLocation();
	}
	
	/**
	 * 指定した場所のテレポート名を取得する
	 * @param location
	 * @return 場所がテレポートロケーションとして未登録 -> null
	 */
	public static String getTeleportName(Location location) {
		if (!isTeleportLocation(location)) return null;
		return teleports.get(location).getName();
	}
	
	/**
	 * 指定したブロックのテレポート名を取得する
	 * @param block
	 * @return
	 */
	public static String getTeleportName(Block block) {
		return getTeleportName(block.getLocation());
	}
	
	/**
	 * 指定した場所のテレポートタイプを取得する
	 * @param location
	 * @return テレポートタイプではない場合 -> OTHER
	 */
	public static TeleportType getType(Location location) {
		return teleports.get(location).getType();
	}
	
	/**
	 * 指定したブロックがテレポートロケーションかを判定する
	 * @param block
	 * @return 場所がテレポートロケーションとして登録されていた場合 -> true
	 */
	public static boolean isTeleportBlock(Block block) {
		return isTeleportLocation(block.getLocation());
	}
	
	/**
	 * 指定した場所がテレポートロケーションかを判定する
	 * @param location
	 * @return 場所がテレポートロケーションとして登録されていた場合 -> true
	 */
	public static boolean isTeleportLocation(Location location) {
		return teleports.containsKey(location);
	}
	
	/**
	 * 指定した名前をすでに持っているか確認する
	 * @param name
	 * @return 名前が存在した場合 -> true
	 */
	public static boolean hasName(String name) {
		return names.containsKey(name);
	}
	
	/**
	 * 登録されているテレポートロケーションを取得する
	 * @return
	 */
	public static Collection<TeleportLocationData> getTeleportLocationDatas() {
		return teleports.values();
	}
	
	/**
	 * 指定したブロックのテレポート先が存在するか確認する
	 * @param block
	 * @return 存在した場合 -> true
	 */
	public static boolean hasTeleportLocation(Block block) {
		return hasTeleportLocation(getTeleportName(block.getLocation()));
	}
	
	/**
	 * 指定した名前のテレポート先が存在するか確認する
	 * @param name
	 * @return 存在した場合 -> true
	 */
	public static boolean hasTeleportLocation(String name) {
		return names.containsKey(name);
	}
	
	/**
	 * 指定したブロックのテレポートローケーションデータを取得する
	 * @param block
	 * @return 存在しなかった場合 -> null
	 */
	public static TeleportLocationData getTeleportLocationData(Block block) {
		return getTeleportLocationData(block.getLocation());
	}
	
	public static TeleportLocationData getTeleportLocationData(Location location) {
		return teleports.get(location);
	}
	
	/* Private Methods --------------------------------------------------------------------------*/
	
	private static boolean isEnd(TeleportType type) {
		return type == TeleportType.END;
	}
	
	private static void put(TeleportLocationData data) {
		if (isEnd(data.getType())) {
			names.put(data.getName(), data);
		}
		teleports.put(data.getBlockLocation(), data);
	}
	
	/* Load and Save Method ---------------------------------------------------------------------*/
	
	public static void save() {
		for (TeleportLocationData tld : teleports.values()) {
			tld.write();
		}
	}
	
	public static void load() {
		for (File file : teleportLocationDataFolder.listFiles()) {
			put(new TeleportLocationData(file));
		}
	}
}
