package rus.logovo.StoryTelling.Overlay.black;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;

public class BlockOverlay extends Screen {
    private static final float FADE_SPEED = 0.02f;
    private static final int TICKS_PER_SECOND = 20;

    private float alpha = 0f;
    private int tick = 0;
    private final int targetSeconds;
    private FadeState state = FadeState.FADE_IN;

    public BlockOverlay(int seconds) {
        super(Component.literal("transparent_screen"));
        this.targetSeconds = seconds;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        renderBlackground();
        // Не вызываем super.render(), чтобы избежать рендеринга курсора
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public boolean mouseClicked(double p_94695_, double p_94696_, int p_94697_) {
        return false; // Отключаем обработку кликов
    }

    @Override
    public boolean mouseReleased(double p_94686_, double p_94687_, int p_94688_) {
        return false; // Отключаем обработку отпускания кнопки
    }

    @Override
    public boolean mouseDragged(double p_94698_, double p_94699_, int p_94700_, double p_94701_, double p_94702_) {
        return false; // Отключаем обработку перемещения с зажатой кнопкой
    }

    private void renderBlackground() {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();

        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
        RenderSystem.setShaderTexture(0, BACKGROUND_LOCATION);
        RenderSystem.setShaderColor(0f, 0f, 0f, alpha);

        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);
        bufferbuilder.vertex(0.0D, this.height, 0.0D).uv(0.0F, 1.0F).color(0f, 0f, 0f, alpha).endVertex();
        bufferbuilder.vertex(this.width, this.height, 0.0D).uv(1.0F, 1.0F).color(0f, 0f, 0f, alpha).endVertex();
        bufferbuilder.vertex(this.width, 0.0D, 0.0D).uv(1.0F, 0.0F).color(0f, 0f, 0f, alpha).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, 0.0D).uv(0.0F, 0.0F).color(0f, 0f, 0f, alpha).endVertex();

        tesselator.end();
        RenderSystem.disableBlend();
    }

    @Override
    public void tick() {
        switch (state) {
            case FADE_IN:
                alpha = Math.min(1f, alpha + FADE_SPEED);
                if (alpha >= 1f) {
                    state = FadeState.HOLD;
                }
                break;

            case HOLD:
                tick++;
                if (tick >= targetSeconds * TICKS_PER_SECOND) {
                    state = FadeState.FADE_OUT;
                }
                break;

            case FADE_OUT:
                alpha = Math.max(0f, alpha - FADE_SPEED);
                if (alpha <= 0f) {
                    this.minecraft.setScreen(null);
                }
                break;
        }
    }

    private enum FadeState {
        FADE_IN,
        HOLD,
        FADE_OUT
    }
}