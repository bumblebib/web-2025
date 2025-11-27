new Vue({
  el: '#app',
  data: {
    empregados: [],
    novo: {
      firstName: '',
      lastName: '',
      username: '',
      pass: '',
      email: '',
      phone: '',
      country: ''
    },
    mensagem: '',
    sucesso: false,

    // novos dados para consulta
    paises: ['Brazil', 'Argentina', 'Chile', 'México', 'Portugal'],
    paisSelecionado: '',
    empregados: [],
    consultaFeita: false
  },
  methods: {
   salvar() {
      fetch('api/empregados', {
        method: 'POST',
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
          if (data.sucesso) {
            alert('Empregado salvo com sucesso!');
            this.novo = { firstName: '', lastName: '', username: '', pass: '', email: '', phone: '', country: '' };
            this.carregar();
          } else {
            alert('Falha ao salvar o empregado.');
          }
        })
        .catch(error => {
          console.error('Erro ao salvar:', error);
          alert('Erro ao salvar empregado');
        });
    },
    consultarPorPais() {
      this.empregados = [];
      this.consultaFeita = false;

      fetch('api/empregados?country=' + encodeURIComponent(this.paisSelecionado))
      .then(response => {
        if(!response.ok) throw new Error('Erro na consulta');
        return response.json();
      })
      .then(data => {
        this.empregados = data;
        this.consultaFeita = true;
        if (data.length > 0) {
          this.mensagem = 'Consulta concluída com sucesso !';
          this.sucesso = true;
        }else{
          this.mensagem = 'Nenhum resultado encontrado.';
          this.sucesso = false;
        }
      })
      .catch(error => {
        console.error('Erro na consulta:', error);
        this.mensagem = 'Erro ao consultar o servidor';
        this.sucesso = false;
      });
    }
  },
  created() {
    this.carregar();
  }
});
