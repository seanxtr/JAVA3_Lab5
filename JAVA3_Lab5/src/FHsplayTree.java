//--------------- Class FHsplayTree Definition ---------------
import cs_1c.*;

/**
 * Class definition of a splay tree which always place newly accessed value on
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
      if (mRoot == null) {
         mRoot = new FHs_treeNode<E>(x,null,null);
         return true;
      }
      else
         mRoot = splay(mRoot, x);
      
      int compareResult = x.compareTo(mRoot.data);
      if (compareResult < 0) {
         mRoot = new FHs_treeNode<E>(x,mRoot.lftChild, mRoot);
         return true;
      }
      else if (compareResult > 0) {
         mRoot = new FHs_treeNode<E>(x,mRoot, mRoot.rtChild);
         return true;
      } else {
         return false;
      }
   }
   
   /**
    * Method to remove a value
    */
   public boolean remove(E x){
      FHs_treeNode<E> newRoot;
      
      if (mRoot == null)
         return false;
      else
         mRoot = splay(mRoot, x);

      if (mRoot.data.compareTo(x) != 0)
         return false;
      
      if (mRoot.lftChild == null) {
         newRoot = mRoot.rtChild;
      } else {
         newRoot = mRoot.lftChild;
         mRoot = splay(newRoot, x);
         newRoot.rtChild = mRoot.rtChild;
      }
      
      mRoot = newRoot;
      return true;
   }

   /**
    * Method to get the current root value
    * @return      root value
    */
   public E showRoot(){
      return super.mRoot == null ? null : mRoot.data;
   }
    

   /**
    * Internal method to perform a top-down splaying tree
    * The last accessed node becomes the new root
    * @param x     target value
    * @param t     root node of subtree
    * @return      result node
    */
   protected FHs_treeNode<E> splay(FHs_treeNode<E> root, E x){
      FHs_treeNode<E> leftTree = null;
      FHs_treeNode<E> rightTree = null;
      FHs_treeNode<E> rightTreeMin = null;
      FHs_treeNode<E> leftTreeMax = null;
      int compareResult;
      
      while (root != null){
         compareResult = x.compareTo(root.data); 
         if (compareResult < 0){
            if (root.lftChild == null)
               break;
            
            if (x.compareTo(root.lftChild.data) < 0)
            {
               root = rotateWithLeftChild(root);
            
               if (root.lftChild == null)
                  break;
            }
            
            if (rightTree == null){
               rightTree = root;
               rightTreeMin = root;
            } else {
               rightTreeMin.lftChild = root;
               rightTreeMin = root;
            }
            
            root = root.lftChild;
         }
         else if (compareResult > 0) {
            if (root.rtChild == null)
               break;
            
            if (x.compareTo(root.rtChild.data ) > 0) {
               root = rotateWithRightChild(root);
               
               if (root.rtChild == null)
                  break;
            }
            
            if (leftTree == null){
               leftTree = root;
               leftTreeMax = root;
            } else {
               leftTreeMax.rtChild = root;
               leftTreeMax = root;
            }
            root = root.rtChild;
         } else {
            break;// find the target
         }
      }
      
      
      if (leftTree != null) {
         leftTreeMax.rtChild = root.lftChild;
         root.lftChild = leftTree;
      }
      
      if (rightTree != null) {
         rightTreeMin.lftChild = root.rtChild;
         root.rtChild = rightTree;
      }
      
      return root;
   }
    

   protected FHs_treeNode<E> rotateWithLeftChild( FHs_treeNode<E> k2 ) {
      FHs_treeNode<E> k1 = k2.lftChild;
      k2.lftChild = k1.rtChild;
      k1.rtChild = k2;
      return k1;
   }

   protected FHs_treeNode<E> rotateWithRightChild( FHs_treeNode<E> k2 ) {
      FHs_treeNode<E> k1 = k2.rtChild;
      k2.rtChild = k1.lftChild;
      k1.lftChild = k2;
      return k1;
   }
    
   /**
    * Find an node in the tree
    */
   protected FHs_treeNode<E> find( FHs_treeNode<E> root, E x ){
       root = splay(root, x);

       if( root == null || root.data.compareTo(x) != 0 )
           return null;

       return root;
   }
}
