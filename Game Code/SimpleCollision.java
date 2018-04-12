import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.engine.BaseAudio;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.collision.CollisionGroup;

public class SimpleCollision extends BasicCollisionGroup {

  public void collided(Sprite s1, Sprite s2) {
    // Cast s1 from Sprite to Hero
    Hero h = (Hero)s1;

    // Cast s2 from Sprite to Enemy
    Platform p = (Platform)s2;


    // Check whether the collision is on the right, left, bottom, or top
    h.hitSomething();
    p.hitSomething();
    }
  }
