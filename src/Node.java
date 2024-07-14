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


    public Node<Key> leftRotate(){
        Node<Key> root = this;
        if (root==null)
            return null;
        Node<Key> newRoot = root.getRightChild();
        if (newRoot==null)
            return null;
        root.setRightChild(newRoot.getLeftChild());
        if (newRoot.getLeftChild()!=null){
            newRoot.getLeftChild().setParent(root);
        }
        newRoot.setParent(root.getParent());
        newRoot.setLeftChild(root);
        root.setParent(newRoot);

        // set new sizes
        int size = root.getSubtreeSize();
        newRoot.setSubtreeSize(size);

        int leftSize = root.getLeftChild().getSubtreeSize();
        int rightSize = newRoot.getLeftChild().getSubtreeSize();
        int newRootSize = leftSize+rightSize+1;
        root.setSubtreeSize(newRootSize);

        // set new heights

        // height for old root
        int leftHeight = root.getLeftChild().getHeightFromBottom();
        int rightHeight = newRoot.getLeftChild().getHeightFromBottom();
        if(leftHeight>rightHeight)
            root.setHeightFromBottom(leftHeight+1);
        else
            root.setHeightFromBottom(rightHeight+1);

        // height for new root
        int leftHeight2 = root.getHeightFromBottom();
        int rightHeight2 = newRoot.getRightChild().getHeightFromBottom();
        if(leftHeight2>rightHeight2)
            newRoot.setHeightFromBottom(leftHeight2+1);
        else
            newRoot.setHeightFromBottom(rightHeight2+1);

        return newRoot;

    }

    /**
     * this method performs a right rotation on a specific node and updates the nodes accordingly
     * @return it returns the new root after rotation
     */

    public Node<Key> rightRotate(){

        Node<Key> root = this;
        if (root==null)
            return null;
        Node<Key> newRoot = root.getLeftChild();
        if (root==null)
            return null;

        root.setLeftChild(newRoot.getRightChild());
        if (newRoot.getRightChild()!=null){
            newRoot.getRightChild().setParent(root);
        }
        newRoot.setParent(root.getParent());
        newRoot.setRightChild(root);
        root.setParent(newRoot);

        // set new heights

        //height for old root
        int leftHeight =newRoot.getRightChild().getHeightFromBottom();
        int rightHeight = root.getRightChild().getHeightFromBottom();
        if(leftHeight>rightHeight)
            root.setHeightFromBottom(leftHeight+1);
        else
            root.setHeightFromBottom(rightHeight+1);

        // height for new root
        int leftHeight2 =newRoot.getLeftChild().getHeightFromBottom();
        int rightHeight2 = root.getHeightFromBottom();
        if(leftHeight2>rightHeight2)
            newRoot.setHeightFromBottom(leftHeight2+1);
        else
            newRoot.setHeightFromBottom(rightHeight+1);

        // set new sizes
        int size = root.getSubtreeSize();
        newRoot.setSubtreeSize(size);

        int leftSize = newRoot.getRightChild().getSubtreeSize();
        int rightSize = root.getRightChild().getSubtreeSize();
        int newRootSize = leftSize+rightSize+1;
        root.setSubtreeSize(newRootSize);

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