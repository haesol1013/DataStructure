import java.util.NoSuchElementException;

public class DList <E> {
    // DList의 처음/끝 노드, 노드의 수를 저장
    protected DNode<E> head;
    protected DNode<E> tail;
    private int size;

    // DList의 생성자
    public DList() {
        // haed는 item, 이전 노드, 다음 노드까지 null로 초기화
        this.head = new DNode<>(null, null, null);
        // tail은 이전 노드를 head로 설정
        this.tail = new DNode<>(null, this.head, null);
        // head의 다음 노드를 tail로 설정
        this.head.setNext(this.tail);
        // 원소의 개수는 0
        this.size = 0;
    }

    // 노드의 개수를 리턴
    public int size() {
        return this.size;
    }

    // DList가 비었는지(head와 tail 사이에 데이터 노드가 없는지)를 리턴
    public boolean isEmpty() {
        return this.size == 0;
    }

    // 원하는 노드, 저장할 값을 지정하여 타겟 노드 앞에 붙임
    public void insertBefore(DNode<E> target, E newItem) {
        // 타겟 노드의 이전 노드를 저장
        DNode<E> prev = target.getPrev();
        // 이전/다음 노드와 연결된 새 노드 생성
        DNode<E> newNode = new DNode<>(newItem, prev, target);
        // prev의 다음 노드를 newNode로 설정
        prev.setNext(newNode);
        // target의 이전 노드를 newNode로 설정
        target.setPrev(newNode);
        size++;
    }

    // 타겟 노드 뒤에 새 노드를 붙임
    public void insertAfter(DNode<E> target, E newItem) {
        // 타겟 노드의 다음 노드를 저장
        DNode<E> next = target.getNext();
        // 이전/다음 노드와 연결된 새 노드 생성
        DNode<E> newNode = new DNode<>(newItem, target, next);
        // 타겟의 다음 노드를 newNode로 설정
        target.setNext(newNode);
        // next의 이전 노드를 newNode로 설정
        next.setPrev(newNode);
        size++;
    }

    // 타겟 노드를 제거
    public void delete(DNode<E> target) {
        // 타겟이 null이라면 오류 발생
        if (target == null) {
            throw new NoSuchElementException();
        }
        // 타겟 노드의 이전/이후 노드를 저장
        DNode<E> prev = target.getPrev();
        DNode<E> next = target.getNext();
        // 이전 노드와 이후 노드 연결
        prev.setNext(next);
        next.setPrev(prev);
        size--;
    }

    // DList 내의 노드의 아이템들을 출력
    public void print() {
        if (isEmpty()) {
            System.out.println("리스트 비어있음");
            return;
        }
        // head 다음 노드부터 시작 (첫 번째 실제 데이터 노드)
        DNode<E> node = head.getNext();
        // node가 tail이 아닐 동안 출력
        while (node.getItem() != null) {
            System.out.print(node.getItem() + " ");
            node = node.getNext();
        }
        System.out.println();
    }
}
