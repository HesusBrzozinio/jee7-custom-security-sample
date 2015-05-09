package pl.kb.controllers;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.dto.Book;
import model.dto.valueholder.Quantity;

@ManagedBean(name = "shoppingCart")
@SessionScoped
public class ShoppingCartController {

	private Map<Book, Quantity> books = new HashMap<Book, Quantity>();
	private BigDecimal totalPrice = BigDecimal.ZERO;
	private int cartItemQuantity;

	public void addBook(final Book book, final int quantity) {
		updateCart(book, quantity);
		calculatePriceAndQuantity();
	}

	private void updateCart(final Book book, final int quantity) {
		if (books.containsKey(book)) {
			Quantity q = books.get(book);
			int newQuantity = q.getValue() + quantity;
			q.setValue(newQuantity);
			books.put(book, q);
		} else {
			final Quantity q = new Quantity();
			q.setValue(quantity);
			books.put(book, q);
		}
	}

	private void calculatePriceAndQuantity() {
		totalPrice = BigDecimal.ZERO;
		cartItemQuantity = 0;
		for (Book b : books.keySet()) {
			final BigDecimal itemPrice = b.getPrice();
			final Quantity itemQueantity = books.get(b);
			final BigDecimal price = itemPrice.multiply(new BigDecimal(
					itemQueantity.getValue()));

			totalPrice = totalPrice.add(price);
			cartItemQuantity = cartItemQuantity + itemQueantity.getValue();
		}
	}

	public Map<Book, Quantity> getBooks() {
		return books;
	}

}
