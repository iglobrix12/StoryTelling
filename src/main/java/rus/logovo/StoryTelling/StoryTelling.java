package rus.logovo.StoryTelling;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import rus.logovo.StoryTelling.Dialog.Dialog;
import rus.logovo.StoryTelling.Dialog.Rest;
import rus.logovo.StoryTelling.Entity.ModEntityTypes;
import rus.logovo.StoryTelling.Entity.client.NpcRenderer;
import rus.logovo.StoryTelling.Entity.custom.NpcEntity;
import software.bernie.geckolib3.GeckoLib;

@Mod(StoryTelling.MODID)
public class StoryTelling {
    public static final String MODID = "storytelling";
    public static ServerPlayer serverPlayer;
    public StoryTelling() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        ModEntityTypes.register(modEventBus);
        GeckoLib.initialize();
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
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntityTypes.NPC.get(), NpcRenderer::new);
        }
    }
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.NPC.get(), NpcEntity.setAttributes());
        }
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
            dialog.show();
        }
    }
}