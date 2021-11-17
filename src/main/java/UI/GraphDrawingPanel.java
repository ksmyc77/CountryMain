package UI;

import country.CountryDAO;
import country.CountryDTO;

import javax.swing.*;

public class GraphDrawingPanel extends JPanel {
    //dao 호출 참고
    CountryDAO dao = CountryDAO.getInstance();

    public GraphDrawingPanel(){
        //나라 이름으로 정보 얻기(예시)
        CountryDTO CountryData = dao.getCountryByName("가나");

    }
}
