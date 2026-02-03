/* Name - Harsh Patel
   PRN - 123B1F029 */

import java.util.*;

class Order {
    int orderId;
    long timestamp;
    double value;

    Order(int orderId, long timestamp, double value) {
        this.orderId = orderId;
        this.timestamp = timestamp;
        this.value = value;
    }
}

public class MergeSortOrders {

    static void merge(List<Order> arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        List<Order> L = new ArrayList<>();
        List<Order> R = new ArrayList<>();

        for (int i = 0; i < n1; i++)
            L.add(arr.get(left + i));
        for (int j = 0; j < n2; j++)
            R.add(arr.get(mid + 1 + j));

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L.get(i).timestamp <= R.get(j).timestamp) {
                arr.set(k++, L.get(i++));
            } else {
                arr.set(k++, R.get(j++));
            }
        }

        while (i < n1) {
            arr.set(k++, L.get(i++));
        }

        while (j < n2) {
            arr.set(k++, R.get(j++));
        }
    }

    static void mergeSort(List<Order> arr, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    static List<Order> generateOrders(int n) {
        List<Order> orders = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int orderId = i + 1;
            long timestamp = rand.nextInt(1_000_000_000) + 1;
            double value = rand.nextInt(100_000) / 100.0;
            orders.add(new Order(orderId, timestamp, value));
        }
        return orders;
    }

    static void printOrders(List<Order> orders, int limit) {
        for (int i = 0; i < Math.min(limit, orders.size()); i++) {
            Order o = orders.get(i);
            System.out.println(
                "Order " + o.orderId +
                " | Timestamp: " + o.timestamp +
                " | Value: " + o.value
            );
        }
    }

    public static void main(String[] args) {
        int n = 20;
        List<Order> orders = generateOrders(n);

        System.out.println("Orders before sorting:");
        printOrders(orders, 10);

        mergeSort(orders, 0, n - 1);

        System.out.println("\nOrders after sorting by timestamp:");
        printOrders(orders, 10);
    }
}
