package hashTable;

import java.util.Scanner;

public class TestHashTable {
	//capacity(�޸� �뷮),loadFactor ����
	HashTable ht = new HashTable(4, 0.75F);
	
	public TestHashTable() {
		while(true) {
			System.out.println("1. [Hash] put ");
			System.out.println("2. [Hash] get ");
			System.out.println("3. ����");
			
			Scanner scan = new Scanner(System.in);
			System.out.print("�Է�>>>");
			int num = scan.nextInt();
			
			switch(num) {
			case 1:
				System.out.print("key�� �Է��ϼ���:");
				String key=scan.next();
				System.out.print("������ �Է��ϼ���:");
				String country=scan.next();
				System.out.print("�� �Է��ϼ���:");
				String lang=scan.next();
				
				Country cnty1 = (Country)ht.put(key, new Country(country,lang));
				if(cnty1!=null) System.out.println("������: "+cnty1.toString()); 
				break;
			case 2:
				System.out.print("key�� �Է��ϼ���:");
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
