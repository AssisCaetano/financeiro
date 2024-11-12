package com.controle.financeiro.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.repositore.ContasAPagarRepository;
import com.controle.financeiro.domain.repositore.PagamentoRepository;
import com.controle.financeiro.dto.PagamentoDto;

@Service
public class PagamentoService {
    
    @Autowired
    private ContasAPagarRepository contasAPagarRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;
    
        public Pagamento lancarPagamento(UUID id, PagamentoDto pagamentoDto){

        Optional<ContasAPagar> cap = contasAPagarRepository.findById(id);

        ContasAPagar conta = cap.get();

        Pagamento pg = new Pagamento();
        BeanUtils.copyProperties(pagamentoDto, pg);

        double saldoRestante = conta.getSaldoDevedor() - pg.getValorDoPagamento();

        conta.setSaldoDevedor(saldoRestante);
        conta.setValorPago(pg.getValorDoPagamento());
        conta.setDataDePagamento(pg.getDataPagamento());

        if("Pago".equals(conta.getStatus())){
            
        }
        conta.setStatus("Pago");
        contasAPagarRepository.save(conta);

        return pagamentoRepository.save(pg);
        
    }

    public List<Pagamento> listaPagamento(){
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return pagamentos;
    }
}
