package DogeBase;
//Doge colum types
public enum DogeTypeID {
    FILE,   //A separate file. Basically data of a certain size under a filename.
    DATA,   //raw bytes
    STRING,  //any text
    INTEGER,  //a number
    DOUBLE, //a floating point number
    DATE,  //A date, like the time of update
    URL,  //a URL
    LINK, //reference to other location in database
    OBJECT // a java object
}
