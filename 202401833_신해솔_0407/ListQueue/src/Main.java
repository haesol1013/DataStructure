public class Main {
    public static void main(String[] args) {
        ListQueue<String> q = new ListQueue<>();
        q.add("apple");
        q.add("orange");
        q.add("cherry");
        q.add("pear");
        q.print();

        q.remove();
        q.print();

        q.remove();
        q.print();

        q.add("grape");
        q.print();
    }
}