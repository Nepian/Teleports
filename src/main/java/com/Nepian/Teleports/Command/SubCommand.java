package com.Nepian.Teleports.Command;

import java.util.List;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {
	private String name;
	private String permission;
	private String[] aliases;
	
	public SubCommand(String name) {
		this(name, new String[0]);
	}
	
	public SubCommand(String name, String... aliases) {
		this.name = name;
		this.aliases = aliases;
	}
	
	/**
	 * コマンドを実行する
	 * @param sender -> コマンド実行者
	 * @param label -> 使用したコマンド名
	 * @param args -> コマンドの引数
	 * @throws CommandException
	 */
	public abstract void execute(CommandSender sender, String label,
			String[] args) throws CommandException;
	
	/**
	 * コマンドの送信者が権限を所持しているか判定する
	 * @param sender = (判定対象)-> コマンドの送信者
	 * @return (非所持)-> false (所持||NullPermisson)-> true
	 */
	public final boolean hasPermission(CommandSender sender) {
		if (permission == null) return true;
		return sender.hasPermission(permission);
	}
	
	/**
	 * 指定した文字列がコマンドとして適正か判定する
	 * @param name = (判定対象)-> コマンド名
	 * @return (適正)-> true (不適正)-> false
	 */
	public final boolean isValidTrigger(String name) {
		if (this.name.equalsIgnoreCase(name)) {
			return true;
		}
		
		if (aliases != null) {
			for (String alias : aliases) {
				if (alias.equalsIgnoreCase(name)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * コマンドで可能な引数の文字列を取得する
	 * @return
	 */
	public abstract String getPossibleArguments();

	/**
	 * コマンドで最低限必要な引数の長さを取得する
	 * @return
	 */
	public abstract int getMinimumArguments();
	
	/**
	 * コマンドのチュートリアルメッセージを取得する
	 * @return
	 */
	public abstract List<String> getTutorial();
	
	/**
	 * コマンドのタイプを取得する
	 * @return
	 */
	public abstract SubCommandType getType();
	
	public enum SubCommandType {
		GENERIC, HIDDEN
	}
	
	/* Getter -----------------------------------------------------------------------------------*/
	
	public String getName() {
		return name;
	}
	
	public String getPermission() {
		return permission;
	}
	
	public void setPermission(String permission) {
		this.permission = permission;
	}
}
