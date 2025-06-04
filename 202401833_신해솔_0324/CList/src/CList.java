import java.util.NoSuchElementException;

public class CList <E> {
    // 마지막 노드, 노드의 개수를 저장하는 변수 선언
    private Node<E> last;
    private int size;

    // CList의 생성자
    public CList() {
        // 마지막 노드를 null, 노드의 개수를 0으로 설정
        this.last = null;
        this.size = 0;
    }

    // 노드의 개수를 리턴
    public int size() {
        return this.size;
    }

    // 리스트의 empty 여부 리턴
    public boolean isEmpty() {
        return size == 0;
    }

    // 마지막 노드 반환
    public Node<E> getLast() {
        return this.last;
    }

    // 마지막 노드가 가리키는 노드의 다음에 새 노드 삽입
    public void insert(E newItem) {
        // 새 노드 생성
        Node<E> newNode = new Node<>(newItem, null);
        // 마지막 노드가 null이라면
        if (last == null) {
            // newNode의 다음 노드를 자기 자신으로 설정
            newNode.setNext(newNode);
            // 마지막 노드를 newNode로 설정
            last = newNode;
        } else {
            // newNode의 다음 노드를 마지막 노드의 다음 노드로 설정
            newNode.setNext(last.getNext());
            // 마지막 노드의 다음 노드를 newNode로 설정
            last.setNext(newNode);
        }
        size++;
    }

    // 마지막 노드가 가리키는 노드의 다음 노드를 제거
    public Node<E> delete() {
        // 리스트가 비어있다면 오류 발생
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // 마지막 노드의 다음 노드를 저장
        Node<E> tmp = last.getNext();
        if (tmp == last) {
            last = null;
        } else {
            // 마지막 노드의 다음 노드를 tmp 노드의 다음 노드로 저장
            last.setNext(tmp.getNext());
            // tmp의 다음 노드를 null로 설정
            tmp.setNext(null);
        }
        size--;
        return tmp;
    }

    // 리스트 노드들의 항목들을 차례로 출력
    public void print() {
        // 첫 번째 노드부터 시작
        Node<E> node = last.getNext();
        for (int i = 0; i < size; i++) {
            System.out.print(node.getItem() + " ");
            node = node.getNext();
        }
    }
}
