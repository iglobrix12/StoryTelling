package rus.logovo.StoryTelling.Hero;

import com.mojang.math.Vector3d;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import rus.logovo.StoryTelling.Function.StoryFunction;

import java.util.Objects;

public class Hero {
    private final PathfinderMob entity;
    private String name;
    private static final String DEFAULT_NAME = "Noname";

    public Hero(PathfinderMob entity, BlockPos pos) {
        this.entity = Objects.requireNonNull(entity, "cannot null");
        this.name = DEFAULT_NAME;
        entity.setPos(
                Objects.requireNonNull(pos, "Position cannot null").getX(),
                pos.getY(),
                pos.getZ()
        );

        if (!entity.level.isClientSide) {
            entity.level.addFreshEntity(entity);
        }
    }

    public void stopMovement() {
        entity.goalSelector.getAvailableGoals().stream()
                .map(WrappedGoal::getGoal)
                .filter(goal -> goal instanceof MovePlayerEntity)
                .forEach(entity.goalSelector::removeGoal);

        entity.getNavigation().stop();
    }

    public void moveTo(Vector3d position, float speed,int dis) {
        Objects.requireNonNull(position, "Position vector null");
        entity.goalSelector.addGoal(1,
                new MovePlayerEntity(
                        entity,
                        position.x,
                        position.y,
                        position.z,
                        speed,
                        dis
                )
        );
    }
    public void moveTo(Vector3d position, float speed) {
        Objects.requireNonNull(position, "Position vector null");
        entity.goalSelector.addGoal(1,
                new MovePlayerEntity(
                        entity,
                        position.x,
                        position.y,
                        position.z,
                        speed
                )
        );
    }

    public void setRotation(float yaw) {
        entity.setYRot(yaw);
        entity.yRotO = yaw;
        entity.setYHeadRot(yaw);
    }

    public void sendMessage(String text) {
        StoryFunction.send(name, Objects.requireNonNull(text, "Message be null"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNullElse(name, DEFAULT_NAME);
    }

    public PathfinderMob getEntity() {
        return entity;
    }
}