// September 12th, 2020
package _2016;

public class _19b {

    public static void main(String[] args) {

        int elfCount = 3004953;

        CircularLinkedList<Elf> elves = new CircularLinkedList<>();
        for (int i = 0; i < elfCount; i++) elves.add(new Elf(i + 1));

        Node<Elf> node = elves.getHead();
        Node<Elf> opposite = node;
        for (int i = 0; i < elfCount >> 1; i++) opposite = opposite.getNext();

        while (node.getNext() != node) {
            node.getItem().takeFrom(opposite.getItem());
            Node<Elf> oldOpposite = opposite;
            opposite = opposite.getNext();
            if (elfCount % 2 != 0) opposite = opposite.getNext();
            oldOpposite.join();
            node = node.getNext();
            elfCount--;
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
