public class Entry <Key extends Comparable<Key>, Value>{
    // Entry의 field
    private Key key;
    private Value value;

    // Entry의 생성자
    public Entry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    // key의 getter
    public Key getKey() {
        return key;
    }

    // value의 getter
    public Value getValue() {
        return value;
    }

    // key의 setter
    public void setKey(Key key) {
        this.key = key;
    }

    // value의 setter
    public void setValue(Value value) {
        this.value = value;
    }
}
