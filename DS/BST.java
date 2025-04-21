package DS;

class BSTNode <T> {
    public String Key ;
    public T data ;
    public BSTNode<T> left , right ;

    public BSTNode (String Key , T data){
        this.Key = Key ;
        this.data = data ;
        left = right = null ;
    }
}
    public class BST<T> {
        private BSTNode<T> root , current ;

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

        public boolean findKey (String k) {
            BSTNode<T> p = root ;

            while ( p != null){
                current = p ;

                if (k.compareTo(p.Key) == 0){
                    return true ;
                } else if (k.compareTo(p.Key) < 0){
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
               if (k.compareTo(current.Key) < 0) {
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
                    if (k1.compareTo(p.Key) < 0) {
                        q = p ;
                        p = p.left ;
                    }else if (k1.compareTo(p.Key) > 0){
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
                        p.Key = min.Key ;
                        p.data = min.data ;
                        k1 = min.Key ;
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
                        if (k1.compareTo(q.Key) < 0) {
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
    }
        
