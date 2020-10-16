
import javax.swing.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class Graphical-terminal extends JFrame implements ActionListener{
JFrame frame =new JFrame("GUI-Terminal");
JPanel panel1,panel2,panel3;  
JTextField field,pfield;
JButton button1,button2,Ubutton;
JLabel INlabel,Ulabel,Alabel;  
JTabbedPane tab;
JLabel Iarea,Uarea;
String filepath;
String line,line1;
Container cp = frame.getContentPane();

Terminal(){
	
	tab=new JTabbedPane(JTabbedPane.TOP);	
	 panel1=new JPanel();
	 panel2=new JPanel();
	 panel3=new JPanel();
		field=new JTextField();
		pfield=new JTextField();

	button1=new JButton();
	button2=new JButton();
	Ubutton=new JButton("Uninstall");
	Ubutton.addActionListener(this);
	Ulabel=new JLabel("Enter Software Name");
	Alabel=new JLabel();
	Iarea=new JLabel();
	Uarea=new JLabel();
	Iarea.setText("line");
	Uarea.setText("line1");
	 JScrollPane scrollPane = new JScrollPane(Iarea);
	 JScrollPane scrollPane1 = new JScrollPane(Uarea);
	 
	Alabel.setText("<HTML>HELLO FRIENDS THIS IS SIMPLE AND EASY TO USE <BR> LINUX GRAPHUCAL TERMNAL USING THIS TOOL YOU CAN EASLY INSTALL <BR>"
			+ "INSTALL ANY SOFTWARE with .deb file<HTML>");
	button1.setText("Select file");
	button1.addActionListener(this);
	button2.setText("Install");
	button2.addActionListener(this);
	field.setToolTipText("Enter file name OR Loction Path");
	pfield.setToolTipText("Enter Software name");
	button1.setBounds(360,50,120,25);
	button2.setBounds(100,100,100,25);
	field.setBounds(10,50,350,25);
	Ubutton.setBounds(70,100,100,25);
	pfield.setBounds(10,50,250,25);
	Ulabel.setBounds(10, 30,250, 25);
	Alabel.setBounds(50,10,200,100);
	scrollPane.setBounds(10, 150, 500, 80);
	scrollPane1.setBounds(10, 150, 500, 80);

	 panel1.add(scrollPane, BorderLayout.CENTER);
	 panel2.add(scrollPane1, BorderLayout.CENTER);

	panel1.add(field);
	panel1.add(button1);
	panel1.add(button2);
	panel2.add(Ubutton);
	panel2.add(Ulabel);
	panel2.add(pfield);
	panel3.add(Alabel);

	tab.addTab("Install", panel1);
	tab.addTab("Uninstall",panel2);
    tab.addTab("About", panel3);
	frame.add(tab);
	frame.setSize(550,400);
	panel1.setLayout(null);
	panel2.setLayout(null);
	panel3.setLayout(null);

	frame.setVisible(true);
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.getContentPane();

 
}
	@Override
	public void actionPerformed(ActionEvent evt) {
	if(evt.getSource()==button1) {
	  
		    JFileChooser fc=new JFileChooser();    
		    int i=fc.showOpenDialog(this);    
		    if(i==JFileChooser.APPROVE_OPTION){
		        File f=fc.getSelectedFile(); 
		       
		         filepath=f.getPath();
		        
		    field.setText(filepath); 
		      } }
	else if(evt.getSource()==button2) {
		try {
		      filepath=field.getText();  
		File file=new File(filepath);
		System.out.print(filepath);
		if(file.exists()) {
              Process process = Runtime.getRuntime().exec("dpkg -i "+filepath); 
			BufferedReader reader = new BufferedReader(new InputStreamReader (process.getInputStream()));
			while((line = reader.readLine()) != null) {
			System.out.println(line);
				Iarea.setText("<HTML>"+line+"<HTML>");
			}
			int exitVal = process.waitFor();
			if (exitVal == 0) {
				
			
				Iarea.setText("Success");
				}
	           } 
		else if(filepath.length()!=0) {
				  Process process1 = Runtime.getRuntime().exec("apt install "+filepath+" -y"); 
					BufferedReader reader1= new BufferedReader(new InputStreamReader (process1.getInputStream()));
				
				
					while((line1 = reader1.readLine()) != null) {
					System.out.println(line1);
						Iarea.setText("<HTML>"+line1+"<HTML>");
					}
					int exitVal1 = process1.waitFor();
					if (exitVal1 == 0) {
					
					
		
						Iarea.setText("Success");
						}
					else{
						
						Iarea.setText(filepath+" Software not found");
						}}
					}
		catch (IOException e) {
		JOptionPane.showMessageDialog(button1, filepath+"Wrong input");	
		} 
             catch (InterruptedException e) {
			System.out.println("Somthing Wrong");
		}}
	else if(evt.getSource()==Ubutton) {
		try {
		String f=pfield.getText();
		if(f.length()!=0) {
			   Process process = Runtime.getRuntime().exec("apt remove "+f+" -y"); 
				BufferedReader reader = new BufferedReader(new InputStreamReader (process.getInputStream()));
				String line;
				while((line = reader.readLine()) != null) {
				System.out.println(line);
				  Uarea.setText("<HTML>"+line+"<HTML>");
				}
				int exitVal = process.waitFor();
				if (exitVal == 0) {
				   
					Uarea.setText("Success");
					}
		           
		else {
			System.out.print("Sofware not found");
			Uarea.setText(f+"Software not found");
		}
		}
		}
		catch(IOException e1) {
			System.out.println("Somthing wrong");
		
		}
		catch(InterruptedException e) {
			System.out.println("Somthing wrong");
		}
	}
	}}

public class tgterminal {
	public static void main(String a[]) {
	Terminal T=new Terminal();
	}}
