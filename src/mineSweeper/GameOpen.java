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
        boolean isrunning=myFrame.timeTrigger.isRunning();
        myFrame.timeTrigger.stop();
        int returnVal=chooser.showOpenDialog(null);
        if(returnVal==JFileChooser.APPROVE_OPTION){
            try{
                ObjectInputStream s=new ObjectInputStream(new FileInputStream(
                        initDirection+"/"+chooser.getSelectedFile().getName()));
                MinePanel savedPanel=(MinePanel)s.readObject();
                myFrame.minePanel.myReset(savedPanel);
                myFrame.myResize(myFrame.minePanel.column,
                        myFrame.minePanel.row,myFrame.minePanel.squareSize);
                myFrame.minePanel.repaint();
                myFrame.timeLabel.setText(myFrame.minePanel.time);
                myFrame.boomsRemain.setText("还剩"+
                        (myFrame.minePanel.booms-myFrame.minePanel.flags)+"个雷");
                s.close();
                myFrame.timeTrigger.start();
            }catch(FileNotFoundException exp){
                System.out.println(exp);
            }catch(IOException exp){
                System.out.println(exp);
            }catch(ClassNotFoundException exp){
                System.out.println(exp);
            }
        }
        if(isrunning){
            myFrame.timeTrigger.start();
        }
    }
    
}
