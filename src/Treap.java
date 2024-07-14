
public class Treap<Key extends Comparable<Key>>{
    private Node<Key> root;
    private Node<Key> min;
    private Node<Key> max;

    public Node<Key> add(Key key){
        this.root = add_helper(key,this.root, null, null);
        return root;
    }

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

    public void updateSucAndPre(Node<Key> node , Node<Key> pre, Node<Key> suc){
        if (pre != null){
            pre.setSuccessor(node.getSuccessor());
        }
        if (suc !=null){
            suc.setPredecessor(node.getPredecessor());
        }
    }

    public Node<Key> delete(Key key){
        this.root = delete_helper(key,this.root);
        return root;
    }

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
