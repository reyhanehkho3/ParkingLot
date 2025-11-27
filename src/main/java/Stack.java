//this Stack class is implemented using LinkedList
class Node{
    public Car car;
    public Node next;

    public Node(Car car){
        this.car = car;
        next = null;
    }

    public Node getNext() {
        return next;
    }

    public Car getCar() {
        return car;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
public class Stack {
    public Node head = null;
    public Node tail = null;
    private int stackNumber;
    private Node top;
    private int capacity = 0;
    private static final Stack[] stacks;
    static{
        stacks = new Stack[5]; //n stacks that we are going to assume in qual to 5
        stacks[0] = new Stack(10, null);//for now we assume the capacity (m) is 10
        stacks[1] = new Stack(10, null);
        stacks[2] = new Stack(10, null);
        stacks[3] = new Stack(10, null);
        stacks[4] = new Stack(10, null);
    }

    public Stack(int m, Node head) {
        this.head = head;
        capacity = m;
        top = null;
    }



    public int getStackNumber(){
        return stackNumber;
    }

    //O(1)
    public void push(Car car) {
        Node temp = new Node(car);
        if(head == null){
            head = temp;
        }
        else {
            temp.next = top;
            top = temp;
        }
        // what if the stack is full?
    }
    //O(1)
    public Car pop() {
        if (top == null) {
            return null; // stack in empty
        }
        Node temp = top;
        top = top.next;
        Car c = temp.car;
        temp = null;
        return c;
    }
    //O(1)
    public Car top() {//or peek
        if (top == null) {
            return null; // stack is empty
        }
        return top.car;
    }
    //O(1)
    public boolean isEmpty() {
        return (top == null);
    }

    public int find(int carID){
        //stack number
        //which node the car is in
    }

    public static void mergeSort(int[] data, int low, int high){
            if(data.length == 1){
                return;
            }
            else{
                int mid = (high + low) / 2;
                mergeSort(data, 0, mid - 1);
                mergeSort(data, mid+1, high);
            }
        }
    public boolean isFull(Stack s) {
        Node curr = head;
        int pos = 0;
        while (curr != null) {
            pos++;
            curr = curr.next;
        }
        if (pos == s.capacity) {
            return true;
        }
        else{
            return false;
        }
    }

    public Stack findAvailableStack(){
        for(Stack s: stacks){
            if(!isFull(s)){
                return s;
            }
        }
        return null;
    }

}
