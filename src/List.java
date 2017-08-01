import java.util.Iterator;

public class List<E extends Data<E>> implements ListInterface<E>, Iterable<E> {
	private Node<E> head, tail, current;
	private int size;

	List() {
		this.current = this.head = new Node<E>(null, null, null);
		this.tail = new Node<E>(null, this.head, null);
		size = 0;
		head.next = this.tail;

		current = head;

	}

	List(List<E> src) {
		 
		for (E e : src) {

			E ee = e.clone();

			this.insert(ee);
		}

		this.current = src.current.clone();
		this.tail = src.tail.clone();
		this.current = src.current.clone();
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public ListInterface<E> init() {
		size = 0;
		head = tail = current = new Node<E>(null, null, null);
		return this;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public ListInterface<E> insert(E d) {

		if (size == 0) {
			head = new Node<E>(d);
			current = tail = head;
			size++;

		} else {
			Node<E> tmp = head;
			while (tmp.data.compareTo(d) < 0) {
				if (tmp.next == null) {

					current = tail = tmp.next = new Node<E>(d, tmp, null);

					size++;

					return this;
				}
				tmp = tmp.next;

			}
			if (tmp.prior == null) {
				current = head = tmp.prior = new Node<E>(d, null, tmp);
				size++;

				return this;
			} else {

				tmp = tmp.prior;

				current = tmp.next = new Node<E>(d, tmp, tmp.next);
				current.next.prior = current;
				size++;
			}

		}

		return this;

	}

	@Override
	public E retrieve() {

		return current.data;

	}

	@Override
	public ListInterface<E> remove() {

		if (isEmpty())

			return null;
		if (size() == 1) {
			init();
			return this;
		} else {

			if (current.next == null) {
				current = tail = current.prior;
				current.next = null;

				size--;

				return this;
			}

			if (current.prior == null) {

				current = current.next;
				current.prior = null;
				head = current;

				size--;

				return this;
			} else {

				current.prior.next = current.next;
				current.next.prior = current.prior;

				size--;

			}
		}
		return this;
	}

	@Override
	public boolean find(E d) {
		if (isEmpty())

			return false;
		
		Node<E> tmp = head;

		for (int i = 0; i < size(); i++) {
			if (tmp.data.compareTo(d) == 0) {

				current = tmp;

				return true;
			}
			tmp = tmp.next;

		}
		if (d.compareTo(head.data) == -1) {
			current = head;
		} else {
			current = tail;
		}

		return false;
	}

	@Override
	public boolean goToFirst() {

		if (isEmpty()) {
			return false;
		}
		while (current.prior != null) {
			current = current.prior;
		}

		return true;
	}

	@Override
	public boolean goToLast() {

		if (isEmpty())
			return false;

		while (current.next != null) {
			current = current.next;
		}
		return true;
	}

	@Override
	public boolean goToNext() {
		if (isEmpty()) {
			current = null;
			return false;
		}
		if (current.next == null) {
			return false;
		}

		current = current.next;
		return true;
	}

	@Override
	public boolean goToPrevious() {
		if (isEmpty()) {
			current = null;
			return false;
		}
		if (current.prior != null) {
			current = current.prior;
			return true;
		}

		return false;
	}

	@Override
	public ListInterface<E> clone() {

		return new List<E>(this);

	}

	@Override
	public Iterator<E> iterator() {

		Iterator<E> i = new Iterator<E>() {
			int iter = 0;
			Node<E> n = head;
			@Override
			public void remove(){
				throw new UnsupportedOperationException("remove");
			}
			@Override
			public boolean hasNext() {
				if (iter > size - 1) {

					return false;
				}

				iter++;
				return true;
			}

			@Override
			public E next() {

				Node<E> e = n;
				n = n.next;
				return e.data;
			}

		};
		return i;
	}

	class Node<T extends Data<T>> {

		T data;
		Node<T> prior, next;

		public Node(T d) {
			this(d, null, null);
		}

		public Node(T data, Node<T> prior, Node<T> next) {
			this.data = data == null ? null : data;
			this.prior = prior;
			this.next = next;
		}

		public Node<T> clone() {
			if (data == null) {
				return new Node<T>(null, null, null);
			}
			return new Node<T>(this.data.clone(), this.prior, this.next);
		}

	}
}
