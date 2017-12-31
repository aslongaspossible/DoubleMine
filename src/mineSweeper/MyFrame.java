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
    MinePanel minePanel;
    
    JMenuBar menuBar;
    JMenu gameMenu;
    JMenuItem setGame;
    JMenuItem saveGame;
    JMenuItem openGame;
    
    MouseClick mouseClick;
    GameSetting settingListen;
    GameSave saveListen;
    GameOpen openListen;
    
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
        mouseClick.myInit(minePanel);
        
        settingListen=new GameSetting();
        settingListen.myInit(this);
        setGame.addActionListener(settingListen);
        
        saveListen=new GameSave();
        saveListen.myInit(this);
        saveGame.addActionListener(saveListen);
        
        openListen=new GameOpen();
        openListen.myInit(this);
        openGame.addActionListener(openListen);
    }
     
    public void myResize(int column,int row){
        this.column=column;
        this.row=row;
        setSize(column*size+addWidth,row*size+addHeight);
    }
     
}
