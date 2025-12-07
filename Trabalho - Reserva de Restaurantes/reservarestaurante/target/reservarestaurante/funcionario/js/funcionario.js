const { createApp } = Vue;

createApp({
    data(){
        return{
            mesas: [],
            erro: "",
            sucesso: ""
        };
    },

    methods:{

        // ==== CARREGAR TODAS AS MESAS + RESERVAS ====
        async atualizarMesas(){
            try {
                const [resMesas, resReservas] = await Promise.all([
                    fetch('/reservarestaurante/api/mesas'),
                    fetch('/reservarestaurante/api/reservas')
                ]);

                const mesas = await resMesas.json();
                const reservas = await resReservas.json();

                // JUNTAR RESERVA DENTRO DA MESA
                this.mesas = mesas.map(m => {
                    const r = reservas.find(r => r.numeroMesa === m.numero);
                    return {
                        ...m,
                        reserva: r || null
                    };
                });

            } catch (err){
                this.erro = "Erro ao atualizar mesas.";
            }
        },

        // ==== CANCELAR RESERVA ====
        async cancelarReserva(id){
            try {
                const response = await fetch(`/reservarestaurante/api/reservas?id=${id}`, {
                    method: "DELETE"
                });

                const dados = await response.json();

                if(response.ok && dados.sucesso){
                    this.sucesso = "Reserva cancelada com sucesso.";
                    this.erro = "";
                }
                else if(response.status === 400){
                    this.erro = "ID inválido ou não informado.";
                    this.sucesso = "";
                }
                else {
                    this.erro = "Erro ao cancelar reserva.";
                    this.sucesso = "";
                }

                this.atualizarMesas();

            } catch (err){
                this.erro = "Erro de comunicação com o servidor.";
            }
        }
    },

    mounted(){
        // Carregar imediatamente ao abrir
        this.atualizarMesas();

        // Atualização automática a cada 5 segundos
        setInterval(() => {
            this.atualizarMesas();
        }, 3000);
    }

}).mount("#app");
