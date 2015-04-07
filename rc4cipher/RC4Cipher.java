package rc4cipher;
import java.io.*;

/**
 * RC4Cipher will take inputs of key, filename to encrypt/decrypt, 
 * and encrypted/decrypted filename and will encrypt/decrypt the file. The 
 * program uses an implementation of the RC4 cipher and will encrypt/decrypt 
 * byte by byte.
 * @author Daniel Weller
 */
public class RC4Cipher {

    private int outputsize;
    
    public void main (String filename, String keyname, String outputname) {
        
        try {
            //set outputfile size
            File myFile = new File(outputname);
            this.outputsize = (int)myFile.length();
            
            //buffered reader reads in text file of key
            BufferedReader keyi = new BufferedReader(new FileReader(keyname));
            String skey = keyi.readLine();
            //find length of key file
            int klength = skey.length();
            
            //read in key to a stream
            FileInputStream in = new FileInputStream(keyname);
            
            //initialize byte array
            byte[] K = null;
            //give array length of file
            K = new byte[klength];
            
            //loop to write key byte by byte into K[];
            for(int i=0; i<K.length; i++) {
                int key = in.read();
                //convert int to byte
                byte b = (byte) key;
                //append byte value to K value spot
                K[i] = b;
                //System.out.println(K[i]);
            }
                        
            //open input stream file for reading file to be encrypted
            FileInputStream input = new FileInputStream(filename);    
            //write to the destination file
            FileOutputStream out = new FileOutputStream(outputname);
            
            //initialize/set variables
            byte[] S = new byte[256];
            byte[] T = new byte[256];
            int keylen;
            keylen = K.length;
            
            /* Initialization- Step 1 */
            for (int i=0; i<256; i++ ) {
                S[i] = (byte)i;
                T[i] = K[i % keylen];
            }

            /* Initial Permutation of S- Step 2 */
            for (int i=0,j=0; i<256; i++) {   
                j = (j + S[i] + T[i]) & 0xFF;
                /*Swap (S[i], S[j]); goes to: */
                byte temp = S[j];
                S[j] = S[i];
                S[i] = temp;
            }

            //initialize variables
            int i = 0;
            int j = 0;
            int t;
            int k;
            byte c;
            byte p;
            
            //while loop that runs while stream is around.
            /* Stream Generation- Step 3 */
            while(input.available()>0){
                // read the file byte by byte
                p = (byte) input.read();
                i = (i + 1) & 0xFF;
                j = (j + S[i]) & 0xFF;
                /*Swap (S[i], S[j]); goes to: */
                byte temp = S[j];
                S[j] = S[i];
                S[i] = temp;
                t = (S[i] + S[j]) & 0xFF;
                k = S[t];
                //XOR plaintext with k
                c = (byte) (p ^ k);
                //write ciphertext to file
                out.write(c); 
            }
            //close everything
            in.close();
            input.close();
            keyi.close();
            out.close();
        } catch (IOException ioe) {
            //error message
            System.out.println(ioe.getMessage());
        }
    }
    
    public int getOutputFileSize() {
       return this.outputsize;
    }
}