/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package updateapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.ServletException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.catalina.LifecycleException;

import com.ron.Main;

/**
 *
 * @author Thomas Otero H3R3T1C
 */
public class UpdateInfo extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
        infoPane.setText("Tomcat Server starting...");
    	new Thread(new Runnable() {
		    public void run() {
		        // TODO Auto-generated method stub
		    	try {
					 Main.start();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					infoPane.setText("Tomcat Server start error!!");
				} catch (ServletException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					infoPane.setText("Tomcat Server start error!!");
				} catch (LifecycleException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					infoPane.setText("Tomcat Server start error!!");
				} catch(Exception e){							
					System.out.println("erro!");
				}
		    }
		}).start();
    	
		System.out.println("started");
		infoPane.setText("Tomcat Server started");
    }
    
    private void initComponents() {

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        //this.setDefaultCloseOperation(0);//点击右上角,DO_NOTHING_ON_CLOSE，不执行任何操作。  
        //this.setDefaultCloseOperation(1);//HIDE_ON_CLOSE，只隐藏界面，setVisible(false)  
        //this.setDefaultCloseOperation(2);//DISPOSE_ON_CLOSE,隐藏并释放窗体。当最后一个窗口被释放后，则程序也随之运行结束。  
        //this.setDefaultCloseOperation(3);//EXIT_ON_CLOSE,直接关闭应用程序System.exit(0);  
        
        this.setTitle("New Update Found ");
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
            	new Thread(new Runnable() {
				    public void run() {
				        // TODO Auto-generated method stub
				    	try {
							 Main.start();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
//							e.printStackTrace();
							infoPane.setText("Tomcat Server start error!!");
						} catch (ServletException e) {
							// TODO Auto-generated catch block
//							e.printStackTrace();
							infoPane.setText("Tomcat Server start error!!");
						} catch (LifecycleException e) {
							// TODO Auto-generated catch block
//							e.printStackTrace();
							infoPane.setText("Tomcat Server start error!!");
						} catch(Exception e){							
							System.out.println("erro!");
						}
				    }
				}).start();
            	
				System.out.println("started");
				infoPane.setText("Tomcat Server started");
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
            	
            	new Thread(new Runnable() {
				    public void run() {
				        // TODO Auto-generated method stub
				    	try {
							 Main.stop();
							 System.out.println("stopped");
							 infoPane.setText("Tomcat Server stopped");
							 
						} catch (LifecycleException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				}).start();
            	
                UpdateInfo.this.dispose();
            }
        });
        
        
        pan2.add(start);
        pan2.add(ok);
        pan2.add(cancel);
        pan1.add(pan2, BorderLayout.SOUTH);
        pan1.add(scp, BorderLayout.CENTER);
        this.add(pan1);
        this.pack();
        this.setVisible(true); 
        this.setSize(300, 200);
        

        this.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		new Thread(new Runnable() {
				    public void run() {
				        // TODO Auto-generated method stub
				    	try {
							 Main.stop();
							 System.out.println("stopped");
							 infoPane.setText("Tomcat Server stopped");
							 
						} catch (LifecycleException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				}).start();
        		System.exit(0);
        	}
        });

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
