package ads;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by USER on 28.05.15.
 */
public class ReadData {
    private List<String[]> lines;
    private int capacity = 4;
    private int numOfPairs = 6;
    public ReadData(String way, int capacity){
        this.capacity = capacity;
//        int Min =0;
//        int Max = 15;
//        if(capacity == 4){
//            Max = 15;
//        }
//        lines = new ArrayList<String []>();
//       String [] pair = new String[2];
//        Random rand = new Random();
//        for(int i = 0; i < numOfPairs; i++){
//            String number = Integer.toBinaryString(rand.nextInt(Max + 1) + Min);
//            while(number.length() != capacity+1){
//                number = "0"+ number;
//            }
//            pair[0] = number;
//            number = Integer.toBinaryString(rand.nextInt(Max + 1) + Min);
//            while(number.length() != capacity+1){
//                number = "0"+ number;
//            }
//            pair[1] = number;
//            lines.add(pair);
//        }
       BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(way));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        lines = new ArrayList<String []>();
        try {
           // capacity = Integer.parseInt(reader.readLine());
            while ((line = reader.readLine()) != null) {
                String[] pair =  line.trim().split("\\s+");
                lines.add(pair);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String []> getData(){
        return lines;
    }
}
