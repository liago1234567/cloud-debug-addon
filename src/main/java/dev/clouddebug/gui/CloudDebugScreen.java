package dev.clouddebug.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import dev.clouddebug.modules.CloudDebug;

public class CloudDebugScreen extends Screen {
    private Screen previousScreen;
    private String selectedCategory = "Player";

    public CloudDebugScreen() {
        super(Text.literal("Cloud Debug Menu"));
        this.previousScreen = MinecraftClient.getInstance().currentScreen;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        // Background
        fill(matrices, 0, 0, this.width, this.height, 0xFF1a1a1a);

        // Title
        drawCenteredString(matrices, this.textRenderer, "§9☁ Cloud Debug §9☁", this.width / 2, 15, 0xFFFFFF);
        drawString(matrices, this.textRenderer, "§7by liago", this.width / 2 - 30, 30, 0x888888);

        // Category buttons
        int categoryY = 50;
        int categoryButtonWidth = 80;
        int categoryButtonHeight = 25;
        int categorySpacing = 5;

        String[] categories = {"Player", "Movement", "Render", "World", "Misc", "Cloud"};
        int categoryX = 20;

        for (String category : categories) {
            boolean isSelected = category.equals(selectedCategory);
            int bgColor = isSelected ? 0xFF9933FF : 0xFF333333;
            
            fill(matrices, categoryX, categoryY, categoryX + categoryButtonWidth, categoryY + categoryButtonHeight, bgColor);
            drawCenteredString(matrices, this.textRenderer, category, categoryX + categoryButtonWidth / 2, categoryY + 8, 0xFFFFFF);
            
            categoryX += categoryButtonWidth + categorySpacing;
        }

        // Content area
        int contentY = 85;
        fill(matrices, 20, contentY, this.width - 20, this.height - 20, 0xFF222222);
        
        drawCategoryContent(matrices, selectedCategory, contentY);

        // Back button
        fill(matrices, 20, this.height - 30, 100, this.height - 10, 0xFF555555);
        drawCenteredString(matrices, this.textRenderer, "§cBack", 60, this.height - 25, 0xFFFFFF);

        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) return super.mouseClicked(mouseX, mouseY, button);

        // Category buttons
        String[] categories = {"Player", "Movement", "Render", "World", "Misc", "Cloud"};
        int categoryY = 50;
        int categoryButtonWidth = 80;
        int categoryButtonHeight = 25;
        int categorySpacing = 5;
        int categoryX = 20;

        for (String category : categories) {
            if (mouseX >= categoryX && mouseX < categoryX + categoryButtonWidth &&
                mouseY >= categoryY && mouseY < categoryY + categoryButtonHeight) {
                selectedCategory = category;
                return true;
            }
            categoryX += categoryButtonWidth + categorySpacing;
        }

        // Back button
        if (mouseX >= 20 && mouseX < 100 && mouseY >= this.height - 30 && mouseY < this.height - 10) {
            this.close();
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void drawCategoryContent(MatrixStack matrices, String category, int startY) {
        int contentX = 30;
        int contentStartY = startY + 15;
        int lineHeight = 20;
        int currentY = contentStartY;

        switch (category) {
            case "Player":
                drawString(matrices, this.textRenderer, "§b▪ Player Info: §r" + (CloudDebug.INSTANCE.playerInfo.get() ? "§aON" : "§cOFF"), contentX, currentY, 0xFFFFFF);
                currentY += lineHeight;
                drawString(matrices, this.textRenderer, "§b▪ Player List: §r" + (CloudDebug.INSTANCE.playerList.get() ? "§aON" : "§cOFF"), contentX, currentY, 0xFFFFFF);
                break;

            case "Movement":
                drawString(matrices, this.textRenderer, "§b▪ Freecam: §r" + (CloudDebug.INSTANCE.freecamEnabled.get() ? "§aON" : "§cOFF"), contentX, currentY, 0xFFFFFF);
                currentY += lineHeight;
                drawString(matrices, this.textRenderer, "§b▪ Speed: §r" + CloudDebug.INSTANCE.freecamSpeed.get(), contentX, currentY, 0xFFFFFF);
                currentY += lineHeight;
                drawString(matrices, this.textRenderer, "§b▪ Normal Walk: §r" + (CloudDebug.INSTANCE.normalWalkWhileFreecam.get() ? "§aON" : "§cOFF"), contentX, currentY, 0xFFFFFF);
                break;

            case "Render":
                drawString(matrices, this.textRenderer, "§b▪ SUS Chunk Finder: §r" + (CloudDebug.INSTANCE.susChunkFinder.get() ? "§aON" : "§cOFF"), contentX, currentY, 0xFFFFFF);
                currentY += lineHeight;
                drawString(matrices, this.textRenderer, "§b▪ Distance: §r" + CloudDebug.INSTANCE.chunkFinderDistance.get(), contentX, currentY, 0xFFFFFF);
                break;

            case "World":
                drawString(matrices, this.textRenderer, "§b▪ Block Notifier: §r" + (CloudDebug.INSTANCE.blockNotifier.get() ? "§aON" : "§cOFF"), contentX, currentY, 0xFFFFFF);
                currentY += lineHeight;
                drawString(matrices, this.textRenderer, "§7(Spawner placement only)", contentX, currentY, 0x888888);
                break;

            case "Cloud":
                drawString(matrices, this.textRenderer, "§6Author: §rliago", contentX, currentY, 0xFFFFFF);
                currentY += lineHeight;
                drawString(matrices, this.textRenderer, "§6Version: §r0.1.0", contentX, currentY, 0xFFFFFF);
                break;
        }
    }

    @Override
    public void close() {
        this.client.setScreen(this.previousScreen);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return true;
    }
}
