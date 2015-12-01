/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package updateapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Thomas Otero H3R3T1C
 */
public class UpdateInfo extends JFrame{

    private JEditorPane infoPane;
    private JScrollPane scp;
    private JButton ok;
    private JButton start;
    private JButton cancel;
    private JPanel pan1;
    private JPanel pan2;

    public UpdateInfo() {
        initComponents();
//        infoPane.setText(info);
    }

    private void initComponents() {

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle("New Update Found v2.0");
        pan1 = new JPanel();
        pan1.setLayout(new BorderLayout());

        pan2 = new JPanel();
        pan2.setLayout(new FlowLayout());

        infoPane = new JEditorPane();
        infoPane.setContentType("text/html");

        scp = new JScrollPane();
        scp.setViewportView(infoPane);

        start = new JButton("Start Server");
        start.addActionListener( new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                         	 
	           infoPane.setText("Tomcat Server starting...");
            }
        });
        
        ok = new JButton("Update");
        ok.addActionListener( new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                         	 
	            try {
					if (Integer.parseInt(Updater.getLatestVersion()) > 0) {						
					    update();
					}else{
						
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
					infoPane.setText("Version Number error.");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					infoPane.setText("Connection error.");
				}
            }
        });

        cancel = new JButton("Cancel");
        cancel.addActionListener( new ActionListener(){

            public void actionPerformed(ActionEvent e) {
                UpdateInfo.this.dispose();
            }
        });
        
        
        pan2.add(start);
        pan2.add(ok);
        pan2.add(cancel);
        pan1.add(pan2, BorderLayout.SOUTH);
        pan1.add(scp, BorderLayout.CENTER);
        this.add(pan1);
        pack();
        show();
        this.setSize(300, 200);
    }
    private void update()
    {
        String[] run = {"java","-jar","update.jar"};
        try {
            Runtime.getRuntime().exec(run);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

}
