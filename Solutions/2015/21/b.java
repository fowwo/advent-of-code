// September 1st, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class b {

    public static void main(String[] args) {

        Fighter boss = null;

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            boss = new Fighter(
                    Integer.parseInt(s.nextLine().split(": ")[1]),
                    Integer.parseInt(s.nextLine().split(": ")[1]),
                    Integer.parseInt(s.nextLine().split(": ")[1]));
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Item> weapons = new ArrayList<>();
        List<Item> armors = new ArrayList<>();
        List<Item> rings = new ArrayList<>();

        weapons.add(new Item(8, 4, 0));
        weapons.add(new Item(10, 5, 0));
        weapons.add(new Item(25, 6, 0));
        weapons.add(new Item(40, 7, 0));
        weapons.add(new Item(74, 8, 0));

        armors.add(new Item(13, 0 , 1));
        armors.add(new Item(31,  0, 2));
        armors.add(new Item(53,  0, 3));
        armors.add(new Item(75,  0, 4));
        armors.add(new Item(102,  0, 5));

        rings.add(new Item(25,  1, 0));
        rings.add(new Item(50,  2, 0));
        rings.add(new Item(100,  3, 0));
        rings.add(new Item(20,  0, 1));
        rings.add(new Item(40,  0, 2));
        rings.add(new Item(80,  0, 3));

        // Create all loadouts
        List<List<Item>> loadouts = new ArrayList<>();
        for (Item weapon : weapons) {
            List<Item> loadout = new ArrayList<>();
            loadout.add(weapon);
            loadouts.add(loadout); // 1 weapon, 0 armor, 0 rings

            for (Item ring : rings) {
                List<Item> copy = new ArrayList<>(loadout);
                copy.add(ring);
                loadouts.add(copy); // 1 weapon, 0 armor, 1 ring
                for (Item ring2 : rings) {
                    List<Item> copy2 = new ArrayList<>(copy);
                    if (ring != ring2) {
                        copy2.add(ring2);
                        loadouts.add(copy2); // 1 weapon, 0 armor, 2 rings
                    }
                }
            }

            for (Item armor : armors) {
                List<Item> copy = new ArrayList<>(loadout);
                copy.add(armor);
                loadouts.add(copy); // 1 weapon, 1 armor, 0 rings
                for (Item ring : rings) {
                    List<Item> copy2 = new ArrayList<>(copy);
                    copy2.add(ring);
                    loadouts.add(copy2); // 1 weapon, 1 armor, 1 ring
                    for (Item ring2 : rings) {
                        List<Item> copy3 = new ArrayList<>(copy2);
                        if (ring != ring2) {
                            copy3.add(ring2);
                            loadouts.add(copy3); // 1 weapon, 1 armor, 2 rings
                        }
                    }
                }
            }
        }

        // Find maximum cost loss
        int maximumCost = 0;
        for (List<Item> loadout : loadouts) {
            int totalCost = 0;
            int totalDamage = 0;
            int totalArmor = 0;
            for (Item item : loadout) {
                totalCost += item.getCost();
                totalDamage += item.getDamage();
                totalArmor += item.getArmor();
            }
            Fighter player = new Fighter(100, totalDamage, totalArmor);

            // Fight
            int turn = 0;
            int playerHP = 100;
            int bossHP = boss.getHP();
            while (playerHP > 0 && bossHP > 0) {
                if (turn == 0) {
                    bossHP -= Math.max(player.damage - boss.armor, 1);
                    turn = 1;
                } else {
                    playerHP -= Math.max(boss.damage - player.armor, 1);
                    turn = 0;
                }
            }
            if (playerHP <= 0 && totalCost > maximumCost) {
                maximumCost = totalCost;
            }
        }

        System.out.println(maximumCost);

    }

    public static class Fighter {

        private int hp;
        private int damage;
        private int armor;

        public Fighter(int hp, int damage, int armor) {
            this.hp = hp;
            this.damage = damage;
            this.armor = armor;
        }

        public int getHP() { return hp; }
        public int getDamage() { return damage; }
        public int getArmor() { return armor; }

    }

    public static class Item {

        private int cost;
        private int damage;
        private int armor;

        public Item(int cost, int damage, int armor) {
            this.cost = cost;
            this.damage = damage;
            this.armor = armor;
        }

        public int getCost() { return cost; }
        public int getDamage() { return damage; }
        public int getArmor() { return armor; }

    }

}
