public class Main {
    public static void main(String[] args) throws InterruptedException {
        UI con=new UI();
        con.runConsole(1);
        Simulator.runSimulation(0,10 , 1); // tic w sekundach
    }
}