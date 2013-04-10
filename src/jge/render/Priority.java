package jge.render;

/**
 * Priority
 *
 *for RENDERING:
 *Designates in what order objects or rendered
 *and therefor if they can be covered by other objects
 *LOWEST is rendered first, putting it in the background
 *HIGHEST is rendered last, putting it in the foreground, only covered by GUI elements
 *
 */
public final class Priority implements Comparable<Priority>{

	public final static Priority HIGHEST = new Priority(5);
	public final static Priority HIGH = new Priority(4);
	public final static Priority NORMAL = new Priority(3);
	public final static Priority LOW = new Priority(2);
	public final static Priority LOWEST = new Priority(1);
	
	private final int numerical;
	
	private Priority(int numerical){
		this.numerical = numerical;
	}
	
	public int getAsInteger(){
		return numerical;
	}

	@Override
	public int compareTo(Priority p){
		if(numerical > p.numerical){
			return 1;
		}else if(numerical == p.numerical){
			return 0;
		}return -1;
	}
	
	public boolean greater(Priority p){
		return numerical > p.numerical;
	}
	
	public boolean equals(Priority p){
		return numerical > p.numerical;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Priority){
			return numerical == ((Priority)o).numerical;
		}else if(o instanceof Number){
			return numerical == ((Number)o).intValue();
		}return false;
	}
	
	public boolean lesser(Priority p){
		return numerical < p.numerical;
	}
	
	@Override
	public String toString(){
		return "Priority " + numerical;
	}
}
