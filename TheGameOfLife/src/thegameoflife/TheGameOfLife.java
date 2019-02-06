
package thegameoflife;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;



public class TheGameOfLife implements MouseListener,ActionListener, Runnable{
    
    boolean[][] cell = new boolean[8][8];
    JFrame frame = new JFrame();
    JButton Start = new JButton();
    JButton Stop = new JButton();
    JButton Refresh = new JButton();
    boolean flag = false;
    
    Container bottomContainer = new Container();
    Board gridboard = new Board(cell);
    
    
    public TheGameOfLife(){
        
        frame.setTitle("The Game of Life"); // name of frame title.
        frame.setSize(400,456); //sets size of frame
        frame.setResizable(false); // stops user from altering size of frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // places gui at center of screen.
        frame.setVisible(true);
        
        // button container 
        bottomContainer.setLayout(new GridLayout(1,2));
        Start.setVisible(true);
        Start.setText("Start");
        Start.addActionListener(this);
        
        Stop.setVisible(true);
        Stop.setText("Stop");
        Stop.addActionListener(this);
        
        Refresh.setVisible(true);
        Refresh.setText("Refresh");
        Refresh.addActionListener(this);
        
        //adds buttons to container
        bottomContainer.add(Start);
        bottomContainer.add(Stop);
        bottomContainer.add(Refresh);
        
        //adds container to frame.
        frame.add(bottomContainer,BorderLayout.SOUTH);
        frame.add(gridboard,BorderLayout.CENTER);
        
        // creates a mouse listener which listens to mouse events.
        gridboard.addMouseListener(this);
    
    }
    public static void main(String[] args) {
        // runs then TheGameOfLife() method.
        new TheGameOfLife();
       
    }
 
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource().equals(Start)){
            if(flag == false){
                flag = true;
                Thread thread = new Thread(this);
                thread.start();
            
            }
            System.out.println("start");
        }
        if(ae.getSource().equals(Stop)){
            flag = false;
            System.out.println("Stop");

        }
        if(ae.getSource().equals(Refresh)){
        System.out.println("Refresh");    
        cell = new boolean[8][8];  // replaces content of cell to newly changed cells.
        gridboard.setNewCell(cell); // sends nextgeneration cells to board class.
        frame.repaint();  // repaints the new cells.
        }
        
    }
    @Override
    public void run() { // this method is called when thread is started.
        while(flag == true){
        nextGeneration();
            try {
                Thread.sleep(400);
            } catch (Exception ex) {
                System.out.println("Error" + ex);
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent me) { // method which allows user to select cells on the grid.
        flag = false;
        int width =50;
        int height =50;
        int column = me.getX() /  width; //gets positioning of cell
        int row =  me.getY()/ height; //gets positioning of cell
        System.out.println(row + "," + column);
        cell[row][column] = true; // sets the value of the row/colum to true as default all rows/colums are false by default.
        frame.repaint(); // repaints the grid.
    }
    
    public void nextGeneration(){
    int NumRows = cell.length; // returns the length of rows in cell.
    int NumColsInRow = cell[0].length; //returns number of colums in a row.
    
    boolean[][] nextcell = new boolean[NumRows][NumColsInRow]; // new 2d array created with same size of cell.
    
    for(int r = 0; r < NumRows; r++){ // reads through each row
        for(int c=0; c < NumColsInRow; c++){ // reads through each column
            int CountNumNeighbors = 0; // variable which stores the number of neighbors an active cell has.
            
            // checking for neighbors in all directions.
            if(r > 0 && cell[r - 1][c] == true){ // up
             CountNumNeighbors++;
            }
            if(r < NumRows - 1 && cell[r + 1][c] == true){ // down
             CountNumNeighbors++;
            }
            if(c > 0 && cell[r][c - 1] == true){ // left
             CountNumNeighbors++;
            }
            if(c < NumColsInRow - 1 && cell[r][c + 1] == true){ // right
             CountNumNeighbors++;
            }
            
            if(r > 0 && c > 0 && cell[r - 1][c - 1] == true){ // upLeft
             CountNumNeighbors++;
            }
            if(r > 0 && c < NumColsInRow-1 && cell[r - 1][c + 1] == true){ // upRight
             CountNumNeighbors++;
            }
            if(r < NumRows-1 && c > 0 && cell[r + 1][c - 1] == true){ // downLeft
             CountNumNeighbors++;
            }
            if(r < NumRows-1 && c < NumColsInRow-1 && cell[r + 1][c + 1] == true){ // downRight
             CountNumNeighbors++;
            }
            
            // Rules of how cells are alive, dead or reborn.
            
            if(cell[r][c] == true){ // cell is currently alive.
               if(CountNumNeighbors < 2 || CountNumNeighbors > 3){ // if neighbors less than 2 or greater than 3 cell dies.
                 nextcell[r][c] = false;
               }  
               else if(CountNumNeighbors == 2 || CountNumNeighbors == 3){ // if neighbors eqal to 2 or 3 then cell remains alive.
                 nextcell[r][c] = true;
               }
            
            }else{ //cells currently dead.
            if(CountNumNeighbors  == 3){ // if empty cell has 3 neighbors a cell is created.
              nextcell[r][c] = true;
             }else{
              nextcell[r][c] = false;
             }
            
            }
        
        }
      } 
        cell = nextcell;  // replaces content of cell to newly changed cells.
        gridboard.setNewCell(cell); // sends nextgeneration cells to board class.
        frame.repaint();  // repaints the new cells.
    }

    

    // inbuild methods which must be used when implementing mouse listener.

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }
    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    

    

 
    
}
