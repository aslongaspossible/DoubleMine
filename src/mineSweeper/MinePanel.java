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
    int column,row;
    int size;
    String[][] words;
    Color black;
    Color white;
    Font myFont;
    
    public void initPanel(int column,int row,int size){
        this.column=column;
        this.row=row;
        this.size=size;
        black = new Color(70,70,70);
        white=new Color(240,240,240);
        myFont=new Font("SanSerif",Font.BOLD,size);
        ifOpen=new boolean[row][];
        words=new String[row][];
        for(int r=0;r<row;++r){
            ifOpen[r]=new boolean[column];
            words[r]=new String[column];
            for(int c=0;c<column;++c){
                ifOpen[r][c]=false;
                words[r][c]="";
            }
        }
    }
    
    public void paint(Graphics g){
        super.paint(g);
        g.setFont(myFont);
        for(int r=0;r<row;++r){
            for(int c=0;c<column;++c){
                g.setColor(black);
                if(ifOpen[r][c]){
                    g.drawString(words[r][c], c, c);
                    g.setColor(white);
                }
                g.fillRoundRect(size*c, size*r, size-1, size-1, size/2, size/2);
            }
        }
    }
    
    
    
}
