package com.Nepian.Teleports.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import com.Nepian.Teleports.Configuration.Files;
import com.Nepian.Teleports.Event.TeleporterCreatingEvent;

public class TeleporterData {
	private File file;
	private TeleporterType type;
	private OfflinePlayer owner;
	private String name;
	private Location blockLocation;
	private Location teleportLocation;
	private List<OfflinePlayer> member;
	private boolean isPrivate;
	
	public TeleporterData(TeleporterCreatingEvent event) {
		this.type = event.getType();
		this.owner = event.getPlayer();
		this.name = event.getName();
		this.blockLocation = event.getLocation();
		this.teleportLocation = initTeleportLocation(event.getLocation());
		this.member = new LinkedList<OfflinePlayer>();
		this.isPrivate = false;
	}
	
	public TeleporterData(File file) {
		this.file = file;
		this.read();
	}
	
	/* Getter -----------------------------------------------------------------------------------*/
		
	public TeleporterType getType() {
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
	
	public List<OfflinePlayer> getMember() {
		return member;
	}
	
	/* Setter -----------------------------------------------------------------------------------*/
	
	public void setTeleportLocation(Location location) {
		this.teleportLocation = location;
	}
	
	public void setPrivate() {
		this.isPrivate = true;
	}
	
	public void setPublic() {
		this.isPrivate = false;
	}
	
	public void changePrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	/* Method -----------------------------------------------------------------------------------*/
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(type.toString()).append(": ");
		sb.append("Name: ").append(name).append("\n");
		sb.append("Owner: ").append(owner.getName()).append("\n");
		sb.append("Blcok: ").append(LocationStringable.toString(blockLocation)).append("\n");
		sb.append("Teleport: ").append(LocationStringable.toString(teleportLocation)).append("\n");
		sb.append("Member: ").append(memberToString(member)).append("\n");
		sb.append("Private: ").append(isPrivate);
		return sb.toString();
	}
	
	public void deleteFile() {
		if (file != null) {
			if (file.exists()) {
				file.delete();
			}
		}
	}
	
	public void addMemebr(OfflinePlayer player) {
		member.add(player);
	}
	
	public boolean isPrivate() {
		return isPrivate;
	}
	
	public boolean hasMember(OfflinePlayer player) {
		return member.contains(player);
	}
	
	/* Private Method ---------------------------------------------------------------------------*/
	
	/**
	 * 指定したテレポート先初期化する
	 * @param location
	 * @return 初期化したロケーションを返す
	 */
	private static Location initTeleportLocation(Location location) {
		return location.clone().add(0.5, 1, 0.5);
	}
	
	/**
	 * 指定したロケーションを使ってファイルを作成する
	 * @param location
	 * @return 作成したファイル
	 */
	private static File createFileFromLocation(Location location) {
		String fileName = LocationStringable.toString(location) + ".yml";
		return new File(Files.FOLDER_TELEPORTS, fileName);
	}
	
	/**
	 * 指定したOfflinePlayerリストから新しくUUIDのStringリストを作成する
	 * @param member
	 * @return 作成したUUIDのStringリスト
	 */
	private static List<String> getStringUidList(List<OfflinePlayer> member) {
		List<String> uuids = new ArrayList<String>();
		
		for (OfflinePlayer player : member) {
			uuids.add(player.getUniqueId().toString());
		}
		
		return uuids;
	}
	
	/**
	 * プレイヤーのUUIDの文字列リストから新しくOfflinePlayerリストを生成する
	 * @param strPlayerUidList
	 * @return
	 */
	private static List<OfflinePlayer> getOfflinePlayerList(List<String> strPlayerUidList) {
		List<OfflinePlayer> players = new LinkedList<OfflinePlayer>();
		for (String str : strPlayerUidList) {
			players.add(Bukkit.getOfflinePlayer(UUID.fromString(str)));
		}
		return players;
	}
	
	/**
	 * メンバリストを一つの文字列で生成する
	 * @param member
	 * @return
	 */
	private static String memberToString(List<OfflinePlayer> member) {
		StringBuilder str = new StringBuilder("");
		for (OfflinePlayer player : member) {
			str.append(player.getName()).append(" ");
		}
		return str.toString();
	}
	
	/*-------------------------------------------------------------------------------------------*/
	///////////////////////////////////////////////////////////////////////////////////////////////
	/*-------------------------------------------------------------------------------------------*/
	
	public static class Path {
		public static final String TYPE = "type";
		public static final String OWNER = "owner";
		public static final String NAME = "name";
		public static final String BLOCK = "block";
		public static final String TELEPORT = "teleport";
		public static final String MEMBER = "member";
		public static final String PRIVATE = "private";
	}
	
	public void write() {
		if (file == null) {
			file = createFileFromLocation(blockLocation);
		}
		
		YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
		
		data.set(Path.TYPE, type.toString());
		data.set(Path.OWNER, owner.getUniqueId().toString());
		data.set(Path.NAME, name);
		data.set(Path.BLOCK, LocationStringable.toString(blockLocation));
		data.set(Path.TELEPORT, LocationStringable.toString(teleportLocation));
		data.set(Path.MEMBER, getStringUidList(member));
		data.set(Path.PRIVATE, isPrivate);
		
		try {
			data.save(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void read() {
		if (file == null) {
			file = createFileFromLocation(blockLocation);
		}
		
		YamlConfiguration data = YamlConfiguration.loadConfiguration(file);
		
		this.type = TeleporterType.getType(data.getString(Path.TYPE));
		this.owner = Bukkit.getOfflinePlayer(UUID.fromString(data.getString(Path.OWNER)));
		this.name = data.getString(Path.NAME);
		this.blockLocation = LocationStringable.toLocation(data.getString(Path.BLOCK));
		this.teleportLocation = LocationStringable.toLocation(data.getString(Path.TELEPORT));
		this.member = getOfflinePlayerList(data.getStringList(Path.MEMBER));
		this.isPrivate = data.getBoolean(Path.PRIVATE, false);
	}
}