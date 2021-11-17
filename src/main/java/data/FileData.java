package data;

import country.CountryDTO;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class FileData {

    String filePath;
    File file =null;
    BufferedWriter bw = null;
    private static final String NEXTLINE = System.lineSeparator();
    private static final char UTF_8_WITHOUT_BOM = '\ufeff';
    List<CountryDTO> dataList;

    public FileData(JFrame frame, List<CountryDTO> list){
        //파일 다이얼로그에서 사용자에게 저장할 파일 위치와 파일 이름을 받아온다.
        FileDialog fd = new FileDialog(frame, "file select", FileDialog.SAVE);
        fd.setDirectory(".");   // .은 지금폴더
        fd.setVisible(true);
        dataList = list;
        filePath = fd.getDirectory() + fd.getFile() + ".csv";

    }

    public void save(){
        try{
            //파일 경로 설정
            file = new File(filePath);
            //BufferWriter에 파일 입력
            bw = new BufferedWriter(new FileWriter(file));
            //인코딩 설정
            bw.write(UTF_8_WITHOUT_BOM);
            //첫행은 항목
            bw.write("국가,국가코드,수도,위치,주요도시");
            bw.write(NEXTLINE);
            for(int i =0; i<dataList.size(); i++){
                //두번째 행부터 데이터 넣기
                //넣기 전에 ,를 제거한 가공한 데이터 입력
                bw.write(dataList.get(i).getName() +"," + dataList.get(i).getCode() + "," + removeComma(dataList.get(i).getCapital()) + "," +
                        removeComma(dataList.get(i).getLocation())+"," + removeComma(dataList.get(i).getMainCity()));
                bw.write(NEXTLINE);
                //항목마다 flush해준다.
                bw.flush();
            }
            JOptionPane.showMessageDialog(null, "저장 완료");
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "저장 실패");
        }
    }
    //csv 파일은 콤마를 기준으로 하나의 데이터로 인식된다
    //기존 csv파일의 하나의 데이터 안에 ,가 들어있는 데이터들이 존재해서 이를 제거해주는 작업을 한다.
    public String removeComma(String data){
        String[] splitData = data.split(",");
        String commaRemovedData ="";
        for(int i =0; i<splitData.length; i++){
            commaRemovedData = commaRemovedData +" " +splitData[i];
        }
        return commaRemovedData;
    }
}
