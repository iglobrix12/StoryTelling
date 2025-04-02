package rus.logovo.StoryTelling.Dialog;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;

public class DialogGUI extends Screen {
    private final String question;
    private final Rest[] options;
    private final Entity entity;
    private final EntityRenderDispatcher entityRenderer;
    private float entityRotationYaw = 0;
    private float entityRotationPitch = 0;

    public DialogGUI(String question, Rest[] options, Entity entity) {
        super(Component.literal("Dialog"));
        this.question = question;
        this.options = options;
        this.entity = entity;
        this.entityRenderer = Minecraft.getInstance().getEntityRenderDispatcher();
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
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(poseStack);
        drawCenteredString(poseStack, this.font, question, this.width / 2, this.height / 2 - 40, 0xFFFFFF);
        renderEntity(poseStack, mouseX, mouseY);
        super.render(poseStack, mouseX, mouseY, partialTick);
    }

    private void renderEntity(PoseStack poseStack, int mouseX, int mouseY) {
        int entityX = this.width / 2;
        int entityY = this.height / 2 - 70;
        float scale = 50.0F; // Увел

        double deltaX = mouseX - entityX;
        double deltaY = mouseY - entityY;

        float targetYaw = (float) Math.toDegrees(Math.atan2(deltaY, deltaX)) - 90.0F;
        float targetPitch = (float) Math.toDegrees(Math.atan2(deltaY, Math.sqrt(deltaX * deltaX + deltaY * deltaY))) * 0.5F;

        targetYaw = Math.max(-90.0F, Math.min(90.0F, targetYaw));

        entityRotationYaw += (targetYaw - entityRotationYaw) * 0.1F;
        entityRotationPitch += (targetPitch - entityRotationPitch) * 0.1F;

        poseStack.pushPose();
        poseStack.translate(entityX, entityY, 100.0);
        poseStack.scale(scale, scale, scale);
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(entityRotationYaw));
        poseStack.mulPose(Vector3f.XP.rotationDegrees(entityRotationPitch));

        entityRenderer.render(
                entity,
                0.0, 0.0, 0.0,
                0.0F,
                1.0F,
                poseStack,
                this.minecraft.renderBuffers().bufferSource(),
                15728880
        );
        poseStack.popPose();
    }
}