package be.razerstorm.rskrasloten.utils;

import be.razerstorm.rskrasloten.rsKrasloten;
import be.razerstorm.rskrasloten.utils.Format;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KraslotenMNGR {

    private Player p;

    public KraslotenMNGR(Player p) {
        this.p = p;
    }

    JavaPlugin main = JavaPlugin.getPlugin(rsKrasloten.class);

    public ItemStack getItemStack() {
        ItemStack kraslotItem = new ItemStack(Material.PAPER);
        ItemMeta kraslotMeta = kraslotItem.getItemMeta();
        kraslotMeta.setDisplayName(Format.chat("&6Kraslot"));
        ArrayList<String> lores = new ArrayList<>();
        for (String currentLore : main.getConfig().getStringList("kraslot-item.lores")) {
            lores.add(Format.chat(currentLore));
        }
        kraslotMeta.setLore(lores);
        kraslotItem.setItemMeta(kraslotMeta);
        return kraslotItem;
    }

    Economy economy = rsKrasloten.getEconomy();

    public void openKraslot(double min, double max) {
        String title = main.getConfig().getString("messages.krassen.title");
        String subTitle = main.getConfig().getString("messages.krassen.subtitle");

        p.sendTitle(Format.chat(title), Format.chat(subTitle), 10, 20*3,0);
        Bukkit.getScheduler().runTaskLater(main, new Runnable() {
            @Override
            public void run() {
                DecimalFormat df = new DecimalFormat("####0.00");
                Random random = new Random();

                double rawAmount = min + (max - min) * random.nextDouble();
                double amount = Math.round(rawAmount*100.0)/100.0;

                economy.depositPlayer(p, amount);

                String title = main.getConfig().getString("messages.gekrast.title").replaceAll("%money%", String.valueOf(amount));
                String subTitle = main.getConfig().getString("messages.gekrast.subtitle").replaceAll("%money%", String.valueOf(amount));

                p.sendTitle(Format.chat(title), Format.chat(subTitle), 10, 20*2, 20);
                rsKrasloten.superCooleKrassers.remove(p.getUniqueId());
            }
        }, 20*2);

    }
}
