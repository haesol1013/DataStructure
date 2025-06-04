import java.util.EmptyStackException;

public class ArrayStack <E> {
    // 스택을 구현할 배열과 최상위 요소의 인덱스를 저장하는 변수 선언
    private E[] arr;
    private int top;

    // ArrayStack의 생성자
    public ArrayStack() {
        // 크기가 1인 배열 생성, 최상위 요소 인덱스를 -1로 설정
        arr = (E[]) new Object[1];
        top = -1;
    }

    // 스택의 크기를 리턴
    public int size() {
        return top + 1;
    }

    // 스택의 empty 여부 리턴
    public boolean isEmpty() {
        return top == -1;
    }

    // 스택의 최상위 요소 반환 (제거하지 않음)
    public E peek() {
        // 스택이 비어있으면 예외 발생
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return arr[top];
    }

    // 스택의 최상위 요소 제거 후 반환
    public E pop() {
        // 스택이 비어있으면 예외 발생
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        // 스택의 크기가 배열 길이의 1/4 이하면 배열 크기 축소
        if (size() <= (float) arr.length * 1/4) {
            resize(arr.length / 2);
        }
        // 최상위 요소 반환 후 top 인덱스 감소
        return arr[top--];
    }

    // 스택에 새로운 요소 추가
    public void push(E item) {
        // 스택이 꽉 찼으면 배열 크기 확장
        if (size() == arr.length) {
            resize(arr.length * 2);
        }
        // top 인덱스 증가 후 새 요소 저장
        arr[++top] = item;
    }

    // 배열 크기 조정 메서드
    private void resize(int newLength) {
        // 새로운 크기의 배열 생성
        E[] tmp = (E[]) new Object[newLength];
        // 기존 요소들을 새 배열로 복사
        for (int i = 0; i < size(); i++) {
            tmp[i] = arr[i];
        }
        // 기존 배열을 새 배열로 대체
        arr = tmp;
    }
}