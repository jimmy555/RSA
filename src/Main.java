import java.util.ArrayList;

public class Main {

    private static int N = 36019;
    private static int n = 13589;

    private static String message = "Successful entrepreneurs are givers and not takers of positive energy. ~Anonymous";
    private static int[] decryptM = {7449, 11450, 11450, 3191, 4127, 1346, 436, 3191, 4379, 436, 998, 2720, 12765, 3771, 3191, 5752, 2720, 1527, 3191, 5752, 4127, 12765, 998, 3191, 13574, 436, 1346, 998, 3191, 13236, 3723, 3191, 2411, 998, 3191, 11955, 2720, 6864, 998, 3191, 13574, 11955, 998, 3191, 5752, 4127, 1346, 436, 2720, 5610, 998, 3191, 13574, 4127, 3191, 5800, 1346, 436, 3771, 1346, 998, 3191, 13574, 11955, 998, 12765, 3191, 761, 8609, 2720, 11450, 13574, 3191, 5691, 13236, 3771, 1527, 998, 5178};

    public static void main(String[] args) {
        /*
            ENCRYPTION
         */
        // Make sure the arguments are not empty and that there are two arguments, to avoid getting a crash
        if (args != null && args.length == 2){

            // Get p,q and the message from the arguments
            String nCombined = args[0];
            int p = Integer.valueOf(nCombined.split("\\*")[0]);
            int q = Integer.valueOf(nCombined.split("\\*")[1]);
            String message = args[1];

            // Start time for timing how long the encryption takes
            long startTime = System.currentTimeMillis();

            // Instantiate the RSA class
            RSA rsa = new RSA(p, q);

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
        }

        /*
            DECRYPTION
            Uncomment for enabling decryption
         */
        // Make sure the arguments are not empty and that there are two arguments, to avoid getting a crash
        /*
        if (args != null && args.length == 2){

            // Get p and the message from the arguments
            int p = Integer.valueOf(args[0]);
            String message = args[1];
            String [] letters = message.split(",");
            ArrayList<Integer> integers = new ArrayList<>();
            for (String letter : letters) {
                int letterCode = Integer.valueOf(letter);
                integers.add(letterCode);
            }

            // Start time for timing how long the encryption takes
            long startTime = System.currentTimeMillis();

            // Instantiate the RSA class
            RSA rsa = new RSA(p);
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
        */
    }
}
