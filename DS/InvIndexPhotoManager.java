package DS;


public class InvIndexPhotoManager {
    BST<LinkedList<Photo>>index;
    LinkedList<Photo>allPhotos;
    public int nbtags=0;
 
 //constructor
    public InvIndexPhotoManager(){
       index=new BST<LinkedList<Photo>>();
       allPhotos = new LinkedList<Photo>();
    }
 
 //add a photo
    public void addPhoto(Photo p)
    {
       if(Photo_Exist(allPhotos, p)) 
          return; //to prevent repetition
       allPhotos.insert(p);
       LinkedList<String>tags= p.getTags();
       if(tags.empty()) 
          return; //additional
       tags.findFirst();
       while(!tags.last())
       {
          String curTag = tags.retrieve();
          boolean found = index.findKey(curTag);
          if(!found)
          {
             LinkedList<Photo>curPhotos = new LinkedList<Photo>();
             curPhotos.insert(p);
             index.insert(curTag, curPhotos);
             nbtags++;
          }
          else
          {
             LinkedList<Photo>curPhotos = index.retrieve();
             curPhotos.insert(p);
          }
          tags.findNext();  
       
       }
    
    //for last element 
       String curTag = tags.retrieve();
       boolean found = index.findKey(curTag);
       if(!found)
       {
          LinkedList<Photo>curPhotos = new LinkedList<Photo>();
          curPhotos.insert(p);
          index.insert( curTag, curPhotos);
       }
       else
       {
          LinkedList<Photo>curPhotos = index.retrieve();
          curPhotos.insert(p);
       } 
    }
 //end of method add
    public void remove_From_List(LinkedList<Photo>L, Photo p)
    {
       if(L.empty()) 
          return;
       L.findFirst();
       while(!L.empty() && !L.last()){
          if(L.retrieve().path.equals(p.path))
             L.remove();
          else
             L.findNext();
       }
       if(!L.empty() && L.retrieve().path.equals(p.path))
          L.remove();
    }
 
 //delete a photo--> gets tags of photo by the path and goes through all the tags of that photo
    public void deletePhoto(String path){
    // 1- retrieve the photo wiht the path
       if(allPhotos.empty()) 
          return;
       LinkedList<String>tags = null;
       allPhotos.findFirst();
       while(!allPhotos.last())
       {
          if(allPhotos.retrieve().path.equals(path)){
             tags = allPhotos.retrieve().tags;
             allPhotos.remove();
             break;
          }
          else
             allPhotos.findNext();
       }
       if(allPhotos.retrieve().path.equals(path)){
          tags = allPhotos.retrieve().tags;
          allPhotos.remove();
       }
     
     //2- deleting
       if(tags==null || tags.empty()) 
          return;
       Photo ourPhoto = new Photo(path, tags);
       tags.findFirst();
       while(!tags.last()){
          if(index.findKey(tags.retrieve())){
             remove_From_List(index.retrieve(), ourPhoto );
             if(index.retrieve().empty())
                index.removeKey(tags.retrieve());
             nbtags--;
          }
          tags.findNext();
       }
       if(index.findKey(tags.retrieve())){
          remove_From_List(index.retrieve(), ourPhoto);
          if(index.retrieve().empty())
             index.removeKey(tags.retrieve());
          nbtags--;
       }
      
    }
   //end of delete  
   
  
 
 
 //return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getPhotos(){
       return index;
    
    }
 
 //helping method
    public boolean Photo_Exist(LinkedList<Photo>L, Photo p){
    
       if(L.empty()) 
          return false;
       L.findFirst();
       while(!L.last())
       {
          if(L.retrieve().path.equals(p.path))
             return true;
          L.findNext();
       
       }
       if(L.retrieve().path.equals(p.path))
          return true;
       return false;
    }
 
 //
 
 // Returns the full list of managed photos (unsorted).
    public LinkedList<Photo> getAllPhotos() {
       return allPhotos;
    }
 
 
 
 
 }