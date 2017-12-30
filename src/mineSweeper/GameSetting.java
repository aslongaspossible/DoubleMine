/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class GameSetting implements ActionListener{
    MyFrame myFrame;
    public void myInit(MyFrame myFrame){
        this.myFrame=myFrame;
    }
    
    public void actionPerformed(ActionEvent e){
        DIYFrame settingFrame=new DIYFrame();
        settingFrame.myInit(myFrame);
        settingFrame.setVisible(true);
    }
}
