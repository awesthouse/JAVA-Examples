import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import static javax.swing.JOptionPane.*;
import java.io.*;
import java.util.Scanner;

public class Notepad extends JFrame
{
    private JTextArea txtArea;
    private JMenuItem btnExit, btnClose, btnOpen, btnSave, fontCourierNew, fontHelvetica;
    private JMenuBar menuBar;
    private JMenu file, format, font;
    
    public Notepad()
    {
        setTitle("Notepad by Westhouse");
        txtArea = new JTextArea();
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setBackground(Color.WHITE);
        txtArea.setForeground(Color.BLACK);
        
        txtArea.setBorder(BorderFactory.createCompoundBorder(txtArea.getBorder(), 
        BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        menuBar = new JMenuBar();
        
        file = new JMenu("File"); 
        format = new JMenu("Format");
        btnExit = new JMenuItem("Exit");
        btnClose = new JMenuItem("Close");
        btnOpen = new JMenuItem("Open");
        btnSave = new JMenuItem("Save");
        font = new JMenu("Font");
        fontCourierNew = new JMenuItem("Courier New");
        fontHelvetica = new JMenuItem("Helvetica");
        
        menuBar.add(file);
        menuBar.add(format);
        file.add(btnOpen);
        file.add(btnSave);
        file.add(btnExit);
        format.add(font);
        font.add(fontCourierNew);
        font.add(fontHelvetica);
        
        Font monospace = new Font("Courier New", Font.PLAIN, 14);
        Font helvetica = new Font("Helvetica", Font.PLAIN, 14);
        
        btnOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });
        
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        fontCourierNew.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 txtArea.setFont(monospace);
             }
        });
        fontHelvetica.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 txtArea.setFont(helvetica);
             }
        });
        
        JScrollPane jsc = new JScrollPane(txtArea);
        add(jsc, BorderLayout.CENTER);
        
        
        
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
    }
    
    
    public void load() {
        txtArea.setText("");
        
        try {
            String fileName = showInputDialog(this, "Filename:");
            Scanner fileIn = new Scanner(new File(fileName));
            while (fileIn.hasNext()) {
                String data = fileIn.nextLine();
                txtArea.append(data + "\n");
            }
            fileIn.close();
        } catch (IOException ioex) {
            JOptionPane.showMessageDialog(this, "IO-feil: " + ioex.getMessage());
        }
    }
    
    public void save() {
        try{
            String fileName = showInputDialog(this, "Filename");
            PrintWriter fileOut = new PrintWriter(new File(fileName));
            fileOut.println(txtArea.getText());
            fileOut.close();
        } catch (IOException ioex) {
            JOptionPane.showMessageDialog(Notepad.this, "IO-feil: " + ioex.getMessage());
        }
    }
}
