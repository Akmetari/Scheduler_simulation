public class Stat {
    double value;
    String description;

    public Stat(double v, String s){
        value=v;
        description=s;
    }

    public String statToString(){
        String readyStat=description+ "    ";
        for(int i=0; i<value; i++) readyStat+="|";
        return readyStat;
    }

}
