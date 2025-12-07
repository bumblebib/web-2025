const { createApp } = Vue;

createApp({
    data(){
        return{
            mesas: [],
            mesasAnteriores: [],
            mesasDestaque: new Set(), // guarda números das mesas a destacar
            reserva: {
                numeroMesa: "",
                nomeCliente: "",
                data: ""
            },
            erro: "",
            sucesso: ""
        };
    },

    methods:{

        // === DESTAQUE DE MESA ===
        destacarMesa(numero){
            this.mesasDestaque.add(numero);

            // remove após 3 segundos
            setTimeout(() => {
                this.mesasDestaque.delete(numero);
            }, 3000);
        },

        // ==== BUSCAR MESAS DISPONÍVEIS ====
        async buscarMesasDisponiveis(){
            try{
                const response = await fetch('/reservarestaurante/api/mesas?disponivel=true');
                const novasMesas = await response.json();

                // === 1) Verificar quais mesas ficaram livres ===
                const anteriores = new Set(this.mesasAnteriores.map(m => m.numero));
                const atuais = new Set(novasMesas.map(m => m.numero));

                novasMesas.forEach(mesa => {
                    if (!anteriores.has(mesa.numero)) {
                        // mesa acabou de entrar na lista → ficou disponível agora
                        this.destacarMesa(mesa.numero);
                    }
                });

                // Atualiza estado
                this.mesas = novasMesas;
                this.mesasAnteriores = novasMesas.map(m => ({ ...m })); // cópia

            } catch (err){
                this.erro = "Erro ao carregar mesas disponíveis.";
            }
        },


        // ==== ENVIAR RESERVA ====
        async enviarReserva(){
            try{
                this.erro = "";
                this.sucesso = "";

                const response = await fetch('/reservarestaurante/api/reservas', {
                    method: 'POST',
                    headers: { 'Content-type': 'application/json' },
                    body: JSON.stringify(this.reserva)
                });

                const dados = await response.json();

                if(dados.sucesso){
                    this.sucesso = "Reserva realizada com sucesso!";
                    this.limpar();

                    // Atualiza mesas após reservar
                    this.buscarMesasDisponiveis();
                } else {
                    this.erro = dados.mensagem || "Erro ao realizar reserva.";
                }

            } catch (err){
                this.erro = "Erro ao enviar reserva.";
            }
        },

        limpar(){
            this.reserva = {
                numeroMesa: "",
                nomeCliente: "",
                data: ""
            };
        },
    },

    mounted(){

        // 1) Carrega as mesas ao abrir
        this.buscarMesasDisponiveis();

        // 2) Atualiza automaticamente quando uma reserva é cancelada
        setInterval(() => {
            this.buscarMesasDisponiveis();
        }, 3000); // a cada 3 segundos

    }

}).mount("#app");
