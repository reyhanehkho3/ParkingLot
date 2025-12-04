//this Stack class is implemented using LinkedList
import java.util.Scanner;
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
        stacks = new Stack[5]; // we assume there are 5 stacks in the parking
        for(int i = 0; i < 5; i++) {
            stacks[i] = new Stack(10, null);
            stacks[i].setStackNumber(i);
        }

    }
    public Stack(){

    }

    public Stack(int m, Node head) {
        this.head = head;
        capacity = m;
        top = null;
    }

    public Stack(int capacity) {
        this.capacity = capacity;
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
            top = temp;
        }
        else {
            temp.next = top;
            top = temp;
            head = top;
        }
    }
    //O(1)
    public static Car pop(int carID) {
        int[] numbers = find(carID);
        Stack stack = stacks[numbers[0]];
        if (stack.top == null) {
            return null; // stack in empty
        }
        else if(stack.top.getCar().getCarID() == carID){
            Node temp = stack.top;
            stack.top = stack.top.next;

            //update head if we're removing the last node
            if (stack.top == null) {
                stack.head = null;
            }
            //if head was pointing to the same node as top, update head
            else if (stack.head == temp) {
                stack.head = stack.top;
            }

            Car c = temp.car;
            return c; // the car is permitted to leave
        }
        else{
            System.out.println("It is not possible to exit the car. Enter another car ID:");
            Scanner input = new Scanner(System.in);
            int ID = input.nextInt();
            return Stack.pop(ID);
        }
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

    public static int[] find(int carID){
        for(Stack s: stacks) {
            Node curr = s.head;
            int position = 0;
            while (curr != null) {
                if (curr.getCar().getCarID() == carID) {
                    return new int[]{s.stackNumber, position};
                }
                curr = curr.getNext();
                position++;
            }
        }
        System.out.println("No car with such carID found.");
        return new int[] {-1, -1};
    }

    public static Stack order(int stackNumber){
        Stack c = stacks[stackNumber];
        c.head = MergeSort.mergeSort(c.head);
        Node curr = c.head;
        Stack result = new Stack(c.capacity, c.head);
        while(curr != null){
            result.push(curr.getCar());
            curr = curr.getNext();
        }
        return (result);
    }
    public static void display(Stack D){
        if(D.isEmpty()){
            System.out.println("This section is empty.");
        }
        else {
            Node curr = D.head;
            Stack temp = new Stack(D.getCapacity());
            for(int i = 0; i < temp.getCapacity(); i++){
                if(curr == null){
                    continue;
                }
                temp.push(curr.getCar());
                curr = curr.next;
            }
            Node currr = temp.head;
            for (int i = 0; i < temp.getCapacity(); i++) {
                if(currr == null){
                    continue;
                }

                System.out.println(currr.getCar().getCarID() + " ");
                currr = currr.getNext();
            }
        }
    }
    public static void displayNumber(int stackNumber){
        Stack D = stacks[stackNumber];
        if(D.isEmpty()){
            System.out.println("This section is empty.");
        }
        else {
            Node curr = D.head;
            for (int i = 0; i < D.getCapacity(); i++) {
                if(curr == null){
                    continue;
                }
                if(curr.getCar() != null) {
                    System.out.println(curr.getCar().getCarID() + " ");
                }
                curr = curr.getNext();
            }
        }
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

    public static void relocate(int stackNumber1, int stackNumber2){
        Stack stack1 = stacks[stackNumber1];
        Stack stack2 = stacks[stackNumber2];
        Node curr = stack1.head;
        while(!stack1.isEmpty()){
            while(!stacks[stackNumber2].isFull()){
                stack2.push(stack1.pop(curr.getCar().getCarID()));
                curr = curr.getNext();
            }
            stackNumber2++;
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
