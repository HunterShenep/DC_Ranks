package com.huntershenep.DCRANKS;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Sub {
	public UUID uuid;
	public boolean active;
	public Date paidUntil;
	public Rank rank;
	public boolean givenFirstReward;
	
	public static int month = 31;
	
	public static ArrayList<Sub> theSubs = new ArrayList<Sub>();
	
	
	public Sub(UUID uuid, Date paidUntil, Rank rank, boolean firstReward) {
		this.uuid = uuid;
		this.paidUntil = paidUntil;
		this.active = true;
		this.rank = rank;
		this.givenFirstReward = firstReward;
	}
	
	public static void newSub(UUID uuid, Rank rank) {
		boolean exists = false;
		
		for(int i = 0; i < theSubs.size(); i++) {
			if(theSubs.get(i).uuid.equals(uuid)) {
				if(theSubs.get(i).rank.equals(rank)) {
					exists = true;
					if(DateUtil.isExpired(theSubs.get(i).paidUntil)) {
						//is expired
						theSubs.get(i).paidUntil = DateUtil.addDaysToDate(theSubs.get(i).paidUntil, month);
						Methods.givePexRewards(uuid, rank, false);
					}
					else {

						//renewing
						theSubs.get(i).active = true;
						theSubs.get(i).paidUntil = DateUtil.addDaysToTODAY(month);
						Methods.givePexRewards(uuid, rank, true);
					}
				}
			}
		}
		
		if(exists == false) {
			theSubs.add(new Sub(uuid, DateUtil.addDaysToTODAY(month), rank, true));
			Methods.givePexRewards(uuid, rank, false);
		}
	}
	
	
	public static void newSub(UUID uuid, Rank rank, int numDays, int tokens) {
		boolean exists = false;
		
		for(int i = 0; i < theSubs.size(); i++) {
			if(theSubs.get(i).uuid.equals(uuid)) {
				if(theSubs.get(i).rank.equals(rank)) {
					exists = true;
					if(DateUtil.isExpired(theSubs.get(i).paidUntil)) {
						//is expired
						
						System.out.println("################## EXPIRED");
						theSubs.get(i).paidUntil = DateUtil.addDaysToDate(theSubs.get(i).paidUntil, numDays);
						Methods.givePexRewards(uuid, rank, false, tokens);
					}
					else {
						System.out.println("################## NOT EXPIRED");
						//renewing
						theSubs.get(i).active = true;
						theSubs.get(i).paidUntil = DateUtil.addDaysToTODAY(numDays);
						Methods.givePexRewards(uuid, rank, true, tokens);
					}
				}
			}
		}
		
		if(exists == false) {
			System.out.println("################## NEW ");
			theSubs.add(new Sub(uuid, DateUtil.addDaysToTODAY(numDays), rank, true));
			Methods.givePexRewards(uuid, rank, false, tokens);
		}
	}
	
	
	public static void addSubFromCfg(UUID uuid, Date paidUntil, Rank rank, boolean firstReward){
		theSubs.add(new Sub(uuid, paidUntil, rank, firstReward));
	}
	
	public static boolean subExists(Player player) {
		boolean exists = false;
		
		for(int i = 0; i < theSubs.size(); i++) {
			if(theSubs.get(i).uuid.equals(player.getUniqueId())) {
				exists = true;
				break;
			}
		}
		
		return exists;
	}
	
	public static int getSubIndex(Player player) {
		int index = 9999;
		
		for(int i = 0; i < theSubs.size(); i++) {
			if(theSubs.get(i).uuid.equals(player.getUniqueId())) {
				index = i;
				break;
			}
		}
		
		return index;
	}
	
	
}
