package com.entrepiscoynazca.appweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import com.entrepiscoynazca.appweb.model.Cliente;
import com.entrepiscoynazca.appweb.model.Pago;
import com.entrepiscoynazca.appweb.model.Pedido;
import com.entrepiscoynazca.appweb.model.Proforma;
import com.entrepiscoynazca.appweb.model.Usuario;
import com.entrepiscoynazca.appweb.repository.ClienteRepository;
import com.entrepiscoynazca.appweb.repository.DetallePedidoRepository;
import com.entrepiscoynazca.appweb.repository.PagoRepository;
import com.entrepiscoynazca.appweb.repository.PedidoRepository;
import com.entrepiscoynazca.appweb.repository.ProformaRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.List;
import java.math.BigDecimal;
import java.util.Date;

@Controller
public class PagoController {

    private static final String VIEW_INDEX ="pago/create";
    private static String MODEL_VIEW="pago";
    private final ProformaRepository proformaData;
    private final ClienteRepository clienteData;
    private final PagoRepository pagoData;
    private final PedidoRepository pedidoData;
    private final DetallePedidoRepository detallePedidoData;
    
    public PagoController(ProformaRepository proformaData,
        ClienteRepository clienteData,
        PagoRepository pagoData,
        PedidoRepository pedidoData,
        DetallePedidoRepository detallePedidoData
        ){
        this.proformaData = proformaData;
        this.clienteData = clienteData;
        this.pagoData = pagoData;
        this.pedidoData = pedidoData;
        this.detallePedidoData = detallePedidoData;
    } 
    
    
    @GetMapping("/pago/create")
    public String index(Model model, HttpSession session){
        Usuario user = (Usuario)session.getAttribute("user"); 
        Cliente cliente = clienteData.findByUsuario(user);
        List<Proforma> listItems = proformaData.findItemsByUsuario(user);
        BigDecimal montoTotal = listItems.stream().
            map(n -> n.getPrecio().multiply(
                        new BigDecimal(n.getCantidad()))).
                    reduce(BigDecimal.ZERO, BigDecimal::add);
        Pago pago = new Pago();
        pago.setPaymentDate(new Date());
        pago.setMontoTotal(montoTotal);
        pago.setClienteId(cliente.getId());
        pago.setNombreTarjeta(
            cliente.getFirstName().concat(" ").
            concat(cliente.getLastName()));
        model.addAttribute(MODEL_VIEW, pago);
        return VIEW_INDEX;
    }   

    @PostMapping("/pago/create")
    public String createSubmitForm(Model model, 
        @Valid Pago objPago, BindingResult result ){
        if(result.hasFieldErrors()) {
            model.addAttribute("mensaje", "No se puede registrar pago");
        }else{
            Pedido pedido = new Pedido();
            pedido.setClienteId(objPago.getClienteId());
            pedido.setMontoTotal(objPago.getMontoTotal());
            pedido.setOrderDate(new Date());
            pedidoData.save(pedido);
            pagoData.save(objPago);
            model.addAttribute(MODEL_VIEW, objPago);
            model.addAttribute("mensaje", "Se registro su pago y se genero su pedido");
        }
        return VIEW_INDEX;
    }
}
