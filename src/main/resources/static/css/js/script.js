document.addEventListener('DOMContentLoaded', function () {
    const listenButton = document.getElementById('listenButton');
    const gameForm = document.getElementById('gameForm');
    const feedbackDiv = document.getElementById('feedback');
    const newGameButton = document.getElementById('newGameButton');
    let currentLetter;

    // Function to generate and play the speech
    listenButton.addEventListener('click', function () {
        // Get the letters from the form buttons
        const options = Array.from(gameForm.elements)
            .filter(el => el.name === 'answer')
            .map(el => el.textContent);

        // Randomly select one letter to be the currentLetter
        currentLetter = options[Math.floor(Math.random() * options.length)];

        // Create a SpeechSynthesisUtterance to speak the current letter
        const msg = new SpeechSynthesisUtterance(currentLetter);
        window.speechSynthesis.speak(msg);
    });

    // Modify the form submission to check if the answer is correct
    gameForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission

        const selectedLetter = event.submitter.textContent;

        if (selectedLetter === currentLetter) {
            feedbackDiv.style.display = 'block';
            gameForm.style.display = 'none';
        }
    });

    // Start a new game
    newGameButton.addEventListener('click', function () {
        window.location.href = '/'; // This will refresh the page and start a new game
    });
});
