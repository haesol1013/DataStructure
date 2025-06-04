import java.util.NoSuchElementException;

public class ListQueue <E> {
    // Queue의 맨 앞 Node
    private Node<E> front;
    // Queue의 맨 뒤 Node
    private Node<E> rear;
    // Queue의 요소 개수
    private int size;

    // ListQueue 클래스의 생성자
    // 빈 Queue로 초기화
    public ListQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Queue의 현재 크기 반환
    public int size() {
        return this.size;
    }

    // Queue가 비어있는지 확인
    // 비어있으면 true 반환
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Queue의 맨 뒤에 새로운 item 추가
    public void add(E item) {
        Node<E> newNode = new Node<>(item, null);
        // Queue가 비어있는 경우 front도 새 노드를 가리킴
        if (isEmpty()) {
            this.front = newNode;
        } else {
            // 마지막 노드의 next를 새 노드로 연결
            rear.setNext(newNode);
        }
        // rear 포인터 업데이트
        this.rear = newNode;
        this.size++;
    }

    // Queue의 맨 앞에서 item 제거 후 반환
    public E remove() {
        // 빈 Queue에서 제거시 예외 발생
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node<E> tmp = this.front.getNext();
        E val = front.getItem();
        // 기존 front 노드 연결 해제
        front.setNext(null);
        // front 포인터 업데이트
        front = tmp;
        size--;
        return val;
    }

    // Queue의 모든 item을 순서대로 출력
    public void print() {
        Node<E> node = this.front;
        while (!(node==null)) {
            System.out.print(node.getItem() + " ");
            node = node.getNext();
        }
        System.out.println();
    }
}