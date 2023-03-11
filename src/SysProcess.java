import java.util.ArrayList;
import java.util.Random;

public class SysProcess extends MyProcess{ // moze tworzyc inne procesy
    ArrayList<MyProcess> childProcesses;

    public SysProcess(boolean end, int waitT, String name, int[] faze, CPU cpu ){
        super(end, waitT,name,faze, cpu);
        childProcesses=new ArrayList<>();
    }

    public SysProcess(CPU cpu){
        super(cpu);
        childProcesses=new ArrayList<>();
    }
//***********************GETTERS & SETTERS***********************************
    public ArrayList<MyProcess> getChildProcesses() {
        return childProcesses;
    }
    public void setChildProcesses(ArrayList<MyProcess> childProcesses) {
        this.childProcesses = childProcesses;
    }
//****************************************************************************

    private MyProcess createChildProcess(){
        MyProcess child=generateRandomMyProcess(getProcessCPU());
        childProcesses.add(child);
        getProcessCPU().assignProcessToQueue(child);
        return child;
    }

    @Override
    public void finishProcess() {
        super.finishProcess();
        for(MyProcess p: childProcesses) p.finishProcess();
    }

    public static SysProcess generateRandomSysProcess(CPU cpu){
        SysProcess ret= new SysProcess(cpu);
        ret.setName(generateProcessName());
        ret.setHasEnded(false);
        ret.setProcessorFazes(generateProcessorFazes(50));

        return ret;
    }

    @Override
    public int assignQuant( int time){
        Random rand=new Random();
        int closestNonZero=0;
        boolean found=false;
        int[] fazes=getProcessorFazes();

        do{
            if(fazes[closestNonZero]!=0) found=true;
            else closestNonZero++;
        }while (!found && fazes.length>closestNonZero); // searching for next processor faze

        if(!found) setHasEnded(true);
        else {
            if((rand.nextInt()%100==0)){  // creating child processes while running processor faze
                createChildProcess();
                fazes[closestNonZero] -= Math.round(time/2);
                int spareTime=getProcessCPU().queues.get(0).processes.get(0).assignQuant(time);
                getProcessCPU().increaseWaitingTimes(time-spareTime);
            }else fazes[closestNonZero] -= time;

            if (fazes[closestNonZero] < 0) {
                int tmp = fazes[closestNonZero];
                fazes[closestNonZero] = 0;
                return tmp;
            }
        }
        return 0;
    }
}
