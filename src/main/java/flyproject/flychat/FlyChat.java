package flyproject.flychat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlyChat extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("\n ______ _        _____ _           _   \n" +
                "|  ____| |      / ____| |         | |  \n" +
                "| |__  | |_   _| |    | |__   __ _| |_ \n" +
                "|  __| | | | | | |    | '_ \\ / _` | __|\n" +
                "| |    | | |_| | |____| | | | (_| | |_ \n" +
                "|_|    |_|\\__, |\\_____|_| |_|\\__,_|\\__|\n" +
                "           __/ |                       \n" +
                "          |___/                        \n" +
                "\n(c) Copyright 2021 FlyProject\n" +
                "Version: 1.0-SNAPSHOT");
        Bukkit.getPluginManager().registerEvents(new ChatEvent(),this);
        saveDefaultConfig();
        // Plugin startup logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args[0].equalsIgnoreCase("reload")){
            sender.sendMessage("&7[&bFlyChat&7] &a配置文件成功重载");
            reloadConfig();
        } if (args[0].equalsIgnoreCase("test")) {
            Player p = (Player) sender;
            p.spigot().sendMessage(HoverText.getClickHoverText("测试","测试内容","SUGGEST_COMMAND","测试"));
            sender.sendMessage("&7&m=========================");
            sender.sendMessage("&bFlyChat v1.0-SNAPSHOT");
            sender.sendMessage("&b/flychat reload ———— 重载配置文件");
            sender.sendMessage("&7&m=========================");
        }
        return super.onCommand(sender, command, label, args);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private static FlyChat instance;
    public static FlyChat getInstance(){
        return instance;
    }
}
