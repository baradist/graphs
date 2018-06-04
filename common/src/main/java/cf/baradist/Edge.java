package cf.baradist;

public final class Edge {
    private int from;
    private int to;
    private Double weight;

    public Edge(int from, int to, Double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", weight=" + weight +
                '}';
    }
}
