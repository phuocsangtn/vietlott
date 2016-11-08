import luanvm.vietlott.util.FileReaderUtil;

public class Main {

    public static void main(String[] args) {
        System.out.println("Ty Phu sau 7h!");
        String inputFile = "src/source/results.txt";
        FileReaderUtil fileReader = new FileReaderUtil();
        fileReader.readFile(inputFile);
        fileReader.printAllPairs();

    }
}
