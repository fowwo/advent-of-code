// June 1st, 2020
package _2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class _14b {

    public static void main(String[] args) {

        try {

            File f = new File("input/2015/14.txt");
            Scanner s = new Scanner(f);

            ArrayList<Reindeer> reindeer = new ArrayList<>();

            while (s.hasNextLine()) {

                String[] line = s.nextLine().split(" ");
                reindeer.add(new Reindeer(0, Integer.parseInt(line[3]), Integer.parseInt(line[6]), Integer.parseInt(line[13])));

            }
            s.close();

            for (int i = 1; i <= 2503; i++) {

                int max = 0;
                for (Reindeer r : reindeer) {
                    max = Math.max(max, r.getDistance(i));
                }
                for (Reindeer r : reindeer) {
                    if (r.getDistance(i) == max) {
                        r.setScore(r.getScore() + 1);
                    }
                }

            }

            int score = 0;
            for (Reindeer r : reindeer) {
                score = Math.max(score, r.getScore());
            }
            System.out.println("Highest Score: " + score);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static class Reindeer {

        private int score;
        private int speed;
        private int duration;
        private int rest;

        public Reindeer(int score, int speed, int duration, int rest) {
            this.score = score;
            this.speed = speed;
            this.duration = duration;
            this.rest = rest;
        }

        public int getDistance(int time) {
            int distance = 0;
            while (time > 0) {
                distance += Math.min(duration, time) * speed;
                time -= (duration + rest);
            }
            return distance;
        }
        public int getScore() {
            return score;
        }
        public void setScore(int score) {
            this.score = score;
        }
        public int getSpeed() {
            return speed;
        }
        public void setSpeed(int speed) {
            this.speed = speed;
        }
        public int getDuration() {
            return duration;
        }
        public void setDuration(int duration) {
            this.duration = duration;
        }
        public int getRest() {
            return rest;
        }
        public void setRest(int rest) {
            this.rest = rest;
        }

    }

}
