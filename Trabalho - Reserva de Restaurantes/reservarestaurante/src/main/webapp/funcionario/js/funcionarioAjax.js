//variavel para guardar as reservas 
let ListadeReservas = [];

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

//Função que busca as mesas já reservadas 
function BuscarReserva(){
     fetch('/api/reservas')
    .then(Response =>Response.json())
    .then(dados => {
        ListadeReservas = dados;
        Atualizar_Reserva(dados);
    })
    .catch(err => {
        console.error("Erro ao buscar as reservas:", err)
    });
}

//Função que atualiza a interface com os dados recebidos
function Atualizar_Mesas(MesasDisponiveis){
 //Atualizar essa função depois que tiver o HTML
    console.log(MesasDisponiveis);
}

//Função que atualiza a interface com os dados recebidos
function Atualizar_Reserva(MesasReservadas){
 //Atualizar essa função depois que tiver o HTML
    console.log(MesasReservadas);
}

//Atualização automática 
function AutoAtualizar(){
    BuscarMesa();
    BuscarReserva();
    setInterval(() => {
        BuscarMesa();
        BuscarReserva();
     } ,3000); //Deixei para repetir a cada 3 segundos, mas podemos mudar
}

window.onload = AutoAtualizar;