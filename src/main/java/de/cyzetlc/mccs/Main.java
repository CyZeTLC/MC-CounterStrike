package de.cyzetlc.mccs;

import de.cyzetlc.mccs.utils.motd.MotdHandler;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;

public class Main {
    static void main(String[] args) {
        System.out.println("Hello world!");

        MinecraftServer minecraftServer = MinecraftServer.init(new Auth.Online());

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        instanceContainer.setGenerator(unit -> {
            unit.modifier().fillHeight(40,41, Block.GRASS_BLOCK);
            unit.modifier().fillHeight(1,40, Block.DIRT);
            unit.modifier().fillHeight(0,1, Block.BEDROCK);
        });

        instanceContainer.setChunkSupplier(LightingChunk::new);

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 42, 0));
            player.setGameMode(GameMode.CREATIVE);
        });

        MotdHandler.enableMotd(globalEventHandler);
        minecraftServer.start("0.0.0.0", 25565);
    }
}