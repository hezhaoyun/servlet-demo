package cn.ws38.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hezhaoyun
 * Create Data - 2017/5/16.
 */
public class ServletDemo extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InsertQueryDao insertQueryDao = new InsertQueryDao();

        insertQueryDao.save();
        String result = insertQueryDao.query();

        PrintWriter writer = response.getWriter();
        writer.println(result);
        writer.flush();
    }
}
