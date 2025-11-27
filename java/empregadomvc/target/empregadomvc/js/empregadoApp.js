const { createApp } = Vue;

createApp({
    data() { 
        return{
            empregados: [], 
            novo: { nome: '', cargo: '', salario: ''},
            mensagem: ''
        };
    },
    methods: {
        async listar(){
            const res = await fetch("EmpregadoServlet");
            this.empregados = await res.json();
        },
        async cadastrar(){
            const res = await fetch("EmpregadoServlet", {
                method: "POST",
                headers: { "Content-Type": "application/json"},
                body: JSON.stringify(this.novo)
            });
            this.mensagem = await res.text();
            this.novo = { nome: '', cargo: '', salario: ''};
            this.listar();
        }
    },
    mounted(){
        this.listar();
    }
}).mount("#app");