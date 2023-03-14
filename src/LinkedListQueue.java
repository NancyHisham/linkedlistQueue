import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IQueue {
  /*** Inserts an item at the queue front.*/
  public void enqueue(Object item);
  /*** Removes the object at the queue rear and returnsit.*/
  public Object dequeue();
  /*** Tests if this queue is empty.*/
  public boolean isEmpty();
  /*** Returns the number of elements in the queue*/
  public int size();
}

public class LinkedListQueue implements IQueue {

    private SingleLinkedList que;

    public LinkedListQueue() {
        que = new SingleLinkedList();
    }
    
    /*** Inserts an item at the queue front.*/
    public void enqueue(Object item) {
        que.add(0, item);
    }
    
    /*** Removes the object at the queue rear and returnsit.*/
    public Object dequeue() {
        if (que.isEmpty()) {
            System.out.println("Error");
            System.exit(0);
        }
        Object element = que.get(que.size() - 1);
        que.remove(que.size() - 1);
        return element;
    }
    
    /*** Tests if this queue is empty.*/
    public boolean isEmpty(){
        return que.isEmpty();
    }
    
    /*** Returns the number of elements in the queue*/
    public int size(){
        return que.size();
    }
    
    /*** print queue in array format*/
    public String print() {
    	String S="";
    	S=S+'[';
        //System.out.print("[");
        for (int i = 0; i < que.size(); i++) {
        	S=S+que.get(i);
        	//System.out.print(que.get(i));
            if (i != que.size() - 1) {
            	S=S+',';
                S=S+' ';}
            	//System.out.print(", ");
        }
        S=S+']';
        //System.out.print("]");
        return S;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");
    
        LinkedListQueue que = new LinkedListQueue();

        if(s.length > 1 || !s[0].isEmpty())
            for (int i = s.length - 1; i >= 0; i--)
                que.enqueue(Integer.parseInt(s[i]));

        String keyWord = sc.nextLine();

        switch (keyWord) {

            case "enqueue":
                que.enqueue(sc.nextInt());
                System.out.println(que.print());
                break;

            case "dequeue":
                que.dequeue();
                System.out.println(que.print());
                break;

            case "isEmpty":
                if (que.isEmpty())
                    System.out.print("True");
                else
                    System.out.print("False");
                break;

            case "size":
                System.out.print(que.size());
                break;

            default:
                System.out.print("Error");
                break;
        }
    }
}

interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     *
     * @param index
     * @param element
     */
    public void add(int index, Object element);

    /**
     * Inserts the specified element at the end of the list.
     *
     * @param element
     */
    public void add(Object element);

    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param index
     * @param element
     */
    public void set(int index, Object element);

    /**
     * Removes all of the elements from this list.
     */
    public void clear();

    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index
     */
    public void remove(int index);

    /**
     * @return the number of elements in this list.
     */
    public int size();

    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(int fromIndex, int toIndex);

    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}

class SingleLinkedList implements ILinkedList {
    /* Implement your linked list class here*/
    private Node head;
    private int size;

    public SingleLinkedList() {
        head = new Node(null, null);
        size = 0;
    }

    //Inserts a specified element at the specified position in the list
    public void add(int index, Object element) {
        if (index > size || index < 0) {
            System.out.println("Error");
            System.exit(0);
        }

        Node e = new Node(element, null);
        Node v = head;
        int i = 0;
        if (index == 0) {
            e.setNext(head);
            head = e;
        } else {
            while (i < index - 1) {
                v = v.getNext();
                i++;
            }

            e.setNext(v.getNext());
            v.setNext(e);

        }
        ++size;
    }

    //Inserts the specified element at the end of the list
    public void add(Object element) {
        Node p = head;
        Node e = new Node(element, null);
        if (size == 0) head = e;
        while (p.getNext() != null)
            p = p.getNext();
        p.setNext(e);
        ++size;
    }

    //return the element at the specified position in this list
    public Object get(int index) {
        if (index >= size || index < 0) {
            System.out.println("Error");
            System.exit(0);
        }
        int i = 0;
        Node p = head;
        while (p.getNext() != null && i < index) {
            p = p.getNext();
            i++;
        }

        return p.getElement();
    }

    //Replaces the element at the specified position in this list with the
    //specified element.
    public void set(int index, Object element) {
        if (index >= size || index < 0) {
            System.out.println("Error");
            System.exit(0);
        }
        int i = 0;
        Node p = head;
        while (i < index) {
            p = p.getNext();
            i++;
        }
        p.setElement(element);
    }

    //Removes all of the elements from this list.
    public void clear() {
        head = null;
        size = 0;
    }

    //true if this list contains no elements.
    public boolean isEmpty() {
        return size == 0;
    }

    //Removes the element at the specified position in this list.
    public void remove(int index) {
        if (index >= size || index < 0) {
            System.out.println("Error");
            System.exit(0);
        }
        if (index == 0) head = head.getNext();
        else {
            int i = 0;
            Node p = head;
            Node q = head;
            while (i < index) {
                i++;
                q = p;
                p = p.getNext();//specified node
            }
            q.setNext(p.getNext());
            p.setNext(null);
        }
        size--;
    }

    //return the number of elements in this list.
    public int size() {
        return size;
    }

    // return a view of the portion of this list between the specified fromIndex
    // and toIndex, inclusively.
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex >= size || toIndex >= size || fromIndex > toIndex || fromIndex < 0 || toIndex < 0) {
            System.out.println("Error");
            System.exit(0);
        }

        SingleLinkedList sublist = new SingleLinkedList();
        int i = 0;
        Node p = head;
        while (i < fromIndex) {
            p = p.getNext();
            i++;
        }
        while (i <= toIndex) {
            sublist.add(p.getElement());
            p = p.getNext();
            i++;
        }
        return sublist;
    }

    public boolean contains(Object o) {
        if (size == 0) {
            System.out.println("Error");
            System.exit(0);
        }
        int i = 0;
        while (i < size) {
            if (get(i) == o)
                return true;
            i++;
        }
        return false;
    }

    public static void printList(SingleLinkedList SList) {
        System.out.print("[");
        for (int i = 0; i < SList.size; i++) {
            System.out.print(SList.get(i));
            if (i != SList.size - 1) System.out.print(", ");
        }

        System.out.print("]");
    }
}

class Node {
    private Object element;
    private Node next;

    public Node(Object e, Node n) {
        element = e;
        next = n;
    }

    public Object getElement() {
        return element;
    }

    public Node getNext() {
        return next;
    }

    public void setElement(Object e) {
        element = e;
    }

    public void setNext(Node n) {
        next = n;
    }
}