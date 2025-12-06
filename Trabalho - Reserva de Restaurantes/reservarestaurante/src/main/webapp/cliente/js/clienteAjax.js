//Ajax com uso do Vue para Cliente
const { createApp } = Vue;

createApp({
    data(){
        return{
        //Variaveis, todas iguais ao model para Mesa
         mesas: [],  
         reserva: {
            numeroMesa: null,
            nomeCliente: "",
            inicio: "",
            fim: ""
         } 
        };
    },

    //Computed = vai ser usado para filtrar se as mesas estao disponiveis (Cliente só ve mesas disponiveis)
    computed:{
        mesasDisponiveis(){
            return this.mesas.filter(mesa => mesa.disponivel === true);
        }
    },

    //Funçoes são colocadas como metodos
    methods:{

        //Função que busca as mesas 
        async buscarMesa(){
          try{
            const response = await fetch('/api/mesas');
            const dados = await response.json();
            this.mesas = dados; // Mesmo que Atualizar_Mesas(dados);
          } catch (err) {
             console.error("Erro ao buscar as mesas:", err);
          } 

        },

        //Função que envia a reserva
        async enviarReserva(){
          try{
            const response = await fetch('/api/reservas', {
                method: 'POST', // POST signica que está sendo enviado
                headers: {
                    'Content-type': 'application/json' // informa qual tipo dos dados
                }, 
                body: JSON.stringify(this.reserva) //Transforma em uma string 
            });

            const dados = await response.json();
            console.log("Resposta do servidor:", dados);
            this.respostaServidor(dados);
            } catch (err){
                 console.error("Erro ao enviar a reserva:", err);
            }
         },

         //Função que trata a resposta do servidor (sucesso ou erro)
         respostaServidor(resposta){
            if(resposta.sucesso){
                console.log("Reserva realizada!");
                this.limpar();
                this.buscarMesa(); // atualiza as mesas disponiveis
            } else {
                console.error("Erro na reserva:", resposta.mensagem);
            }
        },

        //Função para limpar o formulário usado, podemos tirar se não for necessaria
        limpar(){
            this.reserva = {
            numeroMesa: null,
            nomeCliente: "",
            inicio: "",
            fim: ""
         };
        }
    },
        
    //Função que carrega todas as informações, podemos tirar se não for necessaria
    mounted(){
        this.buscarMesa();
    }

}).mount("#app")

// busca mesas disponíveis
function BuscarMesa() {

    fetch('/reservarestaurante/api/mesas?disponivel=true')
        .then(response => response.json())
        .then(dados => {
            Atualizar_Mesas(dados);
            PreencherSelect(dados);
        })
        .catch(err => {
            console.error("Erro ao buscar as mesas:", err);
        });
}

// mostra lista de mesas
function Atualizar_Mesas(mesas) {

    const div = document.getElementById("lista-mesas");
    div.innerHTML = "";

    mesas.forEach(mesa => {
        div.innerHTML += `
            <div class="mesa-card">
                <p class="mesa-numero"><strong>Mesa ${mesa.numero}</strong></p>
                <p class="capacidade">Capacidade: ${mesa.capacidade}</p>
                <p class="status livre">Disponível</p>
            </div>
        `;
    });
}

// preenche o select de mesas disponíveis
function PreencherSelect(mesas) {

    const select = document.getElementById("inputMesa");
    select.innerHTML = "";

    if (mesas.length === 0) {
        select.innerHTML = `<option disabled>Nenhuma mesa disponível</option>`;
        return;
    }

    mesas.forEach(m => {
        select.innerHTML += `
            <option value="${m.numero}">
                Mesa ${m.numero} — capacidade ${m.capacidade}
            </option>
        `;
    });
}

function Enviar_Reserva() {

    const reserva = {
        numeroMesa: parseInt(document.getElementById("inputMesa").value),
        nomeCliente: document.getElementById("inputNome").value,
        inicio: document.getElementById("inputInicio").value,
        fim: document.getElementById("inputFim").value
    };

    // validações simples
    if (!reserva.nomeCliente || !reserva.inicio || !reserva.fim) {
        alert("Preencha todos os campos!");
        return;
    }

    fetch('/reservarestaurante/api/reservas', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(reserva)
    })
    .then(response => response.json())
    .then(dados => {
        if (dados.sucesso) {
            alert("Reserva realizada com sucesso!");
            BuscarMesa(); // atualiza mesas depois da reserva
        } else {
            alert("Erro ao fazer reserva. Mesa ocupada ou horário inválido.");
        }
    })
    .catch(err => {
        console.error("Erro ao enviar reserva:", err);
    });
}
