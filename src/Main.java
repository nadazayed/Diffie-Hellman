import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main 
{

	public static void main(String[] args) 
	{
		Verify verify = new Verify();
		Scanner scan = new Scanner (System.in);
		
		BigInteger Xa, Ka;
		BigInteger Xb, Kb;
		BigInteger a,  q;
		BigInteger EYa, EYb;
		
		System.out.println("Enter common a and q values:\n");
		a = new BigInteger(String.valueOf(scan.nextInt()));
		q = new BigInteger(String.valueOf(scan.nextInt()));
		
		if (!verify.check(a,q))
		{
			System.out.println("inputs don't fit the criteria for prime and primitive root of a prime.");
			System.exit(1);
		}
		
//		System.out.println("A: "+"Enter your private key:\n");
//		Xa = new BigInteger(String.valueOf(scan.nextInt()));
		Entity A = new Entity("A", a, q);
		A.generatePrivateKey(); //XA
		A.generatePublicKey();  //YA = α^XA mod q
		
//		System.out.println("B: "+"Enter your private key:\n");
//		Xb = new BigInteger(String.valueOf(scan.nextInt()));
		Entity B = new Entity("B", a, q);
		B.generatePrivateKey();
		B.generatePublicKey(); 

		//Generate RSAKeys
		BigInteger []KUA = A.generateRsaKU();
		BigInteger []KRA = A.generateRsaKR();
		System.out.println("A: KU: {e:"+ KUA[0]+",n:"+KUA[1]+"} KR: {d:"+KRA[0]+",n:"+KRA[1]+"}");
		
		BigInteger []KUB = B.generateRsaKU();
		BigInteger []KRB = B.generateRsaKR();
		System.out.println("B: KU: {e:"+ KUB[0]+",n:"+KUB[1]+"} KR: {d:"+KRB[0]+",n:"+KRB[1]+"}");
		
//		Encrypt DH-Public keys using RSA Public key of rcvr
		EYa = A.encPublicKey(KUB[0], KUB[1]);
		EYb = B.encPublicKey(KUA[0], KUA[1]);
		
//		Generate Session Keys
		Ka = A.generateSessionKey("A", EYb, q, KRA[0], KRA[1]);
		Kb = B.generateSessionKey("B", EYa, q, KRB[0], KRB[1]);
		
		System.out.println(" ");
		A.Display("A");
		B.Display("B");
	}

}
