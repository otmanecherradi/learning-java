package me.otmane.assignment.services;

import me.otmane.assignment.core.ConnectionFactory;
import me.otmane.assignment.core.IDao;
import me.otmane.assignment.models.Branch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchRepo implements IDao<Branch> {
  private final Connection cnx;

  public BranchRepo() throws SQLException {
    cnx = ConnectionFactory.getInstance();
  }

  @Override
  public Branch insert(Branch branch) throws SQLException {
    PreparedStatement st = cnx.prepareStatement("insert into branches (name) values (?) returning pk;");
    st.setObject(1, branch.getName());

    st.execute();

    ResultSet lastPK = st.getResultSet();
    lastPK.next();
    long pk = lastPK.getLong("pk");
    st.close();

    branch.setPk(pk);

    return branch;
  }

  @Override
  public List<Branch> all() throws SQLException {
    ResultSet rs = cnx.createStatement().executeQuery("select pk, name from branches order by pk;");
    ArrayList<Branch> branches = new ArrayList<>();
    while (rs.next()) {
      branches.add(Branch.builder()
          .pk(rs.getLong("pk"))
          .name(rs.getString("name"))
          .build());
    }

    rs.close();

    return branches;
  }

  @Override
  public Branch get(long pk) throws SQLException {
    PreparedStatement st = cnx.prepareStatement("select pk, name from branches where pk = ?;");
    st.setObject(1, pk);

    st.execute();
    Branch branch = null;
    ResultSet rs = st.getResultSet();
    if (rs != null) {
      rs.next();
      branch = Branch.builder()
          .pk(rs.getLong("pk"))
          .name(rs.getString("name"))
          .build();
      rs.close();
    }
    st.close();
    return branch;
  }

  @Override
  public void update(Branch branch) throws SQLException {
    PreparedStatement st = cnx.prepareStatement("update branches set name = ? where pk = ?;");
    st.setObject(1, branch.getName());

    st.execute();
    st.close();
  }

  @Override
  public void delete(long pk) throws SQLException {
    PreparedStatement st = cnx.prepareStatement("delete from branches where pk = ?;");
    st.setObject(1, pk);

    st.execute();
    st.close();
  }
}
