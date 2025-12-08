package ch.ebynaqon.aoc.aoc25.day08;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

interface Day08 {

    static List<JunctionBox> parseProblem(RawProblemInput input) {
        return input.getLines().stream().map(Day08::parseLine).toList();
    }

    private static JunctionBox parseLine(String input) {
        String[] xYZ = input.split(",");
        if (xYZ.length != 3) {
            throw new IllegalArgumentException("Position of junction box does not have 3 coordinates: %s".formatted(input));
        }
        return new JunctionBox(
                Integer.parseInt(xYZ[0]),
                Integer.parseInt(xYZ[1]),
                Integer.parseInt(xYZ[2])
        );
    }

    static long solvePart1(RawProblemInput input, int maxConnections) {
        List<JunctionBox> junctionBoxes = parseProblem(input);
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < junctionBoxes.size() - 1; i++) {
            JunctionBox fromBox = junctionBoxes.get(i);
            for (int j = i + 1; j < junctionBoxes.size(); j++) {
                JunctionBox toBox = junctionBoxes.get(j);
                double distance = fromBox.distanceTo(toBox);
                connections.add(new Connection(i, j, distance));
            }
        }
        List<Connection> smallestDistanceConnections = connections.stream()
                .sorted(Comparator.comparing(Connection::distance))
                .toList();
        int[] setIndex = new int[junctionBoxes.size()];
        for (int i = 0; i < junctionBoxes.size(); i++) {
            setIndex[i] = i;
        }
        int nextSetId = 1;
        int numberOfNewConnections = 0;
        for (var connection : smallestDistanceConnections) {
            if (numberOfNewConnections >= maxConnections) {
                break;
            }
            int fromBox = connection.fromBoxId();
            int toBox = connection.toBoxId();
            var fromSet = setIndex[fromBox];
            var toSet = setIndex[toBox];
            if (fromSet != toSet) {
                for (int i = 0; i < junctionBoxes.size(); i++) {
                    if (setIndex[i] == toSet) {
                        setIndex[i] = fromSet;
                    }
                }
                numberOfNewConnections++;
            }
        }
        Collection<Long> setSizes = Arrays.stream(setIndex)
                .filter(i -> i > 0)
                .mapToObj(i -> (Integer) i)
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()))
                .values();
        return setSizes.stream()
                .sorted(Comparator.comparingLong(Long::longValue).reversed())
                .limit(3)
                .reduce(1L, (a, b) -> a * b);
    }

    static long solvePart2(RawProblemInput input) {
        List<JunctionBox> problem = parseProblem(input);
        return 0;
    }
}

record JunctionBox(int x, int y, int z) {

    public double distanceTo(JunctionBox other) {
        return Math.sqrt((other.x - x) * (other.x - x)
                         + (other.y - y) * (other.y - y)
                         + (other.z - z) * (other.z - z));
    }
}

record Connection(int fromBoxId, int toBoxId, double distance) {
}
