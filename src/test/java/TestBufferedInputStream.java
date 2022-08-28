import java.io.*;
import java.util.Objects;

public class TestBufferedInputStream {
    public static void main(String[] args) throws IOException {
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("C:\\Users\\betha\\Documents\\tt.csv"));
        BufferedReader r = new BufferedReader(new FileReader("C:\\Users\\betha\\Documents\\tt.csv"));
        int i = 0;
        String s = r.readLine();
        while(s!=null&&!Objects.equals(s,"")){
            System.out.println(i++);
            System.out.println(s);
            s = r.readLine();
        }

    }
}
