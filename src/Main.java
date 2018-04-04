import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        /*
            ENCRYPTION
         */
        // Make sure the arguments are not empty and that there are two arguments, to avoid getting a crash
        /*if (args != null && args.length == 2){

            // Get p,q and the message from the arguments
            String nCombined = args[0];
            int p = Integer.valueOf(nCombined.split("\\*")[0]);
            int q = Integer.valueOf(nCombined.split("\\*")[1]);
            String message = args[1];

            // Start time for timing how long the encryption takes
            long startTime = System.currentTimeMillis();

            int n = p * q;
            // Instantiate the RSA class
            RSA rsa = new RSA(n);

            // Encrypt the message
            ArrayList<Integer> encryptedMessage = rsa.encrypt(message);

            // Finish time for timing how long the encryption takes
            long finishTime = System.currentTimeMillis();

            // Print output
            System.out.println("Output: p is: " + rsa.getP());
            System.out.println("Output: q is: " + rsa.getQ());
            System.out.println("Output: e is: " + rsa.getE());
            System.out.println("Output: Message after encryption is: " + encryptedMessage);
            System.out.println("Output: Amount of time busy encoding was: " + (finishTime - startTime) + " ms");

        } else {
            System.out.println("Make sure there are exactly 2 arguments");
        }*/

        /*
            DECRYPTION
            Uncomment for enabling decryption
         */
        // Make sure the arguments are not empty and that there are two arguments, to avoid getting a crash

        if (args != null && args.length == 3){

            // Get p and the message from the arguments
            int n = Integer.valueOf(args[0]);
            int e = Integer.valueOf(args[1]);
            String message = args[2];
            String [] letters = message.split(",");
            ArrayList<Integer> integers = new ArrayList<>();
            for (String letter : letters) {
                int letterCode = Integer.valueOf(letter.replace(" ", ""));
                integers.add(letterCode);
            }

            // Start time for timing how long the encryption takes
            long startTime = System.currentTimeMillis();

            // Instantiate the RSA class
            RSA rsa = new RSA(n, e);
            // Encrypt the message
            String decryptedMessage = rsa.decrypt(integers);

            // Finish time for timing how long the encryption takes
            long finishTime = System.currentTimeMillis();

            // Print output
            System.out.println("Output: p is: " + rsa.getP());
            System.out.println("Output: q is: " + rsa.getQ());
            System.out.println("Output: e is: " + rsa.getE());
            System.out.println("Output: Message after decoding is: " + decryptedMessage);
            System.out.println("Output: Amount of time busy encoding was: " + (finishTime - startTime) + " ms");

        } else {
            System.out.println("Make sure there are exactly 2 arguments");
        }

    }
}
