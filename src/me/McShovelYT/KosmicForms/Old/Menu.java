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

import me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeLogic;
import me.McShovelYT.KosmicForms.Old.Methods.Methods;
import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;

public class Menu implements Listener {
    static Plugin plugin = Main.getPlugin(Main.class);

    public Menu(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public static void openMenu(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.BLUE + "Kosmic Forms Menu");
        ItemStack e = Methods.createItemStack(Material.STAINED_GLASS_PANE, 6, "" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Created by McShovelYT", "");
        ItemStack god = Methods.createItemStack(Material.INK_SACK, 5, ChatColor.DARK_PURPLE + "G.O.D Form", "");
        ItemStack UI = Methods.createItemStack(Material.INK_SACK, 15, "" + ChatColor.WHITE + ChatColor.BOLD + "U.I Form", "");
        ItemStack SK = Methods.createItemStack(Material.INK_SACK, 1, ChatColor.RED + "S.K Form", "");
    	int help2;
    	int help3;
    	String help4;
    	
        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
    	int Purity = PlayerPersisted.getInt("jrmcAlign");
    	
    	if (Purity >= 51) {
    		help2 = 0;
    	} else {
    		help2 = 1;
    	}
    	
        if (help2 == 0) {
        	help3 = 4;
        } else {
        	help3 = 13;
        }
        
        if (help2 == 0) {
        	help4 = ChatColor.BLUE + "Mastered SS Blue Form";
        } else {
        	help4 = ChatColor.DARK_PURPLE + "Mastered SS Rose Form";
        }
        
        ItemStack EVO = Methods.createItemStack(Material.INK_SACK, help3, help4, "");
        ItemStack MY = Methods.createItemStack(Material.INK_SACK, 6, "" + ChatColor.AQUA + ChatColor.BOLD + "Mystic Form" , "");
        ItemStack RAGE = Methods.createItemStack(Material.INK_SACK, 11, "" + ChatColor.GOLD + ChatColor.BOLD + "Rage Form" , "");
        ItemStack SSJ5 = Methods.createItemStack(Material.INK_SACK, 7, "" + ChatColor.GRAY + ChatColor.BOLD + "SSJ 5 Form" , "");
        ItemStack exit = Methods.createItemStack(Material.REDSTONE_BLOCK, 0, "" + ChatColor.DARK_RED + ChatColor.BOLD + "Exit Menu", "");
        ItemStack selectForm = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta selectFormMeta = selectForm.getItemMeta();
        selectFormMeta.setDisplayName(ChatColor.BLUE + "Select Form");
        selectForm.setItemMeta(selectFormMeta);
        inv.setItem(10, SSJ5);
        inv.setItem(11, MY);
        inv.setItem(12, god);
        inv.setItem(13, SK);
        inv.setItem(14, UI);
        inv.setItem(15, EVO);
        inv.setItem(16, RAGE);
        inv.setItem(4, selectForm);
        inv.setItem(26, e);
        inv.setItem(inv.getSize() - 9, e);
        inv.setItem(0, e);
        inv.setItem(8, e);
		inv.setItem(22, exit);
        player.openInventory(inv);
    }

	public static void openSelectFormMenu(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.BLUE + "Select form Menu");
        int help = 0;
        String help1;
        
        ItemStack e = Methods.createItemStack(Material.STAINED_GLASS_PANE, 6, "" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "Created by McShovelYT", "");
        
        if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".GodForm.GodLevel") == null || Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.GodLevel")) == 0) {
        	help = 0;
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.GodLevel")) > 0) {
        	help = 1;
        }
        
        if (help == 1) {
        	help1 = ChatColor.DARK_PURPLE + "Purchased, Level: " + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.GodLevel"));
        } else {
        	help1 = "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Not purchased";
        }
        
    	ItemStack god = Methods.createItemStack(Material.INK_SACK, 5, ChatColor.DARK_PURPLE + "G.O.D Form", help1);
        
        if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".UIForm.UILevel") == null || Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.UILevel")) == 0) {
        	help = 0;
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.UILevel")) > 0) {
        	help = 1;
        }
        
        if (help == 1) {
        	help1 = "" + ChatColor.BOLD + ChatColor.WHITE + "Purchased, Level: " + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.UILevel"));
        } else {
        	help1 = "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Not purchased";
        }
        
        ItemStack UI = Methods.createItemStack(Material.INK_SACK, 15, "" + ChatColor.WHITE + ChatColor.BOLD + "U.I Form", help1);
        
        if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".SKForm.SKLevel") == null || Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.SKLevel")) == 0) {
        	help = 0;
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.SKLevel")) > 0) {
        	help = 1;
        }
        
        if (help == 1) {
        	help1 = ChatColor.RED + "Purchased, Level: " + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.SKLevel"));
        } else {
        	help1 = "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Not purchased";
        }

    	ItemStack SK = Methods.createItemStack(Material.INK_SACK, 1, ChatColor.RED + "S.K Form", help1);
        
    	int help2;
    	int help3;
    	String help4;

        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
    	int Purity = PlayerPersisted.getInt("jrmcAlign");
    	
    	if (Purity >= 51) {
    		help2 = 0;
    	} else {
    		help2 = 1;
    	}
    	
        if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".EVOForm.EVOLevel") == null || Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.EVOLevel")) == 0) {
        	help = 0;
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.EVOLevel")) > 0) {
        	help = 1;
        }
        
        if (help == 1) {
        	if (help2 == 0) {
        		help1 = ChatColor.BLUE + "Purchased, Level: " + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.EVOLevel"));
        	} else {
        		help1 = ChatColor.DARK_PURPLE + "Purchased, Level: " + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.EVOLevel"));
        	}
        } else {
        	help1 = "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Not purchased";
        }
        
        if (help2 == 0) {
        	help3 = 4;
        } else {
        	help3 = 13;
        }
        
        if (help2 == 0) {
        	help4 = ChatColor.BLUE + "Mastered SS Blue Form";
        } else {
        	help4 = ChatColor.DARK_PURPLE + "Mastered SS Rose Form";
        }
        
    	ItemStack EVO = Methods.createItemStack(Material.INK_SACK, help3, help4, help1);
        
        if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".MYForm.MYLevel") == null || Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.MYLevel")) == 0) {
        	help = 0;
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.MYLevel")) > 0) {
        	help = 1;
        }
        
        if (help == 1) {
        	help1 = "" + ChatColor.AQUA + ChatColor.BOLD + "Purchased, Level: " + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.MYLevel"));
        } else {
        	help1 = "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Not purchased";
        }
        
    	ItemStack MY = Methods.createItemStack(Material.INK_SACK, 6, "" + ChatColor.AQUA + ChatColor.BOLD + "Mystic Form", help1);
    	
        if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".RAGEForm.RAGELevel") == null || Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".RAGEForm.RAGELevel")) == 0) {
        	help = 0;
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".RAGEForm.RAGELevel")) > 0) {
        	help = 1;
        }
        
        if (help == 1) {
        	help1 = ChatColor.GOLD + "Purchased, Level: " + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".RAGEForm.RAGELevel"));
        } else {
        	help1 = "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Not purchased";
        }
        
    	ItemStack RAGE = Methods.createItemStack(Material.INK_SACK, 11, ChatColor.GOLD + "Rage Form", help1);
    	
        if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".SS5Form.SS5Level") == null || Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SS5Form.SS5Level")) == 0) {
        	help = 0;
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SS5Form.SS5Level")) > 0) {
        	help = 1;
        }
        
        if (help == 1) {
        	help1 = ChatColor.GRAY + "Purchased, Level: " + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SS5Form.SS5Level"));
        } else {
        	help1 = "" + ChatColor.DARK_GRAY + ChatColor.BOLD + "Not purchased";
        }
        
    	ItemStack SSJ5 = Methods.createItemStack(Material.INK_SACK, 7, ChatColor.GRAY + "SSJ 5 Form", help1);

        
        ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit menu");
        exit.setItemMeta(exitMeta);
        ItemStack deselect = new ItemStack(Material.REDSTONE, 1);
        ItemMeta deselectMeta = exit.getItemMeta();
        deselectMeta.setDisplayName(ChatColor.RED + "Deselect form");
        deselect.setItemMeta(deselectMeta);
        inv.setItem(4, deselect);
        inv.setItem(10, SSJ5);
        inv.setItem(11, MY);
        inv.setItem(12, god);
        inv.setItem(13, SK);
        inv.setItem(14, UI);
        inv.setItem(15, EVO);
        inv.setItem(16, RAGE);
        inv.setItem(22, exit);
        inv.setItem(26, e);
        inv.setItem(inv.getSize() - 9, e);
        inv.setItem(0, e);
        inv.setItem(8, e);
        player.openInventory(inv);
    }

    public static void openGodMenu(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.DARK_PURPLE + "G.O.D Form Menu");
        ItemStack godLvl1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)10);
        ItemMeta godLvl1Meta = godLvl1.getItemMeta();
        godLvl1Meta.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "G.O.D Form lvl 1");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.CreateFirst(plugin, player, godLvl1Meta, ".GodForm.GodLevel", plugin.getConfig().getInt("FormCosts.GOD.Level1"), "" + ChatColor.DARK_PURPLE);
        godLvl1.setItemMeta(godLvl1Meta);
        ItemStack godLvl2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)10);
        ItemMeta godLvl2Meta = godLvl2.getItemMeta();
        godLvl2Meta.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "G.O.D Form lvl 2");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl2Meta, 2, ".GodForm.GodLevel", plugin.getConfig().getInt("FormCosts.GOD.Level2"), "" + ChatColor.DARK_PURPLE);
        godLvl2.setItemMeta(godLvl2Meta);
        ItemStack godLvl3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)10);
        ItemMeta godLvl3Meta = godLvl3.getItemMeta();
        godLvl3Meta.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "G.O.D Form lvl 3");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl3Meta, 3, ".GodForm.GodLevel", plugin.getConfig().getInt("FormCosts.GOD.Level3"), "" + ChatColor.DARK_PURPLE);
        godLvl3.setItemMeta(godLvl3Meta);
        ItemStack godLvl4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)10);
        ItemMeta godLvl4Meta = godLvl4.getItemMeta();
        godLvl4Meta.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "G.O.D Form lvl 4");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl4Meta, 4, ".GodForm.GodLevel", plugin.getConfig().getInt("FormCosts.GOD.Level4"), "" + ChatColor.DARK_PURPLE);
        godLvl4.setItemMeta(godLvl4Meta);
        ItemStack godLvl5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)10);
        ItemMeta godLvl5Meta = godLvl5.getItemMeta();
        godLvl5Meta.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "G.O.D Form lvl 5");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl5Meta, 5, ".GodForm.GodLevel", plugin.getConfig().getInt("FormCosts.GOD.Level5"), "" + ChatColor.DARK_PURPLE);
        godLvl5.setItemMeta(godLvl5Meta);
        ItemStack godTP = new ItemStack(Material.INK_SACK, 1, (short)5);
        ItemMeta godTPMeta = godTP.getItemMeta();
        if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.GodTP") == null) {
            godTPMeta.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "G.O.D TP: " + ChatColor.RESET + ChatColor.DARK_PURPLE + 0);
            plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.GodTP", "0");
        } else {
            godTPMeta.setDisplayName("" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "G.O.D TP: " + ChatColor.RESET + ChatColor.DARK_PURPLE + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.GodTP")));
        }

        godTP.setItemMeta(godTPMeta);
        ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit menu");
        exit.setItemMeta(exitMeta);
        inv.setItem(4, godTP);
        inv.setItem(11, godLvl1);
        inv.setItem(12, godLvl2);
        inv.setItem(13, godLvl3);
        inv.setItem(14, godLvl4);
        inv.setItem(15, godLvl5);
        inv.setItem(22, exit);
        player.openInventory(inv);
    }
    
    public static void openMYMenu(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "" + ChatColor.AQUA + ChatColor.BOLD + "Mystic Form Menu");
        ItemStack godLvl1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3);
        ItemMeta godLvl1Meta = godLvl1.getItemMeta();
        godLvl1Meta.setDisplayName("" + ChatColor.AQUA + ChatColor.BOLD + "Mystic Form lvl 1");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.CreateFirst(plugin, player, godLvl1Meta, ".MYForm.MYLevel", plugin.getConfig().getInt("FormCosts.MY.Level1"), "" + ChatColor.AQUA);
        godLvl1.setItemMeta(godLvl1Meta);
        ItemStack godLvl2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3);
        ItemMeta godLvl2Meta = godLvl2.getItemMeta();
        godLvl2Meta.setDisplayName("" + ChatColor.AQUA + ChatColor.BOLD + "Mystic Form lvl 2");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl2Meta, 2, ".MYForm.MYLevel", plugin.getConfig().getInt("FormCosts.MY.Level2"), "" + ChatColor.AQUA);
        godLvl2.setItemMeta(godLvl2Meta);
        ItemStack godLvl3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3);
        ItemMeta godLvl3Meta = godLvl3.getItemMeta();
        godLvl3Meta.setDisplayName("" + ChatColor.AQUA + ChatColor.BOLD + "Mystic Form lvl 3");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl3Meta, 3, ".MYForm.MYLevel", plugin.getConfig().getInt("FormCosts.MY.Level3"), "" + ChatColor.AQUA);
        godLvl3.setItemMeta(godLvl3Meta);
        ItemStack godLvl4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3);
        ItemMeta godLvl4Meta = godLvl4.getItemMeta();
        godLvl4Meta.setDisplayName("" + ChatColor.AQUA + ChatColor.BOLD + "Mystic Form lvl 4");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl4Meta, 4, ".MYForm.MYLevel", plugin.getConfig().getInt("FormCosts.MY.Level4"), "" + ChatColor.AQUA);
        godLvl4.setItemMeta(godLvl4Meta);
        ItemStack godLvl5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3);
        ItemMeta godLvl5Meta = godLvl5.getItemMeta();
        godLvl5Meta.setDisplayName("" + ChatColor.AQUA + ChatColor.BOLD + "Mystic Form lvl 5");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl5Meta, 5, ".MYForm.MYLevel", plugin.getConfig().getInt("FormCosts.MY.Level5"), "" + ChatColor.AQUA);
        godLvl5.setItemMeta(godLvl5Meta);
        ItemStack godTP = new ItemStack(Material.INK_SACK, 1, (short)6);
        ItemMeta godTPMeta = godTP.getItemMeta();
        if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.MYTP") == null) {
            godTPMeta.setDisplayName("" + ChatColor.AQUA + ChatColor.BOLD + "Mystic TP: " + ChatColor.RESET + ChatColor.AQUA + ChatColor.BOLD + 0);
            plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.MYTP", "0");
        } else {
            godTPMeta.setDisplayName("" + ChatColor.AQUA + ChatColor.BOLD + "Mystic TP: " + ChatColor.RESET + ChatColor.AQUA + ChatColor.BOLD + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.MYTP")));
        }

        godTP.setItemMeta(godTPMeta);
        ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit menu");
        exit.setItemMeta(exitMeta);
        inv.setItem(4, godTP);
        inv.setItem(11, godLvl1);
        inv.setItem(12, godLvl2);
        inv.setItem(13, godLvl3);
        inv.setItem(14, godLvl4);
        inv.setItem(15, godLvl5);
        inv.setItem(22, exit);
        player.openInventory(inv);
    }
    
    public static void openSKMenu(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.RED + "S.K Form Menu");
        ItemStack SKLvl1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
        ItemMeta SKLvl1Meta = SKLvl1.getItemMeta();
        SKLvl1Meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "S.K Form lvl 1");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.CreateFirst(plugin, player, SKLvl1Meta, ".SKForm.SKLevel", plugin.getConfig().getInt("FormCosts.SK.Level1"), "" + ChatColor.RED);
        SKLvl1.setItemMeta(SKLvl1Meta);
        
        ItemStack SKLvl2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
        ItemMeta SKLvl2Meta = SKLvl2.getItemMeta();
        SKLvl2Meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "S.K Form lvl 2");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, SKLvl2Meta, 2, ".SKForm.SKLevel", plugin.getConfig().getInt("FormCosts.SK.Level2"), "" + ChatColor.RED);
        SKLvl2.setItemMeta(SKLvl2Meta);
        
        ItemStack SKLvl3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
        ItemMeta SKLvl3Meta = SKLvl3.getItemMeta();
        SKLvl3Meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "S.K Form lvl 3");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, SKLvl3Meta, 3, ".SKForm.SKLevel", plugin.getConfig().getInt("FormCosts.SK.Level3"), "" + ChatColor.RED);
        SKLvl3.setItemMeta(SKLvl3Meta);
        
        ItemStack SKLvl4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
        ItemMeta SKLvl4Meta = SKLvl4.getItemMeta();
        SKLvl4Meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "S.K Form lvl 4");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, SKLvl4Meta, 4, ".SKForm.SKLevel", plugin.getConfig().getInt("FormCosts.SK.Level4"), "" + ChatColor.RED);
        SKLvl4.setItemMeta(SKLvl4Meta);
        
        ItemStack SKLvl5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
        ItemMeta SKLvl5Meta = SKLvl5.getItemMeta();
        SKLvl5Meta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "S.K Form lvl 5");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, SKLvl5Meta, 5, ".SKForm.SKLevel", plugin.getConfig().getInt("FormCosts.SK.Level5"), "" + ChatColor.RED);
        SKLvl5.setItemMeta(SKLvl5Meta);
        
        ItemStack SKTP = new ItemStack(Material.INK_SACK, 1, (short)1);
        ItemMeta SKTPMeta = SKTP.getItemMeta();
        if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.SKTP") == null) {
            SKTPMeta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "S.K TP: " + ChatColor.RESET + ChatColor.RED + 0);
            plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.SKTP", "0");
        } else {
            SKTPMeta.setDisplayName("" + ChatColor.RED + ChatColor.BOLD + "S.K TP: " + ChatColor.RESET + ChatColor.RED + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.SKTP")));
        }

        SKTP.setItemMeta(SKTPMeta);
        ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit menu");
        exit.setItemMeta(exitMeta);
        inv.setItem(4, SKTP);
        inv.setItem(11, SKLvl1);
        inv.setItem(12, SKLvl2);
        inv.setItem(13, SKLvl3);
        inv.setItem(14, SKLvl4);
        inv.setItem(15, SKLvl5);
        inv.setItem(22, exit);
        player.openInventory(inv);
    }

    public static void openUIMenu(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "" + ChatColor.RESET + ChatColor.GRAY + ChatColor.BOLD + "U.I Form Menu");
        ItemStack UILvl1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0);
        ItemMeta UILvl1Meta = UILvl1.getItemMeta();
        UILvl1Meta.setDisplayName("" + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I Form lvl 1");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.CreateFirst(plugin, player, UILvl1Meta, ".UIForm.UILevel", plugin.getConfig().getInt("FormCosts.UI.Level1"), "" + ChatColor.WHITE);
        UILvl1.setItemMeta(UILvl1Meta);
        ItemStack UILvl2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0);
        ItemMeta UILvl2Meta = UILvl2.getItemMeta();
        UILvl2Meta.setDisplayName("" + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I Form lvl 2");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, UILvl2Meta, 2, ".UIForm.UILevel", plugin.getConfig().getInt("FormCosts.UI.Level2"), "" + ChatColor.WHITE);
        UILvl2.setItemMeta(UILvl2Meta);
        ItemStack UILvl3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0);
        ItemMeta UILvl3Meta = UILvl3.getItemMeta();
        UILvl3Meta.setDisplayName("" + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I Form lvl 3");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, UILvl3Meta, 3, ".UIForm.UILevel", plugin.getConfig().getInt("FormCosts.UI.Level3"), "" + ChatColor.WHITE);
        UILvl3.setItemMeta(UILvl3Meta);
        ItemStack UILvl4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0);
        ItemMeta UILvl4Meta = UILvl4.getItemMeta();
        UILvl4Meta.setDisplayName("" + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I Form lvl 4");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, UILvl4Meta, 4, ".UIForm.UILevel", plugin.getConfig().getInt("FormCosts.UI.Level4"), "" + ChatColor.WHITE);
        UILvl4.setItemMeta(UILvl4Meta);
        ItemStack UILvl5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0);
        ItemMeta UILvl5Meta = UILvl5.getItemMeta();
        UILvl5Meta.setDisplayName("" + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "M.U.I Form");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, UILvl5Meta, 5, ".UIForm.UILevel", plugin.getConfig().getInt("FormCosts.UI.Level5"), "" + ChatColor.WHITE);
        UILvl5.setItemMeta(UILvl5Meta);
        ItemStack UITP = new ItemStack(Material.INK_SACK, 1, (short)15);
        ItemMeta UITPMeta = UITP.getItemMeta();
        if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.UITP") == null) {
            UITPMeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "U.I TP: " + ChatColor.RESET + ChatColor.WHITE + 0);
            plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.UITP", "0");
        } else {
            UITPMeta.setDisplayName("" + ChatColor.WHITE + ChatColor.BOLD + "U.I TP: " + ChatColor.RESET + ChatColor.WHITE + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.UITP")));
        }

        UITP.setItemMeta(UITPMeta);
        ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit menu");
        exit.setItemMeta(exitMeta);
        inv.setItem(4, UITP);
        inv.setItem(11, UILvl1);
        inv.setItem(12, UILvl2);
        inv.setItem(13, UILvl3);
        inv.setItem(14, UILvl4);
        inv.setItem(15, UILvl5);
        inv.setItem(22, exit);
        player.openInventory(inv);
    }
    
    public static void openEVOMenu(Player player) {
    	
    	int help2;
    	int help3;
    	String help4;
    	String help5;
    	String help6;
    	int help7;
    	String help8;
    	
        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
    	int Purity = PlayerPersisted.getInt("jrmcAlign");
    	
    	if (Purity >= 51) {
    		help2 = 0;
    	} else {
    		help2 = 1;
    	}
    	
        if (help2 == 0) {
        	help3 = 4;
        } else {
        	help3 = 13;
        }
        
        if (help2 == 0) {
        	help4 = ChatColor.BLUE + "Mastered SS Blue Form Menu";
        } else {
        	help4 = ChatColor.DARK_PURPLE + "Mastered SS Rose Form Menu";
        }
        
        if (help2 == 0) {
        	help5 = "" + ChatColor.BLUE + ChatColor.BOLD + "Mastered SS Blue TP: " + ChatColor.RESET + ChatColor.BLUE;
        } else {
        	help5 = "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Mastered SS Rose TP: " + ChatColor.RESET + ChatColor.DARK_PURPLE;
        }
        
        if (help2 == 0) {
        	help6 = "" + ChatColor.BLUE;
        } else {
        	help6 = "" + ChatColor.DARK_PURPLE;
        }
        
        if (help2 == 0) {
        	help7 = 11;
        } else {
        	help7 = 6;
        }
        
        if (help2 == 0) {
        	help8 = "" + ChatColor.BLUE + ChatColor.BOLD + "Mastered SS Blue Form";
        } else {
        	help8 = "" + ChatColor.DARK_PURPLE + ChatColor.BOLD + "Mastered SS Rose Form";
        }
    	
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, help4);
        
        ItemStack godLvl1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)help7);
        ItemMeta godLvl1Meta = godLvl1.getItemMeta();
        godLvl1Meta.setDisplayName(help8 + " lvl 1");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.CreateFirst(plugin, player, godLvl1Meta, ".EVOForm.EVOLevel", plugin.getConfig().getInt("FormCosts.EVO.Level1"), help6);
        godLvl1.setItemMeta(godLvl1Meta);
        ItemStack godLvl2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)help7);
        ItemMeta godLvl2Meta = godLvl2.getItemMeta();
        godLvl2Meta.setDisplayName(help8 + " lvl 2");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl2Meta, 2, ".EVOForm.EVOLevel", plugin.getConfig().getInt("FormCosts.EVO.Level2"), help6);
        godLvl2.setItemMeta(godLvl2Meta);
        ItemStack godLvl3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)help7);
        ItemMeta godLvl3Meta = godLvl3.getItemMeta();
        godLvl3Meta.setDisplayName(help8 + " lvl 3");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl3Meta, 3, ".EVOForm.EVOLevel", plugin.getConfig().getInt("FormCosts.EVO.Level3"), help6);
        godLvl3.setItemMeta(godLvl3Meta);
        ItemStack godLvl4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)help7);
        ItemMeta godLvl4Meta = godLvl4.getItemMeta();
        godLvl4Meta.setDisplayName(help8 + " lvl 4");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl4Meta, 4, ".EVOForm.EVOLevel", plugin.getConfig().getInt("FormCosts.EVO.Level4"), help6);
        godLvl4.setItemMeta(godLvl4Meta);
        ItemStack godLvl5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)help7);
        ItemMeta godLvl5Meta = godLvl5.getItemMeta();
        godLvl5Meta.setDisplayName(help8 + " lvl 5");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl5Meta, 5, ".EVOForm.EVOLevel", plugin.getConfig().getInt("FormCosts.EVO.Level5"), help6);
        godLvl5.setItemMeta(godLvl5Meta);
        ItemStack godTP = new ItemStack(Material.INK_SACK, 1, (short)help3);
        ItemMeta godTPMeta = godTP.getItemMeta();
        if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.EVOTP") == null) {
            godTPMeta.setDisplayName(help5 + 0);
            plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.EVOTP", "0");
        } else {
            godTPMeta.setDisplayName(help5 + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.EVOTP")));
        }

        godTP.setItemMeta(godTPMeta);
        ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit menu");
        exit.setItemMeta(exitMeta);
        inv.setItem(4, godTP);
        inv.setItem(11, godLvl1);
        inv.setItem(12, godLvl2);
        inv.setItem(13, godLvl3);
        inv.setItem(14, godLvl4);
        inv.setItem(15, godLvl5);
        inv.setItem(22, exit);
        player.openInventory(inv);
    }
    
    public static void openRAGEMenu(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.GOLD + "Rage Form Menu");
        ItemStack godLvl1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
        ItemMeta godLvl1Meta = godLvl1.getItemMeta();
        godLvl1Meta.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "Rage Form lvl 1");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.CreateFirst(plugin, player, godLvl1Meta, ".RAGEForm.RAGELevel", plugin.getConfig().getInt("FormCosts.RAGE.Level1"), "" + ChatColor.GOLD);
        godLvl1.setItemMeta(godLvl1Meta);
        ItemStack godLvl2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
        ItemMeta godLvl2Meta = godLvl2.getItemMeta();
        godLvl2Meta.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "Rage Form lvl 2");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl2Meta, 2, ".RAGEForm.RAGELevel", plugin.getConfig().getInt("FormCosts.RAGE.Level2"), "" + ChatColor.GOLD);
        godLvl2.setItemMeta(godLvl2Meta);
        ItemStack godLvl3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
        ItemMeta godLvl3Meta = godLvl3.getItemMeta();
        godLvl3Meta.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "Rage Form lvl 3");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl3Meta, 3, ".RAGEForm.RAGELevel", plugin.getConfig().getInt("FormCosts.RAGE.Level3"), "" + ChatColor.GOLD);
        godLvl3.setItemMeta(godLvl3Meta);
        ItemStack godLvl4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
        ItemMeta godLvl4Meta = godLvl4.getItemMeta();
        godLvl4Meta.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "Rage Form lvl 4");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl4Meta, 4, ".RAGEForm.RAGELevel", plugin.getConfig().getInt("FormCosts.RAGE.Level4"), "" + ChatColor.GOLD);
        godLvl4.setItemMeta(godLvl4Meta);
        ItemStack godLvl5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
        ItemMeta godLvl5Meta = godLvl5.getItemMeta();
        godLvl5Meta.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "Rage Form lvl 5");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl5Meta, 5, ".RAGEForm.RAGELevel", plugin.getConfig().getInt("FormCosts.RAGE.Level5"), "" + ChatColor.GOLD);
        godLvl5.setItemMeta(godLvl5Meta);
        ItemStack godTP = new ItemStack(Material.INK_SACK, 1, (short)11);
        ItemMeta godTPMeta = godTP.getItemMeta();
        if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".RAGEForm.RAGETP") == null) {
            godTPMeta.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "Rage TP: " + ChatColor.RESET + ChatColor.GOLD + 0);
            plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".RAGEForm.RAGETP", "0");
        } else {
            godTPMeta.setDisplayName("" + ChatColor.GOLD + ChatColor.BOLD + "Rage TP: " + ChatColor.RESET + ChatColor.GOLD + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".RAGEForm.RAGETP")));
        }

        godTP.setItemMeta(godTPMeta);
        ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit menu");
        exit.setItemMeta(exitMeta);
        inv.setItem(4, godTP);
        inv.setItem(11, godLvl1);
        inv.setItem(12, godLvl2);
        inv.setItem(13, godLvl3);
        inv.setItem(14, godLvl4);
        inv.setItem(15, godLvl5);
        inv.setItem(22, exit);
        player.openInventory(inv);
    }
    
    public static void openSSJ5Menu(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, ChatColor.GRAY + "SSJ 5 Form Menu");
        ItemStack godLvl1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
        ItemMeta godLvl1Meta = godLvl1.getItemMeta();
        godLvl1Meta.setDisplayName("" + ChatColor.GRAY + ChatColor.BOLD + "SSJ 5 Form lvl 1");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.CreateFirst(plugin, player, godLvl1Meta, ".SS5Form.SS5Level", plugin.getConfig().getInt("FormCosts.SS5.Level1"), "" + ChatColor.GRAY);
        godLvl1.setItemMeta(godLvl1Meta);
        ItemStack godLvl2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
        ItemMeta godLvl2Meta = godLvl2.getItemMeta();
        godLvl2Meta.setDisplayName("" + ChatColor.GRAY + ChatColor.BOLD + "SSJ 5 Form lvl 2");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl2Meta, 2, ".SS5Form.SS5Level", plugin.getConfig().getInt("FormCosts.SS5.Level2"), "" + ChatColor.GRAY);
        godLvl2.setItemMeta(godLvl2Meta);
        ItemStack godLvl3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
        ItemMeta godLvl3Meta = godLvl3.getItemMeta();
        godLvl3Meta.setDisplayName("" + ChatColor.GRAY + ChatColor.BOLD + "SSJ 5 Form lvl 3");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl3Meta, 3, ".SS5Form.SS5Level", plugin.getConfig().getInt("FormCosts.SS5.Level3"), "" + ChatColor.GRAY);
        godLvl3.setItemMeta(godLvl3Meta);
        ItemStack godLvl4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
        ItemMeta godLvl4Meta = godLvl4.getItemMeta();
        godLvl4Meta.setDisplayName("" + ChatColor.GRAY + ChatColor.BOLD + "SSJ 5 Form lvl 4");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl4Meta, 4, ".SS5Form.SS5Level", plugin.getConfig().getInt("FormCosts.SS5.Level4"), "" + ChatColor.GRAY);
        godLvl4.setItemMeta(godLvl4Meta);
        ItemStack godLvl5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
        ItemMeta godLvl5Meta = godLvl5.getItemMeta();
        godLvl5Meta.setDisplayName("" + ChatColor.GRAY + ChatColor.BOLD + "SSJ 5 Form lvl 5");
        me.McShovelYT.KosmicForms.Old.Methods.CreateUpgradeItemLogic.Create(plugin, player, godLvl5Meta, 5, ".SS5Form.SS5Level", plugin.getConfig().getInt("FormCosts.SS5.Level5"), "" + ChatColor.GRAY);
        godLvl5.setItemMeta(godLvl5Meta);
        ItemStack godTP = new ItemStack(Material.INK_SACK, 1, (short)7);
        ItemMeta godTPMeta = godTP.getItemMeta();
        if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SS5Form.SS5TP") == null) {
            godTPMeta.setDisplayName("" + ChatColor.GRAY + ChatColor.BOLD + "SSJ 5 TP: " + ChatColor.RESET + ChatColor.GRAY + 0);
            plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SS5Form.SS5TP", "0");
        } else {
            godTPMeta.setDisplayName("" + ChatColor.GRAY + ChatColor.BOLD + "SSJ 5 TP: " + ChatColor.RESET + ChatColor.GRAY + Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SS5Form.SS5TP")));
        }

        godTP.setItemMeta(godTPMeta);
        ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK, 1);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit menu");
        exit.setItemMeta(exitMeta);
        inv.setItem(4, godTP);
        inv.setItem(11, godLvl1);
        inv.setItem(12, godLvl2);
        inv.setItem(13, godLvl3);
        inv.setItem(14, godLvl4);
        inv.setItem(15, godLvl5);
        inv.setItem(22, exit);
        player.openInventory(inv);
    }

    @EventHandler
    public void onInventoryCLick(InventoryClickEvent e) {
        Player player;
        if (e.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Kosmic Forms Menu")) {
            player = (Player)e.getWhoClicked();
            e.setCancelled(true);
            if (e.getSlot() == 10) {
                openSSJ5Menu(player);
            }
            
            if (e.getSlot() == 11) {
                openMYMenu(player);
            }
            
            if (e.getSlot() == 12) {
                openGodMenu(player);
            }
            
            if (e.getSlot() == 13) {
                openSKMenu(player);
            }

            if (e.getSlot() == 14) {
                openUIMenu(player);
            }
            
            if (e.getSlot() == 15) {
                openEVOMenu(player);
            }
            
            if (e.getSlot() == 16) {
                openRAGEMenu(player);
            }

            if (e.getSlot() == 4) {
                openSelectFormMenu(player);
            }
            
            if (e.getSlot() == 22) {
                player.closeInventory();
            }
        }

        if (e.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Select form Menu")) {
            player = (Player)e.getWhoClicked();
            e.setCancelled(true);
            if (e.getSlot() == 12) {
                if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".GodForm.GodLevel") == null) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.GodLevel")) > 0) {
                    if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm") == "god") {
                        player.sendMessage(ChatColor.RED + "Form already selected");
                    } else {
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm", "god");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.isSKEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.isMYEnabled", false);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.BLUE + "You have selected " + ChatColor.DARK_PURPLE + "G.O.D form");
                        player.closeInventory();
                    }
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.GodLevel")) == 0) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                }
            }

            if (e.getSlot() == 14) {
                if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".UIForm.UILevel") == null) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.UILevel")) > 0) {
                    if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm") == "UI") {
                        player.sendMessage(ChatColor.RED + "Form already selected");
                    } else {
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm", "UI");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.isSKEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.isMYEnabled", false);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.BLUE + "You have selected " + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I form");
                        player.closeInventory();
                    }
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.UILevel")) == 0) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                }
            }
            
            if (e.getSlot() == 15) {
                if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".EVOForm.EVOLevel") == null) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.EVOLevel")) > 0) {
                    if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm") == "EVO") {
                        player.sendMessage(ChatColor.RED + "Form already selected");
                    } else {
                    	int help2;
                    	String help4;
                    	
                        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                    	int Purity = PlayerPersisted.getInt("jrmcAlign");
                    	
                    	if (Purity >= 51) {
                    		help2 = 0;
                    	} else {
                    		help2 = 1;
                    	}
                    	
                        if (help2 == 0) {
                        	help4 = ChatColor.BLUE + "Mastered SS Blue Form";
                        } else {
                        	help4 = ChatColor.DARK_PURPLE + "Mastered SS Rose Form";
                        }
                        
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm", "EVO");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.isSKEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.isMYEnabled", false);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.BLUE + "You have selected " + ChatColor.RESET + help4);
                        player.closeInventory();
                    }
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.EVOLevel")) == 0) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                }
            }
            
            if (e.getSlot() == 13) {
                if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".SKForm.SKLevel") == null) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.SKLevel")) > 0) {
                    if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm") == "SK") {
                        player.sendMessage(ChatColor.RED + "Form already selected");
                    } else {
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm", "SK");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.isMYEnabled", false);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.BLUE + "You have selected " + ChatColor.RESET + ChatColor.RED + "S.K form");
                        player.closeInventory();
                    }
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.SKLevel")) == 0) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                }
            }
            
            if (e.getSlot() == 11) {
                if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".MYForm.MYLevel") == null) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.MYLevel")) > 0) {
                    if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm") == "MY") {
                        player.sendMessage(ChatColor.RED + "Form already selected");
                    } else {
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm", "MY");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.isSKEnabled", false);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.BLUE + "You have selected " + ChatColor.RESET + ChatColor.AQUA + "Mystic form");
                        player.closeInventory();
                    }
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.MYLevel")) == 0) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                }
            }
            
            if (e.getSlot() == 16) {
                if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".RAGEForm.RAGELevel") == null) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".RAGEForm.RAGELevel")) > 0) {
                    if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm") == "RAGE") {
                        player.sendMessage(ChatColor.RED + "Form already selected");
                    } else {
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm", "RAGE");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.isSKEnabled", false);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.BLUE + "You have selected " + ChatColor.RESET + ChatColor.GOLD + "Rage form");
                        player.closeInventory();
                    }
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".RAGEForm.RAGELevel")) == 0) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                }
            }
            
            if (e.getSlot() == 10) {
                if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".SS5Form.SS5Level") == null) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SS5Form.SS5Level")) > 0) {
                    if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm") == "SS5") {
                        player.sendMessage(ChatColor.RED + "Form already selected");
                    } else {
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm", "SS5");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", false);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.isSKEnabled", false);
                        plugin.saveConfig();
                        player.sendMessage(ChatColor.BLUE + "You have selected " + ChatColor.RESET + ChatColor.GRAY + "SSJ 5 form");
                        player.closeInventory();
                    }
                } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SS5Form.SS5Level")) == 0) {
                    player.sendMessage(ChatColor.RED + "You do not have this form");
                }
            }

            if (e.getSlot() == 22) {
                openMenu(player);
            }

            if (e.getSlot() == 4) {
                if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm") == null) {
                    player.sendMessage(ChatColor.RED + "No form selected");
                } else {
                    if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("god")) {
                        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                        PlayerPersisted.put("jrmcState", 0);
                        PlayerPersisted.put("jrmcTech1", "");
                        Forgadata.put("PlayerPersisted", PlayerPersisted);
                        NBTManager.getInstance().writeForgeData(player, Forgadata);
                        player.sendMessage(ChatColor.BLUE + "You have deselected " + ChatColor.DARK_PURPLE + "G.O.D form");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", false);
                    } else if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("ui")) {
                    	if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".UIForm.UILevel")) < 5) {
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            PlayerPersisted.put("jrmcStatusEff", "");
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                            player.sendMessage(ChatColor.BLUE + "You have deselected " + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I form");
                            plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", false);
                    	} else {
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            PlayerPersisted.put("jrmcStatusEff", "");
                            int Race = PlayerPersisted.getInt("jrmcRace");
                            if (Race == 0) {
                            	PlayerPersisted.put("jrmcDNS", plugin.getConfig().get("PlayerData." + player.getUniqueId() + ".DefaultHairColor"));
                            }
                            if (Race == 1) {
                            	PlayerPersisted.put("jrmcDNS", plugin.getConfig().get("PlayerData." + player.getUniqueId() + ".DefaultHairColor"));
                            }
                            if (Race == 2) {
                            	PlayerPersisted.put("jrmcDNS", plugin.getConfig().get("PlayerData." + player.getUniqueId() + ".DefaultHairColor"));
                            }
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                    	}
                        player.sendMessage(ChatColor.BLUE + "You have deselected " + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "M.U.I form");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", false);
                    } else if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("SK")) {
                        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                        PlayerPersisted.put("jrmcState2", "");
                        Forgadata.put("PlayerPersisted", PlayerPersisted);
                        NBTManager.getInstance().writeForgeData(player, Forgadata);
                        player.sendMessage(ChatColor.BLUE + "You have deselected " + ChatColor.RESET + ChatColor.RED + "S.K form");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SKForm.isSKEnabled", false);
                    } else if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("EVO")) {
                    	int help2;
                    	String help4;
                    	
                        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                    	int Purity = PlayerPersisted.getInt("jrmcAlign");
                    	
                    	if (Purity >= 51) {
                    		help2 = 0;
                    	} else {
                    		help2 = 1;
                    	}
                    	
                        if (help2 == 0) {
                        	help4 = ChatColor.BLUE + "Mastered SS Blue Form";
                        } else {
                        	help4 = ChatColor.DARK_PURPLE + "Mastered SS Rose Form";
                        }
                        PlayerPersisted.put("jrmcState", "");
                        PlayerPersisted.put("jrmcStatusEff", "");
                        Forgadata.put("PlayerPersisted", PlayerPersisted);
                        NBTManager.getInstance().writeForgeData(player, Forgadata);
                        player.sendMessage(ChatColor.BLUE + "You have deselected " + ChatColor.RESET + help4);
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", false);
                    } else if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("MY")) {
                        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                        PlayerPersisted.put("jrmcStatusEff", "");
                        PlayerPersisted.put("jrmcRelease", 100);
                        int var = plugin.getConfig().getInt("PlayerData." + player.getUniqueId() + ".DefaultAuraColor");
                        PlayerPersisted.put("jrmcAuraColor", var);
                        Forgadata.put("PlayerPersisted", PlayerPersisted);
                        NBTManager.getInstance().writeForgeData(player, Forgadata);
                        player.sendMessage(ChatColor.BLUE + "You have deselected " + ChatColor.RESET + ChatColor.BOLD + "MY form");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.isMYEnabled", false);
                    } else if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("RAGE")) {
                        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                        PlayerPersisted.put("jrmcStatusEff", "");
                        PlayerPersisted.put("jrmcState", 0);
                        Forgadata.put("PlayerPersisted", PlayerPersisted);
                        NBTManager.getInstance().writeForgeData(player, Forgadata);
                        player.sendMessage(ChatColor.BLUE + "You have deselected " + ChatColor.RESET + ChatColor.GOLD + "Rage form");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".MYForm.isMYEnabled", false);
                    } else if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("SS5")) {
                        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                        PlayerPersisted.put("jrmcStatusEff", "");
                        PlayerPersisted.put("jrmcState", 0);
                        Forgadata.put("PlayerPersisted", PlayerPersisted);
                        NBTManager.getInstance().writeForgeData(player, Forgadata);
                        player.sendMessage(ChatColor.BLUE + "You have deselected " + ChatColor.RESET + ChatColor.GRAY + "SSJ 5 form");
                        plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SS5Form.isSS5Enabled", false);
                    }

                    plugin.getConfig().set("PlayerData." + player.getPlayer().getUniqueId() + ".SelectedForm", (Object)null);
                    plugin.saveConfig();
                    player.closeInventory();
                }
            }
        }

        if (e.getInventory().getName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "G.O.D Form Menu")) {
            player = (Player)e.getWhoClicked();
            e.setCancelled(true);
            if (e.getSlot() == 11) {
                CreateUpgradeLogic.CreateFirst(player, ".GodForm.GodLevel", ".GodForm.GodTP", plugin.getConfig().getInt("FormCosts.GOD.Level1"), ChatColor.DARK_PURPLE + "G.O.D Form", "G.O.D", 1);
            }

            if (e.getSlot() == 12) {
                CreateUpgradeLogic.Create(player, ".GodForm.GodLevel", ".GodForm.GodTP", plugin.getConfig().getInt("FormCosts.GOD.Level2"), ChatColor.DARK_PURPLE + "G.O.D Form", "G.O.D", 2);
            }

            if (e.getSlot() == 13) {
                CreateUpgradeLogic.Create(player, ".GodForm.GodLevel", ".GodForm.GodTP", plugin.getConfig().getInt("FormCosts.GOD.Level3"), ChatColor.DARK_PURPLE + "G.O.D Form", "G.O.D", 3);
            }

            if (e.getSlot() == 14) {
                CreateUpgradeLogic.Create(player, ".GodForm.GodLevel", ".GodForm.GodTP", plugin.getConfig().getInt("FormCosts.GOD.Level4"), ChatColor.DARK_PURPLE + "G.O.D Form", "G.O.D", 4);
            }

            if (e.getSlot() == 15) {
                CreateUpgradeLogic.Create(player, ".GodForm.GodLevel", ".GodForm.GodTP", plugin.getConfig().getInt("FormCosts.GOD.Level5"), ChatColor.DARK_PURPLE + "G.O.D Form", "G.O.D", 5);
            }

            if (e.getSlot() == 22) {
                openMenu(player);
            }
        }

        if (e.getInventory().getName().equalsIgnoreCase("" + ChatColor.RESET + ChatColor.GRAY + ChatColor.BOLD + "U.I Form Menu")) {
            player = (Player)e.getWhoClicked();
            e.setCancelled(true);
            if (e.getSlot() == 11) {
                CreateUpgradeLogic.CreateFirst(player, ".UIForm.UILevel", ".UIForm.UITP", plugin.getConfig().getInt("FormCosts.UI.Level1"), "" + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I Form", "U.I", 1);
            }

            if (e.getSlot() == 12) {
                CreateUpgradeLogic.Create(player, ".UIForm.UILevel", ".UIForm.UITP", plugin.getConfig().getInt("FormCosts.UI.Level2"), "" + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I Form", "U.I", 2);
            }

            if (e.getSlot() == 13) {
                CreateUpgradeLogic.Create(player, ".UIForm.UILevel", ".UIForm.UITP", plugin.getConfig().getInt("FormCosts.UI.Level3"), "" + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I Form", "U.I", 3);
            }

            if (e.getSlot() == 14) {
                CreateUpgradeLogic.Create(player, ".UIForm.UILevel", ".UIForm.UITP", plugin.getConfig().getInt("FormCosts.UI.Level4"), "" + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I Form", "U.I", 4);
            }

            if (e.getSlot() == 15) {
                CreateUpgradeLogic.Create(player, ".UIForm.UILevel", ".UIForm.UITP", plugin.getConfig().getInt("FormCosts.UI.Level5"), "" + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "M.U.I Form", "M.U.I", 5);
            }

            if (e.getSlot() == 22) {
                openMenu(player);
            }
        }
        
        if (e.getInventory().getName().equalsIgnoreCase(ChatColor.RED + "S.K Form Menu")) {
            player = (Player)e.getWhoClicked();
            e.setCancelled(true);
            if (e.getSlot() == 11) {
                CreateUpgradeLogic.CreateFirst(player, ".SKForm.SKLevel", ".SKForm.SKTP", plugin.getConfig().getInt("FormCosts.SK.Level1"), "" + ChatColor.RESET + ChatColor.RED + "S.K Form", "S.K", 1);
            }

            if (e.getSlot() == 12) {
                CreateUpgradeLogic.CreateFirst(player, ".SKForm.SKLevel", ".SKForm.SKTP", plugin.getConfig().getInt("FormCosts.SK.Level2"), "" + ChatColor.RESET + ChatColor.RED + "S.K Form", "S.K", 2);
            }

            if (e.getSlot() == 13) {
                CreateUpgradeLogic.CreateFirst(player, ".SKForm.SKLevel", ".SKForm.SKTP", plugin.getConfig().getInt("FormCosts.SK.Level3"), "" + ChatColor.RESET + ChatColor.RED + "S.K Form", "S.K", 3);
            }

            if (e.getSlot() == 14) {
                CreateUpgradeLogic.CreateFirst(player, ".SKForm.SKLevel", ".SKForm.SKTP", plugin.getConfig().getInt("FormCosts.SK.Level4"), "" + ChatColor.RESET + ChatColor.RED + "S.K Form", "S.K", 4);
            }

            if (e.getSlot() == 15) {
                CreateUpgradeLogic.CreateFirst(player, ".SKForm.SKLevel", ".SKForm.SKTP", plugin.getConfig().getInt("FormCosts.SK.Level5"), "" + ChatColor.RESET + ChatColor.RED + "S.K Form", "S.K", 5);
            }

            if (e.getSlot() == 22) {
                openMenu(player);
            }
        }
        
        if (e.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "Mastered SS Blue Form Menu") || e.getInventory().getName().equalsIgnoreCase(ChatColor.DARK_PURPLE + "Mastered SS Rose Form Menu")) {
            player = (Player)e.getWhoClicked();
        	int help2;
        	String help3;
        	String help4;
        	
            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
        	int Purity = PlayerPersisted.getInt("jrmcAlign");
        	
        	if (Purity >= 51) {
        		help2 = 0;
        	} else {
        		help2 = 1;
        	}
        	
            if (help2 == 0) {
            	help4 = ChatColor.BLUE + "Mastered SS Blue Form";
            } else {
            	help4 = ChatColor.DARK_PURPLE + "Mastered SS Rose Form";
            }
            
            if (help2 == 0) {
            	help3 = "Mastered SS Blue";
            } else {
            	help3 = "Mastered SS Rose";
            }
        	
            e.setCancelled(true);
            if (e.getSlot() == 11) {
                CreateUpgradeLogic.CreateFirst(player, ".EVOForm.EVOLevel", ".EVOForm.EVOTP", plugin.getConfig().getInt("FormCosts.EVO.Level1"), help4, help3, 1);
            }

            if (e.getSlot() == 12) {
                CreateUpgradeLogic.CreateFirst(player, ".EVOForm.EVOLevel", ".EVOForm.EVOTP", plugin.getConfig().getInt("FormCosts.EVO.Level2"), help4, help3, 2);
            }

            if (e.getSlot() == 13) {
                CreateUpgradeLogic.CreateFirst(player, ".EVOForm.EVOLevel", ".EVOForm.EVOTP", plugin.getConfig().getInt("FormCosts.EVO.Level3"), help4, help3, 3);
            }

            if (e.getSlot() == 14) {
                CreateUpgradeLogic.CreateFirst(player, ".EVOForm.EVOLevel", ".EVOForm.EVOTP", plugin.getConfig().getInt("FormCosts.EVO.Level4"), help4, help3, 4);
            }

            if (e.getSlot() == 15) {
                CreateUpgradeLogic.CreateFirst(player, ".EVOForm.EVOLevel", ".EVOForm.EVOTP", plugin.getConfig().getInt("FormCosts.EVO.Level5"), help4, help3, 5);
            }

            if (e.getSlot() == 22) {
                openMenu(player);
            }
        }
        
        if (e.getInventory().getName().equalsIgnoreCase("" + ChatColor.AQUA + ChatColor.BOLD + "Mystic Form Menu")) {
            player = (Player)e.getWhoClicked();
            e.setCancelled(true);
            if (e.getSlot() == 11) {
                CreateUpgradeLogic.CreateFirst(player, ".MYForm.MYLevel", ".MYForm.MYTP", plugin.getConfig().getInt("FormCosts.MY.Level1"), "" + ChatColor.RESET + ChatColor.BLUE + "MY Form", "MY", 1);
            }

            if (e.getSlot() == 12) {
                CreateUpgradeLogic.CreateFirst(player, ".MYForm.MYLevel", ".MYForm.MYTP", plugin.getConfig().getInt("FormCosts.MY.Level2"), "" + ChatColor.RESET + ChatColor.BLUE + "MY Form", "MY", 2);
            }

            if (e.getSlot() == 13) {
                CreateUpgradeLogic.CreateFirst(player, ".MYForm.MYLevel", ".MYForm.MYTP", plugin.getConfig().getInt("FormCosts.MY.Level3"), "" + ChatColor.RESET + ChatColor.BLUE + "MY Form", "MY", 3);
            }

            if (e.getSlot() == 14) {
                CreateUpgradeLogic.CreateFirst(player, ".MYForm.MYLevel", ".MYForm.MYTP", plugin.getConfig().getInt("FormCosts.MY.Level4"), "" + ChatColor.RESET + ChatColor.BLUE + "MY Form", "MY", 4);
            }

            if (e.getSlot() == 15) {
                CreateUpgradeLogic.CreateFirst(player, ".MYForm.MYLevel", ".MYForm.MYTP", plugin.getConfig().getInt("FormCosts.MY.Level5"), "" + ChatColor.RESET + ChatColor.BLUE + "MY Form", "MY", 5);
            }

            if (e.getSlot() == 22) {
                openMenu(player);
            }
        }
        
        if (e.getInventory().getName().equalsIgnoreCase(ChatColor.GOLD + "Rage Form Menu")) {
            player = (Player)e.getWhoClicked();
            e.setCancelled(true);
            if (e.getSlot() == 11) {
                CreateUpgradeLogic.CreateFirst(player, ".RAGEForm.RAGELevel", ".RAGEForm.RAGETP", plugin.getConfig().getInt("FormCosts.RAGE.Level1"), "" + ChatColor.RESET + ChatColor.GOLD + "RAGE Form", "RAGE", 1);
            }

            if (e.getSlot() == 12) {
                CreateUpgradeLogic.CreateFirst(player, ".RAGEForm.RAGELevel", ".RAGEForm.RAGETP", plugin.getConfig().getInt("FormCosts.RAGE.Level2"), "" + ChatColor.RESET + ChatColor.GOLD + "RAGE Form", "RAGE", 2);
            }

            if (e.getSlot() == 13) {
                CreateUpgradeLogic.CreateFirst(player, ".RAGEForm.RAGELevel", ".RAGEForm.RAGETP", plugin.getConfig().getInt("FormCosts.RAGE.Level3"), "" + ChatColor.RESET + ChatColor.GOLD + "RAGE Form", "RAGE", 3);
            }

            if (e.getSlot() == 14) {
                CreateUpgradeLogic.CreateFirst(player, ".RAGEForm.RAGELevel", ".RAGEForm.RAGETP", plugin.getConfig().getInt("FormCosts.RAGE.Level4"), "" + ChatColor.RESET + ChatColor.GOLD + "RAGE Form", "RAGE", 4);
            }

            if (e.getSlot() == 15) {
                CreateUpgradeLogic.CreateFirst(player, ".RAGEForm.RAGELevel", ".RAGEForm.RAGETP", plugin.getConfig().getInt("FormCosts.RAGE.Level5"), "" + ChatColor.RESET + ChatColor.GOLD + "RAGE Form", "RAGE", 5);
            }

            if (e.getSlot() == 22) {
                openMenu(player);
            }
        }
        
        if (e.getInventory().getName().equalsIgnoreCase(ChatColor.GRAY + "SSJ 5 Form Menu")) {
            player = (Player)e.getWhoClicked();
            e.setCancelled(true);
            if (e.getSlot() == 11) {
                CreateUpgradeLogic.CreateFirst(player, ".SS5Form.SS5Level", ".SS5Form.SS5TP", plugin.getConfig().getInt("FormCosts.SS5.Level1"), "" + ChatColor.RESET + ChatColor.GRAY + "SSJ 5 Form", "SSJ 5", 1);
            }

            if (e.getSlot() == 12) {
                CreateUpgradeLogic.CreateFirst(player, ".SS5Form.SS5Level", ".SS5Form.SS5TP", plugin.getConfig().getInt("FormCosts.SS5.Level2"), "" + ChatColor.RESET + ChatColor.GRAY + "SSJ 5 Form", "SSJ 5", 2);
            }

            if (e.getSlot() == 13) {
                CreateUpgradeLogic.CreateFirst(player, ".SS5Form.SS5Level", ".SS5Form.SS5TP", plugin.getConfig().getInt("FormCosts.SS5.Level3"), "" + ChatColor.RESET + ChatColor.GRAY + "SSJ 5 Form", "SSJ 5", 3);
            }

            if (e.getSlot() == 14) {
                CreateUpgradeLogic.CreateFirst(player, ".SS5Form.SS5Level", ".SS5Form.SS5TP", plugin.getConfig().getInt("FormCosts.SS5.Level4"), "" + ChatColor.RESET + ChatColor.GRAY + "SSJ 5 Form", "SSJ 5", 4);
            }

            if (e.getSlot() == 15) {
                CreateUpgradeLogic.CreateFirst(player, ".SS5Form.SS5Level", ".SS5Form.SS5TP", plugin.getConfig().getInt("FormCosts.SS5.Level5"), "" + ChatColor.RESET + ChatColor.GRAY + "SSJ 5 Form", "SSJ 5", 5);
            }

            if (e.getSlot() == 22) {
                openMenu(player);
            }
        }
    }
}