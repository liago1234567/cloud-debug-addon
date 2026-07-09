package dev.clouddebug.features;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.client.util.math.MatrixStack;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.meteorclient.renderer.Renderer3D;
import meteordevelopment.meteorclient.renderer.ShapeMode;
import meteordevelopment.meteorclient.utils.render.color.Color;
import dev.clouddebug.modules.CloudDebug;

public class SusChunkFinder {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void renderSusChunks(Render3DEvent event) {
        if (!CloudDebug.INSTANCE.susChunkFinder.get()) return;
        if (mc.player == null || mc.world == null) return;

        int distance = CloudDebug.INSTANCE.chunkFinderDistance.get();
        int alpha = CloudDebug.INSTANCE.chunkFinderAlpha.get();
        ChunkPos playerChunkPos = mc.player.getChunkPos();
        int renderDistance = (distance / 16) + 1;

        for (int x = -renderDistance; x <= renderDistance; x++) {
            for (int z = -renderDistance; z <= renderDistance; z++) {
                ChunkPos chunkPos = new ChunkPos(playerChunkPos.x + x, playerChunkPos.z + z);
                renderChunk(event.matrices, chunkPos, alpha);
            }
        }
    }

    private static void renderChunk(MatrixStack matrices, ChunkPos chunkPos, int alpha) {
        double x = chunkPos.getStartX();
        double z = chunkPos.getStartZ();
        double y = mc.player.getY();
        double x1 = x, y1 = y, z1 = z;
        double x2 = x + 16, y2 = y + 256, z2 = z + 16;

        Color redColor = new Color(255, 0, 0, alpha);
        Renderer3D.drawBox(matrices, x1, y1, z1, x2, y2, z2, redColor, null, ShapeMode.Both, 0);
    }
}
