/**
 * ADT for the class Table
 * 
 * @author Andrey Horban,Piotr Zaitev
 * @elements elements of the types K,V
 * @structure none
 * @domain no duplicate K
 * @constructor
 *
 *              Table() ;
 *              <dl>
 *              Creates the new instance of the type Table
 *
 *              <dt><b>@PRE-condition</b>
 *              <dd>-
 *
 *              <dt><b>@POST-condition</b>
 *              <dd>
 *              The new Table object was created
 *              </dl>
 * <br>
 *              Table(Table src);
 *              <dl>
 *              Creates the new instance of the Table which is the copy of the
 *              src
 *              <dt><b>@PRE-condition</b>
 *              <dd>-
 *              <dt><b>@POST-condition</b>
 *              <dd>The new Table-object contains a copy of the src.
 *              </dl>
 *
 **/
public interface TableInterface<K extends Data<K>, V extends Data<V>> extends
		Clonable<TableInterface<K, V>> {
	/**
	 * 
	 * add a new element with the key K,value V to the Table
	 * 
	 * @precondition -
	 * @postcondition the copy of the new element was added in the end of the
	 *                Table.If the element with the given key K was already in
	 *                the Table,it would be overridden
	 * 
	 **/

	public void add(K key, Set<V> set);
	/**
	 * checks weather the given element is present in the table
	 * 
	 * @precondition -
	 * @postcondition True: the given element is present in the table 
	 * False:otherwise
	 **/
	public boolean contains(K key);
	/**
	 * returns the copy of the element with the given key
	 * @throws APException if the given element is not in the Table
	 * @precondition -
	 * @postcondition the copy of the element with the given key was returned
	 **/
	public Set<V> getByKey(K key) throws APException;
	/**
	 * removes the element with the given key K from the Table
	 * @throws APException if the element with the key K is not in the Table
	 * @precondition - Table is not empty
	 * @postcondition the element with the given key was removed
	 **/
	public void remove(K key) throws APException;

}