package rus.logovo.StoryTelling.Overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rus.logovo.StoryTelling.StoryTelling;

import java.util.LinkedList;
import java.util.Queue;

@Mod.EventBusSubscriber(modid = StoryTelling.MODID)
public class ChatOverlay {
    private static final Queue<ChatMessage> messageQueue = new LinkedList<>();
    private static ChatMessage currentMessage = null;
    private static int displayTicks = 0;
    private static final int DISPLAY_TIME = 100;
    private static final int AVATAR_SIZE = 32;
    private static final ResourceLocation BACKGROUND_TEXTURE =
            new ResourceLocation(StoryTelling.MODID, "textures/uu/uu.png");

    public static void addChatMessage(String avatar, String name, ChatFormatting chatFormatting, String text) {
        if (avatar.equals("default")) {
            avatar = "storytelling:textures/uu/uu.png";
        }

        MutableComponent formattedName = Component.literal("[" + name + "]: ")
                .withStyle(style -> style.withColor(chatFormatting.getColor()));

        MutableComponent messageText = Component.literal(text)
                .withStyle(style -> style.withColor(0xFFFFFF));

        Component fullMessage = formattedName.append(messageText);

        messageQueue.add(new ChatMessage(
                new ResourceLocation(avatar),
                fullMessage
        ));
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (currentMessage == null && !messageQueue.isEmpty()) {
                currentMessage = messageQueue.poll();
                displayTicks = DISPLAY_TIME;
            }

            if (currentMessage != null && displayTicks-- <= 0) {
                currentMessage = null;
            }
        }
    }

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (currentMessage == null || displayTicks <= 0) return;

        PoseStack poseStack = event.getPoseStack();
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        int textWidth = mc.font.width(currentMessage.text);
        int boxWidth = textWidth + AVATAR_SIZE + 40;
        int boxHeight = 40;
        int startX = (screenWidth - boxWidth) / 2;
        int startY = screenHeight - 100;

        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        Gui.blit(poseStack, startX, startY, 0, 0, boxWidth, boxHeight, boxWidth, boxHeight);

        if (currentMessage.avatar != null) {
            RenderSystem.setShaderTexture(0, currentMessage.avatar);
            Gui.blit(poseStack,
                    startX + 10,
                    startY + 4,
                    0, 0,
                    AVATAR_SIZE, AVATAR_SIZE,
                    AVATAR_SIZE, AVATAR_SIZE
            );
        }

        mc.font.drawShadow(
                poseStack,
                currentMessage.text,
                startX + AVATAR_SIZE + 20,
                startY + 12,
                0xFFFFFF
        );

        RenderSystem.disableBlend();
    }

    private static class ChatMessage {
        final ResourceLocation avatar;
        final Component text;

        ChatMessage(ResourceLocation avatar, Component text) {
            this.avatar = avatar;
            this.text = text;
        }
    }
}