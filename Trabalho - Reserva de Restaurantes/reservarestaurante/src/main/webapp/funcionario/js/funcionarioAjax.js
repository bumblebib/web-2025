//Ajax com uso do Vue para Funcionario
const { createApp } = Vue;

createApp({
    data(){
        return{
        //Variaveis, todas iguais ao model para Mesa
         mesas: [],  
         reservas: [],
        };
    },

    //Computed = vai ser usado para filtrar se as mesas estao disponiveis e ocupadas (Funcionario pode ver ambos)
    computed:{
        mesasDisponiveis(){
            return this.mesas.filter(mesa => mesa.disponivel === true);
        },

        mesasOcupadas(){
            return this.mesas.filter(mesa => mesa.disponivel === false);
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

         //Função que busca as reservas já realizadas
        async buscarReserva(){
          try{
            const response = await fetch('/api/reservas');
            const dados = await response.json();
            this.reservas = dados; // Mesmo que Atualizar_Mesas(dados);
          } catch (err) {
             console.error("Erro ao buscar as reservas realizadas:", err);
          } 

        },

        //Função que faz uma reserva
        async finalizarReserva(numeroMesa,nomeCliente,inicio,fim){
            try{

            if (!this.mesasDisponiveis.some(m => m.numero === numeroMesa)) {
                alert("Mesa já está ocupada!");
                return;
            }

            const response = await fetch('/api/reservas', {
                method: 'POST', // POST signica que está sendo enviado
                headers: {
                    'Content-type': 'application/json' // informa qual tipo dos dados
                }, 
                body: JSON.stringify({
                    numeroMesa: numeroMesa,
                    nomeCliente: nomeCliente,
                    inicio: inicio,
                    fim: fim
                })
            });

            if(!response.ok) throw new Error("Erro identificado ao reservar mesa");
            await this.mesaIndisponivel(numeroMesa);
            alert("Mesa reserva com sucesso");
            this.atualizarTela();
            } catch (err){
                 console.error("Erro ao enviar a reserva:", err);
            }
        },

        //Função que torna a mesa indisonivel assim que reservada 
        async mesaIndisponivel(numeroMesa){
            try{
                await fetch(`/api/mesas/${numeroMesa}`, {
                method: 'PUT', // POST signica que está sendo colocado 
                headers: {
                    'Content-type': 'application/json' // informa qual tipo dos dados
                }, 
                body: JSON.stringify({disponivel: false})  
            });

            } catch (err){
                 console.error("Erro encontrado:", err);
            }
         },

        //Função que torna a mesa disponivel se a reserva for cancelada 
        async mesaDisponivel(numeroMesa){
            try{
                await fetch(`/api/mesas/${numeroMesa}`, {
                method: 'PUT', // POST signica que está sendo colocado 
                headers: {
                    'Content-type': 'application/json' // informa qual tipo dos dados
                }, 
                body: JSON.stringify({disponivel: true})  
            });

            } catch (err){
                 console.error("Erro encontrado:", err);
            }
         },

        //Função que cancela uma reserva se for solicitado 
        async cancelamento(numeroReserva){
            try{
            const reserva = this.reservas.find(r => r.id === numeroReserva);
            //Verifica se a reserva existe
            if (!reserva) {
                console.error("Reserva não encontrada!");
                return;
        }

            const numeroMesa = reserva.numeroMesa;

            await fetch(`/api/reservas/${numeroReserva}`, {
                method: 'DELETE'
            });
                await this.MesaDisponivel(numeroMesa);
                console.log("Cancelamento concluido com sucesso!")
                this.atualizarTela();
            } catch (err){
                 console.error("Erro ao cancelar reserva:", err);
            }
        },

        //Função para atualizar a tela com as atualizações, podemos tirar se nao for preciso 
        async atualizarTela(){
            await this.buscarMesa();
            await this.buscarReserva();

        }

    },
        
    //Função que carrega todas as informações, podemos tirar se não for necessaria
    mounted(){
        this.atualizarTela();
    }

}).mount("#app")