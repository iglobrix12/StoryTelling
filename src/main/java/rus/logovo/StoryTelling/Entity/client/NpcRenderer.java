package rus.logovo.StoryTelling.Entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import rus.logovo.StoryTelling.Entity.custom.NpcEntity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class NpcRenderer extends GeoEntityRenderer<NpcEntity> {
    public NpcRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new NpcModel());
        this.shadowRadius = 0.3f;
    }

    @Override
    public RenderType getRenderType(NpcEntity animatable, float partialTicks, PoseStack stack,
                                    @Nullable MultiBufferSource renderTypeBuffer,
                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation) {
        stack.scale(1.0f, 1.0f, 1.0f);
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
    }
}