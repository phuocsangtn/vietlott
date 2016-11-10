package luanvm.vietlott.util;

import java.io.*;
import java.util.*;

/**
 * Created by luan on 11/8/16.
 */
public class FileReaderUtil {

    TreeMap<String,ArrayList<String>> pairs = new TreeMap<String,ArrayList<String>>();
    private int startLine = 46;// lan quay thu may

    public String readFile(String source){
        String result = "";
        try {
            File file = new File(source);
            Reader fileReader = new java.io.FileReader(file);
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int count = 1;
            while(line != null){
                if(count > startLine)
                    break;
                String[] list = line.split(" ");
                buildPairs(list);
                line = bufferedReader.readLine();
                count++;
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
    /*
        return the best number that have the most appearances with the given number.
        return 1 con so hay ra nhat voi con so dc chon
     */
    public String getBestFriendOf(String key){
        ArrayList<String> friends = pairs.get(key);
        ArrayList<String> worstList = getMostFrequents(friends);
        return worstList.toString();
    }
    /*
        danh sach nhung con so chua bao gio di cung voi key
     */
    public ArrayList<String> getWorstFriends(String key){
        ArrayList<String> result = new ArrayList<String>(45); //maxinum is 45 numbers
        ArrayList<String> friends = pairs.get(key);
        for(int i =1; i<=45;i++){
            String obj = String.valueOf(i);
            if(i <10)
                obj = "0" +String.valueOf(i);

            if(!friends.contains(obj)){
                result.add(obj);
            }
        }
        return result;
    }
    private ArrayList<String> getMostFrequents(ArrayList<String> list){
        ArrayList<String> temp = new ArrayList<String>(list.size());
        int count=0;
        TreeMap<String,Integer> frequent = new TreeMap<String,Integer>();
        for(int i=0; i<list.size();i++){
            if(frequent.containsKey(list.get(i))){
                Integer newValue = frequent.get(list.get(i)) + 1;
                frequent.put(list.get(i), newValue);
            }else{
                frequent.put(list.get(i), 1);
            }
        }
        System.out.println(frequent);
        int max=0;
        for(int i=0; i<list.size();i++){
            if(frequent.get(list.get(i)) > max){
                max = frequent.get(list.get(i));
            }
        }
        count++;

        Set<String> keys = frequent.keySet();
        Iterator<String> iterator = keys.iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            if(frequent.get(key) >= max){
                temp.add(key);
            }
        }
        return temp;
    }
}
