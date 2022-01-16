package com.sweepyspud.shield_thorns.mixin;

import com.sweepyspud.shield_thorns.config.ShieldThornsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.EnchantmentHelper;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity{

    ShieldThornsConfig config = AutoConfig.getConfigHolder(ShieldThornsConfig.class).getConfig();

    //Obligatory placeholder constructor to make Java happy
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world){
        super(entityType, world);
    }

    //Inject into the damage detection method of players
    @Inject(at = @At(value = "HEAD"), method = "damage")
    private void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        LivingEntity attacker = (LivingEntity) source.getAttacker();
        ItemStack shield = this.getMainHandStack();

        //Check if shield/entity source are valid and the damage has been blocked by the shield
        if (shield != null && attacker != null && this.blockedByShield(source) && amount != 0.0f && config.enableShieldBlockDamage) {
            //Credits to supersaiyansubtlety#0079 in the Quilt discord for helping me improve shield hand detection
            final EquipmentSlot shieldHand;
            if (shield.getItem() instanceof ShieldItem) {
                shieldHand = EquipmentSlot.MAINHAND;
            } else {
                shieldHand = EquipmentSlot.OFFHAND;
                shield = this.getOffHandStack();
            }

            //Get level of thorns on shield
            int level = EnchantmentHelper.getLevel(Enchantments.THORNS, shield);
            //Use vanilla RNG to decide if thorns damage should be applied or not
            if (ThornsEnchantment.shouldDamageAttacker(level, random)) {
                //Deal damage to attacker
                attacker.damage(DamageSource.thorns(this), ThornsEnchantment.getDamageAmount(level, random));
                //Deal damage to shield durability
                shield.damage(2, this, entity -> entity.sendEquipmentBreakStatus(shieldHand));
            }

        }
    }
}
