package com.controle.financeiro.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.financeiro.domain.model.ContasAPagar;
import com.controle.financeiro.domain.model.Pagamento;
import com.controle.financeiro.domain.repositore.ContasAPagarRepository;
import com.controle.financeiro.domain.repositore.PagamentoRepository;
import com.controle.financeiro.domain.service.strategy.impl.ControleStrategy;
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
            
            pg.setDataDeVencimento(conta.getDataDeVencimento());
            pg.setValorDivida(conta.getSaldoDevedor());

            ControleStrategy controle = new ControleStrategy();
            controle.processarPagamento(conta, pg);

        //     BigDecimal novoValorDoJuros;
        //     BigDecimal resultado;
        //     if(pg.getDataPagamento().isAfter(conta.getDataDeVencimento())){

        //         novoValorDoJuros = conta.getSaldoDevedor().multiply(conta.getTaxaDeJuros());
        //         conta.setValorDoJuros(novoValorDoJuros);
        //         resultado = conta.getSaldoDevedor().add(conta.getValorDoJuros());
        //         conta.setSaldoDevedor(resultado);
            
        //         BigDecimal  valorRestante;
        //         if(pg.getValorDoPagamento().compareTo(novoValorDoJuros) > 0){
        //             valorRestante = conta.getSaldoDevedor().subtract(pg.getValorDoPagamento());
        //             conta.setSaldoDevedor(valorRestante);
        //         }else{
        //             if(pg.getValorDoPagamento().equals(novoValorDoJuros)){
        //                 conta.getSaldoDevedor();
        //         }
        //     }
        // }else{
        //     if(pg.getDataPagamento().isEqual(conta.getDataDeVencimento())){
        //         if(pg.getValorDoPagamento().compareTo(conta.getValorDoJuros()) > 0){
        //             BigDecimal pagamento = conta.getSaldoDevedor().subtract(pg.getValorDoPagamento());
        //             conta.setSaldoDevedor(pagamento);
        //         }
        //     }
        // }
            int diaVencimento = pg.getDataDeVencimento().getDayOfMonth();

            LocalDate novoVencimento = pg.getDataDeVencimento()
            .plusMonths(1)
            .withDayOfMonth(Math.min(diaVencimento, pg.getDataDeVencimento().plusMonths(1).lengthOfMonth()));

            conta.setDataDeVencimento(novoVencimento);
            
            contasAPagarRepository.save(conta);
            return pagamentoRepository.save(pg);
    }
    public List<Pagamento> listaPagamentos(){
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return pagamentos;
    }

    public Optional<Pagamento> pagamaentoId(UUID id){
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
