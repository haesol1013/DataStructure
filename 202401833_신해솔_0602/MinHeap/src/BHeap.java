public class BHeap<Key extends Comparable<Key>, Value> {
    // 원소들을 저장할 배열
    private final Entry<Key, Value>[] arr;
    // 배열 내 원소 수
    private int size;

    // BHeap의 생성자
    public BHeap(Entry<Key, Value>[] arr, int size) {
        this.arr = arr;
        this.size = size;
    }

    // 힙의 크기를 반환하는 메서드
    public int getSize() {
        return size;
    }

    // 초기 힙을 생성하는 메서드
    public void createHeap() {
        // leaf 노드를 제외한 가장 낮은 index를 갖는 노드부터 루트까지 순회하며 downheap 수행
        for (int i = size/2; i > 0; --i) {
            downheap(i);
        }
    }

    // 새로운 원소를 삽입하는 메서드
    public void insert(Key key, Value value) {
        // 새로운 노드 생성
        Entry<Key, Value> newEntry = new Entry<>(key, value);
        // 마지막 인덱스 다음에 삽입
        arr[++size] = newEntry;
        // upheap 진행
        upheap(size);
    }

    // 최솟값을 삭제하고 반환하는 메서드
    public Entry<Key, Value> deleteMin() {
        // min 노드 저장
        Entry<Key, Value> min = arr[1];
        // root 노드와 마지막 노드 교환
        swap(1, size);
        // 마지막 노드를 비움
        arr[size--] = null;
        // root 노드부터 downheap 수행
        downheap(1);
        // min 노드 반환
        return min;
    }

    // downheap을 수행하는 메서드
    private void downheap(int i) {
        // left child가 있을 때까지 반복
        while (2*i <= size) {
            // left child의 index 저장
            int k = 2*i;
            // k가 마지막 인덱스가 아니고, left child의 key가 right child의 key보다 크면
            if (k < size && greater(k, k+1)) {
                // right child의 index로 이동
                ++k;
            }
            // parent가 child 둘 보다 작다면 종료
            if (!greater(i, k)) {
                break;
            }
            // parent와 child 교환
            swap(i, k);
            // 저장 child의 index로 이동
            i = k;
        }
    }

    // upheap을 수행하는 메서드
    private void upheap(int i) {
        // root의 index가 아니고 child의 key가 parent key보다 클 때까지 반복
        while (i > 1 && greater(i/2, i)) {
            // parent와 child 교환
            swap(i/2, i);
            // parent로 이동
            i = i/2;
        }
    }

    // key 비교후 boolean 값을 반환하는 메서드
    private boolean greater(int i, int j) {
        return arr[i].getKey().compareTo(arr[j].getKey()) > 0;
    }

    // 두 원소의 위치를 교환하는 메서드
    private void swap(int i, int j) {
        Entry<Key, Value> tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 힙을 출력하는 메서드
    public void print() {
        for (int i = 1; i <= size; ++i) {
            System.out.print("[" + arr[i].getKey() + " " + arr[i].getValue() + "] ");
        }
        System.out.println();
        System.out.print("힙 크기 = " + getSize() + "\n\n");
    }
}


