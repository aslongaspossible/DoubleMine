/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper;
import javax.swing.*;
import java.lang.*;
import java.awt.*;

/**
 *
 * @author Luddite
 */
public class MinePanel extends JPanel{
    boolean[][] ifOpen;
    boolean[][] ifBoom;
    boolean isFirst;
    int column,row;
    int squareSize;
    int booms;
    int order;
    int addHeight;
    int[][] count;
    Color black,white,yellow,green;
    Font myFont;
    
    public void initPanel(int column,int row,int booms,int size,int order){
        this.column=column;
        this.row=row;
        this.booms=booms;
        this.squareSize=size;
        this.order=order;
        addHeight=40;
        black = new Color(70,70,70);
        white=new Color(240,240,240);
        yellow=new Color(240,240,100);
        green=new Color(50,240,100);
        myFont=new Font("SanSerif",Font.BOLD,size-5);
        isFirst=true;
        ifOpen=new boolean[row][];
        count=new int[row][];
        ifBoom=new boolean[row][];
        for(int r=0;r<row;++r){
            ifOpen[r]=new boolean[column];
            count[r]=new int[column];
            ifBoom[r]=new boolean[column];
            for(int c=0;c<column;++c){
                ifOpen[r][c]=false;
                count[r][c]=0;
                ifBoom[r][c]=false;
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
                        g.drawString("*", squareSize*c, squareSize*r+addHeight);
                    }else{
                        g.drawString(count[r][c]+"", squareSize*c, squareSize*r+addHeight);
                    }
                }else{
                    g.setColor(black);
                    g.fillRoundRect(squareSize*c, squareSize*r, squareSize-1, squareSize-1, squareSize/2, squareSize/2);
                }
            }
        }
        
    }
    
    
    
}
