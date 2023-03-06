/******************************************************************************
 *  Name:
 *
 *  Hours to complete assignment (optional): ~ 6 
 *
 ******************************************************************************/

Programming Assignment 2: Autocomplete


/******************************************************************************
 *  Describe how your firstIndexOf() method in BinarySearchDeluxe.java
 *  finds the first index of a key that equals the search key.
 *****************************************************************************/

Using a standard binary search approach, the firstIndexOf() method adjusts the hi bounds
when a match is found to be the mid index. This allows the search to continue to the left until 
the index of the first matched value is found.


/******************************************************************************
 *  What is the order of growth of the number of compares (in the
 *  worst case) that each of the operations in the Autocomplete
 *  data type make, as a function of the number of terms n and the
 *  number of matching terms m?  (Big-Oh notation)
 * 
 *  Recall that with order-of-growth notation, you should discard
 *  leading coefficients and lower-order terms, e.g., m^2 + m log n.
 *****************************************************************************/

constructor: O(n log n).  The Java system sort method is O(n log n) and the constructor calls it once.

allMatches(): O(log n + m log n).  The firstIndexOf and lastIndexOf calls are O(log n)  The final sort is O(m log n).

numberOfMatches(): O(log n). The firstIndexOf and lastIndexOf calls are O(log n). The number of matches is the difference between the indices.


/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/


/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings or lectures, but do include
 *  any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *
 *  Also include any resources (including the web) that you may
 *  may have used in creating your design.
 *****************************************************************************/

Sedgewicks implementation of BinarySearch.java from the Princeton Algorithms course website was used as a reference for the BinarySearchDeluxe.java implementation.

/******************************************************************************
 *  Describe any serious problems you encountered.
 *****************************************************************************/


/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 *****************************************************************************/

This is a cool assignment. It has furthered my understanding of some nuances of Java.
