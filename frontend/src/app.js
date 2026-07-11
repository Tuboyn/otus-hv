const API_BASE = '/api';

const loadingEl = document.getElementById('loading');
const errorEl = document.getElementById('error');
const formEl = document.getElementById('survey-form');
const questionsEl = document.getElementById('questions');
const successEl = document.getElementById('success');

async function loadQuestions() {
    try {
        const response = await fetch(`${API_BASE}/questions`);
        if (!response.ok) throw new Error('Ошибка загрузки вопросов');
        const questions = await response.json();
        renderQuestions(questions);
        showForm();
    } catch (err) {
        showError(err.message);
    }
}

function renderQuestions(questions) {
    questionsEl.innerHTML = questions
        .map(q => `
            <div class="form-group">
                <label for="q${q.id}">${q.text}</label>
                <input type="text" id="q${q.id}" name="${q.id}" required>
            </div>
        `)
        .join('');
}

function showForm() {
    loadingEl.hidden = true;
    formEl.hidden = false;
}

function showError(message) {
    loadingEl.hidden = true;
    errorEl.textContent = message;
    errorEl.hidden = false;
}

function showSuccess() {
    formEl.hidden = true;
    successEl.hidden = false;
}

function collectAnswers() {
    const inputs = questionsEl.querySelectorAll('input');
    const answers = {};
    inputs.forEach(input => {
        answers[parseInt(input.name)] = input.value;
    });
    return answers;
}

async function submitAnswers(event) {
    event.preventDefault();
    const submitBtn = formEl.querySelector('button[type="submit"]');
    submitBtn.disabled = true;
    submitBtn.textContent = 'Отправка...';

    try {
        const response = await fetch(`${API_BASE}/answers`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ answers: collectAnswers() })
        });
        if (!response.ok) throw new Error('Ошибка отправки ответов');
        showSuccess();
    } catch (err) {
        submitBtn.disabled = false;
        submitBtn.textContent = 'Отправить';
        showError(err.message);
    }
}

formEl.addEventListener('submit', submitAnswers);

loadQuestions();
