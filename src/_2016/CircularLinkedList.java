// September 12th, 2020 - Used in 19a
package _2016;

public class CircularLinkedList<T> {

    private Node<T> head;

    public CircularLinkedList() { head = null; }
    public CircularLinkedList(T item) { head = new Node<>(item); }

    public Node<T> getHead() { return head; }

    public void add(T item) {
        Node<T> node = new Node<>(item);
        if (head == null) {
            head = node;
            head.setPrevious(node);
            head.setNext(node);
        } else {
            node.setPrevious(head.getPrevious());
            node.setNext(head);
            head.getPrevious().setNext(node);
            head.setPrevious(node);
        }
    }

    @Override
    public String toString() {
        if (head == null) return "[]";
        StringBuilder sb = new StringBuilder("[ " + head);
        Node<T> node = head;
        while (node.getNext() != head) {
            node = node.getNext();
            sb.append(", ").append(node);
        }
        return sb.toString() + ", ... ]";
    }

}
