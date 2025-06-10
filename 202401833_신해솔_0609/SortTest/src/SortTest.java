public class SortTest {
    public void SelectionSort(Comparable[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            // 정렬 안 된 부분에서 가장 작은 value의 key를 저장
            int minIndex = i;
            for (int j = i+1; j < arr.length; ++j) {
                if (isLess(arr[j], arr[minIndex])) {
                    minIndex = j;
                }
            }
            // i와 minIndex의 value swap
            swap(arr, i, minIndex);
        }
    }

    public void InsertionSort(Comparable[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            // 하나씩 왼쪽으로 가며 값 비교 후, 현재 값이 더 작을 시 swap
            for (int j = i; j > 0; --j) {
                if (isLess(arr[j], arr[j-1])) {
                    swap(arr, j, j-1);
                }
            }
        }
    }


    public void HeapSort(Comparable[] arr) {
        // 0번째 index는 미사용 하므로 size--
        int heapSize = arr.length - 1;
        // leaf node를 제외하고 downHeap 진행
        // maxHeap을 만듦
        for (int i = heapSize/2; i > 0; --i) {
            downHeap(arr, i, heapSize);
        }
        // array의 마지막 원소에 max 값을 이동
        while (heapSize > 1) {
            swap(arr, 1, heapSize);
            downHeap(arr, 1, --heapSize);
        }
    }

    private void downHeap(Comparable[] arr, int p, int heapSize) {
        // child의 index가 존재할 때까지 반복
        while (p*2 <= heapSize) {
            // left child의 index 저장
            int c = p*2;
            // right child가 더 크다면 right child의 index를 저장
            if (c < heapSize && isLess(arr[c], arr[c+1])) ++c;
            // parent가 더 크다면 종류
            if (!isLess(arr[p], arr[c])) break;
            // parent와 child 교체
            swap(arr, p, c);
            // child로 이동하여 반복 진행
            p = c;
        }
    }


    public void mergeSort(Comparable[] arr) {
        // 복사를 위한 array 생성
        Comparable[] copyArr = new Comparable[arr.length];
        mergeSort(arr, copyArr, 0, arr.length-1);
    }

    private void mergeSort(Comparable[] arr, Comparable[] copyArr, int low, int high) {
        // low가 high 보다 커지거나 같아지면 종료
        if (high <= low) return;
        // mid 계산
        int mid = low + ((high-low) / 2);
        // 왼쪽 절반 sort
        mergeSort(arr, copyArr, low, mid);
        // 오른쪽 절반 sort
        mergeSort(arr, copyArr, mid+1, high);
        // 정렬된 왼쪽, 오른쪽을 merge
        merge(arr, copyArr, low, mid, high);
    }

    private void merge(Comparable[] arr, Comparable[] copyArr, int low, int mid, int high) {
        // 왼쪽 array의 index를 가리키는 i
        int i = low;
        // 오른쪽 array의 index를 가리키는 j
        int j = mid + 1;
        // 전체 범위를 순회
        for (int k = low; k <= high; ++k) {
            // 왼쪽 array를 모두 순회한 경우
            if (i > mid) {
                // 오른쪽 array의 원소를 추가
                copyArr[k] = arr[j++];
            // 오른쪽 array를 모두 순회한 경우
            } else if (j > high) {
                // 왼쪽 array의 원소를 추가
                copyArr[k] = arr[i++];
            // 왼쪽 array의 원소가 오른쪽 array의 원소보다 작을 경우
            } else if (isLess(arr[i], arr[j])) {
                // 왼쪽 array의 원소를 추가
                copyArr[k] = arr[i++];
            } else {
                // 오른쪽 array의 원소를 추가
                copyArr[k] = arr[j++];
            }
        }
        for (int k = low; k <= high; ++k) {
            arr[k] = copyArr[k];
        }
    }

    // key 비교 함수
    private boolean isLess(Comparable i, Comparable j) {
        return i.compareTo(j) < 0;
    }

    // value 교환 함수
    private void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
