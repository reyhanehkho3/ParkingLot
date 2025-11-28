import java.util.Scanner;
public class Queue {
    private Car[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public Queue() {
        this.capacity = capacity;
        arr = new Car[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
    Scanner input = new Scanner(System.in);
    public void enqueueN(Car c, int stackNumber){
        Stack s = Stack.getStack(stackNumber);
        if (s.isFull()) {
            System.out.println("Sorry, this section is full. Please choose another section:");
            enqueueN(c, input.nextInt());
        }
        rear = (rear + 1) % capacity;
        arr[rear] = c;
        size++;
    }

    public void enqueue(Car c) {
        if (isFull()) {
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = c;
        size++;
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("There is no car remained");
        }
        Car removed = arr[front];
        front = (front + 1) % capacity;
        size--;
        Stack found_stack = Stack.findAvailableStack();
        if(found_stack == null){
            System.out.println("Sorry, the parking is full");
        }
        else{
            found_stack.push(removed);
        }
    }

    public Car peek(){
        if (isEmpty()){
            return null;
        }
        return arr[front];
    }

    public int getSize() {
        return size;
    }
}
