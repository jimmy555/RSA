import static java.math.BigInteger.valueOf;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class RSA {

	// Distinct prime numbers
	private int p;
	private int q;
	// p*q
	private int n;
	// Co prime to the lcm
	private int e;
	// Modular multiplicative inverse of e
	private int d;

	// For Encryption
	public RSA(int n){
		this.n = n;
		factorize(n);

		// Least common multiple
		int lcm = lcm(this.p, this.q);

		// public exponent e
		this.e = calculateE(lcm);
	}

	// For Decryption
	public RSA(int n, int e){
		this.n = n;
		factorize(n);

		// Least common multiple
		int lcm = lcm(this.p, this.q);
		this.e = e;

		// private exponent d
		d = calculateD(e, lcm);

	}

	public int getP(){
		return this.p;
	}

	public int getQ(){
		return this.q;
	}

	public int getE(){
		return this.e;
	}

	public int getD(){
		return this.d;
	}

	// Encrypt function
	public ArrayList<Integer> encrypt(String message) {
		ArrayList<Integer> encryptedMessage = new ArrayList<>();

		// Loop through all the characters in the message
		for(int i = 0; i < message.length(); i ++){
			// Get the ASCII value of the character
			int code = Character.codePointAt(message, i);
			// Encrypt the character
			BigInteger encryptedChar = valueOf(code).pow(this.e).remainder(valueOf(this.n));
			// Convert BigInteger to char
			int c = encryptedChar.intValue();
			// Add int to the encrypted message
			encryptedMessage.add(c);
		}

		return encryptedMessage;
	}

	// Decrypt function
	public String decrypt(ArrayList<Integer> encryptedMessage){
		String message = "";

		// Loop through the encrypted string
		for(int i = 0; i < encryptedMessage.size(); i++) {
			int code = encryptedMessage.get(i);
			// Decrypt the character
			BigInteger decryptedChar = valueOf(code).pow(this.d).remainder((valueOf(this.n)));
			// Convert BigInteger to char
			char c = (char)decryptedChar.intValue();
			// Add character to the message
			message += String.valueOf(c);
		}

		return message;
	}

	// Calculate the private exponent, or also known as D
	private int calculateD(int e, int lcm) {
		for (int i = 1; i < lcm; i++) {
			int a = (i * e) % lcm;
			if (a == 1) {
				return i;
			}
		}

		return 0;
	}

	// Calculate the public exponent, or also known as E
	private static int calculateE(int lcm) {
		int e = 2;
		while(1 < e && e < lcm){
			if (relativelyPrime(e, lcm)) {
				return e;
			}
			e++;
		}

		return 0;
	}

	// Calculate the lcm with Reduction by the Greatest Common Divisor
	private int lcm(int p, int q) {
		return ((p-1)*(q-1))/gcd(p-1, q-1);
	}

	// Factorize p and q from N
	public void factorize(int n){
		List<Integer> factorizedN = primeFactors(n);
		p = factorizedN.get(0);
		q = factorizedN.get(1);
	}

	private ArrayList<Integer> primeFactors(int n) {
		ArrayList<Integer> factors = new ArrayList<>();

		for (int i = 2; i <= n/i; i++) {
			while (n % i == 0) {
				factors.add(i);
				n /= i;
			}
		}

		if (n > 1) {
			factors.add(n);
		}

		return factors;
	}

	// Get the greates common divisor of two variables
	private static int gcd(int a, int b) {
		int t;
		while(b != 0){
			t = a;
			a = b;
			b = t%b;
		}
		return a;
	}

	// Check if e and lcm are relatively prime
	private static boolean relativelyPrime(int e, int lcm) {
		// If greates comman divisor of e and lcm is equal to one, they are relatively prime
		return gcd(e,lcm) == 1;
	}

	private static boolean isPrime(int n) {
		// Check if n is a multiple of 2
		if (n%2==0) return false;
		//If not, then just check the odds
		for(int i=3;i*i<=n;i+=2) {
			if(n%i==0)
				return false;
		}
		return true;
	}

}