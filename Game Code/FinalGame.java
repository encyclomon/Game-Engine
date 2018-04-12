// CP 122 Computer Science I, Final Project
// December 20, 2016
// Darryl Filmore and Jia Kang

// JFC
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.awt.image.BufferedImage;
import java.io.File;


// GTGE
import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.*;
import com.golden.gamedev.engine.BaseAudio;
import com.golden.gamedev.object.collision.BasicCollisionGroup;
import com.golden.gamedev.object.collision.CollisionGroup;
import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.util.ImageUtil;
import com.golden.gamedev.object.font.BitmapFont;

public class FinalGame extends Game {

    // Instance variables
    Hero hero;
    Hero hero1;
    Coin coin1;
    Coin coin2;
    Coin coin3;
    int m_score;
    int om_score;
    BufferedImage[] coinImage;
    ArrayList<Platform> platforms = new ArrayList<Platform>();
    String s = "Score: ";
    ImageBackground background;
    SpriteGroup PLAYER_GROUP, PLATFORM_GROUP, COIN_GROUP;
    SimpleCollision  hitChecker;
    SimpleCollision2 hitChecker2;

    public void initResources() {

        // Initialize a new Hero named Mario
        hero = new Hero(getImage("mario.png"),  100, 300);
        hero.set_hero_name("Mario");

        // Initialize a new Hero named Old Mario
        hero1 = new Hero(getImage("old_mario.png"), 100, 300);
        hero1.set_hero_name("Old Mario");

        // Create an array filled with different positions of the coin picture
        coinImage = new BufferedImage[6];
        coinImage[0] = getImage("Coin1.png");
        coinImage[1] = getImage("Coin2.png");
        coinImage[2] = getImage("Coin3.png");
        coinImage[3] = getImage("Coin4.png");
        coinImage[4] = getImage("Coin5.png");
        coinImage[5] = getImage("Coin6.png");

        // Create a coin, and make it spin
        coin1 = new Coin(coinImage, 0, 0);
        coin1.getAnimationTimer().setDelay(100);
        coin1.setAnimationFrame(0, 5);
        coin1.setAnimate(true);
        coin1.setLoopAnim(true);

        // Create another coin, and make it spin
        coin2 = new Coin(coinImage, 0, 0);
        coin2.getAnimationTimer().setDelay(100);
        coin2.setAnimationFrame(0, 5);
        coin2.setAnimate(true);
        coin2.setLoopAnim(true);

        // Create another coin, and make it spin
        coin3 = new Coin(coinImage, 0, 0);
        coin3.getAnimationTimer().setDelay(100);
        coin3.setAnimationFrame(0, 5);
        coin3.setAnimate(true);
        coin3.setLoopAnim(true);

        // Create 42 platforms
        for(int i = 0; i < 42; i++){
          Platform curr_platform = new Platform(getImage("platform.png"), 100, 300);
          platforms.add(curr_platform);
        }


        // Set the starting positions of the heroes
        hero.setLocation(575, 405);
        hero1.setLocation(25, 405);

        // Set starting positions for the coins
        coin1.setLocation(187, 330);
        coin2.setLocation(512, 230);
        coin3.setLocation(337, 130);

        // Set starting positions for the bottom platforms
        for (int j = 0; j < 27; j++){
          platforms.get(j).setLocation(j*25, 455);
        }

        // Set starting positions for the lowest 5-group of platforms
        for (int l = 27; (l < 32); l++){
          int starting_position = 125;
          platforms.get(l).setLocation(starting_position +((l - 27)*25), 355);
        }

        // Set starting positions for the middle 5-group of platforms
        for (int m = 32; (m < 37); m++){
          int starting_position = 450;
          platforms.get(m).setLocation(starting_position +((m - 32)*25), 255);
        }

        // Set starting positions for the highest 5-group of platforms
        for (int n = 37; (n < 42); n++){
          int starting_position = 275;
          platforms.get(n).setLocation(starting_position +((n - 37)*25), 155);
        }
        // Set the original speed and movement speed for Mario
        hero.setSpeed(0.0, 0);
        hero.set_movement_speed(0.3);
        // Set the original speed and movement speed for old Mario
        hero1.setSpeed(0.0, 0);
        hero1.set_movement_speed(0.3);

        // Create the background
        background = new ImageBackground(getImage("background.jpg"));
        hero.setBackground(background);

        // Create the group for both Heroes
        PLAYER_GROUP = new SpriteGroup("Player Group");
        PLAYER_GROUP.add(hero);
        PLAYER_GROUP.add(hero1);

        // Create the group for all Coins
        COIN_GROUP = new SpriteGroup("Coin Group");
        COIN_GROUP.add(coin1);
        COIN_GROUP.add(coin2);
        COIN_GROUP.add(coin3);

        // Create the group for all Platforms
        PLATFORM_GROUP = new SpriteGroup("Platform Group");
        for (int k = 0; k < 42; k++){
          PLATFORM_GROUP.add(platforms.get(k));
        }

        // Create a collision checking object for the player and platforms
        hitChecker = new SimpleCollision();
        hitChecker.setCollisionGroup(PLAYER_GROUP, PLATFORM_GROUP);

        // Create a collision checking object for the player and coins
        hitChecker2 = new SimpleCollision2();
        hitChecker2.setCollisionGroup(PLAYER_GROUP, COIN_GROUP);

        // Set variables to hold the scores of each hero
        m_score = hero.get_m_score();
        om_score = hero.get_om_score();
    }


    public void update(long elapsedTime) {
        // Convert the hero scores to strings
        String s_m_score = Integer.toString(m_score);
        String s_om_score = Integer.toString(om_score);

        // Update each group
        PLAYER_GROUP.update(elapsedTime);
        PLATFORM_GROUP.update(elapsedTime);
        COIN_GROUP.update(elapsedTime);

        // Determine the chracter's action when pressing up key
        if (keyDown(KeyEvent.VK_UP)){
          // Make it not jump up through all three platforms
          if (hero.getY() >= 355 && hero.getY() <= 380 && hero.getX() >= 95 && hero.getX() <= 235){
            hero.move(0,0);
          }
          else if(hero.getY() >= 255 && hero.getY() <= 280 && hero.getX() >= 420 && hero.getX() <= 560){
            hero.move(0,0);
          }
          else if(hero.getY() >= 155 && hero.getY() <= 180 && hero.getX() >= 245 && hero.getX() <= 385){
            hero.move(0,0);
          }
          // Jump upwards action
          else{
            hero.attemptJump();
          }
        }

        // Determine the chracter's action when pressing right key
        if (keyDown(KeyEvent.VK_RIGHT)) {
          // Not go through from left side for all three platforms
          if(hero.getY() >= 310 && hero.getY() <= 370 && hero.getX() >= 80 && hero.getX() <= 230){
            hero.move(0,0);
          }
          else if(hero.getY() >= 210 && hero.getY() <= 270 && hero.getX() >= 405 && hero.getX() <= 555){
            hero.move(0,0);
          }
          else if(hero.getY() >= 110 && hero.getY() <= 170 && hero.getX() >= 230 && hero.getX() <= 380){
            hero.move(0,0);
          }
          // Not move right out of the screen
          else if(hero.getX() >= 600){
            hero.move(0,0);
          }
          // Move towards right
          else{
        	  hero.move(hero.movement_speed*elapsedTime, 0);
          }
        }

        // Determine the chracter's action when pressing left key
        if (keyDown(KeyEvent.VK_LEFT)) {
          // Not go through from right side for all three platforms
          if(hero.getY() >= 310 && hero.getY() <= 370 && hero.getX() >= 95 && hero.getX() <= 245){
            hero.move(0,0);
          }
          else if(hero.getY() >= 210 && hero.getY() <= 270 && hero.getX() >= 420 && hero.getX() <= 570){
            hero.move(0,0);
          }
          else if(hero.getY() >= 110 && hero.getY() <= 170 && hero.getX() >= 245 && hero.getX() <= 395){
            hero.move(0,0);
          }
          // Not move left out of the screen
          else if(hero.getX() <= 0){
            hero.move(0,0);
          }
          // Move towards left
          else{
        	  hero.move(-hero.movement_speed*elapsedTime, 0);
          }
        }

        // Determine the chracter's action when pressing W key
        if (keyDown(KeyEvent.VK_W)) {
          // Make it not jump up through all three platforms
          if (hero1.getY() >= 355 && hero1.getY() <= 380 && hero1.getX() >= 95 && hero1.getX() <= 235){
            hero1.move(0,0);
          }
          else if(hero1.getY() >= 255 && hero1.getY() <= 280 && hero1.getX() >= 420 && hero1.getX() <= 560){
            hero1.move(0,0);
          }
          else if(hero1.getY() >= 155 && hero1.getY() <= 180 && hero1.getX() >= 245 && hero1.getX() <= 385){
            hero1.move(0,0);
          }
          // Jump upwards action
          else{
            hero1.attemptJump();
          }
        }

        // Determine the chracter's action when pressing D key
        if (keyDown(KeyEvent.VK_D)) {
          // Not go through from left side for all three platforms
          if(hero1.getY() >= 310 && hero1.getY() <= 370 && hero1.getX() >= 80 && hero1.getX() <= 230){
            hero1.move(0,0);
          }
          else if(hero1.getY() >= 210 && hero1.getY() <= 270 && hero1.getX() >= 405 && hero1.getX() <= 555){
            hero1.move(0,0);
          }
          else if(hero1.getY() >= 110 && hero1.getY() <= 170 && hero1.getX() >= 230 && hero1.getX() <= 380){
            hero1.move(0,0);
          }
          // Not move right out of the screen
          else if(hero1.getX() >= 600){
            hero1.move(0,0);
          }
          // Move towards right
          else{
        	  hero1.move(hero1.movement_speed*elapsedTime, 0);
          }
        }

        // Determine the chracter's action when pressing A key
        if (keyDown(KeyEvent.VK_A)) {
          // Not go through from right side for all three platforms
          if(hero1.getY() >= 310 && hero1.getY() <= 370 && hero1.getX() >= 95 && hero1.getX() <= 245){
            hero1.move(0,0);
          }
          else if(hero1.getY() >= 210 && hero1.getY() <= 270 && hero1.getX() >= 420 && hero1.getX() <= 570){
            hero1.move(0,0);
          }
          else if(hero1.getY() >= 110 && hero1.getY() <= 170 && hero1.getX() >= 245 && hero1.getX() <= 395){
            hero1.move(0,0);
          }
          // Not move left out of the screen
          else if(hero1.getX() <= 0){
            hero1.move(0,0);
          }
          // Move towards left
          else{
        	  hero1.move(-hero1.movement_speed*elapsedTime, 0);
          }
        }

        // Check for collisions between Heroes and Platforms, and Heroes and Coins
        hitChecker.checkCollision();
        hitChecker2.checkCollision();
    }

    // Put all objects on the game screen
    public void render(Graphics2D g) {
    	background.render(g);
    	PLATFORM_GROUP.render(g);
      PLAYER_GROUP.render(g);
      COIN_GROUP.render(g);
    }

    // Start the game
    public static void main(String[] args) throws InterruptedException{
        GameLoader game = new GameLoader();
        game.setup(new FinalGame(), new Dimension(650, 480), false);
        game.start();
    }

}
