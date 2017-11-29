import demo.ADemo;
import demo.BDemo;

public class P2Drive {
    public static void main(String[] args) {
        ADemo a = new ADemo();
        BDemo b = new BDemo();

        System.out.println(a.data);
        a.hello();
        System.out.println(b.data);
        b.hello();
    }
}
