package de.cyzetlc.mccs.utils.weapons;

import lombok.Getter;

public enum WeaponType {
    AK47(35, 30, 0.1, WeaponCategory.RIFLE),
    USP_S(12, 12, 0.2, WeaponCategory.PISTOL),
    AWP(115, 10, 1.5, WeaponCategory.SNIPER);

    @Getter
    private final double damage;
    @Getter
    private final int maxAmmo;
    @Getter
    private final double fireRate;
    @Getter
    private final WeaponCategory category;

    WeaponType(double damage, int maxAmmo, double fireRate, WeaponCategory category) {
        this.damage = damage;
        this.maxAmmo = maxAmmo;
        this.fireRate = fireRate;
        this.category = category;
    }
}
