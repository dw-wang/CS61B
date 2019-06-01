package bearmaps.proj2ab;

import java.util.*;
import java.util.stream.Stream;

public class KDTree implements PointSet {

    private KDTreeNode root;
    private int size;
    private final int dimension = 2;

    // Default constructor to initialize an empty tree
    public KDTree() {
        size = 0;
    }

    public KDTree(List<Point> points) {
        for (Point pt: points) {
            insert(pt);
        }
    }

    public void insert(Point pt) {
        root = insertHelper(root, pt, 0);
    }


    public Point nearest(double x, double y) {
        return nearestHelper(root, new Point(x,y), root.point, Double.MAX_VALUE, 0);
    }

    private KDTreeNode insertHelper(KDTreeNode root, Point p, int level) {
        if (root == null) {
            root = new KDTreeNode(p, null, null);
            size += 1;
        } else {
            // Ignore duplicate points
            if (!root.point.equals(p)) {
                int axis = level % dimension;
                if (axis == 0) {
                    if (p.getX() <= root.point.getX()) {
                        root.left = insertHelper(root.left, p, level + 1);
                    } else {
                        root.right = insertHelper(root.right, p, level + 1);
                    }
                } else {
                    if (p.getY() <= root.point.getY()) {
                        root.left = insertHelper(root.left, p, level + 1);
                    } else {
                        root.right = insertHelper(root.right, p, level + 1);
                    }
                }
            }
        }
        return root;
    }

    private Point nearestHelper(KDTreeNode root, Point target, Point guess, double best_dist, int level) {
        if (root == null) {
            return guess;
        } else {
            double dist = Point.distance(target, root.point);
            if (dist < best_dist) {
                guess = root.point;
                best_dist = dist;
            }

            int axis = level % dimension;
            KDTreeNode good_side = null;
            KDTreeNode bad_side = null;
            if (axis == 0) {
                if (target.getX() <= root.point.getX()) {
                    good_side = root.left;
                    bad_side = root.right;
                } else {
                    good_side = root.right;
                    bad_side = root.left;
                }

                guess = nearestHelper(good_side, target, guess, best_dist, level+1);

                if (Point.distance(target, guess) > target.getX() - root.point.getX()) {
                    guess = nearestHelper(bad_side, target, guess, best_dist, level+1);
                }

            }
            if (axis == 1) {
                if (target.getY() <= root.point.getY()) {
                    good_side = root.left;
                    bad_side = root.right;
                } else {
                    good_side = root.right;
                    bad_side = root.left;
                }

                guess = nearestHelper(good_side, target, guess, best_dist, level+1);

                if (Point.distance(target, guess) > target.getY() - root.point.getY()) {
                    guess = nearestHelper(bad_side, target, guess, best_dist, level+1);
                }
            }

            return guess;
        }
    }

    private class KDTreeNode {
        Point point;
        KDTreeNode left;
        KDTreeNode right;

        public KDTreeNode(Point p, KDTreeNode l, KDTreeNode r) {
            point = p;
            left = l;
            right = r;
        }
    }

    private class SortByX implements Comparator<Point>{

        @Override
        public int compare(Point o1, Point o2) {
            return Double.compare(o1.getX(), o2.getX());
        }
    }

    private class SortByY implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {
            return Double.compare(o1.getX(), o2.getX());
        }
    }
}
