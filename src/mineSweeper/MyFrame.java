/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;


/**
 *
 * @author Luddite
 */

class MyTimer implements ActionListener{//<editor-fold>
    MyFrame myFrame;
    Calendar calendar;
    Date date;
    SimpleDateFormat sdf;
    
    public MyTimer(){
        super();
        calendar=Calendar.getInstance();
        sdf=new SimpleDateFormat("HH:mm:ss");
    }
    
    public void myInit(MyFrame myFrame){
        this.myFrame=myFrame;
    }
    
    public void actionPerformed(ActionEvent e){
        try{
            date=sdf.parse(myFrame.timeLabel.getText());
            calendar.setTime(date);
            calendar.add(Calendar.SECOND, 1);
            myFrame.minePanel.time=sdf.format(calendar.getTime());
            myFrame.timeLabel.setText(myFrame.minePanel.time);
        }catch(ParseException exp){
            System.out.println(exp);
        }
    }
//</editor-fold>
}

public class MyFrame extends JFrame{
    //<editor-fold>
    int column,row;
    int size;
    int booms;
    int addWidth,addHeight;
    MinePanel minePanel;
    
    JMenuBar menuBar;
    JMenu gameMenu;
    JMenuItem setGame;
    JMenuItem saveGame;
    JMenuItem openGame;
    
    JLabel timeLabel;
    Timer timeTrigger;
    JLabel boomsRemain;
    
    
    MouseClick mouseClick;
    GameSetting settingListen;
    GameSave saveListen;
    GameOpen openListen;
    MyTimer timeListen;
    //</editor-fold>
    
    public MyFrame(){
        super();
        myInit();
    }
    
    private void myInit() {
        Container contentPane=getContentPane();
        addWidth=16;
        addHeight=62;
        column=15;
        row=10;
        size=50;//can't be smaller than 5 because of line 39 in MinePanel
        booms=30;
        setSize(column*size+addWidth,row*size+addHeight);
        
        menuBar=new JMenuBar();
        gameMenu=new JMenu("开始");
        saveGame=new JMenuItem("存档");
        setGame=new JMenuItem("自定义");
        openGame=new JMenuItem("恢复存档");
        gameMenu.add(saveGame);
        gameMenu.add(openGame);
        gameMenu.add(setGame);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
        
        minePanel=new MinePanel();
        minePanel.setSize(column*size,row*size);
        minePanel.initPanel(column,row,booms,size,1);
        contentPane.add(minePanel);
        
        mouseClick = new MouseClick();
        mouseClick.myInit(this);
        
        timeLabel=new JLabel();
        timeLabel.setText("00:00:00");
        menuBar.add(timeLabel);
        
        boomsRemain=new JLabel();
        boomsRemain.setText("还剩"+(minePanel.booms-minePanel.flags)+"个雷");
        menuBar.add(boomsRemain);
        
        settingListen=new GameSetting();
        settingListen.myInit(this);
        setGame.addActionListener(settingListen);
        
        saveListen=new GameSave();
        saveListen.myInit(this);
        saveGame.addActionListener(saveListen);
        
        openListen=new GameOpen();
        openListen.myInit(this);
        openGame.addActionListener(openListen);
        
        timeListen=new MyTimer();
        timeListen.myInit(this);
        timeTrigger=new Timer(1000,timeListen);
    }
     
    public void myResize(int column,int row,int size){
        this.column=column;
        this.row=row;
        this.size=size;
        setSize(column*size+addWidth,row*size+addHeight);
    }
     
}
