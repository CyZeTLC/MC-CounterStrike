package de.cyzetlc.mccs.listener;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.InstanceContainer;

public class PlayerConfigurationListener implements EventListener<AsyncPlayerConfigurationEvent> {
    private final InstanceContainer instanceContainer;

    public PlayerConfigurationListener(InstanceContainer instanceContainer) {
        this.instanceContainer = instanceContainer;
    }

    @Override
    public Class<AsyncPlayerConfigurationEvent> eventType() {
        return AsyncPlayerConfigurationEvent.class;
    }

    @Override
    public Result run(AsyncPlayerConfigurationEvent event) {
        Player player = event.getPlayer();
        event.setSpawningInstance(this.instanceContainer);
        player.setRespawnPoint(new Pos(0, 42, 0));
        player.setGameMode(GameMode.CREATIVE);
        return Result.SUCCESS;
    }
}
