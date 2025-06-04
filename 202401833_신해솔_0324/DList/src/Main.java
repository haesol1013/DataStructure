public class Main {
    public static void main(String[] args) {
        DList<String> s = new DList<>();

        s.insertAfter(s.head, "apple");
        s.insertBefore(s.tail, "orange");
        s.insertBefore(s.tail, "cherry");
        s.insertAfter(s.head.getNext(), "pear");
        s.print(); System.out.println();

        s.delete(s.tail.getPrev());
        s.print(); System.out.println();

        s.insertBefore(s.tail, "grape");
        s.print(); System.out.println();
        s.delete(s.head.getNext()); s.print(); s.delete(s.head.getNext()); s.print();
        s.delete(s.head.getNext()); s.print(); s.delete(s.head.getNext()); s.print();
    }
}
