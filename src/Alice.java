import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Alice {

    private BigInteger n;
    private BigInteger e = BigInteger.TWO;
    private BigInteger p;
    private BigInteger q;
    private BigInteger f_n;
    private BigInteger d;
    private int bitLength = 15;

    BigInteger getN() {
        return n;
    }

    BigInteger getE() { return e; }

    Alice(){


        p = RandomGenerate(bitLength);

        q = RandomGenerate(bitLength);

        System.out.println("p " + p);
        System.out.println("q " + q);

        n = p.multiply(q);

        f_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        while(!e.gcd(f_n).equals(BigInteger.ONE)){

            e = e.add(BigInteger.ONE);

        }

        System.out.println("Открытый ключ: {" + n + "; " + e + "}");

        BigInteger k = BigInteger.ZERO;

        BigDecimal bigDecimal;

        do {
            k = k.add(BigInteger.ONE);
            bigDecimal = (new BigDecimal(k)).multiply(new BigDecimal(f_n)).add(BigDecimal.ONE).divide(new BigDecimal(e), 6,  BigDecimal.ROUND_HALF_UP);
        }while (!bigDecimal.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO.setScale(6)));

        System.out.println("k " + k);

        d = bigDecimal.toBigInteger();

        System.out.println("Закрытый ключ: {" + n + "; " + d + "}");

    }

    private BigInteger RandomGenerate(int bitLength){

        Random rnd = new Random();

        boolean bool;

        BigInteger bigInteger;

        do {

            bigInteger = new BigInteger(bitLength, rnd);
            bool = bigInteger.isProbablePrime((int) Math.log(bigInteger.longValue()));

        }while(!bool);

        return bigInteger;
    }

    void getMessage(BigInteger c){


        System.out.println("Get Message: ");

        BigInteger m = BigInteger.ONE;
        for(BigInteger i = BigInteger.ZERO; !i.equals(d); i = i.add(BigInteger.ONE)){
            m = c.multiply(m).mod(n);
        }
        System.out.println(m);

        //This cycle with 'long' is faster than cycle with 'BigInteger'.

        /*
        m = BigInteger.ONE;
        long mm = m.longValue();
        long nn = n.longValue();
        long dd = d.longValue();
        long cc = c.intValue();


        for (long i = 1; i <= dd; i++) {
            mm = (cc * mm)%nn;
        }
        System.out.println(mm);
        */


    }


}
