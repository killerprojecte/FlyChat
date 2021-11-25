package flyproject.flychat;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class ChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        String msg = event.getMessage();
        event.setCancelled(true);
        TextComponent tc = new TextComponent();
        TextComponent prefix = Prefix(event.getPlayer());
        TextComponent playername = PlayerName(event.getPlayer());
        tc.addExtra(prefix);
        tc.addExtra(playername);
        tc.addExtra(Message(msg,event.getPlayer()));
        tc.addExtra(suffix(event.getPlayer()));
        for (Player player : Bukkit.getOnlinePlayers()){
            player.spigot().sendMessage((BaseComponent) tc);
        }
    }
    private TextComponent Prefix(Player player){
        File config = new File(flyproject.flychat.FlyChat.getPlugin(flyproject.flychat.FlyChat.class).getDataFolder(),"config.yml");
        FileConfiguration cconfig = YamlConfiguration.loadConfiguration(config);
        String prefix = ChatColor.translateAlternateColorCodes('&',cconfig.getString("format.prefix.text"));
        prefix = PlaceholderAPI.setPlaceholders(player,prefix);
        String pcommand = cconfig.getString("format.prefix.command");
        pcommand = PlaceholderAPI.setPlaceholders(player,pcommand);
        StringBuilder phs = new StringBuilder();
        for (String prefixhover:cconfig.getStringList("format.prefix.hovertext")){
            prefixhover = PlaceholderAPI.setPlaceholders(player,prefixhover);
            phs.append(prefixhover);
            phs.append("\n");
        }
        phs.delete(phs.length() - 1,phs.length());
        TextComponent txh;
        txh = HoverText.getClickHoverText(prefix,phs.toString(),getActionClickType(cconfig.getString("format.prefix.action")),pcommand);
        return txh;
    }
    public static String getActionClickType(String arg) {
        String temp = arg;
        if (temp.equalsIgnoreCase("url"))
            return "OPER_URL";
        if (temp.equalsIgnoreCase("cmd"))
            return "RUN_COMMAND";
        if (temp.equalsIgnoreCase("suggest_cmd"))
            return "SUGGEST_COMMAND";
        return null;
    }
    private TextComponent PlayerName(Player player){
        File config = new File(flyproject.flychat.FlyChat.getPlugin(flyproject.flychat.FlyChat.class).getDataFolder(),"config.yml");
        FileConfiguration cconfig = YamlConfiguration.loadConfiguration(config);
        String prefix = ChatColor.translateAlternateColorCodes('&',cconfig.getString("format.player.text"));
        prefix = PlaceholderAPI.setPlaceholders(player,prefix);
        String pcommand = cconfig.getString("format.player.command");
        pcommand = PlaceholderAPI.setPlaceholders(player,pcommand);
        StringBuilder phs = new StringBuilder();
        for (String prefixhover:cconfig.getStringList("format.player.hovertext")){
            prefixhover = PlaceholderAPI.setPlaceholders(player,prefixhover);
            phs.append(prefixhover);
            phs.append("\n");
        }
        phs.delete(phs.length() - 1,phs.length());
        TextComponent txh;
        txh = HoverText.getClickHoverText(prefix,phs.toString(),getActionClickType(cconfig.getString("format.player.action")),pcommand);
        return txh;
    }
    private TextComponent suffix(Player player){
        File config = new File(flyproject.flychat.FlyChat.getPlugin(flyproject.flychat.FlyChat.class).getDataFolder(),"config.yml");
        FileConfiguration cconfig = YamlConfiguration.loadConfiguration(config);
        String prefix = ChatColor.translateAlternateColorCodes('&',cconfig.getString("format.suffix.text"));
        prefix = PlaceholderAPI.setPlaceholders(player,prefix);
        String pcommand = cconfig.getString("format.suffix.command");
        pcommand = PlaceholderAPI.setPlaceholders(player,pcommand);
        StringBuilder phs = new StringBuilder();
        for (String prefixhover:cconfig.getStringList("format.suffix.hovertext")){
            prefixhover = PlaceholderAPI.setPlaceholders(player,prefixhover);
            phs.append(prefixhover);
            phs.append("\n");
        }
        phs.delete(phs.length() - 1,phs.length());
        TextComponent txh;
        txh = HoverText.getClickHoverText(prefix,phs.toString(),getActionClickType(cconfig.getString("format.suffix.action")),pcommand);
        return txh;
    }
    private String Message(String msg, Player player){
        String bycheck = checkColor(player,msg,false);
        return bycheck;
    }
    public static String checkColor(Player paramPlayer, String paramString, boolean paramBoolean) {
        paramString = paramString.replaceAll("[§]", "&");
        if (paramBoolean) {
            if (!paramPlayer.hasPermission("flychat.p.color")) {
                String str = "(&+)([0-9a-fA-F])";
                paramString = paramString.replaceAll(str, "");
            }
            if (!paramPlayer.hasPermission("flychat.p.formatting")) {
                String str = "(&+)([k-orK-OR])";
                paramString = paramString.replaceAll(str, "");
            }
        } else {
            if (!paramPlayer.hasPermission("flychat.color")) {
                String str = "(&+)([0-9a-fA-F])";
                paramString = paramString.replaceAll(str, "");
            }
            if (!paramPlayer.hasPermission("flychat.formatting")) {
                String str = "(&+)([k-orK-OR])";
                paramString = paramString.replaceAll(str, "");
            }
        }
        return paramString;
    }
}
