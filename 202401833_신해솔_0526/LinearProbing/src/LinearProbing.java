import javax.print.DocFlavor;

public class LinearProbing<K, V> {
    public static void main(String[] args) {
        LinearProbing<Integer, String> t = new LinearProbing<>();
        t.put(25, "grape");
        t.put(37, "apple");
        t.put(18, "banana");
        t.put(55, "cherry");
        t.put(22, "mango");
        t.put(35, "lime");
        t.put(50, "orange");
        t.put(63, "watermelon");

        System.out.println("탐색 결과: ");
        System.out.println("50의 data = " + t.get(50));
        System.out.println("63의 data = " + t.get(63));
        System.out.println();

        System.out.println("해시 테이블: ");
        for (int i = 0; i < t.M; ++i) {
            System.out.printf("%-6d", i);
        }
        System.out.println();
        for (int i = 0; i < t.M; ++i) {
            System.out.printf("%-6d", t.table[i]);
        }
        System.out.println();
    }

    // hash table의 크기
    private final int M = 13;
    // key들을 저장할 array
    private final Object[] table = new Object[M];
    // value(data)들을 저장할 array
    private final Object[] values = new Object[M];

    // hash 값을 반환하는 메서드
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // 새로운 원소를 삽입하는 메서드
    private void put(K key, V data) {
        // hash 값을 i에 저장
        int i = hash(key);
        // hash table의 사이즈만큼 반복
        for (int j = 0; j < M; ++j) {
            // 해당 index에 key가 없다면
            if (table[i] == null) {
                // key 생성
                table[i] = key;
                // data 생성
                values[i] = data;
                return;
            }
            // key가 이미 존재 한다면
            if (table[i].equals(key)) {
                // data 교체
                values[i] = data;
                return;
            }
            // 다음 index로
            i = (i+1) % M;
        }
    }

    // key에 해당하는 value를 get하는 메서드
    public V get(K key) {
        // hash 값을 i에 저장
        int i = hash(key);
        // key가 존재하는 index까지만 반복
        while (table[i] != null) {
            // 해당 index의 key 값이 key 값과 같다면
            if (table[i].equals(key)) {
                // 해당하는 index의 data를 반환
                return (V) values[i];
            }
            // 다음 index로
            i = (i+1) % M;
        }
        // 반복이 끝났음에도 반환된 값이 없다면 찾지 못한 것
        return null;
    }
}
