package me.McShovelYT.KosmicForms.Old.Utils;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.McShovelYT.KosmicForms.Old.Main;

public class Fireworks{

    static Plugin plugin = Main.getPlugin(Main.class);
    
    public Fireworks() {
    }
	
	public static void explosion(Location loc, Color color, Color color2) {
		final Firework fw = (Firework)loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
    	FireworkMeta meta = fw.getFireworkMeta();
    	meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(color).withFade(color2).with(Type.BALL_LARGE).build());
    	meta.addEffect(FireworkEffect.builder().trail(false).flicker(false).withColor(color2).withFade(color).with(Type.BURST).build());
    	meta.setPower(0);
    	fw.setFireworkMeta(meta);
    	(new BukkitRunnable() {
    		public void run() {
    			fw.detonate();
    			}
    		}).runTaskLater(plugin, 2L);
	}
}
