import java.util.ArrayList;

public class SysProcess extends MyProcess{ // moze tworzyc inne procesy
    ArrayList<MyProcess> childProcesses;

    public SysProcess(boolean end, int waitT, String name, int[] faze){
        super(end, waitT,name,faze);
        childProcesses=new ArrayList<>();
    }

    public SysProcess(){
        super();
    }
//***********************GETTERS & SETTERS***********************************
    public ArrayList<MyProcess> getChildProcesses() {
        return childProcesses;
    }
    public void setChildProcesses(ArrayList<MyProcess> childProcesses) {
        this.childProcesses = childProcesses;
    }
//****************************************************************************

    private MyProcess createChildProcess(){
        MyProcess child=generateRandomMyProcess();
        childProcesses.add(child);
        getProcessCPU().assignProcessToQueue(child);
        return child;
    }

    @Override
    public void finishProcess() {
        super.finishProcess();
        for(MyProcess p: childProcesses) p.finishProcess();
    }

    public static SysProcess generateRandomSysProcess(){
        SysProcess ret= new SysProcess();
        ret.setName(generateProcessName());
        ret.setHasEnded(false);
        ret.setProcessorFazes(generateProcessorFazes(50));

        return ret;
    }
}
