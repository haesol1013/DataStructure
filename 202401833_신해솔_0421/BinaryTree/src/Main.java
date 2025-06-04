public class Main {
    public static void main(String[] args) {
        Node<Integer> n1 = new Node<>(100, null, null);
        Node<Integer> n2 = new Node<>(200, null, null);
        Node<Integer> n3 = new Node<>(300, null, null);
        Node<Integer> n4 = new Node<>(400, null, null);
        Node<Integer> n5 = new Node<>(500, null, null);
        Node<Integer> n6 = new Node<>(600, null, null);
        Node<Integer> n7 = new Node<>(700, null, null);
        Node<Integer> n8 = new Node<>(800, null, null);

        n1.setLeft(n2); n1.setRight(n3);
        n2.setLeft(n4); n2.setRight(n5);
        n3.setLeft(n6); n3.setRight(n7);
        n4.setLeft(n8);

        BinaryTree<Integer> t = new BinaryTree<>();
        t.setRoot(n1);

        System.out.println("트리 노드 수 = " + t.size(t.getRoot()));
        System.out.println("트리 높이 = " + t.height(t.getRoot()));

        System.out.print("전위 순회 : ");
        t.preorder(t.getRoot());

        System.out.print("\n중위 순회 : ");
        t.inorder(t.getRoot());

        System.out.print("\n후위 순회 : ");
        t.postorder(t.getRoot());

        System.out.print("\n레벨 순회 : ");
        t.levelorder(t.getRoot());
        System.out.println();

        Node<Integer> n10 = new Node<>(100, null, null);
        Node<Integer> n20 = new Node<>(200, null, null);
        Node<Integer> n30 = new Node<>(300, null, null);
        Node<Integer> n40 = new Node<>(400, null, null);
        Node<Integer> n50 = new Node<>(500, null, null);
        Node<Integer> n60 = new Node<>(600, null, null);
        Node<Integer> n70 = new Node<>(700, null, null);
        Node<Integer> n80 = new Node<>(800, null, null);

        n10.setLeft(n20); n10.setRight(n30);
        n20.setLeft(n40); n20.setRight(n50);
        n30.setLeft(n60); n30.setRight(n70);
        n40.setLeft(n80);

        BinaryTree<Integer> t2 = new BinaryTree<>();
        t2.setRoot(n10);

        System.out.println("동일성 검사: " + BinaryTree.isEqual(t.getRoot(), t2.getRoot()));

        BinaryTree<Integer> t3 = new BinaryTree<>();
        t3.setRoot(t3.copy(t.getRoot()));
        System.out.println("copy 테스트: " + BinaryTree.isEqual(t.getRoot(), t3.getRoot()));
    }
}