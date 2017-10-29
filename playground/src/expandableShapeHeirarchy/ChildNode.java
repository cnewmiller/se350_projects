package expandableShapeHeirarchy;


/**
 * 
 * Interface representing any node that can have a parent. Not every shape necessarily can be placed into other shapes, so this differentiates them.
 * 
 * @author Clay
 *
 */
public interface ChildNode {
	/**
	 * 
	 * Sets this ChildNode's parent node to p
	 * 
	 * @param p
	 */
	public void addParent(ParentNode p);
	
	
	/**
	 * Returns this node's parent
	 * 
	 * @return a ParentNode that contains this node, or null if this node has no parent
	 */
	public ParentNode getParent();
}
