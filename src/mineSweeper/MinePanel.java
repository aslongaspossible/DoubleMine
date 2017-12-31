/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper;

import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.io.Serializable;

/**
 *
 * @author Luddite
 */

public class MinePanel extends JPanel implements Serializable{
    //<editor-fold>
    boolean[][] ifOpen,ifBoom,ifFlag;//是否打开、是否是雷、是否有旗
    int[][] count;//近邻的雷数
    boolean isFirst;//是否是第一次点击
    int column,row;//行列
    int booms,flags;//雷、旗的数目
    int order;//近邻级数
    int unopenArea;//未打开的格子数
    String time;
    
    int squareSize;//格子边长
    int addHeight;//竖直偏移量
    Color black,white,yellow,green;//预设颜色
    Font myFont;//预设字体
    //</editor-fold>
    
    public MinePanel(){
        super();
        black = new Color(70,70,70);
        white=new Color(240,240,240);
        yellow=new Color(240,240,100);
        green=new Color(50,240,100);
        addHeight=40;
    }
    
    public void initPanel(int column,int row,int booms,int size,int order){
        this.column=column;
        this.row=row;
        this.squareSize=size;
        this.order=order;
        
        unopenArea=column*row;
        if(booms<unopenArea){
            this.booms=booms;
        }else{
            this.booms=unopenArea-1;
        }
        
        myFont=new Font("SanSerif",Font.BOLD,size*2/3);
        
        time="00:00:00";
        flags=0;
        isFirst=true;
        
        ifOpen=new boolean[row][];
        ifBoom=new boolean[row][];
        ifFlag=new boolean[row][];
        count=new int[row][];
        
        for(int r=0;r<row;++r){
            ifOpen[r]=new boolean[column];
            count[r]=new int[column];
            ifBoom[r]=new boolean[column];
            ifFlag[r]=new boolean[column];
            for(int c=0;c<column;++c){
                ifOpen[r][c]=false;
                count[r][c]=0;
                ifBoom[r][c]=false;
                ifFlag[r][c]=false;
            }
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setFont(myFont);
        for(int r=0;r<row;++r){
            for(int c=0;c<column;++c){
                if(ifOpen[r][c]){
                    g.setColor(yellow);
                    g.fillRoundRect(squareSize*c, squareSize*r, squareSize-1, squareSize-1, squareSize/2, squareSize/2);
                    g.setColor(green);
                    if(ifBoom[r][c]){
                        g.drawString(" *", squareSize*c, squareSize*r+addHeight);
                    }else{
                        g.drawString(count[r][c]+"", squareSize*c, squareSize*r+addHeight);
                    }
                }else{
                    g.setColor(black);
                    g.fillRoundRect(squareSize*c, squareSize*r, squareSize-1, squareSize-1, squareSize/2, squareSize/2);
                    if(ifFlag[r][c]){
                        g.setColor(white);
                        g.drawString(" *", squareSize*c, squareSize*r+addHeight);
                    }
                }
            }
        }
    }
    
    public void myReset(MinePanel savedPanel){
        isFirst=savedPanel.isFirst;
        column=savedPanel.column;
        row=savedPanel.row;
        order=savedPanel.order;
        
        booms=savedPanel.booms;
        unopenArea=savedPanel.unopenArea;
        flags=savedPanel.flags;
        
        time=savedPanel.time;
        
        ifOpen=savedPanel.ifOpen;
        ifBoom=savedPanel.ifBoom;
        ifFlag=savedPanel.ifFlag;
        count=savedPanel.count;
        
        squareSize=savedPanel.squareSize;
        myFont=savedPanel.myFont;
    }
     
}