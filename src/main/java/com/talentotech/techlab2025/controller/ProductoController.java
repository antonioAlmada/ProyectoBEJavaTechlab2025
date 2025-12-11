package com.talentotech.techlab2025.controller;

import com.talentotech.techlab2025.model.Producto;
import com.talentotech.techlab2025.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {

        List<Producto> productos = productoService.listarProducto();

        return ResponseEntity.status(HttpStatus.OK).body(productos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable Long id) {

        Producto producto = productoService.buscarProductoPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body(producto);
    }
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto nuevoProducto) {

        Producto producto = productoService.crearProducto(nuevoProducto);

        return ResponseEntity.status(HttpStatus.CREATED).body(producto);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto nuevosDatos) {

        Producto producto = productoService.actualizarProducto(id, nuevosDatos);

        return ResponseEntity.status(HttpStatus.OK).body(producto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {

        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }
}
