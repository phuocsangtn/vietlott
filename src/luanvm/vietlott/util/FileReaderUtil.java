package luanvm.vietlott.util;

import java.io.*;
import java.util.*;

/**
 * Created by luan on 11/8/16.
 */
public class FileReaderUtil {

    TreeMap<String,ArrayList<String>> pairs = new TreeMap<String,ArrayList<String>>();

    public String readFile(String source){
        String result = "";
        try {
            File file = new File(source);
            Reader fileReader = new java.io.FileReader(file);
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while(line != null){
              //  System.out.println(line);
                String[] list = line.split(" ");
                buildPairs(list);
                line = bufferedReader.readLine();
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    private void buildPairs(String[] list){
        int i=0;
        for(String item : list){
            if(pairs.get(item) == null){
                //new key
                ArrayList<String> friends = getFriends(list,i);
                pairs.put(item, friends);

            }else{
                // this item is already existing, then update its friends list.

                ArrayList<String> newFriends = getFriends(list,i);
                addNewFriends(newFriends,item);
            }
            i++;
        }
    }
    private ArrayList<String> getFriends(String[] list, int index){
        ArrayList<String> result = new ArrayList<String>(list.length-1);
       for(int i=0; i<list.length;i++){
           if(i == index)
               continue;
           result.add(list[i]);
       }
        return result;

    }
    private void addNewFriends(ArrayList<String> newFriends, String key){
        if(newFriends != null) {
            ArrayList<String> existingFriends = pairs.get(key);
            existingFriends.addAll(newFriends);
            // pairs.put(key,existingFriends);
        }
    }
    public void printAllPairs(){
        Set<String> keys = pairs.keySet();
        Iterator<String> iterator = keys.iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            ArrayList<String> friends = pairs.get(key);
            System.out.print(key + " :");
            for(String value : friends){
                System.out.print(value + " ");
            }
            System.out.print(System.lineSeparator());
        }
    }
}
