package ru.netology.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.exceptions.AlreadyExistsException;
import ru.netology.exceptions.NotFoundException;

public class ShopRepositoryTest {
    private Product product1;
    private Product product2;
    private Product product3;
    private ShopRepository repo;

    @BeforeEach
    public void setUpRepository() {
        product1 = new Product(1, "Чай", 200);
        product2 = new Product(2, "Кофе", 270);
        product3 = new Product(3, "Молоко", 180);

        repo = new ShopRepository();
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
    }
    
    @Test
    public void shouldRemoveExistingProduct() {
        repo.remove(1);

        Product[] expected = {product2, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenProductNonExistent() {

        Assertions.assertThrows(NotFoundException.class, () ->
                repo.remove(50));
    }

    @Test
    public void shouldAddNewProduct() {
        Product product4 = new Product(4, "Компот", 145);
        repo.add(product4);

        Product[] expected = {product1, product2, product3, product4};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenProductAlreadyExists() {

        Assertions.assertThrows(AlreadyExistsException.class, () ->
                repo.add(product1));
    }
}
