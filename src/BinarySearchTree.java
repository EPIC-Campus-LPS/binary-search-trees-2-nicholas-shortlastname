import com.sun.source.tree.EmptyStatementTree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.zip.ZipEntry;

/**
 * A cless representing a Binary Search Tree
 *
 * @author Nicholas Un
 * @version 1.0, 12/5/23
 */
public class BinarySearchTree {
    public TreeNode<Integer> root = null;

    public BinarySearchTree() {
        this.root = root;
    }

    /**
     * Adds a value
     * @param value value added to the tree
     */
    public void add(Integer value) {
        TreeNode<Integer> checkingNode = root;
        if (root == null) {
            root = new TreeNode<Integer>(value, null, null);
        } else {
            while (checkingNode.getValue().compareTo(value) >= 0 && checkingNode.getLeftChild() != null || (checkingNode.getValue().compareTo(value) < 0 && checkingNode.getRightChild() != null)) {

                if (checkingNode.getValue().compareTo(value) >= 0) {
                    checkingNode = checkingNode.getLeftChild();
                } else if (checkingNode.getValue().compareTo(value) < 0) {
                    checkingNode = checkingNode.getRightChild();
                }
            }
            if (checkingNode.getValue().compareTo(value) >= 0) {
                checkingNode.setLeftChild(new TreeNode(value, null, null));
            } else if (checkingNode.getValue().compareTo(value) < 0) {
                checkingNode.setRightChild(new TreeNode(value, null, null));
            }
        }
    }

    /**
     * Checks if a value is in the tree
     * @param value The value that might be in the search tree
     * @return If the value is in the tree or not
     */
    public boolean contains(Integer value) {
        Queue<Integer> queue = inOrder(root);
        while (!queue.isEmpty()) {
            if (queue.remove() == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Counts how many nodes there are
     * @return The number of nodes
     */
    public int countNodes() {
        Queue<Integer> queue = inOrder(root);
        int nodes = 0;
        while (!queue.isEmpty()) {
            queue.remove();
            nodes++;
        }
        return nodes;
    }

    /**
     * Counts how many leaf nodes there are
     * @return The number of leaf nodes
     */
    public int countLeafNodes() {
        return countingLeafNodes(root);
    }

    /**
     * Recursive helper method for counting leaf nodes
     * @param startPoint the starting node/root of the method
     * @return how many leaf nodes the subtree with root startPoint has
     */
    private int countingLeafNodes(TreeNode<Integer> startPoint) {
        if (startPoint == null) {
            return 0;
        }
        int leafNodes = countingLeafNodes(startPoint.getLeftChild()) + countingLeafNodes(startPoint.getRightChild());

        if (startPoint.getRightChild() == null && startPoint.getLeftChild() == null) {
            leafNodes++;
        }

        return leafNodes;
    }

    /**
     * Checks the height
     * @return Height of the tree
     */
    public int getHeight() {
        return gettingHeight(root) - 1;
    }

    /**
     * Recursive helper method for checking the height
     * @param startPoint the starting node/root of the method
     * @return the height of startPoint
     */
    private int gettingHeight(TreeNode<Integer> startPoint) {
        if (startPoint == null) {
            return -1;
        }
        int height1 = 0;
        int height2 = 0;
        if (startPoint.getLeftChild() != null) {
            height1 = gettingHeight(startPoint.getLeftChild());
        }
        if (startPoint.getRightChild() != null) {
            height2 = gettingHeight(startPoint.getRightChild());
        }

        if (height1 > height2) {
            return height1 + 1;
        } else {
            return height2 + 1;
        }
    }

    /**
     * Helper method for outputting ordering methods to System.out
     * @param orderInts The queue of integers needing to be outputted
     */
    private void printOrder(Queue<Integer> orderInts) {
        String output = "";
        while (!orderInts.isEmpty()) {
            output += orderInts.remove() + " ";
        }
        System.out.println(output);
    }

    /**
     * Outputs the tree in Pre-Order order
     */
    public void printPreorder() {
        printOrder(preOrder(root));
    }

    /**
     * Recursive helper method for printing in Pre-Order
     * @param startPoint the starting node/root of the method
     * @return The Pre-Order order of the subtree with root startPoint
     */
    private Queue<Integer> preOrder(TreeNode<Integer> startPoint) {
        if (startPoint == null) {
            return new LinkedList<>();
        }
        Queue<Integer> queue2 = new LinkedList<>();
        Queue<Integer> queue3 = new LinkedList<>();
        Queue<Integer> queueOutput = new LinkedList<>();
        if (startPoint.getLeftChild() != null) {
            queue2 = preOrder(startPoint.getLeftChild());
        }
        if (startPoint.getRightChild() != null) {
            queue3 = preOrder(startPoint.getRightChild());
        }

        queueOutput.add(startPoint.getValue());
        while (!queue2.isEmpty()) {
            queueOutput.add(queue2.remove());
        }
        while (!queue3.isEmpty()) {
            queueOutput.add(queue3.remove());
        }

        return queueOutput;
    }

    /**
     * Outputs the tree in In-Order order
     */
    public void printInorder() {
        printOrder(inOrder(root));
    }
    /**
     * Recursive helper method for printing in In-Order
     * @param startPoint the starting node/root of the method
     * @return The In-Order order of the subtree with root startPoint
     */
    private Queue<Integer> inOrder(TreeNode<Integer> startPoint) {
        if (startPoint == null) {
            return new LinkedList<>();
        }
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue3 = new LinkedList<>();
        Queue<Integer> queueOutput = new LinkedList<>();
        if (startPoint.getLeftChild() != null) {
            queue1 = inOrder(startPoint.getLeftChild());
        }
        if (startPoint.getRightChild() != null) {
            queue3 = inOrder(startPoint.getRightChild());
        }

        while (!queue1.isEmpty()) {
            queueOutput.add(queue1.remove());
        }
        queueOutput.add(startPoint.getValue());
        while (!queue3.isEmpty()) {
            queueOutput.add(queue3.remove());
        }

        return queueOutput;
    }

    /**
     * Outputs the tree in Post-Order order
     */
    public void printPostorder() {
        printOrder(postOrder(root));
    }
    /**
     * Recursive helper method for printing in Post-Order
     * @param startPoint the starting node/root of the method
     * @return The Post-Order order of the subtree with root startPoint
     */
    private Queue<Integer> postOrder(TreeNode<Integer> startPoint) {
        if (startPoint == null) {
            return new LinkedList<>();
        }
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        Queue<Integer> queueOutput = new LinkedList<>();
        if (startPoint.getLeftChild() != null) {
            queue1 = postOrder(startPoint.getLeftChild());
        }
        if (startPoint.getRightChild() != null) {
            queue2 = postOrder(startPoint.getRightChild());
        }

        while (!queue1.isEmpty()) {
            queueOutput.add(queue1.remove());
        }
        while (!queue2.isEmpty()) {
            queueOutput.add(queue2.remove());
        }
        queueOutput.add(startPoint.getValue());

        return queueOutput;
    }

    /**
     * Deletes value from the tree and reconfigures the tree around the deletion
     * If the value cannot be found, throw NoSuchElementException
     * @param value The element to be deleted from the tree
     * @return The value deleted from the tree
     */
    public Integer delete(Integer value) {
        if (!contains(value)) {
            throw new NoSuchElementException("Element is not in tree");
        }

        if (root.getValue() == value) { // special case for the root
            Queue<Integer> rightNodes = inOrder(root.getRightChild());
            root = root.getLeftChild();

            while (!rightNodes.isEmpty()) {
                add(rightNodes.remove());
            }
        } else {

            Queue<TreeNode<Integer>> treeNodeQueue = treeNodeQueue(root); // get a queue of all nodes in the tree

            TreeNode<Integer> parentNodeOfRemoved = null; // make a node that will be filled with the parent node of node with value once found;
            while (parentNodeOfRemoved == null) {
                if ((treeNodeQueue.peek().getLeftChild() != null && treeNodeQueue.peek().getLeftChild().getValue() == value)
                        || (treeNodeQueue.peek().getRightChild() != null && treeNodeQueue.peek().getRightChild().getValue() == value)) {
                    // if the node being checked is a parent of the node being removed, fill the parentNodeOfRemoved, subsequently stopping the checking process
                    parentNodeOfRemoved = treeNodeQueue.remove();
                } else { //otherwise, move on to the next node
                    treeNodeQueue.remove();
                }
            }

            boolean isLeftChild = false;
            TreeNode<Integer> removedNode = null; // define which side of the parent is the removed node, and which node it is
            if (parentNodeOfRemoved.getLeftChild() != null && parentNodeOfRemoved.getLeftChild().getValue() == value) {
                isLeftChild = true;
                removedNode = parentNodeOfRemoved.getLeftChild();
            } else if (parentNodeOfRemoved.getRightChild() != null && parentNodeOfRemoved.getRightChild().getValue() == value) {
                isLeftChild = false;
                removedNode = parentNodeOfRemoved.getRightChild();
            }

            // logic for what to do based on how many children the removed node has
            if (removedNode.getLeftChild() == null && removedNode.getRightChild() == null) { // if none, replace the removed node with null
                if (isLeftChild) {
                    parentNodeOfRemoved.setLeftChild(null);
                } else {
                    parentNodeOfRemoved.setRightChild(null);
                }
            } else if (removedNode.getLeftChild() == null ^ removedNode.getRightChild() == null) { // if one child, replace the removed node with its child
                TreeNode<Integer> successorNode = null;
                if (removedNode.getLeftChild() == null) {
                    successorNode = removedNode.getRightChild();
                } else {
                    successorNode = removedNode.getLeftChild();
                }
                if (isLeftChild) {
                    parentNodeOfRemoved.setLeftChild(successorNode);
                } else {
                    parentNodeOfRemoved.setRightChild(successorNode);
                }
            } else if (removedNode.getLeftChild() != null && removedNode.getRightChild() != null) { // if two children, set the left as the successor and re-add the ones on the right
                TreeNode<Integer> successorNode = removedNode.getLeftChild();
                if (isLeftChild) {
                    parentNodeOfRemoved.setLeftChild(successorNode);
                } else {
                    parentNodeOfRemoved.setRightChild(successorNode);
                }
                Queue<Integer> rightNodes = inOrder(removedNode.getRightChild());
                while (!rightNodes.isEmpty()) {
                    add(rightNodes.remove());
                }
            }
        }
        return value;
    }

    /**
     * Recursive helper method for getting a queue of all Nndes in the tree
     * @param startPoint the starting node/root of the method
     * @return A queue of all the Nodes in the subtree with root startPoint
     */
    private Queue<TreeNode<Integer>> treeNodeQueue(TreeNode<Integer> startPoint) {
        if (startPoint == null) {
            return new LinkedList<>();
        }
        Queue<TreeNode<Integer>> queue1 = new LinkedList<>();
        Queue<TreeNode<Integer>> queue2 = new LinkedList<>();
        Queue<TreeNode<Integer>> queueOutput = new LinkedList<>();
        if (startPoint.getLeftChild() != null) {
            queue1 = treeNodeQueue(startPoint.getLeftChild());
        }
        if (startPoint.getRightChild() != null) {
            queue2 = treeNodeQueue(startPoint.getRightChild());
        }

        while (!queue1.isEmpty()) {
            queueOutput.add(queue1.remove());
        }
        while (!queue2.isEmpty()) {
            queueOutput.add(queue2.remove());
        }
        queueOutput.add(startPoint);

        return queueOutput;
    }
}
