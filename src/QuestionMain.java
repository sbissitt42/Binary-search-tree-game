import java.io.*;
import java.util.Scanner;

public class QuestionMain {
    public static void main(String[] args) throws FileNotFoundException {
        QuestionTree game = new QuestionTree();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the question program.");
        if (game.yesTo("Do you want to read in the previous tree?")) {
            Scanner fileInput = new Scanner(new File("question.txt"));
            game.read(fileInput);
            fileInput.close();
        }

        do {
            System.out.println();
            System.out.println("Please think of an object for me to guess.");
            game.askQuestions();
            System.out.println();
        } while (game.yesTo("Do you want to go again?"));

        PrintStream output = System.out;

        System.out.println("Thank you for playing!");
    }
}
