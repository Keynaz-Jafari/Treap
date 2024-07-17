import java.util.ArrayList;

public class Treap<Key extends Comparable<Key>>{
    private Node<Key> root;
    private Node<Key> min;
    private Node<Key> max;


    /**
     * This method searches for a specific node in the treap, based on the key value
     * @param root
     * @param val
     * @return the node
     */
    public Node<Key> treapSearch(Node<Key> root, Key val){
        if (root==null)
            return root;
        int cmp = val.compareTo(root.getKey());
        if(cmp<0)
            return treapSearch(root.getLeftChild(),val);
        else if (cmp>0)
            return treapSearch(root.getRightChild(),val);
        return root;
    }

    /**
     * Add a Key to the Treap.
     * @param key The key to add to the tree.
     * @return A node containing the added key.
     */
    public Node<Key> add(Key key){
        this.root = add_helper(key,this.root, null, null);
        Node<Key> current = root;
        min = null;
        while (current != null) {
            min = current;
            current = current.getLeftChild(); // chap tarin peida mishe ke kuchiktarine
        }

        current = root;
        max = null;
        while (current != null) {
            max = current;
            current = current.getRightChild(); // rast tarin peida mishe ke bozorgtarine
        }
        return root;
    }

    /**
     * Helper function for add. Recursively adds a Node with the given Key to the Treap and finally Heapifys the added node using rotations.
     * @param key The key to add to the Treap.
     * @param root Treap root node.
     * @param pre The predecessor of the node.
     * @param suc The successor of the node.
     * @return A node containing the added key.
     */

    private Node<Key> add_helper(Key key, Node<Key> root, Node<Key> pre, Node<Key> suc) {
        if (root == null) {
            Node<Key> newNode = new Node<Key>(key);
            newNode.setPredecessor(pre);
            newNode.setSuccessor(suc);
            if (pre != null) {
                pre.setSuccessor(newNode);
            }
            if (suc != null) {
                suc.setPredecessor(newNode);
            }
            return newNode;
        }
        int cmp = key.compareTo(root.getKey());
        if (cmp < 0) {
            root.setLeftChild(add_helper(key, root.getLeftChild(), pre, root));
            root.getLeftChild().setParent(root);
            if (root.getLeftChild().getPriority() > root.getPriority()) {
                root = root.rightRotate();
            }
        } else if (cmp > 0) {
            root.setRightChild(add_helper(key, root.getRightChild(), root, suc));
            root.getRightChild().setParent(root);
            if (root.getRightChild().getPriority() > root.getPriority()) {
                root = root.leftRotate();
            }
        }
        if (root == null) {
            return root;
        }

        int leftSubtreeSize = (root.getLeftChild() != null) ? root.getLeftChild().getSubtreeSize() : 0;
        int rightSubtreeSize = (root.getRightChild() != null) ? root.getRightChild().getSubtreeSize() : 0;

        root.setSubtreeSize(leftSubtreeSize + rightSubtreeSize + 1);

        int leftHeight = (root.getLeftChild() != null) ? root.getLeftChild().getHeightFromBottom() : -1;
        int rightHeight = (root.getRightChild() != null) ? root.getRightChild().getHeightFromBottom() : -1;

        root.setHeightFromBottom(Math.max(leftHeight, rightHeight) + 1);
        return root;
    }

    /**
     * returns the k-th smallest element in the Treap.
     @param  k   index + 1 of the desired element.
     */
//    public Key kthSmallestElement(int k) {
//        Node<Key> tmp = min;
//        for (int i = 0; i < k-1 ; i++) {
//            if (tmp == null) {
//                System.out.println("k is out of range");
//                return null;
//            }
//            tmp = tmp.getSuccessor();
//        }
//        if (tmp == null) {
//            System.out.println("K is null");
//            return null;
//        }
//        return tmp.getKey();
//    }

    public Key kthSmallestElement(int k) {
        return kthSmallestElementHelper(root, k);
    }

    private Key kthSmallestElementHelper(Node<Key> node, int k) {
        if (node == null) {
            return null; 
        }

        int leftSubtreeSize = (node.getLeftChild() != null) ? node.getLeftChild().getSubtreeSize() : 0;

        if (k <= leftSubtreeSize) {
            return kthSmallestElementHelper(node.getLeftChild(), k);
        } else if (k == leftSubtreeSize + 1) {
            return node.getKey();
        } else {
            return kthSmallestElementHelper(node.getRightChild(), k - leftSubtreeSize - 1);
        }
    }




    /**
     * this method deletes a key from the treap
     * @param key the key that should be deleted
     */
    public void delete(Key key){
        this.root = delete_helper(key, this.root);
        updateMinMax(root);
    }

    private Node<Key> delete_helper(Key key, Node<Key> root) {
        if (root == null) {
            return null;
        }

        int cmp = key.compareTo(root.getKey());
        if (cmp < 0) {
            root.setLeftChild(delete_helper(key, root.getLeftChild()));
            if (root.getLeftChild() != null) {
                root.getLeftChild().setParent(root);
            }
        } else if (cmp > 0) {
            root.setRightChild(delete_helper(key, root.getRightChild()));
            if (root.getRightChild() != null) {
                root.getRightChild().setParent(root);
            }
        }

        else {
            // Node to delete found
            if (root.getLeftChild() == null) {
                Node<Key> tmp = root.getRightChild();
                updateSucAndPre(root, null, tmp);
                root = tmp;
            } else if (root.getRightChild() == null) {
                Node<Key> tmp = root.getLeftChild();
                updateSucAndPre(root, tmp, null);
                root = tmp;
            } else {
                // Node has two children
                if (root.getLeftChild().getPriority() < root.getRightChild().getPriority()) {
                    root = root.leftRotate();
                    root.setLeftChild(delete_helper(key, root.getLeftChild()));
                    if (root.getLeftChild() != null) {
                        root.getLeftChild().setParent(root);
                    }
                } else {
                    root = root.rightRotate();
                    root.setRightChild(delete_helper(key, root.getRightChild()));
                    if (root.getRightChild() != null) {
                        root.getRightChild().setParent(root);
                    }
                }
            }
        }

        // Update subtree size and height
        if (root != null) {
            int leftSubtreeSize = (root.getLeftChild() != null) ? root.getLeftChild().getSubtreeSize() : 0;
            int rightSubtreeSize = (root.getRightChild() != null) ? root.getRightChild().getSubtreeSize() : 0;
            root.setSubtreeSize(leftSubtreeSize + rightSubtreeSize + 1);

            int leftHeight = (root.getLeftChild() != null) ? root.getLeftChild().getHeightFromBottom() : -1;
            int rightHeight = (root.getRightChild() != null) ? root.getRightChild().getHeightFromBottom() : -1;
            root.setHeightFromBottom(Math.max(leftHeight, rightHeight) + 1);
        }

        return root;
    }

    /**
     * this method updates the min and max of the treap after changes
     * @param root the root
     */

    // Update min and max after deletion
    private void updateMinMax(Node<Key> root) {
        if (root == null) {
            min = null;
            max = null;
            return;
        }

        min = findMin(root);
        max = findMax(root);
    }

    /**
     * this method finds the minimum key
     * @param node
     * @return minimum key
     */

    private Node<Key> findMin(Node<Key> node) {
        if (node == null) {
            return null;
        }
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }

    /**
     * this method finds the maximum key
     * @param node
     * @return maximum key
     */
    private Node<Key> findMax(Node<Key> node) {
        if (node == null) {
            return null;
        }
        while (node.getRightChild() != null) {
            node = node.getRightChild();
        }
        return node;
    }


    /**
     *this method updates the successors and predecessors after the changes
     * @param node
     * @param pre
     * @param suc
     */

    public void updateSucAndPre(Node<Key> node, Node<Key> pre, Node<Key> suc) {
        if (node == null) {
            return;
        }
        if (pre != null) {
            pre.setSuccessor(node.getSuccessor());
        }
        if (suc != null) {
            suc.setPredecessor(node.getPredecessor());
        }
    }

    /**
     * this method gives the keys of the treap in an ascending order
     * @return an arraylist of the keys in ascending order
     */
    public ArrayList<Key> orderedKeys(){
        ArrayList keys = new ArrayList();
        Node<Key> tmp = min;
        while(tmp!=null){
            keys.add(tmp.getKey());
            tmp = tmp.getSuccessor();
        }
        return keys;
    }


    /**
     * get the height of the Treap. 0 can either mean that the Treap is empty or has only a single Node in it.
     * @return Treap height.
     */
    public int getTreapHeight(){
        if (this.root != null){
            return root.getHeightFromBottom();
        }
        return 0;
    }


    public Key getTreapMin(){
        if (this.min!=null){
            return min.getKey();
        }
        return null;
    }

    public Key getTreapMax(){
        if (this.max!=null){
            return max.getKey();
        }
        return null;
    }

    public Node<Key> getRoot() {
        return root;
    }

    public void setRoot(Node<Key> root) {
        this.root = root;
    }

    public Node<Key> getMin() {
        return min;
    }

    public void setMin(Node<Key> min) {
        this.min = min;
    }

    public Node<Key> getMax() {
        return max;
    }

    public void setMax(Node<Key> max) {
        this.max = max;
    }

}