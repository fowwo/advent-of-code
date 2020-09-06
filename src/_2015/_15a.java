// June 5th, 2020
package _2015;

public class _15a {

    public static void main(String[] args) {

        final Ingredient frosting = new Ingredient(4, -2, 0, 0, 5);
        final Ingredient candy = new Ingredient(0, 5, -1, 0, 8);
        final Ingredient butterscotch = new Ingredient(-1, 0, 5, 0, 6);
        final Ingredient sugar = new Ingredient(0, 0, -2, 2, 1);

        int max = 0;
        for (int f = 0; f <= 100; f++) {
            for (int c = 0; c <= 100 - f; c++) {
                for (int b = 0; b <= 100 - f - c; b++) {
                    for (int s = 0; s <= 100 - f - c - b; s++) {

                        int capacity = Math.max(f * frosting.getCapacity() + c * candy.getCapacity() + b * butterscotch.getCapacity() + s * sugar.getCapacity(), 0);
                        int durability = Math.max(f * frosting.getDurability() + c * candy.getDurability() + b * butterscotch.getDurability() + s * sugar.getDurability(), 0);
                        int flavor = Math.max(f * frosting.getFlavor() + c * candy.getFlavor() + b * butterscotch.getFlavor() + s * sugar.getFlavor(), 0);
                        int texture = Math.max(f * frosting.getTexture() + c * candy.getTexture() + b * butterscotch.getTexture() + s * sugar.getTexture(), 0);
                        int score = capacity * durability * flavor * texture;
                        if (score > max) {
                            max = score;
                        }

                    }
                }
            }
        }

        System.out.println(max);

    }

    public static class Ingredient {

        private int capacity;
        private int durability;
        private int flavor;
        private int texture;
        private int calories;

        public Ingredient(int capacity, int durability, int flavor, int texture, int calories) {
            this.capacity = capacity;
            this.durability = durability;
            this.flavor = flavor;
            this.texture = texture;
            this.calories = calories;
        }

        public int getCapacity() {
            return capacity;
        }
        public int getDurability() {
            return durability;
        }
        public int getFlavor() {
            return flavor;
        }
        public int getTexture() {
            return texture;
        }
        public int getCalories() {
            return calories;
        }

    }

}
