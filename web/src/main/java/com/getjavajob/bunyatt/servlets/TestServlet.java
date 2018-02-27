package com.getjavajob.bunyatt.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/index")
public class TestServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println(">>> SERVLET INIT");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();


        //todo реализация через сессию
//        AtomicInteger ai = new AtomicInteger(1);
//        HttpSession session = req.getSession(true); //True - вернет сессию пользователя либо созданную false - если сессия была, то ее вернет
//        if (session.getAttribute("counter") == null) {
//            session.setAttribute("counter", ai);
//        }
//        AtomicInteger ai2 = (AtomicInteger) session.getAttribute("counter");
//        out.print("you have visit this page " + ai2.getAndIncrement() + " times");


        //todo реализация через куки
//        int visitCount = 0;
//        Cookie[] cookies = req.getCookies();
//        for (Cookie cookie : cookies) {
//            if ("visitCount".equals(cookie.getName())) {
//                visitCount = Integer.valueOf(cookie.getValue());
//                visitCount++;
//            }
//        }
//        resp.addCookie(new Cookie("visitCount", "" + visitCount));
//        System.out.println(req.getQueryString());
//        out.print("you have visit this page " + visitCount + " times");


        out.close();


    }

    @Override
    public void destroy() {
        super.destroy();
        // this method for saving some important info to DB or Loger or etc...
    }
}
