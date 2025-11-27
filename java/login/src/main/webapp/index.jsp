<!DOCTYPE html>
<html lang="pt">
    <head>
        <title>Sistema de login</title>

        <meta charset="UTF-8">

        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="ajax.js" defer></script>
    </head>

    <body>

        <header class="container">
            <h1>Login-System</h1>

            <nav class="menu">
                <ul> <!-- Our nav menu -->
                    <li><a href="index.html">Home</a></li>
                    <li><a href="#">Sign up</a></li>
                </ul>
            </nav>
            
            <!--nav: menu de navegação, links pela página -->
            <!--ul: lista não ordenada, "bullet points" -->
            <!--li: itens da lista não ordenada -->
            <!-- a: hyperlink, links que redirecionam para outros endereços -->
        </header>

        <section class="container">
            <article>
                <h1>Login</h1>
                <form>
                    <p><label for="login">Username:</label>
                        <input type="text" id="login" name="username"></p>

                    <p><label for="senha">Password:</label>
                        <input type="password" id="senha" name="password"></p>

                    <p><input type="submit" value="Log in" id="btnEnviar"></p>

                    <div id="mensagem"></div>
                </form>

            </article>
            <!--article: conteúdo "self-contained", independente do resto do site -->
            <!--form: é um container para elementros de entradas (inputs)-->
            <!--inputs: campos de entradas do usuário -->
        </section>

        <footer class="container">
            <p>Web Development - UFSCar Sorocaba - 2025</p>
        </footer>
        
    </body>
</html>