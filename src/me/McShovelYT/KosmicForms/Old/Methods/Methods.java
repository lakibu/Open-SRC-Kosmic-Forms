package me.McShovelYT.KosmicForms.Old.Methods;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Methods {

	
    public static ItemStack createItemStack(Material material, int damage, String name, String lore) {
        ItemStack itemStack = new ItemStack(material, 1, (short)damage);
        ItemMeta itemStackMeta = itemStack.getItemMeta();
        itemStackMeta.setDisplayName(name);
        itemStackMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemStackMeta);
        return itemStack;
    }
    
    public static String lowerCaseString(String string, String string2) {
        return string.toLowerCase().indexOf(string2.toLowerCase()) == -1 ? string : string.replaceAll(string.substring(string.toLowerCase().indexOf(string2.toLowerCase()), string.toLowerCase().indexOf(string2.toLowerCase()) + string2.length()), string2);
    }
}
