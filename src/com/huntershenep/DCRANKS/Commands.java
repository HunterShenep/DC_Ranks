package com.huntershenep.DCRANKS;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    private final Main plugin;

    public Commands(Main plugin) {
        this.plugin = plugin;
    }

    
    //COMMANDS
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		
		if (cmd.getName().equalsIgnoreCase("rank")) {
			
			if(sender instanceof ConsoleCommandSender) {
				
				if(args.length == 0) {
					
				}
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("save")) {
						Methods.saveData();
						Methods.log("Data saved.");
					}
					if(args[0].equalsIgnoreCase("list")) {
						Methods.listPlayerRanks();
					}
					
				}
				if(args.length == 3) {
					if(args[0].equalsIgnoreCase("give")) {
						Sub.newSub(Bukkit.getServer().getPlayer(args[1]).getUniqueId(), Rank.getRank(args[2]));
						Methods.log("Gave " + Bukkit.getServer().getPlayer(args[1]).getName() + " rank: " + Rank.getRank(args[2]).name);
					}
				}
				if(args.length == 5) {
					if(args[0].equalsIgnoreCase("give")) {
						Sub.newSub(Bukkit.getServer().getPlayer(args[1]).getUniqueId(), Rank.getRank(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
						Methods.log("Gave " + Bukkit.getServer().getPlayer(args[1]).getName() + " rank: " + Rank.getRank(args[2]).name);
					}
				}
				
				
			}
			else {
				
				Player player = (Player) sender;
			
				if(args.length == 0) {

				}
				
				
				if(args.length == 1) {
					
						if(args[0].equalsIgnoreCase("start")) {
	
						}
						else if(args[0].equalsIgnoreCase("stop")) {
							
						}
					}
				}
			
			}
		
		return true;
	}
}
