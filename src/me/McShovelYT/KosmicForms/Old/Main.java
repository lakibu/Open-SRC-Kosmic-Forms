package me.McShovelYT.KosmicForms.Old;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import me.McShovelYT.KosmicForms.Old.Methods.Methods;

public class Main extends JavaPlugin implements Listener {
    static HashMap<UUID, List<String>> inbox;
	
    public Main() {
    }

    public void onEnable() {
        this.configShit();
        this.getConfig().options().copyDefaults(true);
        this.getServer().getPluginManager().registerEvents(this, this);
        this.permissionsShit();
        new Listeners(this);
        new Menu(this);
        new Particles(this);
        Particles.particles();
        Particles.regen();
        this.getLogger().warning("This ain't it chief");
        inbox = new HashMap<UUID, List<String>>();
    }

    public void onDisable() {
    	this.getLogger().info("This still ain't it chief");
    	
        Iterator<UUID> var2 = inbox.keySet().iterator();

        while(var2.hasNext()) {
            UUID uuid = var2.next();
            Iterator<String> var4 = inbox.get(uuid).iterator();

            while(var4.hasNext()) {
                String s = (String)var4.next();
                this.getConfig().getStringList("PlayerData." + uuid.toString() + ".Inbox").add(s);
            }
        }

        this.saveConfig();
    }

    public void permissionsShit() {
        Permission SetFormPermission = new Permission("Kosmic.SetForm");
        Permission GiveFormTPPermission = new Permission("Kosmic.GiveFormTP");
        Permission MenuPermission = new Permission("Kosmic.Menu");
        this.getServer().getPluginManager().addPermission(SetFormPermission);
        this.getServer().getPluginManager().addPermission(GiveFormTPPermission);
        this.getServer().getPluginManager().addPermission(MenuPermission);
    }
    
	@SuppressWarnings("deprecation")
	@EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        OfflinePlayer player;
        int var3;
        int var4;
        OfflinePlayer[] var5;
        if (e.getMessage().toLowerCase().contains("@everyone")) {
        	if (e.getPlayer().isOp()) {
            e.setMessage(Methods.lowerCaseString(e.getMessage(), "@everyone").replaceAll("@everyone", "" + ChatColor.BLUE + ChatColor.UNDERLINE + "@everyone" + ChatColor.RESET));
            var4 = (var5 = this.getServer().getOfflinePlayers()).length;

            for(var3 = 0; var3 < var4; ++var3) {
                player = var5[var3];
                if (player.isOnline()) {
                	if (!player.getPlayer().getName().equals(e.getPlayer().getName())) {
                		((Player)player).sendMessage("" + ChatColor.BLUE + ChatColor.BOLD + "You are mentioned by " + ChatColor.YELLOW + ChatColor.BOLD + e.getPlayer().getName() + ChatColor.BLUE + ChatColor.BOLD + "!");
                    	((Player)player).playNote(e.getPlayer().getLocation(), (byte)4, (byte)7);
                    	((Player)player).playNote(e.getPlayer().getLocation(), (byte)4, (byte)10);
                	}
                }
            }
        	}
        }

        if (e.getMessage().toLowerCase().contains("@here")) {
        	if (e.getPlayer().isOp()) {
            e.setMessage(Methods.lowerCaseString(e.getMessage(), "@here").replaceAll("@here", "" + ChatColor.BLUE + ChatColor.UNDERLINE + "@here" + ChatColor.RESET));
            Player[] var7;
            var4 = (var7 = this.getServer().getOnlinePlayers()).length;

            for(var3 = 0; var3 < var4; ++var3) {
                Player player1 = var7[var3];
            	if (!player1.getPlayer().getName().equals(e.getPlayer().getName())) {
            		player1.sendMessage("" + ChatColor.BLUE + ChatColor.BOLD + "You are mentioned by " + ChatColor.YELLOW + ChatColor.BOLD + e.getPlayer().getName() + ChatColor.BLUE + ChatColor.BOLD + "!");
                	player1.playNote(e.getPlayer().getLocation(), (byte)4, (byte)7);
                	player1.playNote(e.getPlayer().getLocation(), (byte)4, (byte)10);
            	}
            }
        	}
        }

        var4 = (var5 = this.getServer().getOfflinePlayers()).length;

        for(var3 = 0; var3 < var4; ++var3) {
            player = var5[var3];
            if (e.getMessage().toLowerCase().contains('@' + player.getName().toLowerCase())) {
                e.setMessage(Methods.lowerCaseString(e.getMessage(), '@' + player.getName()).replaceAll('@' + player.getName(), "" + ChatColor.BLUE + ChatColor.UNDERLINE + '@' + player.getName() + ChatColor.RESET));
                if (player.isOnline()) {
                	if (!player.getPlayer().getName().equals(e.getPlayer().getName())) {
                		((Player)player).sendMessage("" + ChatColor.BLUE + ChatColor.BOLD + "You are mentioned by " + ChatColor.YELLOW + ChatColor.BOLD + e.getPlayer().getName() + ChatColor.BLUE + ChatColor.BOLD + "!");
                    	((Player)player).playNote(e.getPlayer().getLocation(), (byte)4, (byte)7);
                    	((Player)player).playNote(e.getPlayer().getLocation(), (byte)4, (byte)10);
                	}
                }
            }
        }

    }
	
	@EventHandler
    public void onTabComplete(PlayerChatTabCompleteEvent e) {
        for(int size = e.getChatMessage().length() - 1; size >= 0 && e.getChatMessage().getBytes()[size] != 32; --size) {
            if (e.getChatMessage().getBytes()[size] == 64) {
                OfflinePlayer[] var6;
                int var5 = (var6 = this.getServer().getOfflinePlayers()).length;

                for(int var4 = 0; var4 < var5; ++var4) {
                    OfflinePlayer player = var6[var4];
                    if (e.getPlayer().isOp()) {
                    	e.getTabCompletions().add("@here");
                    	e.getTabCompletions().add("@everyone");
                    }
                    e.getTabCompletions().add('@' + player.getName());
                }

                return;
            }
        }

    }
	
	@SuppressWarnings("deprecation")
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("kSetForm")) {
	        	List<String> list = new ArrayList<String>();
	            Player[] var9;
	            Player player;
				int var8;
	            var8 = (var9 = Bukkit.getServer().getOnlinePlayers()).length;
	            int var11 = 0;
	            player = var9[var11];
	        	if (args.length == 1) {
	        		for(var11 = 0; var11 < var8; ++var11) {
	                    	list.add(player.getName());
	        		}
	        	}
	        	
	        	if (args.length == 2) {
	        			list.add("god");
	        			list.add("ui");
	        			list.add("sk");
	        			list.add("evo");
	        			list.add("my");
	        	}
	        	
	        	return list;
	        } else if (cmd.getName().equalsIgnoreCase("kGiveFormTP")) {
	        	List<String> list = new ArrayList<String>();
	            Player[] var9;
	            Player player;
				int var8;
	            var8 = (var9 = Bukkit.getServer().getOnlinePlayers()).length;
	            int var11 = 0;
	            player = var9[var11];
	        	if (args.length == 1) {
	        		list.add("set");
	        		list.add("add");
	        	}
	        	
	        	if (args.length == 2) {
	        		for(var11 = 0; var11 < var8; ++var11) {
	        			list.add(player.getName());
	        		}
	        	}
	        	
	        	if (args.length == 3) {
        			list.add("god");
        			list.add("ui");
        			list.add("sk");
        			list.add("evo");
        			list.add("my");
	        	}
	        	
	        	return list;
	        } else {
	        	return null;
        }
	}
    

	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        if (cmd.getName().equalsIgnoreCase("kReload")) {
            if (sender.hasPermission("Kosmic.Reload")) {
                this.reloadConfig();
                this.configShit();
                sender.sendMessage(ChatColor.BLUE + "Config succufully reloaded");
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Insufficient permissions");
            }
        }

        int length = 0;
        OfflinePlayer playerToGiveFormTP;
        int var7;
        int var8;
        OfflinePlayer[] var9;
        if (cmd.getName().equalsIgnoreCase("kSetForm")) {
            if (sender.hasPermission("Kosmic.SetForm")) {
                length = args.length;
                if (length == 3) {
                    var8 = (var9 = Bukkit.getServer().getOfflinePlayers()).length;

                    for(var7 = 0; var7 < var8; ++var7) {
                        playerToGiveFormTP = var9[var7];
                        if (playerToGiveFormTP.getName().equalsIgnoreCase(args[0])) {
                            if (args[1].equalsIgnoreCase("god")) {
                                this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".GodForm.GodLevel", args[2]);
                                this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".GodForm.isGodEnabled", false);
                                this.saveConfig();
                                sender.sendMessage(ChatColor.BLUE + "You have successfully given " + ChatColor.YELLOW + playerToGiveFormTP.getName() + ChatColor.DARK_PURPLE + " G.O.D form " + ChatColor.BLUE + "Level " + ChatColor.YELLOW + args[2]);
                            } else if (args[1].equalsIgnoreCase("UI")) {
                                this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".UIForm.UILevel", args[2]);
                                this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".UIForm.isUIEnabled", false);
                                this.saveConfig();
                                sender.sendMessage(ChatColor.BLUE + "You have successfully given " + ChatColor.YELLOW + playerToGiveFormTP.getName() + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + " U.I form " + ChatColor.BLUE + "Level " + ChatColor.YELLOW + args[2]);
                            } else if (args[1].equalsIgnoreCase("SK")) {
                                this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".SKForm.SKLevel", args[2]);
                                this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".SKForm.isSKEnabled", false);
                                this.saveConfig();
                                sender.sendMessage(ChatColor.BLUE + "You have successfully given " + ChatColor.YELLOW + playerToGiveFormTP.getName() + ChatColor.RESET + ChatColor.RED + " S.K form " + ChatColor.BLUE + "Level " + ChatColor.YELLOW + args[2]);
                            } else if (args[1].equalsIgnoreCase("EVO")) {
                                this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".EVOForm.EVOLevel", args[2]);
                                this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".EVOForm.isEVOEnabled", false);
                                this.saveConfig();
                                sender.sendMessage(ChatColor.BLUE + "You have successfully given " + ChatColor.YELLOW + playerToGiveFormTP.getName() + ChatColor.RESET + ChatColor.BLUE + " Mastered SS Blue form " + ChatColor.BLUE + "Level " + ChatColor.YELLOW + args[2]);
                            } else if (args[1].equalsIgnoreCase("MY")) {
                                this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".MYForm.MYLevel", args[2]);
                                this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".MYForm.isMYEnabled", false);
                                this.saveConfig();
                                sender.sendMessage(ChatColor.BLUE + "You have successfully given " + ChatColor.YELLOW + playerToGiveFormTP.getName() + ChatColor.RESET + ChatColor.WHITE + " Mystic form " + ChatColor.BLUE + "Level " + ChatColor.YELLOW + args[2]);
                            } else {
                                sender.sendMessage(ChatColor.YELLOW + args[1] + ChatColor.RED + " Is not a valid form");
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "/kSetForm <USERNAME> <GOD/UI/SK/EVO/MY> <LEVEL>");
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Insufficient permissions");
            }
        }

        if (cmd.getName().equalsIgnoreCase("kGiveFormTP")) {
            if (sender.hasPermission("Kosmic.GiveFormTP")) {
                length = args.length;
                if (length == 4) {
                    var8 = (var9 = Bukkit.getServer().getOfflinePlayers()).length;

                    for(var7 = 0; var7 < var8; ++var7) {
                        playerToGiveFormTP = var9[var7];
                        if (playerToGiveFormTP.getName().equalsIgnoreCase(args[1])) {
                            int x;
                            int x2;
                            if (args[2].equalsIgnoreCase("god")) {
                                if (args[0].equalsIgnoreCase("set")) {
                                    this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".GodForm.GodTP", args[3]);
                                    this.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "You have successfully set " + ChatColor.YELLOW + playerToGiveFormTP.getName() + ChatColor.BLUE + "'s " + ChatColor.DARK_PURPLE + " G.O.D form TP " + ChatColor.BLUE + "amount to " + ChatColor.YELLOW + args[3]);
                                } else if (args[0].equalsIgnoreCase("add")) {
                                    if (this.getConfig().getString("PlayerData." + playerToGiveFormTP.getUniqueId() + ".GodForm.GodTP") == null) {
                                        this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".GodForm.GodTP", "0");
                                    }

                                    x = Integer.parseInt(this.getConfig().getString("PlayerData." + playerToGiveFormTP.getUniqueId() + ".GodForm.GodTP"));
                                    x2 = Integer.parseInt(args[3]);
                                    this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".GodForm.GodTP", x + x2);
                                    this.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "You have successfully given " + ChatColor.YELLOW + playerToGiveFormTP.getName() + " " + ChatColor.YELLOW + x2 + ChatColor.DARK_PURPLE + " G.O.D form TP ");
                                } else {
                                    sender.sendMessage(ChatColor.RED + "/kGiveFormTP <SET/ADD> <USERNAME> <FORM> <ANOUNT>");
                                }
                            } else if (args[2].equalsIgnoreCase("UI")) {
                                if (args[0].equalsIgnoreCase("set")) {
                                    this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".UIForm.UITP", args[3]);
                                    this.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "You have successfully set " + ChatColor.YELLOW + playerToGiveFormTP.getName() + ChatColor.BLUE + "'s " + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + " U.I form TP " + ChatColor.BLUE + "amount to " + ChatColor.YELLOW + args[3]);
                                } else if (args[0].equalsIgnoreCase("add")) {
                                    if (this.getConfig().getString("PlayerData." + playerToGiveFormTP.getUniqueId() + ".UIForm.UITP") == null) {
                                        this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".UIForm.UITP", "0");
                                    }

                                    x = Integer.parseInt(this.getConfig().getString("PlayerData." + playerToGiveFormTP.getUniqueId() + ".UIForm.UITP"));
                                    x2 = Integer.parseInt(args[3]);
                                    this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".UIForm.UITP", x + x2);
                                    this.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "You have successfully given " + ChatColor.YELLOW + playerToGiveFormTP.getName() + " " + ChatColor.YELLOW + x2 + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + " U.I form TP ");
                                } else {
                                    sender.sendMessage(ChatColor.RED + "/kGiveFormTP <SET/ADD> <USERNAME> <FORM> <ANOUNT>");
                                }
                            } else if (args[2].equalsIgnoreCase("SK")) {
                                if (args[0].equalsIgnoreCase("set")) {
                                    this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".SKForm.SKTP", args[3]);
                                    this.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "You have successfully set " + ChatColor.YELLOW + playerToGiveFormTP.getName() + ChatColor.BLUE + "'s " + ChatColor.RESET + ChatColor.RED + " S.K form TP " + ChatColor.BLUE + "amount to " + ChatColor.YELLOW + args[3]);
                                } else if (args[0].equalsIgnoreCase("add")) {
                                    if (this.getConfig().getString("PlayerData." + playerToGiveFormTP.getUniqueId() + ".SKForm.SKTP") == null) {
                                        this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".SKForm.SKTP", "0");
                                    }

                                    x = Integer.parseInt(this.getConfig().getString("PlayerData." + playerToGiveFormTP.getUniqueId() + ".SKForm.SKTP"));
                                    x2 = Integer.parseInt(args[3]);
                                    this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".SKForm.SKTP", x + x2);
                                    this.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "You have successfully given " + ChatColor.YELLOW + playerToGiveFormTP.getName() + " " + ChatColor.YELLOW + x2 + ChatColor.RESET + ChatColor.RED + " S.K form TP ");
                                } else {
                                    sender.sendMessage(ChatColor.RED + "/kGiveFormTP <SET/ADD> <USERNAME> <FORM> <ANOUNT>");
                                }
                            } else if (args[2].equalsIgnoreCase("EVO")) {
                                if (args[0].equalsIgnoreCase("set")) {
                                    this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".EVOForm.EVOTP", args[3]);
                                    this.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "You have successfully set " + ChatColor.YELLOW + playerToGiveFormTP.getName() + ChatColor.BLUE + "'s " + ChatColor.RESET + ChatColor.BLUE + " Mastered SS Blue form TP " + ChatColor.BLUE + "amount to " + ChatColor.YELLOW + args[3]);
                                } else if (args[0].equalsIgnoreCase("add")) {
                                    if (this.getConfig().getString("PlayerData." + playerToGiveFormTP.getUniqueId() + ".EVOForm.EVOTP") == null) {
                                        this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".EVOForm.EVOTP", "0");
                                    }

                                    x = Integer.parseInt(this.getConfig().getString("PlayerData." + playerToGiveFormTP.getUniqueId() + ".EVOForm.EVOTP"));
                                    x2 = Integer.parseInt(args[3]);
                                    this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".EVOForm.EVOTP", x + x2);
                                    this.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "You have successfully given " + ChatColor.YELLOW + playerToGiveFormTP.getName() + " " + ChatColor.YELLOW + x2 + ChatColor.RESET + ChatColor.BLUE + " Mastered SS Blue form TP ");
                                } else {
                                    sender.sendMessage(ChatColor.RED + "/kGiveFormTP <SET/ADD> <USERNAME> <FORM> <ANOUNT>");
                                }
                            } else if (args[2].equalsIgnoreCase("MY")) {
                                if (args[0].equalsIgnoreCase("set")) {
                                    this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".MYForm.MYTP", args[3]);
                                    this.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "You have successfully set " + ChatColor.YELLOW + playerToGiveFormTP.getName() + ChatColor.BLUE + "'s " + ChatColor.RESET + ChatColor.WHITE + " Mystic form TP " + ChatColor.BLUE + "amount to " + ChatColor.YELLOW + args[3]);
                                } else if (args[0].equalsIgnoreCase("add")) {
                                    if (this.getConfig().getString("PlayerData." + playerToGiveFormTP.getUniqueId() + ".MYForm.MYTP") == null) {
                                        this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".MYForm.MYTP", "0");
                                    }

                                    x = Integer.parseInt(this.getConfig().getString("PlayerData." + playerToGiveFormTP.getUniqueId() + ".MYForm.MYTP"));
                                    x2 = Integer.parseInt(args[3]);
                                    this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".MYForm.MYTP", x + x2);
                                    this.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "You have successfully given " + ChatColor.YELLOW + playerToGiveFormTP.getName() + " " + ChatColor.YELLOW + x2 + ChatColor.RESET + ChatColor.WHITE + " Mystic form TP ");
                                } else {
                                    sender.sendMessage(ChatColor.RED + "/kGiveFormTP <SET/ADD> <USERNAME> <FORM> <ANOUNT>");
                                }
                            } else {
                                sender.sendMessage(ChatColor.YELLOW + args[2] + ChatColor.RED + " Is not a valid form");
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "/kGiveFormTP <SET/ADD> <USERNAME> <GOD/UI/SK/EVO/MY> <ANOUNT>");
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Insufficient permissions");
            }
        }

        if (cmd.getName().equalsIgnoreCase("kMenu")) {
            if (sender.hasPermission("Kosmic.Menu")) {
                Menu.openMenu((Player) sender);
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Insufficient permissions");
            }
        }
	return false;
    }

    void configShit() {
        this.reloadConfig();
        if (this.getConfig().getString("Rank1") != null) {
            this.getConfig().set("Rank1", (Object)null);
            this.saveConfig();
        }

        if (this.getConfig().getString("Rank2") != null) {
            this.getConfig().set("Rank2", (Object)null);
            this.saveConfig();
        }

        if (this.getConfig().getString("Rank3") != null) {
            this.getConfig().set("Rank3", (Object)null);
            this.saveConfig();
        }

        if (this.getConfig().getString("Rank4") != null) {
            this.getConfig().set("Rank4", (Object)null);
            this.saveConfig();
        }

        if (this.getConfig().getString("Rank5") != null) {
            this.getConfig().set("Rank5", (Object)null);
            this.saveConfig();
        }

        if (this.getConfig().getString("GlobalMultiplier") != null) {
            this.getConfig().set("GlobalMultiplier", (Object)null);
            this.saveConfig();
        }

        if (this.getConfig().getString("EventName") != null) {
            this.getConfig().set("EventName", (Object)null);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.GOD.Level1") == 0) {
            this.getConfig().set("FormCosts.GOD.Level1", 1000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.GOD.Level1") == 0) {
            this.getConfig().set("FormDamages.GOD.Level1", 1000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.GOD.Level1") == 0.0D) {
            this.getConfig().set("FormDefences.GOD.Level1", 0.1D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.GOD.Level2") == 0) {
            this.getConfig().set("FormCosts.GOD.Level2", 1500000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.GOD.Level2") == 0) {
            this.getConfig().set("FormDamages.GOD.Level2", 1500000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.GOD.Level2") == 0.0D) {
            this.getConfig().set("FormDefences.GOD.Level2", 0.15D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.GOD.Level3") == 0) {
            this.getConfig().set("FormCosts.GOD.Level3", 2000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.GOD.Level3") == 0) {
            this.getConfig().set("FormDamages.GOD.Level3", 2000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.GOD.Level3") == 0.0D) {
            this.getConfig().set("FormDefences.GOD.Level3", 0.2D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.GOD.Level4") == 0) {
            this.getConfig().set("FormCosts.GOD.Level4", 2500000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.GOD.Level4") == 0) {
            this.getConfig().set("FormDamages.GOD.Level4", 2500000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.GOD.Level4") == 0.0D) {
            this.getConfig().set("FormDefences.GOD.Level4", 0.25D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.GOD.Level5") == 0) {
            this.getConfig().set("FormCosts.GOD.Level5", 3000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.GOD.Level5") == 0) {
            this.getConfig().set("FormDamages.GOD.Level5", 3000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.GOD.Level5") == 0.0D) {
            this.getConfig().set("FormDefences.GOD.Level5", 0.3D);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.GOD.Level1") == 0.0D) {
            this.getConfig().set("FormRegens.GOD.Level1", 100000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.GOD.Level2") == 0.0D) {
            this.getConfig().set("FormRegens.GOD.Level2", 150000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.GOD.Level3") == 0.0D) {
            this.getConfig().set("FormRegens.GOD.Level3", 200000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.GOD.Level4") == 0.0D) {
            this.getConfig().set("FormRegens.GOD.Level4", 250000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.GOD.Level5") == 0.0D) {
            this.getConfig().set("FormRegens.GOD.Level5", 300000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.UI.Level1") == 0) {
            this.getConfig().set("FormCosts.UI.Level1", 2000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.UI.Level1") == 0) {
            this.getConfig().set("FormDamages.UI.Level1", 2000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.UI.Level1") == 0.0D) {
            this.getConfig().set("FormDefences.UI.Level1", 0.1D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.UI.Level2") == 0) {
            this.getConfig().set("FormCosts.UI.Level2", 3000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.UI.Level2") == 0) {
            this.getConfig().set("FormDamages.UI.Level2", 3000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.UI.Level2") == 0.0D) {
            this.getConfig().set("FormDefences.UI.Level2", 0.3D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.UI.Level3") == 0) {
            this.getConfig().set("FormCosts.UI.Level3", 4000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.UI.Level3") == 0) {
            this.getConfig().set("FormDamages.UI.Level3", 4000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.UI.Level3") == 0.0D) {
            this.getConfig().set("FormDefences.UI.Level3", 0.4D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.UI.Level4") == 0) {
            this.getConfig().set("FormCosts.UI.Level4", 5000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.UI.Level4") == 0) {
            this.getConfig().set("FormDamages.UI.Level4", 5000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.UI.Level4") == 0.0D) {
            this.getConfig().set("FormDefences.UI.Level4", 0.5D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.UI.Level5") == 0) {
            this.getConfig().set("FormCosts.UI.Level5", 6000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.UI.Level5") == 0) {
            this.getConfig().set("FormDamages.UI.Level5", 6000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.UI.Level5") == 0.0D) {
            this.getConfig().set("FormDefences.UI.Level5", 0.6D);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.UI.Level1") == 0.0D) {
            this.getConfig().set("FormRegens.UI.Level1", 200000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.UI.Level2") == 0.0D) {
            this.getConfig().set("FormRegens.UI.Level2", 300000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.UI.Level3") == 0.0D) {
            this.getConfig().set("FormRegens.UI.Level3", 400000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.UI.Level4") == 0.0D) {
            this.getConfig().set("FormRegens.UI.Level4", 500000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.UI.Level5") == 0.0D) {
            this.getConfig().set("FormRegens.UI.Level5", 600000);
            this.saveConfig();
        }
        
        if (this.getConfig().getInt("FormCosts.SK.Level1") == 0) {
            this.getConfig().set("FormCosts.SK.Level1", 100000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.SK.Level1") == 0) {
            this.getConfig().set("FormDamages.SK.Level1", 1000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.SK.Level1") == 0.0D) {
            this.getConfig().set("FormDefences.SK.Level1", 0.1D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.SK.Level2") == 0) {
            this.getConfig().set("FormCosts.SK.Level2", 2000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.SK.Level2") == 0) {
            this.getConfig().set("FormDamages.SK.Level2", 2000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.SK.Level2") == 0.0D) {
            this.getConfig().set("FormDefences.SK.Level2", 0.1D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.SK.Level3") == 0) {
            this.getConfig().set("FormCosts.SK.Level3", 3000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.SK.Level3") == 0) {
            this.getConfig().set("FormDamages.SK.Level3", 3000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.SK.Level3") == 0.0D) {
            this.getConfig().set("FormDefences.SK.Level3", 0.1D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.SK.Level4") == 0) {
            this.getConfig().set("FormCosts.SK.Level4", 5000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.SK.Level4") == 0) {
            this.getConfig().set("FormDamages.SK.Level4", 5000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.SK.Level4") == 0.0D) {
            this.getConfig().set("FormDefences.SK.Level4", 0.1D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.SK.Level5") == 0) {
            this.getConfig().set("FormCosts.SK.Level5", 6000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.SK.Level5") == 0) {
            this.getConfig().set("FormDamages.SK.Level5", 6000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.SK.Level5") == 0.0D) {
            this.getConfig().set("FormDefences.SK.Level5", 0.6D);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.SK.Level1") == 0.0D) {
            this.getConfig().set("FormRegens.SK.Level1", 200000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.SK.Level2") == 0.0D) {
            this.getConfig().set("FormRegens.SK.Level2", 300000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.SK.Level3") == 0.0D) {
            this.getConfig().set("FormRegens.SK.Level3", 400000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.SK.Level4") == 0.0D) {
            this.getConfig().set("FormRegens.SK.Level4", 500000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.SK.Level5") == 0.0D) {
            this.getConfig().set("FormRegens.SK.Level5", 600000);
            this.saveConfig();
        }
        
        if (this.getConfig().getInt("FormCosts.EVO.Level1") == 0) {
            this.getConfig().set("FormCosts.EVO.Level1", 1000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.EVO.Level1") == 0) {
            this.getConfig().set("FormDamages.EVO.Level1", 1000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.EVO.Level1") == 0.0D) {
            this.getConfig().set("FormDefences.EVO.Level1", 0.1D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.EVO.Level2") == 0) {
            this.getConfig().set("FormCosts.EVO.Level2", 1500000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.EVO.Level2") == 0) {
            this.getConfig().set("FormDamages.EVO.Level2", 1500000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.EVO.Level2") == 0.0D) {
            this.getConfig().set("FormDefences.EVO.Level2", 0.15D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.EVO.Level3") == 0) {
            this.getConfig().set("FormCosts.EVO.Level3", 2000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.EVO.Level3") == 0) {
            this.getConfig().set("FormDamages.EVO.Level3", 2000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.EVO.Level3") == 0.0D) {
            this.getConfig().set("FormDefences.EVO.Level3", 0.2D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.EVO.Level4") == 0) {
            this.getConfig().set("FormCosts.EVO.Level4", 2500000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.EVO.Level4") == 0) {
            this.getConfig().set("FormDamages.EVO.Level4", 2500000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.EVO.Level4") == 0.0D) {
            this.getConfig().set("FormDefences.EVO.Level4", 0.25D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.EVO.Level5") == 0) {
            this.getConfig().set("FormCosts.EVO.Level5", 3000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.EVO.Level5") == 0) {
            this.getConfig().set("FormDamages.EVO.Level5", 3000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.EVO.Level5") == 0.0D) {
            this.getConfig().set("FormDefences.EVO.Level5", 0.3D);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.EVO.Level1") == 0.0D) {
            this.getConfig().set("FormRegens.EVO.Level1", 100000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.EVO.Level2") == 0.0D) {
            this.getConfig().set("FormRegens.EVO.Level2", 150000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.EVO.Level3") == 0.0D) {
            this.getConfig().set("FormRegens.EVO.Level3", 200000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.EVO.Level4") == 0.0D) {
            this.getConfig().set("FormRegens.EVO.Level4", 250000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.EVO.Level5") == 0.0D) {
            this.getConfig().set("FormRegens.EVO.Level5", 300000);
            this.saveConfig();
        }
        
        if (this.getConfig().getInt("FormCosts.MY.Level1") == 0) {
            this.getConfig().set("FormCosts.MY.Level1", 1000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.MY.Level1") == 0) {
            this.getConfig().set("FormDamages.MY.Level1", 1000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.MY.Level1") == 0.0D) {
            this.getConfig().set("FormDefences.MY.Level1", 0.1D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.MY.Level2") == 0) {
            this.getConfig().set("FormCosts.MY.Level2", 1500000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.MY.Level2") == 0) {
            this.getConfig().set("FormDamages.MY.Level2", 1500000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.MY.Level2") == 0.0D) {
            this.getConfig().set("FormDefences.MY.Level2", 0.15D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.MY.Level3") == 0) {
            this.getConfig().set("FormCosts.MY.Level3", 2000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.MY.Level3") == 0) {
            this.getConfig().set("FormDamages.MY.Level3", 2000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.MY.Level3") == 0.0D) {
            this.getConfig().set("FormDefences.MY.Level3", 0.2D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.MY.Level4") == 0) {
            this.getConfig().set("FormCosts.MY.Level4", 2500000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.MY.Level4") == 0) {
            this.getConfig().set("FormDamages.MY.Level4", 2500000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.MY.Level4") == 0.0D) {
            this.getConfig().set("FormDefences.MY.Level4", 0.25D);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormCosts.MY.Level5") == 0) {
            this.getConfig().set("FormCosts.MY.Level5", 3000000);
            this.saveConfig();
        }

        if (this.getConfig().getInt("FormDamages.MY.Level5") == 0) {
            this.getConfig().set("FormDamages.MY.Level5", 3000000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormDefences.MY.Level5") == 0.0D) {
            this.getConfig().set("FormDefences.MY.Level5", 0.3D);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.MY.Level1") == 0.0D) {
            this.getConfig().set("FormRegens.MY.Level1", 100000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.MY.Level2") == 0.0D) {
            this.getConfig().set("FormRegens.MY.Level2", 150000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.MY.Level3") == 0.0D) {
            this.getConfig().set("FormRegens.MY.Level3", 200000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.MY.Level4") == 0.0D) {
            this.getConfig().set("FormRegens.MY.Level4", 250000);
            this.saveConfig();
        }

        if (this.getConfig().getDouble("FormRegens.MY.Level5") == 0.0D) {
            this.getConfig().set("FormRegens.MY.Level5", 300000);
            this.saveConfig();
        }

        this.getConfig().set("TimesLoaded", this.getConfig().getDouble("TimesLoaded") + 1.0D);
        this.saveConfig();
    }
}
