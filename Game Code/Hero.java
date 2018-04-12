// JFC
import java.awt.*;
import java.awt.event.*;

// GTGE
import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.engine.BaseAudio;
import com.golden.gamedev.object.Sprite;



public class Hero extends Sprite implements CoinCollider, PlatformCollider{

    // Initialize instance variables
    String hero_name;
    int _m_score = 0;
    int _om_score = 0;
    double movement_speed = 0.01;
    double max_speed = 1;
    double accel = 0.002;
    double jump_speed = 1;
    boolean jumping = false;


    // Hero constructor
    public Hero(java.awt.image.BufferedImage bi, int a, int b) {
    	super(bi, a, b);
    }

    // Method that gives the hero a name
    public void set_hero_name(String name) {
    	hero_name = name;
    }

    // Method the retrieves the hero's name
    public String get_hero_name(Hero s1) {
      return hero_name;
    }

    // Method that retrieves Mario's score
    public int get_m_score() {
      return _m_score;
    }

    // Method that retrieves Old Mario's score
    public int get_om_score() {
      return _om_score;
    }

    // Method that set hero's movement speed
    public void set_movement_speed(double new_speed) {
    	movement_speed = new_speed;
    }

    // Method that set the speed to 0 when hiting platforms during jumping
    public void hitSomething(){
      jumping = false;
      if (getVerticalSpeed() < 0.02){
        setY(getOldY());
      }
      setVerticalSpeed(0);
      setHorizontalSpeed(0);
    }

    // Method that update hero's downwards speed with elapsed time
    public void update(long elapsedTime){
      this.updateMovement(elapsedTime);
      this.addVerticalSpeed(elapsedTime,accel,max_speed);
    }

    // Method that set a jumping speed for hero
    public void attemptJump(){
        if (!jumping){
          this.setVerticalSpeed(-jump_speed);
          jumping = true;
        }
    }

    //Method that updated the correct hero's score when one collides with a coin
    public void hitCoin(){
      if (get_hero_name(this).equals("Mario")){
        _m_score++;
        System.out.println(get_hero_name(this) + "'s score is: " + _m_score);
      }
      else if (get_hero_name(this).equals("Old Mario")){
        _om_score++;
        System.out.println(get_hero_name(this) + "'s score is: " + _om_score);
      }
    }
}
