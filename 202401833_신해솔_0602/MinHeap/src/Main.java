public class Main {
    public static void main(String[] args) {
        Entry<Integer, String>[] arr = new Entry[16];  // a[0]은 사용 안함
        arr[1] = new Entry<>(90, "watermelon");
        arr[2] = new Entry<>(80, "pear");
        arr[3] = new Entry<>(70, "melon");
        arr[4] = new Entry<>(50, "lime");
        arr[5] = new Entry<>(60, "mango");
        arr[6] = new Entry<>(20, "cherry");
        arr[7] = new Entry<>(30, "grape");
        arr[8] = new Entry<>(35, "orange");
        arr[9] = new Entry<>(10, "apricot");
        arr[10] = new Entry<>(15, "banana");
        arr[11] = new Entry<>(45, "lemon");
        arr[12] = new Entry<>(40, "kiwi");

        // 힙 객체 생성
        BHeap<Integer, String> heap = new BHeap<>(arr, 12);
        System.out.println("힙 만들기 전:");
        heap.print();

        heap.createHeap(); // 힙 만들기
        System.out.println("최소힙:");
        heap.print();

        System.out.println("min 삭제 후:");
        System.out.println(heap.deleteMin().getValue());
        heap.print();

        heap.insert(5, "apple");
        System.out.println("5 삽입 후:");
        heap.print();
    }
}