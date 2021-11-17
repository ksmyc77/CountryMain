package UI;

import data.CountryTableModel;
import data.FileData;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SearchPanel extends ImagePanel implements ItemListener{

    /**
     * Create the panel.
     */
    //컴포넌트
    private ImageButton btnGraph;
    private JLabel lbSearch;
    private JTextField tfSearch;
    private ImageButton btnHome;
    private ImageButton btnFileOut;
    private JPanel tablePanel;
    private JScrollPane scrollPane;
    private JTableHeader header;
    private JCheckBox[] checkBoxes = new JCheckBox[5];
    
    //데이터
    private TableRowSorter<AbstractTableModel> tableSorter;
    private JTable table;
    private CountryTableModel tableModel;
    private FileData saveFile;
    private String searchData;
    private ArrayList<Integer> columnChecked = new ArrayList<Integer>();

    //파라메터: 배경이미지, 멤버ID, 퀴즈종료 후 돌아갈 들어갈 결과페이지, 화면전환을 위한 생성된 퀴즈패널 빼열
    public SearchPanel(Image image, ImagePanel graphPanel, ImagePanel menuPanel, JFrame frame) {
        super(image);
        btnGraph = new ImageButton(new ImageIcon("image\\SearchPanel\\graphBtn.png"));
        btnGraph.setBounds(1144, 71, 288, 86);
        add(btnGraph);
        btnGraph.addActionListener(e -> {
            //그래프 패널로 이동
            setVisible(false);
            graphPanel.setVisible(true);
        });

        lbSearch = new JLabel("SEARCH");
        lbSearch.setHorizontalAlignment(SwingConstants.CENTER);
        lbSearch.setForeground(Color.WHITE);
        lbSearch.setFont(new Font("HY견고딕", Font.BOLD, 15));
        lbSearch.setBounds(79, 112, 103, 45);
        add(lbSearch);

        tfSearch = new JTextField();
        tfSearch.setBounds(210, 112, 342, 45);
        add(tfSearch);
        tfSearch.setBorder(null);
        tfSearch.setColumns(10);
        //체크박스 중 list에 있는 항목들을 사용해서 rowFilter을 적용한다.
        //list의 사이즈에 따라 항목의 개수가 다르므로, 다른 생성자를 호출한다.
        tfSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                searchData = tfSearch.getText();
                tableSorter = new TableRowSorter<>(tableModel);
                table.setRowSorter(tableSorter);
                int numCheckedBox = columnChecked.size();
                
                //size가 0일 때: 모든 항목을 사용
                if(numCheckedBox==0)
                    tableSorter.setRowFilter(RowFilter.regexFilter(searchData));
                //size가 1일 때: 리스트의 첫번째 요소(인덱스)항목을 사용
                else if(numCheckedBox==1) {
                    tableSorter.setRowFilter(RowFilter.regexFilter(searchData,columnChecked.get(0)));
                }
                //size가 2일 때: 리스트의 첫번째 요소(인덱스), 두번째 요소(인덱스) 항목을 사용
                else if(numCheckedBox==2) {
                    tableSorter.setRowFilter(RowFilter.regexFilter(searchData,columnChecked.get(0),columnChecked.get(1)));
                }
                //size가 3일 때
                else if(numCheckedBox==3) {
                    tableSorter.setRowFilter(RowFilter.regexFilter(searchData,columnChecked.get(0),columnChecked.get(1),columnChecked.get(2)));
                }
                //size가 4일 때
                else if(numCheckedBox==4) {
                    tableSorter.setRowFilter(RowFilter.regexFilter(searchData,columnChecked.get(0),columnChecked.get(1),columnChecked.get(2),columnChecked.get(3)));
                }
                //size가 5일 때: 0~4까지의 요소 = 모든 항목(인덱스)를 사용
                else if(numCheckedBox==5)
                    tableSorter.setRowFilter(RowFilter.regexFilter(searchData,columnChecked.get(0),columnChecked.get(1),columnChecked.get(2),columnChecked.get(3),columnChecked.get(4)));

            }
        });

        //체크 박스 초기화
        checkBoxes[0] = new JCheckBox("이름");
        checkBoxes[1] = new JCheckBox("국가코드");
        checkBoxes[2] = new JCheckBox("수도");
        checkBoxes[3] = new JCheckBox("위치");
        checkBoxes[4] = new JCheckBox("주요도시");

        //체크박스 UI 설정
        for(int i =0; i<checkBoxes.length; i++){
            checkBoxes[i].setBounds(i*125+91, 185, 115, 23);
            checkBoxes[i].setForeground(Color.WHITE);
            checkBoxes[i].setSelected(false);
            checkBoxes[i].addItemListener(this);
            checkBoxes[i].setContentAreaFilled(false);
            checkBoxes[i].setBorder(null);
            checkBoxes[i].setFont(new Font("HY견고딕", Font.PLAIN, 12));
            add(checkBoxes[i]);
        }


        //홈버튼
        btnHome = new ImageButton(new ImageIcon("image\\SearchPanel\\homeBtn.png"));
        btnHome.setBounds(834, 743, 232, 72);
        add(btnHome);
        btnHome.addActionListener(e -> {
            //홈으로 이동
            menuPanel.setVisible(true);
            setVisible(false);
        });

        //파일 출력 버튼
        btnFileOut = new ImageButton(new ImageIcon("image\\SearchPanel\\fileBtn.png"));
        btnFileOut.setBounds(1112, 744, 232, 72);
        add(btnFileOut);
        btnFileOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //csv 파일로 저장
                saveFile = new FileData(frame, tableModel.getList());
                saveFile.save();
            }
        });

        tablePanel = new JPanel();
        tablePanel.setBounds(54, 232, 1430, 481);
        tablePanel.setOpaque(false);
        add(tablePanel);

        table = new JTable();
        table.setBounds(80, 250, 1380, 450);
        table.setRowHeight(30);
        table.setFont(new Font("굴림", Font.BOLD, 16));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(80, 250, 1380, 450);
        add(scrollPane);

        header = table.getTableHeader();
        header.setBackground(Color.DARK_GRAY);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("굴림", Font.BOLD, 18));
    }

    public void setTableModel(CountryTableModel model){
        tableModel = model;
        table.setModel(model);
    }

    //체크박스가 체크되면 해당 index를 list에 저장한다.
    //해당 항목이 이미 체크가 되어있는 항목이라면 list에서 제거
    //그렇지 않다면 항목에 추가
    @Override
    public void itemStateChanged(ItemEvent e) {
        //체크된 항목
        JCheckBox checked = (JCheckBox) e.getSource();
        //체크박스 인덱스
        int index = -1;
        //항목체크 변수
        boolean check = true;

        for(int i =0; i<checkBoxes.length; i++){
            if(checked == checkBoxes[i]){
                index = i;
            }
        }

        Iterator iter = columnChecked.iterator();
        while(iter.hasNext()){
            if(iter.next().equals(index)){
                check = false;
                iter.remove();
            }
        }

        if(check){
            columnChecked.add(index);
        }
    }

}

