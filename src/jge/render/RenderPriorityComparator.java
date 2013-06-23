package jge.render;

import java.util.Comparator;

public final class RenderPriorityComparator implements Comparator<Renderable>{

	/**
	 * Sorts a collection of Renderabes based on the Priority class
	 * If two Renderables share the same priority, the Renderable which was added
	 * to the world most recently will act as is it has a higher priority.
	 */
	@Override
	public int compare(Renderable r1, Renderable r2){
		int r1v = Priority.NORMAL.getAsInteger();
		int r2v = Priority.NORMAL.getAsInteger();
		if(r1 instanceof Prioritizable) r1v = ((Prioritizable) r1).getPriority().getAsInteger();
		if(r2 instanceof Prioritizable) r2v = ((Prioritizable) r2).getPriority().getAsInteger();
		if(r1v > r2v) return 1;
		else if(r1v == r2v) return 0;
		else return -1;
	}

}
