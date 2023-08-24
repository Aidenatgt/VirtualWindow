import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;

public class Window extends JFrame {

    private JLabel time = new JLabel("Time", SwingConstants.CENTER);

    public Window(Monitor monitor) {
        super("Window");

        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setUndecorated(true);
        setColor(Color.BLACK);
        time.setFont(new Font("Serif", Font.BOLD, 128));
        time.setForeground(new Color(255, 255, 255));
        this.add(time);
        this.pack();
    }

    public void setColor(Color color) {
        this.getContentPane().setBackground(color);
        time.setForeground(invert(color));
    }

    public void setText(String string) {
        time.setText(string);
    }

    public static final int MAX_RGB_VALUE = 255;

    public static Color invert(Color c) {
        int r = MAX_RGB_VALUE - c.getRed();
        int g = MAX_RGB_VALUE - c.getGreen();
        int b = MAX_RGB_VALUE - c.getBlue();
        
        return new Color(r, g, b);
    }
}