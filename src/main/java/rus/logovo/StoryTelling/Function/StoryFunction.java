package rus.logovo.StoryTelling.Function;

import net.minecraft.network.chat.Component;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

import static rus.logovo.StoryTelling.StoryTelling.serverPlayer;

public class StoryFunction {
    private static class TimedScene {
        final Runnable action;
        final int delayTicks;

        TimedScene(Runnable action, int delaySeconds) {
            this.action = action;
            this.delayTicks = delaySeconds * 20;
        }
    }

    private final List<TimedScene> scenes = new ArrayList<>();
    private int currentTick = 0;
    private int currentSceneIndex = 0;
    private boolean isActive = false;

    public StoryFunction() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void addScene(Runnable action, int delaySeconds) {
        scenes.add(new TimedScene(action, delaySeconds));
        if (!isActive && !scenes.isEmpty()) {
            isActive = true;
        }
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (!isActive || event.phase != TickEvent.Phase.END) return;

        if (currentSceneIndex >= scenes.size()) {
            isActive = false;
            return;
        }

        TimedScene currentScene = scenes.get(currentSceneIndex);
        currentTick++;

        if (currentTick >= currentScene.delayTicks) {
            currentScene.action.run();
            currentTick = 0;
            currentSceneIndex++;
        }
    }
    public static void send(String name,String text) {
        serverPlayer.sendSystemMessage(Component.literal("[" + name + "] " + text));
    }
}