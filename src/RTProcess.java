import java.util.Random;

public class RTProcess extends MyProcess {
    private int maxWaitingTime;

    public RTProcess(boolean end, int waitT, String name, int[] faze, int maxWaitingTime,CPU cpu){
        super(end, waitT,name,faze, cpu);
        this.maxWaitingTime=maxWaitingTime;
    }
    public RTProcess(CPU cpu){
        super(cpu);
    }
    public String toString(){
        String fazesStr="";
        for(int i = 0; i< getProcessorFazes().length; i++) fazesStr=fazesStr+ getProcessorFazes()[i]+",";
        return (""+ getName() +" "+ getWaitingTime() +" "+ getHasEnded() +" "+ maxWaitingTime+ " "+fazesStr);
    }
//******************GETTERS & SETTERS*****************************
    public int getMaxWaitingTime() {
        return maxWaitingTime;
    }
    public void setMaxWaitingTime(int maxWaitingTime) {
        this.maxWaitingTime = maxWaitingTime;
    }
//***************************************************************
public static RTProcess generateRandomRTProcess(int maxWaitingTimeBoundary, CPU cpu){
    RTProcess ret= new RTProcess(cpu);
    Random rand= new Random();

    ret.setName(generateProcessName());
    ret.setHasEnded(false);
    ret.setProcessorFazes(generateProcessorFazes(50));
    ret.setMaxWaitingTime(rand.nextInt(maxWaitingTimeBoundary));
    return ret;
}

//TODO checking wiating time. If to big, automaticly jumping to the begining of the queue ??
}
