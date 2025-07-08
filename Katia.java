<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    ```html
<meta name="theme-color" content="#87CEEB">
html
<link rel="icon"
href="icon-192.png"
sizes="192x192" type="image/png">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Binaural Calm App</title>
    <!-- Tailwind CSS CDN para estilização rápida e responsiva -->
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #f0f4f8; /* Cor de fundo suave */
            color: #334155; /* Cor de texto padrão */
        }
        .container {
            max-width: 900px;
            margin: 2rem auto;
            padding: 1.5rem;
            background-color: #ffffff;
            border-radius: 1rem; /* Cantos arredondados */
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05); /* Sombra suave */
        }
        .btn {
            padding: 0.75rem 1.5rem;
            border-radius: 0.75rem; /* Cantos arredondados */
            font-weight: 600;
            transition: background-color 0.2s, transform 0.2s;
            cursor: pointer;
            display: inline-flex;
            align-items: center;
            justify-content: center;
        }
        .btn-primary {
            background-color: #6366f1; /* Azul vibrante */
            color: white;
        }
        .btn-primary:hover {
            background-color: #4f46e5; /* Azul mais escuro no hover */
            transform: translateY(-2px); /* Efeito de elevação */
        }
        .btn-secondary {
            background-color: #e2e8f0; /* Cinza claro */
            color: #475569;
        }
        .btn-secondary:hover {
            background-color: #cbd5e1;
            transform: translateY(-2px);
        }
        .input-range {
            width: 100%;
            height: 8px;
            background: #cbd5e1;
            border-radius: 4px;
            outline: none;
            -webkit-appearance: none;
            appearance: none;
            cursor: pointer;
        }
        .input-range::-webkit-slider-thumb {
            -webkit-appearance: none;
            appearance: none;
            width: 20px;
            height: 20px;
            background: #6366f1;
            border-radius: 50%;
            cursor: pointer;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }
        .input-range::-moz-range-thumb {
            width: 20px;
            height: 20px;
            background: #6366f1;
            border-radius: 50%;
            cursor: pointer;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
        }

        /* Estilo para o modal de mensagem */
        .modal {
            display: none; /* Escondido por padrão */
            position: fixed; /* Posição fixa na tela */
            z-index: 1000; /* Acima de outros elementos */
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto; /* Habilitar rolagem se o conteúdo for grande */
            background-color: rgba(0,0,0,0.4); /* Fundo semi-transparente */
            display: flex; /* Usar flexbox para centralizar */
            justify-content: center;
            align-items: center;
        }
        .modal-content {
            background-color: #fefefe;
            margin: auto;
            padding: 20px;
            border-radius: 1rem;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 400px;
            text-align: center;
        }
        .close-button {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
            cursor: pointer;
        }
        .close-button:hover,
        .close-button:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        /* Estilo para a seção de Perguntas e Respostas (Acordeão) */
        .accordion-item {
            border-bottom: 1px solid #e2e8f0;
        }
        .accordion-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem;
            background-color: #f8fafc;
            cursor: pointer;
            border-radius: 0.5rem;
            margin-bottom: 0.5rem;
            transition: background-color 0.2s;
        }
        .accordion-header:hover {
            background-color: #f1f5f9;
        }
        .accordion-content {
            padding: 0 1rem 1rem 1rem;
            max-height: 0;
            overflow: hidden;
            transition: max-height 0.3s ease-out;
        }
        .accordion-content.active {
            max-height: 500px; /* Altura máxima para mostrar o conteúdo, ajuste conforme necessário */
            transition: max-height 0.3s ease-in;
        }
        .accordion-header svg {
            transition: transform 0.3s ease-in-out;
        }
        .accordion-header.active svg {
            transform: rotate(180deg);
        }
        .loading-spinner {
            border: 4px solid rgba(0, 0, 0, 0.1);
            border-left-color: #6366f1;
            border-radius: 50%;
            width: 24px;
            height: 24px;
            animation: spin 1s linear infinite;
        }
        @keyframes spin {
            to { transform: rotate(360deg); }
        }

        /* Estilo para a biblioteca de sons */
        .sound-card {
            background-color: #fff;
            border-radius: 0.75rem;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
            padding: 1rem;
            text-align: center;
            cursor: pointer;
            transition: transform 0.2s, box-shadow 0.2s;
        }
        .sound-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05);
        }
        .sound-card-icon {
            font-size: 2.5rem;
            margin-bottom: 0.5rem;
            color: #4f46e5;
        }

        /* Estilo para a tela de início */
        #startScreen {
            background-image: url('https://raw.githubusercontent.com/google-gemini/gemini-web-ai-examples/main/images/1751854280319%20(1).jpg');
            background-size: cover;
            background-position: center;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
            color: #e0f2fe; /* Azul claro para o texto */
            position: relative; /* Para o overlay */
            background-color: #f0f4f8; /* Fundo branco quase azul */
        }
        #startScreen::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.5); /* Overlay escuro para melhorar a legibilidade do texto */
            z-index: 1;
        }
        #startScreen > * {
            z-index: 2; /* Garante que o conteúdo esteja acima do overlay */
        }
        .start-screen-title {
            font-size: 3.5rem; /* Título maior */
            font-weight: 700;
            margin-bottom: 1rem; /* Reduzido para dar espaço à descrição */
            text-shadow: 2px 2px 4px rgba(0,0,0,0.7); /* Sombra para o texto */
            color: #e0f2fe; /* Azul claro */
        }
        .start-screen-description {
            font-size: 1.25rem;
            margin-bottom: 2rem;
            max-width: 600px;
            color: #d0e8ff; /* Azul ainda mais claro para a descrição */
            text-shadow: 1px 1px 2px rgba(0,0,0,0.5);
        }
        .start-screen-button {
            background-color: rgba(99, 102, 241, 0.9); /* Azul vibrante semi-transparente */
            color: white;
            padding: 1rem 2.5rem;
            border-radius: 0.75rem;
            font-size: 1.25rem;
            font-weight: 600;
            margin-bottom: 1rem;
            transition: background-color 0.2s, transform 0.2s;
            cursor: pointer;
            border: 2px solid white; /* Borda branca */
        }
        .start-screen-button:hover {
            background-color: rgba(79, 70, 229, 0.9); /* Azul mais escuro no hover */
            transform: translateY(-3px);
        }

        /* Estilo para cards de música */
        .music-card {
            background-color: #fff;
            border-radius: 0.75rem;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06);
            padding: 1rem;
            text-align: center;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-between;
        }
        .music-card img {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 0.75rem;
        }
        .music-card h4 {
            font-weight: 600;
            color: #334155;
            margin-bottom: 0.25rem;
        }
        .music-card p {
            font-size: 0.875rem;
            color: #64748b;
            margin-bottom: 0.75rem;
        }
        .music-card button {
            background-color: #8b5cf6; /* Roxo para o botão de play */
            color: white;
            padding: 0.5rem 1rem;
            border-radius: 0.5rem;
            font-size: 0.875rem;
            transition: background-color 0.2s;
        }
        .music-card button:hover {
            background-color: #7c3aed;
        }

        /* Estilo para o modal de adicionar a playlist */
        .add-to-playlist-modal-content {
            background-color: #fefefe;
            padding: 20px;
            border-radius: 1rem;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 90%;
            max-width: 500px;
            text-align: left;
        }
        .add-to-playlist-modal-content h3 {
            text-align: center;
            margin-bottom: 1.5rem;
        }
        .add-to-playlist-modal-content label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
        }
        .add-to-playlist-modal-content select,
        .add-to-playlist-modal-content input[type="checkbox"] {
            margin-bottom: 1rem;
        }
        .add-to-playlist-modal-content .checkbox-item {
            display: flex;
            align-items: center;
            margin-bottom: 0.5rem;
        }
        .add-to-playlist-modal-content .checkbox-item input {
            margin-right: 0.75rem;
        }
    </style>
</head>
<body class="p-0">
    <!-- Tela de Início -->
    <div id="startScreen" class="w-full h-full absolute top-0 left-0">
        <img src="https://raw.githubusercontent.com/google-gemini/gemini-web-ai-examples/main/images/1751854280319%20(1).jpg" alt="Capa Binaural Calm" class="absolute inset-0 w-full h-full object-cover z-0">
        <div class="absolute inset-0 bg-black opacity-50 z-10"></div> <!-- Overlay -->

        <h1 class="start-screen-title relative z-20">Binaural Calm</h1>
        <p class="start-screen-description relative z-20 px-4">
            Seu refúgio diário para sons que acalmam, focam e inspiram. Descubra a paz e o bem-estar através de frequências personalizadas e músicas que você ama.
        </p>
        <button id="enterAppButton" class="start-screen-button relative z-20">Entrar no App</button>
    </div>

    <!-- Conteúdo Principal do Aplicativo (Inicialmente oculto) -->
    <div id="mainAppContent" class="hidden">
        <div class="container">
            <h1 class="text-3xl font-bold text-center mb-6 text-indigo-700">Binaural Calm App Completo</h1>
            <p class="text-center text-sm text-gray-500 mb-6">ID do Usuário: <span id="userIdDisplay">Carregando...</span></p>

            <!-- Seção do Gerador de Sons Binaurais -->
            <section class="mb-8 p-6 bg-indigo-50 rounded-lg shadow-inner">
                <h2 class="text-2xl font-semibold mb-4 text-indigo-600">Gerador de Sons Binaurais</h2>
                <div class="grid grid-cols-1 md:grid-cols-2 gap-4 mb-4">
                    <div>
                        <label for="baseFrequencyRange" class="block text-lg font-medium text-gray-700 mb-2">Frequência Base: <span id="baseFrequencyValue">440</span> Hz</label>
                        <input type="range" id="baseFrequencyRange" min="200" max="1000" value="440" class="input-range">
                    </div>
                    <div>
                        <label for="beatFrequencyRange" class="block text-lg font-medium text-gray-700 mb-2">Frequência Binaural: <span id="beatFrequencyValue">5</span> Hz</label>
                        <input type="range" id="beatFrequencyRange" min="1" max="30" value="5" class="input-range">
                    </div>
                </div>

                <div class="flex space-x-2 mt-4">
                    <button id="playBinauralButton" class="btn btn-primary">
                        <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM9.555 7.168A1 1 0 008 8v4a1 1 0 001.555.832l3-2a1 1 0 000-1.664l-3-2z" clip-rule="evenodd"></path></svg>
                        Tocar Binaural
                    </button>
                    <button id="stopBinauralButton" class="btn btn-secondary" disabled>
                        <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM8 9a1 1 0 00-1 1v2a1 1 0 102 0v-2a1 1 0 00-1-1zM12 9a1 1 0 00-1 1v2a1 1 0 102 0v-2a1 1 0 00-1-1z" clip-rule="evenodd"></path></svg>
                        Parar
                    </button>
                    <button id="downloadBinauralButton" class="btn btn-secondary" disabled>
                        <svg class="w-5 h-5 mr-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M3 17a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zm3.293-7.707a1 1 0 011.414 0L9 10.586V3a1 1 0 112 0v7.586l1.293-1.293a1 1 0 111.414 1.414l-3 3a1 1 0 01-1.414 0l-3-3a1 1 0 010-1.414z" clip-rule="evenodd"></path></svg>
                        Baixar (WAV)
                    </button>
                </div>
                <p class="text-sm text-gray-600 mt-4">
                    Nota: O download é em formato WAV.
                </p>
            </section>

            <!-- Seção de Biblioteca de Sons -->
            <section class="mb-8 p-6 bg-yellow-50 rounded-lg shadow-inner">
                <h2 class="text-2xl font-semibold mb-4 text-yellow-600">Biblioteca de Sons</h2>
                <p class="text-sm text-gray-600 mb-4">
                    Ouça sons e músicas pré-selecionados à vontade. Para baixar, acesse a plataforma de origem!
                </p>
                <div class="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4" id="soundLibrary">
                    <!-- Sons serão carregados aqui via JS -->
                </div>
            </section>

            <!-- Seção de Perguntas e Respostas com IA -->
            <section class="mb-8 p-6 bg-blue-50 rounded-lg shadow-inner">
                <h2 class="text-2xl font-semibold mb-4 text-blue-600">Perguntas e Respostas com IA (YouTube)</h2>
                <div class="mb-4">
                    <label for="aiQuestion" class="block text-lg font-medium text-gray-700 mb-2">Faça sua pergunta:</label>
                    <textarea id="aiQuestion" class="w-full p-3 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500" rows="3" placeholder="Ex: Como lidar com a ansiedade no TDAH?"></textarea>
                </div>
                <button id="askAiButton" class="btn btn-primary w-full md:w-auto">
                    <span id="askAiSpinner" class="loading-spinner hidden mr-2"></span>
                    Perguntar à IA
                </button>
                <div id="aiResponse" class="mt-4 p-4 bg-blue-100 rounded-lg border border-blue-200 hidden">
                    <p class="font-semibold text-blue-800 mb-2">Resposta da IA:</p>
                    <div id="aiResponseContent" class="text-gray-700"></div>
                </div>
            </section>

            <!-- Seção de Autoavaliação e Metas -->
            <section class="mb-8 p-6 bg-green-50 rounded-lg shadow-inner">
                <h2 class="text-2xl font-semibold mb-4 text-green-600">Autoavaliação e Metas</h2>
                <div class="mb-4">
                    <label for="behaviorDescription" class="block text-lg font-medium text-gray-700 mb-2">Descreva seu comportamento ou dificuldade:</label>
                    <textarea id="behaviorDescription" class="w-full p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500" rows="4" placeholder="Ex: Tenho dificuldade em manter o foco em tarefas longas."></textarea>
                </div>
                <button id="getAiFeedbackButton" class="btn btn-primary w-full md:w-auto mb-4">
                    <span id="feedbackSpinner" class="loading-spinner hidden mr-2"></span>
                    Obter Feedback da IA
                </button>
                <div id="aiFeedbackResponse" class="mt-4 p-4 bg-green-100 rounded-lg border border-green-200 hidden">
                    <p class="font-semibold text-green-800 mb-2">Resposta da IA:</p>
                    <div id="aiFeedbackContent" class="text-gray-700"></div>
                </div>
                <div class="mt-6">
                    <label for="weeklyGoal" class="block text-lg font-medium text-gray-700 mb-2">Minha Meta Semanal:</label>
                    <input type="text" id="weeklyGoal" class="w-full p-3 border border-gray-300 rounded-lg focus:ring-green-500 focus:border-green-500" placeholder="Ex: Concluir 3 tarefas sem interrupções.">
                </div>
                <button id="completeGoalButton" class="btn btn-primary w-full md:w-auto mt-4">Etapa Concluída</button>
            </section>

            <!-- Seção de Minhas Músicas (Recompensa) -->
            <section class="p-6 bg-purple-50 rounded-lg shadow-inner">
                <h2 class="text-2xl font-semibold mb-4 text-purple-600">Minhas Músicas</h2>
                <div id="addMusicSection" class="hidden p-4 bg-purple-100 rounded-lg mb-4">
                    <h3 class="text-xl font-semibold mb-3 text-purple-700">Adicionar Músicas (Recompensa!)</h3>
                    <p class="text-sm text-gray-600 mb-3">
                        Você pode adicionar até 5 músicas do seu celular após completar uma meta.
                        <strong class="font-bold text-red-700">Atenção:</strong> Estas músicas são temporárias e não serão salvas permanentemente. Elas precisarão ser adicionadas novamente se você recarregar o aplicativo.
                    </p>
                    <div class="mb-2">
                        <label for="localAudioFile" class="block text-sm font-medium text-gray-700">Arquivo de Áudio:</label>
                        <input type="file" id="localAudioFile" accept="audio/*" class="w-full p-2 border border-gray-300 rounded-md">
                    </div>
                    <div class="mb-4">
                        <label for="localCoverFile" class="block text-sm font-medium text-gray-700">Capa (Opcional):</label>
                        <input type="file" id="localCoverFile" accept="image/*" class="w-full p-2 border border-gray-300 rounded-md">
                    </div>
                    <button id="addLocalMusicButton" class="btn btn-primary w-full md:w-auto">Adicionar Música Local</button>
                </div>

                <div id="mySongsList" class="grid grid-cols-1 sm:grid-cols-2