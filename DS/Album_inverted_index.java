package DS;


public class Album_inverted_index{
    //constructors
       String name;
       String condition;
       InvIndexPhotoManager manager;
    
    //constructor
       public Album_inverted_index(String name, String condition, InvIndexPhotoManager manager)
       {
          this.name = name;
          this.condition = condition;
          this.manager = manager;
       }
    
    //return name of the album
       public String getName(){
          return name;
        
       }
    
    //return the condition associated with the album   
       public String getCondition(){
          return condition;
       }
     
     // return the manager 
       public InvIndexPhotoManager getManager(){
          return manager;
       }
       
       
        
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
        
       public LinkedList<Photo> getTagPhotos(String tag)
       {
       
          LinkedList<Photo>res = new LinkedList<Photo>();
          boolean found = manager.getPhotos().findKey1(tag);
          if(found)
             res = manager.getPhotos().retrieve();
          return res;
       }
      
       public LinkedList<Photo> And(LinkedList<Photo>A,LinkedList<Photo>B)
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
      
      
      
      // return number of comparisons used to find all photos of the album 
       public int getNbComps(){
          return manager.index.num_comp;
       }
      
      //
      
      
      // Return all photos matching this album’s condition, via the inverted index
       public LinkedList<Photo> getPhotos() {
        // 1- reset the BST comparison counter:
          manager.index.num_comp = 0;
       
        // 2- empty or blank condition?  Return all the photos 
          if (condition == null || condition.trim().isEmpty()) {
             return manager.getAllPhotos();
          }
       
        // 3-not empty condition: split on "AND" and trim whitespace
          String[] terms = condition.split("AND");
          for (int i = 0; i < terms.length; i++) {
             terms[i] = terms[i].trim();
          }
       
        // 4-first tag lookup check boolean
          boolean found = manager.index.findKey1(terms[0]);
          if (!found) {
          // tag not in the index → no photos match
             return new LinkedList<Photo>();
          }
          LinkedList<Photo> result = manager.index.retrieve();
       
        // 5-remaining tags: lookup + intersect
          for (int i = 1; i < terms.length; i++) {
             found = manager.index.findKey1(terms[i]);
             if(!found){
                return new LinkedList<Photo>();
             }
             LinkedList<Photo> nextList = manager.index.retrieve();
             result = And(result, nextList);
          }
       
          return result;
       }
    
      
      
      
      
    }