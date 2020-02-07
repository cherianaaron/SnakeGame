import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 
import java.awt.Window.*;
import java.io.*;
import  sun.audio.*; 
public class Board extends JPanel implements ActionListener {

    private int w = 770;
    private  int h = 490;
    private  int d = 10;
    private  int dAll = 900;
    private  int RAND_POS = 29;
    private int DELAY = 60;

    private int x[] = new int[dAll];
    private int y[] = new int[dAll];

    private int dots;
    private int dot_x;
    private int dot_y;

    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    public boolean inGame = true;

    public static int score=0;

    public boolean blaze = false;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    private Image blazeI;

    Audio audio = new Audio();

    int counter = 0;

    public Board() {        
        addKeyListener(new TAdapter());

        setBackground(Color.white);

        ImageIcon iid = new ImageIcon(this.getClass().getResource("images/dot.gif"));
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon(this.getClass().getResource("images/dot.gif"));
        apple = iia.getImage();

        ImageIcon iib = new ImageIcon(this.getClass().getResource("images/flame.gif"));
        blazeI = iib.getImage();

        setFocusable(true);
        initGame();  
    }

    public void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z*10;
            y[z] = 50;
        }

        locateDot();

        timer = new Timer(DELAY, this);
        timer.start();

    }

    public void paint(Graphics g) {
        super.paint(g);
        if (inGame) {
            score(g);
            g.drawImage(apple, dot_x, dot_y, this);
            for (int z = 0; z < dots; z++) {
                if (z == 0)
                    g.drawImage(head, x[z], y[z], this);
                else g.drawImage(ball, x[z], y[z], this);
            }
            if(blaze)
            {
                BLAZE(g);
                for(int x =0 ; x<690 ; x+=70)
                {
                    g.drawImage(blazeI, x, 430, this);
                }
                for(int x =0 ; x<690 ; x+=35)
                {
                    g.drawImage(blazeI, x, 430, this);
                }
            }
            g.dispose();
        } else {
            gameOver(g);
            g.dispose();
        }
    }

    public void gameOver(Graphics g) {
        String msg = "Game Over, Score: " + score;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(msg, (w - metr.stringWidth(msg)) / 2,
            h / 2);
    }

    public void score(Graphics g)
    {
        String scoreDisplay = "Score: " + score;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(scoreDisplay, (3),11);
    }

    public void BLAZE (Graphics g)
    {
        String BLAZE = "BLAZE";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(BLAZE, 725,11);
    }

    public void checkDot() {
        if ((x[0] == dot_x) && (y[0] == dot_y)) {
            audio.SnakeNoise();
            dots+=1;
            locateDot();
            score+=10;
            DELAY-=1;
            int r = (int)(1+Math.random()*4);
            int r1 = (int)(1+Math.random()*4);
            if(r1 == r)
            {
                blaze = true;
                timer.setDelay((int)(10+Math.random()*13));
                ImageIcon iid = new ImageIcon(this.getClass().getResource("images/orange.png"));
                ball = iid.getImage();
                ImageIcon iia = new ImageIcon(this.getClass().getResource("images/boost.png"));
                apple = iia.getImage();
                score+=20;
            }
            else
            {
                blaze = false;
                timer.setDelay(DELAY);
                ImageIcon iid = new ImageIcon(this.getClass().getResource("images/dot.gif"));
                ball = iid.getImage();
                ImageIcon iia = new ImageIcon(this.getClass().getResource("images/dot.gif"));
                apple = iia.getImage();
            }
        }
    }

    public void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (left) {
            x[0] -= d;
        }

        if (right) {
            x[0] += d;
        }

        if (up) {
            y[0] -= d;
        }

        if (down) {
            y[0] += d;
        }
    }

    public void checkCollision() {

        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
                printScore();
                break;
            }
        }

        if (y[0] > h) {
            inGame = false;
            printScore(); 
        }

        if (y[0] < 0) {
            inGame = false;
            printScore();  
        }

        if (x[0] > w) {
            inGame = false;
            printScore();
        }

        if (x[0] < 0) {
            inGame = false;
            printScore();
        }

    }

    public void locateDot() {
        int r = (int) (Math.random() * RAND_POS);
        dot_x = ((r * d));
        r = (int) (Math.random() * RAND_POS);
        dot_y = ((r * d));
    }

    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkDot();
            checkCollision();
            move();
        }
        repaint();
    }

    public void printScore()
    {
        try
        {
            HighScoreTable put = new HighScoreTable();
            put.storeScore(score,GUI.Name.getText());
        }
        catch(Exception c){}
    } 

    public void pause()
    {
        try{
            Thread.sleep(100*100*100);
        }catch(InterruptedException e){}
    }

    public void resume()
    {
        /*try{
        Thread.interrupt();
        }catch(Exception s){}*/
    }

    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!right)) {
                left = true;
                up = false;
                down = false;
            }

            else if ((key == KeyEvent.VK_RIGHT) && (!left)) {
                right = true;
                up = false;
                down = false;
            }

            else if ((key == KeyEvent.VK_UP) && (!down)) {
                up = true;
                right = false;
                left = false;
            }

            else if ((key == KeyEvent.VK_DOWN) && (!up)) {
                down = true;
                right = false;
                left = false;
            }
            else if (key == KeyEvent.VK_P) {
                if(counter==0)
                {
                    counter++;
                    pause();
                }
                else if (counter == 1)
                {
                    counter=0;
                    resume();
                }
            }
        }
    }
}