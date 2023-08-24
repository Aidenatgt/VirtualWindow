import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalTime;

public class App {

    public static int START;
    public static int SUNRISE_START;
    public static int SUNRISE_END;

    private Window primary = new Window(Monitor.Primary);
    private boolean running = true;

    public class Listener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                running = false;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
        
    }

    public App(String... args) {

        START = secondOfDay(6, 9, 0);
        SUNRISE_START = secondOfDay(6, 9, 1);
        SUNRISE_END = secondOfDay(7, 20, 0);

        if (args.length > 0) {
            if (args[0].equals("demo") || args[0].equals("-demo") || args[0].equals("--demo")) {
                START = currentSeconds()+5;
                SUNRISE_START = currentSeconds()+6;
                SUNRISE_END = currentSeconds()+26;
            }
        }

        primary.setColor(Color.BLACK);
        primary.addKeyListener(new Listener());
        ColorScale scale = new ColorScale(new ColorPoint(SUNRISE_START, Color.BLACK), new ColorPoint(SUNRISE_END, new Color(135, 206, 235)));

        int previousSeconds = currentSeconds();
        System.out.println();

        while(true) {
            int currentSeconds = currentSeconds();

            if (currentSeconds > START && previousSeconds <= START && !running) {
                running = true;
                primary.dispose();
                primary = new Window(Monitor.Primary);

                primary.setColor(Color.BLACK);
                primary.addKeyListener(new Listener());
            }
            previousSeconds = currentSeconds;

            System.out.print(String.format("\rTime: %s; Start Time: %s, Running: %b", currentSeconds, START, running));

            LocalTime time = LocalTime.now();
            primary.setText(String.format("%02d:%02d", time.getHour(), time.getMinute()));

            if (currentSeconds > SUNRISE_START && currentSeconds < SUNRISE_END) {
                primary.setColor(scale.getPoint(currentSeconds));
            }

            primary.setVisible(running);

            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new App(args);
    }

    private static int currentSeconds() {
        LocalTime time = LocalTime.now();
        return time.toSecondOfDay();
    }

    public static int secondOfDay(int hours, int minutes, int seconds) {
        return hours * 3600 + minutes * 60 + seconds;
    }

    public static int secondOfDay(int hours, int minutes) {
        return secondOfDay(hours, minutes, 0);
    }

    public static int secondOfDay(int hours) {
        return secondOfDay(hours, 0, 0);
    }
}
