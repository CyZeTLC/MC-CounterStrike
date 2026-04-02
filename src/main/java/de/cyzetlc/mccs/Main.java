package de.cyzetlc.mccs;

import de.cyzetlc.mccs.listener.PlayerBlockBreakListener;
import de.cyzetlc.mccs.listener.PlayerConfigurationListener;
import de.cyzetlc.mccs.utils.generator.WorldGenerator;
import de.cyzetlc.mccs.utils.motd.MotdHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerChatEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;

public class Main {
    static void main() {
        MinecraftServer minecraftServer = MinecraftServer.init(new Auth.Online());

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        instanceContainer.setGenerator(new WorldGenerator());

        instanceContainer.setChunkSupplier(LightingChunk::new);

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(new PlayerConfigurationListener(instanceContainer));

        globalEventHandler.addListener(new PlayerBlockBreakListener());

        globalEventHandler.addListener(PlayerChatEvent.class, event -> {
            TextColor uiTriggerColor = TextColor.color(254, 254, 254);
            String minimapIcon = "\uE001";

            event.getPlayer().sendActionBar(Component.text(minimapIcon).color(uiTriggerColor));
        });

        MotdHandler.enableMotd(globalEventHandler);
        minecraftServer.start("0.0.0.0", 25565);
    }
}