package heap;

public class HeapNullNode extends HeapNode {

	@Override
	public int height() {
		return 0;
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean hasRight() {
		return false;
	}

	@Override
	public boolean hasLeft() {
		return false;
	}

	@Override
	public boolean isNull() {
		return true;
	}
}
