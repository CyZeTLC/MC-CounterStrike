package de.cyzetlc.mccs.utils.weapons;

import lombok.Getter;
import lombok.Setter;
import net.minestom.server.entity.Player;

@Getter @Setter
public abstract class Weapon {
    private final WeaponType type;
    @Setter
    private int ammoLoaded;

    public Weapon(WeaponType type) {
        this.type = type;
        this.ammoLoaded = type.getMaxAmmo();
    }

    public void shoot(Player player) {
        if (this.ammoLoaded > 0) {
            // todo: take damage
            // todo: shoot animation etc.
            this.ammoLoaded--;
        } else {
            // todo: play no ammo sound
        }
    }

    public void reload() {
        this.ammoLoaded = this.type.getMaxAmmo();
    }
}
