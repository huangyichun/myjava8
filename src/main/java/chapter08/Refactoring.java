package chapter08;



/**
 * @author huangyichun
 * @date 2018/12/14
 */
public class Refactory {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };
        new Thread(runnable).start();

        Runnable runnable1 = () -> System.out.println("hello");
        new Thread(runnable1).start();

        int a = 10;
       /* Runnable r1 = () -> {
            int a = 2;
            System.out.println(a);
        };*/
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                int a = 2;
                System.out.println(a);
            }
        };
        new Thread(r2).start();
    }

    public static void doSomething(Runnable r) {
        r.run();
    }

    public static void doSomething(Task a) {
        a.execute();
    }
}
