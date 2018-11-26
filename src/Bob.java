import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Bob {

    private BigInteger n;
    private BigInteger e;
    private String message;
    private BigInteger m;
    private BigInteger c;
    private List<BigInteger> codMess = new ArrayList<>();



    public List<BigInteger> getC() {
        return codMess;
    }

    Bob(BigInteger n, BigInteger e){
        this.n = n;
        this.e = e;
    }

    public void Message(){

        System.out.println("Message: ");
        Scanner in = new Scanner(System.in);

        message = in.nextLine();
        char[] mesChar = message.toCharArray();


        for (int i = 0; i < mesChar.length; i++) {

            c = BigInteger.valueOf(mesChar[i]).pow(e.intValue()).mod(n);
            codMess.add(c);

        }



    }

}
