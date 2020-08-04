import java.math.BigInteger;
import java.util.stream.IntStream;

public class Verify 
{
	 boolean check(BigInteger a, BigInteger q)
	{
		 System.out.println(a.intValue()+"\t"+q.intValue());
		if (isPrime(q) && isPrimitiveRoot(a,q))
			return true;
		else
			return false;
	}
	 
	 boolean isPrime (BigInteger q)
	 {
		 BigInteger i;
		 boolean flag = true;
		 
		 if(q.equals(0)||q.equals(1))
		 {  
			   System.out.println(q+" is not prime number");    
			   flag = false;
		 }
		 
		 else
		 {  
			   for(i=new BigInteger(String.valueOf(2)) ;i.compareTo(q)>0; i.add(BigInteger.valueOf(1)))
			   {      
				    if(q.mod(i).equals(0))
				    {      
					     System.out.println(q+" is not prime number");        
					     flag = false;  
					     break;
				    }      
			   } 
		 }
		 return flag;
	 }
	 
	 boolean isPrimitiveRoot (BigInteger a, BigInteger q) //a=5  q=23  5^1 mod 23=5
	 {
		 Boolean flag = true;
		 
		 int length = q.subtract(BigInteger.ONE).intValue();
		 
		 int[] arr;
		 arr = IntStream.rangeClosed(1,length ).toArray();
		 
		 BigInteger res;
		 
		 for (int i=1; i<=length; i++)
		 {
			 res = a.modPow(new BigInteger(String.valueOf(i)), q);
//			 System.out.println(a+"^"+i+"%"+q+"="+res);
			 
			 arr[res.intValue()-1] = 0;
		 }
		 
		 for (int i=0; i<arr.length; i++)
		 {
			 if (arr[i]!=0)
			 {
				 flag = false;
			 		break;
			 }
		 }
			 
//		 System.out.println("FLAG: "+flag);
		 return flag;
	 }
	
}
