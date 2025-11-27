new Vue({
  el: '#app',
  data: {
    usuario: '',
    senha: '',
    mensagem: '',
    erro: false,
    nomeUsuario: '',
    sessionId: '',
    logado: false
  },
  created() {
    const params = new URLSearchParams(window.location.search);
    const usuarioParam = params.get('usuario');
    const sessionParam = params.get('sessionId');
    const erroParam = params.get('erro');
    const logoutParam = params.get('logout');

    // Identifica a página atual
    const pagina = window.location.pathname.split("/").pop();

    if (pagina === 'sucesso.html' || pagina === 'checkout.html') {
      if (usuarioParam && sessionParam) {
        this.nomeUsuario = usuarioParam;
        this.sessionId = sessionParam;
        this.logado = true;
      }
    }

    if (erroParam) {
      this.mensagem = 'Usuário ou senha incorretos.';
      this.erro = true;
    }

    if (logoutParam) {
      this.mensagem = 'Logout realizado com sucesso.';
      this.erro = false;
    }
  }
});
