package DS;

public class Photo {
    String path ;
    LinkedList<String> tags ;
 
    public Photo (String path , LinkedList<String> tags) {
       this.path = path ;
       this.tags = tags ;
    }
 
    public String getPath() {
       return path ;
    }
    public LinkedList<String> getTags() {
       return tags ;
    }
 
    public void displayPhoto() {
       System.out.print("path = " + path) ;
       System.out.print("tags of this photo + ") ;
       tags.display();
       System.out.print("\n_______________________") ;
    }
    
 }