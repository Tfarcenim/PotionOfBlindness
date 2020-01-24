package tfar.examplemod;

import net.minecraft.entity.MobEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(PotionOfBlindness.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotionOfBlindness {
    // Directly reference a log4j logger.

    public static final String MODID = "potionofblindness";

    @SubscribeEvent
    public static void potion(RegistryEvent.Register<Potion> e){
        e.getRegistry().register(new Potion("blindness",new EffectInstance(Effects.BLINDNESS,1800)).setRegistryName("blindness"));
        e.getRegistry().register(new Potion("blindness",new EffectInstance(Effects.BLINDNESS,4800)).setRegistryName("long_blindness"));
    }
    @Mod.EventBusSubscriber
    public static class a{
        @SubscribeEvent
        public static void target(LivingSetAttackTargetEvent e){
            if (e.getEntityLiving().isPotionActive(Effects.BLINDNESS) && e.getEntityLiving() instanceof MobEntity){
                //ObfuscationReflectionHelper.setPrivateValue(MobEntity.class,(MobEntity)e.getEntityLiving(),null,"field_188693_f");
            }
        }
    }
}
