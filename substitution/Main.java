
package substitution;
import java.io.*;
import java.util.*;

/**
 * This program can be used to conduct frequency analysis to help decrypt 
 * ciphertext that has been encrypted using substitution. A hash map is used to 
 * count the letters in the ciphertext and display the most frequently used 
 * letters in the ciphertext. The program uses a switch statement to substitute 
 * different letters into the correct spot.
 * 
 * @author Daniel Weller
 */
public class Main {
    
    public static void main (String[] args) {
        //ciphertext section
        try {
            
            //hashmap to count frequencies
            HashMap<Character, Integer> frequency = new HashMap<Character, Integer>();
            
            //buffered reader reads in text file of domains
            BufferedReader reader = new BufferedReader(new FileReader("../P1_Daniel_Ciphertext.txt"));
            
            /* count letter frequencies */
            String line1;
            while ((line1 = reader.readLine()) != null) {
                for(int i = 0; i < line1.length(); i++){
                    char c = line1.charAt(i);
                    Integer val = frequency.get(new Character(c));
                    if(val != null){
                      frequency.put(c, new Integer(val + 1));
                    }else{
                      frequency.put(c,1);
                    }
                 }
            }
            
            //print out frequencies for each letter
            for (Character key : frequency.keySet()) {
                System.out.println(key + " " + frequency.get(key));
            }
            /* end count frequencies */
            
            //write to file
            FileWriter writer = new FileWriter("../P1_Plaintext_dweller0.txt");
            
            /* output section */
            //read from file and print out
            File file = new File("../P1_Daniel_Ciphertext.txt");
            Scanner fileScanner = new Scanner(file);
            //scanner line by line
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.equals("")) {
                    writer.write("\r\n");
                }

                Scanner lineScanner = new Scanner(line);
                //scanner to look at each line individually
                while (lineScanner.hasNext()) {
                    String ciphertext = lineScanner.nextLine();
                    /*transform char to new char*/
                    String plaintext = "";
                    for (int i=0; i<ciphertext.length(); i++){
                        char ch = line.toLowerCase().charAt(i);
                        /* switch statement*/
                        char newch = ' ';
                        if (Character.isLetter(ch)) {
                            switch (ch) {
                                case 'a' :    newch = 'i';
                                                break;
                                case 'b' :    newch = 'g';
                                                break;
                                case 'c' :    newch = 'v';
                                                break;
                                case 'd' :    newch = 'x';
                                                break;
                                case 'e' :    newch = 'k';
                                                break;
                                case 'f' :    newch = 'l';
                                                break;
                                case 'g' :    newch = 'd';
                                                break;
                                case 'h' :    newch = 'e';
                                                break;
                                case 'i' :    newch = 'h';
                                                break;
                                case 'j' :    newch = 'w';
                                                break;
                                case 'k' :    newch = 'p';
                                                break;
                                case 'l' :    newch = 'y';
                                                break;
                                case 'm' :    newch = 'z';
                                                break;
                                case 'n' :    newch = 'r';
                                                break;
                                case 'o' :    newch = 'u';
                                                break;
                                case 'p' :    newch = 'm';
                                                break;
                                case 'q' :    newch = 't';
                                                break;
                                case 'r' :    newch = 'f';
                                                break;
                                case 's' :    newch = 'c';
                                                break;
                                case 't' :    newch = 'a';
                                                break;
                                case 'u' :    newch = 'o';
                                                break;
                                case 'v' :    newch = 's';
                                                break;
                                case 'w' :    newch = 'q';
                                                break;
                                case 'x' :    newch = 'b';
                                                break;
                                case 'y' :    newch = 'n';
                                                break;
                                case 'z' :    newch = 'j';
                                                break;
                                default:      break;
                            }
                            /* end switch statement*/
                            String ptext = Character.toString(newch);
                            plaintext = plaintext.concat(ptext);
                        }  else {
                            String ptext = Character.toString(ch);
                            plaintext = plaintext.concat(ptext);
                        } /* end if statement */
                    }
                    /*end transforming char */
                    writer.write(plaintext + "\r\n");
                }
                lineScanner.close();
            }
            fileScanner.close();
            /*end of output section */
            
            //close filewriter
            writer.close();
            //close filereader
            reader.close();
        } catch (IOException ioe) {
            //error message
            System.out.println(ioe.getMessage());
        }   
        //end ciphertext section
    }
}
