import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Bob {

    private List<BigInteger> c_message = new ArrayList<>();

    List<BigInteger> getC_message(){
        return c_message;
    }

    void Message(BigInteger n, BigInteger e){

        System.out.println("Message: ");
        System.out.print(">>> ");
        Scanner in = new Scanner(System.in);

        String message = in.nextLine();

        char[] mesChar = message.toCharArray();

        c_message = new ArrayList<>();
        for(int i = 0; i<mesChar.length;i++) {
            BigInteger kodChar = BigInteger.valueOf((int) mesChar[i]);
            c_message.add(kodChar.pow(e.intValue()).mod(n));
        }

    }

}
