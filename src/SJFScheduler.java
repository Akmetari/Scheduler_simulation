import java.util.Collections;

public class SJFScheduler extends Scheduler{
    boolean expropriating;
    public SJFScheduler( int q, int oldeningTime, boolean isExpropriating){
        cpu= new CPU();
        timeQuant=q;
        this.oldeningTime=oldeningTime;
        averageWaitingTime=0;
        expropriating=isExpropriating;
    }


    @Override
    public CPU prepareCPU(int numberOfProcesses) {
        cpu.queues.add(new MyQueue("SJF queue"));
        cpu.assignProcesses(cpu.generateRandomListOfProcesses(numberOfProcesses));
        cpu.queues.set(0, sortQueue(cpu.queues.get(0)));
        return cpu;
    }

    @Override
    public MyQueue sortQueue(MyQueue q) {
        Collections.sort(q.processes,new MyProcessComparator());
        return q;
    }


}
