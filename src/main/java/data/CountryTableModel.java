package data;

import country.CountryDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class CountryTableModel extends AbstractTableModel {

    protected List<CountryDTO> list;
    protected String memberId;
    private static String[] header = {"이름", "국가코드", "수도", "위치", "주요도시"};

    public CountryTableModel(String memberId) {
        // TODO Auto-generated constructor stub
        this.memberId = memberId;
        getData();
    }

    @Override
    public String getColumnName(int cell) {
        return header[cell];
    }

    public List<CountryDTO> getList(){
        return list;
    }

    /*NEDD OVERRIDE*/
    public void getData() {
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getName();
            case 1:
                return list.get(rowIndex).getCode();
            case 2:
                return list.get(rowIndex).getCapital();
            case 3:
                return list.get(rowIndex).getLocation();
            case 4:
                return list.get(rowIndex).getMainCity();
        }
        return null;
    }
}
