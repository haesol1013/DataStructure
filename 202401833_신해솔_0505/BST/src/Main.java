public class Main {
    public static void main(String[] args) {
        BST<Integer, String> t = new BST<>(500, "Apple");

        t.put(600, "Banana");
        t.put(200, "Melon");
        t.put(100, "Orange");
        t.put(400, "Tangerine");
        t.put(250, "Kiwi");
        t.put(150, "Grape");
        t.put(800, "Strawberry");
        t.put(700, "Cherry");
        t.put(50, "Pear");
        t.put(350, "Lemon");
        t.put(10, "Watermelon");
        t.print(t.getRoot());
        System.out.println();
        System.out.println("높이 = " + t.height());
        System.out.println("key 350값 = " + t.get(350));
        System.out.println("최소값 = " + t.min());
        t.deleteMin();
        t.deleteMax();
        t.delete(200);
        t.print(t.getRoot());
    }
}