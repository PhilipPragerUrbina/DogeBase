package DogeBase;




import java.io.*;

import static DogeBase.DogeTypeID.OBJECT;
//store a java object in the database, must implement serializable
public class DogeObject implements DogeType{
    //actual value
    private Object m_value;
    private DogeTypeID m_id = OBJECT;
    //create the value
    public DogeObject(Object value){
        m_value = value;
    }

    //deserialize
    public DogeObject(byte[] data){
        try (ByteArrayInputStream byte_stream = new ByteArrayInputStream(data);
             ObjectInputStream object_stream = new ObjectInputStream(byte_stream)) {
            m_value = object_stream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public Object getValue(){
        return m_value;
    }

    //serialize in order to send
    @Override
    public byte[] serialize() {
        try (ByteArrayOutputStream out_bytes = new ByteArrayOutputStream();
             ObjectOutputStream out_object = new ObjectOutputStream(out_bytes)) {
            out_object.writeObject(m_value);
            return out_bytes.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

