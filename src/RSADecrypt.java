import java.math.BigInteger;

public class RSADecrypt 
{
	public BigInteger dec (BigInteger c, BigInteger key, BigInteger n)
    {
        return c.modPow(key, n);
    }
}
