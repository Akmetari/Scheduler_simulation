import java.util.ArrayList;
import java.util.HashSet;

// the 3 queue scheduler
public class MyScheduler extends Scheduler{
    @Override
    public CPU prepareCPU(int numberOfProcesses) {
        cpu=new CPU();
        HashSet<MyProcess> tmp= new HashSet<>();

        tmp.add(new SysProcess(cpu));
        cpu.queues.add(new MyQueue("Sys queue",0,new ArrayList<MyProcess>(),tmp));
        tmp.removeAll(tmp);
        tmp.add(new RTProcess(cpu));
        cpu.queues.add(new MyQueue("RT queue", 0,new ArrayList<MyProcess>(),tmp));
        tmp.removeAll(tmp);
        tmp.add(new MyProcess(cpu));
        cpu.queues.add(new MyQueue("W queue",0,new ArrayList<MyProcess>(),tmp));

        cpu.assignProcesses(cpu.generateRandomListOfProcesses(numberOfProcesses));
        return cpu;
    }

    @Override
    public MyQueue sortQueue(MyQueue q) {
        return q;
    }
}
