import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static class Connection {
        public final int cost;
        public final int destination;
        
        public int timesToVisit;
        public final int originalTimesToVisit;
        
        public Connection(int cost, int destination, int originalTimesToVisit) {
            this.cost = cost % 10;
            this.destination = destination;
            
            this.timesToVisit = this.originalTimesToVisit = originalTimesToVisit;
        }
        
        public static int getTimesToVisit(int cost) {
            int result;
            int currentCost = cost % 10;
            if (currentCost == 0) {
                result = 0;
            } else if (currentCost % 2 == 0) {
                result = 4;
            } else if (currentCost % 5 == 0) {
                result = 1;
            } else {
                result = 9;
            }
            return result;
        }
        
        public void resetTimesToVisit() {
            timesToVisit = originalTimesToVisit;
        }
    }
    
    public static class Node {
        public final int number;
        public final List<Connection> connections;
        
        public boolean visited;
        
        public Node(int number) {
            this.number = number;
            this.connections = new LinkedList<Connection>();
        }
        
        public void cleanVisitations() {
            for (Connection connection : connections) {
                connection.resetTimesToVisit();
            }
        }
        
        public void addConnectionToSelf(int cost) {
            boolean isPresent = false;
            for (Connection connection : connections) {
                if (connection.destination == this.number &&
                   connection.cost == (cost % 10)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                this.connections.add(new Connection(cost, this.number, Connection.getTimesToVisit(cost)));
            }
        }
        
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("Node " + this.number + " with connections: ");
            for (int counter = 0; counter < connections.size(); counter++) {
                Connection connection = connections.get(counter);
                builder.append(connection.destination);
                builder.append("(" + connection.cost + "/" + connection.originalTimesToVisit + ")");
            }
            return builder.toString();
        }
    }
    
    public static class Path {
        public final List<Integer> nodes;
        public int currentCost;
        
        public Path(int source) {
            this.nodes = new LinkedList<Integer>();
            this.nodes.add(source);
            this.currentCost = 0;
        }
        
        public Path(Path path) {
            this.nodes = new LinkedList<Integer>(path.nodes);
            this.currentCost = path.currentCost;
        }
        
        public boolean isNodeInPath(int nodeNumber) {
            return nodes.contains(nodeNumber);
        }
        
        public boolean isNodeInPath(Node node) {
            return isNodeInPath(node.number);
        }
        
        public int getLastNode() {
            return this.nodes.get(this.nodes.size() - 1);
        }
        
        public void addNodeToPath(int nodeNumber, int cost) {
            this.currentCost += cost;
            this.nodes.add(nodeNumber);
        }
        
        public boolean canCircle() {
            if (this.nodes.size() > 1) {
                return this.nodes.get(this.nodes.size() - 1) != this.nodes.get(this.nodes.size() - 2);
            } else {
                return true;
            }
        }
        
        public boolean isSolution() {
            if (this.nodes.size() > 1) {
                return this.nodes.get(0) != this.nodes.get(this.nodes.size() - 1);
            } else {
                return false;
            }
        }
        
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(nodes.get(0));
            for (int counter = 1; counter < nodes.size(); counter++) {
                builder.append(" -> ");
                builder.append(nodes.get(counter));
            }
            builder.append(" with cost: " + currentCost);
            return builder.toString();
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int e = in.nextInt();
        
        List<Node> nodes = new ArrayList<Node>(n);
        for (int nodeCounter = 0; nodeCounter < n; nodeCounter++) {
            nodes.add(new Node(nodeCounter));
        }
        for (int a0 = 0; a0 < e; a0++){
            int x = in.nextInt();
            int y = in.nextInt();
            int r = in.nextInt();
            
            Node source = nodes.get(x - 1);
            Node destination = nodes.get(y - 1);
            
            source.connections.add(new Connection(r, destination.number, 1));
            destination.connections.add(new Connection(1000 - r, source.number, 1));
        }
        
        // find cycles
        for (int counter = 0; counter < n; counter++) {
            Node sourceNode = nodes.get(counter);
            //System.out.println("Finding cycles for node: " + counter);
            List<Path> paths = new LinkedList<Path>();
            Path firstPath = new Path(sourceNode.number);
            paths.add(firstPath);
            while (!paths.isEmpty()) {
                Path currentPath = paths.remove(0);
                //System.out.println("Growing path: " + currentPath.toString());
                Node currentNode = nodes.get(currentPath.getLastNode());
                //System.out.println("Last node: " + currentNode.number);
                //System.out.println("Connections: " + currentNode.connections.size());
                
                for (int cCounter = 0; cCounter < currentNode.connections.size(); cCounter++) {
                    Connection connection = currentNode.connections.get(cCounter);
                    //System.out.println("Checking connection to: " + connection.destination);
                    if (connection.originalTimesToVisit == 1) {
                        int destination = connection.destination;
                        if (!currentPath.isNodeInPath(destination)) {
                            Path newPath = new Path(currentPath);
                            newPath.addNodeToPath(destination, connection.cost);
                            paths.add(newPath);
                        }
                        if ((destination == sourceNode.number) && currentPath.nodes.size() > 2) {
                            Path newPath = new Path(currentPath);
                            newPath.addNodeToPath(destination, connection.cost);
                            int timesToVisit = Connection.getTimesToVisit(newPath.currentCost);
                            if (timesToVisit > 0) {
                                sourceNode.addConnectionToSelf(newPath.currentCost);
                            }
                            //System.out.println(newPath.toString());
                        };
                    }
                }
            }
        }
        
        // find paths
        int[] totalResults = new int[10];
        for (int nCounter = 0; nCounter < n; nCounter++) {
            Node sourceNode = nodes.get(nCounter);
            int[] partialResults = new int[10];
            //System.out.println("Finding path for node: " + counter);
            List<Path> paths = new LinkedList<Path>();
            Path firstPath = new Path(sourceNode.number);
            paths.add(firstPath);
            while (!paths.isEmpty()) {
                Path currentPath = paths.remove(0);
                //System.out.println("Growing path: " + currentPath.toString());
                Node currentNode = nodes.get(currentPath.getLastNode());
                //System.out.println("Last node: " + currentNode.number);
                //System.out.println("Connections: " + currentNode.connections.size());
                
                for (int cCounter = 0; cCounter < currentNode.connections.size(); cCounter++) {
                    Connection connection = currentNode.connections.get(cCounter);
                    //System.out.println("Checking connection to: " + connection.destination);
                    int destination = connection.destination;
                    if (destination == currentNode.number && currentPath.canCircle()) {
                        for (int counter = 0; counter < connection.originalTimesToVisit; counter++) {
                            Path newPath = new Path(currentPath);
                            newPath.addNodeToPath(destination, connection.cost);
                            paths.add(newPath);
                        }
                    } else if (!currentPath.isNodeInPath(destination)) {
                        Path newPath = new Path(currentPath);
                        newPath.addNodeToPath(destination, connection.cost);
                        paths.add(newPath);
                    }
                }
                if (currentPath.nodes.size() > 1 && currentPath.isSolution()) {
                    int cost = currentPath.currentCost % 10;
                    if (partialResults[cost] == 0) partialResults[cost]++;
                }
            }
            for (int counter = 0; counter < 10; counter++) {
                //System.out.print(partialResults[counter]);
                totalResults[counter] += partialResults[counter];
            }
            //System.out.println();
        }
        for (int counter = 0; counter < 10; counter++) {
            System.out.println(totalResults[counter]);
        }
    }
}