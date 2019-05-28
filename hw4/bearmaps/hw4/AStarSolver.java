package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {

        // Create a HashSet to record already visited vertices
        HashSet<Vertex> visited = new HashSet<>();
        // Create a HashMap to record the cost so far from start vertex
        HashMap<Vertex, Double> cost_to = new HashMap<>();

        // Create an empty pq and insert the starting vertex into it
        ArrayHeapMinPQ<Vertex> fringe = new ArrayHeapMinPQ<>();
        fringe.add(start, input.estimatedDistanceToGoal(start, end));
        cost_to.put(start, 0.);

        while (fringe.size() != 0) {
            Vertex v = fringe.removeSmallest();
            visited.add(v);

            if (v.equals(end)) {
                // Found it, done!
                return;
            }

            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(v);
            for (WeightedEdge<Vertex> edge: neighborEdges) {
                if (!visited.contains(edge.to()) && cost_to.get(v)+edge.weight() < cost_to.get(edge.to())) {
                    double heuristicCost = edge.weight() + input.estimatedDistanceToGoal(edge.to(), end);
                    if (!fringe.contains(edge.to())) {
                        fringe.add(edge.to(), heuristicCost);
                    } else {
                        fringe.changePriority(edge.to(), heuristicCost);
                    }
                }
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return null;
    }

    @Override
    public List<Vertex> solution() {
        return null;
    }

    @Override
    public double solutionWeight() {
        return 0;
    }

    @Override
    public int numStatesExplored() {
        return 0;
    }

    @Override
    public double explorationTime() {
        return 0;
    }
}
