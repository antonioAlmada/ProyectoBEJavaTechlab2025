package com.talentotech.techlab2025.service;

import com.talentotech.techlab2025.model.Producto;
import com.talentotech.techlab2025.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    public List<Producto> listarProducto() {

        List<Producto> productos = productoRepository.findAll();

        return productos;
    }
    public Producto buscarProductoPorId(Long id) {

        Producto producto = productoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No se encuentra el producto con la ID: " + id));

        return producto;
    }
    public Producto crearProducto(Producto nuevoProducto) {

        Producto creado = productoRepository.save(nuevoProducto);

        return creado;
    }
    public Producto actualizarProducto(Long id, Producto nuevosDatos) {

        Producto producto = productoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No se encuentra el producto con la ID: " + id));

        producto.setNombre(nuevosDatos.getNombre());
        producto.setPrecio(nuevosDatos.getPrecio());
        producto.setStock(nuevosDatos.getStock());

        return productoRepository.save(producto);
    }
    public void eliminarProducto(Long id) {

        Producto producto = productoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No se encuentra el producto con la ID: " + id));

        productoRepository.delete(producto);
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public Producto validarYReservar(Long idProducto, int stockPedido) {

        Producto producto = productoRepository.findById(idProducto).orElseThrow(() ->
                new EntityNotFoundException("No se encuentra el producto con la ID: " + idProducto));

        if (producto.getStock() < stockPedido) {
            throw new RuntimeException("No hay stock suficiente para el producto: " + producto.getNombre());
        }

        producto.setStock(producto.getStock() - stockPedido);
        return productoRepository.save(producto);
    }
    @Transactional(propagation = Propagation.MANDATORY)
    public Producto recuperarStock(Long id, int stockCantidad) {

        Producto producto = productoRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("No se encuentra el producto con la ID: " + id));

        producto.setStock(producto.getStock() + stockCantidad);

        return productoRepository.save(producto);
    }
}
