import java.util.Iterator;

public class BST<K extends Comparable<K>,V> {

    private Node<K,V> root;

    // A private inner class that represents a node in the binary search tree.
    private class Node<K extends Comparable<K>,V> implements Comparable<Node<K,V>> {

        private K key;

        private V value;

        private Node<K,V> left;

        private Node<K,V> right;

        private int size;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node<K,V> getLeft() {
            return left;
        }

        public void setLeft(Node<K,V> left) {
            this.left = left;
        }

        public Node<K,V> getRight() {
            return right;
        }

        public void setRight(Node<K,V> right) {
            this.right = right;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        // Compares this node to the given node.
        @Override
        public int compareTo(Node<K,V> other) {
            return key.compareTo(other.key);
        }
    }

    // Inserts the given key and value into the binary search tree.
    public void put(K key, V value) {
        if (root == null) {
            root = new Node<>(key, value);
        } else {
            put(root, key, value);
        }
    }

    // Private helper method that recursively inserts the given key and value into the binary search tree.
    private void put(Node<K,V> node, K key, V value) {
        if (key.compareTo(node.key) < 0) {
            if (node.left == null) {
                node.left = new Node<>(key, value);
            } else {
                put(node.left, key, value);
            }
        } else if (key.compareTo(node.key) > 0) {
            if (node.right == null) {
                node.right = new Node<>(key, value);
            } else {
                put(node.right, key, value);
            }
        }
        node.size++;
    }

    // Returns the value associated with the given key.
    public V get(K key) {
        if (root == null) {
            return null;
        } else {
            return get(root, key);
        }
    }

    // Private helper method that recursively searches for the value associated with the given key.
    private V get(Node<K,V> node, K key) {
        if (node == null) {
            return null;
        } else if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    // Deletes the node with the given key from the binary search tree.
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node<K,V> delete(Node<K,V> node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node<K,V> minNode = min(node.right);
                node.key = minNode.key;
                node.value = minNode.value;
                node.right = delete(node.right, node.key);
            }
        }

        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private int size(Node<K,V> node) {
        if (node == null) {
            return 0;
        } else {
            return node.size;
        }
    }

    // Returns the minimum key in the binary search tree.
    public K min() {
        if (root == null) {
            return null;
        } else {
            return min(root).key;
        }
    }

    // Private helper method that recursively finds the minimum key in the binary search tree.
    private Node<K,V> min(Node<K,V> node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    // Returns the maximum key in the binary search tree.
    public K max() {
        if (root == null) {
            return null;
        } else {
            return max(root).key;
        }
    }

    // Private helper method that recursively finds the maximum key in the binary search tree.
    private Node<K,V> max(Node<K,V> node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.right);
        }
    }

    // Returns the size of the binary search tree.
    public int size() {
        if (root == null) {
            return 0;
        } else {
            return root.size;
        }
    }

    // Performs an in-order traversal of the binary search tree.
    public void inorder() {
        if (root == null) {
            return;
        } else {
            inorder(root);
        }
    }

    // Private helper method that recursively performs an in-order traversal of the binary search tree.
    private void inorder(Node<K,V> node) {
        if (node != null) {
            inorder(node.left);
            System.out.println("key is " + node.key + " and value is " + node.value);
            inorder(node.right);
        }
    }

    // Iterator that iterates over the binary search tree in in-order fashion.
    public Iterator<Node<K,V>> iterator() {
        return new Iterator<Node<K,V>>() {

            // The current node in the iteration.
            Node<K,V> current = root;

            // Returns true if the iteration has more elements.
            public boolean hasNext() {
                return current != null;
            }

            // Returns the next element in the iteration.
            public Node<K,V> next() {
                Node<K,V> result = current;
                current = current.left;
                return result;
            }
        };
    }
}