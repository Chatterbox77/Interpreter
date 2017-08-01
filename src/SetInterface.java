/**
 * ADT for the class Set
 * 
 * @author Andrey Horban,Piotr Zaitev
 * @elements elements of the type E
 * @structure none
 * @domain all instances of the type E
 * @constructor
 *
 *              Set() ;
 *              <dl>
 *              Creates the new instance of the type Set
 *
 *              <dt><b>@PRE-condition</b>
 *              <dd>-
 *
 *              <dt><b>@POST-condition</b>
 *              <dd>
 *              The empty Set object was created
 *              </dl>
 * <br>
 *              Set(Set s);
 *              <dl>
 *              Creates the new instance of the Set which is the copy of the src
 *              <dt><b>@PRE-condition</b>
 *              <dd>-
 *              <dt><b>@POST-condition</b>
 *              <dd>The new Set-object contains a copy of the src.
 *              </dl>
 *
 **/

public interface SetInterface<E extends Data<E>> extends Iterable<E>,
		Clonable<SetInterface<E>> {

	/**
	 * 
	 * add one more E to the Set
	 * 
	 * @precondition -
	 * @postcondition the copy of the new element was added in the end of the
	 *                Set
	 * 
	 **/
	void add(E e);

	/**
	 * 
	 * check weather the Set-object is empty
	 * 
	 * @precondition -
	 * @postcondition True:Set is empty 
	 * False:otherwise
	 * 
	 **/
	boolean isEmpty();

	/**
	 * 
	 * remove specific element from the Set
	 * 
	 * @precondition Set is not empty,specific element is in the set
	 * @postcondition specific element was removed
	 * 
	 **/
	public void remove(E e);

	/**
	 * initializes the Set object to the empty set
	 * 
	 * @precondition -
	 * @postcondition Set is empty
	 **/

	void init();

	/**
	 * returns the number of elements in Set
	 * 
	 * @precondition -
	 * @postcondition returns the number of elements in Set as an int
	 * 
	 **/
	int size();

	/**
	 * perform Difference operation on two objects of the type Set
	 * 
	 * @precondition -
	 * @postcondition the new Set which is the difference of two sets and name
	 *                key
	 * 
	 **/
	SetInterface<E> difference(SetInterface<E> s);

	/**
	 * perform Symmetric Difference operation on two objects of the type Set
	 * 
	 * @precondition -
	 * @postcondition the new Set which is the symmetric difference of two sets
	 *                and name key
	 * 
	 **/
	SetInterface<E> symmetricDifference(SetInterface<E> s);

	/**
	 * perform Intersection operation on two objects of the type Set
	 * 
	 * @precondition -
	 * @postcondition the new Set which is the intersection of two sets and name
	 *                key
	 * 
	 **/
	SetInterface<E> intersection(SetInterface<E> s);

	/**
	 * perform Union operation on two objects of the type Set
	 * 
	 * @precondition -
	 * @postcondition the new Set which is the union of two sets c
	 * 
	 **/
	SetInterface<E> union(SetInterface<E> s);

}
