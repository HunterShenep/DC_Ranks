package com.huntershenep.DCRANKS;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
	private static File file;
	private static FileConfiguration customFile;
	
	
	//find or generates the config.yml file
	public static void setup() {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("DCRANKS").getDataFolder(), "config.yml");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		customFile = YamlConfiguration.loadConfiguration(file);
	}
	
	
	//Returns file
	public static FileConfiguration get() {
		return customFile;
	}
	
	
	//Saves file
	public static void save() {
		try {
			customFile.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	
	//Reloads file
	public static void reload() {
		customFile = YamlConfiguration.loadConfiguration(file);
	}
	
	
	//Add new entry
	public static void addLine(String name, String value) {
		customFile.set(name, value);
		
		save();
	}
	public static void addLine(String name, Double value) {
		
		customFile.set(name, value);
		save();
	}
	public static void addLine(String name, int value) {
		
		customFile.set(name, value);
		save();
	}
	public static void addLine(String name, float value) {
		
		customFile.set(name, value);
		save();
	}
	
	public static void addLine(String name, Boolean value) {
		
		customFile.set(name, value);
		save();
	}
}
