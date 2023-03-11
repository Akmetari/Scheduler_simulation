import java.util.ArrayList;
import java.util.Collections;

public class SJFScheduler extends Scheduler{
    boolean exclusive;
    public SJFScheduler( int q, int oldeningTime, boolean isExclusive){
        cpu= new CPU();
        timeQuant=q;
        this.oldeningTime=oldeningTime;
        averageWaitingTime=0;
        exclusive=isExclusive;
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
