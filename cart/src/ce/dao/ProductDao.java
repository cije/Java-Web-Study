package ce.dao;

import ce.bean.Product;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public Product getProduct(int id) {
        Product product = null;
        Entity where = Entity.create("product").set("id", id);
        try {
            product = Db.use().find(where, Product.class).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    public List<Product> list() {
        List<Product> list = new ArrayList<>();
        try {
            Entity where = Entity.create("product");
            list = Db.use().findAll(where, Product.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        ProductDao pd = new ProductDao();
        List<Product> list = pd.list();
        for (Product product : list) {
            System.out.println(product);
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
        }
        System.out.println();
        Product product = pd.getProduct(1);
        System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice());
    }
}
