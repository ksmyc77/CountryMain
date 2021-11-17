package UI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ToolBar {

    public JMenuBar mb;
    private JFrame frame;

    public ToolBar(JFrame frame)
    {
        mb = new JMenuBar();
        this.frame = frame;
        createMenu();
    }


    private void createMenu()
    {
        JMenuItem [] menuItem = new JMenuItem [5];
        String[] itemTitle = {"이동", "최소화", "최대화", "도움말", "닫기"};

        JMenuItem [] menuItem_2 = new JMenuItem [4];
        String[] itemTitle_2 = {"로그인", "문제풀기", "오답노트", "나라검색"};

        JMenu menu_1 = new JMenu("시스템(S)");
        JMenu menu_2 = new JMenu("게임(G)");

        MenuActionListener listener = new MenuActionListener();

        for(int i=0; i<menuItem.length; i++)
        {
            menuItem[i] = new JMenuItem(itemTitle[i]);
            menuItem[i].addActionListener(listener);
            menu_1.add(menuItem[i]);
        }

        for(int i=0; i<menuItem_2.length; i++)
        {
            menuItem_2[i] = new JMenuItem(itemTitle_2[i]);
            menuItem_2[i].addActionListener(listener);
            menu_2.add(menuItem_2[i]);
        }

        mb.add(menu_1);
        mb.add(menu_2);
    }

    class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch(cmd)
            {
                case "이동" :
                    break;
                case "최소화" :
                    frame.setSize(350,250);
                    break;
                case "최대화" :
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    break;
                case "도움말" :
                    JFrame helpscreen = new JFrame();
                    helpscreen.setTitle("도움말");
                    helpscreen.setSize(400,70);
                    helpscreen.setVisible(true);
                    JLabel helplabel = new JLabel("이 프로그램은 고급 객체지향 프로그래밍 5조가 만든 프로그램 입니다");
                    helpscreen.add(helplabel);
                    break;
                case "닫기" :
                    System.exit(0);
                    break;
                case "로그인" :
                    //로그인 창으로 이동
                    System.exit(0);//미구현상태 추가할것
                    break;
                case "문제풀기" :
                    //문제 풀기 창으로 이동
                    System.exit(0);//미구현상태 추가할것
                    break;
                case "오답노트" :
                    //오답노트 창으로 이동
                    System.exit(0);//미구현상태 추가할것
                    break;
                case "나라검색" :
                    //나라 검색 창으로 이동
                    System.exit(0);//미구현상태 추가할것
                    break;
            }
        }
    }
}