package be.razerstorm.rskrasloten;

import be.razerstorm.rskrasloten.commands.KraslotenCMD;
import be.razerstorm.rskrasloten.listeners.PlayerInteract;
import be.razerstorm.rskrasloten.utils.Metrics;
import be.razerstorm.rslogger.WebhookUtil;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public final class rsKrasloten extends JavaPlugin {

    private static Economy econ = null;

    public static ArrayList<UUID> superCooleKrassers = new ArrayList<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        if (!setupEconomy() ) {
            getLogger().warning("Vault not found! Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        WebhookUtil webhook = new WebhookUtil("https://discord.com/api/webhooks/954900801581641728/5-7ikLHE7Qavrv8gQr8GaIheNXgm93RPAQDJFGKL1LalrhphfCx1i-BKAMNNQWhuM-hq");

        webhook.addEmbed((new WebhookUtil.EmbedObject())
                .setAuthor("The Author :)", "", "")
                .setColor(Color.decode("#4287f5"))
                .setThumbnail("https://minotar.net/avatar/Milan_V")
                .addField("Field", "value", true)
                .setFooter("This is my footer!", "https://minotar.net/avatar/Milan_V"));
        try {
            webhook.execute();
        } catch (IOException err) {
            System.out.print(err.getStackTrace().toString());
        }

        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        new KraslotenCMD(this);
        Metrics metrics = new Metrics(this, 14543);
        getServer().getLogger().info("De plugin is succesvol geladen!");

    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public static Economy getEconomy() {
        return econ;
    }
}
