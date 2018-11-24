package ArbolB;

public class BTree {


    private int order;
    private int numberOfTreeEntries = 0;
    private int numberOfTreeNodes = 0;
    private int minEntries;
    private int middle;
    private BNode root = null;

    public BTree(int order)
    {
        this.order = order;
        this.minEntries = (int) Math.ceil(getOrder() / 2);
        this.middle = this.order/2;
    }

    public BNode searchKey(int key)
    {
        return searchKey(getRoot(), key);
    }

    private BNode searchKey(BNode node, int key)
    {
        if(node == null) 
            return null;

        int entryPosition = node.containsKey(key);

        if(entryPosition < 0)
        {
            return searchKey(node.getChild(node.getNextChildPositionForKey(key)), key);
        }
        else
        {
            return node;
        }
    }

    public boolean insertEntry(int key)
    {
        NodeB n = new NodeB(key);
        n.ID = numberOfTreeEntries;
        return insertEntry(new NodeB(key));
    }

    public boolean insertEntry(NodeB entry)
    {
        if (getRoot() == null)
        {
            setRoot(new BNode(null, numberOfTreeNodes++));
        }

        /*if(searchKey(entry.getKey()) != null)
        {
            return false;
        }*/

        insertEntry(getRoot(), entry);

        if (getRoot().getNumberOfEntries() > getOrder()-1)
        {
            BNode newRoot = new BNode(null, numberOfTreeNodes++);
            splitTree(newRoot, 0, getRoot());
            setRoot(newRoot);
        }
        setNumberOfTreeEntries(getNumberOfTreeEntries() + 1);
        return true;
    }

    private void insertEntry(BNode node, NodeB entry)
    {
        if(node!=null)
        {
        if(node.getNumberOfChildren() == 0)
        {	
            int positionToInsert = 0;
            for(int i = 0; i < order; i++)
            {
                if(node.getNumberOfEntries() > i)
                {
                    ///////////////////////////////
                    if(entry.getKey() < node.getKey(i))
                    {
                        positionToInsert = i;
                        break;
                    }
                }
                else
                {
                    positionToInsert = i;
                    break;
                }
            }
            node.addEntry(positionToInsert, entry);
        }
        else
        {
            int childPositionToInsert = node.getNextChildPositionForKey(entry.getKey());

            insertEntry(node.getChild(childPositionToInsert), entry);

            if(node.getChild(childPositionToInsert).getNumberOfEntries() > order-1)
            {
                splitTree(node, childPositionToInsert, node.getChild(childPositionToInsert));
            }
        }
        }
    }

    private void splitTree(BNode node, int childPositionToSplit, BNode subNodeToSplit)
    {
        BNode rightSubTree = null;

        node.addEntry(childPositionToSplit, subNodeToSplit.removeEntry(getMiddle()));

        rightSubTree = new BNode(node, numberOfTreeNodes++);
        while(getMiddle() < subNodeToSplit.getNumberOfEntries())
        {
            rightSubTree.addChild(subNodeToSplit.removeChild(getMiddle()+1));
            rightSubTree.addEntry(subNodeToSplit.removeEntry(getMiddle()));
        }
        rightSubTree.addChild(subNodeToSplit.removeChild(subNodeToSplit.getNumberOfChildren() - 1));

        node.setChild(childPositionToSplit, subNodeToSplit);
        node.addChild(childPositionToSplit+1, rightSubTree);
    }

    private int getOrder()
    {
        return order;
    }

    public int getNumberOfTreeEntries()
    {
        return numberOfTreeEntries;
    }

    private void setNumberOfTreeEntries(int numberOfTreeEntries)
    {
        this.numberOfTreeEntries = numberOfTreeEntries;
    }

    private int getMiddle()
    {
        return middle;
    }

    public BNode getRoot()
    {
        return root;
    }

    private void setRoot(BNode root)
    {
        this.root = root;
        if(root != null)
            this.root.setParent(null);
    }
}