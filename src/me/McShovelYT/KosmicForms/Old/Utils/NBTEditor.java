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
}
