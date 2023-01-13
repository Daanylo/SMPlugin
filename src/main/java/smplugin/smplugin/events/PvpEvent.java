package smplugin.smplugin.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class PvpEvent implements Listener {

    @EventHandler
    public void onPlayerDamage (EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player & event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBowShot (ProjectileHitEvent event) {
        Location impactPoint = event.getEntity().getLocation();
        event.getEntity().getWorld().strikeLightning(impactPoint);
        event.getEntity().getWorld().createExplosion(impactPoint, 3);
        event.getEntity().remove();
    }
}
