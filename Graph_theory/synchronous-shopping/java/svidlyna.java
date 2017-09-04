import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {    
    private static final class ShopTime {
        private final int shopId;
        private final long time;
        
        public ShopTime(int shopId, long time) {
            this.shopId = shopId;
            this.time = time;
        }
    }
    
    private static final class Shop {
        private final int offeredProducts;     
        private final Map<Integer, Long> purchasedProductsToTime = new HashMap<>();

        public Shop(int offeredProducts) {
            this.offeredProducts = offeredProducts;
        }
        
        private final List<ShopTime> adjacentShops = new ArrayList<>();
    } 
    
    private static final class Bitville {
        private final Shop[] shops;
        private int targetProducts = 0;
        
        public Bitville(int n, int k) {
            this.shops = new Shop[n];            
            
            for (int i = 1; i <= k; i++) {
                targetProducts |= (1 << i);
            }
        }
        
        public void initShop(int shopId, int product) {
            shops[shopId] = new Shop(product);
        }
        
        public void initAdjacentShops(int shopId1, int shopId2, int time) {
            shops[shopId1].adjacentShops.add(new ShopTime(shopId2, time));  
            shops[shopId2].adjacentShops.add(new ShopTime(shopId1, time)); 
        }
        
        public long findMinimumTime(int end) {
            Queue<Shop> queue = new LinkedList<>();
            queue.add(shops[0]);
            shops[0].purchasedProductsToTime.put(shops[0].offeredProducts, 0L);
            shops[0].purchasedProductsToTime.put(0, 0L);
            
            while (!queue.isEmpty()) {
                Shop currentShop = queue.remove();
                
                Set<Integer> currentShopPurchases = currentShop.purchasedProductsToTime.keySet();                
                for (int i = 0; i < currentShop.adjacentShops.size(); i++) {
                    ShopTime adjacentShopTime = currentShop.adjacentShops.get(i);
                    Shop adjacentShop = shops[adjacentShopTime.shopId];
                    Set<Integer> adjacentShopPurchases = adjacentShop.purchasedProductsToTime.keySet();
                    
                    boolean found = false;                                    
                    for (int currentPurchase : currentShopPurchases) {
                        int adjacentPurchase = currentPurchase; 
                        Long existingTime = adjacentShop.purchasedProductsToTime.get(adjacentPurchase);
                        long expectedTime = adjacentShopTime.time + currentShop.purchasedProductsToTime.get(currentPurchase);  

                        if (existingTime == null || existingTime > expectedTime) {
                            adjacentShop.purchasedProductsToTime.put(adjacentPurchase, expectedTime);                  
                            found = true;
                        }
                    }
                    
                    if (adjacentShop.offeredProducts != 0) {
                        for (int currentPurchase : currentShopPurchases) {
                            int adjacentPurchase = currentPurchase | adjacentShop.offeredProducts; 
                            Long existingTime = adjacentShop.purchasedProductsToTime.get(adjacentPurchase);
                            long expectedTime = adjacentShopTime.time + currentShop.purchasedProductsToTime.get(currentPurchase);  

                            if (existingTime == null || existingTime > expectedTime) {
                                adjacentShop.purchasedProductsToTime.put(adjacentPurchase, expectedTime);                  
                                found = true;
                            }
                        }
                    }
                    
                    if (found) {
                        queue.add(adjacentShop);
                    }
                }
            }
            
            int[] products = toArray(shops[end].purchasedProductsToTime.keySet());
            long minimumTime = Long.MAX_VALUE;   
            
            for (int i = 0; i < products.length; i++) {
                for (int j = i + 1; j < products.length; j++) {
                    int givenProducts = products[i] | products[j];   
                    
                    if (givenProducts == targetProducts) {
                        long collectiveTime = Math.max(shops[end].purchasedProductsToTime.get(products[i]), shops[end].purchasedProductsToTime.get(products[j]));
                        minimumTime = Math.min(minimumTime, collectiveTime);
                    }
                }    
            }
            
            return minimumTime;
        }
        
        private int[] toArray(Set<Integer> set) {
            int[] ints = new int[set.size()];
            
            int i = 0;
            for (Integer elem : set) {
                ints[i++] = elem;
            }
            
            return ints;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] nmk = scanner.nextLine().split("\\s+");
        int n = Integer.parseInt(nmk[0]);
        int m = Integer.parseInt(nmk[1]);
        int k = Integer.parseInt(nmk[2]);
        
        Bitville bitville = new Bitville(n, k);
        for (int i = 0; i < n; i++) {
            String[] ta = scanner.nextLine().split("\\s+");
            int numOfProducts = Integer.parseInt(ta[0]);
            
            int product = 0;            
            for (int j = 1; j <= numOfProducts; j++) {
                int a = Integer.parseInt(ta[j]);
                product |= (1 << a);
            }
            bitville.initShop(i, product);
        }
        
        for(int i = 0; i < m; i++) {
            String[] roadsLine = scanner.nextLine().split("\\s+");
            int shop1 = Integer.parseInt(roadsLine[0]);
            int shop2 = Integer.parseInt(roadsLine[1]);
            int time = Integer.parseInt(roadsLine[2]);
            
            bitville.initAdjacentShops(shop1 - 1, shop2 - 1, time);
        }
        
        System.out.println(bitville.findMinimumTime(n - 1));
    }
}