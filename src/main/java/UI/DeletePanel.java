package UI;

import country.CountryDAO;

import javax.swing.*;
import java.awt.*;

public class DeletePanel extends ImagePanel {

    JLabel lbDelete;
    ImageButton btnHome;
    ImageButton btnDelete;
    JTextField tfDelete;
    CountryDAO dao = CountryDAO.getInstance();

    //나라 정보 삭제를 위한 패널
    public DeletePanel(Image image, ImagePanel menuPanel){
        super(image);

        lbDelete = new JLabel("삭제할 나라를 입력하세요");
        lbDelete.setBounds(448, 233, 454, 75);
        lbDelete.setForeground(Color.WHITE);
        lbDelete.setHorizontalAlignment(SwingConstants.CENTER);
        lbDelete.setFont(new Font("HY견고딕", Font.BOLD, 30));
        add(lbDelete);

        btnDelete = new ImageButton(new ImageIcon("image\\updatePanel\\delete\\deleteBtn.png"));
        btnDelete.setBounds(1148, 437, 226, 75);
        add(btnDelete);
        btnDelete.addActionListener(e -> {
            
            if(dao.deleteCountry(tfDelete.getText())){
                JOptionPane.showMessageDialog(null, "삭제 성공", "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "해당 국가가 없습니다.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
            }
        });


        btnHome = new ImageButton(new ImageIcon("image\\updatePanel\\delete\\homeBtn.png"));
        btnHome.setBounds(1171, 97, 226, 75);
        add(btnHome);
        btnHome.addActionListener(e -> {
            setVisible(false);
            menuPanel.setVisible(true);
        });

        tfDelete = new JTextField();
        tfDelete.setBounds(211, 437, 861, 75);
        tfDelete.setBorder(null);
        add(tfDelete);
        tfDelete.setColumns(10);
    }
}
