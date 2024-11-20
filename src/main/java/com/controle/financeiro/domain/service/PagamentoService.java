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
        /*
         * Salvando Pagamento.
         */
        public Pagamento lancarPagamento(UUID id, PagamentoDto pagamentoDto){
            /*
             * Aqui recebo todos os dados da conta.
             */
        Optional<ContasAPagar> cap = contasAPagarRepository.findById(id);
            /*
             * Atribuo as informações recebido no cap á conta.
             */
        ContasAPagar conta = cap.get();
            /*
             * Nesse ponto realizo a intânciação da classe pagamento.
             */
        Pagamento pg = new Pagamento();
        /*
         * Aqui uso a classe BeanUtils com metodo copyProperties para realizar a trânsferecia das informações recebidas no objeto
         * dto para pg.
         */
        BeanUtils.copyProperties(pagamentoDto, pg);
            /*
             * Estou setando os dados contidos em pagamento na classe conta.
             */
        conta.setValorPago(pg.getValorDoPagamento());
        conta.setDataDePagamento(pg.getDataPagamento().now());

        // Recebo as informaçoes de vencimento e valor da divida de contas a pagar
        pg.setDataDeVencimento(conta.getDataDeVencimento());
        pg.setValorDivida(conta.getSaldoDevedor());
       /*
        * Verificando se a data do Pagamento é posterior ao Vencimento e se for
        * realizo o calculo para adicionar 20% ao saldo devedor.
        */
        double novoValorDoJuros;
        double resultado;
        if(pg.getDataPagamento().isAfter(conta.getDataDeVencimento())){     
           
            novoValorDoJuros = (conta.getSaldoDevedor() * conta.getTaxaDeJuros());
            conta.setValorDoJuros(novoValorDoJuros);          
            resultado = conta.getSaldoDevedor() + conta.getValorDoJuros();
            conta.setSaldoDevedor(resultado);
            /*
             * Verificando se o valor do pagamento é maior que o juros
             * caso seja realiza a subtração do saldo total.
             */
            double  valorRestante;
            if(pg.getValorDoPagamento() > novoValorDoJuros){                    
                valorRestante = conta.getSaldoDevedor() - pg.getValorDoPagamento();
                conta.setSaldoDevedor(valorRestante);
            }else{
                /*
                 * Caso o valor seja igual ao juros o saldo sofre alteração caso a data de pagamento
                 * seja posterior ao vencimento, lembrando que o calculo do juros para informar pagamento
                 * é feito de forma manual.
                 */
                if(pg.getValorDoPagamento() == novoValorDoJuros){
                    conta.getSaldoDevedor();
                }
            }
            
        }else{
            /*
             * Caso o pagamento seja igual a data de vencimento, execulta o que está dentro do bloco.
             */
            if(pg.getDataPagamento().isEqual(conta.getDataDeVencimento())){
                /*
                 * O pagamento sendo maior reliza a subtração do pagamento, do saldo devedor. 
                 */
                if(pg.getValorDoPagamento() > conta.getValorDoJuros()){
                    double pagamento = conta.getSaldoDevedor() - pg.getValorDoPagamento();
                    conta.setSaldoDevedor(pagamento);
                }
            }
        }
        /*
         * Salvo as informações na conta, e aqui também já serve como atualização
         */
        contasAPagarRepository.save(conta);
        /*
         * E por fim finaliza o método salvado o pagamento com as informações de vencimento
         * e valor da divida vindo da classe contas a pagar.
         */
        return pagamentoRepository.save(pg);
        
    }
    /*
     * Retornando uma lista de Pagamento.
     */
    public List<Pagamento> listaPagamentos(){
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return pagamentos;
    }
    /*
     * Retornando um Pagamento.
     */
    public Optional<Pagamento> pagamaentoId(UUID id){
        Optional<Pagamento> byIdPagamento = pagamentoRepository.findById(id);
        return byIdPagamento;
    }
    /*
     * Realizando uma atualização de Pagamento.
     */
    public Optional<Pagamento> atualizaPagamento(UUID id, PagamentoDto pagamentoDto){
        Optional<Pagamento> atualizar = pagamentoRepository.findById(id);
        Pagamento pagamento = atualizar.get();
        BeanUtils.copyProperties(pagamentoDto, pagamento);
        pagamentoRepository.save(pagamento);
        return atualizar;
    }
    /*
     * Deletando um Pagamento.
     */
    public Optional<Pagamento> deletandoPagamento(UUID id){
        /*
         * Primeiro busco o pagamento pelo id
         */
        Optional<Pagamento> deleteById = pagamentoRepository.findById(id);
        /*
         * Depois realizo a exclusão do registro.
         */
        pagamentoRepository.deleteById(id);
        /*
         * E por fim retorno o id deletado.
         */
        return deleteById;
    }
}
