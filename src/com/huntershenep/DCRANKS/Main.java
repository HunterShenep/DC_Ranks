package com.huntershenep.DCRANKS;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin implements Listener{
	
    @SuppressWarnings("deprecation")
	@Override
    public void onEnable() {
    	Bukkit.getPluginManager().registerEvents(this, this);
    	this.getCommand("rank").setExecutor(new Commands(this));
    	//this.getCommand("tokens").setExecutor(new Commands(this));
		ConfigManager.setup();
		ConfigManager.get().addDefault("numberOfRanks", 0);
		ConfigManager.get().options().copyDefaults(true);
		ConfigManager.save();
		
    	Rank.setValues();
    	Methods.getDataFromConfig();
    	//expireANDrewardFirst
    	
		Bukkit.getServer().getScheduler().runTaskTimer(this, new BukkitRunnable() {
		    public void run() {
		    		//Check for if player is inside the PvP area.
		    	
		    	Methods.expireANDrewardFirst();
	        }
		}, 150L, 1200L);
    	
    	
		Bukkit.getServer().getScheduler().runTaskTimer(this, new BukkitRunnable() {
		    public void run() {
		    		//Check for if player is inside the PvP area.
		    	
		    	Methods.saveData();
	        }
		}, 150L, 12210L);
    	
    	
    }
    
    
	@Override
	public void onDisable() {
		Methods.saveData();
	}
    
}
