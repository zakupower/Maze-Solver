/**
 * Created by Tomov on 5.4.2017 г..
 */
public class Node{
    private int id;
    private double weight;
    public Node(int id){
        this.id = id;
        this.weight=Double.MIN_VALUE;
    }
    public Node(int id, double weight){
        this.id = id;
        this.weight=weight;
    }
    public boolean equals(Object ob) {
        return (ob instanceof Node) && ((Node) ob).id ==this.id;
    }

    public int getId() {
        return id;
    }
    public double getWeight(){
        return weight;
    }
}
