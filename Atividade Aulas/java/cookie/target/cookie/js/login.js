new Vue({
  el: '#app',
  data: {
    usuario: '',
    senha: '',
    logado: false,
    nomeUsuario: '',
    mensagem: '',
    erro: false
  },
  created() {
    // Verifica se já existe cookie de login
    fetch('LoginServlet?action=status')
      .then(r => r.json())
      .then(d => {
        if (d.logado) {
          this.logado = true;
          this.nomeUsuario = d.usuario;
        }
      });
  },
  methods: {
    fazerLogin() {
      this.mensagem = '';
      this.erro = false;

      fetch('LoginServlet', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `usuario=${encodeURIComponent(this.usuario)}&senha=${encodeURIComponent(this.senha)}`
      })
        .then(r => r.json())
        .then(d => {
          if (d.sucesso) {
            this.logado = true;
            this.nomeUsuario = d.usuario;
            this.mensagem = 'Login realizado com sucesso!';
          } else {
            this.mensagem = 'Usuário ou senha incorretos.';
            this.erro = true;
          }
        })
        .catch(() => {
          this.mensagem = 'Erro ao conectar com o servidor.';
          this.erro = true;
        });
    },

    logout() {
      fetch('LogoutServlet')
        .then(() => {
          this.logado = false;
          this.usuario = '';
          this.senha = '';
          this.nomeUsuario = '';
          this.mensagem = 'Logout realizado com sucesso.';
        });
    }
  }
});
