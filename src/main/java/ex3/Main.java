package ex3;

public class Main {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable();

        // Put some key values.
        for (int i = 0; i < 30; i++) {
            final String key = String.valueOf(i);
            hashTable.put(key, key);
        }

        // Print the HashTable structure
        hashTable.log("****   HashTable  ***");
        hashTable.log(hashTable.toString());
        hashTable.log("\nValue for key(20) : " + hashTable.get("20"));
    }
}
