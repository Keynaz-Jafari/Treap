# Treap Project

## Overview

A Treap is a combination of a binary search tree and a heap. This project implements a Treap data structure in Java, providing various operations for efficient management and querying of key-value pairs.

## Features

### Basic Operations

1. **Insertion (`add`)**
    - Adds a new key to the Treap while maintaining the binary search tree and heap properties.
2. **Deletion (`delete`)**
    - Removes a specified key from the Treap and adjusts the structure.
3. **Search (`treapSearch`)**
    - Searches for a specific key in the Treap.

### Advanced Operations

1. **K-th Smallest Element (`kthSmallestElement`)**
    - Finds the k-th smallest element in the Treap.
2. **Ordered Keys (`orderedKeys`)**
    - Returns the keys of the Treap in ascending order.

### Utility Functions

1. **Get Minimum (`getTreapMin`)**
    - Returns the minimum key in the Treap.
2. **Get Maximum (`getTreapMax`)**
    - Returns the maximum key in the Treap.
3. **Get Height (`getTreapHeight`)**
    - Returns the height of the Treap.

## Implementation Details

### Node Class

- **Key**: The value stored in the node.
- **Priority**: A random value assigned to each node to maintain heap property.
- **Subtree Size**: The size of the subtree rooted at this node.
- **Height From Bottom**: The height of the node from the bottom of the tree.
- **Successor & Predecessor**: Pointers to the next and previous nodes in sorted order.
- **Parent, Left Child, Right Child**: Pointers to the parent, left, and right children nodes.

### Treap Class

- **Root**: The root node of the Treap.
- **Min & Max**: Pointers to the nodes with the minimum and maximum keys, respectively.

### Rotation Operations

- **Left Rotate**
    - Performs a left rotation on a node to maintain heap property. The heap property is based on the priority value which is generated randomly.
- **Right Rotate**
    - Performs a right rotation on a node to maintain heap property. The heap property is based on the priority value which is generated randomly.

### Min/Max Updates

- **Update Min/Max**
    - Updates the `min` and `max` pointers after an insertion or deletion.

### Size and Height Updates

- **Update Subtree Size**
    - Updates the size of the subtree rooted at a given node.
- **Update Height**
    - Updates the height of a node from the bottom of the tree.

## Example Usage

```java
javaCopy code
public class Main {
    public static void main(String[] args) {
        Treap<Integer> treap = new Treap<>();

        // Adding elements
        treap.add(5);
        treap.add(3);
        treap.add(8);

        // Searching for an element
        Node<Integer> node = treap.treapSearch(treap.getRoot(), 3);

        // Deleting an element
        treap.delete(5);

        // Finding the k-th smallest element
        Integer kthElement = treap.kthSmallestElement(2);

        // Getting the ordered keys
        ArrayList<Integer> keys = treap.orderedKeys();

        // Getting the minimum and maximum keys
        Integer min = treap.getTreapMin();
        Integer max = treap.getTreapMax();

        // Getting the height of the Treap
        int height = treap.getTreapHeight();
    }
}

```

## Performance

The Treap ensures that the binary search tree and heap properties are maintained with each insertion and deletion operation, resulting in balanced trees that support efficient query operations. The average height of the Treap after inserting 100,000 elements is evaluated over 1,000 trials in the provided `main` method.