package DogeBase;

import java.util.Scanner;

import static DogeBase.OpCode.*;

public class DogeClient {
    private DogeConnection connection;
    public boolean m_connected = false;
    public DogeClient(String ip, int port){
        connection = new DogeConnection(ip,port);
        if(!connection.isConnected()){
            return;
        }
        m_connected = true;
    }

    public int sendTest(int a){
        DogeInteger to_send = new DogeInteger(a);
        DogeInteger done = new DogeInteger( request(to_send.serialize()));
        return done.getValue();
    }

    public Object sendTest(Object a){
        DogeObject to_send = new DogeObject(a);
        DogeObject done = new DogeObject( request(to_send.serialize()));
        return done.getValue();
    }
    public Object get(int id){
        connection.sendOpCode(DOGE_READ);
        connection.write(new DogeInteger(id));
       return new DogeObject(connection.read()).getValue();
    }
    public void clear(){
        connection.sendOpCode(DOGE_CLEAR);
    }

    public int add(Object obj){
        connection.sendOpCode(DOGE_APPEND);
        connection.write(new DogeObject(obj));
        //return id
        return new DogeInteger(connection.read()).getValue();
    }

    private byte[] request(byte[] data) {
        connection.write(data);
        return connection.read();
    }


    public void close() {
        connection.sendOpCode(DOGE_STOP);
        connection.close();
    }
}
