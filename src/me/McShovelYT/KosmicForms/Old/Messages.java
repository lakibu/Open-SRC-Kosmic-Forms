package me.McShovelYT.KosmicForms.Old;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;

public class Messages {
    static Plugin plugin = Main.getPlugin(Main.class);
    public static String Prefix = ChatColor.translateAlternateColorCodes('&', "&7[&9KosmicForms&7]&r ");
    public static String Ascended;
    public static String Descended;
    public static String InsufficientPermissions;
    public static String Reload;
    public static String SetForm1;
    public static String SetForm2;
    public static String SetFormInvalidForm;
    public static String SetFormHelp;

    static {
        Ascended = ChatColor.translateAlternateColorCodes('&', Prefix + plugin.getConfig().getString("Messages.Ascended"));
        Descended = ChatColor.translateAlternateColorCodes('&', Prefix + plugin.getConfig().getString("Messages.Descended"));
        InsufficientPermissions = ChatColor.translateAlternateColorCodes('&', Prefix + plugin.getConfig().getString("Messages.InsufficientPermissions"));
        Reload = ChatColor.translateAlternateColorCodes('&', Prefix + plugin.getConfig().getString("Messages.Reload"));
        SetForm1 = ChatColor.translateAlternateColorCodes('&', Prefix + plugin.getConfig().getString("Messages.SetForm1"));
        SetForm2 = ChatColor.translateAlternateColorCodes('&', Prefix + plugin.getConfig().getString("Messages.SetForm2"));
        SetFormInvalidForm = ChatColor.translateAlternateColorCodes('&', Prefix + plugin.getConfig().getString("Messages.SetFormInvalidForm"));
        SetFormHelp = ChatColor.translateAlternateColorCodes('&', Prefix + plugin.getConfig().getString("Messages.SetFormHelp"));
    }

    public Messages(Messages plugin) {
    }

    public static void CreateMessagesConfig() {
        if (plugin.getConfig().getString("Messages.InsufficientPermissions") == null) {
            plugin.getConfig().set("Messages.InsufficientPermissions", "&4Insufficient Permissions");
            plugin.saveConfig();
        }

        if (plugin.getConfig().getString("Messages.Reload") == null) {
            plugin.getConfig().set("Messages.Reload", "&9Config reloaded succufully");
            plugin.saveConfig();
        }

        if (plugin.getConfig().getString("Messages.SetForm1") == null) {
            plugin.getConfig().set("Messages.SetForm1", "&9You have successfully given ");
            plugin.saveConfig();
        }

        if (plugin.getConfig().getString("Messages.SetForm2") == null) {
            plugin.getConfig().set("Messages.SetForm2", "&9Level ");
            plugin.saveConfig();
        }

        if (plugin.getConfig().getString("Messages.SetFormInvalidForm") == null) {
            plugin.getConfig().set("Messages.SetFormInvalidForm", "&cIs not a valid form");
            plugin.saveConfig();
        }

        if (plugin.getConfig().getString("Messages.SetFormHelp") == null) {
            plugin.getConfig().set("Messages.SetFormHelp", "&c/sSetForm <USERNAME> <GOD/UI> <LEVEL>");
            plugin.saveConfig();
        }

        if (plugin.getConfig().getString("Messages.GiveTPGOD") == null) {
            plugin.getConfig().set("Messages.SetForm2", "&G.O.D form TP");
            plugin.saveConfig();
        }

        if (plugin.getConfig().getString("Messages.SetForm2") == null) {
            plugin.getConfig().set("Messages.SetForm2", "&9Level ");
            plugin.saveConfig();
        }

        if (plugin.getConfig().getString("Messages.SetForm2") == null) {
            plugin.getConfig().set("Messages.SetForm2", "&9Level ");
            plugin.saveConfig();
        }

        if (plugin.getConfig().getString("Messages.SetForm2") == null) {
            plugin.getConfig().set("Messages.SetForm2", "&9Level ");
            plugin.saveConfig();
        }

        if (plugin.getConfig().getString("Messages.Ascended") == null) {
            plugin.getConfig().set("Messages.Ascended", "&9You have ascended into ");
            plugin.saveConfig();
        }

        if (plugin.getConfig().getString("Messages.Descended") == null) {
            plugin.getConfig().set("Messages.Descended", "&9You have descended from ");
            plugin.saveConfig();
        }

    }
}
