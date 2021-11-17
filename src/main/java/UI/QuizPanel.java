package UI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import quiz.QuizDAO;
import quiz.QuizDTO;
import quiz.QuizQuery;

public class QuizPanel extends ImagePanel {

    /**
     * Create the panel.
     */
    //문제 번호
    private int quizNum;
    //문제 아이디
    private int id;
    //멤버 아이디
    private String memberId = null;
    //퀴즈를 푼 후, 틀리 문제 번호를 담을 배열
    private int inCorrectQuiz[];
    //퀴즈 정답
    private String answer;

    //컴포넌트
    private QuizResultPanel resultPanel;
    private JPanel[] quizPanels;
    private JTextField tfQuestion;
    private ButtonGroup group;
    private JRadioButton[] radioGroup;
    private JRadioButton clearBtn;
    private JTextField tfRemainQuiz;
    private JButton nextBtn;

    //dao
    QuizDAO dao = QuizDAO.getInstance();

    //파라메터: 배경이미지, 멤버ID, 퀴즈종료 후 돌아갈 들어갈 결과페이지, 화면전환을 위한 생성된 퀴즈패널 빼열
    public QuizPanel(Image image, QuizResultPanel resultPanel, QuizPanel[] quizPanel) {
        //backGround 이미지 그리기
        super(image);


        //문제 Setting
        tfQuestion = new JTextField();
        tfQuestion.setFont(new Font("휴먼편지체",Font.BOLD, 40));
        tfQuestion.setForeground(Color.WHITE);
        tfQuestion.setOpaque(false);
        tfQuestion.setBorder(null);
        tfQuestion.setBounds(322, 231, 649, 64);
        tfQuestion.setColumns(10);
        add(tfQuestion);

        //항목 Setting
        group = new ButtonGroup();
        radioGroup = new JRadioButton[5];
        for(int i =0; i<5; i++) {
            radioGroup[i] = new JRadioButton();
            radioGroup[i].setBounds(322, 331+i*40, 337, 47);
            radioGroup[i].setOpaque(false);
            radioGroup[i].setFont(new Font("휴먼편지체",Font.PLAIN, 18));
            radioGroup[i].setForeground(Color.WHITE);
            radioGroup[i].setBorder(null);

            add(radioGroup[i]);
            group.add(radioGroup[i]);
        }

        clearBtn = new JRadioButton();
        group.add(clearBtn);

        //남은 문제 Setting
        tfRemainQuiz = new JTextField();
        tfRemainQuiz.setFont(new Font("휴먼편지체",Font.BOLD, 20));
        tfRemainQuiz.setForeground(Color.WHITE);
        tfRemainQuiz.setOpaque(false);
        tfRemainQuiz.setBorder(null);
        tfRemainQuiz.setBounds(902, 231, 200, 64);
        tfRemainQuiz.setColumns(10);
        add(tfRemainQuiz);


        nextBtn = new JButton();
        nextBtn.setIcon(new ImageIcon("image\\quizPanel\\nextBtn.png"));
        nextBtn.setFocusPainted(false);
        nextBtn.setContentAreaFilled(false);
        nextBtn.setBorderPainted(false);
        nextBtn.setPressedIcon(new ImageIcon("image\\quizPanel\\nextBtnClicked.png"));
        nextBtn.setBounds(1125, 734, 227, 72);
        add(nextBtn);

        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkPanel();
            }
        });
        this.resultPanel = resultPanel;
        quizPanels = quizPanel;
    }

    public void setQuizNum(int quizNum) {
        this.quizNum = quizNum;
    }
    public void setId(int id) {
        this.id = id;
    }

    //퀴즈 만들기
    public void createQuiz(QuizQuery query) {
        int randItems[] = new int[5];
        tfQuestion.setText("문제" + quizNum + ") " + query.getQuestion(id));
        String[] items = query.getItems(id);
        //정답은 배열의 맨첫항목에 저장된다.
        answer = items[0];

        //보기를 랜덤하게 출력하기 위해 1~5의 랜덤 배열을 가져온다.
        Random random = new Random();
        for(int i =0; i<5; i++) {
            //총 row(국가) 수
            randItems[i] = random.nextInt(5)+1;

            //중복 제거
            for(int j=0; j<i; j++) {
                if(randItems[i] == randItems[j]) {
                    i--;
                }
            }
        }
        //항목 설정
        for(int i =0; i<5; i++) {
            radioGroup[i].setText(items[randItems[i]-1]);
        }
        //남은 문제 설정
        tfRemainQuiz.setText("남은 문제: " + quizNum + " / " + 10 );


    }

    public void checkAnswer(){
        boolean checked = false;
        for(int i =0; i<5; i++) {
            if (radioGroup[i].isSelected()) {
                checked = true;
                if(radioGroup[i].getText().equals(answer)) {
                    JOptionPane.showMessageDialog(null, "정답");
                    //정답 처리
                }
                else {
                    JOptionPane.showMessageDialog(null, "오답");
                    //오답 처리 : quiz db에 해당 퀴즈 번호에, 틀린 문제 번호를 저장한다.
                    dao.updateQuiz(memberId, quizNum, id);
                }
            }
        }
        clearBtn.setSelected(true);
        if(!checked) {
            JOptionPane.showMessageDialog(null, "답을 선택하세요");
        }
    }

    //정답 체크 후, db에 오답유무 전달 / 오답이라면 문제ID를 전달해서 저장.
    public void checkPanel() {
        //마지막 패널이라면 퀴즈의 오답목록을 가져온다.
        //100점에서 틀린 문제 한문제 당 10점 씩 감점한다.
        if(quizNum==quizPanels.length) {
            checkAnswer();
            inCorrectQuiz = dao.getIncorrectQuiz(memberId);
            int score = 100;
            for(int j =0; j<quizPanels.length; j++) {
                if(inCorrectQuiz[j]!=0) {
                    score = score - 10;
                }
            }
            resultPanel.setScore(score + "점!");
            setVisible(false);
            resultPanel.setVisible(true);
        }
        //첫번 째 퀴즈패널이라면 멤버의 아아디에 대한 quiz db를 생성한다. 같은 아이디로 퀴즈를 푼 기록이 있다면 기록이 replace된다.
        //아니라면 다음 문제로 들어간다.
        else {
            if(quizNum==1) {
                dao.startQuiz(memberId);
            }
            checkAnswer();
            setVisible(false);
            quizPanels[quizNum].setVisible(true);
        }

    }
    public void setId(String id) {
        memberId = id;
    }
}


