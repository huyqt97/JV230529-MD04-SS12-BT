package rikkei.academy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rikkei.academy.model.dao.ProductDAO;
import rikkei.academy.model.entity.Product;
import rikkei.academy.model.sercice.ProductService;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService = new ProductService();
    @RequestMapping("/product")
    public String listProduct(Model model){
        List<Product> list = productService.findAll();
        model.addAttribute("list", list) ;
        return "list-product";
    }

    @RequestMapping("/create")
    public String createProduct(Model model){
        Product product = new Product();
        model.addAttribute(product);
        return "create-product";
    }

    @RequestMapping("/save")
    public String save(@ModelAttribute("product") Product product) {
        productService.create(product);
        return "redirect:/product" ;
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        productService.delete(id);
        return "redirect:/product" ;
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Product product = productService.findById(id) ;
        model.addAttribute(product);
        return "edit-product" ;
    }

    @RequestMapping("/update")
    public String update(@ModelAttribute("product") Product product){
        productService.update(product, product.getProductId());
        return "redirect:/product";
    }
}
