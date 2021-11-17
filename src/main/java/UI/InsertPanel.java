package UI;

import javax.swing.*;

import country.CountryDAO;
import country.CountryDTO;

import java.awt.*;


public class InsertPanel extends ImagePanel {

    ImageButton btnHome;
    ImageButton btnInsert;

    private JTextField tfName;
    private JTextField tfReligion;
    private JTextField tfWeather;
    private JTextField tfMainCity;
    private JTextField tfMedia;
    private JTextField tfLocation;
    private JTextField tfRace;
    private JTextField tfCode;
    private JTextField tfLanguage;
    private JTextField tfCapital;
    private JTextField tfArea;
    private JTextField tfAreaExplain;
    private JTextField tfAreaSource;
    private JTextField tfBaseYear;

    private JLabel lbName;
    private JLabel lbReligion;
    private JLabel lbWeather;
    private JLabel lbMainCity;
    private JLabel lbMedia;
    private JLabel lbLocation;
    private JLabel lbRace;
    private JLabel lbCode;
    private JLabel lbLanguage;
    private JLabel lbCapital;
    private JLabel lbArea;
    private JLabel lbAreaExplain;
    private JLabel lbAreaSource;
    private JLabel lbBaseYear;

    private String name; 
    private String code; 
    private String capital;
    private String weather;
    private String location;
    private String maincity;
    private String religion;
    private String race;
    private String media;
    private String area;
    private String areaSource;
    private String areaExplain;
    private String language;
    private int baseYear;

	CountryDAO dao;
	CountryDTO TargetCountry;
    
    @SuppressWarnings("null")
	public InsertPanel(Image image, ImagePanel menuPanel){
        super(image);
        initComponent();

        btnInsert = new ImageButton(new ImageIcon("image\\updatePanel\\insert\\insertBtn.png"));
        btnInsert.setBounds(533, 124, 230, 79);
        add(btnInsert);
        //확인 버튼 클릭 시 -> db에 추가되는 작업을 여기서 진행한다.
        dao = CountryDAO.getInstance();
        
        btnInsert.addActionListener(e -> {
        	name = tfName.getText();
        	if(name.equals("")) {
				JOptionPane.showMessageDialog(null, "국가 이름을 입력하세요");
				}
			else {
				code = tfCode.getText();
				capital = tfCapital.getText();
				weather = tfWeather.getText();
				location = tfLocation.getText();
				maincity = tfMainCity.getText();
				religion = tfReligion.getText();
				race = tfRace.getText();
				media = tfMedia.getText();
				area = tfArea.getText();
				areaSource = tfAreaSource.getText();
				areaExplain = tfAreaExplain.getText();
				language = tfLanguage.getText();
				if(tfBaseYear.getText().equals(""))
				{	baseYear = 0;	}
				else
				{	baseYear = Integer.parseInt(tfBaseYear.getText());	}
				
				TargetCountry = new CountryDTO(0,name, code, capital, weather, location, 
						maincity, religion, race, media, area, areaSource, areaExplain, language, baseYear);
			
				if(dao.insertCountry(TargetCountry))
				{System.out.println("작업 완료");}
				else
				{
					System.out.println("작업 실패");
				}
			}
        });


        btnHome = new ImageButton(new ImageIcon("image\\updatePanel\\insert\\homeBtn.png"));
        btnHome.setBounds(1191, 97, 226, 75);
        add(btnHome);
        //메뉴로 돌아간다.
        btnHome.addActionListener(e -> {
            setVisible(false);
            menuPanel.setVisible(true);
        });
    }
    public void initComponent(){
        tfName = new JTextField();
        tfName.setBounds(168, 136, 323, 53);
        this.add(tfName);
        tfName.setColumns(10);
        tfName.setBorder(null);

        tfReligion = new JTextField();
        tfReligion.setColumns(10);
        tfReligion.setBounds(215, 495, 391, 43);
        this.add(tfReligion);
        tfReligion.setBorder(null);

        tfWeather = new JTextField();
        tfWeather.setColumns(10);
        tfWeather.setBounds(215, 594, 391, 43);
        this.add(tfWeather);
        tfWeather.setBorder(null);

        tfMainCity = new JTextField();
        tfMainCity.setColumns(10);
        tfMainCity.setBounds(215, 696, 391, 43);
        this.add(tfMainCity);
        tfMainCity.setBorder(null);

        tfMedia = new JTextField();
        tfMedia.setColumns(10);
        tfMedia.setBounds(979, 495, 391, 43);
        this.add(tfMedia);
        tfMedia.setBorder(null);

        tfLocation = new JTextField();
        tfLocation.setColumns(10);
        tfLocation.setBounds(979, 594, 391, 43);
        this.add(tfLocation);
        tfLocation.setBorder(null);

        tfRace = new JTextField();
        tfRace.setColumns(10);
        tfRace.setBounds(979, 696, 391, 43);
        this.add(tfRace);
        tfRace.setBorder(null);

        tfCode = new JTextField();
        tfCode.setBounds(209, 311, 138, 43);
        this.add(tfCode);
        tfCode.setColumns(10);
        tfCode.setBorder(null);

        tfLanguage = new JTextField();
        tfLanguage.setColumns(10);
        tfLanguage.setBounds(209, 402, 397, 43);
        this.add(tfLanguage);
        tfLanguage.setBorder(null);

        tfCapital = new JTextField();
        tfCapital.setColumns(10);
        tfCapital.setBounds(460, 311, 138, 43);
        this.add(tfCapital);
        tfCapital.setBorder(null);

        tfArea = new JTextField();
        tfArea.setColumns(10);
        tfArea.setBounds(979, 311, 138, 43);
        this.add(tfArea);
        tfArea.setBorder(null);

        tfAreaExplain = new JTextField();
        tfAreaExplain.setColumns(10);
        tfAreaExplain.setBounds(979, 402, 138, 43);
        this.add(tfAreaExplain);
        tfAreaExplain.setBorder(null);

        tfAreaSource = new JTextField();
        tfAreaSource.setColumns(10);
        tfAreaSource.setBounds(1232, 311, 138, 43);
        this.add(tfAreaSource);
        tfAreaSource.setBorder(null);

        tfBaseYear = new JTextField();
        tfBaseYear.setColumns(10);
        tfBaseYear.setBounds(1232, 402, 138, 43);
        this.add(tfBaseYear);
        tfBaseYear.setBorder(null);

        lbCode = new JLabel("국가코드");
        lbCode.setBounds(126, 311, 85, 43);
        lbCode.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbCode.setForeground(Color.WHITE);
        this.add(lbCode);

        lbCapital = new JLabel("수도");
        lbCapital.setBounds(383, 311, 85, 43);
        lbCapital.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbCapital.setForeground(Color.WHITE);
        this.add(lbCapital);

        lbLanguage = new JLabel("언어");
        lbLanguage.setBounds(126, 402, 85, 43);
        lbLanguage.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbLanguage.setForeground(Color.WHITE);
        this.add(lbLanguage);

        lbReligion = new JLabel("종교");
        lbReligion.setBounds(126, 495, 85, 43);
        lbReligion.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbReligion.setForeground(Color.WHITE);
        this.add(lbReligion);

        lbWeather = new JLabel("기후");
        lbWeather.setBounds(126, 594, 85, 43);
        lbWeather.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbWeather.setForeground(Color.WHITE);
        this.add(lbWeather);

        lbMainCity = new JLabel("주요도시");
        lbMainCity.setBounds(126, 696, 85, 43);
        lbMainCity.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbMainCity.setForeground(Color.WHITE);
        this.add(lbMainCity);

        lbArea = new JLabel("면적");
        lbArea.setBounds(890, 331, 85, 43);
        lbArea.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbArea.setForeground(Color.WHITE);
        this.add(lbArea);

        lbAreaSource = new JLabel("면적출처");
        lbAreaSource.setBounds(1155, 311, 85, 43);
        lbAreaSource.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbAreaSource.setForeground(Color.WHITE);
        this.add(lbAreaSource);

        lbBaseYear = new JLabel("기준년도");
        lbBaseYear.setBounds(1155, 402, 85, 43);
        lbBaseYear.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbBaseYear.setForeground(Color.WHITE);
        this.add(lbBaseYear);

        lbAreaExplain = new JLabel("면적설명");
        lbAreaExplain.setBounds(890, 402, 85, 43);
        lbAreaExplain.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbAreaExplain.setForeground(Color.WHITE);
        this.add(lbAreaExplain);

        lbMedia = new JLabel("언론");
        lbMedia.setBounds(890, 495, 85, 43);
        lbMedia.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbMedia.setForeground(Color.WHITE);
        this.add(lbMedia);

        lbLocation = new JLabel("위치");
        lbLocation.setBounds(890, 594, 85, 43);
        lbLocation.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbLocation.setForeground(Color.WHITE);
        this.add(lbLocation);

        lbRace = new JLabel("주요민족");
        lbRace.setBounds(890, 696, 85, 43);
        lbRace.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbRace.setForeground(Color.WHITE);
        this.add(lbRace);

        lbName = new JLabel("추가할 나라를 입력하세요");
        lbName.setBounds(189, 63, 323, 43);
        lbName.setFont(new Font("HY견고딕", Font.PLAIN, 15));
        lbName.setForeground(Color.WHITE);
        this.add(lbName);

    }
}
