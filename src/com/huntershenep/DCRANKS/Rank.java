package com.huntershenep.DCRANKS;

import java.util.ArrayList;

public class Rank {
	public String name;
	public boolean premium;
	public int tokens;
	
	public static ArrayList<Rank> ranks = new ArrayList<Rank>();
	
	
	
	
	
	//public static ArrayList<Ranks> ranks = new ArrayList<Ranks>();
	
	public Rank(String name, boolean premium, int tokens) {
		this.name = name;
		this.premium = premium;
		this.tokens = tokens;
	}
	
	public static void setValues() {
		
		ranks.add(new Rank("vip", true, 15));
		ranks.add(new Rank("vipp", true, 40));
		ranks.add(new Rank("mvp", true, 100));
	}
	
	
	public static Rank getRank(String rankName) {
		Rank rank = null;
		
		for(int i = 0; i < ranks.size(); i++) {
			if(ranks.get(i).name.equalsIgnoreCase(rankName)) {
				rank = ranks.get(i);
			}
		}
		
		return rank;
	}
	
	
}
