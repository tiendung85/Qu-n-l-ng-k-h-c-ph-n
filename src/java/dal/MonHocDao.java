package dal;

import dal.DBConnect;
import model.MonHoc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonHocDao extends DBConnect {

    public List<MonHoc> getAllSubjects() throws SQLException {
        List<MonHoc> list = new ArrayList<>();
PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        String sql = "SELECT * FROM MonHoc";
        stmt = c.prepareStatement(sql);
        rs = stmt.executeQuery();
        while (rs.next()) {
            MonHoc mh = new MonHoc();
            mh.setMaMH(rs.getString("MaMH"));
            mh.setTenMH(rs.getString("TenMH"));
            mh.setSoTC(rs.getInt("SoTC"));
            mh.setSoTiet(rs.getInt("SoTiet"));
            mh.setKhoaPT(rs.getString("KhoaPT"));
            list.add(mh);
        }
} finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return list;
    }

    public MonHoc getSubjectById(String maMH) throws SQLException {
PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
        String sql = "SELECT * FROM MonHoc WHERE MaMH = ?";
        stmt = c.prepareStatement(sql);
        stmt.setString(1, maMH);
        rs = stmt.executeQuery();
        if (rs.next()) {
            MonHoc mh = new MonHoc();
            mh.setMaMH(rs.getString("MaMH"));
            mh.setTenMH(rs.getString("TenMH"));
            mh.setSoTC(rs.getInt("SoTC"));
            mh.setSoTiet(rs.getInt("SoTiet"));
            mh.setKhoaPT(rs.getString("KhoaPT"));
            return mh;
        }
} finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        }
        return null;
    }
}
