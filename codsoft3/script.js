let currentInput = '';
let currentOperator = null;
let previousInput = '';

function appendNumber(number) {
    currentInput += number;
    updateDisplay();
}

function chooseOperator(operator) {
    if (currentInput === '') return;
    if (previousInput !== '') {
        calculateResult();
    }
    currentOperator = operator;
    previousInput = currentInput;
    currentInput = '';
}

function calculateResult() {
    let result;
    const prev = parseFloat(previousInput);
    const current = parseFloat(currentInput);
    if (isNaN(prev) || isNaN(current)) return;
    switch (currentOperator) {
        case '+':
            result = prev + current;
            break;
        case '-':
            result = prev - current;
            break;
        case '*':
            result = prev * current;
            break;
        case '/':
            result = prev / current;
            break;
        default:
            return;
    }
    currentInput = result;
    currentOperator = null;
    previousInput = '';
    updateDisplay();
}

function clearDisplay() {
    currentInput = '';
    currentOperator = null;
    previousInput = '';
    updateDisplay();
}

function updateDisplay() {
    document.getElementById('display').innerText = currentInput;
}
