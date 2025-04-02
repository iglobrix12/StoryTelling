package rus.logovo.StoryTelling;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rus.logovo.StoryTelling.Dialog.Dialog;
import rus.logovo.StoryTelling.Dialog.Rest;
import rus.logovo.StoryTelling.Function.StoryFunction;

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
            if (event.getPlayer().level.isClientSide) return;
            Dialog dialog = new Dialog("Привет!", new Rest[]{
                    new Rest("Да", () -> {
                        System.out.println("выбрал Да");
                    }),
                    new Rest("Нет", () -> {
                        System.out.println("выбрал Нет");
                    })
            });

            dialog.show();
        }
    }
}