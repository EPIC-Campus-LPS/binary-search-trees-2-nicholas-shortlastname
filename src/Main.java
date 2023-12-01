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
        int selection; String secondarySelect;
        System.out.println("Fill the tree from a file: \'1\'\n" +
                "Add a value to tree: \'2\'\n" +
                "Delete a value from the tree: \'3\'\n" +
                "See if tree contains a value: \'4\'\n" +
                "Test traversals (pre, in, post): \'5\'\n" +
                "Print stats (nodes, leaf nodes, height): \'6\'\n" +
                "Clear the tree: \'7\'\n" +
                "Exit Program: \'8\'");
        while (running){
            try {
                System.out.println("Enter selection: ");
                selection = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e){
                continue;
            }
                switch(selection) {
                    case 1: // fill tree from file
                        System.out.println("Enter file name: ");
                        secondarySelect = scanner.nextLine();
                        Queue<Integer> fileIntake = new LinkedList<Integer>();
                        try {
                            Scanner file = new Scanner(new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\binary-search-trees-2-nicholas-shortlastname\\" + secondarySelect));
                            BST = new BinarySearchTree();
                            while (file.hasNextInt()) {
                                BST.add(file.nextInt());
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("Cannot find that file.");
                        }
                        break;
                    case 2: // add value to tree
                        System.out.println("Enter value to add: ");
                        secondarySelect = String.valueOf(scanner.nextInt());
                        BST.add(Integer.valueOf(secondarySelect));
                        break;
                    case 3: //delete value from tree
                        System.out.println("Enter value to delete: ");
                        secondarySelect = String.valueOf(scanner.nextInt());
                        BST.delete(Integer.valueOf(secondarySelect));
                        break;
                    case 4: //see if tree has value
                        System.out.println("Enter value to search for: ");
                        secondarySelect = scanner.nextLine();
                        System.out.print("Value is in tree: " + BST.contains(Integer.valueOf(secondarySelect)));
                        break;
                    case 5: //test traversals
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
                    case 6: //Print stats
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
                    case 7: //Clear tree
                        BST = new BinarySearchTree();
                        break;
                    case 8: //Exit program
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid Selection");
                }
            selection = 0;
            }
        }
    }
