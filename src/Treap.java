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
        if (root == null){
            Node<Key> newNode =  new Node<Key>(key);
            newNode.setPredecessor(pre);
            newNode.setSuccessor(suc);
            if(pre!=null){
                pre.setSuccessor(newNode);
            }
            if(suc!=null){
                suc.setPredecessor(newNode);
            }
            return newNode;
        }
        int cmp = key.compareTo(root.getKey());
        if (cmp < 0) {
            root.setLeftChild(add_helper(key, root.getLeftChild(), pre, root));

            if (root.getLeftChild().getLeftChild() == null && root.getLeftChild().getRightChild() == null){
                root.getLeftChild().setSuccessor(root);
            }
            root.getLeftChild().setParent(root);
            root.setSubtreeSize(root.getSubtreeSize() + 1);

            int height = -1;
            if (root.getLeftChild() != null) {
                height = root.getLeftChild().getHeightFromBottom();
            }
            if (root.getRightChild() != null && root.getRightChild().getHeightFromBottom() > height){
                height = root.getRightChild().getHeightFromBottom();
            }
            root.setHeightFromBottom(height + 1);

            if (root.getLeftChild().getPriority() > root.getPriority())
                root = root.rightRotate();

        }
        else if (cmp > 0) {
            root.setRightChild(add_helper(key, root.getRightChild(), root, suc));
            root.getRightChild().setParent(root);
            root.setSubtreeSize(root.getSubtreeSize()+1);
            int height = -1;
            if (root.getLeftChild() != null) {
                height = root.getLeftChild().getHeightFromBottom();
            }
            if (root.getRightChild() != null && root.getRightChild().getHeightFromBottom() > height){
                height = root.getRightChild().getHeightFromBottom();
            }
            root.setHeightFromBottom(height + 1);

            if (root.getRightChild().getPriority() > root.getPriority())
                root = root.leftRotate();
        }
        return root;
    }

    /**
     * updates the successor and predecessor of a given node
     * @param node changes affect this node
     * @param pre the predecessor to assign the node
     * @param suc the successor to assign the node
     */
    public void updateSucAndPre(Node<Key> node , Node<Key> pre, Node<Key> suc){
        if (pre != null){
            pre.setSuccessor(node.getSuccessor());
        }
        if (suc !=null){
            suc.setPredecessor(node.getPredecessor());
        }
    }

    /**
     * returns the k-th smallest element in the Treap.
     @param  k   index + 1 of the desired element.
     */
    public Key kthSmallestElement(int k){
        Node<Key> tmp = min;
        for (int i = 0; i < k-1; i++){
            tmp = tmp.getSuccessor();
        }
        return tmp.getKey();
    }

    /**
     * Delete a key from the given Treap.
     * @param key key to be removed from the Treap.

     */
    public void delete(Key key){
        this.root = delete_helper(key,this.root);
    }

    /**
     * Helper function to delete a key from a Treap.
     * @param key Key to be removed from the Treap.
     * @param root The root of the Treap.
     * @return returns the root node of the Treap. Null if Treap is empty.
     */
    private Node<Key> delete_helper(Key key, Node<Key> root) {
        if (root == null){
            return root;
        }
        int cmp = key.compareTo(root.getKey());
        if (cmp < 0){
            root.setLeftChild(delete_helper(key, root.getLeftChild()));
            root.getLeftChild().setParent(root);
            root.setSubtreeSize(root.getSubtreeSize() -1);
        } else if (cmp > 0) {
            root.setRightChild(delete_helper(key,root.getRightChild()));
            root.getRightChild().setLeftChild(root);
            root.setSubtreeSize(root.getSubtreeSize() -1);
        }
        else if (root.getLeftChild()==null){
            Node<Key> tmp = root.getRightChild();
            updateSucAndPre(root,null, tmp);
            root = tmp;
        }
        else if (root.getRightChild()==null){
            Node<Key> tmp = root.getLeftChild();
            updateSucAndPre(root,tmp,null);
            root = tmp;
        }
        else if (root.getLeftChild().getPriority() < root.getRightChild().getPriority()){
            root = root.leftRotate();
            root.setSubtreeSize(root.getSubtreeSize()-1);
            root.setLeftChild(delete_helper(key,root.getLeftChild()));
            root.getLeftChild().setParent(root);

        }
        else{
            root = root.rightRotate();
            root.setSubtreeSize(root.getSubtreeSize()-1);

            root.setRightChild(delete_helper(key,root.getRightChild()));
            root.getRightChild().setParent(root);
        }
        if (root !=null) {
            int height = -1;
            if (root.getLeftChild() != null) {
                height = root.getLeftChild().getHeightFromBottom();
            }
            if (root.getRightChild() != null && root.getRightChild().getHeightFromBottom() > height) {
                height = root.getRightChild().getHeightFromBottom();
            }
            root.setHeightFromBottom(height + 1);
        }

        return root;
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