//this Stack class is implemented using LinkedList
import java.util.Scanner;
public class Stack {
    public Node head = null;
    private int stackNumber;
    private int capacity = 0;
    private static final Stack[] stacks;
    static{
        stacks = new Stack[5]; // we assume there are 5 stacks in the parking, n = 5
        for(int i = 0; i < 5; i++) {
            stacks[i] = new Stack(5); //we assume m = 5
            stacks[i].setStackNumber(i);
        }

    }
    public Stack() {
        this.capacity = 5;
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
    public void push(Car car) {//it's the same as the addFirst function in linked list
        Node temp = new Node(car);
        if(head == null){
            head = temp;
        }
        else {
            temp.next = head;
            head = temp;
        }
    }
    //O(1)
    public Car pop(){
        if(head == null){
            return null; //stack is empty
        }
        Node temp = head;
        head= head.getNext();
        return temp.getCar();
    }
    //remove a car by id
    public static Car pop(int carID) {
        int[] numbers = find(carID);
        if(numbers[0] == -1 || numbers[1] == -1){
            System.out.println("No car with such carID found.");
            return null;
        }
        Stack stack = stacks[numbers[0]];
        if (stack.head == null) {
            return null; // stack is empty
        }
        if(stack.head.getCar().getCarID() == carID){//if the car is at the top
            return stack.pop();
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
        if (head == null) {
            return null; // stack is empty
        }
        return head.getCar();
    }
    //O(1)
    public boolean isEmpty() {
        return (head == null);
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
        return new int[] {-1, -1};
    }

    public static Stack order(int stackNumber){
        Stack c = stacks[stackNumber];
        c.head = MergeSort.mergeSort(c.head);
        Stack result = new Stack(c.capacity);
        result.setStackNumber(stackNumber);
        // since push adds to head, we need to reverse the sorted list to maintain order
        // first, we reverse the sorted list
        Node reversedHead = null;
        Node curr = c.head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = reversedHead;
            reversedHead = curr;
            curr = next;//??
        }
        // now push from reversed list to get correct stack order
        curr = reversedHead;
        while (curr != null) {
            result.push(curr.getCar());
            curr = curr.next;
        }
        // update the original stack
        stacks[stackNumber] = result;
        return result;
    }
    public static void display(Stack D){
        if(D.isEmpty()){
            System.out.println("This section is empty.");
        }
        else {
            Node curr = D.head;
           while(curr != null){
               System.out.println(curr.getCar().getCarID() + " ");
               curr = curr.getNext();
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
            while (curr != null) {
                System.out.println(curr.getCar().getCarID() + " ");
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
        return pos == capacity;
    }

    public static Stack getStack(int stackNumber){
        if(stackNumber >= 0 && stackNumber < stacks.length) {
            return stacks[stackNumber];
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
        while(!stack1.isEmpty() && !stack2.isFull()){
            Car car = stack1.pop();
            if(car != null){
                stack2.push(car);
            }
        }
        if(!stack1.isEmpty()){
            for(int i = 0; i < stacks.length; i++){
                if(i != stackNumber2 && !stacks[i].isFull()){
                    while(!stack1.isEmpty() && !stacks[i].isFull()){
                        Car car = stack1.pop();
                        if(car != null){
                            stacks[i].push(car);
                        }
                    }
                }
            }
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