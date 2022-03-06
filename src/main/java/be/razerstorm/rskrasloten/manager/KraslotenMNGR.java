package be.razerstorm.rskrasloten.manager;

import be.razerstorm.rskrasloten.rsKrasloten;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KraslotenMNGR {

    private Player p;

    public KraslotenMNGR(Player p) {
        this.p = p;
    }

    public ItemStack getItemStack() {
        ItemStack kraslotItem = new ItemStack(Material.PAPER);
        List<String> lores = rsKrasloten.getInstance().getConfig().getStringList("lores");
        ItemMeta kraslotMeta = kraslotItem.getItemMeta();
        kraslotMeta.setDisplayName("&6Kraslot");
        kraslotMeta.setLore(lores);
        return kraslotItem;
    }

    public void openKraslot(int min, int max) {
        p.getInventory().remove(getItemStack());
        Random random = new Random();
        int amount = random.nextInt(max - min) + min;
        p.sendTitle("je moeder is gescammed voor 3 euro", "Je hebt " + amount + " ontvangen!");
    }
}
