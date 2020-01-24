package tfar.examplemod.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
  @Shadow public abstract boolean isPotionActive(Effect potionIn);

  @Inject(method = "getVisibilityMultiplier",at = @At("RETURN"),cancellable = true)
  private void modifyVisibility(@Nullable Entity looking, CallbackInfoReturnable<Double> callback){
    if (blind(looking)){
      double modifier = callback.getReturnValueD();
      callback.setReturnValue(modifier * .1);
    }
  }

  private boolean blind(@Nullable Entity looking){
    return ((Entity) (Object) this).isInvisible() || this.isPotionActive(Effects.BLINDNESS);
  }
}
