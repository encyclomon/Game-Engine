import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;
import com.golden.gamedev.engine.BaseAudio;

public class SimpleCollision2 extends CollisionGroup {

   public void collided(Sprite s1, Sprite s2) {
	   // Cast s1 from Sprite to Hero
	   Hero h = (Hero)s1;

	   // Cast s2 from Sprite to Coin
	   Coin c = (Coin)s2;

	   // Methods called when a Hero collides with a Coin
     h.hitCoin();
	   c.hitCoin();
   }
}
