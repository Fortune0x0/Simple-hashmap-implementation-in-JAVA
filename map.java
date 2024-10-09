package map;


 class Node <K, V>{
    K key;
    V value;
    int hash;
    Node<K, V> next;

    public Node(K key, V value, int hash,  Node<K, V> next){
        this.next = next;
        this.key = key;
        this.value = value;
        this.hash = hash;

    }
    

}
public class map <K, V>{
    float loadFactor = 0.75f;
    int capacity = 16;
    int entries = 0;
   


    Node<K, V> table [];
    
    @SuppressWarnings("unchecked")

    public map (){
        table = (Node<K, V>[]) new Node[capacity];
        
       
    }
    public void getter(){
        for(int i = 0; i < table.length; ++i){
            Node<K, V> current = table[i];
            while (current != null) {
                System.out.println(current.key);
                current = current.next;
                System.out.println("I is: " + i);
                
            }

        }
    }
    public void resize(){
        capacity*= 2;
        Node<K, V> [] oldTable = table;
        entries = 0;
        table = (Node<K, V>[]) new Node[capacity];
        for(int i = 0; i < oldTable.length; ++i){
            Node<K, V> current = oldTable[i];
            while(current != null){
                put(current.key, current.value);
                current = current.next;

            }
            
           

        }
        


    }
    public void printCapacity(){System.out.println("Capacity: " + capacity + " " + "Entries: " + entries);}
    public void put(K key, V value){
       if(entries +1 >= (capacity * loadFactor )) resize();
      
        int hash = key.hashCode();
        int bucketIndex  =  (table.length -1) & hash;
          
        if(table[bucketIndex] == null){
            table[bucketIndex] = new Node<>(key, value, hash, null);
             ++entries;
            return;
        }
        Node<K, V> current = table[bucketIndex];
        while(true){

            if(current.key == key || current.hash == hash || current.key.equals(key)){
                current.value = value;
                return;
                
            }
            
            if(current.next == null){
                current.next = new Node<>(key, value, hash, null);
                ++entries;
                return;

            }
            

            current = current.next;

        }
       
        


    }
    
    public V get(K key){
        int hash = key.hashCode();
        int bucketIndex  =  (table.length -1) & hash;
        if(table[bucketIndex] == null) return null;

        Node<K, V> current = table[bucketIndex];
        while(true){
            if(current == null) return null; //Incase there is a collision

            if(current.key == key || current.hash == hash || current.key.equals(key)) return current.value;
            

            current = current.next;

        }


        
    }
    
}


