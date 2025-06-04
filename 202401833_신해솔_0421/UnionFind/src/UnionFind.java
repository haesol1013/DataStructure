public class UnionFind {
    // 원소들을 저장할 array 생성
    protected Node[] arr;

    // UnionFind의 생성자
    public UnionFind(Node[] arr) {
        this.arr = arr;
    }

    // 원소의 root를 반환
    private int find(int i) {
        // 원소가 root가 아니라면
        if (arr[i].getParent() != i) {
            // root까지 올라가고 root를 parent로 설정
            arr[i].setParent(find(arr[i].getParent()));
        }
        // parent 리턴
        return arr[i].getParent();
    }

    // 두 원소를 union하는 함수
    public void union(int i, int j) {
        // i의 root 저장
        int iRoot = find(i);
        // j의 root 저장
        int jRoot = find(j);

        // 두 원소의 root가 같을 경우 아무런 실행 없이 리턴
        if (iRoot == jRoot) {
            return;
        }

        // i의 root의 rank가 더 클 경우
        if (arr[iRoot].getRank() > arr[jRoot].getRank()) {
            // j의 root를 i의 root에 붙임
            arr[jRoot].setParent(iRoot);
        // j의 root의 rank가 더 클 경우
        } else if (arr[iRoot].getRank() < arr[jRoot].getRank()) {
            // i의 root를 j의 root에 붙임
            arr[iRoot].setParent(jRoot);
        // 두 root의 rank가 같을 경우
        } else {
            // 임의로 j의 root를 i의 root에 붙임
            arr[jRoot].setParent(iRoot);
            // i의 root의 rank에 1을 더함
            arr[iRoot].setRank(arr[iRoot].getRank() + 1);
        }
    }
}
