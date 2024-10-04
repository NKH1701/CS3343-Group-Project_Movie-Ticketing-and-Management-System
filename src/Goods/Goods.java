package Goods;

import java.util.Objects;

public abstract class Goods {
     private final String name;
     private final double price;
     public Goods(String name, double price) {
          this.name =name;
          this.price = price;
     }

     @Override
     public boolean equals(Object object) {
          if (this == object) {
               return true;
          }
          if (object == null || getClass() != object.getClass()) {
               return false;
          }
          Goods goods = (Goods) object;
          return Double.compare(price, goods.price) == 0 && Objects.equals(name, goods.name);
     }

     @Override
     public int hashCode() {
          return Objects.hash(name, price);
     }

     //     abstract List<T> getALlGoods();
}

