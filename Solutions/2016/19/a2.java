// September 12th, 2020
public class a2 {

    public static void main(String[] args) {

        CircularLinkedList<Elf> elves = new CircularLinkedList<>();
        for (int i = 0; i < 3004953; i++) elves.add(new Elf(i + 1));

        Node<Elf> node = elves.getHead();
        while (node.getNext() != node) {
            node.getItem().takeFrom(node.getNext().getItem());
            node.getNext().join();
            node = node.getNext();
        }

        System.out.println(node.getItem());

    }

    public static class Elf {

        private int id;
        private int presents;

        public Elf(int id) {
            this.id = id;
            this.presents = 1;
        }

        public int getID() { return id; }
        public int getPresents() { return presents; }
        public void setPresents(int presents) { this.presents = presents; }

        public void takeFrom(Elf elf) {
            presents += elf.getPresents();
            elf.setPresents(0);
        }

        @Override
        public String toString() { return "Elf " + id; }
    }

}
