package me.otmane;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection cnx;
        try {
            cnx = ConnectionFactory.getInstance();

            /*Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("select schemaname, tablename from pg_catalog.pg_tables");
            while (rs.next()) {
                System.out.println(rs.getString(1) + "." + rs.getString(2));
            }
            rs.close();
            st.close();*/

            DatabaseDAO<Article> articleDAO = new ArticleDAOImpl(cnx);

            Article article = Article.builder()
                    .title("Apple Macbook Pro")
                    .price(45000)
                    .description("Apple finest Macbook Pro ever made")
                    .build();

            Article insertedArticle = articleDAO.insert(article);
            System.out.println(insertedArticle);

            Article article2 = articleDAO.get(insertedArticle.getPk());
            System.out.println(article2);

            article2.setPrice(43000);
            articleDAO.update(article2);
            System.out.println(article2);

            // articleDAO.delete(article2.getPk());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}