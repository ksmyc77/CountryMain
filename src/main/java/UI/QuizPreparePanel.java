package UI;

import quiz.CapitalQuizQuery;
import quiz.LocationQuizQuery;
import quiz.QuizQuery;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class QuizPreparePanel extends ImagePanel {
    //QuizPreparePanel
    private ImageButton startBtn;
    //문제 번호 아이디
    private int[] id;
    private QuizPanel[] quizPanel = new QuizPanel[10];
    public QuizPreparePanel(Image image, JFrame frame, QuizResultPanel quizResultPanel){
        //여기서 문제를 낼 국가들의 id를 선정한다.
        super(image);
        Random random = new Random();
        id = new int[10];

        //quizPanel 배경 설정
        ImageIcon[] ic = new ImageIcon[10];
        ic[0] = new ImageIcon("image\\quizPreparePanel\\quizPanel_background1.png");
        ic[1] = new ImageIcon("image\\quizPreparePanel\\quizPanel_background2.png");
        ic[2] = new ImageIcon("image\\quizPreparePanel\\quizPanel_background3.png");
        ic[3] = new ImageIcon("image\\quizPreparePanel\\quizPanel_background4.png");
        ic[4] = new ImageIcon("image\\quizPreparePanel\\quizPanel_background5.png");
        ic[5] = new ImageIcon("image\\quizPreparePanel\\quizPanel_background6.png");
        ic[6] = new ImageIcon("image\\quizPreparePanel\\quizPanel_background7.png");
        ic[7] = new ImageIcon("image\\quizPreparePanel\\quizPanel_background8.png");
        ic[8] = new ImageIcon("image\\quizPreparePanel\\quizPanel_background9.png");
        ic[9] = new ImageIcon("image\\quizPreparePanel\\quizPanel_background10.png");


        //퀴즈 패널 생성 후, frame에 부착한다
        for(int i = 0; i<10; i++) {
            quizPanel[i] = new QuizPanel(ic[i].getImage(),quizResultPanel,quizPanel);
            frame.getContentPane().add(quizPanel[i]);
            quizPanel[i].setVisible(false);
        }


        startBtn = new ImageButton(new ImageIcon("image\\quizPreparePanel\\startBtn.png"),new ImageIcon("image\\quizPreparePanel\\startBtnClicked.png"));
        startBtn.setBounds(1120, 732, 236, 77);
        startBtn.addActionListener(e -> {

            //매번 다른 문제를 출제하기 위해 버튼 클릭 시: 여기서 quiz id를 랜덤으로 구한다.
            for(int i =0; i<10; i++) {
                //총 row(국가) 수
                id[i] = random.nextInt(226)+1;

                //중복 제거
                for(int j=0; j<i; j++) {
                    if(id[i] == id[j]) {
                        i--;
                    }
                }
            }
            QuizQuery cq = new CapitalQuizQuery();
            QuizQuery lq = new LocationQuizQuery();
            for(int i =0; i<10; i++) {
                quizPanel[i].setQuizNum(i+1);
                quizPanel[i].setId(id[i]);
                if(i%2==0){
                    quizPanel[i].createQuiz(lq);
                }
                else{
                    quizPanel[i].createQuiz(cq);
                }
            }
            setVisible(false);
            quizPanel[0].setVisible(true);
        });
        add(startBtn);
    }
    public void setMemberId(String memberId){
        for(int i =0; i<10; i++) {
            quizPanel[i].setId(memberId);
        }
    }

}
