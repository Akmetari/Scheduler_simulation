import java.util.Random;

public abstract class Simulator {
    static Scheduler scheduler;
    static CPU cpu;
    static int tic;
    static UI ui;
    static boolean pause=false;


    public static void runSimulation(int simulationType, int numberOfProcesses, int t)throws InterruptedException{  // tic - co ile kwantow czasu ma sie odswieżayc widok
        tic=t;
        ui=new UI();
//TODO ask for parameters
        switch (simulationType){
            case 0:{
                scheduler=new FIFOScheduler(5,50);
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
        runBasic(numberOfProcesses,false);
    }
    private static void runFIFO(int numberOfProcesses) throws InterruptedException {
        runBasic(numberOfProcesses,false);
    }
    private static void runRR(int numberOfProcesses) throws InterruptedException {

    }
    private static void runMix(int numberOfProcesses) throws InterruptedException {

    }

    private static void runCustom(int numberOfProcesses) throws InterruptedException {

    }

    private static void runBasic(int numberOfProcesses, boolean arch) throws InterruptedException {
        Random rand=new Random();
        boolean end=false;
        cpu=scheduler.prepareCPU(numberOfProcesses);
        ui.writeCPUStats();
        do{
            if(!pause){
                scheduler.assignProcess();
                cpu.cleanProcesses();
                if(scheduler.getWholeTime()%500==0) scheduler.addNewProcesses(rand.nextInt(30));
                ui.writeCPUStats();
                end= cpu.computingEnded();
            }
        }while(!end );
        stopBasic();
    }

    public static void stopBasic(){
        ui.writeln("End of the simulation");
    }


}




















