package me.McShovelYT.KosmicForms.Old;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import me.McShovelYT.KosmicForms.Old.Methods.GetStats;
import me.McShovelYT.KosmicForms.Old.Methods.Methods;
import me.McShovelYT.KosmicForms.Old.Methods.SavesHelp;
import me.McShovelYT.KosmicForms.Old.Utils.NBTEditor;

public class SaveMenu implements Listener {
    static Plugin plugin = Main.getPlugin(Main.class);

    public SaveMenu(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    public static void openMenu(Player player) {
    	Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.GREEN + "Save File Menu");
    	ItemStack e = Methods.createItemStack(Material.STAINED_GLASS_PANE, 15, "", "");
    	
    	int help1 = 0;
    	int help2 = 0;
    	int help3 = 0;
    	
    	String f = plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".Save1");
    	String g = plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".Save2");
    	String h = plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".Save3");
    	
    	if (f != null) {
    		help1 = 5;
    	} else {
    		help1 = 14;
    	}
    	
    	if (g != null) {
    		help2 = 5;
    	} else {
    		help2 = 14;
    	}
    	
    	if (h != null) {
    		help3 = 5;
    	} else {
    		help3 = 14;
    	}
    	
    	ItemStack a = Methods.createItemStack(Material.STAINED_GLASS_PANE, help1, "" + ChatColor.GRAY + ChatColor.BOLD + "Save File 1", "");
    	ItemStack b = Methods.createItemStack(Material.STAINED_GLASS_PANE, help2, "" + ChatColor.GRAY + ChatColor.BOLD + "Save File 2", "");
    	ItemStack c = Methods.createItemStack(Material.STAINED_GLASS_PANE, help3, "" + ChatColor.GRAY + ChatColor.BOLD + "Save File 3", "");
    	inv.setItem(0, e);
    	inv.setItem(1, e);
    	inv.setItem(2, e);
    	inv.setItem(3, e);
    	inv.setItem(4, e);
    	inv.setItem(5, e);
    	inv.setItem(6, e);
    	inv.setItem(7, e);
    	inv.setItem(8, e);
    	inv.setItem(9, e);
    	inv.setItem(10, e);
    	inv.setItem(11, e);
    	inv.setItem(12, a);
    	inv.setItem(13, b);
    	inv.setItem(14, c);
    	inv.setItem(15, e);
    	inv.setItem(16, e);
    	inv.setItem(17, e);
    	inv.setItem(18, e);
    	inv.setItem(19, e);
    	inv.setItem(20, e);
    	inv.setItem(21, e);
    	inv.setItem(23, e);
    	inv.setItem(24, e);
    	inv.setItem(25, e);
    	inv.setItem(26, e);
        ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit menu");
        exit.setItemMeta(exitMeta);
        inv.setItem(22, exit);
    	player.openInventory(inv);
    }
    
    @EventHandler
    public void onInventoryCLick(InventoryClickEvent e) {
        Player player;
        player = (Player)e.getWhoClicked();
        if (e.getInventory().getName().equalsIgnoreCase(ChatColor.GREEN + "Save File Menu")) {
        	if (e.getClick().isLeftClick()) {
	            e.setCancelled(true);
	        	int help1 = 0;
	        	int help2 = 0;
	        	int help3 = 0;
	        	
	        	String f = plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".Save1");
	        	String g = plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".Save2");
	        	String h = plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".Save3");
	        	
	        	if (f != null) {
	        		help1 = 1;
	        	} else {
	        		help1 = 0;
	        	}
	        	
	        	if (g != null) {
	        		help2 = 1;
	        	} else {
	        		help2 = 0;
	        	}
	        	
	        	if (h != null) {
	        		help3 = 1;
	        	} else {
	        		help3 = 0;
	        	}
	        	
	        	if (e.getSlot() == 22) {
	        		Menu.openMenu(player);
	        	}
	        	
	            if (e.getSlot() == 12) {
	            	if (help1 == 1) {
	            		player.sendMessage(ChatColor.BLUE + "You have loaded this save file!");
                        SavesHelp.write(player, 1);
            			NBTEditor.Edit(player, "jrmcBdy", GetStats.getMaxHP(player));
            			NBTEditor.Edit(player, "jrmcEnrgy", 4600000);
            			NBTEditor.Edit(player, "jrmcStamina", GetStats.getMaxStam(player));
	            	} else {
	            		SavesHelp.save(player, 1);
	            		player.sendMessage(ChatColor.BLUE + "You have saved this save file!");
	            	}
	            }
	            if (e.getSlot() == 13) {
	            	if (help2 == 1) {
	            		player.sendMessage(ChatColor.BLUE + "You have loaded this save file!");
	        			NBTEditor.Edit(player, "jrmcBdy", GetStats.getMaxHP(player));
	        			NBTEditor.Edit(player, "jrmcEnrgy", 4600000);
	        			NBTEditor.Edit(player, "jrmcStamina", GetStats.getMaxStam(player));
	                    SavesHelp.write(player, 2);
	            	} else {
	            		SavesHelp.save(player, 2);
	            		player.sendMessage(ChatColor.BLUE + "You have saved this save file!");
	            	}
	            }
	            if (e.getSlot() == 14) {
	            	if (help3 == 1) {
	            		player.sendMessage(ChatColor.BLUE + "You have loaded this save file!");
	        			NBTEditor.Edit(player, "jrmcBdy", GetStats.getMaxHP(player));
	        			NBTEditor.Edit(player, "jrmcEnrgy", 4600000);
	        			NBTEditor.Edit(player, "jrmcStamina", GetStats.getMaxStam(player));
	                    SavesHelp.write(player, 3);
	            	} else {
	            		SavesHelp.write(player, 3);
	            		player.sendMessage(ChatColor.BLUE + "You have saved this save file!");
	            	}
	            }
	    } else {
	            player = (Player)e.getWhoClicked();
	            e.setCancelled(true);
	        	int help1 = 0;
	        	int help2 = 0;
	        	int help3 = 0;
	        	
	        	String f = plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".Save1");
	        	String g = plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".Save2");
	        	String h = plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".Save3");
	        	
	        	if (f != null) {
	        		help1 = 1;
	        	} else {
	        		help1 = 0;
	        	}
	        	
	        	if (g != null) {
	        		help2 = 1;
	        	} else {
	        		help2 = 0;
	        	}
	        	
	        	if (h != null) {
	        		help3 = 1;
	        	} else {
	        		help3 = 0;
	        	}
	        	
	        	if (e.getSlot() == 22) {
	        		Menu.openMenu(player);
	        	}
	        	
	            if (e.getSlot() == 12) {
	            	if (help1 == 1) {
	            		SavesHelp.clear(player, 1);
	            		player.sendMessage(ChatColor.RED + "You have cleared your save data!");
	            	} else {
	            		player.sendMessage(ChatColor.RED + "You don't have any data to clear!");
	            	}
	            }
	            
	            if (e.getSlot() == 13) {
	            	if (help2 == 1) {
	            		SavesHelp.clear(player, 2);
	            		player.sendMessage(ChatColor.RED + "You have cleared your save data!");
	            	} else {
	            		player.sendMessage(ChatColor.RED + "You don't have any data to clear!");
	            	}
	            }
	            
	            if (e.getSlot() == 14) {
	            	if (help3 == 1) {
	            		SavesHelp.clear(player, 3);
	            		player.sendMessage(ChatColor.RED + "You have cleared your save data!");
	            	} else {
	            		player.sendMessage(ChatColor.RED + "You don't have any data to clear!");
	            	}
	            }
	        }
	    }
    }
}
