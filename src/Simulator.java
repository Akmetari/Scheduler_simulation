import java.util.Random;

public abstract class Simulator {
    static Scheduler scheduler;
    static CPU cpu;
    static int tic;
    static UI ui;
    static boolean pause=false;
    static boolean end=false;


    public static void runSimulation(int simulationType, int numberOfProcesses, int t)throws InterruptedException{  // tic - co ile kwantow czasu ma sie odswieżayc widok
        tic=t;
        ui=new UI();
//TODO ask for parameters
        switch (simulationType){
            case 0:{
                runFIFO(numberOfProcesses);
                break;
            } //FIFO
            case 1:{
                runSJF(false,numberOfProcesses);
                break;
            } //SJF
            case 2:{
                runSJF(true,numberOfProcesses);
                break;
            } //SJF expropriating
            case 3:{
                runRR(numberOfProcesses);
                break;
            } //RR
            case 4:{
                runMix(numberOfProcesses);
                break;
            } //mix
            case 5:{
                runCustom(numberOfProcesses);
                break;
            } //custom
        }
    }

    private static void runSJF(boolean isExpropriating, int numberOfProcesses) throws InterruptedException {
        scheduler=new SJFScheduler(5,50, isExpropriating);
        runBasic(numberOfProcesses,false);
    }
    private static void runFIFO(int numberOfProcesses) throws InterruptedException {
        scheduler=new FIFOScheduler(5,50);
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
        cpu=scheduler.prepareCPU(numberOfProcesses);
        ui.writeCPUStats();
        do{
            if(!pause){
                scheduler.assignProcess();
                scheduler.tideUp();
                if(scheduler.getWholeTime()%1000==0) scheduler.addNewProcesses(rand.nextInt(30));
                ui.writeCPUStats();
                if(end!=true)end= cpu.computingEnded();
            }
        }while(!end );
        stopBasic();
    }

    public static void stopBasic(){
        ui.writeln("End of the simulation");
        end=true;
    }


}




















