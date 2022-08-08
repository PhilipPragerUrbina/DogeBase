package DogeBase;




import static DogeBase.DogeTypeID.STRING;

public class DogeString implements DogeType{
    //actual value
    private String m_value;
    private DogeTypeID m_id = STRING;
    //create the value
    public DogeString(String value){
        m_value = value;
    }

    //deserialize
    public DogeString(byte[] data){
        m_value =  new String(data);
    }



    public String getValue(){
        return m_value;
    }

    //serialize in order to send
    @Override
    public byte[] serialize() {
        return m_value.getBytes();
    }

}

