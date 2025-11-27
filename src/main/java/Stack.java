//this Stack class is implemented using LinkedList
class Node{
    public int data;
    public Node next;

    public Node(int data){
        this.data = data;
        next = null;
    }
}
public class Stack {
    private Node top;
    private int capacity;
    public Stack(int m) {
        capacity = m;
        top = null;
    }
    //O(1)
    public void push(int size) {
        Node temp = new Node(size);
        temp.next = top;
        top = temp;
        // what if the stack is full?
    }
    //O(1)
    public int pop() {
        if (top == null) {
            return -1; // stack in empty
        }
        Node temp = top;
        top = top.next;
        int value = temp.data;
        temp = null;
        return value;
    }
    //O(1)
    public int top() {//or peek
        if (top == null) {
            return -1; // stack is empty
        }
        return top.data;
    }
    //O(1)
    public boolean isEmpty() {
        return (top == null);
    }
}
