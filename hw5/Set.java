/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */
  List mylist;
  /**
   * Set ADT invariants:
   *  1)  The Set's elements must be precisely the elements of the List.
   *  2)  The List must always contain Comparable elements, and those elements 
   *      must always be sorted in ascending order.
   *  3)  No two elements in the List may be equal according to compareTo().
   **/

  /**
   *  Constructs an empty Set. 
   *
   *  Performance:  runs in O(1) time.
   **/
  public Set() { 
    // Your solution here.
    mylist = new DList();
  }

  /**
   *  cardinality() returns the number of elements in this Set.
   *
   *  Performance:  runs in O(1) time.
   **/
  public int cardinality() {
    // Replace the following line with your solution.
    return mylist.length();
  }

  /**
   *  insert() inserts a Comparable element into this Set.
   *
   *  Sets are maintained in sorted order.  The ordering is specified by the
   *  compareTo() method of the java.lang.Comparable interface.
   *
   *  Performance:  runs in O(this.cardinality()) time.
   **/
  public void insert(Comparable c) {
    // Your solution here.
    if (c == null) {
      return;
    }

    if (mylist.length() == 0) {
      mylist.insertFront(c);
      return;
    }

    ListNode trav = mylist.front();
    try {
      while (trav.isValidNode()) {
        if (c.compareTo(trav.item()) == 0) {
          return;
        }
        else if (c.compareTo(trav.item()) < 0) {
          trav.insertBefore(c);
          return;
        }
        else if (c.compareTo(trav.item()) > 0) {
          trav = trav.next();
        }
      }
      mylist.insertBack(c);

    }
    catch (InvalidNodeException e) {
      
    }
  }

  /**
   *  union() modifies this Set so that it contains all the elements it
   *  started with, plus all the elements of s.  The Set s is NOT modified.
   *  Make sure that duplicate elements are not created.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Your implementation should NOT copy elements of s or "this", though it
   *  will copy _references_ to the elements of s.  Your implementation will
   *  create new _nodes_ for the elements of s that are added to "this", but
   *  you should reuse the nodes that are already part of "this".
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
   **/
  public void union(Set s) {
    // Your solution here.
    ListNode node1 = mylist.front();
    ListNode node2 = s.mylist.front();
    try {
      while (node1.isValidNode() && node2.isValidNode()) {
        int flag = ((Comparable)node1.item()).compareTo(((Comparable)node2.item()));
        if (flag == 0) {
          node1 = node1.next();
          node2 = node2.next();
        }
        else if (flag > 0) {
          node1.insertBefore(node2.item());
          node2 = node2.next();
        }
        else {
          node1 = node1.next();
        }
      }

      while (node2.isValidNode()) {
          mylist.insertBack(node2.item());
          node2 = node2.next();
      }

    }
    catch (InvalidNodeException e) {
      
    }

  }

  /**
   *  intersect() modifies this Set so that it contains the intersection of
   *  its own elements and the elements of s.  The Set s is NOT modified.
   *
   *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
   *
   *  Do not construct any new ListNodes during the execution of intersect.
   *  Reuse the nodes of "this" that will be in the intersection.
   *
   *  DO NOT MODIFY THE SET s.
   *  DO NOT CONSTRUCT ANY NEW NODES.
   *  DO NOT ATTEMPT TO COPY ELEMENTS.
   **/
  public void intersect(Set s) {
    // Your solution here.
    ListNode node1 = mylist.front();
    ListNode node2 = s.mylist.front();
    try {
      while (node1.isValidNode() && node2.isValidNode()) {
        int flag = ((Comparable)node1.item()).compareTo(((Comparable)node2.item()));
        if (flag == 0) {
          node1 = node1.next();
          node2 = node2.next();
        }
        else if (flag > 0) {
          node2 = node2.next();
        }
        else {
          ListNode temp = node1;
          node1 = node1.next();
          temp.remove();
        }
      }

      while (node1.isValidNode()) {
          ListNode temp = node1;
          node1 = node1.next();
          temp.remove();
      }

    }
    catch (InvalidNodeException e) {

    }
  }

  /**
   *  toString() returns a String representation of this Set.  The String must
   *  have the following format:
   *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
   *            between them.
   *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
   *            "{" or after "}"; two spaces before and after each element.
   *            Elements are printed with their own toString method, whatever
   *            that may be.  The elements must appear in sorted order, from
   *            lowest to highest according to the compareTo() method.
   *
   *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
   *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
   *            DEVIATIONS WILL LOSE POINTS.
   **/
  public String toString() {
    // Replace the following line with your solution.
    StringBuilder sb = new StringBuilder();
    sb.append("{  ");
    ListNode node1 = mylist.front();
    try {
      while (node1.isValidNode()) {
        sb.append(node1.item());
        sb.append("  ");
        node1 = node1.next();
      }
    }
    catch (InvalidNodeException e) {
      
    }
    sb.append("}");
    return sb.toString();
  }

  public static void main(String[] argv) {
    Set s = new Set();
    s.insert(new Integer(3));
    s.insert(new Integer(4));
    s.insert(new Integer(3));
    System.out.println("Set s = " + s);

    Set s2 = new Set();
    s2.insert(new Integer(4));
    s2.insert(new Integer(5));
    s2.insert(new Integer(5));
    System.out.println("Set s2 = " + s2);

    Set s3 = new Set();
    s3.insert(new Integer(5));
    s3.insert(new Integer(3));
    s3.insert(new Integer(8));
    System.out.println("Set s3 = " + s3);

    s.union(s2);
    System.out.println("After s.union(s2), s = " + s);

    s.intersect(s3);
    System.out.println("After s.intersect(s3), s = " + s);

    System.out.println("s.cardinality() = " + s.cardinality());
    // You may want to add more (ungraded) test code here.
  }
}
