package DendrogamCluster;

import java.util.ArrayList;
import java.util.List;

/*
* Class for creating clusters
* */
public class Cluster
{

    private String name;

    private Cluster parent;

    private List<Cluster> children;

    private List<String> leafNames;

    private Distance distance = new Distance();


    public Cluster(String name)
    {
        this.name = name;
        leafNames = new ArrayList<String>();
    }

    public Distance getDistance()
    {
        return distance;
    }

    public Double getWeightValue()
    {
        return distance.getWeight();
    }


    public void setDistance(Distance distance)
    {
        this.distance = distance;
    }

    public List<Cluster> getChildren()
    {
        if (children == null)
        {
            children = new ArrayList<Cluster>();
        }

        return children;
    }

    public void addLeafName(String lname)
    {
        leafNames.add(lname);
    }

    public void appendLeafNames(List<String> lnames)
    {
        leafNames.addAll(lnames);
    }

    public List<String> getLeafNames()
    {
        return leafNames;
    }


    public void setParent(Cluster parent)
    {
        this.parent = parent;
    }


    public String getName()
    {
        return name;
    }


    public void addChild(Cluster cluster)
    {
        getChildren().add(cluster);
    }


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        Cluster other = (Cluster) obj;
        if (name == null)
        {
            if (other.name != null)
            {
                return false;
            }
        } else if (!name.equals(other.name))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        return (name == null) ? 0 : name.hashCode();
    }

    public boolean isLeaf()
    {
        return getChildren().size() == 0;
    }

    public int countLeafs()
    {
        return countLeafs(this, 0);
    }

    public int countLeafs(Cluster node, int count)
    {
        if (node.isLeaf()) count++;
        for (Cluster child : node.getChildren())
        {
            count += child.countLeafs();
        }
        return count;
    }

    
    public String toString(int indent)
    {
        String cdtString = "";
        if(!isLeaf()) cdtString+="( ";

        for (int i = 0; i < indent; i++){
            cdtString += " ";
        }
        
        
        if(isLeaf()) {
            cdtString+=getName();

        }
        
        List<Cluster> children = getChildren();
        
        boolean firstChild = true;
        for (Cluster child : children)
        {
            cdtString += child.toString(indent);
            if(firstChild) {
                cdtString+=", ";
            }

            firstChild=false;
        }
        
        for (int i = 0; i < indent; i++)
            cdtString += " ";
        
        if(!isLeaf()){
            cdtString+=" )";
        }
        
        return cdtString;
    }




}
