package data;

import country.CountryDAO;

import javax.swing.*;
import java.util.ArrayList;

public class AllCountryTableData extends CountryTableModel {

    public AllCountryTableData(String memberId) {
        super(memberId);
        // TODO Auto-generated constructor stub
        getData();
    }

    @Override
    public void getData() {
        list = new ArrayList<>();
        CountryDAO countryDao = CountryDAO.getInstance();

        for(int i=0; i<countryDao.getTotalNum(); i++) {
            list.add(countryDao.getCountryByID(i+1));
        }
        if(list.size()==0) {
            JOptionPane.showMessageDialog(null, "기록이 없습니다. 문제를 먼저 풀어주세요");
        }
    }
}
