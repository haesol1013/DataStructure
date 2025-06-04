import java.util.NoSuchElementException;


public class ArrayQueue <E> {
    // 큐의 요소를 저장할 배열
    private E[] q;
    // 큐의 맨 앞 위치를 가리키는 인덱스
    private int front;
    // 큐의 맨 뒤 위치를 가리키는 인덱스
    private int rear;
    // 큐에 저장된 요소 개수
    private int size;

    // ArrayQueue 클래스의 생성자
    // 크기 2인 배열로 초기화
    public ArrayQueue() {
        q = (E[]) new Object[2];
        front = 0;
        rear = 0;
        size = 0;
    }

    // 큐의 현재 크기 반환
    public int size() {
        return this.size;
    }

    // 큐가 비어있는지 확인
    // 비어있으면 true 반환
    public boolean isEmpty() {
        return this.size == 0;
    }

    // 큐의 맨 뒤에 새로운 item 추가
    public void add(E item) {
        // 큐가 가득 찼는지 확인 (rear+1이 front와 같은 위치가 되면 가득 찬 상태)
        // 가득 찼다면 length를 두 배로 resize
        if ((rear+1) % q.length == front) {
            resize(q.length*2);
        }
        // rear 포인터를 다음 위치로 이동 (원형 큐이므로 나머지 연산 사용)
        rear = (rear+1) % q.length;
        // 새 아이템을 rear 위치에 저장
        q[rear] = item;
        size++;
    }

    // 큐의 맨 앞에서 item 제거 후 반환
    public E remove() {
        // 빈 큐에서 제거시 예외 발생
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        // front 포인터를 증가시킨 후 해당 위치의 요소 꺼내기
        E item = q[++front];
        // 꺼낸 요소 위치를 null로 설정
        q[front] = null;
        size--;
        // 큐의 요소가 배열 크기의 1/4로 줄었다면 배열 크기를 절반으로 축소
        if (size > 0 && size == q.length/4) {
            resize(q.length/2);
        }
        return item;
    }

    // 배열의 크기를 조정하는 메서드
    private void resize(int newSize) {
        E[] new_q = (E[]) new Object[newSize];
        // 기존 배열의 요소들을 새 배열로 복사
        // i: 새 배열의 인덱스 (1부터 시작) (0은 fornt)
        // j: 기존 배열에서 요소를 가져올 인덱스 (front+1부터 시작)
        // size 번 만큼 복사 수행
        for (int i = 1, j = front+1; i <= size; ++i, ++j) {
            // 원형 큐이므로 j값이 배열 끝에 도달하면 다시 처음으로 돌아가도록 나머지 연산
            new_q[i] = q[j%q.length];
        }
        // front 포인터 초기화
        front = 0;
        // rear 포인터를 복사한 요소 개수 위치로 설정
        rear = size;
        q = new_q;
    }

    // 큐의 모든 요소를 순서대로 출력
    public void print() {
        for (E item: q) {
            // 각 요소를 8자리 너비의 공간에 왼쪽 정렬하여 출력
            System.out.printf("%-8s", item == null ? "null" : item.toString());
        }
        System.out.println();
    }
}