import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Bob {

    private BigInteger n;
    private BigInteger e;
    private BigInteger m;
    private BigInteger c = BigInteger.ONE;

    Bob(BigInteger n, BigInteger e){
        this.n = n;
        this.e = e;
    }

    BigInteger getC() {
        return c;
    }

    void Message(){


        System.out.println("Message: ");
        Scanner in = new Scanner(System.in);

        m = in.nextBigInteger();

        c = m.pow(e.intValue()).mod(n);

/*
        for(BigInteger i = BigInteger.ZERO; !i.equals(e); i = i.add(BigInteger.ONE)){
            c = m.multiply(c).mod(n);
        }
*/
    }

}
