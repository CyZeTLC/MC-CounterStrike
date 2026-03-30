package de.cyzetlc.mccs.listener;

import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;

public class PlayerConfigurationListener implements EventListener<AsyncPlayerConfigurationEvent> {
    @Override
    public Class<AsyncPlayerConfigurationEvent> eventType() {
        return AsyncPlayerConfigurationEvent.class;
    }

    @Override
    public Result run(AsyncPlayerConfigurationEvent event) {

        return Result.SUCCESS;
    }
}
