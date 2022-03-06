package be.razerstorm.rskrasloten;

import be.razerstorm.rskrasloten.commands.KraslotenCMD;
import be.razerstorm.rskrasloten.listeners.PlayerInteract;
import be.razerstorm.rskrasloten.utils.Metrics;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

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
