package sg.edu.nus.iss.assessment.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class App {
    public static void main(String[] args) {
        System.out.println("ClientApp.main()");
        String[] connInfo = args[0].split(":");
        System.out.println(connInfo[0] + " " + connInfo[1]);
        Socket sock;
        String requestId;
        String email = "x@x.com";
        String name = "Kenneth Phang Tak Yan";
        float average = 0;
        int totalSum = 0;
        float howManynos = 0;

        try {
            sock = new Socket(connInfo[0], Integer.parseInt(connInfo[1]));
        
            InputStream is = sock.getInputStream();
            //DataInputStream dis = new DataInputStream(is);
            ObjectInputStream dis = new ObjectInputStream(is);

            String response = (String)dis.readObject();
            System.out.println(response);
            String[] splittedResult = response.split(" ");
            requestId = splittedResult[0];
            String[] averageNos = splittedResult[1].split(",");
            howManynos = averageNos.length;
            for (String no : averageNos) {
                totalSum += Integer.parseInt(no);
            }
            average = totalSum / howManynos;
            System.out.println(average);

            OutputStream os = sock.getOutputStream();
            //DataOutputStream dos = new DataOutputStream(os);
            ObjectOutputStream dos = new ObjectOutputStream(os);

            // dos.writeUTF(requestId);
            // dos.writeUTF(name);
            // dos.writeUTF(email);
            // dos.writeFloat(average);
            dos.writeObject(requestId);
            dos.writeObject(name);
            dos.writeObject(email);
            dos.writeObject(average);
            
            
            Boolean isOk = (Boolean)dis.readObject();
            System.out.println(isOk);
            if(isOk){
                System.out.println("SUCCESS");
            }else{
                System.out.println("FAILED");
            }
            sock.close();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
