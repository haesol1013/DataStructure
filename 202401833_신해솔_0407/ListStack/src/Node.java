public class Node <E> {
    // 아이템과 다음 노드를 저장하기 위한 변수 선언
    private E item;
    private Node<E> next;

    // Node 클래스의 생성자
    // 아이템과 노드를 받아 각각 item, next에 저장
    public Node(E newItem, Node<E> node) {
        this.item = newItem;
        this.next = node;
    }

    // item의 getter
    public E getItem() {
        return item;
    }

    // next의 getter
    public Node<E> getNext() {
        return next;
    }

    // item의 setter
    public void setItem(E newItem) {
        item = newItem;
    }

    // next의 setter
    public void setNext(Node<E> node) {
        next = node;
    }
}
