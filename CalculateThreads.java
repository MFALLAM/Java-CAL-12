public class CalculateThreads implements Runnable {

    private final float[] arr;
    private final int startThread;

    public CalculateThreads(float[] arr, int startThread) {
        this.arr = arr;
        this.startThread = startThread;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + (i + startThread) / 5.0) * Math.cos(0.2f + (i + startThread) / 5.0) * Math.cos(0.4f + (i + startThread) / 2.0));
        }
    }

}