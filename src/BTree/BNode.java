
package ArbolB;

import java.util.ArrayList;
import java.util.List;

public class BNode {

    private List<NodeB> entries;

    private List<BNode> children;

    private BNode parent;
    private int number;

    public BNode(BNode parent, int number)
    {
        this.parent = parent;
        this.number = number;
    }

    private List<NodeB> getEntries()
    {
        if(entries == null)
        {
            entries = new ArrayList<NodeB>();
        }
        return entries;
    }

    private List<BNode> getChildren()
    {
        if(children == null)
        {
            children = new ArrayList<BNode>();
        }
        return children;
    }

    public void addEntry(NodeB element)
    {
        if(element != null)
        {
            
            getEntries().add(element);
        }
    }

    public void addEntry(int index,NodeB element)
    {
        if(element != null) {
            getEntries().add(index, element);
        }
    }

    public NodeB setEntry(int index,NodeB element)
    {
        if(element == null && index < getNumberOfEntries())
        {
            return removeEntry(index);
        }
        else if(index < getNumberOfEntries())
        {
            return getEntries().set(index, element);
        }
        else
        {
            getEntries().add(element);
            return null;
        }
    }

    public NodeB removeEntry(int index)
    {
        if(index > getNumberOfEntries() - 1)
            return null;

        return getEntries().remove(index);
    }

    public int getKey(int index)
    {
        if(index > getNumberOfEntries()-1 || index == -1 || getEntries().get(index) == null)
            return -1;
        return getEntries().get(index).getKey();
    }

    public NodeB getEntry(int index)
    {
        if(index > getNumberOfEntries()-1 || index == -1)
            return null;

        return getEntries().get(index);
    }

    public BNode setChild(int index, BNode element)
    {
        if(element == null)
        {
            return removeChild(index);
        }
        else if(index >= getNumberOfChildren())
        {
            element.setParent(this);
            getChildren().add(element);
            return null;
        }
        else
        {
            element.setParent(this);
            return children.set(index, element);
        }
    }

    public void addChild(BNode element)
    {
        if(element != null)
        {
            element.setParent(this);
            getChildren().add(element);
        }
    }

    public void addChild(int index, BNode element)
    {
        if(element != null)
        {
            element.setParent(this);
            getChildren().add(index, element);
        }
    }

    public BNode removeChild(int index)
    {
        if(index > getNumberOfChildren() - 1 || index == -1) return null;
        getChildren().get(index).setParent(null);
        return getChildren().remove(index);
    }

    public BNode getChild(int index)
    {
        if(index > getNumberOfChildren() - 1 || index == -1) return null;
        return getChildren().get(index);
    }

    public int getNextChildPositionForKey(int key)
    {	
        if(containsKey(key) >= 0) 
                return -1;

        int nextChildPosition = -1;
        for(int i = 0; i < getNumberOfEntries(); i++)
        {
            if(key < getKey(i))
            {
                nextChildPosition = i;
                break;
            }
            else if(i == getNumberOfEntries() - 1)
            {
                nextChildPosition = i+1;
            }
        }
        return nextChildPosition;
    }

    public int getNumberOfChildren()
    {
        int size = 0;
        for(BNode child : getChildren())
        {
            if(child != null) size++;
        }
        return size;
    }

    public boolean isLeaf()
    {
        return getNumberOfChildren() == 0;
    }

    public boolean isNode()
    {
        return !isLeaf();
    }

    public int getNumberOfEntries()
    {
        for(int i = 0; i < getEntries().size(); i++)
        {
            if(getEntries().get(i) == null) getEntries().remove(i);
        }
        return getEntries().size();
    }

    public int containsKey(int key)
    {
        int position = -1;
        for(int i = 0; i < getNumberOfEntries(); i++)
        {
            if(getKey(i) == key)
            {
                position = i;
                break;
            }
        }
        return position;
    }

    public BNode getParent()
    {
        return parent;
    }

    public void setParent(BNode parent)
    {
        this.parent = parent;
    }

    public int getNumber()
    {
        return number;
    }
}