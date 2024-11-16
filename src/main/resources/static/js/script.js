document.addEventListener('DOMContentLoaded', function () {
    const listenButton = document.getElementById('listenButton');
    const gameForm = document.getElementById('gameForm');
    const feedbackDiv = document.getElementById('feedback');
    const newGameButton = document.getElementById('newGameButton');
    const scoreDisplay = document.getElementById('scoreDisplay');
    let currentCorrectOption;

    // Function to fetch and play audio blob from the server
    listenButton.addEventListener('click', async function () {
        try {
            // Fetch the audio blob URL from the server endpoint (adjust endpoint as needed)
            const response = await fetch('/getAudioBlob');
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const audioBlob = await response.blob();
            const audioUrl = URL.createObjectURL(audioBlob);
            const audio = new Audio(audioUrl);

            // Play the audio
            audio.play();
        } catch (error) {
            console.error('Failed to fetch and play audio:', error);
        }
    });

    // Modify the form submission to check if the answer is correct
    gameForm.addEventListener('submit', function (event) {
        event.preventDefault();

        // Assume the clicked button has a data-value attribute with its corresponding text
        const selectedOption = event.submitter.dataset.value.trim();

        if (selectedOption === currentCorrectOption) {
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

        // Trigger a new game based on the current path
        if (window.location.pathname === '/letters') {
            window.location.href = '/letters/reset'; // Trigger reset for letters game
        } else if (window.location.pathname === '/numbers') {
            window.location.href = '/numbers/reset'; // Trigger reset for numbers game
        }
    });
});



