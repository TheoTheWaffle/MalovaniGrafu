package pro1.swingComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private final DisplayPanel displayPanel;

    public MainFrame() {
        setTitle("PRO1 Drawing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayPanel = new DisplayPanel();
        add(displayPanel, BorderLayout.CENTER);

        JPanel leftPanel = createLeftPanel();
        add(leftPanel, BorderLayout.WEST);

        displayPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                displayPanel.addCircle(e.getX(), e.getY());
            }
        });

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    private JPanel createLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(220, 0));
        leftPanel.setBorder(new EmptyBorder(20, 15, 20, 15));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(245, 245, 245));

        JLabel radiusLabel = new JLabel("Polomer kolecek");
        radiusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JSlider radiusSlider = new JSlider(5, 120, 30);
        radiusSlider.setAlignmentX(Component.LEFT_ALIGNMENT);
        radiusSlider.setMajorTickSpacing(25);
        radiusSlider.setMinorTickSpacing(5);
        radiusSlider.setPaintTicks(true);
        radiusSlider.setPaintLabels(true);
        radiusSlider.addChangeListener(e -> displayPanel.setCircleRadius(radiusSlider.getValue()));

        JCheckBox hideLinesCheckBox = new JCheckBox("Skryt usecky");
        hideLinesCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        hideLinesCheckBox.setBackground(leftPanel.getBackground());
        hideLinesCheckBox.addActionListener(e -> displayPanel.setLinesHidden(hideLinesCheckBox.isSelected()));

        JButton resetButton = new JButton("reset");
        resetButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        resetButton.addActionListener(e -> displayPanel.reset());

        leftPanel.add(radiusLabel);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(radiusSlider);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(hideLinesCheckBox);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(resetButton);
        leftPanel.add(Box.createVerticalGlue());

        return leftPanel;
    }
}
