// September 12th, 2020
public class Node<T> {

    private T item;
    private Node<T> previous;
    private Node<T> next;

    public Node(T item) {
        this.item = item;
        this.previous = null;
        this.next = null;
    }

    public T getItem() { return item; }
    public Node<T> getPrevious() { return previous; }
    public Node<T> getNext() { return next; }
    public void setItem(T item) { this.item = item; }
    public void setPrevious(Node<T> node) { this.previous = node; }
    public void setNext(Node<T> node) { this.next = node; }

    public boolean join() {
        if (previous != null && next != null) {
            previous.setNext(next);
            next.setPrevious(previous);
            previous = null;
            next = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() { return item.toString(); }

}
