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
public class MouseClick extends MouseAdapter{
    MinePanel minePanel;
    Random random;
    Stack<MyPoint> stackPoint;
    int row,column;
    boolean ifDown;
    
    public void myInit(MinePanel minePanel){
        this.minePanel=minePanel;
        random=new Random();
        row=minePanel.row;
        column=minePanel.column;
        stackPoint=new Stack<MyPoint>();
        
        minePanel.addMouseListener(this);
    }
    
    @SuppressWarnings("empty-statement")
    public void mouseReleased(MouseEvent e){
        int clickX=e.getX()/minePanel.squareSize;
        int clickY=e.getY()/minePanel.squareSize;
        
        if(e.getButton()==e.BUTTON1){//<editor-fold>
            if(minePanel.isFirst){//<editor-fold>
                minePanel.isFirst=false;
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
            if(minePanel.ifBoom[clickY][clickX]){
                if(!minePanel.ifFlag[clickY][clickX]){
                    for(int r=0;r<minePanel.row;++r){
                        for(int c=0;c<minePanel.column;++c){
                            if(minePanel.ifBoom[r][c]){
                                minePanel.ifOpen[r][c]=true;
                            }
                        }
                    }
                    System.out.println("lose!");
                }
            }else{
                stackPoint.push(new MyPoint(clickX,clickY));
            }
            MyPoint thisPoint;
            int thisx,thisy;
            while(!stackPoint.empty()){//<editor-fold>
                thisPoint=stackPoint.pop();
                thisx=thisPoint.x;
                thisy=thisPoint.y;
                if(!minePanel.ifBoom[thisy][thisx]){
                    minePanel.ifOpen[thisy][thisx]=true;
                    if(thisy>0){
                        if(minePanel.ifOpen[thisy-1][thisx]!=true){
                            stackPoint.push(new MyPoint(thisx,thisy-1));
                        }
                    }
                    if(thisy<row-1){
                        if(minePanel.ifOpen[thisy+1][thisx]!=true){
                            stackPoint.push(new MyPoint(thisx,thisy+1));
                        }
                    }
                     if(thisx>0){
                        if(minePanel.ifOpen[thisy][thisx-1]!=true){
                            stackPoint.push(new MyPoint(thisx-1,thisy));
                        }
                    }
                    if(thisx<column-1){
                        if(minePanel.ifOpen[thisy][thisx+1]!=true){
                            stackPoint.push(new MyPoint(thisx+1,thisy));
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
