package bal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main extends JPanel {
    private ArrayList<Circle> circles = new ArrayList<>();
    private Timer timer;

    public Main() {
        // Mouse listener for adding circles on click
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    addCircle(e.getX(), e.getY());
                    repaint();
                }
            }
        });

        // Timer to update circle positions and repaint the panel
        timer = new Timer(16, e -> updateCircles());
        timer.start();
    }

    private void addCircle(int x, int y) {
        Circle newCircle = new Circle(x, y, 20, 1); // Circle with radius 20 and initial value 1

        // Only add the new circle if it doesn’t initially overlap with any existing circle
        for (Circle c : circles) {
            if (newCircle.isColliding(c)) {
                return; // Do not add the circle if it initially collides
            }
        }

        // Add the new circle if no collision found
        circles.add(newCircle);
    }

    private void updateCircles() {
        for (int i = 0; i < circles.size(); i++) {
            Circle c1 = circles.get(i);
            c1.move(getWidth(), getHeight());

            // Check for collisions with other circles
            for (int j = i + 1; j < circles.size(); j++) {
                Circle c2 = circles.get(j);
                if (c1.isColliding(c2)) {
                    c1.bounce(c2); // Make both circles bounce off each other
                    c1.incrementValue(); // Increment value on collision
                    c2.incrementValue(); // Increment value on collision
                }
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Circle c : circles) {
            c.draw(g);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Circle Collision and Bouncing");
        Main collisionPanel = new Main();
        frame.add(collisionPanel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
