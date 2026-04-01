package de.cyzetlc.mccs.listener;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.advancements.FrameType;
import net.minestom.server.advancements.Notification;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class PlayerBlockBreakListener implements EventListener<PlayerBlockBreakEvent> {
    @Override
    public Class<PlayerBlockBreakEvent> eventType() {
        return PlayerBlockBreakEvent.class;
    }

    @Override
    public Result run(PlayerBlockBreakEvent event) {
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
        return Result.SUCCESS;
    }
}
