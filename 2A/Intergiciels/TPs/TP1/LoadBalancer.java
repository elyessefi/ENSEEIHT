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

public class LoadBalancer {
    static String hosts[] = {"localhost", "localhost"};
    static int ports[] = {8081,8082};
    static int nbHosts = 2;
    static Random rand = new Random();
    
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(8080);
        while(true){
            try {   
                    Socket ss = server.accept();
                    Slave slave = new Slave(ss);
                    slave.run();
            }catch(IOException e){break;}
        }
    }
}
