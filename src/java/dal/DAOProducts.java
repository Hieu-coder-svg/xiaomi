/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Products;
import org.apache.tomcat.dbcp.dbcp2.PoolingConnection;

private class DAOProducts extends DBContext {

    /*
    private int ProductID;
    private String ProductName;
    private double ProductName;
    private int ProductName;
    private String ProductName, ImageURL;
    private Categories Category;
     */
    //lay ra tat ca san pham
    public List<Products> getAll() {

        List<Products> list = new ArrayList<>();
        String sql = "select * from Products";
        //chay lenhj truy van
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get Last Product in DB
    public Products getLast() {
        String sql = " select top 1 * from Products order by ProductID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //Get Products by CategoryID
    public List<Products> getProductByCategoryID(int id) {
        List<Products> list = new ArrayList<>();
        String sql = "  select * from Products where CategoryID= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //Get information of Product
    public Products getInfor(String name) {
        Products p = new Products();
        String sql = "select * from Products\n" + "where ProductName like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //search Product
    public List<Products> searchProduct(String namesearch) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products\n"
                + "where ProductName like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + namesearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    /*
     private int ProductID;
    private String ProductName;
    private double Price;
    private int Quantity;
    private String Description, ImageURL;
     */
    //insert new Product
    public void insertProduct(int id, String name, double price, int quantity, String description, String imageURL, int cid) {
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([ProductID]\n"
                + "           ,[ProductName]\n"
                + "           ,[Price]\n"
                + "           ,[Quantity]\n"
                + "           ,[Description]\n"
                + "           ,[ImageURL]\n"
                + "           ,[CategoryID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setInt(1, id);
            pre.setString(2, name);
            pre.setDouble(3, price);
            pre.setInt(4, quantity);
            pre.setString(5, description);
            pre.setString(6, imageURL);
            pre.setInt(7, cid);
            pre.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //get last productID in database
    public int getLastProduct() {
        String sql = "select top 1  * from Products\n"
                + "order by ProductID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int number = rs.getInt("ProductID");
                return number;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //delete product
    public void deleteProduct(int id) {
        String sql = "DELETE FROM Products WHERE ProductID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //get Product by ID
    public Products getProductByID(int id) {
        Products p = new Products();
        String sql = "select * from Products where ProductID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //get All Products where minprice = ? and maxprice = ?
    public List<Products> getByPrice(double min, double max) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products where Price >= ? and Price <= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, min);
            st.setDouble(2, max);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get All Products where price desc
    public List<Products> getPriceDesc() {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products order by Price desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get All Products where price asc
    public List<Products> getPriceAsc() {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products order by Price asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get All Products where cid = ? and price asc
    public List<Products> getPriceAscByCid(int cid) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products where CategoryID=? order by Price asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get All Products where cid = ? and price desc
    public List<Products> getPriceDescByCid(int cid) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products where CategoryID=? order by Price desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get All Products where cid = ? and price in range
    public List<Products> getPriceInRange(int cid, double min, double max) {
        List<Products> list = new ArrayList<>();
        String sql = "select * from Products where CategoryID=? and Price >= ? and Price <= ? order by Price";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            st.setDouble(2, min);
            st.setDouble(3, max);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get total Products
    public int getTotalProducts() {
        String sql = "select count(*) from Products";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int number = rs.getInt(1);
                return number;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //get offfetch next 10 products
    public List<Products> getAllOffFetchProduct(int index) {
        List<Products> list = new ArrayList();
        String sql = "select * from Products\n"
                + "order by ProductID\n"
                + "offset ? rows fetch next 10 rows only;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 10);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get offfectch home
    public List<Products> getAllOffFetchHome(int index) {
        List<Products> list = new ArrayList();
        String sql = "select * from Products\n"
                + "order by ProductID\n"
                + "offset ? rows fetch next 15 rows only;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 15);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get next 15 products by cid = ?
    public List<Products> getAllOffFetchByCid(int cid, int index, int row) {
        List<Products> list = new ArrayList();
        String sql = "select * from Products where CategoryID = ? order by ProductID offset ? rows fetch next ? rows only";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            st.setInt(2, (index - 1) * row);
            st.setInt(3, row);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Products p = new Products();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setDescription(rs.getString("Description"));
                p.setImageURL(rs.getString("ImageURL"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //get total Products by cid
    public int getTotalProducts(int cid) {
        String sql = "select count(*) from Products where CategoryID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int number = rs.getInt(1);
                return number;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    //update product
    public void updateProduct(String name, double price, int quantity, String description, String imageurl, int cid, int id) {
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [ProductName] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[Quantity] = ?\n"
                + "      ,[Description] = ?\n"
                + "      ,[ImageURL] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + " WHERE ProductID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setDouble(2, price);
            st.setInt(3, quantity);
            st.setString(4, description);
            st.setString(5, imageurl);
            st.setInt(6, cid);
            st.setInt(7, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //delete Product where Categoryid=?
    public void deleteProductByCid(int id) {
        String sql = "DELETE FROM [dbo].[Products]\n"
                + "      WHERE CategoryID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    //delete product
    public void deleteProductbyID(int id) {
        String sql = "DELETE FROM [dbo].[Products]\n"
                + "      WHERE ProductID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    

    public static void main(String[] args) {
        DAOProducts dao = new DAOProducts();
        //Products p = dao.getLast();
        // p = dao.getProductByID(5);
        //System.out.println(p.getProductName() + p.getProductID());

        //Products p = dao.getInfor("Vacuum Cleaner");
        //System.out.println(p.getProductName() + "," + p.getPrice());
        /*List<Products> list = dao.getPriceInRange(1, 100, 500);
        for (Products p : list) {
            System.out.println(p.getProductName());
            System.out.println(list.size());
        }
         */
        //int num1 = dao.getTotalProducts();
        /*List<Products> list = dao.getAllOffFetchProduct(1);
        for (Products products : list) {
            System.out.println(products.getProductName());
            System.out.println(list.size());
        }*/
        //int num = dao.getTotalProducts(1);
        //System.out.println(num);
        //dao.updateProduct("galaxy a60", 6000, 29, "ádhfasuhfuasfhiuwheshnfjkashd", "icon/s23.jpg", 1, 56);
        dao.deleteProduct(54);

        //System.out.println(num1);
    }
}
