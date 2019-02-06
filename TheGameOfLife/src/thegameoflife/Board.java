package thegameoflife;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Board extends JPanel{
    int space = 5;
    boolean[][] cells;
    int width;
    int height;

    public Board(boolean[][] cell){
     cells = cell;
    }
    public void setNewCell(boolean[][] cell){
     cells = cell;
    }
  
    public void paint(Graphics graphics){
     width = 50;
     height = 50;
     
     
     //Code used to draw the cells;
     graphics.setColor(Color.GREEN);
     for(int i = 0; i <cells.length; i++){
       for(int j=0; j <cells[0].length; j++){
         if(cells[i][j] == true){
         graphics.fillRect(j*width, i*height, width, height);
             
         }
       }
     }
     
     
     
     //Code used to draw the board.
     for(int i = 0; i <cells[0].length ; i++){
       for(int j=0; j <cells.length; j++){
       graphics.drawRect(i*width,j*height, width , 50);
       }
     }
    }
    
}
