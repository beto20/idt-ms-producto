package org.example.service;

import org.example.model.ProductRequest;
import org.example.model.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService{
    private final List<ProductResponse> registeredProducts = new ArrayList<>();
    private long nextId = 1008L;

    @Override
    public synchronized List<ProductResponse> getProducts() {
        List<ProductResponse> products = new ArrayList<>();

        // 1. Electrónica / Móviles
        products.add(new ProductResponse(
                1000L,
                "Smartphone Pro Max 256GB",
                "Pantalla 6.7\" OLED, 120Hz, cámara triple de 48MP y chip A17",
                35,
                1199.99
        ));

        // 2. Cómputo / Periféricos
        products.add(new ProductResponse(
                1001L,
                "Mouse Gamer Ergonómico",
                "Sensor óptico de 26,000 DPI, 8 botones programables y RGB",
                150,
                59.90
        ));

        // 3. Audio / Wearables
        products.add(new ProductResponse(
                1002L,
                "Audífonos Noise Cancelling",
                "Cancelación activa de ruido, batería de 30 horas y audio Hi-Res",
                80,
                249.50
        ));

        // 4. Electrodomésticos / Hogar
        products.add(new ProductResponse(
                1003L,
                "Freidora de Aire Smart 5.5L",
                "Conexión Wi-Fi, 12 programas de cocción y panel táctil",
                22,
                129.00
        ));

        // 5. Monitores / Displays
        products.add(new ProductResponse(
                1004L,
                "Monitor UltraWide 34\"",
                "Resolución WQHD (3440x1440), panel IPS curva 144Hz HDR400",
                12,
                580.00
        ));

        // 6. Almacenamiento
        products.add(new ProductResponse(
                1005L,
                "SSD NVMe M.2 2TB",
                "Velocidad de lectura hasta 7300 MB/s PCIe Gen4 con disipador",
                200,
                165.75
        ));

        // 7. Accesorios / Conectividad
        products.add(new ProductResponse(
                1006L,
                "Hub USB-C 8 en 1",
                "Puertos HDMI 4K, USB 3.0, lector SD/MicroSD y Power Delivery 100W",
                95,
                42.30
        ));

        // 8. Mobiliario de Oficina
        products.add(new ProductResponse(
                1007L,
                "Escritorio Elevable Eléctrico",
                "Ajuste de altura motorizado de 70cm a 120cm con memoria de posiciones",
                8,
                389.99
        ));

        products.addAll(registeredProducts);
        return products;
    }

    @Override
    public synchronized boolean saveProduct(ProductRequest request) {
        if (request == null || request.getName() == null || request.getName().isBlank()
                || request.getDescription() == null || request.getPrice() <= 0
                || !Double.isFinite(request.getPrice()) || request.getStock() < 0) {
            return false;
        }
        registeredProducts.add(new ProductResponse(nextId++, request.getName().trim(),
                request.getDescription().trim(), request.getStock(), request.getPrice()));
        return true;
    }
}
