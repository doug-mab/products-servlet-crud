package br.com.market.controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.market.model.Product;
import br.com.market.model.User;
import br.com.market.service.DataBase;
import br.com.market.service.PriceHandlerService;
import br.com.market.service.Session;

@WebServlet("/product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Session<Product> db = DataBase.connect("products");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");

		if (action == null) {
			resp.sendRedirect("product?action=list");
		} else if (action.equals("list")) {
			req.setAttribute("products", db.findAll());
			req.getRequestDispatcher("WEB-INF/view/productList.jsp").forward(req, resp);
		} else if (action.equals("new")) {
			req.getRequestDispatcher("WEB-INF/view/newProductForm.jsp").forward(req, resp);
		} else if (action.equals("update")) {
			Product product = db.findById(Integer.parseInt(req.getParameter("id")));
			req.setAttribute("product", product);
			req.getRequestDispatcher("WEB-INF/view/updateProductForm.jsp").forward(req, resp);
		} else if (action.equals("delete")) {
			db.deleteById(Integer.parseInt(req.getParameter("id")));
			resp.sendRedirect("product?action=list");
//		} else if (action.equals("show")) {

		} else {
			resp.sendRedirect("product?action=list");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String paramPrice = req.getParameter("price");
		User loggedUser = (User) req.getSession().getAttribute("loggedUser");
		boolean anyError = false;

		if (action.equals("update")) {
			try {
				int id = Integer.parseInt(req.getParameter("id"));
				Product product = db.findById(id);
				product.setName(req.getParameter("name"));
				BigDecimal price = new PriceHandlerService().parsePriceToBigDecimal(paramPrice);
				product.setPrice(price);
				req.getSession().removeAttribute("exceptionMessage");
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
//				req.setAttribute("exceptionMessage", e.getMessage());
				req.getSession().setAttribute("exceptionMessage", e.getMessage());
				resp.sendRedirect(req.getHeader("Referer"));
				anyError = true;
			}

		} else if (action.equals("new")) {
			try {
				BigDecimal price = new PriceHandlerService().parsePriceToBigDecimal(paramPrice);
				db.create(new Product(req.getParameter("name"), price, loggedUser));
				req.getSession().removeAttribute("exceptionMessage");
			} catch (IllegalArgumentException e) {
				req.getSession().setAttribute("exceptionMessage", e.getMessage());
				req.getRequestDispatcher("WEB-INF/view/newProductForm.jsp").forward(req, resp);
				anyError = true;
			}
		}
		if (!anyError)
			resp.sendRedirect("product?action=list");
	}
}
