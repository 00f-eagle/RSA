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
    private BigInteger m;
    private List<BigInteger> decod = new ArrayList<>();

    BigInteger getN() {
        return n;
    }

    BigInteger getE() { return e; }

    Alice(){


        p = RandomGenerate(10);

        q = RandomGenerate(10);

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
            bigDecimal = (new BigDecimal(k)).multiply(new BigDecimal(f_n)).add(BigDecimal.ONE).divide(new BigDecimal(e), 3,  BigDecimal.ROUND_HALF_UP);
        }while (!bigDecimal.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO.setScale(3)));

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

    public void getMessage(List<BigInteger> codMess){

        for (int i = 0; i < codMess.size(); i++) {

            m = codMess.get(i).pow(d.intValue()).mod(n);
            decod.add(m);
        }

        for (int i = 0; i < decod.size(); i++) {
            System.out.print(decod.get(i));
        }

    }

}
