package dev.timefall.mcdw.api.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;

public class ProjectileEffectHelper {

    public static void mcdw$spawnExtraArrows(LivingEntity owner, LivingEntity makeArrowFromMe, int numArrowsLimit, int distance, double bonusShotDamageMultiplier) {
        List<LivingEntity> nearbyEntities = mcdw$getSecondaryTargets(makeArrowFromMe, distance);
        for (int i = 0; i < Math.min(numArrowsLimit, nearbyEntities.size()); i++) {
            PersistentProjectileEntity arrowEntity = mcdw$createProjectileEntityTowards(makeArrowFromMe, nearbyEntities.get(i));
            arrowEntity.setDamage(arrowEntity.getDamage() * bonusShotDamageMultiplier);
            arrowEntity.setOwner(owner);
            makeArrowFromMe.getWorld().spawnEntity(arrowEntity);
        }
    }

    public static PersistentProjectileEntity mcdw$createAbstractArrow(LivingEntity attacker) {
        return ((ArrowItem) Items.ARROW).createArrow(attacker.getEntityWorld(), new ItemStack(Items.ARROW), attacker);
    }

    public static void mcdw$fireChainReactionProjectileFromTarget(ProjectileParameters params) {
        World world = params.getWorld();
        LivingEntity attacker = params.getAttacker();
        LivingEntity target = params.getTarget();
        float v1 = params.getV1();
        float v2 = params.getV2();

        if (!world.isClient) {
            for (int i = 0; i < 4; i++) {
                PersistentProjectileEntity projectile = mcdw$createAbstractArrow(attacker);
                if (attacker instanceof PlayerEntity) {
                    projectile.setCritical(true);
                }

                projectile.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
                projectile.setShotFromCrossbow(true);
                projectile.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                Vec3d upVector = target.getOppositeRotationVector(1.0F);
                Quaternionf quaternionf = new Quaternionf(upVector.getX(), upVector.getY(), upVector.getZ(), -135.0F + (90.0f * i));
                Vector3f vector3f = target.getRotationVec(1.0F).toVector3f();
                vector3f.rotate(quaternionf);
                projectile.setVelocity(vector3f.x(), vector3f.y(), vector3f.z(), v1, v2);
                projectile.setOwner(target);
                world.spawnEntity(projectile);
            }
        }
    }

    public static List<LivingEntity> mcdw$getSecondaryTargets(LivingEntity source, double radius) {
        // Implementation details
    }

    public static PersistentProjectileEntity mcdw$createProjectileEntityTowards(LivingEntity source, LivingEntity target) {
        PersistentProjectileEntity projectile = mcdw$createAbstractArrow(source);
        double towardsX = target.getX() - source.getX();
        double towardsZ = target.getZ() - source.getZ();
        double euclideanDist = MathHelper.hypot(towardsX, towardsZ);
        double towardsY = target.getBodyY(0.3333333333333333D) - projectile.getY() + euclideanDist * 0.2d;
        mcdw$setProjectileTowards(projectile, towardsX, towardsY, towardsZ);
        projectile.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        return projectile;
    }

    public static void mcdw$setProjectileTowards(ProjectileEntity projectileEntity, double x, double y, double z) {
        Vec3d vec3d = new Vec3d(x, y, z).normalize();
        projectileEntity.setVelocity(vec3d);
        float f = MathHelper.sqrt((float) projectileEntity.squaredDistanceTo(vec3d));
        projectileEntity.setYaw((float) (MathHelper.atan2(vec3d.x, vec3d.z) * (180d / Math.PI)));
        projectileEntity.setPitch((float) (MathHelper.atan2(vec3d.y, f) * (180d / Math.PI)));
        projectileEntity.prevYaw = projectileEntity.getYaw();
        projectileEntity.prevPitch = projectileEntity.getPitch();
    }
}
