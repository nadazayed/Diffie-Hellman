import java.math.BigInteger;

public class RSAEncrypt 
{
	public BigInteger enc (BigInteger m, BigInteger key, BigInteger n)
    {
        return m.modPow(key, n);
    }
}
