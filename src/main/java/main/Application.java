package main;

import java.awt.*;
import javax.swing.*;

import UI.*;
import country.CountryDAO;
import data.*;
import db.DBConnect;
import member.MemberDAO;
import quiz.QuizDAO;


public class Application {

    //Panel / Frame
    private JFrame frame;
    private ImagePanel welcomePanel;
    private ImagePanel menuPanel;
    private QuizPreparePanel quizPreparePanel;
    private ImagePanel graphPanel;
    private QuizResultPanel quizResultPanel;
    private SearchPanel searchAllPanel;
    private SearchPanel searchOdapPanel;
    private DeletePanel deletePanel;
    private InsertPanel insertPanel;
    private ModifyPanel modifyPanel;
    private ImagePanel updateMenuPanel;

    //toolBar
    ToolBar toolbar;

    //WelcomePanel
    private JTextField tfId;
    private JPasswordField pfPassword;
    private ImageButton btnLogin;
    private ImageButton btnJoin;
    private JLabel lbTitle;
    private JLabel lbId;
    private JLabel lbPw;
    //MenuPanel
    private ImageButton btnQuizMenu;
    private ImageButton btnSearchMenu;
    private ImageButton btnModifyMenu;
    //SearchAllPanel
    private ImageButton btnOdap;
    private CountryTableModel allCountry;
    //SearchOdapPanel
    private JButton btnAll;
    private CountryTableModel odapCountry;
    //GraphPanel
    private ImageButton btnRace;
    private ImageButton btnLanguage;
    private ImageButton btnReligion;
    private ImageButton btnSearch;
    private JTextField tfSearch;
    private JLabel lbSearch;
    private GraphDrawingPanel drawPanel;
    //UpdateMenuPanel
    private ImageButton btnModify;
    private ImageButton btnInsert;
    private ImageButton btnDelete;
    private ImageButton btnHome;

    private JLabel lbUpdateMenu;

    //로그인된 멤버의 아이디
    private String memberId;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DBConnect.createDB("oopadb");
                    //에플레이케이션 실행 시 db테이블 생성, 데이터를 로딩한다.
                    CountryDAO.getInstance().createTable("oopadb");
                    MemberDAO.getInstance().createTable("oopadb");
                    QuizDAO.getInstance().createTable("oopadb");

                    Application window = new Application();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Application() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        toolbar = new ToolBar(frame);
        frame.setJMenuBar(toolbar.mb);

        //패널 선언
        welcomePanel = new ImagePanel(new ImageIcon("image\\welcomePanel\\welcomePanel.png").getImage());
        menuPanel = new ImagePanel(new ImageIcon("image\\menuPanel\\menuPanel.png").getImage());
        quizResultPanel = new QuizResultPanel(new ImageIcon("image\\quizResultPanel\\quizResultPanel.png").getImage(), menuPanel);
        quizPreparePanel = new QuizPreparePanel(new ImageIcon("image\\quizPreparePanel\\quizPreparePanel.png").getImage(), frame, quizResultPanel);
        graphPanel = new ImagePanel(new ImageIcon("image\\graphPanel\\graphPanel.png").getImage());
        searchAllPanel = new SearchPanel(new ImageIcon("image\\searchAllPanel\\searchAllPanel.png").getImage(),
                graphPanel, menuPanel, frame);
        searchOdapPanel = new SearchPanel(new ImageIcon("image\\searchOdapPanel\\searchPanel.png").getImage(),
                graphPanel, menuPanel, frame);
        updateMenuPanel = new ImagePanel(new ImageIcon("image\\updatePanel\\updateMenuPanel.png").getImage());
        deletePanel = new  DeletePanel(new ImageIcon("image\\updatePanel\\delete\\deletePanel.png").getImage(),updateMenuPanel);
        insertPanel = new InsertPanel(new ImageIcon("image\\updatePanel\\insert\\insertPanel.png").getImage(),updateMenuPanel);
        modifyPanel = new ModifyPanel(new ImageIcon("image\\updatePanel\\modify\\modifyPanel.png").getImage(),updateMenuPanel);



        frame.setSize(1546,913);

        frame.getContentPane().add(quizResultPanel);
        frame.getContentPane().add(quizPreparePanel);
        frame.getContentPane().add(menuPanel);
        frame.getContentPane().add(welcomePanel);
        frame.getContentPane().add(searchAllPanel);
        frame.getContentPane().add(searchOdapPanel);
        frame.getContentPane().add(graphPanel);
        frame.getContentPane().add(deletePanel);
        frame.getContentPane().add(insertPanel);
        frame.getContentPane().add(modifyPanel);
        frame.getContentPane().add(updateMenuPanel);

    //    frame.getContentPane().add(modifyPanel);
    //    frame.getContentPane().add(insertPanel);

        //	frame.getContentPane().add(infoPanel);
        menuPanel.setVisible(false);
        quizPreparePanel.setVisible(false);
        quizResultPanel.setVisible(false);
        searchOdapPanel.setVisible(false);
        searchAllPanel.setVisible(false);
        graphPanel.setVisible(false);
        deletePanel.setVisible(false);
        insertPanel.setVisible(false);
        modifyPanel.setVisible(false);
        updateMenuPanel.setVisible(false);

        /**
         * WELCOME PANEL
         */
        tfId = new JTextField();
        tfId.setBounds(959, 405, 309, 53);
        welcomePanel.add(tfId);
        tfId.setColumns(10);
        tfId.setBorder(null);

        pfPassword = new JPasswordField();
        pfPassword.setBounds(959, 537, 309, 53);
        welcomePanel.add(pfPassword);
        pfPassword.setBorder(null);

        btnLogin = new ImageButton(new ImageIcon("image\\welcomePanel\\login.png"),new ImageIcon("image\\welcomePanel\\login_clicked.png"));
        btnLogin.setBounds(944, 658, 163, 67);
        welcomePanel.add(btnLogin);

        btnJoin = new ImageButton(new ImageIcon("image\\welcomePanel\\join.png"),new ImageIcon("image\\welcomePanel\\join_clicked.png"));
        btnJoin.setBounds(1134, 658, 163, 67);
        welcomePanel.add(btnJoin);

        //로그인 버튼 클릭 시, 텍스트의 값을 id, pw에 저장하고 dao를 통해 로그인 체크를 한다.
        //성공 시 true, 실패 시 false 반환
        btnLogin.addActionListener(e -> {
            MemberDAO dao = MemberDAO.getInstance();
            String id = tfId.getText();
            String pw = pfPassword.getText();
            System.out.println("id : "+ id + " password : " + pw);
            boolean check = dao.loginCheck(id, pw);

            //성공 시, memberId 변수에 로그인 된 아이디값을 저장한다.
            //동시에 quizPanel에 아이디값을 전달해서, 해당 아이디를 위한 퀴즈를 생성한다.
            if(check) {
                JOptionPane.showMessageDialog(null, "로그인 성공");
                memberId = id;
                for(int i =0; i<10; i++) {
                    quizPreparePanel.setMemberId(memberId);
                }
                welcomePanel.setVisible(false);
                menuPanel.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "로그인 실패");
            }
        });

        //Join 버튼 클릭 시, JoinPanel 생성 : JoinPanel은 하나의 Frame으로 해당 프레임에 상속되지 않고 단독적으로 생성되고 소멸된다.
        btnJoin.addActionListener(e -> new JoinPanel());

        //라벨 설정 후 패널에 add
        lbTitle = new JLabel("COUNTRY QUIZ");
        lbTitle.setForeground(Color.WHITE);
        lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lbTitle.setFont(new Font("Bahnschrift", Font.BOLD, 47));
        lbTitle.setBounds(212, 124, 418, 105);

        lbId = new JLabel("ID");
        lbId.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        lbId.setForeground(Color.WHITE);
        lbId.setBounds(877, 405, 54, 53);

        lbPw = new JLabel("PASSWORD");
        lbPw.setFont(new Font("Bahnschrift", Font.PLAIN, 30));
        lbPw.setForeground(Color.WHITE);
        lbPw.setBounds(768, 537, 163, 53);

        welcomePanel.add(lbTitle);
        welcomePanel.add(lbId);
        welcomePanel.add(lbPw);

        /**
         * MENU PANEL
         */

        //랜덤으로 나라 정보 패널을 붙힌다.
        RandomCountryPanel rc = new RandomCountryPanel();
        menuPanel.add(rc);
        //퀴즈버튼 클릭 시, 퀴즈 준비 화면으로 전환
        btnQuizMenu = new ImageButton(new ImageIcon("image\\menuPanel\\quizBtn.png"));
        btnQuizMenu.setBounds(85, 190, 282, 91);
        btnQuizMenu.addActionListener(e -> {
            menuPanel.setVisible(false);
            quizPreparePanel.setVisible(true);
        });
        menuPanel.add(btnQuizMenu);

        //오답노트 버튼 클릭 시, 로그인 된 ID의 db에 저장된 틀린 문제 정보를 가져와서, 오답노트패널을 생성한다.
        btnSearchMenu = new ImageButton(new ImageIcon("image\\welcomePanel\\odabBtn.png"));
        btnSearchMenu.setBounds(85, 382, 282, 96);
        btnSearchMenu.addActionListener(e -> {
            //SearchPanel 들의 대한 dataModel 세팅
            allCountry = new AllCountryTableData(memberId);
            odapCountry = new OdapCountryTableData(memberId);
            searchAllPanel.setTableModel(allCountry);
            searchOdapPanel.setTableModel(odapCountry);
            menuPanel.setVisible(false);
            searchAllPanel.setVisible(true);
        });
        menuPanel.add(btnSearchMenu);

        //정보 수정 버튼 클릭 시, 정보 수정 패널로 전환된다.
        btnModifyMenu = new ImageButton(new ImageIcon("image\\welcomePanel\\memberBtn.png"));
        btnModifyMenu.setBounds(85, 586, 282, 91);
        btnModifyMenu.addActionListener(e -> {
            menuPanel.setVisible(false);
            updateMenuPanel.setVisible(true);
        });
        menuPanel.add(btnModifyMenu);

        /**
         * UpdateMenuPanel PANEL
         */
        lbUpdateMenu = new JLabel("나라 정보 관리 메뉴");
        lbUpdateMenu.setBounds(488, 103, 615, 155);
        lbUpdateMenu.setFont(new Font("HY견고딕", Font.PLAIN, 40));
        lbUpdateMenu.setForeground(Color.WHITE);
        lbUpdateMenu.setHorizontalAlignment(SwingConstants.CENTER);
        updateMenuPanel.add(lbUpdateMenu);

        btnModify = new ImageButton(new ImageIcon("image\\UpdatePanel\\modifyBtn.png"));
        btnModify.setBounds(129, 348, 298, 177);
        btnModify.addActionListener(e -> {
            updateMenuPanel.setVisible(false);
            modifyPanel.setVisible(true);
        });
        updateMenuPanel.add(btnModify);
        btnInsert = new ImageButton(new ImageIcon("image\\UpdatePanel\\insertBtn.png"));
        btnInsert.setBounds(617, 348, 298, 177);
        btnInsert.addActionListener(e -> {
            updateMenuPanel.setVisible(false);
            insertPanel.setVisible(true);
        });
        updateMenuPanel.add(btnInsert);
        btnDelete = new ImageButton(new ImageIcon("image\\UpdatePanel\\deleteBtn.png"));
        btnDelete.setBounds(1120, 348, 298, 177);
        btnDelete.addActionListener(e -> {
            updateMenuPanel.setVisible(false);
            deletePanel.setVisible(true);
        });
        updateMenuPanel.add(btnDelete);
        btnHome = new ImageButton(new ImageIcon("image\\UpdatePanel\\homeBtn.png"));
        btnHome.setBounds(1118, 734, 226, 75);
        btnHome.addActionListener(e -> {
            updateMenuPanel.setVisible(false);
            menuPanel.setVisible(true);
        });
        updateMenuPanel.add(btnHome);
        //SearchPanel 들의 대한 dataModel 세팅과 초기화는 위에서 했으므로, SearchPanel끼리 버튼 클릭시 변환되는 부분만 구현한다.
        /**
         * SEARCH ALL PANEL
         */
        btnOdap = new ImageButton(new ImageIcon("image\\searchAllPanel\\allBtn.png"));
        btnOdap.setBounds(829, 71, 288, 86);
        searchAllPanel.add(btnOdap);
        btnOdap.addActionListener(e -> {
            searchOdapPanel.setVisible(true);
            searchAllPanel.setVisible(false);
        });

        /**
         * SEARCH ODAP PANEL
         */
        btnAll = new ImageButton(new ImageIcon("image\\searchOdapPanel\\odapBtn.png"));
        btnAll.setBounds(829, 71, 288, 86);
        searchOdapPanel.add(btnAll);
        btnAll.addActionListener(e -> {
            searchAllPanel.setVisible(true);
            searchOdapPanel.setVisible(false);
        });
        /**
         * Graph PANEL
         */
        lbSearch = new JLabel("SEARCH");
        lbSearch.setHorizontalAlignment(SwingConstants.CENTER);
        lbSearch.setForeground(Color.WHITE);
        lbSearch.setFont(new Font("HY견고딕", Font.BOLD, 15));
        lbSearch.setBounds(79, 112, 103, 45);
        graphPanel.add(lbSearch);

        //거
        tfSearch = new JTextField();
        tfSearch.setBounds(216, 111, 556, 47);
        graphPanel.add(tfSearch);
        tfSearch.setBorder(null);
        tfSearch.setColumns(10);

        btnSearch = new ImageButton(new ImageIcon("image\\graphPanel\\searchBtn.png"));
        btnSearch.setBounds(1144, 71, 288, 86);
        graphPanel.add(btnSearch);
        btnSearch.addActionListener(e -> {
            //SearchPanel로 이동
            searchAllPanel.setVisible(true);
            graphPanel.setVisible(false);
        });

        btnLanguage = new ImageButton(new ImageIcon("image\\graphPanel\\languageBtn.png"));
        btnLanguage.setBounds(206, 194, 175, 38);
        graphPanel.add(btnLanguage);
        btnLanguage.addActionListener(new LanguageButtonListener(tfSearch, drawPanel));

        btnReligion = new ImageButton(new ImageIcon("image\\graphPanel\\religionBtn.png"));
        btnReligion.setBounds(410, 194, 175, 38);
        graphPanel.add(btnReligion);
        btnReligion.addActionListener(new LanguageButtonListener(tfSearch, drawPanel));

        btnRace = new ImageButton(new ImageIcon("image\\graphPanel\\raceBtn.png"));
        btnRace.setBounds(613, 194, 175, 38);
        graphPanel.add(btnRace);
        btnRace.addActionListener(new RaceButtonListener(tfSearch, drawPanel));

    }
}
