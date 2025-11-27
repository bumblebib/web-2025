new Vue({
    el: '#app',
    data: {
        empregados: [],
        novo: {
            firstName: '',
            lastName: '',
            userName: '', 
            pass: '', 
            email: '',
            phone: '',
            country: ''
        }
    },
    methods: {
        salvar() {
            fetch('api/empregados', {
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.novo)
            })
            .then(response => {
                if (!response.ok) throw new Error('Erro ao salvar empregado');
                return response.json();
            })
            .then(data => {
                if (data.sucesso){
                    alert('Empregado salvo com sucesso!');
                    this.novo = { firstName: '', lastName: '', userName: '', pass: '', email: '', phone: '', country: '' };
                    this.carregar();
                } else {
                    alert('Falha ao salvar empregado.');
                }
            })
            .catch(error => {
                console.error('Erro ao salvar:', error);
                alert('Erro ao salvar empregado.');
            });
        }
    },
    created() {
        this.carregar();
    }
});