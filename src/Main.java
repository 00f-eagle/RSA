import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        Alice alice = new Alice();
        Bob bob = new Bob();

        List<String> users = new ArrayList<>();


        listCommand();

        boolean bool = true;
        try {

            BufferedReader reader = new BufferedReader(new FileReader("res/Users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(line);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Файл не найден!");
        } catch (IOException ex){
            System.err.println("Ошибка!");
        }

        try {

            FileWriter writer = new FileWriter("res/Users.txt", true);

            while (bool) {
                System.out.println("\nВведите команду:");
                System.out.print(">>> ");
                String string = in.nextLine();
                String name;
                String name_1;
                switch (string) {
                    case "add":
                        System.out.println("\nВведите имя пользователя:");
                        System.out.print(">>> ");
                        name = in.nextLine();
                        boolean add = true;
                        for (int i = 0; i < users.size(); i++) {
                            if (users.get(i).equals(name)) {
                                System.out.println("Данное имя пользователя занято!");
                                add = false;
                                break;
                            }
                        }
                        if(add) {
                            writer.write(name);
                            writer.append('\n');
                            users.add(name);
                            alice.getKey(name);
                        }
                        break;
                    case "list":
                        for (int i = 0; i < users.size(); i++)
                            System.out.println("\n>" + users.get(i));
                        break;
                    case "message":
                        System.out.println("\nКто хочет отправить сообщение?");
                        System.out.print(">>> ");
                        name_1 = in.nextLine();
                        boolean message = false;
                        for (int i = 0; i < users.size(); i++) {
                            if(users.get(i).equals(name_1)) {
                                message = true;
                                break;
                            }
                        }
                        if(!message){
                            System.out.println("Данный пользователь не зарегистрирован!");
                            break;
                        }
                        message = false;
                        System.out.println("Кому отправить сообщение?");
                        System.out.print(">>> ");
                        name = in.nextLine();
                        for (int i = 0; i < users.size(); i++) {
                            if(users.get(i).equals(name)) {
                                message = true;
                                break;
                            }
                        }
                        if(!message){
                            System.out.println("Данный пользователь не зарегистрирован!");
                            break;
                        }else{
                            bob.Message(alice.getOpen_key_n(name), alice.getOpen_key_e(name));
                            alice.getMessage(bob.getC_message(), name, name_1);
                        }
                        break;
                    case "open key":
                        System.out.println("\nВведите имя пользователя:");
                        System.out.print(">>> ");
                        name = in.nextLine();
                        boolean open_key = false;
                        for (int i = 0; i < users.size(); i++) {
                            if (users.get(i).equals(name)) {
                                System.out.println("{" + alice.getOpen_key_e(name) + "; " + alice.getOpen_key_n(name) + "}");
                                open_key = true;
                                break;
                            }
                        }
                        if(!open_key) {
                            System.out.println("Данный пользователь не зарегистрирован!");
                        }
                        break;
                    case "?":
                        listCommand();
                        break;
                    case "exit":
                        bool = false;
                        break;
                    default:
                        System.out.println("Вы ввели команду неправильно!");
                        break;

                }
            }

            writer.close();
        } catch (Exception ex) {
            System.err.println("Ошибка в файле!");
            System.exit(1);
        }


    }

    private static void listCommand() {
        System.out.println("\nСписок команд:");
        System.out.println("'add' - добавить пользователя");
        System.out.println("'list' - список пользователей");
        System.out.println("'message' - отправить сообщение");
        System.out.println("'open key' - получить открытый ключ");
        System.out.println("'?' - список команд");
        System.out.println("'exit' - выйти");
    }

}
