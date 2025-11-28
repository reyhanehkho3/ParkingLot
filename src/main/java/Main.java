import java.util.Objects;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Queue queue = new Queue();
        System.out.println("Please enter the number of cars that are entering the parking:");
        int number = input.nextInt();
        for(int i = 0; i < number; i++) {
            System.out.println("Please enter car Id:");
            Car car = new Car(input.nextInt());
        }
        for(int j = 0; j < number; j++) {
            Car c = queue.peek();
            System.out.println("Parking the car with carID :" + c.getCarID() +
                    "\n Enter YES you want to park your car in a specific section.\n" +
                    " If you don't have a specific place in mind type NO.");
            if (Objects.equals(input.next(), "YES")) {
                System.out.println("Enter section:");
                queue.dequeueN(input.nextInt());
            } else {
                queue.dequeue();
            }
        }

        }


    }
