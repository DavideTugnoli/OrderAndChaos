package graphicui;

import javax.swing.*;
import java.awt.*;

public class OverlayPanel extends JPanel {
    public OverlayPanel() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(255, 255, 255, 100));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
