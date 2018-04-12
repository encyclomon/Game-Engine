// JFC
import java.awt.*;
import java.awt.event.*;

// GTGE
import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.engine.BaseAudio;


public class Platform extends Sprite implements PlatformCollider{

    // Set platform speed to 0, for platforms don't move
    double movement_speed = 0;

    // Platform constructor
    public Platform(java.awt.image.BufferedImage bi, int a, int b) {
    	super(bi, a, b);
    }

    // Empty methods that "occur" when the Hero and Platform collide
    public void hitSomething(){
    }


}
