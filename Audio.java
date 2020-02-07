import java.io.*;
import javax.sound.sampled.AudioInputStream; 
import sun.audio.*; 
public class Audio
{
    public void ButtonNoise()
    {
        try{
            InputStream in = new FileInputStream("Sound/button.wav");
            AudioStream as = new AudioStream(in);   
            AudioPlayer.player.start(as);     
            AudioPlayer.player.stop(as); 
        }
        catch(Exception g){}
    }

    public void SnakeNoise()
    {
        try{
            InputStream in = new FileInputStream("Sound/eat.wav");
            AudioStream as = new AudioStream(in);   
            AudioPlayer.player.start(as);     
            AudioPlayer.player.stop(as); 
        }
        catch(Exception g){}
    }

    public void background(boolean stop)
    {
        try{
            InputStream in = new FileInputStream("Sound/Background.wav");
            AudioStream as = new AudioStream(in);   
            if(stop == true)
            {
                as.close();
            }
            else
            {
                AudioPlayer.player.start(as);
            }
        }
        catch(Exception g){}
    }
}
