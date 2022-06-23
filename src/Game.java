import entity.mob.Player;
import graphics.Screen;
import input.Keyboard;
import level.Level;
import level.RandomLevel;
import level.SpawnLevel;
import level.TileCoordinate;

import javax.swing.JFrame;
//import java.awt.Graphics;
//import java.awt.Canvas;
//import java.awt.Dimension;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class Game extends Canvas implements Runnable {
    public static int width = 300;
    public static int height = width / 16 * 9; //168
    public static int scale = 3;
    public static String title = "Rain";
    private Thread thread;
    private JFrame frame;
    private Keyboard key;
    private Level level;
    private Player player;
    private boolean running = false;
    private Screen screen;
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); //create image
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData(); //convert image to editable array

    public Game() {
        /*
        1) Stretch screen to fit into Canvas's preferred size
         */
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size); // set size of Game::Canvas

        screen = new Screen(width, height);
        frame = new JFrame();

        key = new Keyboard();
//        level = new RandomLevel(64, 64);
        level = new SpawnLevel("/levels/spawn.png");

        TileCoordinate playerSpawn = new TileCoordinate(20, 56);
        player = new Player(playerSpawn.x(), playerSpawn.y(), key);
        addKeyListener(key);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        /*
        Main loop since Game implements Runnable
        1) Call update() every 1/60th second (i.e. 60 times / s)
        2) Call render() every as often as possible
        3) Update fps and ups counter once per second
         */

        long lastTime = System.nanoTime(); // used for updates per second
        long timer = System.currentTimeMillis(); // used for frames per second
        final double ns = 1000000000.0 / 60.0;
        double delta = 0; // timekeeper for Nano-seconds between runs
        int frames = 0;
        int updates = 0;

        requestFocus();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns; // increases delta every iteration (i.e. 5000ps)
            lastTime = now;
            while (delta >= 1) { // executed once every 1/60th of a second
                update();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000; //set target time 1 second forward
                //System.out.println(updates + " ups, " + frames + " fps");
                frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }

    //    int x = 0, y = 0;
    public void update() { // "tick"
        key.update();
        player.update();

//        if (key.up) y--;
//        if (key.down) y++;
//        if (key.left) x--;
//        if (key.right) x++;

    }

    public void render() {
        /*
        1) call graphics.Screen.render
        2) Write pixels from graphics.Screen.pixels into Game.pixels ==> image
        3) Add image to buffer
        4) show buffer content
         */
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        int xScroll = player.x - screen.width/2;
        int yScroll = player.y - screen.height/2;

        level.render(xScroll, yScroll, screen);
        player.render(screen);
//        screen.render(x,y);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen.pixels[i];
        }
        //System.arraycopy(screen.pixels,0,pixels,0, pixels.length);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", 0, 40));
        //g.drawString("X: " + player.x + ", Y: " + player.y, 350, 300);
        g.dispose();
        bs.show();

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle("Rain");
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }


}
