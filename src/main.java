import DogeBase.DogeClient;
import DogeBase.DogeConnection;
import DogeBase.DogeException;

import javax.sound.midi.Soundbank;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {


        DogeClient doge = new DogeClient("10.0.0.115",8080);

    if(!doge.m_connected){
        return;
    }



        System.out.println("Get , end, and new");
        while(true){
            try {
                Scanner input = new Scanner(System.in);
                System.out.println("Enter command");
                String command = input.nextLine();
                if (Objects.equals(command, "end")) {
                    System.out.println("finalized");
                    break;
                } else if (Objects.equals(command, "fill")) {
                    System.out.println("Enter amount");
                    int n = input.nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.println("Creating new food... ");
                        String name = randomName();
                        System.out.println("name: " + name);
                        double price = Math.random() * 100;
                        System.out.println("price: " + price);
                        int yummy = (int) (Math.random() * 10);
                        System.out.println("yummyness: " + yummy);
                        food a = new food(yummy, name, price);
                        System.out.println("ID of new menu item is: " + doge.add(a));

                    }
                } else if (Objects.equals(command, "getall")) {
                    System.out.println("Enter amount");
                    int n = input.nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.println(doge.get(i));
                        System.out.println(i);

                    }
                } else if (Objects.equals(command, "clear")) {
                    doge.clear();
                } else if (Objects.equals(command, "new")) {
                    System.out.println("Creating new food... ");
                    System.out.println("enter name of food: ");
                    String name = input.nextLine();
                    System.out.println("enter price of food: ");
                    double price = input.nextDouble();
                    int yummy = (int) (Math.random() * 10);
                    food a = new food(yummy, name, price);
                    System.out.println("Added item to menu");
                    System.out.println("ID of new is: " + doge.add(a));
                } else if (Objects.equals(command, "n")) {
                    System.out.println("Creating new food... ");
                    String name = randomName();
                    System.out.println("name: " + name);
                    double price = Math.random() * 100;
                    System.out.println("price: " + price);
                    int yummy = (int) (Math.random() * 10);
                    System.out.println("yummyness: " + yummy);
                    food a = new food(yummy, name, price);
                    System.out.println("ID of new menu item is: " + doge.add(a));
                } else {
                    System.out.println("get item with id: ");
                    int id = input.nextInt();
                    System.out.println(doge.get(id));
                }

            }catch (DogeException error){
                System.err.println(error);
                break;
            }

        }



        doge.close();

    }
    public final static String[] elements = { "dog" , "hot" , "cold" , "burger", "cream", "ice", "bun" , "deli", "which" ,"sand","steak", "veggie", "corn", "thing", "heated", "microwave" ,"fast", "ham" , "chicken", "beef", "seafood", "smoked", "grilled", "edible", "canned", "drink", "salt"};
    static String randomName(){
        String out = "";
        int length = ((int)(Math.random() * 3)) + 2 ;
        for (int i = 0; i < length; i++) {
            out = out + " " + elements[new Random().nextInt(elements.length)];
        }
        return out;
    }
}
class food implements Serializable {

    int yummyness;
    String name;
    double price;
    int mass =10;

    public food(int yummyness, String name, double price) {
        this.yummyness = yummyness;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "food{" +
                "yummyness=" + yummyness +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", mass=" + mass +
                '}';
    }
}



/*
public static void main(String[] args) {
        DogeConnection doge = new DogeConnection("10.0.0.115",8080);
        Scanner input = new Scanner(System.in);
        if(!doge.isConnected()){
            return;
        }
        String out = "";
        System.out.println("Input(type stop to end): ");
        while(!out.equals("stop")){
            // Create a Scanner object
            String to_send = input.nextLine();
            out = to_send;
            doge.write(to_send);
            System.out.println(to_send + " <- sent");

            System.out.println(doge.read() + " <- received");
        }
        doge.close();




    }

 */