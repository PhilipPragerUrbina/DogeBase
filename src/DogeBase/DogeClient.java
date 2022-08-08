package DogeBase;

import java.util.Scanner;

public class DogeClient {
    private DogeConnection connection;
    public DogeClient(String ip, int port){
        connection = new DogeConnection(ip,port);
        if(!connection.isConnected()){
            return;
        }
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
      connection.write(new DogeInteger(0));
        connection.read();
        connection.write(new DogeInteger(id));
       return new DogeObject(connection.read()).getValue();
    }
    public void clear(){
        connection.writeString("clear");
        connection.read();
    }

    public int add(Object obj){

        connection.write(new DogeInteger(1));
        connection.read();
        connection.write(new DogeInteger(12));
        connection.read();
        connection.write(new DogeObject(obj));


        //return id
        return new DogeInteger(connection.read()).getValue();
    }

    private byte[] request(byte[] data) {
        connection.write(data);
        return connection.read();
    }


    public void close() {
        String stop_message = "stop";
        connection.writeString(stop_message);
        connection.close();
    }
}
