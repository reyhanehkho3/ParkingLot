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