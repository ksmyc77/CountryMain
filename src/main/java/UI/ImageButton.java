package UI;

import javax.swing.*;
import java.awt.*;

public class ImageButton extends JButton {

    public ImageButton(ImageIcon defaultIcon, ImageIcon pressedIcon){
        super();
        setIcon(defaultIcon);
        setBorder(null);
        setPressedIcon(pressedIcon);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
    public ImageButton(ImageIcon imageIcon){
        super();
        setIcon(imageIcon);
        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
    public ImageButton(ImageIcon imageIcon, String name){
        super(name);
        setIcon(imageIcon);
        setBorder(null);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
    }
}
