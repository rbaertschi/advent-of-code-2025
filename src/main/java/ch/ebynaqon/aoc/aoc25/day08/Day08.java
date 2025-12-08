package ch.ebynaqon.aoc.aoc25.day08;

import ch.ebynaqon.aoc.helper.RawProblemInput;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparingLong;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

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
                .limit(maxConnections)
                .toList();
        int[] circuit = new int[junctionBoxes.size()];
        for (int boxId = 0; boxId < junctionBoxes.size(); boxId++) {
            circuit[boxId] = boxId;
        }
        for (var connection : smallestDistanceConnections) {
            int fromBox = connection.fromBoxId();
            int toBox = connection.toBoxId();
            var fromSet = circuit[fromBox];
            var toSet = circuit[toBox];
            if (fromSet != toSet) {
                for (int boxId = 0; boxId < junctionBoxes.size(); boxId++) {
                    if (circuit[boxId] == toSet) {
                        circuit[boxId] = fromSet;
                    }
                }
            }
        }
        Collection<Long> setSizes = stream(circuit)
                .boxed()
                .collect(groupingBy(identity(), counting()))
                .values();
        return setSizes.stream()
                .sorted(comparingLong(Long::longValue).reversed())
                .limit(3)
                .reduce(1L, (a, b) -> a * b);
    }

    static long solvePart2(RawProblemInput input) {
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
        int[] circuit = new int[junctionBoxes.size()];
        for (int boxId = 0; boxId < junctionBoxes.size(); boxId++) {
            circuit[boxId] = boxId;
        }
        for (var connection : smallestDistanceConnections) {
            int fromBox = connection.fromBoxId();
            int toBox = connection.toBoxId();
            var fromSet = circuit[fromBox];
            var toSet = circuit[toBox];
            if (fromSet != toSet) {
                for (int boxId = 0; boxId < junctionBoxes.size(); boxId++) {
                    if (circuit[boxId] == toSet) {
                        circuit[boxId] = fromSet;
                    }
                }
                if (isOneCircuit(circuit)) {
                    return junctionBoxes.get(fromBox).x() * junctionBoxes.get(toBox).x();
                }
            }
        }
        throw new IllegalArgumentException("No solution possible");
    }

    static boolean isOneCircuit(int[] circuit) {
        int reference = circuit[0];
        for (int i = 1; i < circuit.length; i++) {
            if (circuit[i] != reference) {
                return false;
            }
        }
        return true;
    }
}

record JunctionBox(long x, long y, long z) {

    public double distanceTo(JunctionBox other) {
        return Math.sqrt((other.x - x) * (other.x - x)
                         + (other.y - y) * (other.y - y)
                         + (other.z - z) * (other.z - z));
    }
}

record Connection(int fromBoxId, int toBoxId, double distance) {
}
