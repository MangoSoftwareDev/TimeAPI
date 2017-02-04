package com.matthiaantje.api;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Matthias on 4/02/2017.
 */
public class TimeAPI extends JavaPlugin {

    public void onEnable()
    {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "TimeAPI, by Matthiaantje, has been enabled!");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args)
    {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.GREEN + "[TimeFormat]" + ChatColor.GRAY + " by Matthiaantje");
        } else {
            try
            {
                int i = Integer.parseInt(args[0]);
                sender.sendMessage(ChatColor.GRAY + TimeUnit.toString(i));
            }
            catch (NumberFormatException e)
            {
                sender.sendMessage(ChatColor.RED + "The first argument must be an integer.");
            }
        }
        return false;
    }

}
