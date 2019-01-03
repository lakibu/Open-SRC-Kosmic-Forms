package me.McShovelYT.KosmicForms.Old.Methods;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import me.McShovelYT.KosmicForms.Old.Main;

public class CreateUpgradeItemLogic {
    static Plugin plugin = Main.getPlugin(Main.class);

    public CreateUpgradeItemLogic(CreateUpgradeItemLogic plugin) {
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void Create(Plugin plugin, Player player, ItemMeta Meta, Integer Level, String LevelConfig, Integer Cost, String Color) {
        int PrevLevel = Level - 1;
        List Lore;
        if (plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + LevelConfig) == null) {
            Lore = Arrays.asList(ChatColor.RED + "Level 1 not purchased");
            Meta.setLore(Lore);
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + LevelConfig)) == PrevLevel) {
            Lore = Arrays.asList(Color + "Click to purchase", Color + "Cost: " + Cost);
            Meta.setLore(Lore);
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + LevelConfig)) > PrevLevel) {
            Lore = Arrays.asList(Color + "Already purchased");
            Meta.setLore(Lore);
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + LevelConfig)) < PrevLevel) {
            Lore = Arrays.asList(ChatColor.RED + "Level " + PrevLevel + " not purchased");
            Meta.setLore(Lore);
        }

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static void CreateFirst(Plugin plugin, Player player, ItemMeta Meta, String LevelConfig, Integer Cost, String Color) {
        List Lore;
        if (plugin.getConfig().getString("PlayerData." + player.getUniqueId() + LevelConfig) == null) {
            Lore = Arrays.asList(Color + "Click to purchase", Color + "Cost: " + Cost);
            Meta.setLore(Lore);
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + LevelConfig)) > 0) {
            Lore = Arrays.asList(Color + "Already purchased");
            Meta.setLore(Lore);
        } else if (Integer.parseInt(plugin.getConfig().getString("PlayerData." + player.getPlayer().getUniqueId() + LevelConfig)) < 1) {
            Lore = Arrays.asList(Color + "Click to purchase", Color + "Cost: " + Cost);
            Meta.setLore(Lore);
        }

    }
}
