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
    public Stack(){

    }

    public Stack(int m, Node head) {
        this.head = head;
        capacity = m;
        top = null;
    }
    public void setStackNumber(int stackNumber){
        this.stackNumber = stackNumber;
    }


    public int getStackNumber(){
        return stackNumber;
    }
    public int getCapacity(){
        return capacity;
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
        // what if the stack is full? It is handled in Queue class
    }
    //O(1)
    public Car pop(int carID) {
        int[] numbers = find(carID);
        Stack stack = new Stack();
        for(Stack s: stacks){
            if(s.getStackNumber() == numbers[0]){
                stack.setStackNumber(s.getStackNumber());
                break;
            }
        }
        if (stack.top == null) {
            return null; // stack in empty
        }
        else if(stack.head.getCar().getCarID() == carID){
            Node temp = top;
            top = top.next;
            Car c = temp.car;
            return c;//the car is permitted to leave
        }
        else{
            System.out.println("It is not possible to exit the car.");
        }
    return null;
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

    public int[] find(int carID){
        for(Stack s: stacks) {
            Node curr = head;
            int position = 1;
            while (curr != null) {
                curr = curr.getNext();
                position++;
                if (head.getCar().getCarID() == carID) {
                    return new int[]{s.stackNumber, position};
                }
            }
        }
        System.out.println("No car with such carID found.");
        return new int[] {-1, 1};
    }

    public Stack order(int stackNumber){
        Stack c = new Stack();
        for(Stack s: stacks){
            if(s.getStackNumber() == stackNumber){
                c = s;
                break;
            }
        }
        c.head = MergeSort.mergeSort(c.head);
        Node curr = c.head;
        Stack result = new Stack(c.capacity, c.head);
        while(curr != null){
            result.push(head.getCar());
            curr = curr.getNext();
        }
        return (result);
    }


    public boolean isFull() {
        Node curr = head;
        int pos = 0;
        while (curr != null) {
            pos++;
            curr = curr.next;
        }
        if (pos == capacity) {
            return true;
        }
        else{
            return false;
        }
    }

    public static Stack getStack(int stackNumber){
        for(Stack s: stacks){
            if(stackNumber == s.stackNumber){
                return s;
            }
        }
        return null;
    }
    public static Stack findAvailableStack(){
        for(Stack s: stacks){
            if(!s.isFull()){
                return s;
            }
        }
        return null;
    }

    public void relocate(int stackNumber1, int stackNumber2){
        Stack stack1 = new Stack();
        Stack stack2 = new Stack();
        for(Stack s: stacks){
            if(s.getStackNumber() == stackNumber1){
                stack1 = s;
            }
        }
        for(Stack s: stacks){
            if(s.getStackNumber() == stackNumber2){
                stack2 = s;
            }
        }
        Node curr2 = stack1.head;
        Node curr = stack2.head;
        Stack temp = new Stack(stack2.getCapacity(), stack2.head);
        while(head != null){
            temp.push(stack2.pop(curr.getCar().getCarID()));
            curr = curr.getNext();
        }
        for(int i = 0; i < stack2.getCapacity();i++){
            stack2.push(stack1.pop(curr.getCar().getCarID()));
            curr2 = curr2.getNext();
        }
        Node curr3 = temp.head;
        for(int i = 0; i < stack1.getCapacity(); i++){
            stack1.push(temp.pop(curr3.getCar().getCarID()));
            curr3 = curr3.getNext();
        }
    }


    class MergeSort{
        public static Node split(Node head){
            Node fast = head;// Move fast pointer two steps
            Node slow = head;//Move slow pointer one step until fast reaches the end
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                if (fast != null) {
                    slow = slow.next;
                }
            }

            // Split the list into two halves
            Node temp = slow.next;
            slow.next = null;
            return temp;
        }

        // Function to merge two sorted singly linked lists
        static Node merge(Node first, Node second) {

            // If either list is empty, return the other list
            if (first == null) return second;
            if (second == null) return first;

            // Pick the smaller value between first and second nodes
            if (first.getCar().getCarID() < second.getCar().getCarID()) {
                // Recursively merge the rest of the lists and
                // link the result to the current node
                first.next = merge(first.next, second);
                return first;
            }
            else {
                // Recursively merge the rest of the lists
                // and link the result to the current node
                second.next = merge(first, second.next);
                return second;
            }
        }

        // Function to perform merge sort on a singly linked list
        static Node mergeSort(Node head) {

            // Base case: if the list is empty or has only one node,
            // it's already sorted
            if (head == null || head.next == null) {
                return head;
            }

            // Split the list into two halves
            Node second = split(head);

            // Recursively sort each half
            head = mergeSort(head);
            second = mergeSort(second);

            // Merge the two sorted halves
            return merge(head, second);
        }
        }
    }
