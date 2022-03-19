import tree_body.Traversal;
import tree_body.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        run();
        testTree();
    }

    private static void run() {
        String path = "C:\\Users\\ALmostafa\\IdeaProjects\\RelearningAboutJavaAgain\\Tree-avl\\src\\tree_body\\dictionary.txt";
        textInterface(new Tree(), path);
    }

    private static void textInterface(Tree tree, String path) {
        System.out.println(help());
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Enter a number:");
            String command = scanner.nextLine();
            int i;
            try {
                i = Integer.parseInt(command);
            } catch (NumberFormatException e) {
                System.out.println("Unexpected input");
                continue;
            }
            switch (i) {
                case 1 -> load(tree, path);
                case 2 -> System.out.println("Size = " + tree.getSize());
                case 3 -> {
                    System.out.println("Enter the word: ");
                    command = scanner.nextLine();
                    if (!tree.insert(command)) {
                        System.out.println("ERROR: Word " + command + " already in the dictionary!");
                    }
                }
                case 4 -> {
                    System.out.println("Enter the word: ");
                    command = scanner.nextLine();
                    if (tree.searchData(command) != null) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                }
                case 5 -> {
                    System.out.println("Enter the word: ");
                    command = scanner.nextLine();
                    if (tree.searchData(command) != null) {
                        if (tree.delete(command) != null) {
                            System.out.println("YES");
                        }
                    } else {
                        System.out.println("Word " + command + ", isn't in dictionary.");
                    }
                }
                case 6 -> System.out.println("Tree height = " + tree.getTreeHeight());
                case 7 -> System.out.println(help());
                case 8 -> running = false;
                default -> System.out.println("Unexpected input");
            }
        }
    }

    private static String help() {
        return """
                Enter a number:\s
                1 - to load the dictionary.
                2 - to print the size of the dictionary.
                3 - to insert a word.
                4 - to look-up a word.
                5 - to remove a word.
                6 - to print tree height.
                7 - to help.
                8 - to exit.""";

    }

    private static void load(Tree tree, String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String stringLine;
            while (true) {
                stringLine = bufferedReader.readLine();
                if (stringLine == null) {
                    break;
                }
                tree.insert(stringLine);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    private static void testTree() {
        Tree tree = new Tree();
        String[] inputs = {"e", "b", "f", "a", "d", "g", "c", "z", "q", "w", "r", "m", "i", "o"};
        for (String s : inputs) {
            tree.insert(s);
            System.out.print("In order traversal: ");
            tree.traverse(Traversal.IN_ORDER);
        }
        System.out.println("Tree height: " + tree.getTreeHeight());
        System.out.println("Tree size: " + tree.getSize());
        for (String s : inputs) {
            System.out.println("relative of: " + s + " = " + tree.delete(s));
            System.out.print("In order traversal: ");
            tree.traverse(Traversal.IN_ORDER);
        }
    }
}