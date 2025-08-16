package com.controle.financeiro.domain.service;

import java.time.LocalDate;
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
import com.controle.financeiro.domain.service.strategy.impl.ControlePagamentoStrategy;
import com.controle.financeiro.dto.PagamentoDto;

@Service
public class PagamentoService{
    
    @Autowired
    private ContasAPagarRepository contasAPagarRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

        public Pagamento lancarPagamento(UUID id, PagamentoDto pagamentoDto){
            
            Optional<ContasAPagar> cap = contasAPagarRepository.findById(id);
            
            ContasAPagar conta = cap.get();
            Pagamento pg = new Pagamento();
            
            BeanUtils.copyProperties(pagamentoDto, pg);
            
            conta.setValorPago(pg.getValorDoPagamento());
            conta.setDataDePagamento(LocalDate.now());
            
            pg.setContasAPagar(conta);
            
            pg.setDataDeVencimento(conta.getDataDeVencimento());
            pg.setValorDivida(conta.getCapitalInicial());

            int diaVencimento = pg.getDataDeVencimento().getDayOfMonth();

            LocalDate novoVencimento = pg.getDataDeVencimento()
            .plusMonths(1)
            .withDayOfMonth(Math.min(diaVencimento, pg.getDataDeVencimento()
            .plusMonths(1)
            .lengthOfMonth()));

            ControlePagamentoStrategy controle = new ControlePagamentoStrategy();
            controle.processarPagamento(conta, pg);

            conta.setDataDeVencimento(novoVencimento);
           
            contasAPagarRepository.save(conta);
            return pagamentoRepository.save(pg);
    }
    public List<Pagamento> listaPagamentos(){
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return pagamentos;
    }

    
    public Optional<Pagamento> pagamentoId(UUID id){
        Optional<Pagamento> byIdPagamento = pagamentoRepository.findById(id);
        return byIdPagamento;
    }
    
    public Optional<Pagamento> atualizaPagamento(UUID id, PagamentoDto pagamentoDto){
        Optional<Pagamento> atualizar = pagamentoRepository.findById(id);
        Pagamento pagamento = atualizar.get();
        BeanUtils.copyProperties(pagamentoDto, pagamento);
        pagamentoRepository.save(pagamento);
        return atualizar;
    }
    
    public Optional<Pagamento> deletandoPagamento(UUID id){
        Optional<Pagamento> deleteById = pagamentoRepository.findById(id);
        pagamentoRepository.deleteById(id);
        return deleteById;
    }
}
