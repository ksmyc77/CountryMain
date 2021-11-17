package data;

import UI.GraphDrawingPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReligionButtonListener implements ActionListener {

    public JTextField countryNameField;    // 들어오는 국가의 이름 텍스트필드
    public GraphDrawingPanel drawPanel;    // 구체적으로 그래프를 그리는 객체
    public int ButtonType = 1;


    ReligionButtonListener(JTextField textField, GraphDrawingPanel drawPanel) {
        this.countryNameField = textField;
        this.drawPanel = drawPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}