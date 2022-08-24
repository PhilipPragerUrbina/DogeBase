package DogeBase;

import java.util.Scanner;

import static DogeBase.OpCode.*;

public class DogeClient {
    //the connection to the server
    private DogeConnection connection;

    public boolean m_connected = false;

    public DogeClient(String ip, int port){
        connection = new DogeConnection(ip,port);
        if(!connection.isConnected()){
            return;
        }
        m_connected = true;
    }


    //operations
    public Object get(int id) throws DogeException {
        connection.sendOpCode(DOGE_READ);
        connection.write(new DogeInteger(id));
            return new DogeObject(connection.read()).getValue();
    }
    public void clear() throws DogeException {
        connection.sendOpCode(DOGE_CLEAR);
    }

    public int add(Object obj) throws DogeException {
        connection.sendOpCode(DOGE_APPEND);
        connection.write(new DogeObject(obj));
        //return id
        return new DogeInteger(connection.read()).getValue();
    }
    public void close()  {
        try {
            connection.sendOpCode(DOGE_STOP);
            connection.read();
        }catch (DogeException error){
            System.err.println(error);
        }finally {

            connection.close();
        }
    }
}
