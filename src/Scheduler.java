import java.util.ArrayList;

public abstract class Scheduler {
    protected int timeQuant;
    protected int wholeTime;
    protected double averageWaitingTime;
    protected int oldeningTime;
    protected CPU cpu;

//******************GETTERS & SETTERS*************************************
    public CPU getCpu() {
        return cpu;
    }
    public double getAverageWaitingTime() {
        return averageWaitingTime;
    }
    public int getOldeningTime() {
        return oldeningTime;
    }

    public int getWholeTime() {
        return wholeTime;
    }

    public int getTimeQuant() {
        return timeQuant;
    }
    public void setAverageWaitingTime(double averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }
    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }
    public void setOldeningTime(int oldeningTime) {
        this.oldeningTime = oldeningTime;
    }
    public void setTimeQuant(int timeQuant) {
        this.timeQuant = timeQuant;
    }

    public void setWholeTime(int wholeTime) {
        this.wholeTime = wholeTime;
    }
    //****************************************************************************

    public abstract CPU prepareCPU(int numberOfProcesses);
    protected void addNewProcesses(int n) {
        int active=cpu.countActiveProcesses();
        if(active/(cpu.finishedProc.size()+active)<0.3) cpu.assignProcesses(CPU.generateRandomListOfProcesses(n));
    }
    public abstract void sortQueue();

    public void assignProcess(){
        ArrayList<MyProcess> q=cpu.queues.get(0).processes;
        if(q.size()>0){
            int spareTime= q.get(0).assignQuant(timeQuant);
            cpu.increaseWaitingTimes(timeQuant-spareTime);
            wholeTime+=timeQuant-spareTime;
        }
    }





}
