public abstract class Scheduler {
    private int timeQuant;
    private double averageWaitingTime;
    private int oldeningTime;
    private CPU cpu;

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
//****************************************************************************



}
