package flyproject.flychat;
/*
Powered By FlyLib
(c) Copyright 2021 FlyProject
*/

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class HoverText {
    public static TextComponent getHoverText(String text, String hovertext, String action){
        TextComponent mainComponent = new TextComponent(text);
        mainComponent.setHoverEvent( new HoverEvent(HoverEvent.Action.valueOf(action), new ComponentBuilder(hovertext).create()));
        return mainComponent;
    }

    public static TextComponent getClickHoverText(String text, String hovertext, String action, String vaule){
        TextComponent mainComponent = new TextComponent(text);
        mainComponent.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(hovertext).create()));
        mainComponent.setClickEvent(new ClickEvent(ClickEvent.Action.valueOf(action),vaule));
        return mainComponent;
    }
}
