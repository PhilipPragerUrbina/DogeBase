package DogeBase;

public class DogeException extends Exception{
    private String m_message;
    public DogeException(String message){
       m_message = message;
    }
    public DogeException(){
        m_message = "Unknown error.";
    }

    public String getMessage(){
        return m_message;
    }

    @Override
    public String toString() {
        return m_message;
    }
}
