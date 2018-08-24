package hashTable;

import java.util.Scanner;

public class TestHashTable {
	//capacity(메모리 용량),loadFactor 설정
	HashTable ht = new HashTable(4, 0.75F);
	
	public TestHashTable() {
		while(true) {
			System.out.println("1. [Hash] put ");
			System.out.println("2. [Hash] get ");
			System.out.println("3. 종료");
			
			Scanner scan = new Scanner(System.in);
			System.out.print("입력>>>");
			int num = scan.nextInt();
			
			switch(num) {
			case 1:
				System.out.print("key를 입력하세요:");
				String key=scan.next();
				System.out.print("국가를 입력하세요:");
				String country=scan.next();
				System.out.print("언어를 입력하세요:");
				String lang=scan.next();
				
				Country cnty1 = (Country)ht.put(key, new Country(country,lang));
				if(cnty1!=null) System.out.println("기존값: "+cnty1.toString()); 
				break;
			case 2:
				System.out.print("key를 입력하세요:");
				String SearchKey=scan.next();
				Country cnty2 = (Country)ht.get(SearchKey);
				System.out.println(cnty2.toString());
				
				break;
			case 3:
				break;
			}
		}
	}
	

	
	
	
	public static void main(String[] args) {
		new TestHashTable();
	}
}
