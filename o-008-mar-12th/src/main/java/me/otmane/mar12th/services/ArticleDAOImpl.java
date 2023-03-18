package me.otmane.mar12th.services;

import me.otmane.mar12th.models.Article;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAOImpl implements DatabaseDAO<Article> {
    private final Connection cnx;

    public ArticleDAOImpl(Connection cnx) {
        this.cnx = cnx;
    }

    @Override
    public Article insert(Article article) throws SQLException {
        PreparedStatement st = cnx.prepareStatement("insert into articles (title, price, description) values (?, ?, ?) returning pk;");
        st.setObject(1, article.getTitle());
        st.setObject(2, article.getPrice());
        st.setObject(3, article.getDescription());

        st.execute();

        ResultSet lastPK = st.getResultSet();
        lastPK.next();
        long pk = lastPK.getLong("pk");
        st.close();

        article.setPk(pk);

        return article;
    }

    @Override
    public List<Article> all() throws SQLException {
        ResultSet rs = cnx.createStatement().executeQuery("select pk, title, price, description from articles order by pk;");
        ArrayList<Article> articles = new ArrayList<>();
        while (rs.next()) {
            articles.add(Article.builder()
                    .pk(rs.getLong("pk"))
                    .title(rs.getString("title"))
                    .price(rs.getFloat("price"))
                    .description(rs.getString("description"))
                    .build());
        }

        rs.close();

        return articles;
    }

    @Override
    public Article get(long pk) throws SQLException {
        PreparedStatement st = cnx.prepareStatement("select pk, title, price, description from articles where pk = ?;");
        st.setObject(1, pk);

        st.execute();
        Article article = null;
        ResultSet rs = st.getResultSet();
        if (rs != null) {
            rs.next();
            article = Article.builder()
                    .pk(rs.getLong("pk"))
                    .title(rs.getString("title"))
                    .price(rs.getFloat("price"))
                    .description(rs.getString("description"))
                    .build();
            rs.close();
        }
        st.close();
        return article;
    }

    @Override
    public void update(Article article) throws SQLException {
        PreparedStatement st = cnx.prepareStatement("update articles set title = ?, price = ?, description = ? where pk = ?;");
        st.setObject(1, article.getTitle());
        st.setObject(2, article.getPrice());
        st.setObject(3, article.getDescription());
        st.setObject(4, article.getPk());

        st.execute();
        st.close();
    }

    @Override
    public void delete(long pk) throws SQLException {
        PreparedStatement st = cnx.prepareStatement("delete from articles where pk = ?;");
        st.setObject(1, pk);

        st.execute();
        st.close();
    }
}
