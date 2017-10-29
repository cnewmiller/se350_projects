package expandableShapeHeirarchy;


/**
 * 
 * Interface for any class that can contain ChildNodes.
 * @author Clay
 *
 */
public interface ParentNode {
	/**
	 * Adds the given child to the list of this ParentNode's children. Does nothing if the ChildNode is already a child of this ParentNode.
	 * @param c the ChildNode to add
	 */
	public void addChild(ChildNode c);
	
	/**
	 * Removes the given child from the list of this ParentNode's children. Does nothing if the ChildNode is not a child of this ParentNode.
	 * @param c the ChildNode to remove
	 */
	public void removeChild(ChildNode c);
}
