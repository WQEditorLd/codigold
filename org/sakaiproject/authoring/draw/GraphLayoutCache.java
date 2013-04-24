package org.sakaiproject.authoring.draw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.tree.TreeNode;

import org.jgraph.graph.AbstractCellView;
import org.jgraph.graph.CellView;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphModel;


public class GraphLayoutCache extends org.jgraph.graph.GraphLayoutCache{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2312666487925063335L;

	public GraphLayoutCache(GraphModel model,
			DefaultCellViewFactory defaultCellViewFactory, boolean b) {
		super(model, defaultCellViewFactory, b);
	}

	public GraphLayoutCache(Object model,
			DefaultCellViewFactory defaultCellViewFactory, boolean b) {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public boolean setVisibleImpl(Object[] cells, boolean visible) {
		boolean updatePorts = false;
		
		cells = addVisibleDependencies(cells, visible);
		
		if (cells != null && isPartial()) {
			updatePorts = false;
			
			// Update Visible Set
			CellView[] views = new CellView[cells.length];
			if (!visible) {
				views = removeCells(cells);
			}
			
			// Set used for model roots contains call for performance
			Set modelRoots = null;
			
			for (int i = 0; i < cells.length; i++) {
				if (cells[i] != null) {
					if (visible) {
						Set visibleSet;
						visibleSet.add(cells[i]);
						views[i] = getMapping(cells[i], true);
					} else {
						if (views[i] != null) {
							if (modelRoots == null) {
								modelRoots = 
									new HashSet(DefaultGraphModel.getRootsAsCollection(getModel()));
							}
							TreeNode cell = (TreeNode) views[i].getCell();
							while(cell.getParent() != null) {
							   cell = cell.getParent();
							}
							if (modelRoots.contains(cell) && remembersCellViews) {
							   hiddenMapping.put(views[i].getCell(), views[i]);
							}
							updatePorts = true;
						}
					}
				}
			}
			// Make Cell Views Visible (if not already in place)
			if (visible) {
				Set<CellView> parentSet = new HashSet();
				Set<CellView> removedRoots = null;
				for (int i = 0; i < views.length; i++) {
					if (views[i] != null) {
						CellView view = views[i];
						// Remove all children from roots
						CellView[] children = AbstractCellView
						.getDescendantViews(new CellView[] { view });
						for (int j = 0; j < children.length; j++) {
							if (removedRoots == null) {
								removedRoots = new HashSet();
						}
							removedRoots.add(children[j]);
						}
						view.refresh(this, this, false);
						// Link cellView into graphLayoutCache
						CellView parentView = view.getParentView();
						if (parentView != null)
							parentSet.add(parentView);
						updatePorts = true;
					}
				}
				if (removedRoots !=null && removedRoots.size() > 0) {
					// If any roots have been removed, reform the roots
					// lists appropriately, keeping the order the same
					List<Object> newRoots = new ArrayList();
					Set roots;
					Iterator iter = roots.iterator();
					while (iter.hasNext()) {
						Object cell = iter.next();
						if (!removedRoots.contains(cell)) {
							newRoots.add(cell);
						}
					}
					roots = (Set) newRoots;
				}

				CellView[] parentViews = new CellView[parentSet.size()];
				parentSet.toArray(parentViews);
				refresh(parentViews, true);
			}
			return updatePorts;
		}
		return false;
	}

	private void refresh(CellView[] parentViews, boolean b) {
		// TODO Auto-generated method stub
		
	}

	private CellView[] removeCells(Object[] cells) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isPartial() {
		// TODO Auto-generated method stub
		return false;
	}

	private Object[] addVisibleDependencies(Object[] cells, boolean visible) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object[] addVisibleDependencies(Object[] cells, boolean visible) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	private CellView getMapping(Object object, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	private CellView[] removeCells(Object[] cells) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isPartial() {
		// TODO Auto-generated method stub
		return false;
	}

	private Object[] addVisibleDependencies(Object[] cells, boolean visible) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setLocalAttributes(Set<String> locals) {
		// TODO Auto-generated method stub
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
