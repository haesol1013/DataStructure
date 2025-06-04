public class Node {
    // parent와 rank 값을 저장 (private)
    private int parent;
    private int rank;

    // Node의 생성자
    public Node(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }

    // parent의 getter
    public int getParent() {
        return parent;
    }

    // rank의 getter
    public int getRank() {
        return rank;
    }

    // parent의 getter
    public void setParent(int parent) {
        this.parent = parent;
    }

    // rank setter
    public void setRank(int rank) {
        this.rank = rank;
    }
}
