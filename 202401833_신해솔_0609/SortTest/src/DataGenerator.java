public class DataGenerator {
    // data를 담을 array
    private int[] _dataArray;
    private int _dataSize;
    SortTest sortTest = new SortTest();

    public DataGenerator() {
        this._dataArray = null;
        this._dataSize = 0;
    }

    public void generateSequentialData(int size) {
        generateRandomData(size);
        Integer[] newArr = convertToIntegerArray(_dataArray);
        sortTest.InsertionSort(newArr);
        _dataArray = convertToIntArray(newArr);
    }

    public void generateReverseData(int size) {
        generateSequentialData(size);
        _dataArray = reverseArray(_dataArray);
    }

    public void generateRandomData(int size) {
        // 최대 생성될 수 있는 정수 값
        final int maxNum = 10001;
        // size 크기를 갖는 array 초기화
        int[] newArr = new int[size];
        // array를 순회하며 random한 값 할당
        for (int i = 0; i < size; ++i) {
            newArr[i] = (int) (Math.random() * maxNum);
        }
        // _dataArray에 저장
        _dataArray = newArr;
    }

    public int[] getData(int size) {
        // 복사를 위한 array 초기화
        int[] copyArr = new int[size];
        // 값 복사;
        for (int i = 0; i < size; ++i) {
            copyArr[i] = _dataArray[i];
        }
        return copyArr;
    }

    private Integer[] convertToIntegerArray(int[] arr) {
        Integer[] result = new Integer[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            result[i] = arr[i];
        }
        return result;
    }

    private int[] convertToIntArray(Integer[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            result[i] = arr[i];
        }
        return result;
    }

    private int[] reverseArray(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; ++i)  {
            newArr[i] = arr[arr.length-i-1];
        }
        return newArr;
    }
}
