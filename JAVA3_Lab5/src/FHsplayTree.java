//--------------- Class FHsplayTree Definition ---------------
import cs_1c.*;

/**
 * Class of a splay tree which always place newly accessed value on
 * its root node
 * 
 * @author Tianrong Xiao
 * @param <E>      generic data type
 */
public class FHsplayTree<E extends Comparable< ? super E>>
   extends FHsearch_tree<E> {

   /**
    * Method to insert a value
    */
   public boolean insert(E x){
      boolean res = super.insert(x);
      super.mRoot = splay(super.mRoot, x);
      return res;
   }
   
   /**
    * Method to remove a value
    */
   public boolean remove(E x){
      boolean res = super.remove(x);
      super.mRoot = splay(super.mRoot, x);
      return res;
   }

   /**
    * Method to get the current root value
    * @return      root value
    */
   public E showRoot(){
      return super.mRoot == null ? null : mRoot.data;
   }
    

   // this method is analyzed in depth in the modules and a detailed algorithm is given there.
   protected FHs_treeNode<E> splay(FHs_treeNode<E> root, E x){
      FHs_treeNode<E> leftNode = new FHs_treeNode<E>();
      FHs_treeNode<E> rightNode = new FHs_treeNode<E>();
      FHs_treeNode<E> wkRoot = super.mRoot;

      while (wkRoot != null){
         

      }
   }
    
   // this is (almost) identical with the version found in FHavlTree.java.  Make the trivial adjustments and you've got it.
   protected FHs_treeNode<E> rotateWithLeftChild( FHs_treeNode<E> k2 ){
      FHs_treeNode<E> k1 = k2.lftChild;
      k2.lftChild = k1.rtChild;
      k1.rtChild = k2;
      k2.setHeight( Math.max( heightOf(k2.lftChild),  heightOf(k2.rtChild) ) + 1 );
      k1.setHeight( Math.max( heightOf(k1.lftChild),  k2.getHeight() ) + 1 );
      return k1;
   }
    
   // this is (almost) identical with the version found in FHavlTree.java.  Make the trivial adjustments and you've got it.
   protected FHs_treeNode<E> rotateWithRightChild( FHs_treeNode<E> k2 ){
      FHs_treeNode<E> k1 = k2.rtChild;
      k2.rtChild = k1.lftChild;
      k1.lftChild = k2;
      k2.setHeight( Math.max( heightOf(k2.lftChild),  heightOf(k2.rtChild) ) + 1 );
      k1.setHeight( Math.max( heightOf(k1.rtChild),  k2.getHeight() ) + 1 );
      return k1;
   }
    
   // this is the private partner of the public find().
   protected FHs_treeNode<E> find( FHs_treeNode<E> root, E x ){
    	
   }

}
