
const counterValues = document.getElementsByClassName('counter-value');
const incrementButtons = document.getElementsByClassName('increment-btn');
const decrementButtons = document.getElementsByClassName('decrement-btn');

const prices = document.getElementsByClassName('price');
const totals = document.getElementsByClassName('total');

for (let i = 0; i < counterValues.length; i++) {
    totals[i].innerHTML = parseFloat(prices[i].innerHTML)*parseInt(counterValues[i].value);

    incrementButtons[i].addEventListener('click', () => {
        let counter = parseInt(counterValues[i].value);
        counter++;
        counterValues[i].value = counter;

        totals[i].innerHTML = parseFloat(prices[i].innerHTML)*parseInt(counterValues[i].value);
    });

    decrementButtons[i].addEventListener('click', () => {
        let counter = parseInt(counterValues[i].value);
        counter--;
        if (counter < 1)
            counter = 1;
        counterValues[i].value = counter;

        totals[i].innerHTML = parseFloat(prices[i].innerHTML)*parseInt(counterValues[i].value);
    });
}