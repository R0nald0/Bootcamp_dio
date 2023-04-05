package me.academiadio.AcademiaApi.autenticacao;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class Autenticacao {

    @Autowired
     private AuthenticationManager authenticationManager;
    public ResponseEntity<TokenDTO> autenticar(LoginDTO loginDTO){
        UsernamePasswordAuthenticationToken dados = loginDTO.converter();
           authenticationManager.authenticate(dados);
           tokenService.gerarToken
    }
}
