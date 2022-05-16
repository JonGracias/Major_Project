package Maps;

public class list<T> {
    node<T> head;
    node<T> tail = null;

    void addNode(T data) {
        node<T> temp = new node<>(data);
        temp.next = head;
        temp.prev = null;

        if (head != null)
            head.prev = temp;

        head = temp;
    }

    void printMiddle() {
        node<T> slow_ptr = head;
        node<T> fast_ptr = head;

        while (fast_ptr != null && fast_ptr.next != null) {

            fast_ptr = fast_ptr.next.next;
            slow_ptr = slow_ptr.next;

        }
        assert slow_ptr != null;
        System.out.println("the Middle element is [" + slow_ptr.data + "] \n");

    }

    public void removeLast() {
        if (head == null) {
            System.out.println("Doubly linked list is empty");
        }
        tail = tail.prev;

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
    public void printNodes() {
        //Node current will point to head
        node<T> current = head;
        if (head == null) {
            System.out.println("Doubly linked list is empty");
        }
        System.out.println("Nodes of doubly linked list: ");
        while (current != null) {
            //Print each node and then go to next.
            System.out.print(current.data + " ");
            current = current.next;
        }
    }
}
