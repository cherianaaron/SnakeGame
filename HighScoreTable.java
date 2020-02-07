import java.io.*;
import java.util.*;
public class HighScoreTable
{    
    public void storeScore(int Score,String Name)throws IOException
    {
        PrintWriter out = new PrintWriter ( new FileWriter ("H_Name.txt",true));
        PrintWriter out2 = new PrintWriter ( new FileWriter ("H_Score.txt",true));
        out.println(Name);
        out2.println(Score);
        out.close();
        out2.close();
    }
}

