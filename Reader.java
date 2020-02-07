import java.io.*;
import java.util.*;

public class Reader
{
    public void read() throws IOException
    {
        BufferedReader in = new BufferedReader (new FileReader ("H_Score.txt"));
        BufferedReader in2 = new BufferedReader (new FileReader ("H_Name.txt"));
        int line=0;
        while(in.readLine()!=null && in2.readLine()!=null)
        {
            line++;
        }
        in.close();
        in2.close();

        String names[] = new String[line];
        String scores[] = new String[line];

        BufferedReader in3 = new BufferedReader (new FileReader ("H_Score.txt"));
        BufferedReader in4 = new BufferedReader (new FileReader ("H_Name.txt"));
        for(int x=0;x<line;x++)
        {               
            scores[x] = in3.readLine();
            names[x]=in4.readLine();
        }
        in3.close();
        in4.close();

        if(line==1)
        {
            for(int z=0;z<line;z++)
            {  
                GUI.Highscore.setValueAt(names[z],z+1,0);
                GUI.Highscore.setValueAt(scores[z],z+1,1); 
            }
        }
        String ScoreTab[][] = new String [line][line];
        for(int z=0;z<line;z++)
        {               
            ScoreTab[z][0] = names[z];
            ScoreTab[z][1] = scores[z];
        }

        for (int i=0;i<line-1;++i)                                   
        {
            for (int j=0;j<=line-2;++j)
            {
                int value1 = Integer.parseInt(ScoreTab[j][1]);
                int value2 = Integer.parseInt(ScoreTab[j+1][1]);
                if (value1<value2)
                {
                    for (int x=0; x<2; x++)
                    {
                        String temp = ScoreTab[j][x];
                        ScoreTab[j][x]=ScoreTab[j+1][x];
                        ScoreTab[j+1][x]=temp;
                    }
                }
            }
        }

        for(int z=0;z<line;z++)
        {  
            GUI.Highscore.setValueAt(ScoreTab[z][0],z+1,0);
            GUI.Highscore.setValueAt(ScoreTab[z][1],z+1,1); 
        }

    }
    /*try{
    LinkedList names = new LinkedList();
    LinkedList scores = new LinkedList();
    BufferedReader in = new BufferedReader (new FileReader ("H_Score.txt"));
    BufferedReader in2 = new BufferedReader (new FileReader ("H_Name.txt"));
    int line=0;
    while(in.readLine()!=null && in2.readLine()!=null)
    {
    line++;
    }
    in.close();
    in2.close();

    BufferedReader in3 = new BufferedReader (new FileReader ("H_Score.txt"));
    BufferedReader in4 = new BufferedReader (new FileReader ("H_Name.txt"));
    for(int x=0;x<line;x++)
    {               
    scores.add(in3.readLine());
    names.add(in4.readLine());
    }
    in3.close();
    in4.close();
    //System.out.println(scores);

    for(int y=0;y<line;y++)
    {  
    if(scores.get(y)==null || scores.get(y)=="")
    {
    scores.set(y," ");
    }
    if(names.get(y)==null || scores.get(y)=="")
    {
    names.set(y," ");
    }
    }
    //Collections.reverse(scores);
    //Collections.sort(scores);
    for(int z=0;z<line;z++)
    {  
    GUI.Highscore.setValueAt(names.get(z),z+1,0);
    GUI.Highscore.setValueAt(scores.get(z),z+1,1); 
    }
    }
    catch(FileNotFoundException fnfe){}*/
}
