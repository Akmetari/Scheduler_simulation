import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class MyQueue {
    ArrayList<MyProcess> processes;
    HashMap<String,Boolean> supportedProcesses;
    double averageWaitingTime;
    String name;

    public MyQueue(String n){
        name=n;
        averageWaitingTime=0;
        processes=new ArrayList<>();
        supportedProcesses=new HashMap<>();
        supportedProcesses.put("SysProcess", true);
        supportedProcesses.put("RTProcess", true);
        supportedProcesses.put("MyProcess", true);
    }

    public MyQueue(String n, double time, ArrayList<MyProcess> p, HashSet<MyProcess> supportedProcesses){
        name=n;
        averageWaitingTime=time;
        processes=p;
        this.supportedProcesses=new HashMap<>();
        for(MyProcess proc: supportedProcesses) this.supportedProcesses.put(proc.getClass().getName(), true);
    }

    public void addProcess(MyProcess p){
        processes.add(p);
    }

    public void removeProcess(MyProcess p){
        processes.remove(p);
    }

    public boolean canBeAssigned(MyProcess p){
        if (supportedProcesses.containsKey(p.getClass().getName())) return true;
        else return false;
    }


}
