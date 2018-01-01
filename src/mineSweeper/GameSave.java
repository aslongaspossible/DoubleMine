/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 *
 * @author Admin
 */
public class GameSave implements ActionListener{
    MyFrame myFrame;
    JFileChooser chooser;
    String initDirection;
    
    public void myInit(MyFrame myFrame){
        this.myFrame=myFrame;
        initDirection="./savings";
        chooser=new JFileChooser(initDirection);
        chooser.setFileFilter(new FileNameExtensionFilter("扫雷存档", "doublemine"));
    }
    
    public void actionPerformed(ActionEvent e){
        boolean isrunning=myFrame.timeTrigger.isRunning();
        myFrame.timeTrigger.stop();
        int returnVal=chooser.showSaveDialog(null);
        if(returnVal==JFileChooser.APPROVE_OPTION){
            try{
                ObjectOutputStream s=new ObjectOutputStream(new FileOutputStream(
                        initDirection+"/"+chooser.getSelectedFile().getName()+".doublemine"));
                s.writeObject(myFrame.minePanel);
                s.close();
            }catch(FileNotFoundException exp){
                System.out.println(exp);
            }catch(IOException exp){
                System.out.println(exp);
            }
        }
        if(isrunning){
            myFrame.timeTrigger.start();
        }
    }
}
