package Ui;

import javax.swing.*;
import java.awt.*;

public class ShadowPanel extends JPanel {

    public ShadowPanel() {
        this.setOpaque(false);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int shadowSize = 8;
        g2.setColor(new Color(0, 0, 0, 30));
        g2.fillRoundRect(shadowSize, shadowSize, this.getWidth() - shadowSize, this.getHeight() - shadowSize, 15, 15);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, this.getWidth() - shadowSize, this.getHeight() - shadowSize, 15, 15);
        g2.dispose();
        super.paintComponent(g);
    }
}
