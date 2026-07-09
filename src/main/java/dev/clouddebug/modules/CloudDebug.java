package dev.clouddebug.modules;

import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.settings.SettingGroup;
import meteordevelopment.meteorclient.settings.BoolSetting;
import meteordevelopment.meteorclient.settings.IntSetting;
import meteordevelopment.meteorclient.events.render.Render3DEvent;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import dev.clouddebug.CloudDebugAddon;
import dev.clouddebug.gui.CloudDebugScreen;

public class CloudDebug extends Module {
    public static CloudDebug INSTANCE;

    // Settings Groups
    private final SettingGroup sgPlayer = settings.createGroup("Player");
    private final SettingGroup sgMovement = settings.createGroup("Movement");
    private final SettingGroup sgRender = settings.createGroup("Render");
    private final SettingGroup sgWorld = settings.createGroup("World");
    private final SettingGroup sgMisc = settings.createGroup("Misc");
    private final SettingGroup sgCloud = settings.createGroup("Cloud");

    // Player Settings
    public final BoolSetting playerInfo = sgPlayer.add(new BoolSetting.Builder()
        .name("player info")
        .description("Shows player information")
        .defaultValue(false)
        .build());

    public final BoolSetting playerList = sgPlayer.add(new BoolSetting.Builder()
        .name("player list")
        .description("Shows nearby players")
        .defaultValue(false)
        .build());

    // Movement Settings - Freecam
    public final BoolSetting freecamEnabled = sgMovement.add(new BoolSetting.Builder()
        .name("freecam")
        .description("Enables freecam mode - fly around while still breaking blocks")
        .defaultValue(false)
        .build());

    public final IntSetting freecamSpeed = sgMovement.add(new IntSetting.Builder()
        .name("freecam speed")
        .description("Speed of freecam movement")
        .defaultValue(5)
        .min(1)
        .max(20)
        .sliderMin(1)
        .sliderMax(20)
        .build());

    public final BoolSetting normalWalkWhileFreecam = sgMovement.add(new BoolSetting.Builder()
        .name("normal walk while freecam")
        .description("Allows normal walking while freecam is active")
        .defaultValue(true)
        .build());

    public final BoolSetting breakBlocksWhileFreecam = sgMovement.add(new BoolSetting.Builder()
        .name("break blocks while freecam")
        .description("Allows breaking blocks while freecam is active")
        .defaultValue(true)
        .build());

    // Render Settings - SUS Chunk Finder
    public final BoolSetting susChunkFinder = sgRender.add(new BoolSetting.Builder()
        .name("sus chunk finder")
        .description("Highlights suspicious chunks in red")
        .defaultValue(false)
        .build());

    public final IntSetting chunkFinderDistance = sgRender.add(new IntSetting.Builder()
        .name("chunk finder distance")
        .description("Distance to search for suspicious chunks")
        .defaultValue(100)
        .min(10)
        .max(500)
        .sliderMin(10)
        .sliderMax(500)
        .build());

    public final IntSetting chunkFinderAlpha = sgRender.add(new IntSetting.Builder()
        .name("chunk finder alpha")
        .description("Transparency of chunk highlighting")
        .defaultValue(100)
        .min(0)
        .max(255)
        .sliderMin(0)
        .sliderMax(255)
        .build());

    // World Settings
    public final BoolSetting antiPacketKick = sgWorld.add(new BoolSetting.Builder()
        .name("anti packet kick")
        .description("Prevents packet kick exploits")
        .defaultValue(false)
        .build());

    public final BoolSetting blockNotifier = sgWorld.add(new BoolSetting.Builder()
        .name("block notifier")
        .description("Notifies when spawners are placed")
        .defaultValue(false)
        .build());

    // Misc Settings
    public final BoolSetting debugInfo = sgMisc.add(new BoolSetting.Builder()
        .name("debug info")
        .description("Shows debug information")
        .defaultValue(false)
        .build());

    // Cloud Settings
    public final BoolSetting enableCloudDebug = sgCloud.add(new BoolSetting.Builder()
        .name("enable cloud debug")
        .description("Master switch for Cloud Debug")
        .defaultValue(true)
        .build());

    public CloudDebug() {
        super(CloudDebugAddon.CATEGORY, "cloud-debug", "Advanced debugging and utility features");
        INSTANCE = this;
    }

    @Override
    public void onActivate() {
        MinecraftClient.getInstance().setScreen(new CloudDebugScreen());
    }

    @EventHandler
    private void onRender3D(Render3DEvent event) {
        if (!isActive()) return;

        if (susChunkFinder.get()) {
            renderSusChunks();
        }

        if (freecamEnabled.get()) {
            updateFreecam();
        }
    }

    private void renderSusChunks() {
        // SUS Chunk Finder rendering
    }

    private void updateFreecam() {
        // Freecam update logic
    }
}
