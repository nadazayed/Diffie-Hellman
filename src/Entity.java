import java.math.BigInteger;
import java.util.Random;

public class Entity 
{
	String id;
	BigInteger a,q;
	BigInteger PublicKey, PrivateKey, SessionKey;
	BigInteger EncPublicKey, DecPublicKey;
	BigInteger temp;
	
	Random r;
	Verify verify;
	
	int min, max;
	
	RSAGenKey rsa;
	BigInteger []KU;
	BigInteger []KR;
	
	RSAEncrypt encrypt;
	RSADecrypt decrypt;
	
	public Entity(String id, BigInteger a, BigInteger q)
	{
		System.out.println("Generating RSA keys for enitity: "+id+" ...");
		
		//Generate RSA public and private keys
		rsa = new RSAGenKey();
	    
		r = new Random();
		verify = new Verify();
		
		this.id = id;
		
		this.a = a;
		this.q = q;

		System.out.println("Generating DH keys for enitity: "+id+" ...");
	}
	
	public BigInteger[] generateRsaKU()
	{
		KU = rsa.KU(); // public {e,n}
		return KU;
	}
	
	public BigInteger[] generateRsaKR()
	{
		KR = rsa.KR(); // private {d,n}
		return KR;
	}
	
	public BigInteger encPublicKey(BigInteger key, BigInteger n)
	{
		System.out.println("My Public Key before Enc: "+PublicKey);
		encrypt = new RSAEncrypt();
		EncPublicKey = encrypt.enc(PublicKey, key, n);
		System.out.println(id+": "+"My Public Key after Enc: "+EncPublicKey);
		return EncPublicKey;
	}
	
	public BigInteger decPublicKey(BigInteger C, BigInteger key, BigInteger n)
	{
		System.out.println("His Public Key before Dec: "+C);
		decrypt = new RSADecrypt();
		DecPublicKey = decrypt.dec(C, KR[0], KR[1]);
		System.out.println("His Public Key after Dec: "+DecPublicKey);
		return DecPublicKey;
	}
	
	public void generatePrivateKey ()
	{
		min = 1;
		max = q.intValue()-1;
		
		PrivateKey = new BigInteger (String.valueOf(r.nextInt((max - min) + 1) + min));
//		System.out.println("Random number "+PrivateKey);

	}
	
	public void generatePublicKey ()
	{
		PublicKey = a.modPow(PrivateKey, q);
	}
	
	BigInteger generateSessionKey (String id, BigInteger C, BigInteger q, BigInteger key, BigInteger n)
	{
		BigInteger M = decPublicKey(C, key, n);
		SessionKey = M.modPow(PrivateKey, q);
		return SessionKey;
	}
	
	public void Display(String id)
	{
		System.out.println("Entity: "+id+"\t"+
				   "Public Key: "+PublicKey+"\t"+
				   "Private Key: "+PrivateKey+"\t"+
				   "Session Key: "+SessionKey);
	}
		
	/////////////
	//SNDR KUA{e,n}    KRA{d,n}
	//RCVR KUB{e,n}    KRB{d,n}
	//ENC  C = (M pow e) mod n  => to encrypt with public key
	//DEC  M = (C pow d) mod n  => to decrypt with private key
}
