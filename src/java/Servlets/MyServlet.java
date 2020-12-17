/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import entities.Deal;
import entities.Product;
import entities.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.DealFacade;
import session.ProductFacade;
import session.UserFacade;

/**
 *
 * @author pupil
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/productList",
                                               "/productAdd","/productAddAction",
                                               "/productChange", "/productChangeAction",
                                               "/productBuy", "/productBuyAction"})
public class MyServlet extends HttpServlet {
    @EJB
    private ProductFacade productFacade;
    @EJB
    private UserFacade userFacade;
    @EJB
    private DealFacade dealFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
            String path = request.getServletPath();
            request.setCharacterEncoding("UTF-8");
            switch (path) {

                
//====================================================================================================================                
            case "/productAdd":
                request.setAttribute("info", "Добавить продукт");
                request.getRequestDispatcher("/WEB-INF/productAdd.jsp").forward(request, response);
                break; 
//====================================================================================================================                
            case "/productAddAction":
                String product_name = request.getParameter("name");
                Float price;
                Integer quantity;
                try{
                    price = Float.parseFloat(request.getParameter("price"));
                    quantity = Integer.parseInt(request.getParameter("quantity"));
                }catch(Exception e){
                    price = null;
                    quantity = null;
                }

                if("".equals(product_name) || product_name == null || price == null || quantity == null
                   || price <=0 || quantity < 0){
                    request.setAttribute("info", "Не все поля заполнены или заполнены неверно");
                    request.getRequestDispatcher("/WEB-INF/productAdd.jsp").forward(request, response);
                }
                else{
                    Product product = new Product(product_name, price, quantity);
                    productFacade.create(product);
                    request.setAttribute("info", "Продукт добавлен");
                    request.getRequestDispatcher("/WEB-INF/productAdd.jsp").forward(request, response);
                }
                break;

                
                
                
//==================================================================================================================== 
            case "/productChange":   
                request.setAttribute("productList", productFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/productChange.jsp").forward(request, response);
                break;      
//==================================================================================================================== 
            case "/productChangeAction":   
                boolean confirm = true;
                Product changing_product = productFacade.find(Integer.parseInt(request.getParameter("productId")));

                String changing_name = request.getParameter("name");
                Double changing_price = changing_product.getPrice();
                Integer changing_quantity = changing_product.getQuantity();
                changing_price = Double.parseDouble(request.getParameter("price"));
                changing_quantity = Integer.parseInt(request.getParameter("quantity"));
                if(changing_quantity == null  || changing_name == null || changing_name == "" || changing_price==null){
                    confirm = false;
                    request.setAttribute("info","Заполните все поля");
                }
                changing_product.setName(changing_name);
                changing_product.setPrice(changing_price);
                changing_product.setQuantity(changing_quantity);
                if(confirm){
                    productFacade.edit(changing_product);
                    request.setAttribute("info", "Продукт изменен");
                    request.setAttribute("productList", productFacade.findAll());
                    request.setAttribute("userList", userFacade.findAll());
                    request.getRequestDispatcher("/WEB-INF/productChange.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("info", "Не удалось изменить продукт");
                    request.getRequestDispatcher("/WEB-INF/productChange.jsp").forward(request, response);
                }
                break;           

                
                
                
                
//====================================================================================================================                
            case "/productList":
                request.setAttribute("productList", productFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/productList.jsp").forward(request, response);
                break;
                
                
                
                
                
//====================================================================================================================                
            case "/productBuy":
                request.setAttribute("productList", productFacade.findAll());
                request.getRequestDispatcher("/WEB-INF/productBuy.jsp").forward(request, response);
                break;           
//====================================================================================================================                
            case "/productBuyAction":
                request.setAttribute("productList", productFacade.findAll());
                Integer deal_product_id = Integer.parseInt(request.getParameter("productId"));
                User deal_user = (User) request.getSession().getAttribute("user");
                Integer deal_quantity = Integer.parseInt(request.getParameter("quantity"));

                User user = userFacade.find(deal_user.getId());
                Product product = productFacade.find(deal_product_id);

                if(deal_product_id == null || user == null || deal_quantity == null || deal_quantity <= 0){
                    request.setAttribute("info", "Заполните все поля!");
                    request.getRequestDispatcher("/WEB-INF/productBuy.jsp").forward(request, response);
                }
                else if(product.getQuantity() < deal_quantity){
                    request.setAttribute("info", "На скаладе недостаточно товара!");
                    request.getRequestDispatcher("/WEB-INF/productBuy.jsp").forward(request, response);
                }
                else if(user.getMoney() < product.getPrice()*deal_quantity){
                    request.setAttribute("info", "Недостаточно средств!");
                    request.getRequestDispatcher("/WEB-INF/productBuy.jsp").forward(request, response);
                }
                else{

                    Deal deal = new Deal(user, product, deal_quantity);
                    user.setMoney(user.getMoney()-product.getPrice()*deal_quantity);
                    product.setQuantity(product.getQuantity()-deal_quantity);
                    userFacade.edit(user);
                    request.getSession().setAttribute("user", user);
                    productFacade.edit(product);
                    dealFacade.create(deal);
                    request.setAttribute("info", "Вы купили продукт \""+product.getName()+"\" в количестве "+deal_quantity);
                    request.getRequestDispatcher("/WEB-INF/productBuy.jsp").forward(request, response);
                }
                break;
            default:
                throw new AssertionError();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
