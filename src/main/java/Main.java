import java.util.Scanner;
public class Main {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Queue queue = new Queue();
        System.out.println("Please enter the number of cars that are entering the parking:");
        int number = input.nextInt();
        for(int i = 0; i < number; i++){
            System.out.println("Please enter car Id:");
            Car car = new Car(input.nextInt());
            queue.enqueue(car);
        }


    }
}
