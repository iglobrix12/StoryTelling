package rus.logovo.StoryTelling.Dialog;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.Button;

public class DialogGUI extends Screen {
    private final String question;
    private final Rest[] options;

    protected DialogGUI(String question, Rest[] options) {
        super(Component.literal("Dialog"));
        this.question = question;
        this.options = options;
    }

    @Override
    protected void init() {
        super.init();
        int buttonY = this.height / 2;
        for (int i = 0; i < options.length; i++) {
            int width = 200;
            int height = 20;
            int x = this.width / 2 - width / 2;
            int y = buttonY + (i * 24);

            int finalI = i;
            this.addRenderableWidget(new Button(
                    x, y,
                    width, height,
                    Component.literal(options[i].getText()),
                    btn -> {
                        options[finalI].getAction().run();
                        this.minecraft.setScreen(null);
                    }
            ));
        }
    }

    @Override
    public void render(com.mojang.blaze3d.vertex.PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        drawCenteredString(poseStack, this.font, question, this.width / 2, this.height / 2 - 40, 0xFFFFFF);
        super.render(poseStack, mouseX, mouseY, partialTick);
    }
}