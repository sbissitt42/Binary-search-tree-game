import java.io.*;
import java.util.Scanner;

public class QuestionTree {
    private QuestionNode root;
    private Scanner console;

    // Constructor to initialize the tree with a single leaf node representing "computer"
    public QuestionTree() {
        root = new QuestionNode("computer");
        console = new Scanner(System.in);
    }

    // Method to read the tree from a file and replace the current tree
    public void read(Scanner input) {
        root = readHelper(input);
    }

    // Helper method for reading the tree recursively
    private QuestionNode readHelper(Scanner input) {
        String type = input.nextLine();
        String data = input.nextLine().trim();
        QuestionNode node;
        if (type.equals("Q:")) {
            node = new QuestionNode(data);
            node.setLeft(readHelper(input));
            node.setRight(readHelper(input));
        } else {
            node = new QuestionNode(data);
        }
        return node;
    }

    // Method to write the current tree to a file
    public void write(PrintStream output) {
        writeHelper(output, root);
    }

    // Helper method for writing the tree recursively
    private void writeHelper(PrintStream output, QuestionNode node) {
        if (node != null) {
            output.println(node.isAnswer() ? "A:" : "Q:");
            output.println(node.getData());
            writeHelper(output, node.getLeft());
            writeHelper(output, node.getRight());
        }
    }

    // Method to play the game by asking the user a series of yes/no questions
    public void askQuestions() {
        root = askQuestionsHelper(root);
    }

    // Helper method for asking questions recursively
    private QuestionNode askQuestionsHelper(QuestionNode node) {
        if (node.isAnswer()) {
            if (console.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.println("Great, I got it right!");
            } else {
                node = expandTree(node);
            }
        } else {
            System.out.print(node.getData() + " ");
            if (console.nextLine().trim().equalsIgnoreCase("y")) {
                node.setLeft(askQuestionsHelper(node.getLeft()));
            } else {
                node.setRight(askQuestionsHelper(node.getRight()));
            }
        }
        return node;
    }

    // Method to expand the tree when the computer guesses incorrectly
    private QuestionNode expandTree(QuestionNode node) {
        System.out.print("Please give me a yes/no question that\n"
                + "distinguishes between your object\n"
                + "and mine--> ");
        String newQuestion = console.nextLine().trim();
        System.out.print("And what is the answer for your object? (y/n)? ");
        String newAnswer = console.nextLine().trim().toLowerCase();
        System.out.println();

        QuestionNode oldAnswerNode = new QuestionNode(node.getData());
        QuestionNode newAnswerNode = new QuestionNode(newAnswer.equals("y") ? "computer" : node.getData());
        QuestionNode newQuestionNode = new QuestionNode(newQuestion, newAnswer.equals("y") ? newAnswerNode : oldAnswerNode, newAnswer.equals("n") ? newAnswerNode : oldAnswerNode);

        return newQuestionNode;
    }

    // Method to ask the user a yes/no question until they type "y" or "n"
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }
}
