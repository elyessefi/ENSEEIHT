import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Random;

public class Slave extends Thread {
    Socket s1;
    public Slave(Socket s){
        this.s1 = s;
    }
    
    public void run(){
    try{
        int nb = LoadBalancer.rand.nextInt(LoadBalancer.nbHosts);
        
        Socket s2 = new Socket (LoadBalancer.hosts[nb], LoadBalancer.ports[nb]);
        
        InputStream s1_in = s1.getInputStream();
        InputStreamReader data = new InputStreamReader(s1_in);
        //System.out.println("Client Data : " + new LineNumberReader(data).readLine());
        System.out.println("Client Data : " + data);
        
        OutputStream s1_out = s1.getOutputStream();
        
        InputStream s2_in = s2.getInputStream();
        OutputStream s2_out = s2.getOutputStream();
        
        byte[] buffer = new byte[1024];
        
        int i;
        
        i=s1_in.read(buffer);
        s2_out.write(buffer,0,i);
        
        i=s2_in.read(buffer);
        s1_out.write(buffer,0,i);
        
        s2.close();
        
        System.out.println("Server Respond: " + new String(buffer, Charset.defaultCharset()));
        
        s1.close();
    }catch (IOException e){e.printStackTrace();}
    }
}
