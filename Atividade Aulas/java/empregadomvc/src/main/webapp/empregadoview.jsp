<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <title>Gestao de Empregados</title>
        <script src="https://unpkg.com/vue@3/dist/vue.global.prod.js"></script>
        <link rel="stylesheet" href="style.css">
        <script src="js/empregadoApp.js" defer></script>
    </head>

    <body>
        <div id="app" class="container">
            <h2>Gestão de Empregados - MVC</h2>

            <form @submit.prevent="cadastrar">
                <input v-model="novo.nome" placeholder="Nome" required />
                <input v-model="novo.cargo" placeholder="Cargo" required />
                <input v-model.number="novo.salario" placeholder="Salário" required />
                <button type="submit">Cadastrar</button>
            </form>

            <p v-if="mensagem" class="mensagem">{{ mensagem }}</p>


            <h3>Lista de Empregados</h3>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Cargo</th>
                        <th>Salário</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="e in empregados" :key="e.id">
                        <td>{{ e.id }}</td>
                        <td>{{ e.nome }}</td>
                        <td>{{ e.cargo }}</td>
                        <td>{{ e.salario.toFixed(2) }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>