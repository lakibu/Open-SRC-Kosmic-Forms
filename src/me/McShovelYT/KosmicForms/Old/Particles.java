package me.McShovelYT.KosmicForms.Old;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.McShovelYT.KosmicForms.Old.Utils.NBTEditor;
import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;
import net.minecraft.server.v1_7_R4.PacketPlayOutWorldParticles;

public class Particles {
    static Plugin plugin = Main.getPlugin(Main.class);

    public Particles(Main plugin) {
    }

    public static void particles() {
        (new BukkitRunnable() {

			@SuppressWarnings("deprecation")
			public void run() {
                Player[] var4;
                int var3 = (var4 = Bukkit.getServer().getOnlinePlayers()).length;

                for(int var2 = 0; var2 < var3; ++var2) {
                    Player player = var4[var2];
                    Location loc;
                    Player player2;
                    int var7;
                    int var8;
                    Player[] var9;
                    PacketPlayOutWorldParticles packet;
                    if (Particles.plugin.getConfig().getBoolean("PlayerData." + player.getUniqueId() + ".GodForm.isGodEnabled")) {
                        loc = null;
                        loc = player.getLocation();
                        var8 = (var9 = Bukkit.getServer().getOnlinePlayers()).length;

                        for(var7 = 0; var7 < var8; ++var7) {
                            player2 = var9[var7];
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int Race = PlayerPersisted.getInt("jrmcRace");
                            if (Race == 0) {
                                PlayerPersisted.put("jrmcState", 3);
                            }

                            if (Race == 1) {
                                PlayerPersisted.put("jrmcState", 9);
                            }

                            if (Race == 2) {
                                PlayerPersisted.put("jrmcState", 9);
                            }

                            if (Race == 3) {
                                PlayerPersisted.put("jrmcState", 3);
                            }

                            if (Race == 4) {
                                PlayerPersisted.put("jrmcState", 6);
                            }

                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                            packet = new PacketPlayOutWorldParticles("witchMagic", (float)loc.getX(), (float)loc.getY() + 1.0F, (float)loc.getZ(), 0.2F, 0.5F, 0.2F, 0.05F, 5);
                            ((CraftPlayer)player2).getHandle().playerConnection.sendPacket(packet);
                        }
                    }

                    if (Particles.plugin.getConfig().getBoolean("PlayerData." + player.getUniqueId() + ".UIForm.isUIEnabled")) {
                        loc = null;
                        loc = player.getLocation();
                        var8 = (var9 = Bukkit.getServer().getOnlinePlayers()).length;

                        for(var7 = 0; var7 < var8; ++var7) {
                            player2 = var9[var7];
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            PlayerPersisted.put("jrmcStatusEff", "NB");
                            PlayerPersisted.put("jrmcState", 0);
                            PlayerPersisted.put("jrmcEf8slc", 0);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                            packet = new PacketPlayOutWorldParticles("fireworksSpark", (float)loc.getX(), (float)loc.getY() + 1.0F, (float)loc.getZ(), 0.5F, 0.9F, 0.5F, 0.0F, 5);
                            ((CraftPlayer)player2).getHandle().playerConnection.sendPacket(packet);
                        }
                    }
                    
                    if (Particles.plugin.getConfig().getBoolean("PlayerData." + player.getUniqueId() + ".SKForm.isSKEnabled")) {
                        loc = null;
                        loc = player.getLocation();
                        var8 = (var9 = Bukkit.getServer().getOnlinePlayers()).length;

                        for(var7 = 0; var7 < var8; ++var7) {
                            player2 = var9[var7];
                            NBTEditor.Edit(player, "jrmcState2", 8);
                            packet = new PacketPlayOutWorldParticles("", (float)loc.getX(), (float)loc.getY() + 1.0F, (float)loc.getZ(), 0.5F, 0.9F, 0.5F, 0.0F, 5);
                            ((CraftPlayer)player2).getHandle().playerConnection.sendPacket(packet);
                        }
                    }
                    
                    if (Particles.plugin.getConfig().getBoolean("PlayerData." + player.getUniqueId() + ".EVOForm.isEVOEnabled")) {
                        loc = null;
                        loc = player.getLocation();
                        var8 = (var9 = Bukkit.getServer().getOnlinePlayers()).length;

                        for(var7 = 0; var7 < var8; ++var7) {
                            player2 = var9[var7];
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int Race = PlayerPersisted.getInt("jrmcRace");
                            int Purity = PlayerPersisted.getInt("jrmcAlign");
                            
                            if (Race == 0) {
                                PlayerPersisted.put("jrmcState", 3);
                                PlayerPersisted.put("jrmcStatusEff", "B");
                            }

                            if (Race == 1) {
                                PlayerPersisted.put("jrmcState", 15);
                                if (Purity >= 51) {
                                	PlayerPersisted.put("jrmcStatusEff", "B");
                                } else {
                                	PlayerPersisted.put("jrmcStatusEff", "VB");
                                }
                            }

                            if (Race == 2) {
                                if (Purity > 50) {
                                	PlayerPersisted.put("jrmcStatusEff", "B");
                                	PlayerPersisted.put("jrmcState", 15);
                                } 
                                if (Purity <= 50) {
                                	PlayerPersisted.put("jrmcStatusEff", "VB");
                                	PlayerPersisted.put("jrmcState", 15);
                                }
                            }

                            if (Race == 3) {
                                PlayerPersisted.put("jrmcState", 3);
                                PlayerPersisted.put("jrmcStatusEff", "B");
                            }

                            if (Race == 4) {
                                PlayerPersisted.put("jrmcState", 6);
                                PlayerPersisted.put("jrmcStatusEff", "B");
                            }
                            
                            
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }
                    }
                    
                    if (Particles.plugin.getConfig().getBoolean("PlayerData." + player.getUniqueId() + ".MYForm.isMYEnabled")) {
                        loc = null;
                        loc = player.getLocation();
                        var8 = (var9 = Bukkit.getServer().getOnlinePlayers()).length;

                        for(var7 = 0; var7 < var8; ++var7) {
                            player2 = var9[var7];
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            PlayerPersisted.put("jrmcStatusEff", "CB");
                            
                            int mnd = PlayerPersisted.getInt("jrmcIntI");
                            
                            PlayerPersisted.put("jrmcRelease", (mnd/4000) + 100);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }
                    }
                    
                }

            }
        }).runTaskTimerAsynchronously(plugin, 0L, 5L);
    }

    public static void regen() {
        (new BukkitRunnable() {
            @SuppressWarnings("deprecation")
			public void run() {
                Player[] var4;
                int var3 = (var4 = Bukkit.getServer().getOnlinePlayers()).length;

                for(int var2 = 0; var2 < var3; ++var2) {
                    Player player = var4[var2];
                    int x;
                    if (Particles.plugin.getConfig().getBoolean("PlayerData." + player.getUniqueId() + ".GodForm.isGodEnabled")) {
                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".GodForm.GodLevel")) == 1) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.GOD.Level1");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".GodForm.GodLevel")) == 2) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.GOD.Level2");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".GodForm.GodLevel")) == 3) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.GOD.Level3");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".GodForm.GodLevel")) == 4) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.GOD.Level4");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".GodForm.GodLevel")) >= 5) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.GOD.Level5");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }
                    }
                    
                    if (Particles.plugin.getConfig().getBoolean("PlayerData." + player.getUniqueId() + ".SKForm.isSKEnabled")) {
                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".SKForm.SKLevel")) == 1) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.SK.Level1");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".SKForm.SKLevel")) == 2) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.SK.Level2");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".SKForm.SKLevel")) == 3) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.SK.Level3");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".SKForm.SKLevel")) == 4) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.SK.Level4");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".SKForm.SKLevel")) == 5) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.SK.Level5");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }
                    }

                    if (Particles.plugin.getConfig().getBoolean("PlayerData." + player.getUniqueId() + ".UIForm.isUIEnabled")) {
                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".UIForm.UILevel")) == 1) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.UI.Level1");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".UIForm.UILevel")) == 2) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.UI.Level2");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".UIForm.UILevel")) == 3) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.UI.Level3");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".UIForm.UILevel")) == 4) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.UI.Level4");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".UIForm.UILevel")) >= 5) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.UI.Level5");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }
                    }
                    
                    if (Particles.plugin.getConfig().getBoolean("PlayerData." + player.getUniqueId() + ".EVOForm.isEVOEnabled")) {
                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".EVOForm.EVOLevel")) == 1) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.EVO.Level1");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".EVOForm.EVOLevel")) == 2) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.EVO.Level2");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".EVOForm.EVOLevel")) == 3) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.EVO.Level3");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".EVOForm.EVOLevel")) == 4) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.EVO.Level4");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".EVOForm.EVOLevel")) >= 5) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.EVO.Level5");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }
                    }
                    
                    if (Particles.plugin.getConfig().getBoolean("PlayerData." + player.getUniqueId() + ".MYForm.isMYEnabled")) {
                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".MYForm.MYLevel")) == 1) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.MY.Level1");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".MYForm.MYLevel")) == 2) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.MY.Level2");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".MYForm.MYLevel")) == 3) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.MY.Level3");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".MYForm.MYLevel")) == 4) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.MY.Level4");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }

                        if (Integer.parseInt(Particles.plugin.getConfig().getString("PlayerData." + player.getUniqueId() + ".MYForm.MYLevel")) >= 5) {
                            x = Particles.plugin.getConfig().getInt("FormRegens.MY.Level5");
                            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                            int bdy = PlayerPersisted.getInt("jrmcBdy");
                            PlayerPersisted.put("jrmcBdy", bdy + x);
                            Forgadata.put("PlayerPersisted", PlayerPersisted);
                            NBTManager.getInstance().writeForgeData(player, Forgadata);
                        }
                    }
                }

            }
        }).runTaskTimerAsynchronously(plugin, 0L, 80L);
    }
}
