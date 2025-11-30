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
    public void exitCar(int id){
        Stack.pop(id);
    }
    public void moveStack(int stackNumber1, int stackNumber2){
        Stack.relocate(stackNumber1, stackNumber2);
    }
    public Stack orderStack(int stackNumber){
        return Stack.order(stackNumber);
    }

}
