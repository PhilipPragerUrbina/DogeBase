package DogeBase;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static DogeBase.DogeTypeID.INTEGER;

public class DogeInteger implements DogeType{
    //actual value
    private int m_value;
    private DogeTypeID m_id = INTEGER;
    //create the value
    public DogeInteger(int value){
        m_value = value;
    }

    //deserialize
    public DogeInteger(byte[] data){

        m_value =  ByteBuffer.wrap(data).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }



    public int getValue(){
        return m_value;
    }

    //serialize in order to send
    @Override
    public byte[] serialize() {


        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(m_value).array();
    }
}
