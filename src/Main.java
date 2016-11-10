import luanvm.vietlott.util.FileReaderUtil;

import java.util.ArrayList;

public class Main {

    private FileReaderUtil fileReader = new FileReaderUtil();

    public void getWorstCases(String key){
        ArrayList<String> result = fileReader.getWorstFriends("13");
        System.out.println(result);
    }

    public static void main(String[] args) {
        System.out.println("Ty Phu sau 7h!");
        String inputFile = "src/source/results.txt";
        Main main = new Main();

        FileReaderUtil fileReader = new FileReaderUtil();
        fileReader.readFile(inputFile);
        //fileReader.printAllPairs();
        String key = "13";
        String result = fileReader.getBestFriendOf(key);
        System.out.println("Best number  of " + key +  "is " + result);

        ArrayList<String> worstFriends = fileReader.getWorstFriends("13");
        System.out.println("Worst numbers of " + key +" is " + worstFriends);

    }
}
