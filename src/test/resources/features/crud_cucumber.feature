# Como usuario
# Eu quero cadastrar contas
# Para poder distribuir meu dinheiro de uma forma mais organizada
Feature: Cadastro Conta

	Background: 
		Given que estou acessando a aplicacao
		When informo o usuario "caue@caue"
		And a senha "teste123"
		And seleciono entrar
		Then visualizo a pagina inicial
		When seleciono contas
		And seleciono Adicionar
		
	Scenario Outline: Deve validar regras cadastro contas
		When informo a conta "<conta>"
		And seleciono Salvar
		Then recebo a mensagem "<mensagem>"

	Examples: 
		| 			    conta 		 		|  					 mensagem								|
		|        Conta Teste	 		|  Conta adicionada com sucesso! 		|
		|   											|     Informe o nome da conta				|
		|	    Conta mesmo nome    | Ja existe uma conta com esse nome!|