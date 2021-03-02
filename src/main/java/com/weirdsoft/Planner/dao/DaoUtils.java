package com.weirdsoft.Planner.dao;

import com.weirdsoft.Planner.Configuration;

import java.sql.*;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

public class DaoUtils {
    public static <T> T executeAndMap(String sql, Function<ResultSet,T> mapper, Consumer<PreparedStatement> psEnricher){
        T result = null;
        try (Connection con = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USERNAME, Configuration.DB_PASSWORD)){
            PreparedStatement ps = con.prepareStatement(sql);
            psEnricher.accept(ps);
            ResultSet rs = ps.executeQuery();
            result = mapper.apply(rs);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static UUID uuidMapper(ResultSet rs){
        String result = "";
        try {
            if(rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return UUID.fromString(result);
    }

    public static int integerMapper(ResultSet rs){
        String result = "";
        try {
            if(rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Integer.getInteger(result);
    }
}
