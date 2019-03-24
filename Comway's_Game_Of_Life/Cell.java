
/**
 * A facuility for creating cell objects.
 * 
 * @author Syed Yousuf roll#14
 * @version 1.00 2015-04-22
 */
public class Cell
{
    // Instance fields
    private int locationX;
    private int locationY;
    private boolean isDeadCurrent;
    private boolean isDeadFuture;
    
    /*
     * Constructors
     */
    /**
     * Creates a cell with default values
     * 
     * @param coordinateX The x-value of the cell, must not be < 0
     * @param coordinateY The y-value of the cell, must not be < 0
     */
    public Cell(int coordinateX,int coordinateY)
    {
        locationX = coordinateX;
        locationY = coordinateY;
        
        isDeadCurrent = true;
        isDeadFuture = true;
    
    } // end of public Cell(int coordinateX,int coordinateY)
    
    /*
     * Acessors
     */
    /**
     * Returns if the cell is dead or alive in it's current state.
     * 
     * @return isDeadCurrent If the cell is dead or alive
     */
    public boolean getIsDeadCurrent()
    {
        return isDeadCurrent;
        
    } // end of public boolean getIsDead()
    
    /**
     * Returns if the cell will be dead or alive.
     * 
     * @return isDeadFuture If the cell will be dead or alive
     */
    public boolean getIsDeadFuture()
    {
        return isDeadFuture;
    } // end of public boolean getIsDeadFuture()
    
    /**
     * Returns the x-value of the cell.
     * 
     * @return locationX The x-coordinate where the cell is located
     */
    public int getLocationX()
    {
        return locationX;
    
    } // end of public int getLocationX()
    
    /**
     * Returns the y-value of the cell.
     * 
     * @return locationY The y-coordinate value where the cell is located
     */
    public int getLocationY()
    {
        return locationY;
    } // end of public int getLocationY()
    
    /*
     * Mutators
     */
    /**
     * Sets the value of dead or alive for the cell.
     * 
     * @param isDeadCurrent If the cell is dead or alive, can not be <code>null</code>
     */
    public void setIsDeadCurrent(boolean isDead)
    {
        isDeadCurrent = isDead;
        
    } // end of public void setIsDead(boolean isDead)
    
    /**
     * Sets the value of dead or alive the cell will have.
     * 
     * @param isDeadFuture If the cell will be dead or alive, can not be <code>null</code>
     */
    public void setIsDeadFuture(boolean isDead)
    {
        isDeadFuture = isDead;
    
    } // end of public void setIsDeadFuture(boolean isDead)
} // end of class Cell
