import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Book> LIST;

    public Main() {
    }

    public static void main(String[] args) {
        System.out.println(" ");
        System.out.println("初始化中，先等个一万年哦，亲~");
        load();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    insert(scanner);
                    break;
                case 2:
                    list(scanner);
                    break;
                case 3:
                    modify(scanner);
                    break;
                case 4:
                    delete(scanner);
                    break;
                case 5:
                    System.out.println("系统正在保存，等亿会会....");
                    save();
                    System.out.println("结束，再见104");
                    return;
            }
        }
    }

    private static void showMenu() {
        System.out.println("==========图书管理系统==========");
        System.out.println("1. 录入书籍信息");
        System.out.println("2. 查询书籍信息");
        System.out.println("3. 修改书籍信息");
        System.out.println("4. 删除书籍信息");
        System.out.println("5. 退出系统");
        System.out.println("==============================");
        System.out.println(" ");
    }

    private static void load() {
        File file = new File("date");
        if (file.exists()) {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("date"))) {
                LIST = (List<Book>) input.readObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
                System.out.println("加载数据失败！");
                System.exit(1);
            }
        } else {
            LIST = new LinkedList<>();
        }
    }

    private static void save() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("date"))) {
            output.writeObject(LIST);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insert(Scanner scanner) {
        scanner.nextLine();
        System.out.print("请输入书籍的名称:");
        String title = scanner.nextLine();
        System.out.print("请输入书籍的作者:");
        String author = scanner.nextLine();
        System.out.print("请输入书籍的价格:");
        int price = scanner.nextInt();
        scanner.nextLine();
        Book book = new Book(title, author, price);
        LIST.add(book);
        System.out.println(book);
        System.out.println("书籍录入成功。");
    }

    private static void list(Scanner scanner) {
        for (int i = 0; i < LIST.size(); ++i) {
            System.out.println(i + 1 + "." + LIST.get(i));
        }
    }

    private static void modify(Scanner scanner) {
        scanner.nextLine();
        System.out.print("告诉我想要修改书籍的序号:");

        while (true) {
            int xueLie = scanner.nextInt();
            scanner.nextLine();

            if (xueLie >= 1 && xueLie <= LIST.size()) {
                Book book = LIST.get(xueLie - 1);
                System.out.print("请输入书籍的新名称:");
                book.setTitle(scanner.nextLine());
                System.out.print("请输入书籍的新作者:");
                book.setAuthor(scanner.nextLine());
                System.out.print("请输入书籍的新价格:");
                book.setPrice(scanner.nextInt());
                scanner.nextLine();
                System.out.println("改完了，回去看看吧~");
                break;
            } else {
                System.out.println("没这书，重输！");
            }
        }
    }

    private static void delete(Scanner scanner) {
        System.out.print("告诉我想要删除书籍的序号:");

        while (true) {
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index >= 1 && index <= LIST.size()) {
                LIST.remove(index - 1);
                System.out.println("这书没啦！");
                break;
            } else {
                System.out.println("没这书，重输！");
            }
        }
    }
}
