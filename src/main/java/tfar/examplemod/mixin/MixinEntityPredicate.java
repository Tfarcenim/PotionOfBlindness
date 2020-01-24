package tfar.examplemod.mixin;

import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(EntityPredicate.class)
public class MixinEntityPredicate {
  @Inject(method = "canTarget",at = @At("HEAD"),cancellable = true)
  private void canTarget(@Nullable LivingEntity attacker, LivingEntity target, CallbackInfoReturnable<Boolean> callback){
    if (blind(attacker,target)){
      //callback.setReturnValue(false);
      //callback.cancel();
    }
  }
  private boolean blind(@Nullable LivingEntity attacker,LivingEntity target){
    return attacker != null && attacker.isPotionActive(Effects.BLINDNESS);
  }
}
