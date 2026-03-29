package pro1.swingComponents;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DisplayPanel extends JPanel {
    private final List<Point> circles = new ArrayList<>();
    private int circleRadius = 30;
    private boolean linesHidden = false;

    public DisplayPanel() {
        setBackground(Color.WHITE);
    }

    public void addCircle(int x, int y) {
        circles.add(new Point(x, y));
        repaint();
    }

    public void setCircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
        repaint();
    }

    public void setLinesHidden(boolean linesHidden) {
        this.linesHidden = linesHidden;
        repaint();
    }

    public void reset() {
        circles.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (!linesHidden) {
            graphics.setColor(new Color(70, 70, 70));
            for (int i = 0; i < circles.size(); i++) {
                Point newer = circles.get(i);
                for (int j = 0; j < i; j++) {
                    Point older = circles.get(j);
                    graphics.drawLine(older.x, older.y, newer.x, newer.y);
                }
            }
        }

        for (Point circle : circles) {
            int diameter = circleRadius * 2;
            int topLeftX = circle.x - circleRadius;
            int topLeftY = circle.y - circleRadius;

            graphics.setColor(new Color(77, 143, 255));
            graphics.fillOval(topLeftX, topLeftY, diameter, diameter);
            graphics.setColor(new Color(22, 57, 117));
            graphics.drawOval(topLeftX, topLeftY, diameter, diameter);
        }

        graphics.dispose();
    }
}
