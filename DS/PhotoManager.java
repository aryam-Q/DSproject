package DS;

public class PhotoManager {
    LinkedList<Photo>allPhotos ;
    public int nbtags=0 ;
    public PhotoManager() {

    allPhotos=new LinkedList<Photo>();
    }

    public void addPhoto (Photo p){
        if(Photo_Exist(allPhotos, p))
        return ;
        allPhotos.insert(p);
    }

    public void deletPhoto(String path){
        if(allPhotos.empty())
        return ;

        allPhotos.findFirst();
        while(!allPhotos.last()){
            if(allPhotos.retrieve().path.equals(path)){
                allPhotos.remove();
                break ;
            }
            else
            allPhotos.findNext();
        }
        if(allPhotos.retrieve().path.equals(path)) {
            allPhotos.remove();
        }
    }

    public LinkedList<Photo> getPhotos() {
        return allPhotos ;
    }

    public boolean Photo_Exist(LinkedList<Photo>L, Photo p) {
        if(L.empty())
        return false ;
        L.findFirst();

        while(!L.last()) {
            if(L.retrieve().path.equals(p.path))
            return true ;
            L.findNext() ;
        }
        
        if(L.retrieve().path.equals(p.path))
        return true ;

        return false ;

    }

}


