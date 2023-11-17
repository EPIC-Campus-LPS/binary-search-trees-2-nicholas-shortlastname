import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree BST = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        System.out.println("Fill the tree from a file: \'1\'\n" +
                "Add a value to tree: \'2\'\n" +
                "Delete a value from the tree: \'3\'\n" +
                "See if tree contains a value: \'4\'\n" +
                "Test traversals (pre, in, post): \'5\'\n" +
                "Print stats (nodes, leaf nodes, height): \'6\'\n" +
                "Clear the tree: \'7\'\n" +
                "Exit Program: \'8\'");
        while (running){
            System.out.println("Enter selection: ");
            int selection = 0; String secondarySelect;
            while (selection < 1 && selection > 8){
                selection = Integer.parseInt(scanner.nextLine());
                switch(selection) {
                    case 1: // fill tree from file
                        System.out.println("Enter file name: ");
                        secondarySelect = scanner.nextLine();
                        Queue<Integer> fileIntake = new LinkedList<Integer>();
                        try {
                            Scanner file = new Scanner(new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\rbinary-search-trees-2-nicholas-shortlastname\\" + secondarySelect));
                            BST = new BinarySearchTree();
                            while (file.hasNextInt()) {
                                BST.add(file.nextInt());
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("Cannot find that file.");
                        }
                        break;
                    case 2: // add value to tree
                        System.out.println("Enter file name: ");
                        secondarySelect = String.valueOf(scanner.nextInt());
                        BST.add(Integer.valueOf(secondarySelect));
                        break;
                    case 3: //delete value from tree
                        // uhhhhhh
                    case 4:
                        System.out.println("Enter value to search for: ");
                        secondarySelect = scanner.nextLine();
                        System.out.print("Value is in tree: " + BST.contains(Integer.valueOf(secondarySelect)));
                        break;
                    case 5:
                        System.out.println("Which tree traversal (pre, in, post): ");
                        secondarySelect = scanner.nextLine().toLowerCase();
                        switch (secondarySelect){
                            case "pre":
                                BST.printPreorder();
                                break;
                            case "in":
                                BST.printInorder();
                                break;
                            case "post":
                                BST.printPostorder();
                                break;
                            default:
                                System.out.println("Invalid tree traversal");
                        }
                        break;
                    case 6:
                        System.out.println("Which statistic (nodes, leaf nodes, height): ");
                        secondarySelect = scanner.nextLine().toLowerCase();
                        switch (secondarySelect){
                            case "nodes":
                                System.out.print(BST.countNodes());
                                break;
                            case "leaf nodes":
                                System.out.print(BST.countLeafNodes());
                                break;
                            case "height":
                                System.out.print(BST.getHeight());
                                break;
                            default:
                                System.out.println("Invalid statistic");
                        }
                        break;
                    case 7:
                        BST = new BinarySearchTree();
                        break;
                    case 8:
                        running = false;
                        break;
                    default:
                        System.out.print("Invalid Selection");
                }
            }
        }
    }
}
