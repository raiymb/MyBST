public class Main {

    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();

        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");
        tree.put(4, "four");
        tree.put(5, "five");

        System.out.println("The size of the tree is: " + tree.size());
        System.out.println("The minimum key in the tree is: " + tree.min());
        System.out.println("The maximum key in the tree is: " + tree.max());

        tree.inorder();
        System.out.println();
        System.out.println("Deleting the node with key 5");
        tree.delete(5);

        System.out.println("The size of the tree is: " + tree.size());
        System.out.println("The minimum key in the tree is: " + tree.min());
        System.out.println("The maximum key in the tree is: " + tree.max());

        tree.inorder();
    }
}