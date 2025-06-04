public class Chaining<K, V> {
    public static void main(String[] args) {
        Chaining<Integer, String> t = new Chaining<>();
        t.put(71, "grape");
        t.put(23, "apple");
        t.put(73, "banana");
        t.put(49, "cherry");
        t.put(54, "mango");
        t.put(89, "lime");
        t.put(39, "orange");

        System.out.println();
        System.out.println("해시 테이블");
        for (int i = 0; i < t.M; ++i) {
            System.out.printf("%2d ", i);
            for (Node x = t.table[i]; x != null; x = x.next) {
                System.out.print("-->[" + x.getKey() + ", " + x.getValue() + "]");

            }
            System.out.println();
        }
    }

    // hash table의 사이즈
    private final int M = 11;
    // hash table
    private final Node[] table = new Node[M];

    // Node class
    public static class Node {
        // Node class의 filed
        private final Object key;
        private Object value;
        private Node next;

        // Node class의 생성자
        public Node(Object key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        // key의 getter
        public Object getKey() {
            return key;
        }

        // data의 getter
        public Object getValue() {
            return value;
        }
    }

    // hash 메서드
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // 새로운 원소를 삽입하는 메서드
    public void put(K key, V value) {
        // key에 해당하는 hash 값을 저장
        int i = hash(key);
        // 연결된 node가 없을 때까지 반복
        for (Node x = table[i]; x != null; x = x.next) {
            // 현재 node의 key가 target key와 같다면
            if (x.key.equals(key)) {
                // 현재 node의 value를 업데이트
                x.value = value;
                return;
            }
        }
        // 새로운 key라면 새로운 node를 생성하고 기존 node 앞에 붙임
        table[i] = new Node(key, value, table[i]);
    }

    // key에 해당하는 value를 반환하는 메서드
    public V get(K key) {
        // key에 해당하는 hash 값을 저장
        int i = hash(key);
        // 연결된 node가 없을 때까지 반복
        for (Node x = table[i]; x != null; x = x.next) {
            // 현재 node의 key가 target key와 같다면
            if (x.key.equals(key)) {
                // 해당 node의 value를 반환
                return (V) x.value;
            }
        }
        // 같은 key가 없다면 null 반환
        return null;
    }
}
