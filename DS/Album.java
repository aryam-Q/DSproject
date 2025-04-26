package DS;


public class Album {
    // attributes
       String name;
       String condition;
       PhotoManager manager;
       int totalnbcomp = 0;
    
    
    //constructor
       public Album(String name, String condition, PhotoManager manager){
       
          this.name = name;
          this.condition = condition;
          this.manager = manager;
       }
    
    //return the name of the album
       public String getName(){
       
          return name;
       
       }
    
    //return the condition which determines the album
       public String getCondition(){
       
          return condition;
       
       }
    
    //return the manager
       public PhotoManager getManager(){
       
          return manager;
       }
    
    //method checks if photo p exists in list L by comparing each photos path
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
    
    
    
    
    //search for tag inside LL of tags that belong to a photo if exist return true, counts comparisons 
       public boolean Tag_Exist_in_photo(LinkedList<String>L, String tag){
       
          if(L.empty()) 
             return false;
          L.findFirst();
          while(!L.last())
          {     
             totalnbcomp++;
             if(L.retrieve().equals(tag)){
                return true;
             }
             L.findNext();
              
          }
          totalnbcomp++;
          if(L.retrieve().equals(tag)){
             return true;
          }
          return false; 
       
       }
     
     
     //retrieve photos that match the albums tag condition by intersecting tag specific lists
       public LinkedList<Photo> getPhotos(){
          totalnbcomp = 0;
          LinkedList<Photo>res = new LinkedList<Photo>();
          if(condition==null) 
             return res;
          if(condition.equals("")) 
             return manager.getPhotos();
          String a[] = condition.split("AND");
          for(int i=0; i<a.length ; i++)
          {
             a[i] = a[i].trim();
          }
          LinkedList<Photo>A = getTagPhotos(a[0]);
          for(int i=1 ; i<a.length;i++)
          {
             LinkedList<Photo>B = getTagPhotos(a[i]);
             A=And(A, B);
          }
       
          return A;
       }
      
      
    //return number of comparisons in getPhotos()
       public int getNbComps(){
          return totalnbcomp;
       }
    
    
    //scans all managed photos and collects the ones containing the given tag 
       public LinkedList<Photo> getTagPhotos(String tag)
       {
          LinkedList<Photo>res = new LinkedList<Photo>();
          LinkedList<Photo>allPhotos = manager.getPhotos();
          if(allPhotos.empty()) 
             return res;
          allPhotos.findFirst();
          while(!allPhotos.last())
          {
          
             if(Tag_Exist_in_photo(allPhotos.retrieve().tags,tag)){
                res.insert(allPhotos.retrieve());
             }
               
             allPhotos.findNext();
               
          }
          if(Tag_Exist_in_photo(allPhotos.retrieve().tags,tag)){
             res.insert(allPhotos.retrieve());
          }
          return res;
           
       }
        
        //computes and returns the intersection of two photo lists A and B
       public  LinkedList<Photo> And(LinkedList<Photo>A,LinkedList<Photo>B)
       {
          LinkedList<Photo> result = new LinkedList<Photo>();
          if(A.empty() || B.empty())
             return result;
          A.findFirst();
          while(true){
             boolean found = Photo_Exist(result,A.retrieve());
             if(!found)// not found in result 
             {
                B.findFirst();
                while(true)
                {
                   if(B.retrieve().path.equals(A.retrieve().path))
                   {
                      result.insert(A.retrieve());
                      break;
                   }
                   if(!B.last())
                      B.findNext();
                   else
                      break;
                
                }//end of inner while for B
             
             }//end if not found
             if(!A.last())
                A.findNext();
             else
                break;
          }
          return result;
       }
    
    }