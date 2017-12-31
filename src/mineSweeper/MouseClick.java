/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineSweeper;
import java.awt.event.*; //载入MouseAdapter所在的包
import javax.swing.*; 
import java.util.Random;
import java.util.Stack; 

/**
 *
 * @author Admin
 */

class MyPoint {//<editor-fold>
    int x,y;
    public MyPoint(int x,int y){
        this.x=x;
        this.y=y;
    }
//</editor-fold>
}

public class MouseClick extends MouseAdapter{
    MinePanel minePanel;
    MyFrame myFrame;
    Random random;
    Stack<MyPoint> stackPoint;
    //boolean ifDown;
    
    public MouseClick(){
        super();
        random=new Random();
        stackPoint=new Stack<MyPoint>();
    }
    
    public void myInit(MyFrame myFrame){
        this.myFrame=myFrame;
        minePanel=myFrame.minePanel;
        minePanel.addMouseListener(this);
    }
    
    @SuppressWarnings("empty-statement")
    public void mouseReleased(MouseEvent e){
        int clickX=e.getX()/minePanel.squareSize;
        int clickY=e.getY()/minePanel.squareSize;
        int row=minePanel.row;
        int column=minePanel.column;
        if(e.getButton()==e.BUTTON1){//<editor-fold>
            if(minePanel.isFirst){//<editor-fold>
                minePanel.isFirst=false;
                myFrame.timeTrigger.start();
                int booms=minePanel.booms;
                int area=row*column-1;
                int order=minePanel.order;
                for(int r=0;r<row;++r){
                    for(int c=0;c<column;++c){
                        if(r!=clickY || c!=clickX){
                            if(random.nextInt(area)<booms){
                                minePanel.ifBoom[r][c]=true;
                                for(int y=r-order;y<=r+order;++y){
                                    for(int x=c-order;x<=c+order;++x){
                                        if(x>=0&&y>=0&&x<column&&y<row){
                                            ++minePanel.count[y][x];
                                        }
                                    }
                                }
                                --booms;
                            }
                            --area;
                        }
                        System.out.print(minePanel.ifBoom[r][c]+"\t");
                    }
                    System.out.println();
                }//</editor-fold>
            }
            if(!minePanel.ifFlag[clickY][clickX]){
                if(minePanel.ifBoom[clickY][clickX]){
                    for(int r=0;r<minePanel.row;++r){
                        for(int c=0;c<minePanel.column;++c){
                            if(minePanel.ifBoom[r][c]){
                                minePanel.ifOpen[r][c]=true;
                            }
                        }
                    }
                    minePanel.repaint();
                    myFrame.timeTrigger.stop();
                    JOptionPane.showMessageDialog(null, "You lose!");
                }else{
                    stackPoint.push(new MyPoint(clickX,clickY));
                }
            }
            MyPoint thisPoint;
            int thisx,thisy;
            while(!stackPoint.empty()){//<editor-fold>
                thisPoint=stackPoint.pop();
                thisx=thisPoint.x;
                thisy=thisPoint.y;
                if(!minePanel.ifOpen[thisy][thisx]){
                    minePanel.ifOpen[thisy][thisx]=true;
                    --minePanel.unopenArea;
                }
                if(minePanel.unopenArea==minePanel.booms){
                    minePanel.repaint();
                    myFrame.timeTrigger.stop();
                    JOptionPane.showMessageDialog(null, "You win!");
                }else if(minePanel.count[thisy][thisx]==0){
                    for(int y=thisy-1;y<=thisy+1;++y){
                        for(int x=thisx-1;x<=thisx+1;++x){
                            if(y>=0 && x>=0 && x<column && y<row){
                                if(!minePanel.ifOpen[y][x]){
                                    stackPoint.push(new MyPoint(x,y));
                                }
                            }
                        }
                    }
                }//</editor-fold>
            }
            //</editor-fold>
        }else if(e.getButton()==e.BUTTON3){
            if(!minePanel.ifOpen[clickY][clickX]){
                minePanel.ifFlag[clickY][clickX]=!minePanel.ifFlag[clickY][clickX];
            }
        }
        minePanel.repaint();
    }
    
}
