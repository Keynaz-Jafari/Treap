public class main {
    public static void main(String[] args) {
        Treap<Integer> treap = new Treap<>();

        // Insert nodes
        treap.add(10);
        treap.add(20);
        treap.add(5);
        treap.add(15);
        treap.add(25);

        // Print the root and its left and right children
        System.out.println("After insertions:");
        printTreap(treap.getRoot());

        // Delete a node
        treap.delete(20);

        // Print the root and its left and right children after deletion
        System.out.println("After deletion of 20:");
        printTreap(treap.getRoot());
    }

    private static void printTreap(Node<Integer> node) {
        if (node == null) return;

        System.out.println("Node: " + node.getKey() + ", Priority: " + node.getPriority() + ", SubtreeSize: " + node.getSubtreeSize() + ", Height: " + node.getHeightFromBottom());
        if (node.getLeftChild() != null) {
            System.out.println("  Left Child: " + node.getLeftChild().getKey());
        } else {
            System.out.println("  Left Child: null");
        }
        if (node.getRightChild() != null) {
            System.out.println("  Right Child: " + node.getRightChild().getKey());
        } else {
            System.out.println("  Right Child: null");
        }
        if (node.getLeftChild() != null) printTreap(node.getLeftChild());
        if (node.getRightChild() != null) printTreap(node.getRightChild());
    }
}

