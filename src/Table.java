public class Table<K extends Data<K>,V extends Data<V>> implements  TableInterface<K, V> {
	private List<Tuple<K,V>> list;

	public Table() {

		list = new List<Tuple<K,V>>();
	}

	public Table(Table<K,V> src) {
		this.list = (List<Tuple<K,V>>) src.list.clone();
	}

	

	
	@Override
	public void add(K key, Set<V> set){

		Tuple<K,V> t = new Tuple<K,V>(key, set);
		if (this.contains(key)){
			list.remove();
			list.insert(t);
		}
			
		list.insert(t);
	}



	@Override
	public boolean contains(K key) {
		return list.find(new Tuple<K,V>(key, null));
	}

	

	
	@Override
	public TableInterface<K, V> clone() {

		return new Table<K,V>(this);
	}




	@Override
	public Set<V> getByKey(K key) throws APException {
		
		if (this.contains(key)) {

			return list.retrieve().set;
		}
		throw new APException("Err. Element is not present in the table");
	}


	
	
	@Override
	public void remove(K key) throws APException {
		if (this.contains(key))
			list.remove();
		else
			throw new APException("Err. Element is not present in the table");
	}

	private class Tuple<K extends Data<K>,V extends Data<V>> implements Data<Tuple<K,V>> {

		Set<V> set;
		K key;

		private Tuple(K key, Set<V> set) {
			this.key=key;
			this.set = (Set<V>) set;
		}

		@Override
		public Tuple<K,V> clone() {

			Tuple<K,V> t = new Tuple<K,V>(key.clone(),
					(Set<V>) set.clone());

			return t;

		}

		@Override
		public int compareTo(Tuple<K,V> t) {
			if (this.key.compareTo(t.key) == 0)
				return 0;
			if (this.key.compareTo(t.key) < 0)
				return -1;
			return 1;
		}

	}
}
