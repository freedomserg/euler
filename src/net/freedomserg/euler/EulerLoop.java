package net.freedomserg.euler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class EulerLoop {

    private static Map<Integer, List<Integer>> adjacencyLists;
    private static List<Integer> cycle = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        readGraph();

        for (Map.Entry<Integer, List<Integer>> entry : adjacencyLists.entrySet()) {
            if (entry.getValue().size() % 2 != 0) {
                System.out.println("NONE");
                return;
            }
        }

        getEulerCycleIfExists(1);

        if (!cycle.containsAll(adjacencyLists.keySet())) {
            System.out.println("NONE");
            return;
        }

        for (Integer edge : cycle) {
            System.out.print(edge + " ");
        }

    }

    private static void getEulerCycleIfExists(int node) {
        List<Integer> adjList = adjacencyLists.get(node);
        if (!adjList.isEmpty()) {
            ListIterator<Integer> iterator = adjList.listIterator();
            while (iterator.hasNext()) {
                int adjacency = iterator.next();
                iterator.remove();
                adjacencyLists.get(adjacency).remove(new Integer(node));
                cycle.add(node);
                getEulerCycleIfExists(adjacency);
            }
        }
    }

    private static void readGraph() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String[] countParams = input.split(" ");
        int topsCount = Integer.parseInt(countParams[0]);
        int edgesCount = Integer.parseInt(countParams[1]);

        adjacencyLists = new HashMap<>();
        for (int i = 1; i <= topsCount; i++) {
            adjacencyLists.put(i, new ArrayList<>());
        }

        for (int i = 0; i < edgesCount; i++) {
            input = reader.readLine();
            String[] tops = input.split(" ");
            int top1 = Integer.parseInt(tops[0]);
            int top2 = Integer.parseInt(tops[1]);
            List<Integer> top1AdjList = adjacencyLists.get(top1);
            top1AdjList.add(top2);
            List<Integer> top2AdjList = adjacencyLists.get(top2);
            top2AdjList.add(top1);
        }
    }

}

