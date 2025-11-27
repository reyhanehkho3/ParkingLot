public class Queue {
    private int[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public Queue() {
        this.capacity = capacity;
        arr = new int[capacity];
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

    public void enqueue(int n) {
        if (isFull()) {
            return;
        }
        rear = (rear + 1) % capacity;
        arr[rear] = n;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            return -1;
        }
        int removed = arr[front];
        front = (front + 1) % capacity;
        size--;
        return removed;
    }

    public int peek(){
        if (isEmpty()){
            return -1;
        }
        return arr[front];
    }

    public int getSize() {
        return size;
    }
}
