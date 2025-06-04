import java.util.NoSuchElementException;

public class ArrList <E> {
    // E type을 원소로 갖는 1차원 array 선언
    private E[] arr;
    // 원소의 수를 저장하기 위한 int type의 size 변수 선언
    private int size;

    // ArrList의 생성자
    public ArrList() {
        // length가 1인 Object객체를 생성하고 E type의 1차원 array로 캐스팅
        arr = (E[]) new Object[1];
        // size를 0으로 초기화
        size = 0;
    }

    // 원소가 없는 것을 확인하는 메소드
    public boolean isEmpty() {
        // 원소가 0개라면 true return
        return size == 0;
    }

    // array를 resize하는 메소드
    private void resize(int newSize) {
        // newSize 크기의 Object 생성
        Object[] tmp = new Object[newSize];
        // arr의 원소를 tmp에 복사
        for (int i = 0; i < size; i++) {
            tmp[i] = arr[i];
        }
        // tmp를 E type으로 캐스팅 후 arr에 할당
        arr = (E[]) tmp;
    }

    // idx번째 원소를 return 하는 메소드
    public E peek(int idx) {
        // arr이 비었는지 확인 후, 비었다면 NoSuchElementException throw
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        // arr의 idx번째 원소 return
        return arr[idx];
    }

    // idx번째 원소를 delete하고 return하는 메소드
    public E delete(int idx) {
        // arr이 비었는지 확인 후, 비었다면 NoSuchElementException throw
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        // 원소를 이동하면 idx번째 원소가 지워지므로 tmp 변수에 저장
        E tmp = arr[idx];
        // idx부터 size-1까지 순회하며 오른쪽에 위치한 원소로 값 변환
        for (int i = idx; i < size; i++) {
            arr[i] = arr[i+1];
        }
        // 원소가 하나 지워졌으므로 size 하나 줄이기
        size--;

        // 원소가 채워져 있는 비율을 저장
        float stackRatio = (float) size / arr.length;
        // 0.25보다 같거나 적게 원소가 남았다면 resize 함수 호출
        if (stackRatio <= 0.25) {
            resize( arr.length / 2);
        }

        // tmp에 저장한 idx번째의 원소 return
        return tmp;
    }

    // 리스트의 맨 뒤에 원소를 추가
    public void insertLast(E newItem) {
        // 원소가 가득 찾다면 length를 두 배 늘리기
        if (arr.length == size) {
            resize(arr.length*2);
        }

        // 리스트의 맨 뒤에 원소를 추가
        arr[size++] = newItem;
    }

    public void insert(E newItem, int idx) {
        // 원소가 가득 찾다면 length를 두 배 늘리기
        if (arr.length == size) {
            resize(arr.length*2);
        }

        // 현재 마지막 원소부터 한 칸씩 오른쪽으로 이동
        for (int i = size; i >= idx; i--) {
            arr[i+1] = arr[i];
        }

        // idx 번째에 새로운 원소 저장
        arr[idx] = newItem;
        // size 늘리기
        size++;
    }

    // array 내 원소를 출력
    public void print() {
        // arr의 모든 원소를 " "로 구분하여 출력
        for (E item: arr) {
            System.out.print(item + " ");
        }
        // 모두 출력 후, 줄바꿈
        System.out.println();
    }
}
