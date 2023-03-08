import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

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

    public static CPU start(int numberOfProcesses, boolean myArchitecture){
        ArrayList<MyProcess> generatedProcesses= CPU.generateRandomListOfProcesses(numberOfProcesses);
        CPU ret= new CPU();
        if (myArchitecture){
            HashSet<MyProcess> tmp= new HashSet<>();
            tmp.add(new SysProcess());
            ret.queues.add(new MyQueue("Sys queue",0,new ArrayList<MyProcess>(),tmp));
            tmp.removeAll(tmp);
            tmp.add(new RTProcess());
            ret.queues.add(new MyQueue("RT queue", 0,new ArrayList<MyProcess>(),tmp));
            tmp.removeAll(tmp);
            tmp.add(new MyProcess());
            ret.queues.add(new MyQueue("W queue",0,new ArrayList<MyProcess>(),tmp));
        }
        else{ret.queues.add(new MyQueue("Simple queue"));}
        ret.assignProcesses(generatedProcesses);

        return ret;
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
        for(MyQueue q: queues){
            for(MyProcess p: q.processes){
                if(p.getHasEnded()){
                    finishedProc.add(p);
                    q.removeProcess(p);
                }
            }
        }
    }

    private static MyProcess generateRandomProcess(){
        MyProcess ret= new MyProcess();
        Random rand= new Random();
        int n= rand.nextInt();

        if(n%3==0) ret=MyProcess.generateRandomMyProcess();
        else if(n%3==1) ret=SysProcess.generateRandomSysProcess();
        else ret=RTProcess.generateRandomRTProcess(50);  // MAX RANDOM RT WAIT TIME CHANGE

        return ret;
    }

    private static ArrayList<MyProcess> generateRandomListOfProcesses(int numberOfProcesses){
        ArrayList<MyProcess> processes=new ArrayList<>();
        for(int i=0; i<numberOfProcesses; i++) processes.add(generateRandomProcess());
        return processes;
    }

    private int countActiveProcesses(){
        int ret=0;
        for(MyQueue q: queues){
            ret=ret+q.processes.size();
        }
        return ret;
    }
    public void printStats(){
        int actP=countActiveProcesses();
        int finP= finishedProc.size();
        int allP=actP+finP;
        System.out.println("Number of processes: "+allP);
        System.out.println("Active processes: "+ actP);
        System.out.println("By queues");

        MyQueue tmp;
        for(int i=0; i<queues.size(); i++){
            tmp=queues.get(i);
            System.out.println(tmp.name+": "+tmp.processes.size());
        }
        System.out.println();
        System.out.println("Finished processes: "+ finP);
    }
}
