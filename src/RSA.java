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
	public RSA(int p, int q){
		this.p = p;
		this.q = q;
		n = p * q;

		// Least common multiple
		int lcm = lcm(this.p, this.q);
		e = calculateE(lcm);
		d = calculateD(e, lcm);

	}

	public RSA(int n){
		this.n = n;
		factorize(n);

		// Least common multiple
		int lcm = lcm(this.p, this.q);
		e = calculateE(lcm);
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

	public ArrayList<Integer> encrypt(String message) {
		ArrayList<Integer> encryptedMessage = new ArrayList<>();

		for(int i = 0; i < message.length(); i ++){
			int code = Character.codePointAt(message, i);
			// Encrypt the character
			BigInteger encryptedChar = valueOf(code).pow(this.e).remainder(valueOf(this.n));
			// Convert BigInteger to int
			char c = (char)encryptedChar.intValue();
			encryptedMessage.add((int)c);
		}

		return encryptedMessage;
	}

	public String decrypt(ArrayList<Integer> encryptedMessage){
		String message = "";

		for(int i = 0; i < encryptedMessage.size(); i++) {
			int code = encryptedMessage.get(i);
			// Decrypt the character
			BigInteger decryptedChar = valueOf(code).pow(this.d).remainder((valueOf(this.n)));
			// Convert BigInteger to String
			char c = (char)decryptedChar.intValue();
			message += String.valueOf(c);
		}

		return message;
	}

	private int calculateD(int e, int phi) {
		e = e % phi;
		for (int i = 1; i < phi; i++) {
			int a = (i * e) % phi;
			if (a == 1) {
				return i;
			}
		}

		return 0;
	}

	private static int calculateE(int lcm) {
		int e = 2;
		while(1 < e && e < lcm){
			if (isPrime(e) && relativelyPrime(e, lcm)) {
				return e;
			}
			e++;
		}

		return 0;
	}

	private int lcm(int p, int q) {
		return ((p-1)*(q-1))/gcd(p-1, q-1);
	}

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

	private static int gcd(int a, int b) {
		int t;
		while(b != 0){
			t = a;
			a = b;
			b = t%b;
		}
		return a;
	}

	private static boolean relativelyPrime(int e, int lcm) {
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