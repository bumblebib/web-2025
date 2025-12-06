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