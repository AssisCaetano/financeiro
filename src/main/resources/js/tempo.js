/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
    let inactivityTime = function () {
        let time;
        const oneMinute = 60000; // 60 segundos em milissegundos

        // Resetar o timer de inatividade
        function resetTimer() {
            clearTimeout(time);
            time = setTimeout(logout, oneMinute);
        }

        // Ação a ser tomada após a inatividade
        function logout() {
            // Redireciona para a página de login
            // Use window.location.href para garantir o redirecionamento
            window.location.href = '/logout';
        }

        // Adicionar eventos para detectar atividade do usuário
        window.onload = resetTimer;
        document.onmousemove = resetTimer;
        document.onkeypress = resetTimer;
        document.onmousedown = resetTimer;
        document.ontouchstart = resetTimer;
        document.onclick = resetTimer;
        document.onscroll = resetTimer;
    };
    inactivityTime();
});