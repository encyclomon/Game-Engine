// JFC
import java.awt.*;
import java.awt.event.*;

// GTGE
import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.engine.BaseAudio;


public class Coin extends AnimatedSprite implements CoinCollider{
    // Set the coin speed to 0, for the coins don't move
    double movement_speed = 0;

    // Coin constructor
    public Coin(java.awt.image.BufferedImage[] bi, int a, int b) {
    	super(bi, a, b);
    }

    // Method that occurs if a hero object hits a coin object
    // Make the coin disappear
    public void hitCoin(){
      this.setActive(false);
    }

}
