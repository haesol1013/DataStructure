public class DNode <E> {
    // 값, 이전 노드, 다음 노드를 저장하기 위한 변수 선언
    private E item;
    private DNode<E> prev;
    private DNode<E> next;

    // DNode의 생성자
    // 값, 이전 노드, 다음 노드를 입력받아 저장
    public DNode(E newItem, DNode<E> prev, DNode<E> next) {
        this.item = newItem;
        this.prev = prev;
        this.next = next;
    }

    // Item의 getter
    public E getItem() {
        return this.item;
    }

    // prev의 getter
    public DNode<E> getPrev() {
        return this.prev;
    }

    // next의 getter
    public DNode<E> getNext() {
        return this.next;
    }

    // item의 setter
    public void setItem(E newItem) {
        this.item = newItem;
    }

    // prev의 setter
    public void setPrev(DNode<E> prev) {
        this.prev = prev;
    }

    // next의 setter
    public void setNext(DNode<E> next) {
        this.next = next;
    }
}
