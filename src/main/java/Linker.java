import java.util.Scanner;
public class Linker {
    Scanner input = new Scanner(System.in);
    Queue queue = new Queue();
    public void enter(int id){
        Car car = new Car();
        car.setCarID(id);
        queue.enqueue(car);
        queue.dequeue();
    }
    public void enterCertain(int id, int stackNumber){
        Car car = new Car();
        car.setCarID(id);
        queue.enqueue(car);
        queue.dequeueN(stackNumber);
    }
    public void findCar(int id){
        int[] result = Stack.find(id);
        System.out.println("The car is at section: " + result[0] + ", and place number: " + result[1]);
    }
    public void exitCar(){
        System.out.println("Car exited successfully.");
    }
    public void moveStack(int stackNumber1, int stackNumber2){
        int a = Stack.relocate(stackNumber1, stackNumber2);
        if(a == 0){
            System.out.println("The section moved successfully.");
        }
        else{
            System.out.println("There is no space remained to put the car. The remained cars stay at their section.");
        }
    }
    public Stack orderStack(int stackNumber){
        return Stack.order(stackNumber);
    }
    public void displayStack(int stackNumber){

        Stack.displayNumber(stackNumber);
    }

}
