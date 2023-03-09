import jdk.nashorn.api.tree.WhileLoopTree;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import javax.swing.*;

public class UI {

    JFrame frame;
    JTextArea console;

    public UI(){
        frame=new JFrame();
        console=new JTextArea();
        frame.getContentPane().add(console);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.setSize(500,500);

        console.setForeground(Color.WHITE);
        console.setBackground(Color.BLACK);
        console.setText("SCHEDULER SIMULATOR \n");
        frame.setVisible(true);
    }

    public void runConsole(int tic){

        KeyListener keyListener= new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.isAltDown()) Simulator.stopBasic();
                else {
                    try {
                        Thread.sleep(tic*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        };

    }

    public void write(String text){
        console.setText(console.getText()+text);
    }

    public void clearConsole(){
        console.setText("");
    }

    public void writeln(String text){
        console.setText(console.getText()+text+"\n");
    }

    private String statToString(String caption, double value){
        String readyStat=caption+":   ";
        for(int i=0; i<value; i++) readyStat+="|";
        return readyStat;
    }

    public void writeCPUStats(CPU cpu){
        HashMap<String, Double> stats= Simulator.cpu.generateStats();
        for(String s: stats.keySet()){
            writeln(statToString(s,stats.get(s)));
        }

    }

}
