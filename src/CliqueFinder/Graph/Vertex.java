package CliqueFinder.Graph;

import java.util.Objects;

public class Vertex {
    final private String name;

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return name.equals(vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Clone vertex so it uses a different reference.
     *
     * @return cloned vertex
     */
    public Vertex getCopy() { return new Vertex(name); }

}