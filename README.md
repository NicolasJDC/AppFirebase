# Projeto Firebase Auth (Login e Cadastro)

**Aluno:** Nícolas James de Campos  
**RA:** 213804  
**Aluno:** Adilson De Sousa Santos
**RA:** 251324
**Disciplina:** Programação para Dispositivos Móveis  

---

## 🎯 Sobre o Desafio
O desafio prático proposto consistia em implementar um fluxo completo e seguro de autenticação de usuários no Android utilizando o Google Firebase. Os requisitos principais foram:
- Criar uma tela de Login baseada no modelo da documentação do **FirebaseUI**.
- Disponibilizar a opção de cadastrar novos usuários no aplicativo.
- Desenvolver uma tela pós-login contendo um botão para "Sair" (Logout), que deve encerrar a sessão de forma segura e retornar o usuário para a tela inicial.

## 🛠️ Como foi feito
O projeto foi desenvolvido nativamente em **Kotlin** e integrado aos serviços de backend do Firebase. A arquitetura foi dividida em duas telas principais:

1. **LoginActivity:** Ponto de entrada do app. Ela verifica se o usuário já possui uma sessão ativa (`FirebaseAuth.getInstance().currentUser`). Caso não tenha, invoca o lançador do `FirebaseUI` (`AuthUI`), que gera automaticamente a interface de login e cadastro por E-mail/Senha.
2. **MainActivity:** Tela principal acessada após o sucesso da autenticação. A interface foi construída utilizando `ViewBinding` para capturar os eventos de clique com segurança. O botão "Sair da Conta" chama o método `signOut` do Firebase, limpa os dados da sessão local e redireciona de volta ao fluxo de login.

A configuração exigiu o registro do pacote no Firebase Console, a injeção do arquivo `google-services.json` e a declaração das bibliotecas `firebase-bom`, `firebase-auth-ktx` e `firebase-ui-auth` no Gradle.

## 🚧 Dificuldades Encontradas e Soluções
Durante a execução prática na máquina da universidade, enfrentei alguns obstáculos técnicos de infraestrutura e configuração:

* **Bloqueio de Rede e DNS no Emulador:** A rede da universidade possuía restrições de proxy/firewall que impediam o emulador do Android Studio de se comunicar com os servidores de autenticação do Google (os cliques no botão "Next" falhavam por timeout). A solução foi alterar o DNS da máquina (para 8.8.8.8 e 8.8.4.4) e realizar um *Cold Boot* (arranque a frio) no emulador para que ele assimilasse a nova rota de internet.
* **Erros de Compilação no AndroidManifest:** Ao montar a estrutura do projeto, o *build* quebrou devido