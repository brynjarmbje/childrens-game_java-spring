document.addEventListener('DOMContentLoaded', function () {
    const listenButton = document.getElementById('listenButton');
    const gameForm = document.getElementById('gameForm');
    const feedbackDiv = document.getElementById('feedback');
    const newGameButton = document.getElementById('newGameButton');
    const scoreDisplay = document.getElementById('scoreDisplay');
    let currentLetter;

    // Function to generate and play the speech
    listenButton.addEventListener('click', function () {
        const options = Array.from(gameForm.elements)
            .filter(el => el.name === 'answer')
            .map(el => el.textContent.trim());

        currentLetter = options[Math.floor(Math.random() * options.length)];

        const msg = new SpeechSynthesisUtterance(currentLetter);
        msg.lang = 'en-US'; // Set the language to English (US)

        window.speechSynthesis.speak(msg);
    });

    // Modify the form submission to check if the answer is correct
    gameForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const selectedLetter = event.submitter.textContent.trim();

        if (selectedLetter === currentLetter) {
            feedbackDiv.classList.add('show');
            feedbackDiv.textContent = "That's correct!";
            newGameButton.style.display = 'flex'; // Show the "Start New Game" button
        } else {
            feedbackDiv.classList.add('show');
            feedbackDiv.textContent = "Try again!";
        }
    });

    // Start a new game when the "Start New Game" button is clicked
    newGameButton.addEventListener('click', function () {
        feedbackDiv.classList.remove('show');
        gameForm.style.display = 'block'; // Show the form again
        newGameButton.style.display = 'none'; // Hide the "Start New Game" button
        window.location.href = '/reset'; // This triggers the reset in the controller
    });
});



