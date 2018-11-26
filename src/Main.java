public class Main {

    public static void main(String[] args){


        Alice alice = new Alice();

        Bob bob = new Bob(alice.getN(), alice.getE());

        bob.Message();

        alice.getMessage(bob.getC());

    }

}
