package gameoflife.displayer.text;

public enum CharDisplayer
{
    ALIVE_CELL('#'),
    DEAD_CELL(' '),
    NORTH_BORDER('-'),
    SOUTH_BORDER('-'),
    WEST_BORDER('|'),
    EAST_BORDER('|'),
    NORTH_WEST_CORNER('+'),
    NORTH_EAST_CORNER('+'),
    SOUTH_WEST_CORNER('+'),
    SOUTH_EAST_CORNER('+'),
    NEW_LINE('\n');
    
    private char displayChar;
    
    CharDisplayer(char displayChar)
    {
        this.displayChar = displayChar;
    }
    
    public void setChar(char displayChar)
    {
        this.displayChar = displayChar;
    }
    
    public char getChar()
    {
        return displayChar;
    }
};
