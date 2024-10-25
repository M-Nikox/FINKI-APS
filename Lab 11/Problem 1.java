/*
 * Дадена е една мапа со еднонасочни патишта меѓу N градови во Македонија. За секој пат се знае должината на патот, почетниот и крајниот град. Да се најде вкупната должина на минималниот пат од еден до друг град и назад, и да се испечатат патеките со градовите низ кои треба да се помине напред и во обратен правец. 


Мапата со патишта е претставена како насочен тежински граф. Во првиот ред од влезот е даден број на градови N. Во вториот ред од влезот е даден бројот на насочени патишта. Во следните редови се дадени патиштата во облик: реден број на почетниот град, име на почетниот град, реден број на крајниот град, име на крајниот град, должина на патот. Во последните два реда се дадени имињата на градовите меѓу кои треба да се најде најкраткиот пат. 


Во првиот ред од излезот треба да се испечатат градовите низ кои треба да се помине за да се стигне по минимален пат од почетниот до крајниот град, а во вториот ред градовите низ кои треба да се помина за минималниот пат назад. Во последниот ред од излезот треба да се испечати должината на вкупниот минимален пат во двата правци. 

------------------------------------------------------------------------------

You are given a map with one way roads between N cities in Macedonia. For each road you are given the length of the road, the starting city and the destination city. Find the minimal distance from one city to another and back, and print the roads you are going to take.

The map with roads is given as a directed weighted graph.

Input: In the first line you are given the number of cities N. In the second line you are given the number of roads M. In the following M lines you are given the roads as following: the number of the starting city, the name of the starting city, the number of the destination city, the name of the destination city, length of the road. In the last 2 lines you are given the names of the starting and the destination city.

Output: In the first line of the output you are supposed to print the route which you are supposed to take starting from the starting city and ending at the destination city. In the second line you are supposed to print the route which you are supposed to take starting from the destination city and ending at the starting city. In the last line of the output you are supposed to print a single integer representing the total length of the routes.
 */

 import java.util.*;

class City {
    String name;
    List<Road> roads;

    public City(String name) {
        this.name = name;
        this.roads = new ArrayList<>();
    }
}

class Road {
    City destination;
    float length;

    public Road(City destination, float length) {
        this.destination = destination;
        this.length = length;
    }
}

public class prog {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt(); // Number of cities
        int M = scanner.nextInt(); // Number of roads

        Map<String, City> cityMap = new HashMap<>();

        for (int i = 0; i < M; i++) {
            int startCityNum = scanner.nextInt();
            String startCityName = scanner.next();
            int destCityNum = scanner.nextInt();
            String destCityName = scanner.next();
            float length = scanner.nextFloat();

            City startCity = cityMap.computeIfAbsent(startCityName, City::new);
            City destCity = cityMap.computeIfAbsent(destCityName, City::new);

            startCity.roads.add(new Road(destCity, length));
        }

        String startCityName = scanner.next();
        String destCityName = scanner.next();

        City startCity = cityMap.get(startCityName);
        City destCity = cityMap.get(destCityName);

        Map<City, Float> startDistances = dijkstra(startCity);
        Map<City, Float> destDistances = dijkstra(destCity);

        List<Road> forwardRoute = buildRoute(startCity, destCity, startDistances, destDistances);
        List<Road> backwardRoute = buildRoute(destCity, startCity, destDistances, startDistances);

        printRoute(forwardRoute);
        printRoute(backwardRoute);

        float totalLength = startDistances.get(destCity) + destDistances.get(startCity);
        System.out.println(totalLength);
    }

    private static Map<City, Float> dijkstra(City startCity) {
        Map<City, Float> distances = new HashMap<>();
        PriorityQueue<City> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        distances.put(startCity, 0.0f);
        priorityQueue.offer(startCity);

        while (!priorityQueue.isEmpty()) {
            City currentCity = priorityQueue.poll();

            for (Road road : currentCity.roads) {
                City neighbor = road.destination;
                float newDistance = distances.get(currentCity) + road.length;

                if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    priorityQueue.offer(neighbor);
                }
            }
        }

        return distances;
    }

    private static List<Road> buildRoute(City startCity, City destCity, Map<City, Float> startDistances, Map<City, Float> destDistances) {
        List<Road> route = new ArrayList<>();
        City currentCity = startCity;

        while (currentCity != destCity) {
            float minDist = Float.MAX_VALUE;
            Road minRoad = null;

            for (Road road : currentCity.roads) {
                float totalDist = startDistances.get(currentCity) + road.length + destDistances.get(road.destination);

                if (totalDist < minDist) {
                    minDist = totalDist;
                    minRoad = road;
                }
            }

            route.add(minRoad);
            currentCity = minRoad.destination;
        }

        return route;
    }

    private static void printRoute(List<Road> route) {
        for (Road road : route) {
            System.out.print(road.destination.name + " ");
        }
        System.out.println();
    }
}
