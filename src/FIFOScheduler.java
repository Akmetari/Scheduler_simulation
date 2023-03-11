public class FIFOScheduler extends Scheduler {

    public FIFOScheduler( int q, int oldeningTime){
        cpu= new CPU();
        timeQuant=q;
        this.oldeningTime=oldeningTime;
        averageWaitingTime=0;
    }

    public CPU prepareCPU(int numberOfProcesses){
        cpu.queues.add(new MyQueue("FIFO queue"));
        cpu.assignProcesses(cpu.generateRandomListOfProcesses(numberOfProcesses));
        return cpu;
    }

    @Override
    public MyQueue sortQueue(MyQueue q) {return q; }

}
