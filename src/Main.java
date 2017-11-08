import java.util.List;

public class Main {

    public static void main(String[] args) {
        String input = "data/2000-2010";
        String output = "2000-2010/";
        PaperRead pr = new PaperRead();
        List<Paper> paperList = pr.DataRead(input);
        pr.PaperWrite(paperList,output);


    }
}
