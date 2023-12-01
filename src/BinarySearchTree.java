import com.sun.source.tree.EmptyStatementTree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.zip.ZipEntry;

public class BinarySearchTree {
    public TreeNode<Integer> root = null;

    public BinarySearchTree() {
        this.root = root;
    }

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

    public boolean contains(Integer value) {
        Queue<Integer> queue = inOrder(root);
        while (!queue.isEmpty()) {
            if (queue.remove() == value) {
                return true;
            }
        }
        return false;
    }

    public int countNodes() {
        Queue<Integer> queue = inOrder(root);
        int nodes = 0;
        while (!queue.isEmpty()) {
            queue.remove();
            nodes++;
        }
        return nodes;
    }

    public int countLeafNodes() {
        return countingLeafNodes(root);
    }

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

    public int getHeight() {
        return gettingHeight(root) - 1;
    }

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

    private void printOrder(Queue<Integer> orderInts) {
        String output = "";
        while (!orderInts.isEmpty()) {
            output += orderInts.remove() + " ";
        }
        System.out.println(output);
    }

    public void printPreorder() {
        printOrder(preOrder(root));
    }

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

    public void printInorder() {
        printOrder(inOrder(root));
    }

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

    public void printPostorder() {
        printOrder(postOrder(root));
    }

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
                if ((treeNodeQueue.peek().getLeftChild() != null && treeNodeQueue.peek().getLeftChild().getValue() == value) || (treeNodeQueue.peek().getRightChild() != null && treeNodeQueue.peek().getRightChild().getValue() == value)) {
                    parentNodeOfRemoved = treeNodeQueue.remove();
                } else {
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
