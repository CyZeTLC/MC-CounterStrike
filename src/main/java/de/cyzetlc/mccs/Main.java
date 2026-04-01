package de.cyzetlc.mccs;

import de.cyzetlc.mccs.utils.generator.WorldGenerator;
import de.cyzetlc.mccs.utils.motd.MotdHandler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.minestom.server.Auth;
import net.minestom.server.MinecraftServer;
import net.minestom.server.advancements.FrameType;
import net.minestom.server.advancements.Notification;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.event.player.PlayerChatEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class Main {
    static void main(String[] args) {
        System.out.println("Hello world!");

        MinecraftServer minecraftServer = MinecraftServer.init(new Auth.Online());

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instanceContainer = instanceManager.createInstanceContainer();

        instanceContainer.setGenerator(new WorldGenerator());

        instanceContainer.setChunkSupplier(LightingChunk::new);

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 42, 0));
            player.setGameMode(GameMode.CREATIVE);
        });

        globalEventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            Notification notification = new Notification(
                    Component.text("Hello, Notifications!", NamedTextColor.GREEN),
                    FrameType.GOAL,
                    ItemStack.of(Material.GOLD_INGOT)
            );

            event.getPlayer().sendNotification(notification);

            Material material = event.getBlock().registry().material();

            if (material != null && material != Material.AIR) {
                ItemEntity entity = new ItemEntity(ItemStack.of(material));
                entity.setInstance(event.getInstance(), event.getBlockPosition().add(0.5, 0.5, 0.5));
            }
        });

        globalEventHandler.addListener(PlayerChatEvent.class, event -> {
            TextColor uiTriggerColor = TextColor.color(254, 254, 254);
            String minimapIcon = "\uE001";

            event.getPlayer().sendActionBar(Component.text(minimapIcon).color(uiTriggerColor));
        });

        MotdHandler.enableMotd(globalEventHandler);
        minecraftServer.start("0.0.0.0", 25565);
    }
}