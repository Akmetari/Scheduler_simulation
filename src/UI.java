import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class UI {
    JFrame frame;
    JTextArea console;
    JButton endButton;
    JButton pauseButton;
    JButton resumeButton;

    public UI(){
        frame=new JFrame();
        console=new JTextArea();

        frame.getContentPane().add(console, BorderLayout.CENTER);
        setButtons();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.BLACK);
        frame.setSize(700,500);

        console.setForeground(Color.WHITE);
        console.setBackground(Color.BLACK);
        console.setText("SCHEDULER SIMULATOR \n");
        frame.setVisible(true);
    }

    private void setButtons(){
        endButton=new JButton("END");
        pauseButton=new JButton("PAUSE");
        resumeButton=new JButton("RESUME");
        JPanel buttons=new JPanel(new GridLayout());
        frame.getContentPane().add(buttons, BorderLayout.SOUTH);
        buttons.add(endButton);
        buttons.add(pauseButton);
        buttons.add(resumeButton);
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Simulator.stopBasic();
            }});
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Simulator.pause=true;
            }
        });
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Simulator.pause=false;
            }
        });
    }

    public void writeln(String text){
        console.setText(console.getText()+text+"\n");
    }


    private String mergeStatsToString(){
       ArrayList<Stat> stats= Simulator.cpu.generateStats();
        String merged="SCHEDULER SIMULATION\n";
        for(int i=0; i<stats.size(); i++){
            merged=merged+(stats.get(i).statToString())+"\n";
        }
        return merged;
    }

    public void writeCPUStats(){
        console.setText(mergeStatsToString());
    }

}
