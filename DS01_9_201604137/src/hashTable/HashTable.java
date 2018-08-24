package hashTable;

public class HashTable {
	private Entry[] entries;
	private int size, used; // used: ��� �� ����� �ƾ�!
	private float loadFactor; // �޸��� 4����3������ �����ϰ� �Ǹ� �޸𸮸� �ø��� �ϱ����� ��ǥ
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
		// ���࿡ ���� �޸��� ������ entries�迭�� ũ��*0.75 ���� ũ�ٸ�
		// �浹 �� Ȯ���� �������Ƿ� rehash�� ���� �޸��� ũ�⸦ �÷����´�.
		if (used > loadFactor * entries.length)
			rehash();
		int h = hash(key); // key���� hash�޼ҵ忡 �ְ� ��ȯ�� ���� h�� ����
		if (h == -1)
			return null; // �ش�Ǵ°� ������ null ��ȯ

		for (int i = 0; i < entries.length; i++) {
			int j = nextProbe(h, i); //�浹 �� ����Ž���ϴ� �κ�
			Entry entry = entries[j];
			// �� ����
			if (entry == null) {
				System.out.println("[DEBUG] put," + j);
				entries[j] = new Entry(key, value);
				++size;
				++used;	//�޸��� j��°�� ��� �� ��� ��
				return null;
			}
			// �� ���� (key���� ������ hash���� �ٸ���.)
			if (entry.key.equals(key)) {
				Object oldValue = entry.value;
				entries[j].value = value;
				return oldValue;
			}
			// if���� ���ǵ��� �� �ȸ����� ���� key���� ������ value�� �ٸ��ٴ� �ǹ�==�浹
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
			//key���� null�� �ƴѵ� value�� �ٸ��� �浹
			System.out.println("[DEBUG] Hash Collision,get" + j);
		}
		return null;
	}

	
	//�浹�� ����Ž���� �ϴ� �޼ҵ�
	private int nextProbe(int h, int i) {
		return (h + i) % entries.length;
	}

	private void rehash() {
		Entry[] oldEntries = entries;
		entries = new Entry[2 * oldEntries.length + 1]; //entries�� ũ�⸦ �÷���
		for (int k = 0; k < oldEntries.length; k++) {
			Entry entry = oldEntries[k]; //ũ�⸦ �ø��� �� �迭�� ������ �ϳ��� entry�� �ְ�
			if (entry == null)	//null�̸� for�� ����������
				continue;
			int h = hash(entry.key); //null�� �ƴϸ� �� �ε��� Ű ���� �ؽ���
			for (int i = 0; i < entries.length; i++) {
				int j = nextProbe(h, i);//����Ž��
				if (entries[j] == null) { //���̺��� j�κ��� ���������
					entries[j] = entry;	//�� ����
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
