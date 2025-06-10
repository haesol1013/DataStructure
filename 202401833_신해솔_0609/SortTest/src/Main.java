public class Main {
    public static void main(String[] args) {
        // array의 원소 수
        int numElements = 50000;
        // DataGenerator 객체 생성
        DataGenerator dataGenerator = new DataGenerator();
        // SortTest 객체 생성
        SortTest sortTest = new SortTest();
        // test에 사용할 array 선언
        int[] arr;

        // random data
        System.out.println("Random sample data size: " + numElements);
        dataGenerator.generateRandomData(numElements);
        arr = dataGenerator.getData(numElements);
        testSortingAlgorithm("selection sorting time", sortTest::SelectionSort, arr);
        testSortingAlgorithm("insertion sorting time", sortTest::InsertionSort, arr);
        testSortingAlgorithm("heap sorting time", sortTest::HeapSort, arr);
        testSortingAlgorithm("merge sorting time", sortTest::mergeSort, arr);
        System.out.println("-----------------------------------------");

        // sequential data
        System.out.println("Sequential sample data size: " + numElements);
        dataGenerator.generateSequentialData(numElements);
        arr = dataGenerator.getData(numElements);
        testSortingAlgorithm("selection sorting time", sortTest::SelectionSort, arr);
        testSortingAlgorithm("insertion sorting time", sortTest::InsertionSort, arr);
        testSortingAlgorithm("heap sorting time", sortTest::HeapSort, arr);
        testSortingAlgorithm("merge sorting time", sortTest::mergeSort, arr);
        System.out.println("-----------------------------------------");


        // reverse data
        System.out.println("Reverse sample data size: " + numElements);
        dataGenerator.generateReverseData(numElements);
        arr = dataGenerator.getData(numElements);
        testSortingAlgorithm("selection sorting time", sortTest::SelectionSort, arr);
        testSortingAlgorithm("insertion sorting time", sortTest::InsertionSort, arr);
        testSortingAlgorithm("heap sorting time", sortTest::HeapSort, arr);
        testSortingAlgorithm("merge sorting time", sortTest::mergeSort, arr);
        System.out.println("-----------------------------------------");
    }


    private static void testSortingAlgorithm(String algorithmName, SortingAlgorithm algorithm, int[] originalArr)  {
        // int[]를 Integer[]로 변환
        Integer[] comparableArr = convertToIntegerArray(originalArr);
        // sort 수행 및 시간 측정
        long startTime = System.currentTimeMillis();
        algorithm.sort(comparableArr);
        long endTime = System.currentTimeMillis();
        System.out.printf("%s time: %dms\n", algorithmName, (endTime-startTime));
    }

    // int[]를 Integer[]로 변환하는 메서드
    private static Integer[] convertToIntegerArray(int[] arr) {
        Integer[] result = new Integer[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            result[i] = arr[i];
        }
        return result;
    }

    // 정렬 알고리즘을 나타내는 함수형 인터페이스
    @FunctionalInterface
    private interface SortingAlgorithm {
        void sort(Comparable[] arr);
    }

}