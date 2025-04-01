package rus.logovo.StoryTelling;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(StoryTelling.MODID)
public class StoryTelling {
    public static final String MODID = "storytelling";
    public StoryTelling() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
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
}