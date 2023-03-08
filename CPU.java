import java.util.ArrayList;
import java.util.Random;

public class CPU {
    ArrayList<MyProcess> finishedProc;
    ArrayList<MyQueue> queues;


    public void assignProcessToQueue(MyProcess p){
       for(MyQueue q: queues)
           if(q.canBeAssigned(p)) {
               q.addProcess(p);
           }
       else System.out.println("No matching queues. Process not assigned.");
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
}
