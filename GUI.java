
/****************************************************************/ 
/*                      GUI                             */ 
/*                                                              */ 
/****************************************************************/ 

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.util.*;
import java.io.*;
import javax.imageio.*;
import  sun.audio.*; 
/** 
 * Summary description for GUI 
 * 
 */ 
public class GUI extends JFrame 
{ 
    // Variables declaration 
    private JLabel jLabel1; 
    private JButton jButton1; 
    private JButton jButton3; 
    private JButton jButton4;
    private JButton back;
    private JPanel contentPane;
    private JPanel jPanel1;
    public static JTable Highscore;
    private boolean lol = true;
    public static JTextField Name;
    Audio audio = new Audio();
    // End of variables declaration 

    public GUI() 
    { 
        super(); 
        initializeComponent();   
        this.setVisible(true); 
        setResizable(false);

    }

    public void initializeComponent() 
    { 
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception e){}
        ImageIcon Background = new ImageIcon("images/menubackground.png");
        ImageIcon PLAY = new ImageIcon("images/Button1lol.png");
        ImageIcon HIGH = new ImageIcon("images/Button4.png");
        ImageIcon EXIT = new ImageIcon("images/Button3.png");
        ImageIcon BACK = new ImageIcon("images/BackB.png");
        jLabel1 = new JLabel(Background); 
        jButton1 = new JButton(PLAY);
        jButton1.setBorder(BorderFactory.createEmptyBorder());
        jButton1.setContentAreaFilled(false);
        jButton3 = new JButton(HIGH); 
        jButton3.setBorder(BorderFactory.createEmptyBorder());
        jButton3.setContentAreaFilled(false);
        jButton4 = new JButton(EXIT); 
        jButton4.setBorder(BorderFactory.createEmptyBorder());
        jButton4.setContentAreaFilled(false);
        contentPane = (JPanel)this.getContentPane();
        Highscore = new JTable(11,2);
        back = new JButton(BACK);
        back.setBorder(BorderFactory.createEmptyBorder());
        back.setContentAreaFilled(false);

        // 
        // jLabel1 
        // 
        jLabel1.setText(""); 
        // 
        // jButton1 
        // 
        jButton1.setText("Play"); 
        jButton1.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) 
                { 
                    audio.ButtonNoise();
                    jButton1_actionPerformed(e); 
                } 

            }); 

        // 
        // jButton3 
        // 
        jButton3.setText("High Score"); 
        jButton3.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) 
                { 
                    audio.ButtonNoise();
                    try{
                        jButton3_actionPerformed(e); 
                    }catch(Exception s){}
                } 

            }); 
        // 
        // jButton4 
        // 
        jButton4.setText("Exit"); 
        jButton4.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) 
                { 
                    audio.ButtonNoise();
                    jButton4_actionPerformed(e); 
                } 

            }); 
        // 
        // contentPane 
        //
        int startPosY = 237;
        int startPosX = 333;
        contentPane.setLayout(null);
        addComponent(contentPane, jLabel1, 0, 0, 798, 600); 
        addComponent(jLabel1, jButton1, startPosX, startPosY , 163, 50); 
        addComponent(jLabel1, jButton3, startPosX, startPosY + 60, 163, 50); 
        addComponent(jLabel1, jButton4, startPosX, startPosY + 120, 163, 50);

        //Image Button1 = getToolkit().createImage("Button1.png");
        //ImageIcon myIcon = new ImageIcon(Button1);
        //jButton1.setIcon(myIcon);
        // 
        // GUI 
        //
        this.setTitle("SNAKE - Charga and Aaron"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setSize(new Dimension(798, 600)); 
    }  

    private void addComponent(Container container,Component c,int x,int y,int width,int height) 
    { 
        c.setBounds(x,y,width,height); 
        container.add(c); 
    } 

    private void jButton1_actionPerformed(ActionEvent e) 
    {
        contentPane.removeAll();
        contentPane.revalidate();
        repaint();
        JButton Start = new JButton();
        Start.setText("Go");
        JLabel NameLabel = new JLabel();
        NameLabel.setText("Name: ");
        Name = new JTextField();
        addComponent(contentPane, back,17,32,80,50);
        back.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) 
                {
                    audio.ButtonNoise();
                    contentPane.removeAll();
                    contentPane.revalidate();
                    repaint();
                    initializeComponent();
                } 
            });
        addComponent(contentPane, NameLabel, 295,190,100,28);
        addComponent(contentPane, Name, 342,190,100,28);
        addComponent(contentPane, Start, 445,190,60,28);
        ImageIcon Background = new ImageIcon("images/Rules.png");
        JLabel backgroundPanel = new JLabel(Background);
        addComponent(contentPane, backgroundPanel, 0,0,798, 600);

        Start.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) 
                {
                    if(Name.getText().equals(""))
                    {
                    }
                    else
                    {
                        contentPane.removeAll();
                        contentPane.validate();
                        repaint();
                        jPanel1 = new JPanel();
                        jPanel1 = new Board();
                        Board.score=0;
                        jPanel1.setLayout(new BorderLayout());
                        addComponent(contentPane, jPanel1, 6,5,780,500);
                        jPanel1.setFocusable(true);
                        jPanel1.requestFocusInWindow();
                        back.setText("Return");
                        addComponent(contentPane, back,6,523,80,50);
                        back.addActionListener(new ActionListener() { 
                                public void actionPerformed(ActionEvent e) 
                                {
                                    audio.ButtonNoise();
                                    contentPane.removeAll();
                                    contentPane.revalidate();
                                    repaint();
                                    initializeComponent();
                                } 
                            });
                    }
                } 
            }); 

    } 

    private void jButton3_actionPerformed(ActionEvent e)throws IOException 
    {
        contentPane.removeAll();
        contentPane.revalidate();
        repaint();
        ImageIcon Background = new ImageIcon("images/HighScoreBG.png");
        JLabel backgroundPanel = new JLabel(Background);
        addComponent(contentPane, backgroundPanel, 0,0,798, 600);
        addComponent(backgroundPanel, Highscore, 294,49,200,500);
        back.setText("Back");
        addComponent(backgroundPanel, back,17,32,80,50);
        back.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent e) 
                {
                    audio.ButtonNoise();
                    contentPane.removeAll();
                    contentPane.revalidate();
                    repaint();
                    initializeComponent();
                } 
            });
        Highscore.setRowHeight(45);
        Highscore.setValueAt("Name",0,0);
        Highscore.setValueAt("Score",0,1);
        Reader printScore = new Reader();
        printScore.read();
    } 

    

    private void jButton4_actionPerformed(ActionEvent e) 
    { 
        System.exit(0);
    } 

    public static void main(String args[])
    {
        GUI run = new GUI();
        run.setIconImage(Toolkit.getDefaultToolkit().getImage("snake.png")); 
    }
} 

 