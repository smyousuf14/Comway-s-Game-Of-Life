
/**
 * A facility for creating boards with specified number of cells.
 * 
 * @author Syed Yousuf role #14 
 * @version 1.00 2015-03-22
 * @version 1.01 2015-04-28 
 */
public class Board
{
    // Instance fields
    private Cell cell[][];
    private int generation;
    private boolean isGameOver;
    private int maxColumn;
    private int maxRow;
    
    /*
     * Constructors
     */
    /**
     * Creates a board with the default characteristics.
     * 
     * @param maxColumn The maximum number of columns, must not be < 0
     * @param maxRow The maximum number of rows, must not be < 0
     */
    public Board(int maxColumn, int maxRow)
    {
        // Create cell objects and set values to other instance fields.
        cell = new Cell[maxColumn][maxRow];
        generation = 0;
        isGameOver = false;
        
        // Assign values to the maxColumn and maxumum row.
        this.maxColumn = maxColumn;
        this.maxRow = maxRow;
        
        // Create each cell with the specified x and y coordinate values.
        for(int coordinateX = 0; coordinateX <= maxColumn - 1; coordinateX++) 
        {
            for(int coordinateY = 0; coordinateY <= maxRow - 1; coordinateY++)
            {
                cell[coordinateX][coordinateY] = new Cell(coordinateX,coordinateY);
            
            } // end of for(int coordinateY = 0; coordinateY < maxRow; coordinateY++)
        } // end of for(int coordinateX = 0; coordinateX < maxColumn; coordinateX++) 
        
        
    } // end of public Board()
    /*
     * Accessors
     */
    /**
     * Returns a specified cell.
     * 
     * @param locationX The X-value of the cell, must not be > 0
     * @param locationY The Y-value of the cell, must not be > 0
     * @return The specified cell
     */
    public Cell getCellSpecifed(int locationX, int locationY)
    {
        return cell[locationX][locationY];
        
    } // end of public Cell getCellSpecifed(int locationX, int locationY)
    
    /**
     * Returns the value of the current generation.
     * 
     * @return The current value of generation
     */
    public int getGeneration()
    {
        return generation;
        
    } // end of public int getGeneration()
    
    /**
     * Returns if the game is over.
     * 
     * @return isGameOver The value if the game is over.
     */
    public boolean getIsGameOver()
    {
        return isGameOver;
        
    } // end of public boolean getIsGameOver()
    
    /**
     * Returns the maximum number of columns in the board.
     * 
     * @return maxColumn The maximum number of columns
     */
    public int getMaxColumn()
    {
        return maxColumn;
    
    } // end of public int getMaxColumn()
    
    /**
     * Returns the maximum number of rows in the board.
     * 
     * @return maxRow The maximum number of rows
     */
    public int getMaxRow()
    {
        return maxRow;
    
    } // end of public int getMaxColumn()
    
    /**
     * Returns the current value for the specific cell is alive or dead.
     * 
     * @return isDeadCurrent The current value of the specified cell
     * @param locationX The x-value of the specified cell, must not be < 0
     * @param locationY The y-value of the specified cell, must not be < 0
     */
    public boolean getSpecificIsDeadCurrent(int locationX,int locationY)
    {
        return cell[locationX][locationY].getIsDeadCurrent();
    } // end of public boolean getSpecificIsDead()
    
    /**
     * Returns the future value of the specific cell.
     * 
     * @return isDeadFuture The future value of isDead.
     * @param locationX The x-value of the specified cell, must not be < 0
     * @param locationY The y-value of the specified cell, must not be < 0
     */
    public boolean getSpecificIsDeadFuture(int locationX,int locationY)
    {
        return cell[locationX][locationY].getIsDeadFuture();
    }
    
    /*
     * Mutators
     */
    /**
     * Checks to see if the Game of Life is over. This is determined if the 
     * future cell state is equivalent to the current cell states.
     */
    private void checkIfGameIsOver()
    {
        // Local Variables
        int cellCount = 0;
        
        for(int locationY = 0; locationY <= maxRow - 1; locationY++)
        {
            for(int locationX = 0; locationX <= maxColumn - 1; locationX++)
            {
                if(cell[locationX][locationY].getIsDeadCurrent() == cell[locationX][locationY].getIsDeadFuture())
                {
                    cellCount++;
                } // end of if() 
            } // end of for()
        } // end of for()
        
        // Now check if the all the cell's current and future states are equivalent.
        if(cellCount == maxColumn * maxRow)
        {
            isGameOver = true;
        } // end of if()
        
    } // end of private void checkIfGameIsOver()
    
    /**
     * Sets the specific cell to live or dead current.
     * 
     * @param isDead If the cell is dead or not.
     * @param locationX The x location 
     * @param locationY The y location
     */
    public void setSpecificIsDead(boolean isDead, int locationX, int locationY)
    {
        cell[locationX][locationY].setIsDeadCurrent(isDead);
    } // end of public void setSpecificIsDead(boolean isDead, int locationX, int locationY)
    
    /**
     * Tests the board and makes correct cells dead or alive.
     */
    public void testBoard()
    {
        // Local Variables
        int liveList = 0;
        String borderCell = "";
        
        // Increment the generation
        generation++;
        
        // Main test.
        for(int locationY = 0; locationY <= maxRow - 1; locationY++)
        {
            for(int locationX = 0; locationX <= maxColumn - 1; locationX++)
            {
                // Now set the borderCell to original value and liveList.
                borderCell = "";
                liveList = 0;
                
                // Now test to see if the cell is a border cell and specify which border cell it is.
                if(locationX == 0 && locationY == 0)
                {
                    borderCell = "upLeft";
                }
                else
                if(locationX == maxColumn - 1 && locationY == 0)
                {
                    borderCell = "upRight";
                }
                else
                if(locationX == 0 && locationY == maxRow - 1)
                {
                    borderCell = "downLeft";
                }
                else
                if(locationX == maxColumn - 1 && locationY == maxRow - 1)
                {
                    borderCell = "downRight";
                }
                
                for(int increment = 1; increment <= maxColumn - 2; increment++)
                {
                    if(locationX == increment && locationY == 0)
                    {
                        borderCell = "up";
                    }
                    else
                    if(locationX == increment && locationY == maxRow - 1)
                    {
                        borderCell = "down";
                    }
                }
                
                for(int increment = 1; increment <= maxRow - 2; increment++)
                {
                    if(locationX == 0 && locationY == increment)
                    {
                        borderCell = "left";
                    }
                    else
                    if(locationX == maxColumn - 1 && locationY == increment)
                    {
                        borderCell = "right";
                    }
                }
               
                // Start the test.
                if(borderCell.equals("") || borderCell.equals("upLeft") || borderCell.equals("downLeft") || borderCell.equals("up") || borderCell.equals("down")
                || borderCell.equals("left"))
                {
                    if(cell[locationX + 1][locationY].getIsDeadCurrent() == false)
                    {
                        liveList++;
                    }
           
                } 
                
                if(borderCell.equals("") || borderCell.equals("upLeft") || borderCell.equals("upRight") || borderCell.equals("up") || borderCell.equals("left")
                || borderCell.equals("right"))
                {
                    if(cell[locationX][locationY + 1].getIsDeadCurrent() == false)
                    {
                        liveList++;
                    }
                
                }
                
                if(borderCell.equals("") || borderCell.equals("upRight") || borderCell.equals("downRight") || borderCell.equals("down"))
                {
                    if(cell[locationX - 1][locationY].getIsDeadCurrent() == false)
                    {
                        liveList++;
                    }
                
                }
                
                if(borderCell.equals("") || borderCell.equals("downLeft") || borderCell.equals("downRight") || borderCell.equals("down") 
                || borderCell.equals("left") || borderCell.equals("right"))
                {
                    if(cell[locationX][locationY - 1].getIsDeadCurrent() == false)
                    {
                        liveList++;
                    }
                
                }
                
                if(borderCell.equals("") || borderCell.equals("upLeft") || borderCell.equals("up") || borderCell.equals("left"))
                {
                    if(cell[locationX + 1][locationY + 1].getIsDeadCurrent() == false)
                    {
                        liveList++;
                    }
                }
                
                if(borderCell.equals("") || borderCell.equals("downRight") || borderCell.equals("down"))
                {
                    if(cell[locationX - 1][locationY - 1].getIsDeadCurrent() == false)
                    {
                        liveList++;
                    }
                }
                
                if(borderCell.equals("") || borderCell.equals("upRight") || borderCell.equals("up"))
                {
                    if(cell[locationX - 1][locationY + 1].getIsDeadCurrent() == false)
                    {
                        liveList++;
                    }
                }
                
                if(borderCell.equals("") || borderCell.equals("downLeft") || borderCell.equals("down") || borderCell.equals("left"))
                {
                    if(cell[locationX + 1][locationY - 1].getIsDeadCurrent() == false)
                    {
                        liveList++;
                    }
                
                } // end of if([locationX + 1][locationY].getIsDeadCurrent() == false)
            
                // Now check what has happended and change the isDeadFuture
                if(cell[locationX][locationY].getIsDeadCurrent() == true && liveList == 3)
                {
                    cell[locationX][locationY].setIsDeadFuture(false);
                } // end of if(cell[locationX][locationY].getIsDeadCurrent() == false && liveList == 3)
                else
                if(cell[locationX][locationY].getIsDeadCurrent() == false && liveList > 3 || 
                cell[locationX][locationY].getIsDeadCurrent() == false && liveList < 2)
                {
                    cell[locationX][locationY].setIsDeadFuture(true);
                } // end of if(cell[locationX][locationY].getIsDeadCurrent() == false && liveList > 3 || liveList < 3) 
                else
                if(cell[locationX][locationY].getIsDeadCurrent() == false && liveList == 2 || 
                cell[locationX][locationY].getIsDeadCurrent() == false && liveList == 3)
                {
                    cell[locationX][locationY].setIsDeadFuture(false);
                }// end of if(cell[locationX][locationY].getIsDeadCurrent == false && liveList > 3)
            } // end of for(int locationY = 0; locationY < maxRow; locationY++)
        
        } // end of for(int locationX = 0; locationX < maxColumn; locationX++)
        
        // Now check if game is over.
        checkIfGameIsOver();
        
        // Now update each cell.
        updateCell();
        
    } // end of public void testBoard()
    
    /**
     * Updates the board with the present state of each cell in the board in console-style.
     */
    public void updateBoard()
    {
        // First clear the screen.
        System.out.println("\f");
        
        // State the generation.
        System.out.println("Generation: " + generation);
        
        for(int locationY = 0; locationY <= maxColumn - 1; locationY++)
        {
            for(int locationX = 0; locationX <= maxRow - 1; locationX++)
            {
                // Check each indivisual cell and place cell accordingly.
                if(locationX == maxColumn - 1)
                {
                    if(cell[locationX][locationY].getIsDeadCurrent())
                    {
                        System.out.println("X");
                    }
                    else
                    {
                        System.out.println("*");
                    } //end of if(cell[locationX][locationY].getIsDeadCurrent()) 
                }
                else
                if(cell[locationX][locationY].getIsDeadCurrent())
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print("*");
                } // end of if(cell[locationX][locationY].getIsDeadCurrent()) 
            } // end of for(int locationY = 0; locationY <= maxRow; locationY++)
        } // end of for(int locationX = 0; locationX <= maxColumn; locationX++)
        System.out.println("");
        
    }
    
    /**
     * Updates each cell so that the future state of each cell becomes the present state of the cell.
     */
    private void updateCell()
    {
        for(int locationX = 0; locationX <= maxColumn - 1; locationX++)
        {
            for(int locationY = 0; locationY <= maxRow - 1; locationY++)
            {
                cell[locationX][locationY].setIsDeadCurrent(cell[locationX][locationY].getIsDeadFuture());
            } // end of for(int locationY = 0; locationY < maxRow; locationY++)
        } // for(int locationX = 0; locationX < maxColumn; locationX++)
    } // end of private void updateCell()
} // end of public class Board
