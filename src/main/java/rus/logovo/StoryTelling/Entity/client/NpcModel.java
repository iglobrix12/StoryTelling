package rus.logovo.StoryTelling.Entity.client;

import net.minecraft.resources.ResourceLocation;
import rus.logovo.StoryTelling.Entity.custom.NpcEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import static rus.logovo.StoryTelling.StoryTelling.MODID;

public class NpcModel extends AnimatedGeoModel<NpcEntity> {
    @Override
    public ResourceLocation getModelResource(NpcEntity object) {
        return new ResourceLocation(MODID, "geo/npc.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NpcEntity object) {
        return new ResourceLocation(MODID, "textures/entity/npc.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NpcEntity animatable) {
        return new ResourceLocation(MODID, "animations/npc.story.json");
    }
}