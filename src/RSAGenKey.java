import java.math.BigInteger;
import java.util.Random;

public class RSAGenKey
{
    BigInteger p, q, e;
    int bitlength = 128;
    Random r;
    public RSAGenKey ()
    {
        r = new Random();
        this.p = BigInteger.probablePrime(bitlength, r);
        this.q = BigInteger.probablePrime(bitlength, r);
        this.e = BigInteger.probablePrime(bitlength / 2, r);

        System.out.println("p:"+p+"\nq:"+q+"\ne:"+e+"\n");
    }

    public BigInteger []KU ()
    {
        BigInteger n = (p.multiply(q));
        BigInteger phi = (p.subtract(BigInteger.valueOf(1))).multiply(q.subtract(BigInteger.valueOf(1)));

        BigInteger []arr = {e,n};
        return arr;
    }

    public BigInteger []KR ()
    {
        BigInteger n = (p.multiply(q));
        BigInteger phi = (p.subtract(BigInteger.valueOf(1))).multiply(q.subtract(BigInteger.valueOf(1)));
        BigInteger d = null; //e.d mod ø(n) = 1

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0)
        {
            e = e.add(BigInteger.valueOf(1));
        }
        d = e.modInverse(phi); //their mult = 1

        BigInteger []arr = {d,n};
        return arr;
    }
}
