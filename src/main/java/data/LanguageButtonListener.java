package data;


import UI.GraphDrawingPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LanguageButtonListener implements ActionListener {


    public JTextField countryNameField;	// 들어오는 국가의 이름 텍스트필드
    public GraphDrawingPanel drawPanel;	// 구체적으로 그래프를 그리는 객체
    public int ButtonType = 0;


    public LanguageButtonListener(JTextField textField, GraphDrawingPanel drawPanel){
        this.countryNameField = textField;
        this.drawPanel = drawPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
