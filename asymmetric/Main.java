package asymmetric;
import java.util.*;
import java.io.*;

/**
 * This program will take in the plaintext and encrypt it using the table M2.
 * Tables M1 and M2 are randomly generated and will determine the values of M3.
 * M1[k] and M2 are used as the public key in the encryption. The integers 0-25 
 * will represent the alphabet a-z. We will ignore the case and use ASCII characters
 * to translate from integers to characters. 
 * 
 * @author Daniel Weller
 */
public class Main {
    
    public static void main (String[] args) {
        
        System.out.println("Starting encryption of plaintext");
        
        //create a new arrary for m1
        int[] m1 = new int[26];
        //set all of m1 to -1
        for (int i=0; i<m1.length; i++) {
            m1[i] = -1; 
        }
        //set seed for random number generator
        Random randomNum = new Random(2185);
        //loop to assign random number to m1
        for (int i=0; i<m1.length; i++){
            m1[i] = randomNum.nextInt(26);
            //loop to check array with current number
            for (int j=0; j<i; j++) {
                //if randomNum is same as array value obtain new randomNum
                if (m1[i] == m1[j]) {
                    //decrement i to run i again
                    --i;
                    break;
                }
            }
        }
        
        //create new array for m2
        int[][] m2 = new int[26][26];
        //set all of m2 = -1
        for (int i=0; i<26; i++) {
            for (int j=0; j<26; j++){
                m2[i][j] = -1;
            }
        }
        //loop to assign random number to m2
        for (int i=0; i<26; i++) {
            for (int j=0; j<26; j++){
                m2[i][j] = randomNum.nextInt(26);
                for (int a=0; a<j; a++){
                    if (m2[i][j] == m2[i][a]) {
                       --j;
                       break;
                    }
                }
            }
        }
        
        //private key value
        int k = 17;
        
        //create new array for m3
        int[][] m3 = new int[26][26];
        //set all of m2 = -1
        for (int i=0; i<26; i++) {
            for (int j=0; j<26; j++){
                m3[i][j] = -1;
            }
        }
        
        //loop to assign m3 based on m1 + m2
        for (int i=0; i<26; i++) {
            for (int j=0; j<26; j++){
                int z = m1[j];
                int y = m2[z][i];
                m3[y][j] = i;
            }
        }
        
        String plaintext = new String("Frostburg State University");
        String ciphertext = "";
        // loop to encrypt char by char
        for (int i=0; i<plaintext.length(); i++){
            char ch = plaintext.toLowerCase().charAt(i);
            if (ch == ' '){
                String cspace = Character.toString(ch);
                ciphertext = ciphertext.concat(cspace);
                continue;
            }
            int p = ch - 'a';
            int z = m1[k];
            int y = m2[z][p];
/* int x is used for decryption 
            int x = m3[y][k];*/
            
            //convert to cipher text
            int w = y + 97;
            char c = (char) w;
            String ctext = Character.toString(c);
            ciphertext = ciphertext.concat(ctext);
        }
        
        /*writing the output */
        try {
            FileWriter writer = new FileWriter("../COSC431_P0_Output_dweller0.txt");

            writer.write("\r\n");
            for (int i=0; i<35; i++){
                writer.write("=");
            }
            writer.write("Start of Output");
            for (int i=0; i<45; i++){
                writer.write("=");
            }
            writer.write("\r\n\n");
            writer.write("Random Seed: 2185");
            writer.write("\r\n\n");
            for (int i=0; i<95; i++){
                writer.write("=");
            }
            writer.write("\r\n\n");

            writer.write("Private Key:");
            writer.write("\r\n\n");
            writer.write("K = 17");
            writer.write("\r\n\n\n");
            
            /*writing m1 array*/
            writer.write("M1 =");
            writer.write("\r\n");
            
            for (int i=0; i<m1.length; i++){
                if (i < 10) {
                    writer.write("          " + i + " : " + m1[i]);
                    writer.write("\r\n");
                } else {
                    writer.write("         " + i + " : " + m1[i]);
                    writer.write("\r\n");                    
                }
            }
            /*end of writing m1 array*/
            
            writer.write("\r\n\n\n");
            
            /* writing m3 array */
            writer.write("M3 =");
            writer.write("\r\n"); 
            
            //column margin
            writer.write("             ");
            for (int i=0; i<26; i++) {
                if (i < 10) {
                    writer.write("  " + i);   
                } else {
                    writer.write(" " + i);
                }
            }
            writer.write("\r\n");

            //divider between column and nubmers
            writer.write("             ");
            for (int i=0; i<26; i++) {
                writer.write("___");
            }
            writer.write("\r\n");
            
            //printing out each row
            for (int i=0; i<26; i++) {
                if (i < 10) {
                    writer.write("          " + i + " | ");
                    for (int j=0; j<26; j++){
                        if (m3[i][j] < 10) {
                            writer.write(" " + m3[i][j] + " ");                            
                        } else {
                            writer.write(m3[i][j] + " ");
                        }
                    }
                    writer.write("\r\n");            
                } else {
                    writer.write("         " + i + " | ");
                    for (int j=0; j<26; j++){
                        if (m3[i][j] < 10) {
                            writer.write(" " + m3[i][j]+ " ");
                        } else {
                            writer.write(m3[i][j] + " ");
                        }
                    }
                    writer.write("\r\n");
                }
            }
            /*end of displaying m3 array*/

            writer.write("\r\n\n");
            for (int i=0; i<95; i++){
                writer.write("=");
            }writer.write("\r\n\n");
            
            writer.write("Public key: ");
            writer.write("\r\n\n");
            writer.write("X = M1[K] = 1");
            writer.write("\r\n\n\n");
            
            
            /*writing m2 array*/
            writer.write("M2 = \r\n\n");
            
            //column margin
            writer.write("             ");
            for (int i=0; i<26; i++) {
                if (i < 10) {
                    writer.write("  " + i);   
                } else {
                    writer.write(" " + i);
                }
            }
            writer.write("\r\n");

            //divider between column and nubmers
            writer.write("             ");
            for (int i=0; i<26; i++) {
                writer.write("___");
            }
            writer.write("\r\n");
            
            //printing out each row
            for (int i=0; i<26; i++) {
                if (i < 10) {
                    writer.write("          " + i + " | ");
                    for (int j=0; j<26; j++){
                        if (m2[i][j] < 10) {
                            writer.write(" " + m2[i][j] + " ");                            
                        } else {
                            writer.write(m2[i][j] + " ");
                        }
                    }
                    writer.write("\r\n");            
                } else {
                    writer.write("         " + i + " | ");
                    for (int j=0; j<26; j++){
                        if (m2[i][j] < 10) {
                            writer.write(" " + m2[i][j]+ " ");
                        } else {
                            writer.write(m2[i][j] + " ");
                        }
                    }
                    writer.write("\r\n");
                }
            }
            /*end of writing m2 array*/
            
            writer.write("\r\n");
            for (int i=0; i<95; i++){
                writer.write("=");
            }
            writer.write("\r\n\n");
            
            writer.write("PlainText = \"" + plaintext + "\"\r\n\n");
            writer.write("CipherText = \"" + ciphertext + "\"\r\n\n\n\n");
            
            for (int i=0; i<35; i++){
                writer.write("=");
            }
            writer.write("End of Output");
            for (int i=0; i<47; i++){
                writer.write("=");
            }
            
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("Successfully encrypted file");
    }
}
