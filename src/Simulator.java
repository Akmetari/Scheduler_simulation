import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Simulator {
    static Scheduler scheduler;
    static CPU cpu;
    static int tic;


    public static void runSimulation(int simulationType, int numberOfProcesses, int t)throws InterruptedException{  // tic w sekundach
        tic=t;
        switch (simulationType){
            case 0:{
                runFIFO(numberOfProcesses);
                break;
            }
            case 1:{
                runSJF(false,numberOfProcesses);
                break;
            }
            case 2:{
                runSJF(true,numberOfProcesses);
                break;
            }
            case 3:{
                runRR(numberOfProcesses);
                break;
            }
            case 4:{
                runMix(numberOfProcesses);
                break;
            }
            case 5:{
                runCustom(numberOfProcesses);
                break;
            }
        }
    }

    private static void runSJF(boolean isExclusive, int numberOfProcesses) throws InterruptedException {
        runBasic(numberOfProcesses);
    }
    private static void runFIFO(int numberOfProcesses) throws InterruptedException {
        runBasic(numberOfProcesses);
    }
    private static void runRR(int numberOfProcesses) throws InterruptedException {
        runBasic(numberOfProcesses);
    }
    private static void runMix(int numberOfProcesses) throws InterruptedException {
        runBasic(numberOfProcesses);
    }

    private static void runCustom(int numberOfProcesses) throws InterruptedException {
        runBasic(numberOfProcesses);
    }

    private static void runBasic(int numberOfProcesses) throws InterruptedException {
        boolean end=false;
        cpu=CPU.start(numberOfProcesses, false);



        System.out.println("Starting simulation. To check current status, press any letter.");
        cpu.printStats();
        {

            end= cpu.computingEnded();
        }while(!end);
        stopBasic();
    }

    public static void stopBasic(){
        cpu.printStats();
        System.out.println(" End of the simulation. Press enter to end programme.");
        System.exit(0);
    }


}




















