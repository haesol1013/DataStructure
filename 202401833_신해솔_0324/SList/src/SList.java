import java.util.NoSuchElementException;

public class SList <E> {
    // 맨 앞 node를 저장하는 head 변수 선언
    protected Node<E> head;
    // 노드의 개수를 저장하는 size 변수 선언
    private int size;

    // SList의 생성자
    // head는 null, size는 0으로 초기화
    public SList() {
        head = null;
        size = 0;
    }

    // head의 getter
    public Node<E> getHead() {
        return head;
    }

    // head의 setter
    public void setHead(Node<E> node) {
        head = node;
    }

    // size의 getter
    public int size() {
        return size;
    }

    // SList가 비었는지(노드가 하나도 없는지)를 리턴
    public boolean isEmpty() {
        return size == 0;
    }

    // 맨 앞에 노드를 삽입
    public void insertFront(E newItem) {
        head = new Node<>(newItem, head);
        size++;
    }

    // target 노드 다음에 새 노드를 삽입
    public void insertAfter(E newItem, Node<E> target) {
        target.setNext(new Node<>(newItem, target.getNext()));
        size++;
    }

    // 맨 앞 노드를 삭제
    public void deleteFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        head = head.getNext();
        size--;
    }

    // target 다음의 노드를 제거
    public void deleteAfter(Node<E> target) {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // target node의 다음 노드를 tmp 변수에 저장
        Node<E> tmp = target.getNext();
        // target node의 다음 노드를 tmp의 다음 노드로 설정
        target.setNext(tmp.getNext());
        // tmp node의 다음 노드를 제거
        tmp.setNext(null);
        size--;
    }

    // target을 탐색
    // 존재하지 않는다면 -1 리턴
    public int search(E target) {
        // node 별로 검사하기 때문에 head를 node 변수에 저장
        Node<E> node = head;
        // 원소의 개수만큼 순회
        for (int i = 0; i < size; i++) {
            // 타겟과 현재 노드의 값이 같다면 해당하는 index 리턴
            if (target.equals(node.getItem())) {
                return i;
            }
            // 값이 같지 않다면 다음 노드로 이동
            node = node.getNext();
        }
        // for 문을 순회하며 SList의 모든 노드를 검사했음에도 타겟과 같은 값을 갖는 노드가 없다면 -1 리턴
        return -1;
    }

    // SList의 모든 노드의 item을 출력
    public void print() {
        Node<E> node = head;
        for(int i = 0; i < size; i++) {
            System.out.print(node.getItem() + " ");
            node = node.getNext();
        }
    }
}