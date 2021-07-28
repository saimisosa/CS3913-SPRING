// Saimanasa Juluru
// CS 3913
// Intro to Java Programming
// create a simple ingredient and recipe class with a hard coded main function to test

public class hw1 {
    public static void main(String []args) {
        // create a recipe (in this case, scrambled eggs (yum)
        Ingredient butter = new Ingredient(0.5, "butter");
        Ingredient salt = new Ingredient(0.1, "salt");
        Ingredient pepper = new Ingredient(0.1, "pepper");
        Ingredient eggs = new Ingredient(2, "eggs");

        Ingredient[] ingredients = new Ingredient[4];
        ingredients[0] = butter;
        ingredients[1] = salt;
        ingredients[2] = pepper;
        ingredients[3] = eggs;

        Step one = new Step(1, "Crack two eggs and whisk them.");
        Step two = new Step(2, "Put a saucepan on high heat.");
        Step three = new Step(3, "Put unsalted butter in the sauce pan");
        Step four = new Step(4, "Pour the eggs in and move them around with a spatula consistently");
        Step five = new Step(5, "Once the eggs start to become solid, put more butter and salt/pepper to your taste.");
        Step six = new Step(6, "Turn off pan.");
        Step seven = new Step(7, "Put scrambled eggs in a plate and enjoy!");

        Step[] steps = new Step[7];
        steps[0] = one;
        steps[1] = two;
        steps[2] = three;
        steps[3] = four;
        steps[4] = five;
        steps[5] = six;
        steps[6] = seven;

        Recipe scrambledEggs = new Recipe(ingredients, steps);
        System.out.println(scrambledEggs);
    }
}

class Ingredient {
    double unit;
    String item;

    Ingredient(double _unit, String _item) {
        unit = _unit;
        item = _item;
    }

    @Override
    public String toString() {
        return "\t - " + unit + " units of " + item + "\n";
    }
}

class Step {
    int number;
    String instruction;

    Step(int _number, String _instruction) {
        number = _number;
        instruction = _instruction;
    }

    @Override
    public String toString() {
        return "\t" + number + ": " + instruction + "\n";
    }

}

class Recipe {
    Ingredient[] ingredients;
    Step[] steps;

    Recipe(Ingredient[] _ingredients, Step[] _steps) {
        ingredients = _ingredients;
        steps = _steps;
    }

    @Override
    public String toString() {
        String output = "Ingredients: \n";
        // returns ingredients from location in array
        for (Ingredient ingredient : ingredients) {
            output += (ingredient + " ");
        }

        // two blank lines (\r\n\r\n)
        output += "\r\n\r\n";
        output += "Steps: \n";
        // steps (one per line)
        for (Step step : steps) {
            output += (step + " ");
        }

        return output;
    }
}
