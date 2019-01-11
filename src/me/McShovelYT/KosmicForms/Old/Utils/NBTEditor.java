package me.McShovelYT.KosmicForms.Old.Utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.McShovelYT.KosmicForms.Old.Main;
import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;

public class NBTEditor {
    static Plugin plugin = Main.getPlugin(Main.class);
    
    public NBTEditor() {
    }
	
	public static void Edit(Player p, String NBTkey, Object NBTvalue) {
        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(p);
        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
        PlayerPersisted.put(NBTkey, NBTvalue);
        Forgadata.put("PlayerPersisted", PlayerPersisted);
        NBTManager.getInstance().writeForgeData(p, Forgadata);
	}
	
	public static int GetInt(Player p, String NBTkey) {
        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(p);
        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
        int out = PlayerPersisted.getInt(NBTkey);
		return out;
	}
	
	public static String GetString(Player p, String NBTkey) {
        NBTCompound Forgadata = NBTManager.getInstance().readForgeData(p);
        NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
        String out = PlayerPersisted.getString(NBTkey);
		return out;
	}
	
    public static String getHairColor(Player player) {
        try {
            Class.forName("me.dpohvar.powernbt.api.NBTManager");
            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");

            String color;
            for(color = PlayerPersisted.getString("jrmcDNS"); color.length() != 12; color = color.substring(0, color.length() - 1)) {
                ;
            }

            for(int i = 0; i < 7; ++i) {
                color = color.substring(1);
            }

            return color;
        } catch (ClassNotFoundException var5) {
            return null;
        }
    }
    
    public static Integer getAuraColor(Player player) {
        try {
            Class.forName("me.dpohvar.powernbt.api.NBTManager");
            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
            int color = PlayerPersisted.getInt("jrmcAuraColor");
            return color;
        } catch (ClassNotFoundException var4) {
            return null;
        }
    }

    public static String getTailColor(Player player) {
        try {
            Class.forName("me.dpohvar.powernbt.api.NBTManager");
            NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
            NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
            String color = PlayerPersisted.getString("jrmcDNS");
            if (PlayerPersisted.getString("jrmcDNS").length() > 19) {
                int i;
                for(i = 0; i < 26; ++i) {
                    color = color.substring(0, color.length() - 1);
                }

                for(i = 0; i < 21; ++i) {
                    color = color.substring(1);
                }
            } else {
                while(color.length() != 5) {
                    color = color.substring(1);
                }
            }

            return color;
        } catch (ClassNotFoundException var5) {
            return null;
        }
    }
    
    public static void setHairColor(Player player, String color) {
        if (color != "-1") {
            try {
                Class.forName("me.dpohvar.powernbt.api.NBTManager");
                NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                String after = PlayerPersisted.getString("jrmcDNS");

                String before;
                for(before = PlayerPersisted.getString("jrmcDNS"); before.length() != 7; before = before.substring(0, before.length() - 1)) {
                    ;
                }

                for(int i = 0; i < 12; ++i) {
                    after = after.substring(1);
                }

                if (color.length() == 5) {
                    PlayerPersisted.put("jrmcDNS", before + color + after);
                    Forgadata.put("PlayerPersisted", PlayerPersisted);
                    NBTManager.getInstance().writeForgeData(player, Forgadata);
                }
            } catch (ClassNotFoundException var7) {
                ;
            }
        }
    }
    
    public static void setAuraColor(Player player, int color) {
        if (color != -1) {
            try {
                Class.forName("me.dpohvar.powernbt.api.NBTManager");
                NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                if (String.valueOf(color).length() > 0) {
                    PlayerPersisted.put("jrmcAuraColor", color);
                    Forgadata.put("PlayerPersisted", PlayerPersisted);
                    NBTManager.getInstance().writeForgeData(player, Forgadata);
                }
            } catch (ClassNotFoundException var4) {
                ;
            }
        }

    }

    public static void setTailColor(Player player, String color) {
        if (color != "-1") {
            try {
                Class.forName("me.dpohvar.powernbt.api.NBTManager");
                NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
                NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
                if (color.length() == 5) {
                    String tailbefore = PlayerPersisted.getString("jrmcDNS");
                    String tailafter = PlayerPersisted.getString("jrmcDNS");
                    int i;
                    if (PlayerPersisted.getString("jrmcDNS").length() > 19) {
                        for(i = 0; i < 31; ++i) {
                            tailbefore = tailbefore.substring(0, tailbefore.length() - 1);
                        }

                        for(i = 0; i < 26; ++i) {
                            tailafter = tailafter.substring(1);
                        }
                    } else {
                        for(i = 0; i < 5; ++i) {
                            tailbefore = tailbefore.substring(0, tailbefore.length() - 1);
                        }

                        tailafter = "";
                    }

                    PlayerPersisted.put("jrmcDNS", tailbefore + color + tailafter);
                    Forgadata.put("PlayerPersisted", PlayerPersisted);
                    NBTManager.getInstance().writeForgeData(player, Forgadata);
                }
            } catch (ClassNotFoundException var7) {
                ;
            }
        }
    }
}
