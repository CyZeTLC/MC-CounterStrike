package de.cyzetlc.mccs.utils.motd;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.server.ServerListPingEvent;
import net.minestom.server.ping.Status;

public class MotdHandler {
    public static void enableMotd(GlobalEventHandler globalEventHandler) {
        globalEventHandler.addListener(ServerListPingEvent.class, event -> {
            int onlinePlayers = MinecraftServer.getConnectionManager().getOnlinePlayerCount();

            event.setStatus(Status.builder()
                    .description(Component.text("Minecraft CS2 Server", NamedTextColor.GOLD))
                    .playerInfo(Status.PlayerInfo.builder()
                            .onlinePlayers(onlinePlayers)
                            .maxPlayers(10)
                            .build())
                    .playerInfo(onlinePlayers, 10)
                    .versionInfo(new Status.VersionInfo("1.21.11", 774))
                    .build());
        });
    }
}
