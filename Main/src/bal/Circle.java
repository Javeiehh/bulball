package bal;

import java.awt.*;
import java.util.Random;

public class Circle {
    private int x, y;
    private final int radius;
    private int value;
    private int dx, dy; // Velocity in x and y directions
	private Color color;

    public Circle(int x, int y, int radius, int value) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.value = value;

        // Initialize random velocities
        Random rand = new Random();
        this.dx = rand.nextInt(5) + 1; // Velocity between 1 and 5
        this.dy = rand.nextInt(5) + 1; // Velocity between 1 and 5
        
        this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    // Public method to get the value of the circle
    public int getValue() {
        return value;
    }

    // Increments the value of the circle
    public void incrementValue() {
        this.value++;
    }

    // Method to check collision with another circle
    public boolean isColliding(Circle other) {
        int dx = this.x - other.x;
        int dy = this.y - other.y;
        int distanceSquared = dx * dx + dy * dy;
        int radiusSum = this.radius + other.radius;
        return distanceSquared <= radiusSum * radiusSum;
    }

    // Method to make two circles bounce off each other
    public void bounce(Circle other) {
        // Swap velocities for a simple bounce effect
        int tempDx = this.dx;
        int tempDy = this.dy;
        this.dx = other.dx;
        this.dy = other.dy;
        other.dx = tempDx;
        other.dy = tempDy;
    }

    // Moves the circle and bounces it off the panel edges
    public void move(int panelWidth, int panelHeight) {
        x += dx;
        y += dy;

        // Check for collision with panel boundaries and bounce if necessary
        if (x - radius < 0 || x + radius > panelWidth) {
            dx = -dx; // Reverse direction on x-axis
        }
        if (y - radius < 0 || y + radius > panelHeight) {
            dy = -dy; // Reverse direction on y-axis
        }
    }

    // Draws the circle with its value
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(value), x - 5, y + 5);
    }
}