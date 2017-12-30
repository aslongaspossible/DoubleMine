/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


/**
 *
 * @author Luddite
 */
public class MyFrame extends JFrame{
    int column,row;
    int size;
    int booms;
    int addWidth,addHeight;
    JMenuBar menuBar;
    MinePanel minePanel;
    JMenu gameMenu;
    JMenuItem newGame;
    JMenuItem setGame;
    MouseClick mouseClick;
    GameSetting settingListen;
    
    public MyFrame(){
        myInit();
    }
    
     private void myInit() {
        Container contentPane=getContentPane();
        addWidth=16;
        addHeight=62;
        column=20;
        row=10;
        size=50;//can't be smaller than 5 because of line 39 in MinePanel
        booms=30;
        setSize(column*size+addWidth,row*size+addHeight);
        
        menuBar=new JMenuBar();
        gameMenu=new JMenu("开始");
        newGame=new JMenuItem("新游戏");
        setGame=new JMenuItem("自定义");
        settingListen=new GameSetting();
        gameMenu.add(newGame);
        gameMenu.add(setGame);
        menuBar.add(gameMenu);
        setJMenuBar(menuBar);
        
        minePanel=new MinePanel();
        minePanel.setSize(column*size,row*size);
        minePanel.initPanel(column,row,booms,size,2);
        contentPane.add(minePanel);
        
        mouseClick = new MouseClick();
        mouseClick.myInit(minePanel);
        
        settingListen.myInit(this);
        setGame.addActionListener(settingListen);
        
    }
     
     
     
}
