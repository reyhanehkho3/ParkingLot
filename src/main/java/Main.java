import java.util.Objects;
import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Queue queue = new Queue();
        System.out.println("Please enter the number of cars that are entering the parking:");
        int number = input.nextInt();
        System.out.println("Please enter car Ids:");
        for(int i = 0; i < number; i++) {
            Car car = new Car();
            car.setCarID(input.nextInt());
            queue.enqueue(car);
        }
        for(int j = 0; j < number; j++) {
            Car c = queue.peek();
            if (c == null) {
                System.out.println("No car found in queue");
                break;
            }
            System.out.println("Parking the car with carID :" + c.getCarID() +
                    "\n Enter YES you want to park your car in a specific section.\n" +
                    " If you don't have a specific place in mind type NO.");
            if (Objects.equals(input.next(), "YES")) {
                System.out.println("Enter section (0-4):");
                int section = input.nextInt();
                if (section < 0 || section > 4) {
                    System.out.println("Invalid section! Please enter 0-4:");
                    section = input.nextInt();
                }
                queue.dequeueN(section);
            } else {
                queue.dequeue();
            }
        }

        }


    }
