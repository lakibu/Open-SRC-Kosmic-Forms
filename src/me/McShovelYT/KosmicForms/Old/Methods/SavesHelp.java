package me.McShovelYT.KosmicForms.Old.Methods;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.McShovelYT.KosmicForms.Old.Main;
import me.McShovelYT.KosmicForms.Old.Utils.NBTEditor;
import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;

public class SavesHelp {
    static Plugin plugin = Main.getPlugin(Main.class);
    
    public SavesHelp() {
    }
    
    public static void save(Player player, Object SaveNumber) {
	    NBTCompound Forgadata = NBTManager.getInstance().readForgeData(player);
	    NBTCompound PlayerPersisted = (NBTCompound)Forgadata.get("PlayerPersisted");
		
		FileConfiguration f = plugin.getConfig();
		
        int str = NBTEditor.GetInt(player, "jrmcStrI");
        int dex = NBTEditor.GetInt(player, "jrmcDexI");
        int con = NBTEditor.GetInt(player, "jrmcCnsI");
        int wil = NBTEditor.GetInt(player, "jrmcWilI");
        int mnd = NBTEditor.GetInt(player, "jrmcIntI");
        int spi = NBTEditor.GetInt(player, "jrmcCncI");
		
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".CON", con);
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".STR", str);
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".DEX", dex);
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".WIL", wil);
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".MND", mnd);
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".SPI", spi);
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".RACE", PlayerPersisted.get("jrmcRace"));
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".SSltY", PlayerPersisted.get("jrmcSSltY"));
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".SSltX", PlayerPersisted.get("jrmcSSltX"));
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".SSltZ", PlayerPersisted.get("jrmcSSltZ"));
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".SSltS", PlayerPersisted.get("jrmcSSltS"));
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".type", PlayerPersisted.get("jrmcPwrtyp"));
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".class", PlayerPersisted.get("jrmcClass"));
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".Aura", PlayerPersisted.get("jrmcAuraColor"));
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".tp", PlayerPersisted.get("jrmcTpint"));
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".DNS", PlayerPersisted.get("jrmcDNS"));
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".DNSH", PlayerPersisted.get("jrmcDNSH"));
    }
    
    public static void write(Player player, Object SaveNumber) {
		FileConfiguration f = plugin.getConfig();
		
		Object a = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".CON");
		Object b = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".STR");
		Object c = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".DEX");
		Object d = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".WIL");
		Object e = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".MND");
		Object g = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".SPI");
		Object h = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".RACE");
		Object i = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".SSltY");
		Object j = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".SSltX");
		Object k = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".SSltZ");
		Object l = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".SSltS");
		Object m = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".type");
		Object n = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".class");
		Object o = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".Aura");
		Object p = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".tp");
		Object q = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".DNS");
		Object r = f.get("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber + ".DNSH");
		
		NBTEditor.Edit(player, "jrmcCnsI", a);
		NBTEditor.Edit(player, "jrmcStrI", b);
		NBTEditor.Edit(player, "jrmcDexI", c);
		NBTEditor.Edit(player, "jrmcWilI", d);
		NBTEditor.Edit(player, "jrmcIntI", e);
		NBTEditor.Edit(player, "jrmcCncI", g);
		NBTEditor.Edit(player, "jrmcRace", h);
		NBTEditor.Edit(player, "jrmcSSltY", i);
		NBTEditor.Edit(player, "jrmcSSltX", j);
		NBTEditor.Edit(player, "jrmcSSltZ", k);
		NBTEditor.Edit(player, "jrmcSSltS", l);
		NBTEditor.Edit(player, "jrmcPwrtyp", m);
		NBTEditor.Edit(player, "jrmcClass", n);
		NBTEditor.Edit(player, "jrmcAuraColor", o);
		NBTEditor.Edit(player, "jrmcTpint", p);
		NBTEditor.Edit(player, "jrmcDNS", q);
		NBTEditor.Edit(player, "jrmcDNSH", r);
    }
    
    public static void clear(Player player, Object SaveNumber) {
    	FileConfiguration f = plugin.getConfig();
    	
		f.set("PlayerData." + player.getUniqueId() + ".Save" + SaveNumber, null);
    }
}