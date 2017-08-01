import java.util.Iterator;

public class Set<E extends Data<E>> implements SetInterface<E> {
	private List<E> list;

	public Set() {
		this.list = new List<E>();
	}

	public Set(Set<E> src) {

		this.list = (List<E>) src.list.clone();
		
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {

		list.goToFirst();
		Iterator<E> i = new Iterator<E>() {
			int iter = 0;
			E e = null;
			@Override
			public void remove(){
				throw new UnsupportedOperationException("remove");
			}
			@Override
			public boolean hasNext() {
				if (iter >= size())
					return false;
				iter++;

				return true;
			}

			@Override
			public E next() {
				e = list.retrieve();
				list.goToNext();
				return e;
			}

		};

		return i;
	}

	@Override
	public void add(E e) {
		if (contains(e))
			return;
		list.insert(e);

	}

	public boolean contains(E e) {
		if (list.isEmpty())
			return false;

		return list.find(e);
	}

	@Override
	public void remove(E e) {

		if (list.find(e)) {

			list.remove();
		}
		return;

	}

	@Override
	public void init() {
		this.list = new List<E>();

	}

	@Override
	public int size() {

		return list.size();
	}

	@Override
	public SetInterface<E> difference(SetInterface<E> s) {
		if(s==this){
		
			return new Set<E>();
		}
		if (this.isEmpty()) {

			return this;
		}
		

		Set<E> diff = (Set<E>) this.clone();
		for (E e : this) {
			for (E e2 : s) {
				if (e.compareTo(e2) == 0) {
					
					diff.remove(e);
				}
			}
		}

		return diff;
	}

	@Override
	public SetInterface<E> symmetricDifference(SetInterface<E> s) {
		return (Set<E>) this.clone().difference(s.clone())
				.union(s.clone().difference(this.clone()));
	}

	@Override
	public SetInterface<E> intersection(SetInterface<E> s) {
		if(s==this){
			
			return this;
		}
		Set<E> intersection = new Set<E>();
		for (E e : this) {
			for (E e2 : s) {
				if (e.compareTo(e2) == 0) {
					intersection.add(e);
				}
			}
		}

		return intersection;
	}

	@Override
	public SetInterface<E> union(SetInterface<E> s) {

		Set<E> s1 = (Set<E>) clone();

		Set<E> united = (Set<E>) s1.clone();

		for (E e : s) {

			united.add(e);

		}

		return united;

	}

	@Override
	public SetInterface<E> clone() {
		return new Set<E>(this);

	}

}
