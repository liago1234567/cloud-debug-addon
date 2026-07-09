package dev.clouddebug.features;

import net.minecraft.block.SpawnerBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.text.Text;
import dev.clouddebug.modules.CloudDebug;

public class BlockNotifier {
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    public static void onBlockPlaced(BlockPos pos) {
        if (!CloudDebug.INSTANCE.blockNotifier.get()) return;
        if (mc.world == null) return;

        if (mc.world.getBlockState(pos).getBlock() instanceof SpawnerBlock) {
            if (mc.player != null) {
                String message = String.format("§6[CloudDebug] §cSpawner placed at: §f%d, %d, %d", 
                    pos.getX(), pos.getY(), pos.getZ());
                mc.player.sendMessage(Text.literal(message), false);
            }
        }
    }
}
