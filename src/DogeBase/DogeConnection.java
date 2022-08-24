package DogeBase;
import java.io.*;
import java.net.*;


public class DogeConnection {
    private Socket m_socket;

    private DataInputStream m_in;
    private DataOutputStream m_out;
    public DogeConnection(String address, int port){
        try {

            InetAddress local_address = InetAddress.getLocalHost();
            System.out.println("This machine is: " + local_address.getHostAddress() + " named " + local_address.getHostName());
            //set up socket
            m_socket = new Socket(address, port);
            System.out.println("Connected to: " + m_socket.toString());



            //set up streams
            m_in = new DataInputStream(m_socket.getInputStream());
            m_out = new DataOutputStream(m_socket.getOutputStream());


        } catch (UnknownHostException u) {
            System.out.println(u);
        }
        catch (IOException i) {
            System.out.println(i);
        }
    }

    public boolean isConnected(){
        return m_socket != null;
    }



    public void write(byte[] data){
        try {
            //System.out.println(new String(data));
            m_out.write(data);
        }catch (IOException i) {
            System.out.println(i);
        }
    }
    public byte[] read() throws DogeException{
        try {
            byte[] bytes = new byte[1024];
            int number_read = m_in.read(bytes);
            if(number_read < 0){
                throw new DogeException("Disconnected from server");
            }

            byte[] shortened_bytes = new byte[number_read-4];
            System.arraycopy(bytes, 4, shortened_bytes, 0, shortened_bytes.length);
            byte[] code_bytes = new byte[4];
            System.arraycopy(bytes, 0, code_bytes, 0, code_bytes.length);

            //check if error message was sent using metadata at the beginning of the message
            if(new DogeInteger(code_bytes).getValue() == 1){
                throw new DogeException(new DogeString(shortened_bytes).getValue());
            }

            return shortened_bytes;
        }catch (IOException i) {
            System.out.println(i);
        }
        return new byte[0];
    }
//overloaded write
    public void write(DogeType thing){
        this.write(thing.serialize());
    }
    //send an opcode and await confirmation
    public void sendOpCode(OpCode thing) throws DogeException {
        this.write(new DogeInteger(thing.ordinal())); read();
    }

    public void close() {
        try {
            m_out.close();
            m_in.close();
            m_socket.close();
        }  catch (IOException i) {
            System.out.println(i);
        }
    }
}
