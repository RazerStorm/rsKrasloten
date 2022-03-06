package be.razerstorm.rskrasloten.commands;

import be.razerstorm.rskrasloten.manager.KraslotenMNGR;
import be.razerstorm.rskrasloten.rsKrasloten;
import be.razerstorm.rskrasloten.utils.Format;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class KraslotenCMD implements CommandExecutor {

    public KraslotenCMD(rsKrasloten plugin){
        plugin.getCommand("kraslot").setExecutor(this);
    }

    public void helpMessage(Player p) {
        p.sendMessage(Format.chat("&3/kraslot &bhelp &f- &3Bekijk dit help menu."));
        p.sendMessage(Format.chat("&3/kraslot &bkrijg &f- &3Ontvang een kraslot."));
        p.sendMessage(Format.chat("&3/kraslot &binfo &f- &3Check de versie van de plugin na."));
    }

    JavaPlugin main = JavaPlugin.getPlugin(rsKrasloten.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player p = (Player) sender;
        if(args.length == 0) {
            helpMessage(p);
            return false;
        }
        if(args.length == 1) {
            if(Objects.equals(args[0], "help")) {
                helpMessage(p);
                return false;
            }else if(Objects.equals(args[0], "krijg")) {
                if (!(p.hasPermission("rskrasloten.krijg"))) {
                    p.sendMessage(Format.chat(main.getConfig().getString("messages.permission-error")));
                    return false;
                }
                KraslotenMNGR manager = new KraslotenMNGR(p);
                ItemStack kraslot = manager.getItemStack();
                p.getInventory().addItem(kraslot);
                p.sendMessage(Format.chat(main.getConfig().getString("messages.kraslot-gekregen")));
                return false;
            }else if(Objects.equals(args[0], "info")){
                p.sendMessage(Format.chat("&3rsKrasloten &8| &7Versie: &3V" + main.getDescription().getVersion()));
                p.sendMessage(Format.chat("&3rsKrasloten &8| &7Gemaakt door &3RazerStorm#4199"));
                return false;

            }else{
                helpMessage(p);
                return false;
            }
        }
        helpMessage(p);
        return false;
    }
}
