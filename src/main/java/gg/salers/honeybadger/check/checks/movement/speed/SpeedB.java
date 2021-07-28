package gg.salers.honeybadger.check.checks.movement.speed;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketEvent;
import gg.salers.honeybadger.check.Check;
import gg.salers.honeybadger.check.CheckData;
import gg.salers.honeybadger.data.PlayerData;
import gg.salers.honeybadger.utils.LocationUtils;
import org.bukkit.Material;

import java.time.LocalDate;

@CheckData(name = "Speed (B)", experimental = true)
public class SpeedB extends Check {

    private double lastDeltaXZ;
    private boolean wasOnGround;

    @Override
    public void onPacket(PacketEvent event, PlayerData playerData) {
        if (event.getPacketType() == PacketType.Play.Client.POSITION || event.getPacketType() == PacketType.Play.Client.POSITION_LOOK) {
            double lastDeltaXZ = this.lastDeltaXZ;
            this.lastDeltaXZ = playerData.getMovementProcessor().getDeltaXZ();
            boolean isOnGround = playerData.getBukkitPlayerFromUUID().isOnGround();
            boolean wasOnGround = this.wasOnGround;
            this.wasOnGround = isOnGround;
        }
    }
}
