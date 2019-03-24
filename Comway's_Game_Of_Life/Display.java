import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

/**
 * Creates Conway's game of life with graphical user interface.
 * 
 * @author Syed Yousuf
 * @version 1.00 2015-04-28
 */
public class Display implements Runnable
{
    // Private static fields
   private static final int DELAY = 500;
   private static final int MAXIMUM_ROW = 15;
   private static final int MAXIMUM_COLUMN = 15;
   
   // Instance fields.
   private boolean allowCustomization;
   private boolean allowStart;
   private Board board;
   private JMenuItem exit;
   private JFrame frame;
   private JPanel gridPanel;
   private JButton button[][];
   private JLabel mainLabel;
   private JMenu file;
   private JMenuBar menuBar;
   private JMenuItem customize;
   private JMenuItem startProgram;
   private Thread thread;
   /*
    * Constructors
    */
   /**
    * Creates Display objects with specified MAXIMUM_ROW and MAXIMUM_COLUMN.
    * 
    */
   public Display()
   {
       // Set the instance fields.
       allowCustomization = false;
       allowStart = false;
       thread = new Thread(this);
     
       
       // Create a board.
       board = new Board(MAXIMUM_COLUMN,MAXIMUM_ROW);
       
       makeFrame();
   }
   
   /*
    * Mutators
    */
   
   /*
    * Creates the swing Frame and it's content.
    * 
    */
   private void makeFrame()
   {
       frame = new JFrame("Comway's Game Of Life");
       Container contentPane = frame.getContentPane();
       
       // Create the main layout.
       BorderLayout layoutMain = new BorderLayout();
       
       contentPane.setLayout(layoutMain);
       
       // Create grid layout with specifed rows and columns.
       GridLayout layoutGrid = new GridLayout(MAXIMUM_ROW,MAXIMUM_COLUMN);
              
       // Create another JPanel.
       gridPanel = new JPanel(layoutGrid);
       
       // Create the specifed number of buttons
       button = new JButton[MAXIMUM_COLUMN][MAXIMUM_ROW];
       
       // Create a text-menu.
       mainLabel = new JLabel("Please select an operation to perform; select a custom design");
       
       contentPane.add(mainLabel,BorderLayout.SOUTH);
       contentPane.add(gridPanel, BorderLayout.CENTER);
       
       // Now set the menu bars and items.
       file = new JMenu("File");
       menuBar = new JMenuBar();
       customize = new JMenuItem("Customize Pattern");
       startProgram = new JMenuItem("Start Program");
       exit = new JMenuItem("Exit");
       
       // Create the menu bars.
       frame.setJMenuBar(menuBar);
       menuBar.add(file);
       file.add(customize);
       file.add(startProgram);
       file.add(exit);
       
       // Add action listener to each menu item.
       customize.addActionListener(new PerformCustomizationAction());
       startProgram.addActionListener(new StartProgram());
       exit.addActionListener(new PerformExit());
       
       // Initialize each button.
       for(int locationX = 0; locationX <= MAXIMUM_COLUMN - 1; locationX++)
       {
           for(int locationY = 0; locationY <= MAXIMUM_ROW - 1; locationY++)
           {               
               button[locationX][locationY] = new JButton();
               
               gridPanel.add(button[locationX][locationY]);
               button[locationX][locationY].setActionCommand("" + locationX + "," + locationY);
               button[locationX][locationY].setBackground(Color.DARK_GRAY);
               button[locationX][locationY].addActionListener(new PerformButtonAction());
               
           } // end of for(int locationY = 0; locationY <= MAXIMUM_ROW - 1; locationY++) 
       } // end of for(int locationX = 0; locationX <= MAXIMUM_COLUMN - 1; locationX++)

                             
       frame.pack();
       frame.setVisible(true);
   } // end of private void makeFrame()
   
   /**
    * Applies the rules of Comway's game of life until or the cells are dead.
    */
   public void run()
   {
       do
       {
           mainLabel.setText("Generation: " + board.getGeneration());
           mainLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 25));
           mainLabel.setForeground(Color.RED);
           board.testBoard();
           
           // Now update the board. 
           for(int locationX = 0; locationX <= MAXIMUM_COLUMN - 1; locationX++)
           {
               for(int locationY = 0; locationY <= MAXIMUM_ROW - 1;locationY++)
               {
                   if(board.getSpecificIsDeadCurrent(locationX,locationY) == false)
                   {
                       button[locationX][locationY].setBackground(Color.GREEN);
                    }
                    else
                    {
                        button[locationX][locationY].setBackground(Color.DARK_GRAY);
                    } // end of if()
                } // end of for(int locationY = 0; locationY <= MAXIMUM_ROW - 1;locationY++) 
            } // end of for(int locationX = 0; locationX <= MAXIMUM_COLUMN - 1; locationX++)   
            
           try
           {
              Thread.sleep(DELAY);
           }
           catch(InterruptedException exception)
           {
              System.err.println("Could not delay. Sorry.");
           } // end of catch()  
       }  
       while(board.getIsGameOver() == false);
   } // end of public void run()
   
   private class PerformButtonAction implements ActionListener
   {
       /**
        * Performs the button action.
        */
       public void actionPerformed(ActionEvent event)
       {
           // Local variables
           // Get the coordinates of the button.
           String coordinates = event.getActionCommand();
           String[] buttonPressed = coordinates.split(",");
           
           // Only perfrom this action if allowed.
           if(allowCustomization)
           {
               if(board.getSpecificIsDeadCurrent(Integer.parseInt(buttonPressed[0]), Integer.parseInt(buttonPressed[1])))
               {
                   button[Integer.parseInt(buttonPressed[0])][Integer.parseInt(buttonPressed[1])].setBackground(Color.GREEN);
           
                   // Now change the value of the assigned cell.
                   board.setSpecificIsDead(false,Integer.parseInt(buttonPressed[0]),Integer.parseInt(buttonPressed[1]));
                   allowStart = true;
                }
                else
                {
                   button[Integer.parseInt(buttonPressed[0])][Integer.parseInt(buttonPressed[1])].setBackground(Color.DARK_GRAY);
           
                   // Now change the value of the assigned cell.
                   board.setSpecificIsDead(true,Integer.parseInt(buttonPressed[0]),Integer.parseInt(buttonPressed[1]));
                   allowStart = true;
                }
           } // end of if(allowCustomization)
       } // end of public void actionPerformed(ActionEvent event)
       
   } // end of private class performButtonAction implements ActionListener
   
   private class PerformExit implements ActionListener
   {
       /**
        * Performs the exit and safely closes the program.
        */
       public void actionPerformed(ActionEvent event)
       {
           System.exit(0);
       }
       
   } // end of private class performExit
   
   private class PerformCustomizationAction implements ActionListener
   {
       /**
        * Performs the customization action.
        */
       public void actionPerformed(ActionEvent event)
       {
           allowCustomization = true;
       } // end of public void actionPerformed(ActionEvent event)
   } // end of private class performCustomizationAction implements ActionListener
   
   private class StartProgram implements ActionListener
   {
       /**
        * Starts the test.
        */
       public void actionPerformed(ActionEvent event)
       {
           if(allowStart)
           {
               mainLabel.setText("Generation: " + board.getGeneration());
               mainLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 25));
               mainLabel.setForeground(Color.RED);
               thread.start();
               allowCustomization = false;
           }
       }
       
   }
   
}
