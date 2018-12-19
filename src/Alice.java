import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

class Alice {

    private int bitLength = 15;

    private Map<String, BigInteger> open_key_e;
    private Map<String, BigInteger> open_key_n;
    private Map<String, BigInteger> close_key_d;

    BigInteger getOpen_key_e(String name) {
        return open_key_e.get(name);
    }

    BigInteger getOpen_key_n(String name) {
        return open_key_n.get(name);
    }

    Alice(){

        open_key_e = ReadFile("res/file_E");
        open_key_n = ReadFile("res/file_N");
        close_key_d = ReadFile("res/file_D");

    }

    void getKey(String name){

        BigInteger p = RandomGenerate(bitLength);
        BigInteger q = RandomGenerate(bitLength);

        BigInteger n = p.multiply(q);

        BigInteger f_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = BigInteger.TWO;
        while(!e.gcd(f_n).equals(BigInteger.ONE)){
            e = e.add(BigInteger.ONE);
        }

        BigInteger k = BigInteger.ZERO;
        BigDecimal bigDecimal;
        do {
            k = k.add(BigInteger.ONE);
            bigDecimal = (new BigDecimal(k)).multiply(new BigDecimal(f_n)).add(BigDecimal.ONE).divide(new BigDecimal(e), 6,  BigDecimal.ROUND_HALF_UP);
        }while (!bigDecimal.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO.setScale(6)));


        BigInteger d = bigDecimal.toBigInteger();


        open_key_e.put(name, e);
        open_key_n.put(name, n);
        close_key_d.put(name, d);


        WriteFile("res/file_E", open_key_e);
        WriteFile("res/file_N", open_key_n);
        WriteFile("res/file_D", close_key_d);
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

    void getMessage(List<BigInteger> c_message, String name, String name2){


        StringBuffer message = new StringBuffer();
        for(int i = 0; i<c_message.size();i++) {

            BigInteger m = c_message.get(i).modPow(close_key_d.get(name), open_key_n.get(name));

            char cur = (char) m.intValue();
            message.append(cur);
        }
        System.out.println(name2 + " получил сообщение от " + name + ": " + message);

        try {

            FileWriter writer = new FileWriter("res/Messages.txt", true);
            writer.write(name2 + " получил сообщение от " + name + ": " + message + "\n");
            writer.close();
        } catch (Exception ex) {
            System.err.println("Ошибка в файле!");
            System.exit(1);
        }

    }

    private Map<String, BigInteger> ReadFile(String filename){

        Map<String, BigInteger> key = new HashMap<>();

        try{
            File file=new File(filename);
            FileInputStream fis=new FileInputStream(file);
            ObjectInputStream ois=new ObjectInputStream(fis);

            key =(HashMap<String,BigInteger>) ois.readObject();

            ois.close();
            fis.close();
        }catch (FileNotFoundException ex){
            System.err.println("Файл не найден!");
        }catch (Exception ex){
            System.err.println("Ошибка в файле!");
            System.exit(1);
        }
        return key;
    }

    private void WriteFile(String filename, Map<String, BigInteger> key){


        try{
            File file=new File(filename);
            FileOutputStream fos=new FileOutputStream(file);
            ObjectOutputStream oos=new ObjectOutputStream(fos);

            oos.writeObject(key);
            oos.close();
            fos.close();
        }catch(Exception ex){
            System.err.println("Ошибка в файле!");
            System.exit(1);
        }
    }

}
