let counter = 1;

const counterValue = document.getElementById('counter-value');
const incrementBtn = document.getElementById('increment-btn');
const decrementBtn = document.getElementById('decrement-btn');

// To increment the value of counter
incrementBtn.addEventListener('click', () => {
    counter++;
    counterValue.value = counter;
});

// To decrement the value of counter
decrementBtn.addEventListener('click', () => {
    counter--;
    if (counter < 1)
        counter = 1;
    counterValue.value = counter;
});