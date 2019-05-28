package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;
    private int numStatesExplored;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();

        // Create a HashSet to record already visited vertices
        HashSet<Vertex> visited = new HashSet<>();
        // Create a HashMap to record node seen so far and its previous vertex with best cost
        HashMap<Vertex, Vertex> discovered = new HashMap<>();
        // Create a HashMap to record the cost so far from start vertex
        HashMap<Vertex, Double> cost_to = new HashMap<>();

        solution = new ArrayList<>();
        solutionWeight = 0.0;
        numStatesExplored = 0;

        // Create an empty pq and insert the starting vertex into it
        ArrayHeapMinPQ<Vertex> fringe = new ArrayHeapMinPQ<>();
        fringe.add(start, input.estimatedDistanceToGoal(start, end));
        cost_to.put(start, 0.);
        discovered.put(start, null);

        while (fringe.size() != 0) {
            Vertex v = fringe.removeSmallest();
            visited.add(v);
            numStatesExplored += 1;

            if (v.equals(end)) {
                // Found it, done!
                solutionWeight = cost_to.get(end);
                solution.add(end);
                Vertex from = discovered.get(end);
                while (from != null) {
                    solution.add(from);
                    from = discovered.get(from);
                }
                Collections.reverse(solution);
                timeSpent = sw.elapsedTime();
                outcome = SolverOutcome.SOLVED;
                return;
            }

            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(v);
            for (WeightedEdge<Vertex> edge: neighborEdges) {
                if ((!discovered.containsKey(edge.to()) || (cost_to.get(v)+edge.weight() < cost_to.get(edge.to())))
                    && !visited.contains(edge.to())) {  // The order of the two conditionis in (~ || ~) is important (Short circuiting)
                    discovered.put(edge.to(), edge.from());
                    double new_cost = cost_to.get(v)+edge.weight();
                    cost_to.put(edge.to(), new_cost);
                    double heuristicCost = new_cost + input.estimatedDistanceToGoal(edge.to(), end);
                    if (!fringe.contains(edge.to())) {
                        fringe.add(edge.to(), heuristicCost);
                    } else {
                        fringe.changePriority(edge.to(), heuristicCost);
                    }
                }
            }
            timeSpent = sw.elapsedTime();
            outcome = SolverOutcome.TIMEOUT;
        }
        timeSpent = sw.elapsedTime();
        outcome = SolverOutcome.UNSOLVABLE;
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
