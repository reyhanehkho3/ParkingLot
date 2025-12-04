public class Linker {
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
    public void exitCar(int id){
        Stack.pop(id);
        System.out.println("Car exited successfully.");
    }
    public void moveStack(int stackNumber1, int stackNumber2){
        Stack.relocate(stackNumber1, stackNumber2);
        System.out.println("The section moved successfully.");
    }
    public Stack orderStack(int stackNumber){
        return Stack.order(stackNumber);
    }
    public void displayStack(int stackNumber){
        Stack.displayNumber(stackNumber);
    }

}
