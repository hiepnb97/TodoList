/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import java.sql.*;
import java.util.ArrayList;
import model.Todo;


/**
 *
 * @author hiepn
 */
public class TodoDAO extends DBContext{
    
    public void add(Todo todo) throws SQLException {
        String sql = "INSERT INTO Todo(title, status) VALUES (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, todo.getTitle());
        pstmt.setBoolean(2, todo.isStatus());
        pstmt.execute();
    }
    
    public ArrayList<Todo> getAll() throws SQLException {
        ArrayList<Todo> list = new ArrayList<>();
        String sql = "SELECT * FROM Todo";
        Statement pstmt = connection.createStatement();
        ResultSet rs = pstmt.executeQuery(sql);
        while(rs.next()) {
            Todo todo = new Todo(
                    rs.getInt("id"),
                    rs.getString("title"), 
                    rs.getBoolean("status")
            );
            list.add(todo);
        }
        return list;
    }
    
    public Todo getById(int id) throws SQLException {
        Todo todo = null;
        String sql = "SELECT * FROM Todo WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        
        if(rs.next()) {
            todo = new Todo(
                    rs.getInt("id"),
                    rs.getString("title"), 
                    rs.getBoolean("status")
            );
        }
        
        rs.close();
        pstmt.close();
        return todo;
    }
    
    public void update(Todo todo) throws SQLException {
        String sql = "UPDATE Todo SET title = ?, status = ? WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, todo.getTitle());
        pstmt.setBoolean(2, todo.isStatus());
        pstmt.setInt(3, todo.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }
    
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Todo WHERE id = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        pstmt.close();
    }
}
