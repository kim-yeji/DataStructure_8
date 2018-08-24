package hashTable;

public class HashTable {
	private Entry[] entries;
	private int size, used; // used: 방금 막 사용이 됐어!
	private float loadFactor; // 메모리의 4분의3정도를 차지하게 되면 메모리를 늘리게 하기위한 지표
	private final Entry NIL = new Entry(null, null);

	private class Entry {
		Object key, value;

		Entry(Object k, Object v) {
			key = k; // KR,FI, ...
			value = v; // Country Class(country,language)
		}
	}

	public HashTable(int capacity, float loadFactor) {
		this.entries = new Entry[capacity];
		this.loadFactor = loadFactor;
	}


	public Object put(Object key, Object value) {
		// 만약에 사용된 메모리의 공간이 entries배열의 크기*0.75 보다 크다면
		// 충돌 할 확률이 높아지므로 rehash를 통해 메모리의 크기를 늘려놓는다.
		if (used > loadFactor * entries.length)
			rehash();
		int h = hash(key); // key값을 hash메소드에 넣고 반환된 값을 h에 저장
		if (h == -1)
			return null; // 해당되는게 없으면 null 반환

		for (int i = 0; i < entries.length; i++) {
			int j = nextProbe(h, i); //충돌 시 선형탐색하는 부분
			Entry entry = entries[j];
			// 값 생성
			if (entry == null) {
				System.out.println("[DEBUG] put," + j);
				entries[j] = new Entry(key, value);
				++size;
				++used;	//메모리의 j번째가 방금 막 사용 됨
				return null;
			}
			// 값 수정 (key값은 같은데 hash값이 다르다.)
			if (entry.key.equals(key)) {
				Object oldValue = entry.value;
				entries[j].value = value;
				return oldValue;
			}
			// if문의 조건들이 다 안맞으면 같은 key값은 있으나 value가 다르다는 의미==충돌
			System.out.println("[DEBUG] Hash Collision,put" + j);
		}
		return null;
	}

	public Object get(Object key) {
		int h = hash(key);
		if (h == -1)
			return null;

		for (int i = 0; i < entries.length; i++) {
			int j = nextProbe(h, i);
			Entry entry = entries[j];
			if (entry == null)
				break;
			if (entry.key.equals(key)) {
				System.out.println("[DEBUG] get," + j);
				return entry.value;
			}
			//key값이 null이 아닌데 value가 다르면 충돌
			System.out.println("[DEBUG] Hash Collision,get" + j);
		}
		return null;
	}

	
	//충돌시 선형탐색을 하는 메소드
	private int nextProbe(int h, int i) {
		return (h + i) % entries.length;
	}

	private void rehash() {
		Entry[] oldEntries = entries;
		entries = new Entry[2 * oldEntries.length + 1]; //entries의 크기를 늘려줌
		for (int k = 0; k < oldEntries.length; k++) {
			Entry entry = oldEntries[k]; //크기를 늘리기 전 배열의 값들을 하나씩 entry에 넣고
			if (entry == null)	//null이면 for문 빠져나가기
				continue;
			int h = hash(entry.key); //null이 아니면 그 인덱스 키 값을 해싱함
			for (int i = 0; i < entries.length; i++) {
				int j = nextProbe(h, i);//선형탐색
				if (entries[j] == null) { //테이블의 j부분이 비어있으면
					entries[j] = entry;	//값 삽입
					break;
				}
			}
		}
		used = size;
	}

	private int hash(Object key) {
		if (key == null)
			throw new IllegalArgumentException();

		if (key.equals("KR"))
			return 0;
		else if (key.equals("FI"))
			return 0;
		else if (key.equals("IQ"))
			return 1;
		else if (key.equals("IR"))
			return 2;
		else if (key.equals("SK"))
			return 3;
		else if (key.equals("CA"))
			return 3;
		else
			throw new IllegalArgumentException();
	}

}
