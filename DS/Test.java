package DS;

public class Test {

    public static void main(String[] args) {
    
    
      //basic PhotoManager setup
       PhotoManager manager = new PhotoManager();
    
       Photo photo1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
       manager.addPhoto(photo1);
    
       Photo photo2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
       manager.addPhoto(photo2);
    
       Photo photo3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
       manager.addPhoto(photo3);
    
       
       // Album creation, test linear album
       Album album1 = new Album("Album1", "bear", manager);
       Album album2 = new Album("Album2", "animal AND grass", manager);
    
       System.out.println("Get photo1 path and tags:");
       System.out.println("photo1 path: " + photo1.getPath());
       photo1.tags.display();
    
       System.out.println("Get album2 name and photos:");
       System.out.println(album2.getName() + " -> " + album2.getCondition());
       displayListOfPhoto(album2.getPhotos());
    
       manager.deletePhoto("bear.jpg");  // remove bear
       
      
       // testing the inverted-index 
       //-----------------------------
       System.out.println("\n=== Inverted-Index Album Test ===");
    
       InvIndexPhotoManager invMgr = new InvIndexPhotoManager();
       Photo p1 = new Photo("hedgehog.jpg", toTagsLinkedList("animal, hedgehog, apple, grass, green"));
       Photo p2 = new Photo("bear.jpg", toTagsLinkedList("animal, bear, cab, grass, wind"));
       Photo p3 = new Photo("orange-butterfly.jpg", toTagsLinkedList("insect, butterfly, flower, color"));
       invMgr.addPhoto(p1);
       invMgr.addPhoto(p2);
       invMgr.addPhoto(p3);
    
       Album_inverted_index invAlbum = new Album_inverted_index("InvAlbum", "animal AND grass", invMgr);
       System.out.println("\nInv index " + invAlbum.getCondition() + ":");
       displayListOfPhoto(invAlbum.getPhotos());
       System.out.println("BST comps: " + invAlbum.getNbComps());        
       
    // empty condition should return all photos
       Album_inverted_index allInv = new Album_inverted_index("All", "", invMgr);
       System.out.println("\nAll photos:");
       displayListOfPhoto(allInv.getPhotos());
       System.out.println("BST comps: " + allInv.getNbComps());
    
    // Test: a tag that doesn’t exist should yield an empty list 
       Album_inverted_index noneAlbum = new Album_inverted_index("NoMatch", "unicorn", invMgr);
       System.out.println("\nPhotos matching \"unicorn\":");
       displayListOfPhoto(noneAlbum.getPhotos());
       System.out.println("BST comparisons used: " + noneAlbum.getNbComps());
       
       
    //delete and retest
    // 1. Delete “bear.jpg” from the inverted manager
       invMgr.deletePhoto("bear.jpg");
    
    // 2. Now ask for the “bear” album
       Album_inverted_index postDel = new Album_inverted_index("PostDel", "bear", invMgr);
       System.out.println("\nAfter deletion, photos matching \"bear\":");
       displayListOfPhoto(postDel.getPhotos());
       System.out.println("BST comparisons used: " + postDel.getNbComps());
    
    
    // linear extra tests: 
       Album linAll = new Album("LinAll", "", manager);
       System.out.println("\nLinAll empty:");
       displayListOfPhoto(linAll.getPhotos());
       System.out.println("Lin comps: " + linAll.getNbComps());
    
       Album linNone = new Album("LinNone", "unicorn", manager);
       System.out.println("\nLinNone none:");
       displayListOfPhoto(linNone.getPhotos());
       System.out.println("Lin comps: " + linNone.getNbComps());       
    }
 
 
    //helper to build tags list   
    private static LinkedList<String> toTagsLinkedList(String tags) {
       LinkedList<String> result = new LinkedList<String>();
       String[] tagsArray = tags.split("\\s*,\\s*");
       for (int i = 0; i < tagsArray.length; i++) {
          result.insert(tagsArray[i]);
       }
       return result;
    }
 
  // prints photo paths or empty lists
    public static void displayListOfPhoto(LinkedList<Photo> L) {
       if (L == null) {
          System.out.println("null List");
       } else if (L.empty()) {
          System.out.println("empty list");
       } else {
          L.findFirst();
          while (!L.last()) {
             System.out.print("\n" + L.retrieve().path + " ");
             L.findNext();
          }
          System.out.println("\n" + L.retrieve().path + " ");
          System.out.println("------------------------------");
       }
    }
 }
 
