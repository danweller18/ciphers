package rc4cipher;
import java.io.*;
import java.net.*;

/**
 * Sender will send an encrypted file to a Receiver over a local network. 
 * Sender will first ask for the name of file to send, and key to use as 
 * well. Then you will be asked for the Ip address and port number for 
 * the Receiver. Then the file will be encrypted using the RC4 cipher and 
 * sent to the Receiver.
 * @author Daniel Weller
 */
public class Sender {
    
    public static void main (String[] args) {
    
        try {
            //instance of rc4 to get the needed vars
            RC4Cipher rc4vars = new RC4Cipher();
            
            System.out.println("Please enter the name of file you wish to send:");
            //read in users input
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //set string to users input
            String filename = reader.readLine();
            
            System.out.println("Please enter the name of key you are using for the RC4 algorithm:");
            //read in users input
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
            //set string to users input
            String keyname = reader1.readLine();
            
            //declare name of output
            String outputname = "COSC431_P2_CiphertextSent.txt";

            //encrypt file and save
            System.out.println("Encrypting file " + filename);            
            rc4vars.main(filename, keyname, outputname);
            System.out.println("File encrypted successfully and saved as " + outputname);
            
            System.out.println("What is the receiver's IP address?");
            //read in users input
            BufferedReader ip = new BufferedReader(new InputStreamReader(System.in));
            //set string to users input
            String ipadd = ip.readLine();
            
            System.out.println("What is the receiver's port number?");
            //read in users input
            BufferedReader port = new BufferedReader(new InputStreamReader(System.in));
            //set string to users input
            int portnum = Integer.parseInt(port.readLine());
            
            try {
                //create a new socket
                Socket sock = new Socket(ipadd, portnum);
                //create new file 
                File myFile = new File(outputname);
                //establish new byte array length of myFile
                byte[] filearray  = new byte[(int)myFile.length()];
                //new fileinputstream with file
                FileInputStream fis = new FileInputStream(myFile);
                //put into buffered input stream
                BufferedInputStream bis = new BufferedInputStream(fis);
                //read from the buffered input stream into array
                bis.read(filearray,0,filearray.length);
                //create output stream to send
                OutputStream os = sock.getOutputStream();
                //declare sending of file
                System.out.println("Now sending " + myFile + "(" + filearray.length + " bytes)");
                //Send file from file byte array
                os.write(filearray,0,filearray.length);
                //make sure all has sent
                os.flush();
                System.out.println("Done.");

                //close up everything
                bis.close();
                os.close();
                sock.close();
            } catch (IOException ioe) {
                //error message
                System.out.println(ioe.getMessage());
            }
        } catch (IOException ioe) {
                //error message
                System.out.println(ioe.getMessage());
        } 
    }
}
