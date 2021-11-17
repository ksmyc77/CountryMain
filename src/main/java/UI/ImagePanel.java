package UI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel() {

    }

    public ImagePanel(Image image) {
        img = image;
        setSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        setLayout(null);

    }

    public int getWidth() {
        return img.getWidth(null);
    }

    public int getHeight() {
        return img.getHeight(null);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0,0,null);
    }
}
