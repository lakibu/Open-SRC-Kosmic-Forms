package me.McShovelYT.KosmicForms.Old;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;

import me.McShovelYT.KosmicForms.Old.Methods.GetStats;
import me.McShovelYT.KosmicForms.Old.Utils.Fireworks;
import me.McShovelYT.KosmicForms.Old.Utils.NBTEditor;
import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;
import net.minecraft.server.v1_7_R4.EnumClientCommand;
import net.minecraft.server.v1_7_R4.PacketPlayInClientCommand;

@SuppressWarnings("deprecation")
public class Listeners implements Listener {
    Plugin plugin = Main.getPlugin(Main.class);
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap<String, Long> clickPlayers = new HashMap();

    public Listeners(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
    	Player player = e.getPlayer();
        if (e.isSneaking()) {
            long currTime = System.currentTimeMillis();
            if (!this.clickPlayers.containsKey(e.getPlayer().getName())) {
                this.clickPlayers.put(e.getPlayer().getName(), currTime);
            }
            
            long lastClick = (Long)this.clickPlayers.get(e.getPlayer().getName());
            if (currTime - lastClick >= 1L) {
                if (currTime - lastClick > 300L) {
                    this.clickPlayers.remove(e.getPlayer().getName());
                    this.clickPlayers.put(e.getPlayer().getName(), currTime);
                } else {
                    this.clickPlayers.remove(e.getPlayer().getName());
                    this.clickPlayers.put(e.getPlayer().getName(), currTime);
                    NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                    NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                    int str = PlayerPersisted.getInt("jrmcStrI");
                    int dex = PlayerPersisted.getInt("jrmcDexI");
                    int con = PlayerPersisted.getInt("jrmcCnsI");
                    int wil = PlayerPersisted.getInt("jrmcWilI");
                    int mnd = PlayerPersisted.getInt("jrmcIntI");
                    int spi = PlayerPersisted.getInt("jrmcCncI");
                    int level = (str + dex + con + wil + mnd + spi) / 5;
                    
                    Location loc = player.getLocation();
                    
                    if (this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SelectedForm") != null) {
                        if (this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("god")) {
                            if (this.plugin.getConfig().getBoolean("PlayerData." + e.getPlayer().getUniqueId() + ".GodForm.isGodEnabled")) {
                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", false);
                                e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.DARK_PURPLE + "G.O.D Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".GodForm.GodLevel") + ChatColor.BLUE + ".");
                                PlayerPersisted.put("jrmcState", 0);
                                PlayerPersisted.remove("jrmcTech1");
                                PlayerPersisted.remove("jrmcTech2");
                                PlayerPersisted.remove("jrmcTech3");
                                PlayerPersisted.remove("jrmcTech4");
                                int Race = PlayerPersisted.getInt("jrmcRace");
                                if (Race == 3) {
                                	PlayerPersisted.put("jrmcDNS", this.plugin.getConfig().get("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor"));
                                }
                                Forgadata.put("PlayerPersisted", PlayerPersisted);
                                NBTManager.getInstance().writeForgeData(player, Forgadata);
                            } else {
                            	
                            	if (level >= 20000) {
                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", true);
                                e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.DARK_PURPLE + "G.O.D Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".GodForm.GodLevel") + ChatColor.BLUE + "!");
                            	
                                String var1 = PlayerPersisted.getString("jrmcDNS");
                                int Race = PlayerPersisted.getInt("jrmcRace");
                                if (Race == 3) {
                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor", var1);
                                NBTEditor.Edit(player, "jrmcDNS", "0300a0000000010006kry0UdeL0U9DP000000000000PEEB0PEEB");
                                }
                                
                                Fireworks.explosion(player.getLocation(), Color.PURPLE, Color.FUCHSIA);
                                
                            	} else {
                                    this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", false);
                                    player.sendMessage(ChatColor.RED + "Your level must be 20000 or higher to use this form");
                            	}
                            	
                                this.clickPlayers.put(e.getPlayer().getName(), currTime);
                            }

                            this.plugin.saveConfig();
                        }

                        if (this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("ui")) {
                            if (this.plugin.getConfig().getBoolean("PlayerData." + e.getPlayer().getUniqueId() + ".UIForm.isUIEnabled")) {
                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", false);
                                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".UIForm.UILevel")) < 5) {
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".UIForm.UILevel") + ChatColor.BLUE + ".");
                                    PlayerPersisted.put("jrmcStatusEff", "");
                                    Forgadata.put("PlayerPersisted", PlayerPersisted);
                                    NBTManager.getInstance().writeForgeData(player, Forgadata);
                                } else {
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "M.U.I Form" + ChatColor.BLUE + ".");
                                    NBTEditor.Edit(player, "jrmcStatusEff", "");
                                    NBTEditor.setHairColor(player, (String) this.plugin.getConfig().get("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor"));
                                }
                            } else {
                            	
                            	if (level >= 40000) {

                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", true);
                                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".UIForm.UILevel")) < 5) {
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".UIForm.UILevel") + ChatColor.BLUE + "!");
                                    
                                    Fireworks.explosion(player.getLocation(), Color.AQUA, Color.BLUE);
                                    
                                } else {
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "M.U.I Form" + ChatColor.BLUE + "!");
                                    String var1 = NBTEditor.getHairColor(player);
                                    this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor", var1);
                                    NBTEditor.setHairColor(player, "17ik5");
                                    
                                    Fireworks.explosion(player.getLocation(), Color.WHITE, Color.SILVER);
                                    
                                }
                            	} else {
                                    player.sendMessage(ChatColor.RED + "Your level must be 40000 or higher to use this form");
                                    this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", false);
                            	}
                                this.clickPlayers.put(e.getPlayer().getName(), currTime);
                            }

                            this.plugin.saveConfig();
                        }
                        
                        if (this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("SK")) {
                            if (this.plugin.getConfig().getBoolean("PlayerData." + e.getPlayer().getUniqueId() + ".SKForm.isSKEnabled")) {
                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".SKForm.isSKEnabled", false);
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.RESET + ChatColor.RED + "S.K Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SKForm.SKLevel") + ChatColor.BLUE + ".");
                                    NBTEditor.Edit(player, "jrmcState2", "");
                            } else {
                            	
                            	if (level >= 15000) {
                            	
                            		this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".SKForm.isSKEnabled", true);
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.RED + "S.K Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SKForm.SKLevel") + ChatColor.BLUE + "!");
                                    
                                    Fireworks.explosion(loc, Color.RED, Color.fromRGB(80, 0, 0));
                                    
                            	} else {
                                    this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".SKForm.isSKEnabled", false);
                                    player.sendMessage(ChatColor.RED + "Your level must be 15000 or higher to use this form");
                            	}

                                this.clickPlayers.put(e.getPlayer().getName(), currTime);
                            }

                            this.plugin.saveConfig();
                        }
                        
                        if (this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("EVO")) {
                            if (this.plugin.getConfig().getBoolean("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled")) {
                                	this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", false);
                                	int Purity = PlayerPersisted.getInt("jrmcAlign");
                                
                                	if (Purity >= 51) {
                                    	e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.RESET + ChatColor.BLUE + "Mastered SS Blue level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.EVOLevel") + ChatColor.BLUE + "!");
                                	} else {
                                    	e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.RESET + ChatColor.DARK_PURPLE + "Mastered SS Rose level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.EVOLevel") + ChatColor.BLUE + "!");
                                	}
                                    NBTEditor.Edit(player, "jrmcState", 0);
                                    NBTEditor.Edit(player, "jrmcStatusEff", "");
                            } else {
                            	
                            	if (level >= 30000) {
                            		String s = NBTEditor.GetString(player, "jrmcSSlts");
                            		if (s.contains("GF1") || s.contains("GF2") || s.contains("GF9")) {
	                                	this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", true);
	                                	int Purity = PlayerPersisted.getInt("jrmcAlign");
	                                	Color color = Color.WHITE;
	                                	Color color2 = Color.WHITE;
	                                	
	                                	if (Purity >= 51) {
	                                    	e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.BLUE + "Mastered SS Blue level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.EVOLevel") + ChatColor.BLUE + "!");
	                                    	color = Color.BLUE;
	                                    	color2 = Color.AQUA;
	                                	} else {
	                                    	e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.DARK_PURPLE + "Mastered SS Rose level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.EVOLevel") + ChatColor.BLUE + "!");
	                                    	color = Color.PURPLE;
	                                    	color2 = Color.FUCHSIA;
	                                	}
	                                    
	                                	Fireworks.explosion(loc, color, color2);
                            		} else {
                                    	this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", false);
                                    	player.sendMessage(ChatColor.RED + "You must have GodForm lvl 2 or higher to use this form");
                            		}
                            	} else {
                                	this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", false);
                                    player.sendMessage(ChatColor.RED + "Your level must be 30000 or higher to use this form");
                            	}

                                this.clickPlayers.put(e.getPlayer().getName(), currTime);
                            }

                            this.plugin.saveConfig();
                        }
                        
                        if (this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("MY")) {
                            if (this.plugin.getConfig().getBoolean("PlayerData." + e.getPlayer().getUniqueId() + ".MYForm.isMYEnabled")) {
                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".MYForm.isMYEnabled", false);
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.RESET + ChatColor.BOLD + "Mystic Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".MYForm.MYLevel") + ChatColor.BLUE + ".");
                                    NBTEditor.Edit(player, "jrmcStatusEff", "");
                                    NBTEditor.Edit(player, "jrmcRelease", 100);
                            } else {
                                if (level >= 25000) {
                                if (mnd >= 4000) {
                                	
                                	this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".MYForm.isMYEnabled", true);
                                	
                                    Forgadata.put("PlayerPersisted", PlayerPersisted);
                                    NBTManager.getInstance().writeForgeData(player, Forgadata);
                                    
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.BOLD + "Mystic Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".MYForm.MYLevel") + ChatColor.BLUE + "!");
                                    
                                    Fireworks.explosion(loc, Color.AQUA, Color.BLUE);
                                
                                this.clickPlayers.put(e.getPlayer().getName(), currTime);
                            } else {
                            	player.sendMessage(ChatColor.RED + "You need 4000 or more mind to transform into this form!");
                            	this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".MYForm.isMYEnabled", false);
                            }
                            } else {
                            	this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".MYForm.isMYEnabled", false);
                                player.sendMessage(ChatColor.RED + "Your level must be 25000 or higher to use this form");
                            }
                            }

                            this.plugin.saveConfig();
                        }
                        
                        if (this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("RAGE")) {
                            if (this.plugin.getConfig().getBoolean("PlayerData." + e.getPlayer().getUniqueId() + ".RAGEForm.isRAGEEnabled")) {
                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".RAGEForm.isRAGEEnabled", false);
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.RESET + ChatColor.GOLD + "Rage Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".RAGEForm.RAGELevel") + ChatColor.BLUE + ".");
                                    NBTEditor.Edit(player, "jrmcState", "");
                                    NBTEditor.Edit(player, "jrmcStatusEff", "");
                                    NBTEditor.Edit(player, "jrmcRelease", 100);
                            } else {
                            	
                            	if (level >= 27500) {
                            	
                            		this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".RAGEForm.isRAGEEnabled", true);
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.GOLD + "Rage Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".RAGEForm.RAGELevel") + ChatColor.BLUE + "!");
                                    
                                    Fireworks.explosion(loc, Color.YELLOW, Color.AQUA);
                                    
                            	} else {
                                    this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".RAGEForm.isRAGEEnabled", false);
                                    player.sendMessage(ChatColor.RED + "Your level must be 27500 or higher to use this form");
                            	}

                                this.clickPlayers.put(e.getPlayer().getName(), currTime);
                            }

                            this.plugin.saveConfig();
                        }
                        
                        if (this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("SS5")) {
                            if (this.plugin.getConfig().getBoolean("PlayerData." + e.getPlayer().getUniqueId() + ".SS5Form.isSS5Enabled")) {
                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".SS5Form.isSS5Enabled", false);
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.RESET + ChatColor.GRAY + "SSJ 5 Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SS5Form.SS5Level") + ChatColor.BLUE + ".");
                                    NBTEditor.Edit(player, "jrmcState", 14);
                                    NBTEditor.Edit(player, "jrmcStatusEff", "");
                                    NBTEditor.setHairColor(player, (String) this.plugin.getConfig().get("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor"));
                                    NBTEditor.setTailColor(player, (String) this.plugin.getConfig().get("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultTailColor"));
                            } else {
                            	if (level >= 32500) {
                            		int State = PlayerPersisted.getInt("jrmcState");
                            			if (State == 14) {
		                            		this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".SS5Form.isSS5Enabled", true);
		                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.GRAY + "SSJ 5 Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SS5Form.SS5Level") + ChatColor.BLUE + "!");
		                                    
		                                    String var1 = NBTEditor.getHairColor(player);
		                                    this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor", var1);
		                                    String var2 = NBTEditor.getTailColor(player);
		                                    this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultTailColor", var2);
		                                    NBTEditor.setHairColor(player, "17ik5");
		                                    NBTEditor.setTailColor(player, "17ik5");
		                                    
		                                    Fireworks.explosion(loc, Color.YELLOW, Color.WHITE);
                                    
                            			} else {
                                            this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".SS5Form.isSS5Enabled", false);
                                            player.sendMessage(ChatColor.RED + "You must be in SSJ4 to use this form");
                            			}
                            	} else {
                                    this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".SS5Form.isSS5Enabled", false);
                                    player.sendMessage(ChatColor.RED + "Your level must be 32500 or higher to use this form");
                            	}
                                this.clickPlayers.put(e.getPlayer().getName(), currTime);
                            }
                            this.plugin.saveConfig();
                        }
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
    	final Player p = e.getEntity();
        p.setCanPickupItems(false);
        PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
        ((CraftPlayer) p).getHandle().playerConnection.a(packet);
    }
    
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        e.getPlayer().setCanPickupItems(true);
    }
    
    
	@EventHandler
    public void onCoolFormat(PlayerChatEvent e) {
    	Player p = e.getPlayer();
    	if (p.isOp()) {
        	String l = e.getMessage();
        	e.setMessage("" + ChatColor.WHITE + ChatColor.BOLD + l);
    	}
    }

    @EventHandler
    public void onPlayerDealDamageEvent(EntityDamageByEntityEvent e) {
        Player damaged;
        if (e.getEntity() instanceof Player) {
            damaged = (Player)e.getEntity();
            double x2;
            int x;
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".BB")) {
            	double dmg1 = e.getDamage();
            	double dmg = dmg1 * 100.0D;
            	e.setCancelled(true);
            	NBTEditor.Edit(damaged, "jrmcStamina", GetStats.getCurStam(damaged) - dmg);
            	NBTEditor.Edit(damaged, "jrmcBdy", GetStats.getCurHP(damaged) + dmg1);
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".GodForm.isGodEnabled")) {
                if (!damaged.isOp()) {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.GOD.Level1");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.GOD.Level2");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.GOD.Level3");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.GOD.Level4");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.GOD.Level5");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }
                } else {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.GOD.Level1");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.GOD.Level2");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.GOD.Level3");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.GOD.Level4");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.GOD.Level5");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }
                }
            }

            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".UIForm.isUIEnabled")) {
                if (!damaged.isOp()) {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.UI.Level1");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.UI.Level2");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.UI.Level3");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.UI.Level4");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.UI.Level5");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }
                } else {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.UI.Level1");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.UI.Level2");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.UI.Level3");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.UI.Level4");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.UI.Level5");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".SKForm.isSKEnabled")) {
                if (!damaged.isOp()) {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SK.Level1");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SK.Level2");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SK.Level3");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SK.Level4");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SK.Level5");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }
                } else {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SK.Level1");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SK.Level2");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SK.Level3");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SK.Level4");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SK.Level5");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }
                }
            }
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".EVOForm.isEVOEnabled")) {
                if (!damaged.isOp()) {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.EVO.Level1");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.EVO.Level2");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.EVO.Level3");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.EVO.Level4");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.EVO.Level5");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }
                } else {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.EVO.Level1");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.EVO.Level2");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.EVO.Level3");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.EVO.Level4");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.EVO.Level5");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".MYForm.isMYEnabled")) {
                if (!damaged.isOp()) {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.MY.Level1");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.MY.Level2");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.MY.Level3");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.MY.Level4");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.MY.Level5");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }
                } else {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.MY.Level1");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.MY.Level2");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.MY.Level3");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.MY.Level4");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.MY.Level5");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".RAGEForm.isRAGEEnabled")) {
                if (!damaged.isOp()) {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.RAGE.Level1");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.RAGE.Level2");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.RAGE.Level3");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.RAGE.Level4");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.RAGE.Level5");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }
                } else {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.RAGE.Level1");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.RAGE.Level2");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.RAGE.Level3");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.RAGE.Level4");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.RAGE.Level5");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".SS5Form.isSS5Enabled")) {
                if (!damaged.isOp()) {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SS5.Level1");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SS5.Level2");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SS5.Level3");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SS5.Level4");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SS5.Level5");
                        x = (int)Math.round(x2);
                        damaged.setOp(true);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                        damaged.setOp(false);
                    }
                } else {
                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 1) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SS5.Level1");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 2) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SS5.Level2");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 3) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SS5.Level3");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 4) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SS5.Level4");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }

                    if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) >= 5) {
                        x2 = e.getDamage() * this.plugin.getConfig().getDouble("FormDefences.SS5.Level5");
                        x = (int)Math.round(x2);
                        damaged.chat("/jrmcheal body " + x + 100 + " " + damaged.getName());
                    }
                }
            }
        }
        
        if (e.getDamager() instanceof Player) {
            damaged = (Player)e.getDamager();
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".GodForm.isGodEnabled")) {
                int str = NBTEditor.GetInt(damaged, "jrmcStrI");
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 1) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.GOD.Level1") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 2) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.GOD.Level2") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 3) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.GOD.Level3") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 4) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.GOD.Level4") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) >= 5) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.GOD.Level5") - 1.0D));
                }
            }

            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".UIForm.isUIEnabled")) {
                int str = NBTEditor.GetInt(damaged, "jrmcStrI");
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 1) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.UI.Level1") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 2) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.UI.Level2") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 3) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.UI.Level3") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 4) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.UI.Level4") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) >= 5) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.UI.Level5") - 1.0D));
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".SKForm.isSKEnabled")) {
                int str = NBTEditor.GetInt(damaged, "jrmcStrI");
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 1) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.SK.Level1") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 2) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.SK.Level2") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 3) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.SK.Level3") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 4) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.SK.Level4") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) >= 5) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.SK.Level5") - 1.0D));
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".EVOForm.isEVOEnabled")) {
                int str = NBTEditor.GetInt(damaged, "jrmcStrI");
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 1) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.EVO.Level1") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 2) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.EVO.Level2") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 3) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.EVO.Level3") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 4) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.EVO.Level4") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) >= 5) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.EVO.Level5") - 1.0D));
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".MYForm.isMYEnabled")) {
                int str = NBTEditor.GetInt(damaged, "jrmcStrI");
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 1) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.MY.Level1") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 2) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.MY.Level2") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 3) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.MY.Level3") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 4) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.MY.Level4") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) >= 5) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.MY.Level5") - 1.0D));
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".RAGEForm.isRAGEEnabled")) {
                int str = NBTEditor.GetInt(damaged, "jrmcStrI");
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 1) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.RAGE.Level1") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 2) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.RAGE.Level2") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 3) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.RAGE.Level3") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) == 4) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.RAGE.Level4") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".RAGEForm.RAGELevel")) >= 5) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.RAGE.Level5") - 1.0D));
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".SS5Form.isSS5Enabled")) {
                int str = NBTEditor.GetInt(damaged, "jrmcStrI");
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 1) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.SS5.Level1") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 2) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.SS5.Level2") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 3) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.SS5.Level3") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) == 4) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.SS5.Level4") - 1.0D));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SS5Form.SS5Level")) >= 5) {
                    ((Damageable)e.getEntity()).damage((double)str * 3.5D * (this.plugin.getConfig().getInt("FormDamages.SS5.Level5") - 1.0D));
                }
            }
        }
    }
}
