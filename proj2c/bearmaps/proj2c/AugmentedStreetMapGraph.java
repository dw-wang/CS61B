package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private HashMap<Point, Node> pointNodeHashMap;
    List<Node> nodes;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        nodes = this.getNodes();
        removeNodesWithoutNeighbors();
        pointNodeHashMap = getPointNodeHashMap(nodes);
    }

    private void removeNodesWithoutNeighbors() {
        List<Node> toRemove = new ArrayList<>();
        for (Node node: nodes) {
            if (neighbors(node.id()).size() == 0) {
                toRemove.add(node);
            }
        }
        for (Node node: toRemove) {
            nodes.remove(node);
        }
    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        List<Point> points = getPointsFromNodes(nodes);
        //KDTree kdtree = new KDTree(points);
        //Point nearestPoint = kdtree.nearest(lon, lat);
        //Node nearestNode = pointNodeHashMap.get(nearestPoint);

        WeirdPointSet ps = new WeirdPointSet(points);
        Point pt = ps.nearest(lon, lat);
        Node nearestNode = pointNodeHashMap.get(pt);
        return nearestNode.id();
    }

    private List<Point> getPointsFromNodes(List<Node> nodes) {
        List<Point> points = new ArrayList<>();
        for (Node node: nodes) {
            double x = node.lon();
            double y = node.lat();
            Point point = new Point(x, y);
            points.add(point);
        }
        return points;
    }

    private HashMap<Point, Node> getPointNodeHashMap(List<Node> nodes) {
        HashMap<Point, Node> map = new HashMap<>();
        for (Node node: nodes) {
            double x = node.lon();
            double y = node.lat();
            Point pt = new Point(x, y);
            map.put(pt, node);
        }
        return map;
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        return new LinkedList<>();
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        return new LinkedList<>();
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
