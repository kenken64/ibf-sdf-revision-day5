package sg.edu.nus.iss.assessment.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
            DataInputStream dis = new DataInputStream(is);

            OutputStream os = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            String response = dis.readUTF();
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

            dos.writeUTF(requestId);
            dos.writeUTF(name);
            dos.writeUTF(email);
            dos.writeFloat(average);
            boolean isOk = dis.readBoolean();
            System.out.println(isOk);
            if(isOk){
                System.out.println("SUCCESS");
            }else{
                System.out.println("FAILED");
            }
            sock.close();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
