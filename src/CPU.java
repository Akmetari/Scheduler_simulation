import java.util.*;

public class CPU {
    ArrayList<MyProcess> finishedProc;
    ArrayList<MyQueue> queues;


    public CPU(){
        finishedProc=new ArrayList<>();
        queues=new ArrayList<>();
    }

    public CPU(ArrayList<MyProcess> allProc){
        finishedProc=new ArrayList<>();
        queues=new ArrayList<>();
        assignProcesses(allProc);
    }

    public void assignProcessToQueue(MyProcess p){
       for(MyQueue q: queues)
           if (q.canBeAssigned(p)) {
               q.addProcess(p);
           }
    }

    public void assignProcesses(ArrayList<MyProcess> proc){
        for(MyProcess p: proc) assignProcessToQueue(p);
    }
    public void cleanProcesses(){
        MyProcess p;
        for(MyQueue q: queues){
            for(int i=0; i<q.processes.size(); i++){
                p=q.processes.get(i);
                if(p.getHasEnded()){
                    finishedProc.add(p);
                    q.removeProcess(p);
                }
            }
        }
    }

    public void increaseWaitingTimes(int t){
        for(MyQueue q: queues){
            for(MyProcess p: q.processes) p.setWaitingTime(p.getWaitingTime()+t);
        }
    }

    private MyProcess generateRandomProcess(){
        MyProcess ret;
        Random rand= new Random();
        int n= rand.nextInt();

        if(n%3==0) ret=MyProcess.generateRandomMyProcess(this);
        else if(n%3==1) ret=SysProcess.generateRandomSysProcess(this);
        else ret=RTProcess.generateRandomRTProcess(50, this);  // MAX RANDOM RT WAIT TIME CHANGE

        return ret;
    }

    public ArrayList<MyProcess> generateRandomListOfProcesses(int numberOfProcesses){
        ArrayList<MyProcess> processes=new ArrayList<>();
        for(int i=0; i<numberOfProcesses; i++) processes.add(generateRandomProcess());
        return processes;
    }

    public int countActiveProcesses(){
        int ret=0;
        for(MyQueue q: queues){
            ret=ret+q.processes.size();
        }
        return ret;
    }

    public ArrayList<Stat> generateStats(){
        ArrayList<Stat> stats=new ArrayList<>();
        int actP=countActiveProcesses();
        int finP= finishedProc.size();
        int allP=actP+finP;
        stats.add(new Stat(  allP,"All processes:"));
        stats.add(new Stat(  finP,"Finished processes:"));
        stats.add(new Stat(  actP,"Active processes:"));
        stats.add(new Stat(  0.0,""));

        MyQueue tmp;
        for(int i=0; i<queues.size(); i++){
            tmp=queues.get(i);
            stats.add(new Stat(tmp.processes.size(), tmp.name));
        }
        return stats;
    }

    public boolean computingEnded(){
        return (countActiveProcesses()==0);
    }
}
