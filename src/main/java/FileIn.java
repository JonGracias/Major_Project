import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class FileIn extends JOptionPane {
    public FileIn(JTextPane pane, String filename) throws IOException, BadLocationException {
        // SimpleAttributeSet uses a Hash Table which extends a dictionary and
        // Implements Serializable and Cloneable, this is how I implement an Undoable Edit listener
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        Document doc = pane.getStyledDocument();
        docText(read(filename), attributeSet, doc);
    }

    public LinkedList<String> read(String filename) throws IOException {
        // This method adds Stings to a LinkedList in order to format the text from a file
        LinkedList<String> list = new LinkedList<>();
        // Filename for output
        File fileObj = new File(filename);
        if (fileObj.exists()) {
            Scanner fileIn = new Scanner(fileObj);
            while (fileIn.hasNextLine()) {
                String result = fileIn.nextLine();
                int length = result.length();
                // Ensures that length of each row does not exceed the length of the JTextPane
                int n = 75;
                if (n<length) {
                    n = 75 + (result.substring(75, length).indexOf(" "));
                    for (int i = 0; i < length; i += n) {
                        list.addLast(" " + result.substring(i, Math.min(length, i + n)).trim());
                        list.addLast("\n");
                    }
                } else {
                    list.addLast(" " + result.trim());
                    list.addLast("\n");
                }
            }
            fileIn.close();
            return list;

        } else
            showMessageDialog(null, "Error File not found.");
        return null;
    }
    // iterates to through the LinkedList and adds text to the Document Interface

    public static void docText(LinkedList<String> list, SimpleAttributeSet attributeSet, Document doc) throws BadLocationException {
        for (String s : list) {
            doc.insertString(doc.getLength(), String.valueOf(s), attributeSet);
        }


    }
}

//    public interface MyList<E> extends java.lang.Iterable {
//        /**
//         * Add a new element at the end of this list
//         */
//        public void add(E e);
//
//        /**
//         * Add a new element at the specified index in this list
//         */
//        public void add(int index, E e);
//
//        /**
//         * Clear the list
//         */
//        public void clear();
//
//        /**
//         * Return true if this list contains the element
//         */
//        public boolean contains(E e);
//
//        /**
//         * Return the element from this list at the specified index
//         */
//        public E get(int index);
//
//        /**
//         * Return the index of the first matching element in this list.
//         * Return -1 if no match.
//         */
//        public int indexOf(E e);
//
//        /**
//         * Return true if this list contains no elements
//         */
//        public boolean isEmpty();
//
//        /**
//         * Return the index of the last matching element in this list
//         * Return -1 if no match.
//         */
//        public int lastIndexOf(E e);
//
//        /**
//         * Remove the first occurrence of the element o from this list.
//         * Shift any subsequent elements to the left.
//         * Return true if the element is removed.
//         */
//        public boolean remove(E e);
//
//        /**
//         * Remove the element at the specified position in this list
//         * Shift any subsequent elements to the left.
//         * Return the element that was removed from the list.
//         */
//        public E remove(int index);
//
//        /**
//         * Replace the element at the specified position in this list
//         * with the specified element and return the old element.
//         */
//        public Object set(int index, E e);
//
//        /**
//         * Return the number of elements in this list
//         */
//        public int size();
//    }
//
//    public abstract class MyAbstractList<E> implements MyList<E> {
//        protected int size = 0; // The size of the list
//
//        /**
//         * Create a default list
//         */
//        protected MyAbstractList() {
//        }
//
//        /**
//         * Create a list from an array of objects
//         */
//        protected MyAbstractList(E[] objects) {
//            for (int i = 0; i < objects.length; i++)
//                add(objects[i]);
//        }
//
//        /**
//         * Add a new element at the end of this list
//         */
//        public void add(E e) {
//            add(size, e);
//        }
//
//        /**
//         * Return true if this list contains no elements
//         */
//        public boolean isEmpty() {
//            return size == 0;
//        }
//
//        /**
//         * Return the number of elements in this list
//         */
//        public int size() {
//            return size;
//        }
//
//        /**
//         * Remove the first occurrence of the element o from this list.
//         * Shift any subsequent elements to the left.
//         * Return true if the element is removed.
//         */
//        public boolean remove(E e) {
//            if (indexOf(e) >= 0) {
//                remove(indexOf(e));
//                return true;
//            } else
//                return false;
//        }
//    }
//
//    public class MyLinkedList<E> extends MyAbstractList<E> {
//        private Node<E> head, tail;
//
//        /**
//         * Create a default list
//         */
//        public MyLinkedList() {
//        }
//
//        /**
//         * Create a list from an array of objects
//         */
//        public MyLinkedList(E[] objects) {
//            super(objects);
//        }
//
//        /**
//         * Return the head element in the list
//         */
//        public E getFirst() {
//            if (size == 0) {
//                return null;
//            } else {
//                return head.element;
//            }
//        }
//
//        /**
//         * Return the last element in the list
//         */
//        public E getLast() {
//            if (size == 0) {
//                return null;
//            } else {
//                return tail.element;
//            }
//        }
//
//        /**
//         * Add an element to the beginning of the list
//         */
//        public void addFirst(E e) {
//            Node<E> newNode = new Node<E>(e); // Create a new node
//            newNode.next = head; // link the new node with the head
//            head = newNode; // head points to the new node
//            size++; // Increase list size
//
//            if (tail == null) // the new node is the only node in list
//                tail = head;
//        }
//
//        /**
//         * Add an element to the end of the list
//         */
//        public void addLast(E e) {
//            Node<E> newNode = new Node<E>(e); // Create a new for element e
//
//            if (tail == null) {
//                head = tail = newNode; // The new node is the only node in list                        (2 points)
//            } else {
//                tail.next = newNode; // Link the new with the last node                           (2 points)
//                tail = tail.next; // tail now points to the last node
//            }
//
//            size++; // Increase size
//        }
//
//
//        /**
//         * Add a new element at the specified index in this list
//         * The index of the head element is 0
//         */
//        public void add(int index, E e) {
//            if (index == 0) {
//                addFirst(e);
//            } else if (index >= size) {
//                addLast(e);
//            } else {
//                //
//                Node<E> current = head;
//                // Complete the code for this for loop to obtain the object reference for the node at index  (5 points)
//                for (int i = 0; i < index; i++) {
//                    current = head.next;
//                }
//                Node<E> temp = current.next;
//                current.next = new Node<E>(e);
//                (current.next).next = temp;
//                size++;
//            }
//        }
//
//        // Set based addAll to current list
//        public void addAll(MyAbstractList<E> list) {
//            E element;
//            for (int i = 0; i < list.size(); i++) {
//                element = list.get(i);
//                if (list.contains(element)) {  // Sets do not contain duplicates - add only if it !contain the element (2 points)
//                    addFirst(element);
//                }// Add first is the least amount of work to add the element.
//            }
//        }
//
//        // Set based removeAll to current list
//        public void removeAll(MyAbstractList<E> list) {
//            E element;
//            int rLoc;
//            for (int i = 0; i < list.size(); i++) {
//                rLoc = 0;
//                element = list.get(i);
//                while (rLoc >= 0) {
//                    // find the next location of the element to be removed.                (2 points)
//                    rLoc = indexOf(element);
//                    if (rLoc >= 0)  // Sets do not contain duplicates - remove only if it contains the element
//                        remove(rLoc);
//                }
//            }
//        }
//
//        // Retain all not implemented
//
//        // toArray()
//        public E[] toArray() {
//            // Create an array list to build the array.                                                (2 points)
//            ArrayList<E> aList = new ArrayList<E>();
//            for (int i = 0; i < size(); i++) {
//                aList.add(get(i));
//            }
//            // Use ArrayList's toArray method to return an array on objects that is cast to (E[])      (2 points)
//            return (E[]) aList.toArray();
//        }
//
//
//        /**
//         * Remove the head node and
//         * return the object that is contained in the removed node.
//         */
//        public E removeFirst() {
//            if (size == 0) {
//                return null;
//            } else {
//                Node<E> temp = head;
//                head = head.next;
//                size--;
//                if (head == null) {
//                    tail = null;
//                }
//                return temp.element;
//            }
//        }
//
//        /**
//         * Remove the last node and
//         * return the object that is contained in the removed node.
//         */
//        public E removeLast() {
//            if (size == 0) {
//                return null;
//            } else if (size == 1) {
//                Node<E> temp = head;
//                head = tail = null;
//                size = 0;
//                return temp.element;
//            } else {
//                Node<E> current = head;
//
//                for (int i = 0; i < size - 2; i++) {
//                    current = current.next;
//                }
//
//                Node<E> temp = tail;
//                tail = current;
//                tail.next = null;
//                size--;
//                return temp.element;
//            }
//        }
//
//        /**
//         * Remove the element at the specified position in this list.
//         * Return the element that was removed from the list.
//         */
//        public E remove(int index) {
//            if (index < 0 || index >= size) {
//                return null;
//            } else if (index == 0) {
//                return removeFirst();
//            } else if (index == size - 1) {
//                return removeLast();
//            } else {
//                Node<E> previous = head;
//
//                for (int i = 1; i < index; i++) {
//                    previous = previous.next;
//                }
//
//                Node<E> current = previous.next;
//                previous.next = current.next;
//                size--;
//                return current.element;
//            }
//        }
//
//        @Override
//        public String toString() {
//            StringBuilder result = new StringBuilder("[");
//
//            Node<E> current = head;
//            for (int i = 0; i < size; i++) {
//                result.append(current.element);
//                current = current.next;
//                if (current != null) {
//                    result.append(", "); // Separate two elements with a comma
//                } else {
//                    result.append("]"); // Insert the closing ] in the string
//                }
//            }
//
//            return result.toString();
//        }
//
//        /**
//         * Clear the list
//         */
//        public void clear() {
//            head = tail = null;
//        }
//
//        /**
//         * Return true if this list contains the element o
//         */
//        public boolean contains(Object o) {
//            // Start the current node at the head of the list   (3 points)
//            Node<E> current = head;
//            for (int i = 0; i < size; i++) {
//                if (current.element.equals(o))
//                    return true;
//                // Assign the current to the next reference       (2 points)
//                current = current.next;
//            }
//
//            return false;
//        }
//
//        /**
//         * Return the element from this list at the specified index
//         */
//        public E get(int index) {
//            // Implement it in this exercise
//            if (index < 0 || index > size - 1)
//                return null;
//
//            Node<E> current = head;
//            for (int i = 0; i < index; i++)
//                current = current.next;
//
//            // Return the current element from the linked link.   (3 points)
//            return current.element;
//        }
//
//        /**
//         * Returns the index of the first matching element in this list.
//         * Returns -1 if no match.
//         */
//        public int indexOf(Object o) {
//            Node<E> current = head;
//            // Implement a for loop to iterate the index i from 0 to the size-1.  (6 points)
//            for (int i = 0; i < size - 1; i++) {
//                if (current.element.equals(o))
//                    return i;
//                current = current.next;
//            }
//
//            return -1;
//        }
//
//        /**
//         * Returns the index of the last matching element in this list
//         * Returns -1 if no match.
//         */
//        public int lastIndexOf(Object o) {
//            int lastIndex = -1;
//            Node<E> current = head;
//            // Implement a for loop to iterate the index i from 0 to the size-1.  (6 points)
//            for (int i = 0; i < size - 1; i++) {
//                if (current.element.equals(o))
//                    lastIndex = i;               // Assign the lastIndex to i             (2 points)
//                current = current.next;
//            }
//
//            return lastIndex;
//        }
//
//        /**
//         * Replace the element at the specified position in this list
//         * with the specified element.
//         */
//        public E set(int index, E e) {
//            // Make sure the index is between 0 and the size-1                    (2 points)
//            if (index < 0 || index > size - 1)
//                return null;
//
//            Node<E> current = head;
//            for (int i = 0; i < index; i++)
//                current = current.next;
//
//            E temp = current.element;
//            current.element = e;
//
//            return temp;
//        }
//
//        /**
//         * Override the iterator method defined in Iterable
//         */
//        public java.util.Iterator<E> iterator() {
//            return new LinkedListIterator();
//        }
//
//        private class LinkedListIterator
//                implements java.util.Iterator<E> {
//            private Node<E> current = head; // Current index
//
//            public boolean hasNext() {
//                return (current != null);
//            }
//
//            public E next() {
//                E e = current.element;
//                current = current.next;
//                return e;
//            }
//
///*      public void remove() {
//        System.out.println("Implementation left as an exercise");
//      }
//*/
//        }
//
//
//        private class Node<E> {
//            E element;
//            Node<E> next;
//
//            public Node(E element) {
//                this.element = element;
//            }
//        }
//    }
//}