package rus.logovo.StoryTelling;

import com.mojang.math.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.checkerframework.checker.units.qual.C;
import rus.logovo.StoryTelling.Dialog.Dialog;
import rus.logovo.StoryTelling.Dialog.Rest;
import rus.logovo.StoryTelling.Function.StoryFunction;
import rus.logovo.StoryTelling.Hero.Hero;
import rus.logovo.StoryTelling.Overlay.black.BlockOverlay;

import java.io.Serializable;

@Mod(StoryTelling.MODID)
public class StoryTelling {
    public static final String MODID = "storytelling";
    public static ServerPlayer serverPlayer;
    public StoryTelling() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }
    public void onClientSetup(final FMLClientSetupEvent event) {
        System.err.println(" #####                            #######                                         ####### #######    #    #     #    ### #     #       # #######  #####  ####### \n" +
                "#     # #####  ####  #####  #   #    #    ###### #      #      # #    #  ####        #    #         # #   ##   ##     #  ##    #       # #       #     #    #    \n" +
                "#         #   #    # #    #  # #     #    #      #      #      # ##   # #    #       #    #        #   #  # # # #     #  # #   #       # #       #          #    \n" +
                " #####    #   #    # #    #   #      #    #####  #      #      # # #  # #            #    #####   #     # #  #  #     #  #  #  #       # #####   #          #    \n" +
                "      #   #   #    # #####    #      #    #      #      #      # #  # # #  ###       #    #       ####### #     #     #  #   # # #     # #       #          #    \n" +
                "#     #   #   #    # #   #    #      #    #      #      #      # #   ## #    #       #    #       #     # #     #     #  #    ## #     # #       #     #    #    \n" +
                " #####    #    ####  #    #   #      #    ###### ###### ###### # #    #  ####        #    ####### #     # #     #    ### #     #  #####  #######  #####     #    \n" +
                "                                                                                                                                                                 \n" +
                "#     # ####### #       #       ####### ### ### ### ### \n" +
                "#     # #       #       #       #     # ### ### ### ### \n" +
                "#     # #       #       #       #     # ### ### ### ### \n" +
                "####### #####   #       #       #     #  #   #   #   #  \n" +
                "#     # #       #       #       #     #                 \n" +
                "#     # #       #       #       #     # ### ### ### ### \n" +
                "#     # ####### ####### ####### ####### ### ### ### ### \n" +
                "                                                        \n!");
    }
    @Mod.EventBusSubscriber
    public class DialogEventHandler {

        @SubscribeEvent
        public static void onPlayerJoin(BlockEvent.BreakEvent event) {
            serverPlayer = (ServerPlayer) event.getPlayer();
            Dialog dialog = new Dialog("Hello",new Rest[] {
                    new Rest("Yes",() -> {
                    })
            });
            Zombie cow = new Zombie(EntityType.ZOMBIE,serverPlayer.level);
            cow.moveTo(new Vec3(0,-60,0));
            dialog.show(cow);
        }
    }
}