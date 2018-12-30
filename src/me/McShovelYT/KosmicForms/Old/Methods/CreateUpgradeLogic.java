package me.McShovelYT.KosmicForms.Old.Methods;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.McShovelYT.KosmicForms.Old.Main;

public class CreateUpgradeLogic {
    static Plugin plugin = Main.getPlugin(Main.class);

    public CreateUpgradeLogic(CreateUpgradeLogic plugin) {
    }

    public static void Create(Player player, String Config, String TPConfig, Integer Cost, String FormName, String Form, Integer FormLevel) {
        int PrevLevel = FormLevel - 1;
        if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + Config) == null) {
            player.sendMessage(ChatColor.RED + "Level " + PrevLevel + " not purchased");
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + Config)) >= FormLevel) {
            player.sendMessage(ChatColor.RED + "You already have this form");
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + Config)) < PrevLevel) {
            player.sendMessage(ChatColor.RED + "Level " + PrevLevel + " not purchased");
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + TPConfig)) < Cost) {
            player.sendMessage(ChatColor.RED + "You don't have enough " + Form + " TP to buy this form");
        } else {
            plugin.getConfig().set("PlayerData." + player.getUniqueId() + Config, FormLevel);
            int x = Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getUniqueId() + TPConfig));
            plugin.getConfig().set("PlayerData." + player.getUniqueId() + TPConfig, x - Cost);
            plugin.saveConfig();
            player.closeInventory();
            if (Form == "M.U.I") {
                player.sendMessage(ChatColor.BLUE + "You have successfully bought " + FormName);
            } else {
                player.sendMessage(ChatColor.BLUE + "You have successfully bought " + FormName + " level " + FormLevel);
            }
        }

    }

    public static void CreateFirst(Player player, String Config, String TPConfig, Integer Cost, String FormName, String Form, Integer FormLevel) {
        int x;
        if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + Config) == null) {
            if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + TPConfig) == null) {
                player.sendMessage(ChatColor.RED + "You don't have enough " + Form + " TP to buy this form");
            } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + TPConfig)) < Cost) {
                player.sendMessage(ChatColor.RED + "You don't have enough " + Form + " TP to buy this form");
            } else {
                plugin.getConfig().set("PlayerData." + player.getUniqueId() + Config, 1);
                x = Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + TPConfig));
                plugin.getConfig().set("PlayerData." + player.getUniqueId() + TPConfig, x - Cost);
                plugin.saveConfig();
                player.closeInventory();
                player.sendMessage(ChatColor.BLUE + "You have successfully bought " + FormName + " level 1");
            }
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + Config)) >= FormLevel) {
            player.sendMessage(ChatColor.RED + "You already have this form");
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + TPConfig)) < Cost) {
            player.sendMessage(ChatColor.RED + "You don't have enough " + Form + " TP to buy this form");
        } else {
            plugin.getConfig().set("PlayerData." + player.getUniqueId() + Config, FormLevel);
            x = Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + TPConfig));
            plugin.getConfig().set("PlayerData." + player.getUniqueId() + TPConfig, x - Cost);
            plugin.saveConfig();
            player.closeInventory();
            player.sendMessage(ChatColor.BLUE + "You have successfully bought " + FormName + " level 1");
        }

    }
}
