package me.McShovelYT.KosmicForms.Old;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
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
    }

    public void onDisable() {
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

        int length;
        Player playerToGiveFormTP;
        int var7;
        int var8;
        Player[] var9;
        if (cmd.getName().equalsIgnoreCase("kSetForm")) {
            if (sender.hasPermission("Kosmic.SetForm")) {
                length = args.length;
                if (length == 3) {
                    var8 = (var9 = Bukkit.getServer().getOnlinePlayers()).length;

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
                            } else {
                                sender.sendMessage(ChatColor.YELLOW + args[1] + ChatColor.RED + " Is not a valid form");
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "/kSetForm <USERNAME> <GOD/UI> <LEVEL>");
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Insufficient permissions");
            }
        }

        if (cmd.getName().equalsIgnoreCase("kGiveFormTP")) {
            if (sender.hasPermission("Kosmic.GiveFormTP")) {
                length = args.length;
                if (length == 4) {
                    var8 = (var9 = Bukkit.getServer().getOnlinePlayers()).length;

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
                                    this.getConfig().set("PlayerData." + playerToGiveFormTP.getUniqueId() + ".SKForm.UITP", x + x2);
                                    this.saveConfig();
                                    sender.sendMessage(ChatColor.BLUE + "You have successfully given " + ChatColor.YELLOW + playerToGiveFormTP.getName() + " " + ChatColor.YELLOW + x2 + ChatColor.RESET + ChatColor.RED + " S.K form TP ");
                                } else {
                                    sender.sendMessage(ChatColor.RED + "/kGiveFormTP <SET/ADD> <USERNAME> <FORM> <ANOUNT>");
                                }
                            } else {
                                sender.sendMessage(ChatColor.YELLOW + args[2] + ChatColor.RED + " Is not a valid form");
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "/kGiveFormTP <SET/ADD> <USERNAME> <GOD/UI> <ANOUNT>");
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "Insufficient permissions");
            }
        }

        if (cmd.getName().equalsIgnoreCase("kMenu")) {
            if (sender.hasPermission("Kosmic.Menu")) {
                Menu.openMenu((Player)sender);
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

        this.getConfig().set("TimesLoaded", this.getConfig().getDouble("TimesLoaded") + 1.0D);
        this.saveConfig();
    }
}
