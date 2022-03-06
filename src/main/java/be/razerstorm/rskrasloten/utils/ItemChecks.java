package be.razerstorm.rskrasloten.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemChecks {
    public static boolean checkItemStackDisplayName(ItemStack first, ItemStack second) {
        ItemMeta firstMeta = first.getItemMeta();
        ItemMeta secondMeta = second.getItemMeta();
        if(firstMeta.getDisplayName().equals(secondMeta.getDisplayName())) {
            return true;
        }else {
            return false;
        }
    }
    public static boolean checkItemStackLores(ItemStack first, ItemStack second) {
        ItemMeta firstMeta = first.getItemMeta();
        ItemMeta secondMeta = second.getItemMeta();
        if(firstMeta.getLore().equals(secondMeta.getLore())) {
            return true;
        }else {
            return false;
        }
    }
}
