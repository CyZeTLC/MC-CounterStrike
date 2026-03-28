package de.cyzetlc.mccs.utils.weapons;

import net.minestom.server.entity.Player;

public class ProjectileWeapon extends Weapon {
    public ProjectileWeapon(WeaponType type) {
        super(type);
    }

    @Override
    public void shoot(Player player) {
        super.shoot(player);

        // todo: different animation for nades etc.
    }
}
