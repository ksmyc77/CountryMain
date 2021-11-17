package UI;

import javax.swing.*;
import java.awt.*;

public class QuizResultPanel extends ImagePanel {

    private JTextField tfScore;
    private ImageButton btnHomeFromQuiz;
    private JLabel lbScore;
    public QuizResultPanel(Image image, ImagePanel menuPanel) {
        super(image);

        tfScore = new JTextField();
        tfScore.setBounds(748, 330, 325, 109);
        tfScore.setFont(new Font("휴먼편지체",Font.BOLD, 40));
        tfScore.setForeground(Color.WHITE);
        tfScore.setOpaque(false);
        tfScore.setBorder(null);
        tfScore.setColumns(10);
        add(tfScore);

        lbScore = new JLabel("당신의 점수는?");
        lbScore.setBounds(508, 305, 228, 162);
        lbScore.setFont(new Font("휴먼편지체",Font.PLAIN, 35));
        lbScore.setForeground(Color.WHITE);
        lbScore.setOpaque(false);
        lbScore.setBorder(null);
        add(lbScore);

        btnHomeFromQuiz = new ImageButton(new ImageIcon("image\\QuizResultPanel\\homeBtn.png"),new ImageIcon("image\\SearchPanel\\homeBtnClicked.png"));
        btnHomeFromQuiz.setBounds(1121, 730, 234, 80);
        btnHomeFromQuiz.addActionListener(e -> {
            setVisible(false);
            menuPanel.setVisible(true);
        });
        add(btnHomeFromQuiz);
    }
    public void setScore(String score){
        tfScore.setText(score);
    }
}
