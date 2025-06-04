public class Node <Key extends Comparable<Key>>{
    // Node의 값을 저장하기 위한 Key type의 변수 선언
    private Key key;
    // Left, Right Child를 저장하기 위한 변수 선언
    private Node<Key> left;
    private Node<Key> right;

    // Node의 생성자
    // key, left, right를 인자로 받아 저장
    public Node(Key key, Node<Key> left, Node<Key> right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    // key의 getter
    public Key getKey() {
        return this.key;
    }

    // left의 getter
    public Node<Key> getLeft() {
        return this.left;
    }

    // right의 getter
    public Node<Key> getRight() {
        return this.right;
    }

    // key의 setter
    public void setKey(Key key) {
        this.key = key;
    }

    // left의 setter
    public void setLeft(Node<Key> left) {
        this.left = left;
    }

    // right의 setter
    public void setRight(Node<Key> right) {
        this.right = right;
    }
}
