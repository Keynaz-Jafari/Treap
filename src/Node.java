import java.security.PrivateKey;

public class Node <Key extends Comparable<Key>>{
    private Key key;
    private Node<Key> successor;
    private Node<Key> predecessor;
    private Node<Key> parent;
    private Node<Key> leftChild;
    private Node<Key> rightChild;
    private int subtreeSize;
    private double priority;
    private int heightFromBottom;

    public Node(Key key) {
        this.key = key;
        this.parent = null;
        this.successor = null;
        this.predecessor = null;
        this.leftChild = null;
        this.rightChild = null;
        this.subtreeSize =1;
        this.priority = Math.random();
        this.heightFromBottom=0;
    }

    /**
     * this method performs a left rotate on a specific node and updates the nodes accordingly
     * @param
     * @return it returns the new root after rotation
     */

    public Node<Key> leftRotate() {
        Node<Key> root = this;
        if (root == null) return null;

        Node<Key> newRoot = root.getRightChild();
        if (newRoot == null) return root; // Can't rotate if there is no right child

        root.setRightChild(newRoot.getLeftChild());
        if (newRoot.getLeftChild() != null) {
            newRoot.getLeftChild().setParent(root);
        }
        newRoot.setParent(root.getParent());
        newRoot.setLeftChild(root);
        root.setParent(newRoot);

        // Update subtree sizes
        int leftChildSizeOfRoot = 0;
        if (root.getLeftChild() != null) {
            leftChildSizeOfRoot = root.getLeftChild().getSubtreeSize();
        }

        int rightChildSizeOfRoot = 0;
        if (root.getRightChild() != null) {
            rightChildSizeOfRoot = root.getRightChild().getSubtreeSize();
        }

        root.setSubtreeSize(leftChildSizeOfRoot + rightChildSizeOfRoot + 1);

        int leftChildSizeOfNewRoot = 0;
        if (newRoot.getLeftChild() != null) {
            leftChildSizeOfNewRoot = newRoot.getLeftChild().getSubtreeSize();
        }

        int rightChildSizeOfNewRoot = 0;
        if (newRoot.getRightChild() != null) {
            rightChildSizeOfNewRoot = newRoot.getRightChild().getSubtreeSize();
        }

        newRoot.setSubtreeSize(leftChildSizeOfNewRoot + rightChildSizeOfNewRoot + 1);

        // Update heights
        int leftChildHeightOfRoot = -1;
        if (root.getLeftChild() != null) {
            leftChildHeightOfRoot = root.getLeftChild().getHeightFromBottom();
        }

        int rightChildHeightOfRoot = -1;
        if (root.getRightChild() != null) {
            rightChildHeightOfRoot = root.getRightChild().getHeightFromBottom();
        }

        root.setHeightFromBottom(Math.max(leftChildHeightOfRoot, rightChildHeightOfRoot) + 1);

        int leftChildHeightOfNewRoot = -1;
        if (newRoot.getLeftChild() != null) {
            leftChildHeightOfNewRoot = newRoot.getLeftChild().getHeightFromBottom();
        }

        int rightChildHeightOfNewRoot = -1;
        if (newRoot.getRightChild() != null) {
            rightChildHeightOfNewRoot = newRoot.getRightChild().getHeightFromBottom();
        }

        newRoot.setHeightFromBottom(Math.max(leftChildHeightOfNewRoot, rightChildHeightOfNewRoot) + 1);

        return newRoot;
    }

    /**
     * this method performs a right rotation on a specific node and updates the nodes accordingly
     * @return it returns the new root after rotation
     */
    public Node<Key> rightRotate() {
        Node<Key> root = this;
        if (root == null) return null;

        Node<Key> newRoot = root.getLeftChild();
        if (newRoot == null) return root; // Cannot rotate if there is no left child

        root.setLeftChild(newRoot.getRightChild());
        if (newRoot.getRightChild() != null) {
            newRoot.getRightChild().setParent(root);
        }
        newRoot.setParent(root.getParent());
        newRoot.setRightChild(root);
        root.setParent(newRoot);

        // Update subtree sizes
        int leftChildSizeOfRoot = 0;
        if (root.getLeftChild() != null) {
            leftChildSizeOfRoot = root.getLeftChild().getSubtreeSize();
        }

        int rightChildSizeOfRoot = 0;
        if (root.getRightChild() != null) {
            rightChildSizeOfRoot = root.getRightChild().getSubtreeSize();
        }

        root.setSubtreeSize(leftChildSizeOfRoot + rightChildSizeOfRoot + 1);

        int leftChildSizeOfNewRoot = 0;
        if (newRoot.getLeftChild() != null) {
            leftChildSizeOfNewRoot = newRoot.getLeftChild().getSubtreeSize();
        }

        int rightChildSizeOfNewRoot = 0;
        if (newRoot.getRightChild() != null) {
            rightChildSizeOfNewRoot = newRoot.getRightChild().getSubtreeSize();
        }

        newRoot.setSubtreeSize(leftChildSizeOfNewRoot + rightChildSizeOfNewRoot + 1);

        // Update heights
        int leftChildHeightOfRoot = -1;
        if (root.getLeftChild() != null) {
            leftChildHeightOfRoot = root.getLeftChild().getHeightFromBottom();
        }

        int rightChildHeightOfRoot = -1;
        if (root.getRightChild() != null) {
            rightChildHeightOfRoot = root.getRightChild().getHeightFromBottom();
        }

        root.setHeightFromBottom(Math.max(leftChildHeightOfRoot, rightChildHeightOfRoot) + 1);

        int leftChildHeightOfNewRoot = -1;
        if (newRoot.getLeftChild() != null) {
            leftChildHeightOfNewRoot = newRoot.getLeftChild().getHeightFromBottom();
        }

        int rightChildHeightOfNewRoot = -1;
        if (newRoot.getRightChild() != null) {
            rightChildHeightOfNewRoot = newRoot.getRightChild().getHeightFromBottom();
        }

        newRoot.setHeightFromBottom(Math.max(leftChildHeightOfNewRoot, rightChildHeightOfNewRoot) + 1);

        return newRoot;
    }





    public Key getKey() {
        return this.key;
    }

    public Node<Key> getSuccessor() {
        return successor;
    }

    public void setSuccessor(Node<Key> successor) {
        this.successor = successor;
    }

    public Node<Key> getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Node<Key> predecessor) {
        this.predecessor = predecessor;
    }

    public Node<Key> getParent() {
        return parent;
    }

    public void setParent(Node<Key> parent) {
        this.parent = parent;
    }

    public Node<Key> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<Key> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<Key> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<Key> rightChild) {
        this.rightChild = rightChild;
    }

    public int getSubtreeSize() {
        return subtreeSize;
    }

    public void setSubtreeSize(int subtreeSize) {
        this.subtreeSize = subtreeSize;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public int getHeightFromBottom() {
        return heightFromBottom;
    }

    public void setHeightFromBottom(int heightFromBottom) {
        this.heightFromBottom = heightFromBottom;
    }
}