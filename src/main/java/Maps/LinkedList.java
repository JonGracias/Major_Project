package Maps;

public class LinkedList<T> {
    Node<T> head;
    Node<T> tail = null;
    int size = 0;
    static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

    }

    void addNode(T data) {
        Node<T> temp = new Node<>(data);
        temp.next = null;
        temp.prev = null;

        if(head == null) {
            head = tail = temp;
            head.prev = null;

        }else{
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
        tail.next = null;

        size++;

    }

    void printMiddle() {
        Node<T> slow_ptr = head;
        Node<T> fast_ptr = head;

        while (fast_ptr != null && fast_ptr.next != null) {

            fast_ptr = fast_ptr.next.next;
            slow_ptr = slow_ptr.next;

        }
        assert slow_ptr != null;
        System.out.println("the Middle element is [" + slow_ptr.data + "]");

    }

    public void removeLast() {
        if (head == null) {
            System.out.println("Doubly linked list is empty");
        }
        tail = tail.prev;
        size--;

    }

    public T getLast() {
        if (tail == null) {
            System.out.println("tail is empty");
        }
        return tail.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    //print all the nodes of doubly linked list
    public StringBuilder printNodes() {
        StringBuilder sb = new StringBuilder();
        //Node current will point to head
        Node<T> current = head;
        if (head == null) {
            System.out.println("Doubly linked list is empty");
        }
        System.out.println("Nodes of doubly linked list: ");
        while (current != null) {
            //Print each node and then go to next.
            sb.append(current.data);
            sb.append(System.lineSeparator());
            System.out.println(current.data + "");
            current = current.next;
        }
        return sb;
    }
    public void clear() {

        size = 0;
    }

}
