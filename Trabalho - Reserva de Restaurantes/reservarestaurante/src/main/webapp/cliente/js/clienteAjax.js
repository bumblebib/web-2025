//Função que busca as mesas 
function BuscarMesa() {
    // requisição Get para o backend
    fetch('/api/mesas')
    .then(Response =>Response.json())
    .then(dados => {
        Atualizar_Mesas(dados);
    })
    .catch(err => {
        console.error("Erro ao buscar as mesas:", err)
    });
}

//Função que atualiza a interface com os dados recebidos, mostra as mesas livres 
function Atualizar_Mesas(MesasDisponiveis){
 //Atualizar essa função depois que tiver o HTML
    console.log(MesasDisponiveis);
}

//Função que envia a reserva 
function Enviar_Reserva(){
    fetch('/api/reservas',{
    method: 'POST', // POST signica que está sendo enviado
    headers: {
            'Content-type': 'application/json' // informa qual tipo dos dados
    }, 
        body: JSON.stringify(reserva) //Transforma em uma string 
})
    .then(Response =>Response.json())
    .then(dados => {
        Resposta_Servidor(dados);
    })
    .catch(err => {
        console.error("Erro ao enviar a reserva:", err)
    });
}

//Função que pega dados dos inputs (mesa, nome, horário) PRECISO DO HTML    

//Função que trata a resposta do servidor (sucesso ou erro)
function Resposta_Servidor(resposta){
    if(resposta.sucesso){
        console.log("Reserva realizada!");
        BuscarMesa(); // atualiza as mesas disponiveis
    } else {
        console.error("Erro na reserva:", resposta.mensagem);
    }
}