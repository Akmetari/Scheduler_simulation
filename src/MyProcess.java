import java.util.Comparator;
import java.util.Random;

public class MyProcess{
    private boolean hasEnded;
    private int waitingTime;
    private String name;
    private int[] processorFazes;
    private MyQueue processQueue;
    private CPU processCPU;


    public MyProcess(CPU cpu){
        hasEnded=false;
        waitingTime=0;
        name="New process";
        processorFazes=new int[]{1};
        processCPU=cpu;
    }

    public MyProcess(boolean end, int waitT, String name, int[] faze, CPU cpu){
        processorFazes=faze;
        this.name=name;
        waitingTime=waitT-processDuration();
        hasEnded=end;
        processCPU=cpu;
        processQueue=new MyQueue("");
    }
    public String toString(){
        String fazesStr="";
        for(int i=0; i<processorFazes.length; i++) fazesStr=fazesStr+processorFazes[i]+",";
        return (""+name+" "+waitingTime+" "+ hasEnded + " "+fazesStr);
    }
//**********************GETTERS & SETTERS*******************************************
    public String getName() {
        return name;
    }
    public int getWaitingTime() {
        return waitingTime;
    }
    public int[] getProcessorFazes() {
        return processorFazes;
    }
    public boolean getHasEnded(){
        return hasEnded;
    }
    public CPU getProcessCPU() {
        return processCPU;
    }
    public MyQueue getProcessQueue() {
        return processQueue;
    }
    public void setHasEnded(boolean hasEnded) {
        this.hasEnded = hasEnded;
    }
    public void setWaitingTime(int waitingTime) {
        if(!this.hasEnded) this.waitingTime = waitingTime;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setProcessorFazes(int[] processorFazes) {
        this.processorFazes = processorFazes;
    }
    public void setProcessQueue(MyQueue processQueue) {
        this.processQueue = processQueue;
    }
    public void setProcessCPU(CPU processCPU) {
        this.processCPU = processCPU;
    }
    //*******************GENERATING PROCESS***********************************
    public static MyProcess generateRandomMyProcess(CPU cpu){
        MyProcess ret= new MyProcess(cpu);
        ret.setName(generateProcessName());
        ret.setHasEnded(false);
        ret.setProcessorFazes(generateProcessorFazes(50));
        ret.setProcessCPU(cpu);
        return ret;
    }
    protected static String generateProcessName(){
        String ret="";
        Random rand=new Random();

        for(int i=0; i<3; i++) ret=ret+(char)('a'+ rand.nextInt(26));
        for(int i=0; i<3; i++) ret=ret+(rand.nextInt(11));
        return ret;
    }
    protected static int[] generateProcessorFazes(int b){ // upper boundary of faze time (+1)
        Random rand=new Random();
        int fazesCount= rand.nextInt(20)+1;
        int[] fazes=new int[fazesCount];

        for(int i=0; i<fazesCount; i++)fazes[i]=rand.nextInt(b)+5;
        return fazes;
    }
//***********************************************************************

    public void finishProcess(){
        hasEnded=true;
    }

    public int processDuration(){
        int ret=0;
        for(int i=0; i<processorFazes.length; i++) ret=ret+processorFazes[i];
        return ret;
    }

    public int assignQuant( int time){
        int closestNonZero=0;
        boolean found=false;
        int[] fazes=getProcessorFazes();

        do{
            if(fazes[closestNonZero]!=0) found=true;
            else closestNonZero++;
        }while (!found && fazes.length>closestNonZero); // searching for next processor faze

        if(!found) setHasEnded(true);
        else {
                fazes[closestNonZero] -= time;
            if (fazes[closestNonZero] < 0) {
                int tmp = fazes[closestNonZero];
                fazes[closestNonZero] = 0;
                return tmp;
            }
        }
        return 0;
    }

}


