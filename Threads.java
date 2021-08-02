import java.util.Arrays;
import java.util.Date;

public class Threads {
    private static final int SIZE = 10000000;
    private final int multiple;
    private final float[] arr = new float[SIZE];

    public Threads(int multiple) {
        this.multiple = multiple;
        Arrays.fill(this.arr, 1);
    }

    public void calculate() {
        Date start = new Date();

        int computedRate = SIZE / this.multiple;
        int overcomputedRate = SIZE % this.multiple;

        float[][] sizeMutlArray = new float[multiple][];
        Thread[] calculatingThreads = new Thread[multiple];

        for (int i = 0; i < multiple - 1; i++) {
            sizeMutlArray[i] = new float[computedRate];
            System.arraycopy(arr, i * computedRate, sizeMutlArray[i], 0, computedRate);
            calculatingThreads[i] = new Thread(new CalculateThreads(sizeMutlArray[i], i * computedRate));
            calculatingThreads[i].start();
        }

        sizeMutlArray[multiple - 1] = new float[computedRate + overcomputedRate];

        System.arraycopy(arr, (multiple - 1) * computedRate, sizeMutlArray[multiple - 1], 0, computedRate + overcomputedRate);

        calculatingThreads[multiple - 1] = new Thread(new CalculateThreads(sizeMutlArray[multiple - 1], (multiple - 1) * computedRate));

        calculatingThreads[multiple - 1].start();

        for (Thread computedThread : calculatingThreads) {

            try {
                computedThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < multiple; i++) {
                System.arraycopy(sizeMutlArray[i], 0, arr, i * computedRate, sizeMutlArray[i].length);
            }

            Date endsAt = new Date();
            long finishedAt = endsAt.getTime() - start.getTime();

            System.out.println("Result: " + arr[10000] + ", time: " + finishedAt + ", computed threads : " + multiple);
        }

    }
}