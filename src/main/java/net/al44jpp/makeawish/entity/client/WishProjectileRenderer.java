package net.al44jpp.makeawish.entity.client;

import net.al44jpp.makeawish.MAW;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class WishProjectileRenderer extends ThrownItemRenderer {
    public WishProjectileRenderer(EntityRendererProvider.Context context, float scale, boolean fullBright) {
        super(context, scale, fullBright);
    }
    public WishProjectileRenderer(EntityRendererProvider.Context context) {
        this(context, 1.0F, false);
    }


    @Override
    public ResourceLocation getTextureLocation(Entity entity) {
        return ResourceLocation.fromNamespaceAndPath(MAW.MOD_ID,"textures/entity/wish/wish.png");
    }
}
