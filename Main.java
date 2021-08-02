/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        new Threads(6).calculate();
        new Threads(16).calculate();
        new Threads(2).calculate();
    }
}