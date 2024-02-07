package sg.edu.nus.iss.assessment.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static void main(String[] args) {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        ServerSocket serverSocket = null;
        String serverPort = args[0];
        System.out.println("" + serverPort + " " );
        try {
            // Create a server
            serverSocket = 
                    new ServerSocket(Integer.parseInt(serverPort));
            System.out.println("Server started on " +serverPort);
            pattern = Pattern.compile(EMAIL_PATTERN);
            
            socket = serverSocket.accept();
            is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            os = socket.getOutputStream();
            DataOutputStream dos =new DataOutputStream(os);
            dos.writeUTF("1234abcd 97,35,82,2,45");
            while(true){
                try{
                    String requestId = dis.readUTF();
                    String name = dis.readUTF();
                    String email = dis.readUTF();
                    float average = dis.readFloat();
                    System.out.println("Request Id: " + requestId);
                    System.out.println("Name: " + name);
                    System.out.println("Email: " + email);
                    System.out.println("Average: " + average);
                    System.out.println(average);
                    matcher = pattern.matcher(email);
                    boolean isEmailOk = matcher.matches();
                    System.out.println(isEmailOk);
                    if(average != 52.2f  || isEmailOk == false){
                        dos.writeBoolean(false);
                    }else{
                        dos.writeBoolean(true);
                    }
                }catch(EOFException e){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
                os.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
    }
}
