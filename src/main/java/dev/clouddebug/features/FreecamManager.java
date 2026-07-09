package dev.clouddebug.features;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;
import dev.clouddebug.modules.CloudDebug;

public class FreecamManager {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private static Vec3d freecamPos = Vec3d.ZERO;

    public static void updateFreecam() {
        if (!CloudDebug.INSTANCE.freecamEnabled.get()) return;
        if (mc.player == null) return;

        float speed = CloudDebug.INSTANCE.freecamSpeed.get() * 0.05f;

        if (mc.options.forwardKey.isPressed()) {
            freecamPos = freecamPos.add(getForwardDirection().multiply(speed));
        }
        if (mc.options.backKey.isPressed()) {
            freecamPos = freecamPos.add(getForwardDirection().multiply(-speed));
        }
        if (mc.options.leftKey.isPressed()) {
            freecamPos = freecamPos.add(getRightDirection().multiply(-speed));
        }
        if (mc.options.rightKey.isPressed()) {
            freecamPos = freecamPos.add(getRightDirection().multiply(speed));
        }
        if (mc.options.jumpKey.isPressed()) {
            freecamPos = new Vec3d(freecamPos.x, freecamPos.y + speed, freecamPos.z);
        }
        if (mc.options.sneakKey.isPressed()) {
            freecamPos = new Vec3d(freecamPos.x, freecamPos.y - speed, freecamPos.z);
        }
    }

    private static Vec3d getForwardDirection() {
        float yaw = mc.player.getYaw();
        float pitch = 0;
        float xz = (float) Math.cos(Math.toRadians(pitch));
        float x = -(float) Math.sin(Math.toRadians(yaw)) * xz;
        float y = (float) Math.sin(Math.toRadians(pitch));
        float z = (float) Math.cos(Math.toRadians(yaw)) * xz;
        return new Vec3d(x, y, z).normalize();
    }

    private static Vec3d getRightDirection() {
        float yaw = mc.player.getYaw();
        float x = (float) Math.cos(Math.toRadians(yaw));
        float z = (float) Math.sin(Math.toRadians(yaw));
        return new Vec3d(x, 0, z).normalize();
    }
}
