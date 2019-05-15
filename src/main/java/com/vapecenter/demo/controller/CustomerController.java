package com.vapecenter.demo.controller;

import com.vapecenter.demo.models.*;
import com.vapecenter.demo.models.AboutUs;
import com.vapecenter.demo.models.Cart;
import com.vapecenter.demo.models.Products;
import com.vapecenter.demo.models.Users;
import com.vapecenter.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import java.util.logging.Logger;

@Controller
public class CustomerController {
    private ArrayList<Cart> cartList = new ArrayList<>();

    public CustomerController(){

        /*productsList.add(new Products(1, "Supervape", 5000000));
        productsList.add(new Products(2, "Crapvape", 5));
        productsList.add(new Products(3, "Bluevape", 550));
        productsList.add(new Products(4, "Ecovape", 320));
        productsList.add(new Products(5, "DSBvape", 1500));*/

    }

    @Autowired
    CustomerService customerService;

    private final String CART = "cart";
    private final String CHECKOUT = "checkout";
    private final String DELIVERY = "delivery";
    private final String CATEGORY = "category";
    private final String SEARCH = "search";
    private final String SEARCHRESULT = "searchResult";
    private final String LISTPRODUCTS = "listProducts";

    Logger log = Logger.getLogger(CustomerController.class.getName());

    @GetMapping("/")
    public String index(HttpSession session, Model model){
        log.info("index called");
        ArrayList<Category> categories;

        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", cartList);
        }

        categories = customerService.getAllCategories();
        model.addAttribute("categories", categories);

        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", cartList);
        }

        return "index";
    }


    @GetMapping("/cart")
    public String cart(Model model, HttpSession session){
        log.info("Cart is called...");

        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", cartList);
        }

        double total = 0;

        List<Products> productsList = customerService.getProducts();

        List<Cart> cart = (List<Cart>) session.getAttribute("cart");

        log.info("" + cart.size());

        for(Cart c : cart){
            for(Products p : productsList){
                if (c.getProductId() == p.getProductId()) {
                    total = total + ( c.getAmount() * p.getPrice() );
                }
            }
        }

        model.addAttribute("carts", cart);
        model.addAttribute("products", productsList);
        model.addAttribute("total", total);

        return CART;
    }

    @RequestMapping(value = "/editCart", method = RequestMethod.POST)
    public String editCart(@RequestParam("productId")Integer productId, @RequestParam("amount")Integer amount, Model model, HttpSession session) throws Exception{
        log.info("editCart is called...");

        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", cartList);
        }

        List<Cart> cart = (List<Cart>) session.getAttribute("cart");

        for (Cart c : cart) {
            if(c.getProductId() == productId){
                c.setAmount(amount);
            }
        }

        session.removeAttribute("cart");
        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    @RequestMapping(value = "/deleteCart", method = RequestMethod.POST)
    public String deleteCart(@RequestParam("productId")Integer productId, Model model, HttpSession session) throws Exception {
        log.info("deleteCart is called...");

        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", cartList);
        }

        List<Cart> cart = (List<Cart>) session.getAttribute("cart");

        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProductId() == productId) {
                cart.remove(i);
            }
        }

        session.removeAttribute("cart");
        session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model){
        log.info("Checkout is called...");

        model.addAttribute("checkout", new Checkout());

        return CHECKOUT;
    }

    @PostMapping("/checkout")
    public String checkoutDone(@ModelAttribute Checkout checkout, Model model, HttpSession session){
        log.info("" + checkout.toString());

        session.setAttribute("checkout", checkout);
        Checkout sessionCheckout = (Checkout) session.getAttribute("checkout");

        log.info("Session: " + sessionCheckout.toString());

        return "redirect:/delivery";
    }

    @GetMapping("/delivery")
    public String delivery(Model model){
        log.info("Checkout is called...");

        model.addAttribute("delivery", new ShipingMethod());
        model.addAttribute("shippingMethods", customerService.getShippingMethods());

        return DELIVERY;
    }

    @PostMapping("/delivery")
    public String deliveryDone(@ModelAttribute ShipingMethod delivery, Model model, HttpSession session){
        log.info("" + delivery.toString());

        session.setAttribute("delivery", delivery);
        ShipingMethod sessionDelivery = (ShipingMethod) session.getAttribute("delivery");

        log.info("Session: " + sessionDelivery.toString());

        return "redirect:/";
    }

    @GetMapping("/listProducts")
    public String listProducts(Model model, Cart cart, HttpSession session) {
        log.info("listProducts called...");
        int pages;
        int currentPage = 1;

        ArrayList<Products> productList = customerService.getProducts();
        ArrayList<Products> list15 = new ArrayList<>();
        ArrayList<Integer> pageList = new ArrayList<>();

        pages = customerService.countPages(customerService.getProducts());

        pageList = customerService.getPageArray(pages);

        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", cartList);
        }

        if(productList.size()>15) {
            for(int i = 0; i<15;i++) {
                list15.add(productList.get(i));
            }
        }

        model.addAttribute("productList", list15);
        model.addAttribute("cart", cart);
        model.addAttribute("maxPages", pages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageArray", pageList);

        return "listProducts";
    }

    @GetMapping("/listProducts/{page}")
    public String listProducts(@PathVariable("page") int page, Model model, HttpSession session) {
        ArrayList<Products> list15 = new ArrayList<>();
        ArrayList<Products> productList = customerService.getProducts();
        int maxPages = customerService.countPages(productList);
        ArrayList<Integer> pageList = customerService.getPageArray(maxPages);
        log.info("listProducts called... currentPage="+page+" maxPages="+maxPages);

        if(page>=1) {
            /*if(customerService.modulus(productList) >= 1 && page == customerService.countPages(productList)) {
                for(int i = (page * 15) - 15; i < (page*15)-15+customerService.modulus(productList); i++) {
                    list15.add(productList.get(i));
                }
            }
            else {
                for (int i = (page * 15) - 15; i < page * 15; i++) {
                    list15.add(productList.get(i));
            }*/

            list15 = customerService.list15(productList, page);
        }



        model.addAttribute("productList", list15);
        model.addAttribute("currentPage", page);
        model.addAttribute("maxPages", maxPages);
        model.addAttribute("pageArray", pageList);


        return "listProducts";
    }
    @PostMapping("/listProducts/")
    public String listProducts(@RequestParam("page") int page) {

        return "redirect:/listProducts/"+page;
    }

    @PutMapping("/listProducts")
    public String listProducts(@ModelAttribute Cart cart, Model model, Cart cartNew, HttpSession session) {
        log.info("listProducts putmapping called...");

        //cartList.add(cart);

        List<Cart> sCart = (List<Cart>) session.getAttribute("cart");
        session.removeAttribute("cart");
        sCart.add(cart);
        session.setAttribute("cart", sCart);

        for (Cart testCart: cartList) {
            log.info(""+testCart.getProductId()+" amount:"+testCart.getAmount());
        }


        model.addAttribute("productList", customerService.getProducts());
        model.addAttribute("cart", cartNew);
        return "listProducts";
    }

    @GetMapping("viewProduct/{productId}")
    public String viewProduct(@PathVariable("productId") int productId, Model model, Cart cart, HttpSession session) {
        log.info("viewProduct called with id="+productId);

        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", cartList);
        }

        model.addAttribute("product", customerService.getProductById(productId));
        model.addAttribute("cart", cart);



        return "viewProduct";
    }

    @PutMapping("viewProduct/")
    public String viewProduct(@ModelAttribute Cart cart, Model model, HttpSession session) {
        log.info("viewProduct putmapping called with id="+cart.getProductId());
        Boolean check = false;

        for(int i = 0;i<cartList.size(); i++) {
            if(cart.getProductId()==cartList.get(i).getProductId()) {
                cartList.get(i).setAmount(cartList.get(i).getAmount()+cart.getAmount());
                check = true;
            }
        }
        if(check == false) {
            //cartList.add(cart);
            List<Cart> sCart = (List<Cart>) session.getAttribute("cart");
            session.removeAttribute("cart");
            sCart.add(cart);
            session.setAttribute("cart", sCart);
        }

        for (Cart testCart: cartList) {
            log.info(""+testCart.getProductId()+" amount:"+testCart.getAmount());
        }
        model.addAttribute("product",customerService.getProductById(cart.getProductId()));

        return "redirect:/viewProduct/"+cart.getProductId();
    }

    @GetMapping("/category/{categoryId}")
    public String category(@PathVariable("categoryId") int categoryId, Model model, Cart cart, HttpSession session) {
        log.info("category called...");

        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", cartList);
        }

        /*ArrayList<Products> test = new ArrayList<>();
        test = customerService.getProducts();
        log.info(test.get(1).getProductId()+"");*/
        model.addAttribute("productList", customerService.getProductsByCategory(categoryId));
        model.addAttribute("cart", cart);
        model.addAttribute("categoryName", customerService.getCategoryById(categoryId).getCategoryName());

        return CATEGORY;
    }

    @PutMapping("/category")
    public String category(@ModelAttribute Cart cart, Model model, Cart cartNew, HttpSession session) {
        log.info("category putmapping called...");

        //cartList.add(cart);

        int categoryId = customerService.getProductById(cart.getProductId()).getFk_categoryId();

        List<Cart> sCart = (List<Cart>) session.getAttribute("cart");
        session.removeAttribute("cart");
        sCart.add(cart);
        session.setAttribute("cart", sCart);

        for (Cart testCart: cartList) {
            log.info(""+testCart.getProductId()+" amount:"+testCart.getAmount());
        }


        model.addAttribute("productList", customerService.getProducts());
        model.addAttribute("cart", cartNew);
        return "redirect:/" + CATEGORY + "/" + categoryId;
    }

    @GetMapping("/search")
    public String search(Model model){
        log.info("search called...");

        return SEARCH;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam("searchProduct")String searchProduct, Model model, HttpSession session) throws  Exception{
        log.info("search requestmapping called");
        log.info("search word" + searchProduct);

        if(session.getAttribute("cart") == null){
            session.setAttribute("cart", cartList);
        }

        List<Products> searchResult = customerService.searchProduct(searchProduct);

        log.info("controller searchProduct result: " + searchResult.size());

        model.addAttribute("searchProduct", searchProduct);
        model.addAttribute("products", searchResult);
        return SEARCHRESULT;

    }

    @PutMapping("/searchResult")
    public String searchResult(@ModelAttribute Cart cart, Model model, Cart cartNew, HttpSession session) {
        log.info("category putmapping called...");

        //cartList.add(cart);

        int categoryId = customerService.getProductById(cart.getProductId()).getFk_categoryId();

        List<Cart> sCart = (List<Cart>) session.getAttribute("cart");
        session.removeAttribute("cart");
        sCart.add(cart);
        session.setAttribute("cart", sCart);

        for (Cart testCart: cartList) {
            log.info(""+testCart.getProductId()+" amount:"+testCart.getAmount());
        }


        model.addAttribute("productList", customerService.getProducts());
        model.addAttribute("cart", cartNew);
        return "redirect:/" + LISTPRODUCTS;
    }

    @GetMapping("/aboutUs")
    public String aboutUs(Model model) {
        AboutUs aboutUs = customerService.getAboutInfo(1);
        log.info("" + aboutUs.getPictureLink());
        model.addAttribute("aboutUs", aboutUs);

        return "aboutUs";
    }

    @GetMapping("/paymentProcess")
    public String paymentProcess() {
        log.info("paymentProcess called...");

        return "paymentProcess";
    }

    @PostMapping("/creditcardAccept")
    public String paymentAccept() {
        log.info("paymentAccept postmapping called...");

        return "creditcardAccept";
    }
}
