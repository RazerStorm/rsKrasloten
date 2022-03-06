package be.razerstorm.rskrasloten;

import be.razerstorm.rskrasloten.listeners.PlayerInteract;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class rsKrasloten extends JavaPlugin {

    private static @Getter rsKrasloten instance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
