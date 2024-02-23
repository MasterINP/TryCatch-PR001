package com.tronicmart.tronicmart.service.impl;

import com.tronicmart.tronicmart.model.Cart;
import com.tronicmart.tronicmart.model.CartDetalis;
import com.tronicmart.tronicmart.model.Product;
import com.tronicmart.tronicmart.model.User;
import com.tronicmart.tronicmart.payload.CartDetailDto;
import com.tronicmart.tronicmart.payload.CartDto;
import com.tronicmart.tronicmart.payload.CartHelp;
import com.tronicmart.tronicmart.payload.ProductDto;
import com.tronicmart.tronicmart.repository.CartDetailsRepository;
import com.tronicmart.tronicmart.repository.CartRepository;
import com.tronicmart.tronicmart.repository.ProductRepository;
import com.tronicmart.tronicmart.repository.UserRepository;
import com.tronicmart.tronicmart.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Service
public class CartServiceImpl implements CartService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CartDetailsRepository cartDetailsRepo;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public CartDto CreateCart(CartHelp cartHelp) {
        return null;
    }

    @Override
    public CartDto addProductToCart(CartHelp cartHelp) {

        int productId=cartHelp.getProductId();
        int quantity= cartHelp.getQuantity();
        String userEmail= cartHelp.getUserEmail();
        int total=0;
        AtomicReference<Integer> totalAmount =new AtomicReference<>(0);

        User user= this.userRepo.findByEmail(userEmail);

        Product product=this.productRepo.findById(Long.valueOf(productId)).orElseThrow();

        //create cart detail

        CartDetalis cartDetalis = new CartDetalis();
        cartDetalis.setProducts(product);
        cartDetalis.setQuantity(quantity);
        cartDetalis.setAmount((int) (product.getPrice()*quantity));

        Cart cart=user.getCart();

        if(cart==null){
            Cart cart1=new Cart();
            cart.setUser(user);

            int totalAmount2=0;



            CartDetalis cartDetalis1= new CartDetalis();
            cartDetalis1.setQuantity(quantity);
            cartDetalis1.setProducts(product);
            cartDetalis1.setAmount((int) (product.getPrice()*quantity));
            totalAmount2= cartDetalis1.getAmount();


            List<CartDetalis> pro=cart.getCartDetalis();
            pro.add(cartDetalis1);
            cart1.setCartDetalis(pro);
            cart1.setTotalAmount(totalAmount2);
            cartDetalis1.setCart(cart1);

//            for (CartDetalis i:pro ) {
//                Product p=i.getProducts();
//                p.setImg(decompressBytes(p.getImg()));
//            }
            CartDto map = this.modelMapper.map(cart1, CartDto.class);
            List<CartDetailDto> cartDetalis2 = map.getCartDetalis();


            for (CartDetailDto i:cartDetalis2 ) {
                ProductDto p=i.getProducts();
                p.setImg(decompressBytes(p.getImg()));
            }
            map.setCartDetalis(cartDetalis2);
            return map;



        }

        cartDetalis.setCart(cart);


        List<CartDetalis> list=cart.getCartDetalis();

        AtomicReference<Boolean> flag=new AtomicReference<>(false);

        List<CartDetalis> newProduct = list.stream().map((i) -> {
            if (i.getProducts().getProductId() == productId) {
                i.setQuantity(quantity);
                i.setAmount((int) (i.getQuantity() * product.getPrice()));
                flag.set(true);
            }
            totalAmount.set(totalP(i.getAmount(),totalAmount.get()));

            return i;
        }).collect(Collectors.toList());

        if (flag.get()){
            list.clear();
            list.addAll(newProduct);

        }else {

            cartDetalis.setCart(cart);
            totalAmount.set(totalAmount.get()+(int) (quantity*product.getPrice()));
            list.add(cartDetalis);

        }
        cart.setCartDetalis(list);
        cart.setTotalAmount(totalAmount.get());
        System.out.println(cart.getTotalAmount());
        Cart save = this.cartRepo.save(cart);

        CartDto map = this.modelMapper.map(save, CartDto.class);
        List<CartDetailDto> cartDetalis1 = map.getCartDetalis();


        for (CartDetailDto i:cartDetalis1 ) {
            ProductDto p=i.getProducts();
            p.setImg(decompressBytes(p.getImg()));
        }
        map.setCartDetalis(cartDetalis1);
        return map;
    }

    @Override
    public CartDto GetCart(String userEmail) {
        User user = this.userRepo.findByEmail(userEmail);
        Cart byUser = this.cartRepo.findByUser(user);



        // img decompressBytes
        CartDto map = this.modelMapper.map(byUser, CartDto.class);
        List<CartDetailDto> cartDetalis1 = map.getCartDetalis();


        for (CartDetailDto i:cartDetalis1 ) {
            ProductDto p=i.getProducts();
            p.setImg(decompressBytes(p.getImg()));
        }
        map.setCartDetalis(cartDetalis1);
        return map;
    }

    @Override
    public void RemoveById(Integer ProductId, String userEmail) {
        User user = this.userRepo.findByEmail(userEmail);

        Product product = this.productRepo.findById(Long.valueOf(ProductId)).orElseThrow();
        Cart cart =this.cartRepo.findByUser(user);

        CartDetalis byProductsAndCart = this.cartDetailsRepo.findByProductsAndCart(product, cart);
        int amount = byProductsAndCart.getAmount();
        cart.setTotalAmount(cart.getTotalAmount()-amount);
        this.cartRepo.save(cart);

        this.cartDetailsRepo.delete(byProductsAndCart);


    }



    public Product changeImg(Product product){

        product.setImg(decompressBytes(product.getImg()));

        System.out.println("hello");
        return product;
    }

    public int totalP(int t1, int total){
        return total+t1;
    }



    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
