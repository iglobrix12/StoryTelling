package rus.logovo.StoryTelling.Hero;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class MovePlayerEntity extends Goal {
    private final PathfinderMob mob;
    private final double targetX;
    private final double targetY;
    private final double targetZ;
    private final double speed;
    private final double stopDistanceSq;

    public MovePlayerEntity(PathfinderMob mob, double x, double y, double z, double speed) {
        this(mob, x, y, z, speed, 1.0);
    }

    public MovePlayerEntity(PathfinderMob mob, double x, double y, double z, double speed, double stopDistance) {
        this.mob = mob;
        this.targetX = x;
        this.targetY = y;
        this.targetZ = z;
        this.speed = speed;
        this.stopDistanceSq = stopDistance * stopDistance;
    }

    @Override
    public boolean canUse() {
        return !hasReachedTarget();
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.targetX, this.targetY, this.targetZ, this.speed);
    }

    @Override
    public boolean canContinueToUse() {
        return !this.mob.getNavigation().isDone() && !hasReachedTarget();
    }

    @Override
    public void stop() {
        this.mob.getNavigation().stop();
    }

    private boolean hasReachedTarget() {
        double dx = targetX - mob.getX();
        double dy = targetY - mob.getY();
        double dz = targetZ - mob.getZ();

        return dx*dx + dy*dy + dz*dz <= stopDistanceSq;
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }
}