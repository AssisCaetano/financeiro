/**
 * 
 */
// Exemplo de script para uma página protegida
document.addEventListener('DOMContentLoaded', async function() {
    const token = localStorage.getItem('jwtToken');

    if (!token) {
        // Se não houver token, redireciona para a página de login
        window.location.href = '/login';
        return;
    }
    
    // Exemplo de como fazer uma requisição para uma rota protegida
    try {
        const response = await fetch('/solicitante/cadastro', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}` // Inclui o token no cabeçalho
            }
        });

        if (response.status === 401 || response.status === 403) {
            // Se o token for inválido ou expirado, redireciona para o login
            localStorage.removeItem('jwtToken');
            window.location.href = '/login';
        }

        const data = await response.json();
        // Lógica para exibir os dados na página usando Thymeleaf ou JS
        console.log(data);

    } catch (error) {
        console.error('Erro ao buscar dados:', error);
    }
});