/*
 * @author: Luddite
 * dingsf6@gmail.com
 * 
 */
package mineSweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Admin
 */
public class GameOpen  implements ActionListener{
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
        myFrame.timeTrigger.stop();
        int returnVal=chooser.showOpenDialog(null);
        if(returnVal==JFileChooser.APPROVE_OPTION){
            try{
                ObjectInputStream s=new ObjectInputStream(new FileInputStream(
                        initDirection+"/"+chooser.getSelectedFile().getName()));
                MinePanel savedPanel=(MinePanel)s.readObject();
                myFrame.minePanel.myReset(savedPanel);
                myFrame.myResize(myFrame.minePanel.column,myFrame.minePanel.row);
                myFrame.minePanel.repaint();
                myFrame.timeLabel.setText(myFrame.minePanel.time);
                s.close();
            }catch(FileNotFoundException exp){
                System.out.println(exp);
            }catch(IOException exp){
                System.out.println(exp);
            }catch(ClassNotFoundException exp){
                System.out.println(exp);
            }
        }
        myFrame.timeTrigger.start();
    }
    
}
