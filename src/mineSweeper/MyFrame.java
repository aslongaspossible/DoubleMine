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
    JMenuBar menuBar;
    
    
    
    public MyFrame(){
        initComponents();
    }
    
     private void initComponents() {
        Container contentPane=getContentPane();
        contentPane.setLayout(mgr);

    }    
}
