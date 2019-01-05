package me.McShovelYT.KosmicForms.Old.Methods;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.McShovelYT.KosmicForms.Old.Main;
import me.McShovelYT.KosmicForms.Old.Utils.NBTEditor;

public class GetStats {
	
	static Plugin plugin = Main.getPlugin(Main.class);
	
	public GetStats() {
	}
	
	public static int getCurStam(Player player) {
		int out = NBTEditor.GetInt(player, "jrmcStamina");
		return out;
	}
	
	public static int getMaxStam(Player player) {
		int Class = NBTEditor.GetInt(player, "jrmcClass");
		int help = 0;
		if (Class == 0) {
			help = 4;
		} else if (Class == 1) {
			help = (int) 3.6;
		} else if (Class == 2) {
			help = (int) 4.3;
		}
		int out = NBTEditor.GetInt(player, "jrmcCnsI") * help + 7500;
		return out;
	}
	
	public static int getCurEnrgy(Player player) {
		int out = NBTEditor.GetInt(player, "jrmcEnrgy");
		return out;
	}
	
	public static int getMaxEnrgy(Player player) {
		int Class = NBTEditor.GetInt(player, "jrmcClass");
		int help = 0;
		if (Class == 0) {
			help = (int) 4.3;
		} else if (Class == 1) {
			help = (int) 2.5;
		} else if (Class == 2) {
			help = (int) 2.1;
		}
		int out = NBTEditor.GetInt(player, "jrmcCncI") * help;
		return out;
	}
	
	public static int getCurHP(Player player) {
		int out = NBTEditor.GetInt(player, "jrmcBdy");
		return out;
	}
	
	public static int getMaxHP(Player player) {
		int Class = NBTEditor.GetInt(player, "jrmcClass");
		int help = 0;
		if (Class == 0) {
			help = 20;
		} else if (Class == 1) {
			help = 18;
		} else if (Class == 2) {
			help = 22;
		}
		int out = NBTEditor.GetInt(player, "jrmcCnsI") * help;
		return out;
	}
	
	public static int getCurRelease(Player player) {
		int out = NBTEditor.GetInt(player, "jrmcRelease");
		return out;
	}
	
	public static int getMaxRelease(Player player) {
		String s = NBTEditor.GetString(player, "jrmcSSlts");
		int help = 50;
		if (s.contains("OC0")) {
			help = 55;
		}
		if (s.contains("OC1")) {
			help = 60;
		}
		if (s.contains("OC2")) {
			help = 65;
		}
		if (s.contains("OC3")) {
			help = 70;
		}
		if (s.contains("OC4")) {
			help = 75;
		}
		if (s.contains("OC5")) {
			help = 80;
		}
		if (s.contains("OC6")) {
			help = 85;
		}
		if (s.contains("OC7")) {
			help = 90;
		}
		if (s.contains("OC8")) {
			help = 95;
		}
		if (s.contains("OC9")) {
			help = 100;
		}
		int out = help;
		return out;
	}
}
