package DS;

class BSTNode <T> {
    public String key ;
    public T data ;
    public BSTNode<T> left , right ;
 
    public BSTNode (String key , T data){
       this.key = key ;
       this.data = data ;
       left = right = null ;
    }
 }
 
 public class BST<T> {
    private BSTNode<T> root , current ;
    int num_comp = 0;
 
    public BST () {
       current = root = null ;
    }
 
    public void clear() {
       current = root = null ;
    }
 
    public boolean empty() {
       return root == null ; 
    }
 
    public boolean full() {
       return false ;
    }
 
    public T retrieve() {
       return current.data ;
    }
         
         
         //method searches for given key k in BST 
         //counts each comparison in num_comp and sets current to matching node
         //return true if key is found 
    public boolean findKey1(String k){
         
       BSTNode<T> p = root;
       while(p != null) {
          num_comp++;
          current = p;
          if( k.compareToIgnoreCase(p.key)==0) {
             return true;
          } else if( k.compareToIgnoreCase(p.key)<0){
             p = p.left ;
          } else {
             p = p.right ;
          }
       }
       return false ;
    }
         
        
        //basic search for key without counting comparisons
        //sets current to the node if found 
        //return true if key exists
    public boolean findKey (String k) {
       BSTNode<T> p = root ;
    
       while ( p != null){
          current = p ;
       
          if (k.compareToIgnoreCase(p.key) == 0){
             return true ;
          } else if (k.compareToIgnoreCase(p.key) < 0){
             p = p .left ;
          } else {
             p = p.right ;
          }
       }
       return false ;
    }
 
    public boolean insert (String k , T val) {
       if (root == null) {
          current = root = new BSTNode<T>(k ,val) ;
          return true ;
       }
    
       BSTNode<T> p = current ;
       if (findKey(k)) {
          current = p ;
          return false ;
       } 
    
       BSTNode<T> tmp = new BSTNode<T>(k , val) ;
       if (k.compareTo(current.key) < 0) {
          current.left = tmp ;
       } else {
          current.right = tmp ;
       }
       current = tmp ;
       return true ;
    }
 
    public boolean removeKey (String k) {
    
       String k1 = k ;
       BSTNode<T> p = root ;
       BSTNode<T> q = null ;
    
       while (p != null) {
          if (k1.compareTo(p.key) < 0) {
             q = p ;
             p = p.left ;
          }else if (k1.compareTo(p.key) > 0){
             q = p ;
             p = p .right ;
          }else {
             if ((p.left != null) && (p.right != null)) {
                BSTNode<T> min = p.right ;
                q = p ;
                while (min.left != null){
                   q = min ;
                   min = min.left ;
                }
                p.key = min.key ;
                p.data = min.data ;
                k1 = min.key ;
                p = min ;
             }
             if (p.left != null) {
                p = p.left ;
             }else {
                p = p.right ;
             }
          
             if (q == null){
                root = p ;
             }else {
                if (k1.compareTo(q.key) < 0) {
                   q.left = p ;
                }else {
                   q.right = p ;
                }
             }
             current = root ;
             return true ;
          }
       }
       return false ;
    }
    
         
   //prints pths of all photos in given list if list is empty print empty list..
    public void displayListOfPhoto(LinkedList<Photo>L){
       if(L.empty())   { System.out.println("empty list");}
       else
       {
          L.findFirst();
          while(!L.last())
          {
             System.out.print(L.retrieve().path +" ");
             L.findNext();
          }
       
          System.out.println(L.retrieve().path +" ");
          System.out.println("-----------------------------");
       }
    }
  
  
 }
 
  
 
 