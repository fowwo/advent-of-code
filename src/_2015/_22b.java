// September 1st, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _22b {

    public static void main(String[] args) {

        final int playerHP = 50;
        final int mana = 500;
        int bossHP = -1;
        int bossDamage = -1;

        try {

            File f = new File("input/2015/22.txt");
            Scanner s = new Scanner(f);
            bossHP = Integer.parseInt(s.nextLine().split(": ")[1]);
            bossDamage = Integer.parseInt(s.nextLine().split(": ")[1]);
            s.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create initial loadouts
        System.out.println("Creating initial loadouts...");
        String[] spells = { "Magic Missile", "Drain", "Shield", "Poison", "Recharge" };
        List<List<String>> loadouts = new ArrayList<>();
        for (String s : spells) {
            loadouts.add(new ArrayList<String>(Collections.singleton(s)));
        }

        // Play
        int cost = -1;
        while (cost == -1) {
            if (loadouts.size() == 0) {
                System.out.println("\t> " + loadouts.size() + " loadouts");
                break;
            }
            loadouts = addNextSpells(loadouts, spells);
            removeLosses(loadouts, playerHP, mana, bossHP, bossDamage);
            System.out.println("\t> " + loadouts.size() + " loadouts (" + loadouts.get(0).size() + " spell" + (loadouts.get(0).size() == 1 ? "" : "s") + ")");
            cost = minimumWinningCost(loadouts, playerHP, mana, bossHP, bossDamage);
        }

        System.out.println("Minimum winning cost: " + cost);
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
        for (int i = 1; i < duration / 2 && i < loadout.size(); i++) {
            if (loadout.get(loadout.size() - i).equals(spell)) {
                return false;
            }
        }
        return true;
    }

    public static List<List<String>> addNextSpells(List<List<String>> loadouts, String[] spells) {
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
        return newLoadouts;
    }
    public static void removeLosses(List<List<String>> loadouts, int playerHP, int mana, int bossHP, int bossDamage) {
        for (int i = 0; i < loadouts.size(); i++) {
            if (playLoadout(loadouts.get(i), playerHP, mana, bossHP, bossDamage) == -1) {
                loadouts.remove(i);
                i--;
            }
        }
    }
    public static int playLoadout(List<String> loadout, int playerHP, int mana, int bossHP, int bossDamage) {

        int totalMana = 0;
        int turn = 0;
        int spellIndex = 0;
        Map<String, Integer> effects = new HashMap<>();
        while (playerHP > 0 && mana >= 0 && bossHP > 0 && (spellIndex < loadout.size() || turn == 1)) {

            // At the start of each player turn (before any other effects apply), you lose 1 hit point. If this brings you to or below 0 hit points, you lose.
            if (turn == 0) {
                playerHP--;
                if (playerHP <= 0) return -1;
            }

            // Spell duration
            List<String> removeList = new ArrayList<>();
            for (String spell : effects.keySet()) {
                effects.put(spell, effects.get(spell) - 1);
                switch (spell) {
                    case "Poison":
                        bossHP -= 3;
                        break;
                    case "Recharge":
                        mana += 101;
                        break;
                }
                if (effects.get(spell) == 0) {
                    removeList.add(spell);
                }
            }
            for (String spell : removeList) {
                effects.remove(spell);
            }

            // Attack
            if (turn == 0) {
                String spell = loadout.get(spellIndex);
                switch (spell) {
                    case "Magic Missile":
                        totalMana += 53;
                        mana -= 53;
                        bossHP -= 4;
                        break;
                    case "Drain":
                        totalMana += 73;
                        mana -= 73;
                        bossHP -= 2;
                        playerHP += 2;
                        break;
                    case "Shield":
                        totalMana += 113;
                        mana -= 113;
                        effects.put(spell, 6);
                        break;
                    case "Poison":
                        totalMana += 173;
                        mana -= 173;
                        effects.put(spell, 6);
                        break;
                    case "Recharge":
                        totalMana += 229;
                        mana -= 229;
                        effects.put(spell, 5);
                        break;
                }
                turn = 1;
                spellIndex++;
            } else if (bossHP > 0) {
                if (effects.get("Shield") != null) {
                    playerHP -= Math.max(bossDamage - 7, 1);
                } else {
                    playerHP -= bossDamage;
                }
                turn = 0;
            }
        }
        if (playerHP > 0 && mana >= 0 && bossHP <= 0) {
            return totalMana;
        } else if (playerHP <= 0 || mana < 0) {
            return -1;
        }
        return 0;
    }
    public static int minimumWinningCost(List<List<String>> loadouts, int playerHP, int mana, int bossHP, int bossDamage) {
        int minimumCost = -1;
        for (List<String> loadout : loadouts) {
            int x = playLoadout(loadout, playerHP, mana, bossHP, bossDamage);
            if (x > 0 && (x < minimumCost || minimumCost == -1)) {
                minimumCost = x;
            }
        }
        return minimumCost;
    }

}
