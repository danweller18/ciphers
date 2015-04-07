package rc4cipher;
import java.io.*;
import java.net.*;

/**
 * Receiver will wait for an encrypted file to be sent to it. Then it 
 * will download the received file and decrypt it using RC4 cipher. 
 * @author Daniel Weller
 */
public class Receiver {
    
    public static void main (String[] args) {

        try {
            //instance of rc4 to get the needed vars
            RC4Cipher rc4vars = new RC4Cipher();
            
            //declare static strings
            String keyname = "COSC431_P2_Key.txt";
            String outputname = "COSC431_P2_Message_dweller0.txt";
            
            //create server socket
            ServerSocket server = new ServerSocket(6000);
            
            try {
                //create sock to accept data
                Socket client = server.accept();
                System.out.println("Accepted connection: " + client);
                
                //get ip address and port number
                String ip = client.getRemoteSocketAddress().toString();
                int port = client.getPort();
                System.out.println("The Ip address is: " + ip + " and the "
                        + "port number is " + port);
                
                //get the file name
                String filename = "COSC431_P2_CipertextReceived.txt";
                //get the file size
                //int filesize = rc4vars.getOutputFileSize();
                
                //new byte array with file size
                byte[] filearray = new byte[200000];
                //create inputstream with socket
                InputStream is = client.getInputStream();
                //create fileoutput stream with filename
                FileOutputStream fos = new FileOutputStream(filename);
                //create buffered output stream
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                //Read file into filearrary
                int readbytes = is.read(filearray, 0,filearray.length);
                int currentb = readbytes;
                do { 
                	readbytes = is.read(filearray,currentb,(filearray.length-currentb));
                	if (readbytes >= 0) currentb += readbytes;
                } while (readbytes != -1);
                //write to file
                bos.write(filearray, 0 , currentb);
                //make sure all from outputstream is gone
                bos.flush();
                System.out.println("File " + filename + " downloaded (" + 
                        currentb + " bytes read)");
                
                //decrypting file and save
                System.out.println("Decrypting file " + filename);            
                rc4vars.main(filename, keyname, outputname);
                System.out.println("File decrypted successfully and saved as " + outputname);
                
                //close the things
                bos.close();
                client.close();
                server.close();
            } catch (IOException ioe) {
                System.out.println(ioe);
            }
        } catch (IOException ioe) {
                System.out.println(ioe);
        }
    }
}


