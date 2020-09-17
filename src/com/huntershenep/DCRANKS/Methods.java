package com.huntershenep.DCRANKS;

import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.huntershenep.DCCORE.CoreMethods;

public class Methods {
	
	public static void saveData() {
		ConfigManager.addLine("numberOfRanks", Sub.theSubs.size());
		
		for(int i = 0; i < Sub.theSubs.size(); i++) {
			ConfigManager.addLine((i+1) + ".uuid", Sub.theSubs.get(i).uuid.toString());
			ConfigManager.addLine((i+1) + ".username", Bukkit.getServer().getOfflinePlayer(Sub.theSubs.get(i).uuid).getName());
			ConfigManager.addLine((i+1) + ".active", Sub.theSubs.get(i).active);
			ConfigManager.addLine((i+1) + ".rank", Sub.theSubs.get(i).rank.name);
			ConfigManager.addLine((i+1) + ".paidUntil", DateUtil.dateToString(Sub.theSubs.get(i).paidUntil));
			ConfigManager.addLine((i+1) + ".firstReward", Sub.theSubs.get(i).givenFirstReward);
		}
	}
	
	public static void getDataFromConfig() {
		int numRanks = ConfigManager.get().getInt("numberOfRanks");
		for(int i = 0; i < numRanks; i++) {
			
			UUID uuid = UUID.fromString(ConfigManager.get().getString((i+1) + ".uuid"));
			Date paidUntil = DateUtil.stringToDate(ConfigManager.get().getString((i+1) + ".paidUntil"));
			Rank theRank = Rank.getRank(ConfigManager.get().getString((i+1) + ".rank"));
			boolean firstReward = ConfigManager.get().getBoolean((i+1) + ".firstReward");
			
			Sub.addSubFromCfg(uuid, paidUntil, theRank, firstReward);
		}
	}
	
	
	public static void givePexRewards(UUID uuid, Rank rank, boolean renew) {
		OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(uuid);
		
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tokens add " + player.getName() + " " + rank.tokens); 
		
		log("We made it this far");
		
		if(renew == true) {
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "msg " + player.getName() + " "); 
			msg(player, "Thanks for renewing your rank! You've received your tokens. Thanks for supporting DiamCraft!");
		}
		else {
			msg(player, "You've received your rank and tokens. Thanks for supporting DiamCraft!");
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " group add " + rank.name); 
		}
	}
	
	public static void givePexRewards(UUID uuid, Rank rank, boolean renew, int tokens) {
		OfflinePlayer player = Bukkit.getServer().getOfflinePlayer(uuid);
		
		Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tokens add " + player.getName() + " " + tokens); 
		
		log("We made it this far");
		
		if(renew == true) {
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "msg " + player.getName() + " "); 
			msg(player, "Thanks for renewing your rank! You've received your tokens. Thanks for supporting DiamCraft!");
		}
		else {
			msg(player, "You've received your rank and tokens. Thanks for supporting DiamCraft!");
			Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " group add " + rank.name); 
		}
	}
	
	public static void expireANDrewardFirst() {
		for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			if(Sub.subExists(p)) {
				int index = Sub.getSubIndex(p);
				
				if(DateUtil.isExpired(Sub.theSubs.get(index).paidUntil)) {
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + p.getName() + " group remove " + Sub.theSubs.get(index).rank.name); 
					msg(p, "Your subscription has run out.");
					Sub.theSubs.remove(index);
				}
				
				if(Sub.theSubs.get(index).givenFirstReward == false) {
					Sub.theSubs.get(index).givenFirstReward = true;
					int totalForRank = Sub.theSubs.get(index).rank.tokens;
					int tokenPerDay = totalForRank/30;
					int daysleft = DateUtil.daysLeft(Sub.theSubs.get(index).paidUntil);
					int reward = tokenPerDay * daysleft;
					msg(p, "You've received partial tokens of " + reward + " for having " + daysleft + " days left of your subscription");
					Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "tokens add " + p.getName() + " " + reward); 
				}
				
				
				
				
				
				
				//Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getName() + " group add " + rank.name); 
			}
		}
	}
	
	
	
	public static void listPlayerRanks() {
		for(int i = 0; i < Sub.theSubs.size(); i++) {
			log(Bukkit.getServer().getOfflinePlayer(Sub.theSubs.get(i).uuid).getName() + " : " + Sub.theSubs.get(i).rank.name);
			
			//log(Sub.theSubs.get(i).uuid.toString() + " : " + Sub.theSubs.get(i).rank.name);
		}
	}
	
	public static void msg(OfflinePlayer player, String message) {
		if(player.isOnline() == true) {
			Player playerA = (Player) player;
			CoreMethods.sendmessage(message, playerA, "&cRANKS");
		}
	}
	public static void msg(Player player, String message) {
		CoreMethods.sendmessage(message, player, "&cRANKS");
	}
	public static void log(String message) {
		System.out.println("[DC-RANKS] " + message);
	}
	
	
}
