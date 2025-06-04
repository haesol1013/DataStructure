import java.util.NoSuchElementException;

public class ListStack <E> {
    // Stack의 맨 위 Node를 가리키는 변수
    private Node<E> top;
    // Stack에 저장된 요소의 개수를 저장하는 변수
    private int size;

    // ListStack 클래스의 생성자
    // top을 null로, size를 0으로 초기화
    public ListStack() {
        top = null;
        size = 0;
    }

    // Stack의 크기를 반환하는 메소드
    // 현재 Stack에 저장된 요소의 개수 반환
    public int size() {
        return this.size;
    }

    // Stack이 비어있는지 확인하는 메소드
    // Stack이 비어있으면 true, 아니면 false 반환
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Stack의 맨 위 요소를 제거하지 않고 반환하는 메소드
    // Stack이 비어있으면 NoSuchElementException 발생
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.top.getItem();
    }

    // Stack의 맨 위에 새로운 요소를 추가하는 메소드
    // 매개변수로 받은 item을 Stack의 맨 위에 추가
    public void push(E item) {
        this.top = new Node<>(item, this.top);
        size++;
    }

    // Stack의 맨 위 요소를 제거하고 반환하는 메소드
    // Stack이 비어있으면 NoSuchElementException 발생
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> tmp = this.top.getNext();
        E val = this.top.getItem();
        // 기존 top 노드 연결 해제
        this.top.setNext(null);
        // top 포인터 업데이트
        this.top = tmp;
        size--;
        return val;
    }

    // Stack의 모든 요소를 순서대로 출력하는 메소드
    // top부터 아래로 차례로 출력
    public void print() {
        Node<E> node = this.top;
        while (!(node==null)) {
            System.out.print(node.getItem() + " ");
            node = node.getNext();
        }
        System.out.println();
    }
}