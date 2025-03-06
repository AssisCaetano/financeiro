package com.controle.financeiro.domain.service;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Answers.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.controle.financeiro.domain.model.Solicitante;
import com.controle.financeiro.domain.repositore.SolicitanteRepository;
import com.controle.financeiro.dto.SolicitanteDto;
import com.controle.financeiro.exceptions.BadRequestException;
import com.controle.financeiro.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class SolicitanteServiceTest {

        public static final UUID Id = null;
    
        @Mock
        private SolicitanteRepository solicitanteRepository;
    
        @InjectMocks
        private SolicitanteService solicitanteService;
    
        @BeforeEach
        void setUp(){
            MockitoAnnotations.openMocks(this);
        }
    
        @Nested
        class salvarUsuario {
    
            @Test
            @DisplayName("Should Create a user with success")
            void shouldCreateAUserWithSuccess(){
                //Arrange
                var solicitante = new Solicitante();
                doReturn(solicitante).when(solicitanteRepository).save(any(Solicitante.class));
                var solicitanteDto = new SolicitanteDto("nome",  "sobrenome","endereco", "telefone", "cpf");
                var user = solicitanteService.salvarUsuario(solicitanteDto);
                //Assert
                assertNotNull(user);
                assertEquals(solicitante, user);
            }
            @Test
            @DisplayName("verificandoSeOUsuaroExiste")
            void verificandoSeOUsuarioExiste(){
                Solicitante usuario = new Solicitante();
                usuario.setIdSolicitante(UUID.randomUUID());
                usuario.setNome("nome");
                usuario.setSobrenome("sobrenome");
                usuario.setEndereco("endereco");
                usuario.setTelefone("telefone");
                usuario.setCpf("cpf");
                usuario.setContasAPagar(usuario.getContasAPagar());
    
                var solicitanteDto = new SolicitanteDto("nome", "sobrenome", "endereco", "telefone", "cpf");
                
                when(solicitanteRepository.findByCpf(usuario.getCpf())).thenReturn(Optional.of(usuario));
                assertThrows(BadRequestException.class, () -> solicitanteService.salvarUsuario(solicitanteDto));
            
            }
            @Test
            @DisplayName("Should throw exception when erro occors")
            void shouldThrowExceptionWhenErrorOccors(){
                doThrow(new RuntimeException()).when(solicitanteRepository).save(any(Solicitante.class));
                var solicitanteDto = new SolicitanteDto("nome",  "sobrenome","endereco", "telefone", "cpf");
                assertThrows(RuntimeException.class, () -> solicitanteService.salvarUsuario(solicitanteDto));
            }
        }
        @Nested
        class atualizaSolicitante{
    
            @Test
            @DisplayName("Buscando um usuario antes de atualizar")
            void buscandoUsuarioAntesDeAtualizar(){
                
                Solicitante atualizar = new Solicitante();
                when(solicitanteRepository.findById(Id)).thenReturn(Optional.empty());
                SolicitanteDto solicitanteDto = new SolicitanteDto(null, null, null, null, null);
                assertNotNull(atualizar);

                assertThrows(NotFoundException.class, () -> solicitanteService.atualizaSolicitante(Id, solicitanteDto));
           }
           @Test
           @DisplayName("Atualizando um usuario no banco")
           void atualizandoUmUsuarioNoBanco(){

            Solicitante solicitante = new Solicitante();
            when(solicitanteRepository.findById(solicitante.getIdSolicitante())).thenReturn(Optional.of(solicitante));

            doReturn(solicitante).when(solicitanteRepository).save(any(Solicitante.class));
            SolicitanteDto solicitanteDto = new SolicitanteDto("nome", "sobrenome", "endereco","telefone", "cpf");
            
            var rs = solicitanteService.atualizaSolicitante(Id, solicitanteDto);
            assertEquals(rs, rs);
           }
        }
        @Nested
        class listarSolicitante{

            @Test
            @DisplayName("Se nao existir usuario salvo lancar um excesao")
            void seNaoExistirUsuarioSalvo_DeveLancarUmaExcesao(){
                
            }
        }

}
