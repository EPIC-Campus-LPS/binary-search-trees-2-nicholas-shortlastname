import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree{
    public TreeNode<Integer> root = null;
    public BinarySearchTree(){
        this.root = root;
    }
    public void add(Integer value){
        TreeNode<Integer> checkingNode = root;
        if(root == null){
            root = new TreeNode<Integer>(value, null, null);
        } else {
            while (checkingNode.getValue().compareTo(value) >= 0 && checkingNode.getLeftChild() != null
                || (checkingNode.getValue().compareTo(value) < 0 && checkingNode.getRightChild() != null)){

                if(checkingNode.getValue().compareTo(value) >= 0){
                    checkingNode = checkingNode.getLeftChild();
                } else if(checkingNode.getValue().compareTo(value) < 0){
                    checkingNode = checkingNode.getRightChild();
                }
            }
            if(checkingNode.getValue().compareTo(value) >= 0){
                checkingNode.setLeftChild(new TreeNode(value, null, null));
            } else if(checkingNode.getValue().compareTo(value) < 0) {
                checkingNode.setRightChild(new TreeNode(value, null, null));
            }
        }
    }
    public boolean contains(Integer value){
        Queue<Integer> queue = inOrder(root);
        while (!queue.isEmpty()){
            if(queue.remove() == value){
                return true;
            }
        }
        return false;
    }
    public int countNodes(){
        Queue<Integer> queue = inOrder(root);
        int nodes = 0;
        while (!queue.isEmpty()){
            queue.remove();
            nodes++;
        }
        return nodes;
    }
    public int countLeafNodes(){
        return countingLeafNodes(root);
    }
    private int countingLeafNodes(TreeNode<Integer> startPoint){
        if (startPoint == null){
            return 0;
        }
        int leafNodes = countingLeafNodes(startPoint.getLeftChild()) + countingLeafNodes(startPoint.getRightChild());

        if(startPoint.getRightChild() == null && startPoint.getLeftChild() == null){
            leafNodes++;
        }

        return leafNodes;
    }
    public int getHeight(){
        return gettingHeight(root) - 1;
    }
    private int gettingHeight(TreeNode<Integer> startPoint){
        if (startPoint == null){
            return -1;
        }
        int height1 = 0; int height2 = 0;
        if(startPoint.getLeftChild() != null){
            height1 = gettingHeight(startPoint.getLeftChild());
        }
        if(startPoint.getRightChild() != null){
            height2 = gettingHeight(startPoint.getRightChild());
        }

        if(height1 > height2){
            return height1 + 1;
        }else{
            return height2 + 1;
        }
    }
    private void printOrder(Queue<Integer> orderInts){
        String output = "";
        while (!orderInts.isEmpty()) {
            output += orderInts.remove() + " ";
        }
        System.out.println(output);
    }
    public void printPreorder(){
        printOrder(preOrder(root));
    }
    private Queue<Integer> preOrder(TreeNode<Integer> startPoint){
        if (startPoint == null){
            return new LinkedList<>();
        }
        Queue<Integer> queue2 = new LinkedList<>(); Queue<Integer> queue3 = new LinkedList<>(); Queue<Integer> queueOutput = new LinkedList<>();
        if(startPoint.getLeftChild() != null){
            queue2 = preOrder(startPoint.getLeftChild());
        }
        if(startPoint.getRightChild() != null){
            queue3 = preOrder(startPoint.getRightChild());
        }

        queueOutput.add(startPoint.getValue());
        while (!queue2.isEmpty()){
            queueOutput.add(queue2.remove());
        }
        while (!queue3.isEmpty()){
            queueOutput.add(queue3.remove());
        }

        return queueOutput;
    }
    public void printInorder(){
        printOrder(inOrder(root));
    }
    private Queue<Integer> inOrder(TreeNode<Integer> startPoint){
        if (startPoint == null){
            return new LinkedList<>();
        }
        Queue<Integer> queue1 = new LinkedList<>(); Queue<Integer> queue3 = new LinkedList<>(); Queue<Integer> queueOutput = new LinkedList<>();
        if(startPoint.getLeftChild() != null){
            queue1 = inOrder(startPoint.getLeftChild());
        }
        if(startPoint.getRightChild() != null){
            queue3 = inOrder(startPoint.getRightChild());
        }

        while (!queue1.isEmpty()){
            queueOutput.add(queue1.remove());
        }
        queueOutput.add(startPoint.getValue());
        while (!queue3.isEmpty()){
            queueOutput.add(queue3.remove());
        }

        return queueOutput;
    }
    void printPostorder(){
        printOrder(postOrder(root));
    }
    private Queue<Integer> postOrder(TreeNode<Integer> startPoint){
        if (startPoint == null){
            return new LinkedList<>();
        }
        Queue<Integer> queue1 = new LinkedList<>(); Queue<Integer> queue2 = new LinkedList<>(); Queue<Integer> queueOutput = new LinkedList<>();
        if(startPoint.getLeftChild() != null){
            queue1 = postOrder(startPoint.getLeftChild());
        }
        if(startPoint.getRightChild() != null){
            queue2 = postOrder(startPoint.getRightChild());
        }

        while (!queue1.isEmpty()){
            queueOutput.add(queue1.remove());
        }
        while (!queue2.isEmpty()){
            queueOutput.add(queue2.remove());
        }
        queueOutput.add(startPoint.getValue());

        return queueOutput;
    }
}
