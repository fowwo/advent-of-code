// September 1st, 2020
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class a {

    public static void main(String[] args) {

        int bossStartingHP = -1;
        int bossDamage = -1;

        try {

            File f = new File("input.txt");
            Scanner s = new Scanner(f);
            bossStartingHP = Integer.parseInt(s.nextLine().split(": ")[1]);
            bossDamage = Integer.parseInt(s.nextLine().split(": ")[1]);
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create all permutations with duplicates
        final int maxSpells = 10; // an attempt to prevent heap overflows
        String[] spells = { "Magic Missile", "Drain", "Shield", "Poison", "Recharge" };
        List<List<String>> loadouts = new ArrayList<>();
        for (String s : spells) {
            loadouts.add(new ArrayList<String>(Collections.singleton(s)));
        }
        for (int i = 0; i < maxSpells - 1; i++) {
            List<List<String>> newLoadouts = new ArrayList<>();
            for (List<String> loadout : loadouts) {
                for (String spell : spells) {
                    if (validSpell(spell, loadout)) {
                        List<String> copy = new ArrayList<>(loadout);
                        copy.add(spell);
                        newLoadouts.add(copy);
                    }
                }
            }
            loadouts = newLoadouts;
        }

        // Find minimum cost win
        int minL = 0;
        int minimumCost = 10000;
        for (int j = 0; j < loadouts.size(); j++) {
            List<String> loadout = loadouts.get(j);
            int totalMana = 0;
            int playerHP = 50;
            int mana = 500;
            int bossHP = bossStartingHP;

            // System.out.println(loadout);

            // Fight
            int turn = 0;
            int i = 0;
            Map<String, Integer> effects = new HashMap<>();
            while (playerHP > 0 && mana > 0 && bossHP > 0 && (i < maxSpells || turn == 1)) {

                if (turn == 0) {
                    // System.out.println("\n--- Player Turn ---");
                } else {
                    // System.out.println("\n--- Boss Turn ---");
                }

                // Spell duration
                List<String> removeList = new ArrayList<>();
                for (String spell : effects.keySet()) {
                    effects.put(spell, effects.get(spell) - 1);
                    switch (spell) {
                        case "Poison":
                            // System.out.println("\tPoison deals 3 damage > Boss HP: " + bossHP + " -> " + (bossHP - 3) + " (Spell timer: " + effects.get(spell) + ")");
                            bossHP -= 3;
                            break;
                        case "Recharge":
                            // System.out.println("\tRecharge provides 101 mana > Mana: " + mana + " -> " + (mana + 101) + " (Spell timer: " + effects.get(spell) + ")");
                            mana += 101;
                            break;
                    }
                    if (effects.get(spell) == 0) {
                        removeList.add(spell);
                        // System.out.println("\t" + spell + " has worn off.");
                    }
                }
                for (String spell : removeList) {
                    effects.remove(spell);
                }

                // Attack
                if (turn == 0) {
                    String spell = loadout.get(i);
                    // System.out.print("\tPlayer casts " + spell + " > Mana: " + mana + " -> ");
                    switch (spell) {
                        case "Magic Missile":
                            totalMana += 53;
                            mana -= 53;
                            // System.out.print(mana + " | Boss HP: " + bossHP + " -> " + (bossHP - 4) + "\n");
                            bossHP -= 4;
                            break;
                        case "Drain":
                            totalMana += 73;
                            mana -= 73;
                            // System.out.print(mana + " | Boss HP: " + bossHP + " -> " + (bossHP - 2) + " | Player HP: " + playerHP + " -> " + (playerHP + 2) + "\n");
                            bossHP -= 2;
                            playerHP += 2;
                            break;
                        case "Shield":
                            totalMana += 113;
                            mana -= 113;
                            // System.out.print(mana + "\n");
                            effects.put(spell, 6);
                            break;
                        case "Poison":
                            totalMana += 173;
                            mana -= 173;
                            // System.out.print(mana + "\n");
                            effects.put(spell, 6);
                            break;
                        case "Recharge":
                            totalMana += 229;
                            mana -= 229;
                            // System.out.print(mana + "\n");
                            effects.put(spell, 5);
                            break;
                    }
                    turn = 1;
                    i++;
                } else if (bossHP > 0) {
                    if (effects.get("Shield") != null) {
                        // System.out.println("\tBoss attacks Shield > Player HP: " + playerHP + " -> " + (playerHP - Math.max(bossDamage - 7, 1)));
                        playerHP -= Math.max(bossDamage - 7, 1);
                    } else {
                        // System.out.println("\tBoss attacks > Player HP: " + playerHP + " -> " + (playerHP - bossDamage));
                        playerHP -= bossDamage;
                    }
                    turn = 0;
                }
            }
            if (playerHP > 0 && mana > 0 && bossHP <= 0 && totalMana < minimumCost) {
                minimumCost = totalMana;
                minL = j;
            }
        }

        System.out.println(loadouts.get(minL));
        System.out.println(minimumCost);

    }

    public static boolean validSpell(String spell, List<String> loadout) {
        int duration;
        switch (spell) {
            case "Shield":
            case "Poison":
                duration = 6;
                break;
            case "Recharge":
                duration = 5;
                break;
            default:
                return true;
        }
        // The source of my frustrations; it used to be: i < duration - 1... which is incorrect. The duration also goes down on the boss's turn.
        for (int i = 0; i < duration / 2 - 1 && i < loadout.size(); i++) {
            if (loadout.get(loadout.size() - 1 - i).equals(spell)) {
                return false;
            }
        }
        return true;
    }

}
