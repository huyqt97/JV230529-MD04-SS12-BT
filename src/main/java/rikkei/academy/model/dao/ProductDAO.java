package rikkei.academy.model.dao;

import rikkei.academy.model.entity.Product;
import rikkei.academy.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<Product> findAll() {
        List<Product> list = new ArrayList<>();
        Connection connection = null ;
        connection = ConnectionDB.openConnection();
        CallableStatement callableStatement = null;
        try {
            callableStatement = connection.prepareCall("{call PROC_GET_ALL_PRODUCT()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()){
               Product  product = new Product();
               product.setProductId(rs.getInt("productId"));
               product.setProductName(rs.getString("productName"));
               product.setPrice(rs.getFloat("price"));
               list.add(product) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    public Boolean create(Product product){
        Boolean isCheck = false ;
        Connection connection = null ;

        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement  = connection.prepareCall("{call PROC_CREATE_PRODUCT(?,?)}");
            callableStatement.setString(1,product.getProductName());
            callableStatement.setFloat(2,product.getPrice());
            int check =callableStatement.executeUpdate();
            if ( check > 0 ) {
                isCheck = true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return isCheck ;
    }

    public Boolean delete(Integer id){
        Boolean isCheck = false ;
        Connection connection = null ;

        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement  = connection.prepareCall("{call PROC_DELETE_PRODUCT(?)}");
            callableStatement.setInt(1,id);
            int check =callableStatement.executeUpdate();
            if ( check > 0 ) {
                isCheck = true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return isCheck ;
    }

    public Product findById(Integer id) {
        Connection connection = null ;
        Product product = new Product() ;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call PROC_SELECT_PRODUCT(?)}");
           callableStatement.setInt(1,id);
           ResultSet rs = callableStatement.executeQuery();
           while (rs.next()){
               product.setProductId(rs.getInt("productId"));
               product.setProductName(rs.getString("productName"));
               product.setPrice(rs.getFloat("price"));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return product ;
    }

    public Boolean update(Product product,Integer id){
        Boolean isCheck = false ;
        Connection connection = null ;

        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement  = connection.prepareCall("{call PROC_UPDATE_PRODUCT(?,?,?)}");
            callableStatement.setString(1,product.getProductName());
            callableStatement.setFloat(2,product.getPrice());
            callableStatement.setInt(3,id);
            int check =callableStatement.executeUpdate();
            if ( check > 0 ) {
                isCheck = true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return isCheck ;
    }


}
