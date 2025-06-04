public class Node <Key extends Comparable<Key>, Value> {
    // Node 클래스를 위한 필드
    private Key key;
    private Value value;
    private Node<Key, Value> left, right;

    // Node 클래스의 생성자
    public Node(Key key, Value value) {
        this.key = key;
        this.value = value;
        left = null;
        right = null;
    }

    // key의 getter
    public Key getKey() {
        return key;
    }

    // value의 getter
    public Value getValue() {
        return value;
    }

    // left의 getter
    public Node<Key, Value> getLeft() {
        return left;
    }

    // right의 getter
    public Node<Key, Value> getRight() {
        return right;
    }

    // key의 setter
    public void setKey(Key key) {
        this.key = key;
    }

    // value의 setter
    public void setValue(Value value) {
        this.value = value;
    }

    // left의 setter
    public void setLeft(Node<Key, Value> left) {
        this.left = left;
    }

    // right의 setter
    public void setRight(Node<Key, Value> right) {
        this.right = right;
    }
}
