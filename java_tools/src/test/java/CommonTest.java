import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class CommonTest {

    @Test
    public void listTest() {
        ArrayList<Integer> arrayList = new ArrayList<>(10);
        LinkedList<Integer> linkedList = new LinkedList<Integer>();
        for (int i = 0; i <= 10; i++) {
            arrayList.add(i);
            if(i % 2 == 0) {
                linkedList.add(null);
            } else {
                linkedList.add(i);
            }
        }
        long l1 = System.currentTimeMillis();
        Integer integer = arrayList.get(10);
        long l2 = System.currentTimeMillis();
        Integer integer1 = linkedList.get(10);
        long l3 = System.currentTimeMillis();
        System.out.println(l2 - l1);
        System.out.println(l3 - l2);
    }
}
