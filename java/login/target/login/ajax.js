document.addEventListener("DOMContentLoaded", function(){
    const botao = document.getElementById("btnEnviar");
    botao.addEventListener("click", enviarRequisicao);
});

function enviarRequisicao(){
    const nome = document.getElementById("nome").value;
    const xhr = new XMLHttpRequest();

    xhr.open("GET", "mensagem?nome=" + encodeURIComponent(nome), true);

    xhr.onload = function(){
        const respostaDiv = document.getElementById("mensagem");
        if(xhr.status === 200){
            respostaDiv.innerText = xhr.responseText;
        } else {
            respostaDiv.innerText = "Erro ao buscar dados do servidor.";
        }
    }

    xhr.send();
}