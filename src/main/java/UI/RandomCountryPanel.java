package UI;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;


//메뉴패널 우측에 부분에 부착되는 패널
public class RandomCountryPanel extends JPanel {

    ImageIcon[] countryImage = new ImageIcon[10];
    int randCountryId;
    Image selectedCountry;
    /**
     * Create the panel.
     */
    public RandomCountryPanel() {
        Random random = new Random();

        randCountryId = random.nextInt(6)+1;

        countryImage[0] = new ImageIcon("image\\RandomPanel\\america.png");
        countryImage[1] = new ImageIcon("image\\RandomPanel\\brazil.png");
        countryImage[2] = new ImageIcon("image\\RandomPanel\\china.png");
        countryImage[3] = new ImageIcon("image\\RandomPanel\\england.png");
        countryImage[4] = new ImageIcon("image\\RandomPanel\\germany.png");
        countryImage[5] = new ImageIcon("image\\RandomPanel\\sudan.png");
        countryImage[6] = new ImageIcon("image\\RandomPanel\\thailand.png");

        setCountry(randCountryId);

        setBounds(508, 103,975, 719);
        setLayout(null);
        setOpaque(false);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(selectedCountry, 0,0,null);
    }


    public void setCountry(int randCountryId) {
        selectedCountry = countryImage[randCountryId].getImage();
    }
}
