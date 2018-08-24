package iterator;

import java.util.*;

public class TestFrequency {
	
    public void run() {
        String[] countries ={"KO","DE","ES","FR","DE","ES","DE"};
        List list = Arrays.asList(countries);
        System.out.println("Frequency(list, \"DE\"): " + frequency(list, "DE"));
        System.out.println("Frequency(list, \"KO\"): " + frequency(list, "KO"));
        System.out.println("Frequency(list, \"ES\"): " + frequency(list, "ES"));
        System.out.println("Frequency(list, \"FR\"): " + frequency(list, "FR"));
    }

    int frequency(List list, Object object) {
        
    	Iterator iterator = list.iterator();
        int count=0;
        while (iterator.hasNext()) {
            String currentString = (String) iterator.next();
           
            if(object == currentString) {
                count++;
            }
        }
        return count;
    }
}