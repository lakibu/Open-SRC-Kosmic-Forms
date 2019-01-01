package me.McShovelYT.KosmicForms.Old;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;

import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
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
                    
                    if (this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SelectedForm") != null) {
                        if (this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SelectedForm").equalsIgnoreCase("god")) {
                            if (this.plugin.getConfig().getBoolean("PlayerData." + e.getPlayer().getUniqueId() + ".GodForm.isGodEnabled")) {
                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", false);
                                e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.DARK_PURPLE + "G.O.D Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".GodForm.GodLevel") + ChatColor.BLUE + ".");

                                PlayerPersisted.put("jrmcState", 0);
                                Forgadata.put("PlayerPersisted", PlayerPersisted);
                                NBTManager.getInstance().writeForgeData(player, Forgadata);
                            } else {
                            	
                            	if (level >= 20000) {
                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".GodForm.isGodEnabled", true);
                                e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.DARK_PURPLE + "G.O.D Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".GodForm.GodLevel") + ChatColor.BLUE + "!");
                            	
                                final Firework fw = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
                                FireworkMeta meta = fw.getFireworkMeta();
                                meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.PURPLE).withFade(Color.fromRGB(205, 132, 255)).with(Type.BALL_LARGE).build());
                                meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.fromRGB(206, 140, 216)).withFade(Color.PURPLE).with(Type.BURST).build());
                                meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.fromRGB(108, 0, 181)).withFade(Color.PURPLE).with(Type.BALL).build());
                                meta.setPower(0);
                                fw.setFireworkMeta(meta);
                                (new BukkitRunnable() {
                                	public void run() {
                                		fw.detonate();
                                	}
                                }).runTaskLater(plugin, 2L);
                                
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
                                    PlayerPersisted.put("jrmcStatusEff", "");
                                    int Race = PlayerPersisted.getInt("jrmcRace");
                                    if (Race == 0) {
                                    	PlayerPersisted.put("jrmcDNS", this.plugin.getConfig().get("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor"));
                                    }
                                    if (Race == 1) {
                                    	PlayerPersisted.put("jrmcDNS", this.plugin.getConfig().get("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor"));
                                    }
                                    if (Race == 2) {
                                    	PlayerPersisted.put("jrmcDNS", this.plugin.getConfig().get("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor"));
                                    }
                                    Forgadata.put("PlayerPersisted", PlayerPersisted);
                                    NBTManager.getInstance().writeForgeData(player, Forgadata);
                                }
                            } else {
                            	
                            	if (level >= 40000) {

                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".UIForm.isUIEnabled", true);
                                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".UIForm.UILevel")) < 5) {
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "U.I Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".UIForm.UILevel") + ChatColor.BLUE + "!");
                                    
                                    final Firework fw = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
                                    FireworkMeta meta = fw.getFireworkMeta();
                                    meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.AQUA).withFade(Color.BLUE).with(Type.BALL_LARGE).build());
                                    meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.BLUE).withFade(Color.AQUA).with(Type.BURST).build());
                                    meta.setPower(0);
                                    fw.setFireworkMeta(meta);
                                    (new BukkitRunnable() {
                                    	public void run() {
                                    		fw.detonate();
                                    	}
                                    }).runTaskLater(plugin, 2L);
                                } else {
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.WHITE + ChatColor.BOLD + "M.U.I Form" + ChatColor.BLUE + "!");
                                    String var1 = PlayerPersisted.getString("jrmcDNS");
                                    int Race = PlayerPersisted.getInt("jrmcRace");
                                    if (Race == 0) {
                                    this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor", var1);
                                    PlayerPersisted.put("jrmcDNS", "020000010oa7010016nKR0rgre00000000000000000PCEb0PCEb");
                                    }
                                    if (Race == 1) {
                                    this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor", var1);
                                    PlayerPersisted.put("jrmcDNS", "020000010oa7010016nKR0rgre00000000000000000PCEb0PCEb");
                                    }
                                    if (Race == 2) {
                                    this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultHairColor", var1);
                                    PlayerPersisted.put("jrmcDNS", "020000010oa7010016nKR0rgre00000000000000000PCEb0PCEb");
                                    }
                                    Forgadata.put("PlayerPersisted", PlayerPersisted);
                                    NBTManager.getInstance().writeForgeData(player, Forgadata);
                                    
                                    final Firework fw = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
                                    FireworkMeta meta = fw.getFireworkMeta();
                                    meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.WHITE).withFade(Color.SILVER).with(Type.BALL_LARGE).build());
                                    meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.SILVER).withFade(Color.WHITE).with(Type.BURST).build());
                                    meta.setPower(0);
                                    fw.setFireworkMeta(meta);
                                    (new BukkitRunnable() {
                                    	public void run() {
                                    		fw.detonate();
                                    	}
                                    }).runTaskLater(plugin, 2L);
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
                                    PlayerPersisted.put("jrmcState2", "");
                                    Forgadata.put("PlayerPersisted", PlayerPersisted);
                                    NBTManager.getInstance().writeForgeData(player, Forgadata);
                            } else {
                            	
                            	if (level >= 15000) {
                            	
                                this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".SKForm.isSKEnabled", true);
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.RED + "S.K Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".SKForm.SKLevel") + ChatColor.BLUE + "!");
                                    
                                    final Firework fw = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
                                    FireworkMeta meta = fw.getFireworkMeta();
                                    meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.fromRGB(80, 0, 0)).withFade(Color.RED).with(Type.BALL_LARGE).build());
                                    meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.RED).withFade(Color.fromRGB(80, 0, 0)).with(Type.BURST).build());
                                    meta.setPower(0);
                                    fw.setFireworkMeta(meta);
                                    (new BukkitRunnable() {
                                    	public void run() {
                                    		fw.detonate();
                                    	}
                                    }).runTaskLater(plugin, 2L);
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
                                    	e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.RESET + ChatColor.DARK_BLUE + "Mastered SS Blue level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.EVOLevel") + ChatColor.BLUE + "!");
                                	} else {
                                    	e.getPlayer().sendMessage(ChatColor.BLUE + "You have descended from " + ChatColor.RESET + ChatColor.DARK_PURPLE + "Mastered SS Rose level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.EVOLevel") + ChatColor.BLUE + "!");
                                	}
                                    PlayerPersisted.put("jrmcState", "");
                                    PlayerPersisted.put("jrmcStatusEff", "");
                                    Forgadata.put("PlayerPersisted", PlayerPersisted);
                                    NBTManager.getInstance().writeForgeData(player, Forgadata);
                            } else {
                            	
                            	if (level >= 30000) {
                                	this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.isEVOEnabled", true);
                                	int Purity = PlayerPersisted.getInt("jrmcAlign");
                                
                                	if (Purity >= 51) {
                                    	e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.DARK_BLUE + "Mastered SS Blue level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.EVOLevel") + ChatColor.BLUE + "!");
                                	} else {
                                    	e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.DARK_PURPLE + "Mastered SS Rose level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".EVOForm.EVOLevel") + ChatColor.BLUE + "!");
                                	}
                                    
                                    final Firework fw = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
                                    FireworkMeta meta = fw.getFireworkMeta();
                                    

                                    
                                    if (Purity >= 51) {
                                        meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.AQUA).withFade(Color.BLUE).with(Type.BALL_LARGE).build());
                                        meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.BLUE).withFade(Color.AQUA).with(Type.BURST).build());
                                    } else {
                                        meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.PURPLE).withFade(Color.FUCHSIA).with(Type.BALL_LARGE).build());
                                        meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.FUCHSIA).withFade(Color.PURPLE).with(Type.BURST).build());
                                    }
                                    meta.setPower(0);
                                    fw.setFireworkMeta(meta);
                                    (new BukkitRunnable() {
                                    	public void run() {
                                    		fw.detonate();
                                    	}
                                    }).runTaskLater(plugin, 2L);
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
                                    PlayerPersisted.put("jrmcStatusEff", "");
                                    PlayerPersisted.put("jrmcRelease", 100);
                                    int var = this.plugin.getConfig().getInt("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultAuraColor");
                                    int var1 = this.plugin.getConfig().getInt("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultPurity");
                                    PlayerPersisted.put("jrmcAuraColor", var);
                                    PlayerPersisted.put("jrmcAlign", var1);
                                    Forgadata.put("PlayerPersisted", PlayerPersisted);
                                    NBTManager.getInstance().writeForgeData(player, Forgadata);
                            } else {
                                if (level >= 25000) {
                                if (mnd >= 4000) {
                                	
                                	int Aura = PlayerPersisted.getInt("jrmcAuraColor");
                                	
                                	int Purity = PlayerPersisted.getInt("jrmcAlign");
                                	
                                	this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".MYForm.isMYEnabled", true);
                                	
                                	this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultAuraColor", Aura);
                                	this.plugin.getConfig().set("PlayerData." + e.getPlayer().getUniqueId() + ".DefaultPurity", Purity);
                                	PlayerPersisted.put("jrmcAuraColor", 12189689);
                                	PlayerPersisted.put("jrmcAlign", 100);
                                    Forgadata.put("PlayerPersisted", PlayerPersisted);
                                    NBTManager.getInstance().writeForgeData(player, Forgadata);
                                    
                                    e.getPlayer().sendMessage(ChatColor.BLUE + "You have ascended into " + ChatColor.RESET + ChatColor.BOLD + "Mystic Form level " + this.plugin.getConfig().getString("PlayerData." + e.getPlayer().getUniqueId() + ".MYForm.MYLevel") + ChatColor.BLUE + "!");
                                    
                                    final Firework fw = (Firework) player.getWorld().spawn(player.getLocation(), Firework.class);
                                    FireworkMeta meta = fw.getFireworkMeta();
                                    meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.BLUE).withFade(Color.AQUA).with(Type.BALL_LARGE).build());
                                    meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(Color.AQUA).withFade(Color.WHITE).with(Type.BURST).build());
                                    meta.setPower(0);
                                    fw.setFireworkMeta(meta);
                                    (new BukkitRunnable() {
                                    	public void run() {
                                    		fw.detonate();
                                    	}
                                    }).runTaskLater(plugin, 2L);
                                

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
        }
        
        if (e.getDamager() instanceof Player) {
            damaged = (Player)e.getDamager();
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".GodForm.isGodEnabled")) {
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 1) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.GOD.Level1"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 2) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.GOD.Level2"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 3) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.GOD.Level3"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) == 4) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.GOD.Level4"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".GodForm.GodLevel")) >= 5) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.GOD.Level5"));
                }
            }

            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".UIForm.isUIEnabled")) {
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 1) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.UI.Level1"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 2) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.UI.Level2"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 3) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.UI.Level3"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) == 4) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.UI.Level4"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".UIForm.UILevel")) >= 5) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.UI.Level5"));
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".SKForm.isSKEnabled")) {
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 1) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.SK.Level1"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 2) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.SK.Level2"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 3) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.SK.Level3"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 4) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.SK.Level4"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".SKForm.SKLevel")) == 5) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.SK.Level5"));
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".EVOForm.isEVOEnabled")) {
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 1) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.EVO.Level1"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 2) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.EVO.Level2"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 3) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.EVO.Level3"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) == 4) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.EVO.Level4"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".EVOForm.EVOLevel")) >= 5) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.EVO.Level5"));
                }
            }
            
            if (this.plugin.getConfig().getBoolean("PlayerData." + damaged.getUniqueId() + ".MYForm.isMYEnabled")) {
                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 1) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.MY.Level1"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 2) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.MY.Level2"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 3) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.MY.Level3"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 4) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.MY.Level4"));
                }

                if (Integer.parseInt(this.plugin.getConfig().getString("PlayerData." + damaged.getUniqueId() + ".MYForm.MYLevel")) == 5) {
                    ((Damageable)e.getEntity()).damage(this.plugin.getConfig().getInt("FormDamages.MY.Level5"));
                }
            }
        }
    }
}
